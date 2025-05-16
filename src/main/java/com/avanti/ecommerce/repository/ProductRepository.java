/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.repository;

import com.avanti.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ACER
 */
public interface ProductRepository extends JpaRepository<Product, Long> {}
