package com.hanhong.sycamore.common.util;
/**
 * Sycamore Restaurant System
 *
 * Copyright (c) 2026 sycamore team. All rights reserved.
 *
 * 免责声明：
 * 本代码仅供内部开发与学习使用，未经授权不得外传或用于商业用途。
 *
 * @author Liu qingyang
 * @version 1.0.0
 * @since 2026-04
 */
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    private static final int SUCCESS_CODE = 0;
    private static final String SUCCESS_MESSAGE = "success";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    private int code;
    private String message;
    private T data;
    private String timestamp;
    private String requestId;

    public static <T> Result<T> success() {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, null, getCurrentTimestamp(), null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(SUCCESS_CODE, SUCCESS_MESSAGE, data, getCurrentTimestamp(), null);
    }

    public static <T> Result<T> success(String message, T data) {
        return new Result<>(SUCCESS_CODE, message, data, getCurrentTimestamp(), null);
    }

    public static <T> Result<T> error(int code, String message) {
        return new Result<>(code, message, null, getCurrentTimestamp(), null);
    }

    public static <T> Result<T> error(String message) {
        return new Result<>(500, message, null, getCurrentTimestamp(), null);
    }

    public boolean isSuccess() {
        return this.code == SUCCESS_CODE;
    }

    private static String getCurrentTimestamp() {
        return LocalDateTime.now().format(FORMATTER);
    }
}
