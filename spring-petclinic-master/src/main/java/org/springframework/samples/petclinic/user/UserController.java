/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.samples.petclinic.user;

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

@Controller
public class UserController {
    
    
    private final UserRepository user;
    
    public UserController(UserRepository userRepository){
        this.user = userRepository;
    }
    
    @GetMapping("/users")
    public String processFindForm(User user, BindingResult result, Map<String, Object> model) {
        Collection<User> results = this.user.findAll();
        model.put("users",results);
        return "users/index";
    }
    @GetMapping("/users/create")
    public String initCreationForm(Map<String, Object> model) {
        User user = new User();
        model.put("user",user);
        return "/users/create";
    }
    @PostMapping("/users/store")
    public String processCreationForm(@Valid User user, BindingResult result) {
        if(result.hasErrors()){
            return "/users/create";
        }else{
            this.user.save(user);
            return "redirect:/users";
        }
    }
    
    @GetMapping("/users/{userId}/edit")
    public String initUpdateUserForm(@PathVariable("userId") int userId, Model model) {
        User user = this.user.findById(userId);
        model.addAttribute(user);
        return "users/edit";
    }
    
    @PostMapping("/users/{userId}/edit")
    public String processUpdateUserForm(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        if (result.hasErrors()) {
            return "users/edit";
        } else {
            user.setId(userId);
            this.user.save(user);
            return "redirect:/users";
        }
    }
    
    @PostMapping("/users/{userId}/delete")
    public String delete(@Valid User user, BindingResult result, @PathVariable("userId") int userId) {
        this.user.deleteUser(userId);
        return "redirect:/users";
    }
}
