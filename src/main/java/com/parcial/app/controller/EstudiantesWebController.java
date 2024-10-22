package com.parcial.app.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.parcial.app.entities.Estudiantes;
import com.parcial.app.repository.EstudiantesRepository;
import com.parcial.app.tools.*;


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
    
    
}
