/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avanti.ecommerce.repository;

import com.avanti.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ACER
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}
