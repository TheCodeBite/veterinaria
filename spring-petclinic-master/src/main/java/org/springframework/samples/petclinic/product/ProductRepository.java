/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.product;

import java.util.Collection;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.vet.Vet;
import org.springframework.transaction.annotation.Transactional;

interface ProductRepository extends Repository<Product, Integer> {
    
    @Transactional(readOnly = true)
    Collection<Product> findAll() throws DataAccessException;
    
    @Query("SELECT product FROM Product product WHERE product.id = :id")
    @Transactional(readOnly = true)
    Product findById(@Param("id") Integer id);
    
    void save(Product product) throws DataAccessException;
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Product product where product.id = :id")
    void delete(@Param("id") Integer id);
    
}

