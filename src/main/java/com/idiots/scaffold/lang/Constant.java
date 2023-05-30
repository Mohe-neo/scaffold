package com.idiots.scaffold.lang;

import cn.hutool.core.lang.Pair;
import org.springframework.http.HttpMethod;

import java.util.LinkedList;
import java.util.List;

/**
 * @author idiots-devil
 * @since 2023-05-28
 */
public class Constant {
    /**
     * Session 中存储图形验证码的属性名
     */
    public static final String CAPTCHA_SESSION_KEY = "CAPTCHA_SESSION_KEY";

    public static final String V1="/api/v1/scaffold";

    public static final List<Pair<HttpMethod, String>> WHITES = new LinkedList<>();

    static {
        WHITES.add(Pair.of(HttpMethod.POST, V1 + "/login"));
        WHITES.add(Pair.of(HttpMethod.POST, V1 + "/logout"));
        WHITES.add(Pair.of(HttpMethod.GET, V1 + "/verify"));
        WHITES.add(Pair.of(HttpMethod.GET, "/favicon.ico"));
        WHITES.add(Pair.of(HttpMethod.GET, "/swagger-ui.html"));
        WHITES.add(Pair.of(HttpMethod.GET, "/swagger-ui/**"));
        WHITES.add(Pair.of(HttpMethod.GET, "/v3/**"));
    }
}
