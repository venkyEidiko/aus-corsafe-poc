package com.aus.corsafe.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class UnAuthorizedExceptionCls extends RuntimeException{

    private String errorMessage;
}
