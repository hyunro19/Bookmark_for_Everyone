package com.hyunro.bookmark.domain.bookmark;

import com.hyunro.bookmark.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
}
