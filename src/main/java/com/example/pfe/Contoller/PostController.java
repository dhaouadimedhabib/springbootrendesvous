package com.example.pfe.Contoller;

import com.example.pfe.Domain.Post;
import com.example.pfe.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService ip;


    @PostMapping
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        try {
            // Appeler la méthode savePost et renvoyer une réponse HTTP appropriée
            Post savedPost = ip.savePost(post);
            return new ResponseEntity<>(savedPost, HttpStatus.CREATED);
        } catch (Exception e) {
            // Gérer les erreurs et renvoyer une réponse en cas d'échec
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add/{userId}")
    public ResponseEntity<Post> addPost(@RequestBody Post post, @PathVariable Long userId) {
        Post createdPost = ip.addPost(post, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    /*
    @DeleteMapping("dPost/{idp}")
    void deletePost(@PathVariable("idp") int idp)
    {
        ip.deletePost(idp);
    }

    @PutMapping("updatePost")
    void updatePost(@RequestBody Post p)
    {

        ip.updatePost(p);
    }
*/
    @GetMapping("posts")
    List<Post> getallposts()
    {
        return ip.allPost();

}
    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = ip.getPostById(id);
        return ResponseEntity.ok(post);
    }


    @GetMapping("/user/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return ip.getPostsByUserId(userId);
    }
}

