package com.example.pfe.Service;

import com.example.pfe.Domain.Post;
import com.example.pfe.Domain.StringSimilarity;
import com.example.pfe.Domain.User;
import com.example.pfe.Repo.Postrepo;
import com.example.pfe.Repo.UserRepo;
import com.example.pfe.exception.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    Postrepo postrepo;
    UserRepo userRepository;
    public Post addPost(Post post, Long userId) {
        // Vérifier si l'utilisateur existe
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec ID : " + userId));

        // Associer l'utilisateur au post
        post.setUserPost(user);

        // Sauvegarder le post
        return postrepo.save(post);
    }

    public Post savePost(Post post) throws Exception {

        return postrepo.save(post);
    }
    public Post getPostById(Long id) {
        return postrepo.findById(id)
                .orElseThrow(() -> new PostNotFoundException("Post with ID " + id + " not found"));
    }
    /*
    public void updatePost(Post p) {
        if (postrepo.findById(Math.toIntExact(p.getPostId())).isPresent())
            postrepo.save(p);
        else
            System.out.println("doesnt exist");
    }

    public String deletePost(int idp) {
        if (postrepo.findById(idp).isPresent()) {
            postrepo.delete(postrepo.findById(idp).get());
            return "The comment is deleted successfully";
        } else
            return "The comment doesn't exist";
    }

*/

    public List<Post> getPostsByUserId(Long userId) {
        return postrepo.findByUserPost_UserId(userId);
    }

    public List<Post> allPost() {
        return postrepo.findAll();
    }

}
