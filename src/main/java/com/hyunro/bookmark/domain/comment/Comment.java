package com.hyunro.bookmark.domain.comment;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    private String user_name;

    @ManyToOne
    @JoinColumn(name = "bookmark_id", updatable = false)
    private Bookmark bookmark;

    @Column(length = 500, nullable = false)
    private String content;


    @Builder
    public Comment(User user, String user_name, Bookmark bookmark, String content) {
        this.user = user;
        this.user_name = user_name;
        this.bookmark = bookmark;
        this.content = content;
    }

    public void update(String content) {
        this.content = content;
    }
}
