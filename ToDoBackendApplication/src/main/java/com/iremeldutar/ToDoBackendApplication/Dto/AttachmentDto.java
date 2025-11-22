
package com.iremeldutar.ToDoBackendApplication.Dto;

import java.time.LocalDateTime;
import lombok.*;

@Getter @NoArgsConstructor @AllArgsConstructor @Builder @Setter
public class AttachmentDto {
    private Long id;
    private String fileName;
    private String contentType;
    private Long size;
    private String objectKey;
    private String url;            
    private LocalDateTime createdAt;
}
