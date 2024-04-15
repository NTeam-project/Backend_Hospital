package com.Nteam.backend.hospital.global.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseException extends Exception {
    private ResponseStatus status;
}
