package com.parcial.app.controller;

import jakarta.servlet.http.HttpSession;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.parcial.app.entities.Coordinadores;
import com.parcial.app.repository.CoordinadoresRepository;
import com.parcial.app.tools.NotFoundException;


@Controller
@RequestMapping(value = "/coordinadores")
public class CoordinadoresWebController {

    @Autowired
    private CoordinadoresRepository coordinadoresRepository;

    @GetMapping
    public String coordinadoresListTemplate(Model model) {
        model.addAttribute("listaCoordinadores", coordinadoresRepository.findAll());
        return "list-coordinadores";
    }

    @GetMapping("/new")
    public String coordinadoresNewTemplate(Model model) {
        model.addAttribute("coordinador", new Coordinadores());
        return "form-coordinadores";
    }

    @GetMapping("/edit/{id}")
    public String coordinadorEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("coordinador", coordinadoresRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Coordinador no encontrado")));
        return "form-coordinadores";
    }

    @PostMapping("/save")
public String coordinadoresSaveProcess(@ModelAttribute("coordinador") Coordinadores coordinador) {
    if (coordinador.getCedula() == null || coordinador.getCedula().equals("")) {
        coordinador.setCedula(UUID.randomUUID().toString()); // Asigna un UUID si la cédula es nula
    }
    coordinadoresRepository.save(coordinador);
    return "redirect:/coordinadores";
}


    @GetMapping("/delete/{id}")
    public String coordinadorDeleteProcess(@PathVariable("id") String id) {
        coordinadoresRepository.deleteById(id);
        return "redirect:/coordinadores";
    }


    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("coordinador", new Coordinadores());
        return "login-coordinadores";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute("coordinador") Coordinadores coordinador, 
                             HttpSession session, 
                             RedirectAttributes redirectAttributes) {
     
        Optional<Coordinadores> coordinadorBD = coordinadoresRepository
            .findByCedula(coordinador.getCedula());
        
        if (coordinadorBD.isPresent() && 
            coordinadorBD.get().getContrasena().equals(coordinador.getContrasena())) {
      
            session.setAttribute("coordinadorId", coordinadorBD.get().getCedula());
            return "redirect:/estudiantes";
        }
        
        
        redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
        return "redirect:/coordinadores/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/coordinadores/login";
    }
  
  
}
