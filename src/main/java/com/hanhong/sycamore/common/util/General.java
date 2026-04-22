package com.hanhong.sycamore.common.util;


/**
 * Sycamore Restaurant System
 * <p>
 * Copyright (c) 2026 sycamore team. All rights reserved.
 * <p>
 * 免责声明：
 * 本代码仅供内部开发与学习使用，未经授权不得外传或用于商业用途。
 *
 * @author Liu qingyang
 * @version 1.0.0
 * @since 2026-04
 */

import java.security.MessageDigest;

/**
 * General helper classs
 */
public class General {

    /**
     * 加密密码
     */
    public static String encodePassword(String rawPassword) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(rawPassword.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 校验密码
     */
    public static boolean matchPassword(String rawPassword, String encodedPassword) {
        return encodePassword(rawPassword).equals(encodedPassword);
    }
}
