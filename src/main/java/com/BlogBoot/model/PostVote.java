package com.BlogBoot.model;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "post_votes")
public class PostVote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private Byte value;


}
