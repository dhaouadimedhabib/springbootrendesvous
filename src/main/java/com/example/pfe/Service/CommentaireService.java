package com.example.pfe.Service;

import com.example.pfe.Domain.Commentaire;
import com.example.pfe.Domain.Post;
import com.example.pfe.Repo.CommentaireRepo;
import com.example.pfe.Repo.Postrepo;
import com.example.pfe.Repo.UserRepo;
import com.example.pfe.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentaireService {
    public static int idstatic = 0;
    @Autowired
    CommentaireRepo cr;
    @Autowired
    Postrepo pr;
    @Autowired
    UserRepo userRepository;

    @Transactional
    public void addComment(Commentaire c, Long postId) {
        // Log attempt to find post by ID
        System.out.println("Attempting to find post with ID: " + postId);
        Optional<Post> postOptional = pr.findById((long) Math.toIntExact(postId));

        if (postOptional.isPresent()) {
            // Set post to the comment
            System.out.println("Post found. Assigning post to comment.");
            c.setPostComment(postOptional.get());

            // Attempt to save the comment
            System.out.println("Saving comment: " + c);
            cr.save(c);
        } else {
            throw new PostNotFoundException("Post avec l'ID " + postId + " non trouvé");
        }
    }
    public void updateComment(Commentaire c) {
        if (cr.findById(c.getCommentId()).isPresent())
            cr.save(c);
        else
            System.out.println("Comment doesnt exist");

    }



    public String deleteComment(int idc) {
        if (cr.findById((long) idc).isPresent()) {
            cr.delete(cr.findById((long) idc).get());
            return "The comment is deleted successfully";
        } else
            return "The comment doesn't exist";



    }

    // USER !!!!

    public List<Commentaire> comments() {
        return cr.findAll();

    }

    public Commentaire retrieveCommentById(Integer commentId) {
        return cr.findById(Long.valueOf(commentId)).orElse(null);
    }

    public List<Commentaire> comments(Post p) {
        return cr.getAllComments(p);

    }
    public List<Commentaire> commentsUser(long idu) {

        List<Commentaire> comments = new ArrayList<>();
        return comments;
    }



    public List<Commentaire> getCommentsByPostId(int postId) {
        Post post = pr.findById((long) postId).orElse(null);

        return cr.getAllComments(post);

    }

    public List<Commentaire> getCommentsByPostId(Long postId) {
        return cr.findByPostComment_PostId(postId);
    }

}
