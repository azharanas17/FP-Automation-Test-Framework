package com.automation.api.utils;

import io.restassured.response.Response;

public class ScenarioContext {

    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    private static final ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setResponse(Response res) {
        response.set(res);
    }

    public static Response getResponse() {
        return response.get();
    }

    public static void setUserId(String id) {
        userId.set(id);
    }

    public static String getUserId() {
        return userId.get();
    }

    public static void clear() {
        response.remove();
        userId.remove();
    }
}
