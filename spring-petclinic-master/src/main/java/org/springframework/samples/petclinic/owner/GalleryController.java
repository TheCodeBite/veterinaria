/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.owner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Kevin Alejandro
 */
@Controller
@RequestMapping("/owners/{ownerId}")
public class GalleryController {
    private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";
    private final PetRepository pets;
    private final OwnerRepository owners;
    private final GalleryRepository gallery;
    
    private static String uploadDirectory = System.getProperty("user.dir")+"/src/main/resources/static/resources/imagesPets";

    public GalleryController(PetRepository pets, OwnerRepository owners, GalleryRepository gallery) {
        this.pets = pets;
        this.owners = owners;
        this.gallery = gallery;
    }
   
    @ModelAttribute("gallery")
    public Gallery loadPetWithVisit(@PathVariable("petId") int petId, Map<String, Object> model){
        Pet pet = this.pets.findById(petId);
        model.put("pet",pet);
        Gallery gallery = new Gallery();
        pet.addImage(gallery);
        return gallery;
    }
    
    @GetMapping("/pets/{petId}/gallerys")
    public String verGallery(@PathVariable("petId") int petId, Map<String, Object> model) {
        Pet pet = this.pets.findById(petId);
        model.put("pet", pet);
        return "gallerys/gallery";
    }
    
    @PostMapping("/pets/{petId}/gallerys")
    public String processNewPhoto(@RequestParam("file") MultipartFile[]f,@Valid Gallery gallery, BindingResult result) {
        StringBuilder fileNames = new StringBuilder();
        if (result.hasErrors()) {
            return "redirect:/owners/{ownerId}";
        } else {
            for(MultipartFile file:f){
                System.out.println("ARCHIVO: "+file.getOriginalFilename());
                Path fileNameAndPath = Paths.get(uploadDirectory,file.getOriginalFilename());
                fileNames.append(file.getOriginalFilename());
                try {
                    Files.write(fileNameAndPath, file.getBytes());
                } catch (IOException ex) {
                    Logger.getLogger(PetController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            this.gallery.save(gallery);          
            return "redirect:/owners/{ownerId}/pets/{petId}/gallerys";           
        }
    }
   
}
