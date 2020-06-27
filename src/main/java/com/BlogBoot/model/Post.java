package com.BlogBoot.model;

import com.BlogBoot.model.enums.ModeratorStatus;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(name = "is_active", nullable = false)
    private Byte isActive;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum('NEW', 'ACCEPTED', 'DECLINED')")
    private ModeratorStatus moderatorStatus;

    @Column(name = "moderator_id")
    private Integer moderatorId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "view_count", nullable = false)
    private Integer viewCount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "tag2post",
            joinColumns = {@JoinColumn(name = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    List<Tag> postTags;

    @OneToMany(mappedBy = "post")
    private List<PostVote> postVote;

    @OneToMany(mappedBy = "post", orphanRemoval = true)
    private List<PostComments> postComments;

}
