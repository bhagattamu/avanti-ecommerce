/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avanti.ecommerce;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ACER
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer{

    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String dirName = "product-images";
        System.out.println("com.avanti.ecommerce.MvcConfig.addResourceHandlers()");
        Path productImagesDir = Paths.get(dirName);
        String productImagesPath = productImagesDir.toFile().getAbsolutePath();
        System.out.println(productImagesPath);
        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + productImagesPath + "/");

    }
}
