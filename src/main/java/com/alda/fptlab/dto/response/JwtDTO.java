package com.alda.fptlab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtDTO {
    private String token;
    private String refreshToken;
    @Builder.Default
    private String type = "Bearer";
    private String user;
}
