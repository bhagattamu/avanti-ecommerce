/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.exception;

/**
 *
 * @author ACER
 */
public class OrderNotFoundException extends RuntimeException{
    private String message;

    public OrderNotFoundException() {}

    public OrderNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
