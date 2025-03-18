package org.interstellar.urlshortener.dto;

import org.springframework.lang.Nullable;


public class UserDto {
    private String userId;
    @Nullable
    private String ipAddress;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
