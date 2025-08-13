package com.example.pfe.Contoller;

import com.example.pfe.Domain.Commentaire;
import com.example.pfe.Service.CommentaireService;
import com.example.pfe.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/api/comment")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    CommentaireService ic;
    @GetMapping("comments/{postId}")
    public List<Commentaire> getCommentsByPostId(@PathVariable int postId) {
        return ic.getCommentsByPostId(postId);
    }

    @PostMapping("/addComment/{postId}")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody Commentaire c) {
        try {
            c.setCommentDate(LocalDateTime.now());
            ic.addComment(c, postId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (PostNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur interne : " + e.getMessage());
        }
    }

    @DeleteMapping("dComment/{idc}")
    void deleteComment(@PathVariable("idc") int idc)
    {

        ic.deleteComment(idc);
    }

    @PutMapping("updateComment")
    void updateComment(@RequestBody Commentaire c)
    {

        ic.updateComment(c);
    }

    @GetMapping("comments")
    List<Commentaire> getallcomments()
    {
        return ic.comments();
    }


    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Commentaire>> getCommentsByPostId(@PathVariable Long postId) {
        List<Commentaire> comments = ic.getCommentsByPostId(postId);
        return ResponseEntity.ok(comments);
    }

}