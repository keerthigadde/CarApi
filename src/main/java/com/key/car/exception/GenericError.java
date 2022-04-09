package com.key.car.exception;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@Data
@RequiredArgsConstructor
public class GenericError {
    private final Date timestamp;
    private final String message;
    private final String details;
}
