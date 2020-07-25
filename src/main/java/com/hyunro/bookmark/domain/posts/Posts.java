package com.hyunro.bookmark.domain.posts;

import com.hyunro.bookmark.domain.BaseTimeEntity;
import com.hyunro.bookmark.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 Annotation 선택(코드간결화) // 클래스 내 모든 필드의 Getter메소드 자동 생성
@NoArgsConstructor // 롬복 Annotation 선택(코드간결화) // 기본 생성자 자동 추가, public Post(){}와 같은 효과
@Entity // JPA Annotation 필수,테이블과 링크될 클래스임을 표시. 흔히 카멜표기법을 소문자_소문자_소문자 식으로 네이밍
public class Posts extends BaseTimeEntity { // 실제 DB의 테이블과 매칭될 클래스, Entity클래스 라고도 함
    // JPA를 사용할 경우, DB 데이터에 작업할 경우, 실제 쿼리를 날리기 보다 이 Entity클래스의 수정을 통해 작업

    @Id // 해당 테이블의 PK필드라는 표시
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK생성 규칙을 나타낸다. GenerationType.IDENTITY하면 Auto-Increment
    private Long posts_id;

    private String topic;

    @Column(length = 500, nullable = false)
    private String src_url;

    private String src_title;

    @Column(length = 500)
    private String src_description;

    private String user_name;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false)
    private User user;

    @Builder // 해당 클래스의 빌더 패턴 클래스 생성 // 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String user_name, String topic, String src_url, String src_title, String src_description, User user) {
        this.user_name = user_name;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
        this.user = user;
    }

    // Entity클래스에서는 절대 Setter 메소드를 만들지 않는다.
    // 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가.

    public void update(String topic, String src_url, String src_title, String src_description) {
        this.topic = topic;
        this.src_url = src_url;
        this.src_title = src_title;
        this.src_description = src_description;
    }
}
