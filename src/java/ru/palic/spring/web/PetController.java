/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.palic.spring.web;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.palic.spring.dao.PetDAO;
import ru.palic.spring.domain.Pet;
/**
 *
 * @author Piotr
 */
@Controller
@RequestMapping("/pet")
public class PetController {
    
    @Autowired
    private PetDAO dao;
    
    @RequestMapping(value = "/pets", method = RequestMethod.GET)
    public String listAllPets(Model model) {
        model.addAttribute("pets", dao.listAllPets());
        return "/pet/pets";
}
    
     @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPetById(@PathVariable("id") int id, Model model) {
        Pet pet = dao.getPetById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        model.addAttribute("pet", pet);
        return "/pet/get";
    }
    
     @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String addPet(Model model) {
        model.addAttribute("title", "Add new Pet");
        model.addAttribute("sendURL", "/pet/add");
        return "/pet/form";
    }
    
     @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPet(@ModelAttribute Pet pet, Model model) {
        dao.addPet(pet);
        return "redirect:/pet/list";
    }
    
    @RequestMapping(value = "/{id}/edit", method = RequestMethod.GET)
    public String editPet(@PathVariable("id") int id, Model model) {
        Pet pet = dao.getPetById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        model.addAttribute("title", "Edit pet");
        model.addAttribute("sendURL", "/pet/editConfirm");
        model.addAttribute("pet", pet);
        return "/pet/form";
    }
    
     @RequestMapping(value = "/editConfirm", method = RequestMethod.POST)
    public String editPet(@ModelAttribute Pet pet, Model model) {
        if (dao.editPet(pet)) {
            return "redirect:/pet/list";
        } else {
            return "redirect:/fail";
        }
    }
    
    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String deletePet(@PathVariable("id") int id, Model model) {
        Pet pet =dao.getPetById(id);
        if (pet == null) {
            throw new NotFoundException();
        }
        model.addAttribute("pet", pet);
        return "/pet/delete";
    }
    
    @RequestMapping(value = "/deleteConfirm", method = RequestMethod.POST)
    public String deletePetConfirm(@PathVariable("id") int id, Model model) {
        if (dao.deletePet(dao.getPetById(id))) {
            return "redirect:/pet/pets";
        } else {
            return "redirect:/fail";
        }
    }
    
     @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND,
            reason = "Entity with specified id not found")
    public void handleNotFoundException(NotFoundException ex,
            HttpServletResponse response) throws IOException {
    }
}
