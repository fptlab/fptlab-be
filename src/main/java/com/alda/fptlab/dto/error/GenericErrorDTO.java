package com.alda.fptlab.dto.error;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class GenericErrorDTO {
    private int status;
    private String message;
}


