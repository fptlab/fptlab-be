package com.alda.fptlab.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object rejectedValue;
}
