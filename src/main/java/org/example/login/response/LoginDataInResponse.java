package org.example.login.response;

import java.util.List;

public class LoginDataInResponse {
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private UserDataInResponse user;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public UserDataInResponse getUser() {
        return user;
    }
    public void setUser(UserDataInResponse user) {
        this.user = user;
    }
    public String getAccessToken() {
        return accessToken;
    }
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public String getRefreshToken() {
        return refreshToken;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
