package org.interstellar.urlshortener.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class UserDto {
    private String userId;
    @Nullable
    private String ipAddress;
}
