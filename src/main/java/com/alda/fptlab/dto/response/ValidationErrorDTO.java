package com.alda.fptlab.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationErrorDTO {
    private String object;
    private String field;
    private String message;
    private Object rejectedValue;
}
