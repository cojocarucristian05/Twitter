package com.ligaaclabs.twitter.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.UUID;

@Data
@Entity
@Table(name = "replies")
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "content", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    public User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post parentPost;

//    @ManyToOne
//    @JoinColumn(name = "parent_reply")
//    private Reply parentReply;
//
//    @Column(name = "reply_public", nullable = false)
//    private boolean isPublic;
}
