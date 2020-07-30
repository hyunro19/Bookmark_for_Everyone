package com.hyunro.bookmark.domain.posts;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posts_id;

    private String topic;

    @Column(length = 500, nullable = false)
    private String src_url;

    private String src_title;

    @Column(length = 500)
    private String src_description;

    @Column(length = 500)
    private String src_img;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private String user_name;

    @Builder
    public Posts(String user_name, String topic, String src_url, String src_title, String src_description, String src_img, User user) {
        this.user_name = user_name;
        this.topic = topic;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.src_img = src_img;
        this.user = user;
    }

    public void update(String topic, String src_url, String src_title, String src_description, String src_img) {
        this.topic = topic;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.src_img = src_img;
    }
}
