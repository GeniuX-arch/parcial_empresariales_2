package com.parcial.app.controller;

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

import com.parcial.app.entities.Estudiantes;
import com.parcial.app.repository.EstudiantesRepository;
import com.parcial.app.tools.*;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping(value = "/estudiantes")
public class EstudiantesWebController {

    @Autowired
    private EstudiantesRepository estudiantesRepositorio;

    @GetMapping
    public String estudiantesListTemplate(Model model) {
        model.addAttribute("listaEstudiantes", estudiantesRepositorio.findAll());
        return "list-estudiantes";
}
    @GetMapping("/{id}")
    public String estudianteDetailTemplate(@PathVariable("id") String id, Model model) {
    Estudiantes estudiante = estudiantesRepositorio.findById(id)
        .orElseThrow(() -> new NotFoundException("Estudiante no encontrado"));
    model.addAttribute("estudiante", estudiante);
    return "detail-estudiante"; // Asegúrate de tener una plantilla para mostrar los detalles del estudiante
}

    @GetMapping("/new")
    public String estudiantesNewTemplate(Model model) {
        model.addAttribute("estudiante", new Estudiantes());
        return "form-estudiantes";
    }

    @GetMapping("/edit/{id}")
    public String estudianteEditTemplate(@PathVariable("id") String id, Model model) {
        model.addAttribute("estudiante", estudiantesRepositorio.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado")));
        return "form-estudiantes";
    }

    @PostMapping("/save")
    public String estudiantesSaveProcess(@ModelAttribute("estudiante") Estudiantes estudiante) {
        if (estudiante.getNumeroDocumento() == null || estudiante.getNumeroDocumento().equals("")) {
            estudiante.setNumeroDocumento(UUID.randomUUID().toString()); // Asigna un UUID si el número de documento es nulo
        }
        estudiantesRepositorio.save(estudiante);
        return "redirect:/estudiantes";
    }

    @GetMapping("/delete/{id}")
    public String estudianteDeleteProcess(@PathVariable("id") String id) {
        estudiantesRepositorio.deleteById(id);
        return "redirect:/estudiantes";
        
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
    model.addAttribute("estudiante", new Estudiantes());
    return "login-estudiantes"; // Asegúrate de tener la plantilla correspondiente
    }
    @PostMapping("/login")
    public String processLogin(@ModelAttribute("estudiante") Estudiantes estudiante, 
                           HttpSession session, 
                           RedirectAttributes redirectAttributes) {
    
    Optional<Estudiantes> estudianteBD = estudiantesRepositorio
        .findByNumeroDocumento(estudiante.getNumeroDocumento());
    
    if (estudianteBD.isPresent() && 
        estudianteBD.get().getContrasena().equals(estudiante.getContrasena())) {
        
        session.setAttribute("estudianteId", estudianteBD.get().getNumeroDocumento());
        
        // Redirigir a la ruta con el ID del estudiante
        return "redirect:/estudiantes/" + estudianteBD.get().getId(); // Usa el ID de MongoDB
    }
    
    redirectAttributes.addFlashAttribute("error", "Credenciales inválidas");
    return "redirect:/estudiantes/login";
}
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/estudiantes/login";
    }
    
    
}
