package com.aus.corsafe.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class LoginResponseCls {
    @JsonProperty("jwtToken")
    private String jwtToken;
    @JsonProperty("refreshToken")
    private String refreshToken;
}
