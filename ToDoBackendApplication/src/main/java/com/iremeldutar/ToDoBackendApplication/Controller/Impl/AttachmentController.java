// src/main/java/.../Controller/Impl/AttachmentController.java
package com.iremeldutar.ToDoBackendApplication.Controller.Impl;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.iremeldutar.ToDoBackendApplication.Dto.AttachmentDto;
import com.iremeldutar.ToDoBackendApplication.Services.IAttachmentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/attachments")
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", exposedHeaders = "*")
@RequiredArgsConstructor
public class AttachmentController {

    private final IAttachmentService service;

   
    @PostMapping("/{userId}/{todoId}")
    public ResponseEntity<AttachmentDto> upload(
            @PathVariable Integer userId,
            @PathVariable Integer todoId,
            @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok(service.upload(userId, todoId, file));
    }

   
    @GetMapping("/by-todo/{todoId}")
    public ResponseEntity<List<AttachmentDto>> list(@PathVariable Integer todoId) {
        return ResponseEntity.ok(service.listByTodo(todoId));
    }

  
    @DeleteMapping("/{attachmentId}/{userId}")
    public ResponseEntity<Void> delete(
            @PathVariable Long attachmentId,
            @PathVariable Integer userId) {
        service.delete(userId, attachmentId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{attachmentId}/url")
    public ResponseEntity<String> url(@PathVariable Long attachmentId,
                                      @RequestParam(defaultValue="60") int minutes) {
        return ResponseEntity.ok(service.presignedUrl(attachmentId, minutes));
    }
}
