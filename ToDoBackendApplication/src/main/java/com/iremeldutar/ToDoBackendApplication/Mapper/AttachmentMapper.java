package com.iremeldutar.ToDoBackendApplication.Mapper;

import com.iremeldutar.ToDoBackendApplication.Dto.AttachmentDto;
import com.iremeldutar.ToDoBackendApplication.entity.Attachment;

public final class AttachmentMapper {

    private AttachmentMapper() {}

    public static AttachmentDto toDto(Attachment a) {
        if (a == null) return null;

        return AttachmentDto.builder()
                .id(a.getId())
                .fileName(a.getFileNameString())
                .contentType(a.getContentType())
                .size(a.getSize())
                .objectKey(a.getObjectKey())
                .createdAt(a.getCreatedAt())
                .build();
    }
}
