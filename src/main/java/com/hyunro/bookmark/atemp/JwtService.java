package com.hyunro.bookmark.atemp;

import java.util.Map;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    Map<String, Object> get(String key);
    Long getMemberId();
    boolean isUsable(String jwt);

}
