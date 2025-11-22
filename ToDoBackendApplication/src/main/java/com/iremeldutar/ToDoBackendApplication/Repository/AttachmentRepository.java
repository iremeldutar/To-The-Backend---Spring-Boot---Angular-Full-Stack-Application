package com.iremeldutar.ToDoBackendApplication.Repository;

import java.util.List;
import java.util.Optional;                // ✅ Doğru Optional importu

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iremeldutar.ToDoBackendApplication.entity.Attachment;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {

    // Bir todo'ya bağlı tüm ekler
    List<Attachment> findByTodos_Id(Integer todoId);

    // (Opsiyonel) N+1 azaltmak istersen
    @EntityGraph(attributePaths = {"todos", "todos.user"})
    List<Attachment> findAllByTodos_Id(Integer todoId);

    // Yetkili silme/erişim kontrolü: attachment ilgili kullanıcıya mı ait?
    Optional<Attachment> findByIdAndTodos_User_Id(Long attachmentId, Integer userId);

    // Hızlı sahiplik kontrolü
    boolean existsByIdAndTodos_User_Id(Long attachmentId, Integer userId);

    // (Opsiyonel) Kullanıcı bazında bir todo'nun tüm dosyalarını sil
    long deleteByTodos_IdAndTodos_User_Id(Integer todoId, Integer userId);
}
