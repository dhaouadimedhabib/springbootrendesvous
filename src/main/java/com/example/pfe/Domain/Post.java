package com.example.pfe.Domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Post {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long postId;

    @Column
    //@NotBlank(message = "the Title of tte post is required!")

    private String title;
    @Column
    private String image;
    @Lob
    @Column(name = "post_content", columnDefinition = "LONGTEXT")

    //@NotBlank(message = "the Content of tte post is required!")
    private String postContent;

    @Column
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime postdate;

    @Column
    private Integer countLike;


    @Column
    @Enumerated(EnumType.STRING)
    private State state;




    @OneToMany(cascade = CascadeType.ALL,mappedBy = "postComment")
    @JsonIgnore
    private Set<Commentaire> comments;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_post_id")
    @JsonIgnore
    private User userPost;
    private String followers;



}