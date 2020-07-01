package com.hyunro.bookmark.domain.thumb;

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
public class Thumb extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "thumb_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "bookmark_id", updatable = false)
    private Bookmark bookmark;

    @Builder
    public Thumb(User user, Bookmark bookmark) {
        this.user = user;
        this.bookmark = bookmark;
    }
}
