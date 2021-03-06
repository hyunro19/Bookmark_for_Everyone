package com.hyunro.bookmark.domain.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hyunro.bookmark.domain.BaseTimeEntity;

import com.hyunro.bookmark.domain.posts.Posts;
import com.hyunro.bookmark.web.dto.user.UserUpdateRequestDto;
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
    private Long user_id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy="user")
    private List<Posts> posts = new ArrayList<Posts>();

    @Builder
    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User update(UserUpdateRequestDto requestDto) {
        if (requestDto.getName_new() != null && !requestDto.getName_new().equals("")) {
            this.name = requestDto.getName_new();
        }
        if (requestDto.getPassword_new() != null && !requestDto.getPassword_new().equals("")) {
            this.password = requestDto.getPassword_new();
        }
        return this;
    }

}
