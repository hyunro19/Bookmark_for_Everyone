package com.hyunro.bookmark.domain.bookmark;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    private String user_name;

    private String topic;

    @Column(length = 500, nullable = false)
    private String url;

    private String src_title;

    @Column(length = 500)
    private String src_description;

    @Column(length = 500)
    private String content;

    private Integer number_coment;

    private Integer number_like;

    private Integer number_share;

    private Boolean is_public;

    @Builder
    public Bookmark(User user, String user_name, String topic, String url, String src_title, String src_description, String content, Boolean is_public) {
        this.user = user;
        this.user_name = user_name;
        this.topic = topic;
        this.url = url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.content = content;
        this.is_public = is_public;
    }

    public void update(String topic, String url, String src_title, String src_description, String content, Boolean is_public) {
        this.topic = topic;
        this.url = url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.content = content;
        this.is_public = is_public;
    }
}
