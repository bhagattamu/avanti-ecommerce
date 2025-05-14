/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.util;

import org.springframework.stereotype.Component;

/**
 *
 * @author ACER
 */
@Component
public class ErrorMapperUtil {

    public String createErrorMessage(Throwable e) {
        String errorMsg = e.getMessage();
        return errorMsg;
    }

    public String createErrorMessage(String message) {
        return message;
    }
}
