/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.product;


import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlElement;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.Person;
import static org.thymeleaf.util.StringUtils.append;

@Entity
@Table(name = "product")
public class Product {
    
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public boolean isNew()
    {
        return this.id == null;
    }
    
    @Column(name = "nombre")
    @NotEmpty
    private String nombre;
      
    @Column(name = "descripcion")
    @NotEmpty
    private String descripcion;
    
    @Column(name = "precio")
    @NotEmpty
    private String precio;
    
    @Column(name = "existencia")
    @NotEmpty
    private String existencia;
    
    @Column (name = "imagenes")
    @NotEmpty
    private String imagenes; 
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }
    
     public String getImagenes() {
        return imagenes;
    }
       
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getExistencia() {
        return existencia;
    }

    public void setExistencia(String existencia) {
        this.existencia = existencia;
    }
    
    @Override
    public String toString()
    {
        return new ToStringCreator(this)
                .append("id",this.getId()).append("new",this.isNew())
                .append("nombre",this.getNombre())
                .append("descripcion",this.getDescripcion())
                .append("precio",this.getPrecio())
                .append("imagenes",this.getImagenes()).toString();
    }            
}
