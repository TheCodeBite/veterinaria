/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Map;
import org.springframework.samples.petclinic.owner.Owner;

@Controller
public class ProductController {
    
    
    private final ProductRepository product;
    
    public ProductController(ProductRepository productRepository)
    {
        this.product = productRepository;
    }
    
    @GetMapping("/products")
    public String processFindForm(Product product, BindingResult result, Map<String, Object> model){
        Collection<Product> results = this.product.findAll();
        model.put("products",results);
        return "products/index";
    }
    
    @GetMapping("/products/create")
    public String initCreationForm(Map<String, Object> model)
    {
        Product product = new Product();
        model.put("product",product);
        return "/products/create";
    }
    
    @PostMapping("/products/store")
    public String processCreationForm(@Valid Product product, BindingResult result)
    {
        if(result.hasErrors())
        {
            return "/products/create";
        }else
        {
         this.product.save(product);   
         return "redirect:/products";
        }
    }
    
    @GetMapping("/products/{productId}")
    public ModelAndView showProduct(@PathVariable("productId") int productId) {
        ModelAndView mav = new ModelAndView("products/show");
        mav.addObject(this.product.findById(productId));
        return mav;
    }
    @GetMapping("/products/{productId}/edit")
    public String initUpdateOwnerForm(@PathVariable("productId") int productId, Model model) {
        Product product = this.product.findById(productId);
        model.addAttribute(product);
        return "products/edit";
    }

    @PostMapping("/products/{productId}/edit")
    public String processUpdateOwnerForm(@Valid Product product, BindingResult result, @PathVariable("productId") Integer productId) {
        if (result.hasErrors()) {
            return "products/index";
        } else {
            product.setId(productId);
            this.product.save(product);
            return "redirect:/products/{productId}";
        }
    }
    @PostMapping("/products/{productId}/delete")
    public String delete(@Valid Product product, BindingResult result, @PathVariable("productId") Integer productId) {
        this.product.delete(productId);
        return "redirect:/products";
    }
    
}
