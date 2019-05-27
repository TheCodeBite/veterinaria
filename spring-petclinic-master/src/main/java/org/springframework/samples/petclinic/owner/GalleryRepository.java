/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


/**
 *
 * @author Kevin Alejandro
 */
public interface GalleryRepository extends Repository<Gallery,Integer>{
    void save(Gallery gallery) throws DataAccessException;
    
    List<Gallery> findByPetId(Integer id);
    
}
