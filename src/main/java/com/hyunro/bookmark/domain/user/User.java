package com.hyunro.bookmark.domain.user;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.bookmark.Bookmark;
import com.hyunro.bookmark.domain.comment.Comment;
import com.hyunro.bookmark.domain.thumb.Thumb;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy="user")
    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();

    @OneToMany(mappedBy="user")
    private List<Comment> comments = new ArrayList<Comment>();

    @OneToMany(mappedBy="user")
    private List<Thumb> thumbs = new ArrayList<Thumb>();

    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }
}
