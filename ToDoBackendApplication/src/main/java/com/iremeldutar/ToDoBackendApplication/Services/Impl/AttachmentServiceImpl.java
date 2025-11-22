package com.iremeldutar.ToDoBackendApplication.Services.Impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iremeldutar.ToDoBackendApplication.Dto.AttachmentDto;
import com.iremeldutar.ToDoBackendApplication.Mapper.AttachmentMapper;
import com.iremeldutar.ToDoBackendApplication.Repository.AttachmentRepository;
import com.iremeldutar.ToDoBackendApplication.Repository.ToDoRepository;
import com.iremeldutar.ToDoBackendApplication.Services.IAttachmentService;
import com.iremeldutar.ToDoBackendApplication.entity.Attachment;
import com.iremeldutar.ToDoBackendApplication.entity.Todos;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentServiceImpl implements IAttachmentService {

    private final AttachmentRepository attachmentRepo;
    private final ToDoRepository toDoRepo;
    private final FileServices fileServices;

    @Override
    public AttachmentDto upload(Integer userId, Integer todoId, MultipartFile file) {
        
        Todos todo = toDoRepo.findByIdAndUser_Id(todoId, userId)
            .orElseThrow(() -> new IllegalArgumentException("Todo bulunamadı veya kullanıcıya ait değil"));

       
        String originalName = file.getOriginalFilename() != null ? file.getOriginalFilename() : "file";
        String safeName = originalName.replaceAll("[\\\\/]+", "_");
        String objectKey = userId + "/" + todoId + "/" + UUID.randomUUID() + "_" + safeName;

        
        try {
        	
            String ct = file.getContentType() != null ? file.getContentType() : "application/octet-stream";
            
            fileServices.putObject(objectKey, file.getInputStream(), file.getSize(), ct);
        } catch (Exception e) {
            throw new RuntimeException("MinIO yükleme hatası: " + e.getMessage(), e);
        }

    
        Attachment a = Attachment.builder()
            .todos(todo)
            .objectKey(objectKey)
            .fileNameString(safeName)
            .contentType(file.getContentType())
            .size(file.getSize())
            .build();

        a = attachmentRepo.save(a);
 
   
        AttachmentDto dto = AttachmentMapper.toDto(a);
        try {
            dto.setUrl(fileServices.presignedGetUrl(objectKey, 60)); 
        } catch (Exception ignore) {}
        return dto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttachmentDto> listByTodo(Integer todoId) {
        return attachmentRepo.findByTodos_Id(todoId).stream()
            .map(AttachmentMapper::toDto)
            .map(dto -> {
                try {
                    dto.setUrl(fileServices.presignedGetUrl(dto.getObjectKey(), 60));
                } catch (Exception ignore) {}
                return dto;
            })
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer userId, Long attachmentId) {
      
        Attachment a = attachmentRepo.findByIdAndTodos_User_Id(attachmentId, userId)
            .orElseThrow(() -> new IllegalArgumentException("Dosya bulunamadı ya da yetkiniz yok"));

        
        try {
            fileServices.deleteObject(a.getObjectKey());
        } catch (Exception e) {
            throw new RuntimeException("MinIO silme hatası: " + e.getMessage(), e);
        }
        attachmentRepo.delete(a);
    }

    @Override
    @Transactional(readOnly = true)
    public String presignedUrl(Long attachmentId, int minutes) {
        Attachment a = attachmentRepo.findById(attachmentId)
            .orElseThrow(() -> new IllegalArgumentException("Attachment bulunamadı"));
        try {
            return fileServices.presignedGetUrl(a.getObjectKey(), minutes);
        } catch (Exception e) {
            throw new RuntimeException("URL üretilemedi: " + e.getMessage(), e);
        }
    }
}

