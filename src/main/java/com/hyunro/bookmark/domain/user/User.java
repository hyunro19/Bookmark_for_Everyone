package com.hyunro.bookmark.domain.user;

import com.hyunro.bookmark.domain.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name="user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")
})
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;
//
//    @OneToMany(mappedBy="user")
//    private List<Bookmark> bookmarks = new ArrayList<Bookmark>();
//
//    @OneToMany(mappedBy="user")
//    private List<Comment> comments = new ArrayList<Comment>();
//
//    @OneToMany(mappedBy="user")
//    private List<Thumb> thumbs = new ArrayList<Thumb>();

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User update(String name, String password) {
        this.name = name;
        this.password = password;
        return this;
    }

}
