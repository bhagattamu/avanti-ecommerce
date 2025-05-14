/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce.exception;

/**
 *
 * @author ACER
 */
public class CategoryNotFoundException extends RuntimeException{
    private String message;

    public CategoryNotFoundException() {}

    public CategoryNotFoundException(String msg) {
        super(msg);
        this.message = msg;
    }
}
