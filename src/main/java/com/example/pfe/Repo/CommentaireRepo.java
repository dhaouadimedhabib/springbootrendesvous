package com.example.pfe.Repo;


import com.example.pfe.Domain.Commentaire;
import com.example.pfe.Domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentaireRepo extends JpaRepository<Commentaire, Long> {
    List<Commentaire> findByPostComment_PostId(Long postId); // Assuming Post entity has a field named postId

    @Query("SELECT c FROM Commentaire c")
    List<Commentaire> getAllComments();
    @Query("SELECT c FROM Commentaire c where c.postComment=:post ")
    public List<Commentaire> getAllComments(@Param("post") Post post);
    @Query("SELECT c FROM Commentaire c where c.postComment=:post ")
    List<Commentaire> findAllByCommentPost(Post post);
}
