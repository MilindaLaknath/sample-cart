package com.milinda.assessment.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonInclude
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMsg {
    private String error;
    private String message;
}
