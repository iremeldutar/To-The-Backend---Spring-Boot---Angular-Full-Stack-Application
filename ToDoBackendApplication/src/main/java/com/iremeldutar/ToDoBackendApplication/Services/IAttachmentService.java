package com.iremeldutar.ToDoBackendApplication.Services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.iremeldutar.ToDoBackendApplication.Dto.AttachmentDto;

public interface IAttachmentService {
	   AttachmentDto upload(Integer userId, Integer todoId, MultipartFile file);
	    List<AttachmentDto> listByTodo(Integer todoId);
	    void delete(Integer userId, Long attachmentId);
	    String presignedUrl(Long attachmentId, int minutes);
}
