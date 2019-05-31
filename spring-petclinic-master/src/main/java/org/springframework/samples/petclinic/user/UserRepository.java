/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

import java.util.Collection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.transaction.annotation.Transactional;


interface UserRepository extends Repository<User, Integer> {
    
    @Transactional(readOnly = true)
    Collection<User> findAll() throws DataAccessException;
    
    @Query("SELECT user FROM User user WHERE user.id = :id")
    @Transactional(readOnly = true)
    User findById(@Param("id") Integer id);
    
    void save(User user) throws DataAccessException;

    @Transactional
    @Modifying
    @Query("DELETE FROM User user where user.id = :id")
    void deleteUser(@Param("id")  Integer id);
    
   
}

