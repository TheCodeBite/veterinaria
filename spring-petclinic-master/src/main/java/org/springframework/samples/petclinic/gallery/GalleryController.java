/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.gallery;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Kevin Alejandro
 */
@Controller
public class GalleryController {
    
    @GetMapping({"/mascotitas","/algo"})
    public String galley(){
        
        return "gallerys/gallery";
    }
}
