package com.hyunro.bookmark.domain.bookmark;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.comment.Comment;
import com.hyunro.bookmark.domain.thumb.Thumb;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="bookmark")
public class Bookmark extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
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

    private Integer number_comment;

    private Integer number_thumb;

    private Integer number_share;

    private Integer is_public;

    @OneToMany(mappedBy="bookmark")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy="bookmark")
    private List<Thumb> thumbs = new ArrayList<Thumb>();

    @Builder
    public Bookmark(User user, String user_name, String topic, String url, String src_title, String src_description, String content,
                    Integer number_comment, Integer number_thumb, Integer number_share, Integer is_public) {
        this.user = user;
        this.user_name = user_name;
        this.topic = topic;
        this.url = url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.content = content;
        this.number_comment = number_comment;
        this.number_thumb = number_thumb;
        this.number_share = number_share;
        this.is_public = is_public;
    }

    public void update(String topic, String url, String src_title, String src_description, String content,
                       Integer number_comment, Integer number_thumb, Integer number_share, Integer is_public) {
        this.topic = topic;
        this.url = url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.content = content;
        this.number_comment = number_comment;
        this.number_thumb = number_thumb;
        this.number_share = number_share;
        this.is_public = is_public;
    }
}
