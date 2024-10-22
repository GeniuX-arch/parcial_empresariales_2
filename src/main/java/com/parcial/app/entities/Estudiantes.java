package com.parcial.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "estudiantes")
public class Estudiantes {
	@Id
	private String id;
    private String tipoDocumento;
    private String numeroDocumento;
    private String primerApellido;
    private String segundoApellido;
    private String primerNombre;
    private String segundoNombre;
    private String correoElectronico;
    private String numeroTelefonico;
    private String numeroRegistro;
    private int puntaje;
    private int mediaNacional;
    private boolean aprobadoUTS;
    private int comunicacionEscrita;
    private String comunicacionEscritaNivel;
    private int razonamientoCuantitativo;
    private String razonamientoCuantitativoNivel;
    private int lecturaCritica;
    private String lecturaCriticaNivel;
    private int competenciasCiudadanas;
    private String competenciasCiudadanasNivel;
    private int ingles;
    private String inglesNivel;
    private int formulacionProyectosIngenieria;
    private String formulacionProyectosIngenieriaNivel;
    private int pensamientoCientifico;
    private String pensamientoCientificoNivel;
    private int disenoSoftware;
    private String disenoSoftwareNivel;
    private String categoriaIngles;
    private String contrasena;

    // Constructor por defecto
    public Estudiantes() {
    }
    // Constructor parametrizado (sin niveles)
    public Estudiantes(String tipoDocumento, String numeroDocumento, String primerApellido, String segundoApellido, 
                       String primerNombre, String segundoNombre, String correoElectronico, String numeroTelefonico, 
                       String numeroRegistro, int puntaje, int mediaNacional, int comunicacionEscrita, 
                       int razonamientoCuantitativo, int lecturaCritica, int competenciasCiudadanas, int ingles, 
                       int formulacionProyectosIngenieria, int pensamientoCientifico, int disenoSoftware, String contrasena) {
        
        
        this.tipoDocumento = tipoDocumento;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.correoElectronico = correoElectronico;
        this.numeroTelefonico = numeroTelefonico;
        this.numeroRegistro = numeroRegistro;
        this.puntaje = puntaje;
        this.mediaNacional = mediaNacional;
        this.aprobadoUTS = calcularNivel(puntaje).equals("Nivel 1");
        this.comunicacionEscrita = comunicacionEscrita;
        this.razonamientoCuantitativo = razonamientoCuantitativo;
        this.lecturaCritica = lecturaCritica;
        this.competenciasCiudadanas = competenciasCiudadanas;
        this.ingles = ingles;
        this.formulacionProyectosIngenieria = formulacionProyectosIngenieria;
        this.pensamientoCientifico = pensamientoCientifico;
        this.disenoSoftware = disenoSoftware;
        this.categoriaIngles = calcularNivelIngles(ingles);
        this.contrasena =contrasena;
        
        // Calcular niveles basados en puntajes
        this.comunicacionEscritaNivel = calcularNivel(comunicacionEscrita);
        this.razonamientoCuantitativoNivel = calcularNivel(razonamientoCuantitativo);
        this.lecturaCriticaNivel = calcularNivel(lecturaCritica);
        this.competenciasCiudadanasNivel = calcularNivel(competenciasCiudadanas);
        this.inglesNivel = calcularNivel(ingles);
        this.formulacionProyectosIngenieriaNivel = calcularNivel(formulacionProyectosIngenieria);
        this.pensamientoCientificoNivel = calcularNivel(pensamientoCientifico);
        this.disenoSoftwareNivel = calcularNivel(disenoSoftware);
    }

    // Método para calcular el nivel basado en el puntaje
    private String calcularNivel(int puntaje) {
        if (puntaje >= 191) {
            return "Nivel 4";
        } else if (puntaje >= 156) {
            return "Nivel 3";
        } else if (puntaje >= 126) {
            return "Nivel 2";
        } else {
            return "Nivel 1"; // Reprobado
        }
    }
    private String calcularNivelIngles(int puntaje) {
        if (puntaje >= 200) {
            return "B2";
        } else if (puntaje >= 170) {
            return "B1";
        } else if (puntaje >= 140) {
            return "A2";
        } else if (puntaje >= 120) {
            return "A1";
        } else {
            return "A0"; 
        }
    }
    

     // Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
        public String getCategoriaIngles() {
        return categoriaIngles;
    }

    public void setCategoriaIngles(String categoriaIngles) {
        this.categoriaIngles = categoriaIngles;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNumeroTelefonico() {
        return numeroTelefonico;
    }

    public void setNumeroTelefonico(String numeroTelefonico) {
        this.numeroTelefonico = numeroTelefonico;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getMediaNacional() {
        return mediaNacional;
    }

    public void setMediaNacional(int mediaNacional) {
        this.mediaNacional = mediaNacional;
    }

    public boolean isAprobadoUTS() {
        return aprobadoUTS;
    }

    public void setAprobadoUTS(boolean aprobadoUTS) {
        this.aprobadoUTS = aprobadoUTS;
    }

    public int getComunicacionEscrita() {
        return comunicacionEscrita;
    }

    public void setComunicacionEscrita(int comunicacionEscrita) {
        this.comunicacionEscrita = comunicacionEscrita;
    }

    public String getComunicacionEscritaNivel() {
        return comunicacionEscritaNivel;
    }

    public void setComunicacionEscritaNivel(String comunicacionEscritaNivel) {
        this.comunicacionEscritaNivel = comunicacionEscritaNivel;
    }

    public int getRazonamientoCuantitativo() {
        return razonamientoCuantitativo;
    }

    public void setRazonamientoCuantitativo(int razonamientoCuantitativo) {
        this.razonamientoCuantitativo = razonamientoCuantitativo;
    }

    public String getRazonamientoCuantitativoNivel() {
        return razonamientoCuantitativoNivel;
    }

    public void setRazonamientoCuantitativoNivel(String razonamientoCuantitativoNivel) {
        this.razonamientoCuantitativoNivel = razonamientoCuantitativoNivel;
    }

    public int getLecturaCritica() {
        return lecturaCritica;
    }

    public void setLecturaCritica(int lecturaCritica) {
        this.lecturaCritica = lecturaCritica;
    }

    public String getLecturaCriticaNivel() {
        return lecturaCriticaNivel;
    }

    public void setLecturaCriticaNivel(String lecturaCriticaNivel) {
        this.lecturaCriticaNivel = lecturaCriticaNivel;
    }

    public int getCompetenciasCiudadanas() {
        return competenciasCiudadanas;
    }

    public void setCompetenciasCiudadanas(int competenciasCiudadanas) {
        this.competenciasCiudadanas = competenciasCiudadanas;
    }

    public String getCompetenciasCiudadanasNivel() {
        return competenciasCiudadanasNivel;
    }

    public void setCompetenciasCiudadanasNivel(String competenciasCiudadanasNivel) {
        this.competenciasCiudadanasNivel = competenciasCiudadanasNivel;
    }

    public int getIngles() {
        return ingles;
    }

    public void setIngles(int ingles) {
        this.ingles = ingles;
    }

    public String getInglesNivel() {
        return inglesNivel;
    }

    public void setInglesNivel(String inglesNivel) {
        this.inglesNivel = inglesNivel;
    }

    public int getFormulacionProyectosIngenieria() {
        return formulacionProyectosIngenieria;
    }

    public void setFormulacionProyectosIngenieria(int formulacionProyectosIngenieria) {
        this.formulacionProyectosIngenieria = formulacionProyectosIngenieria;
    }

    public String getFormulacionProyectosIngenieriaNivel() {
        return formulacionProyectosIngenieriaNivel;
    }

    public void setFormulacionProyectosIngenieriaNivel(String formulacionProyectosIngenieriaNivel) {
        this.formulacionProyectosIngenieriaNivel = formulacionProyectosIngenieriaNivel;
    }

    public int getPensamientoCientifico() {
        return pensamientoCientifico;
    }

    public void setPensamientoCientifico(int pensamientoCientifico) {
        this.pensamientoCientifico = pensamientoCientifico;
    }

    public String getPensamientoCientificoNivel() {
        return pensamientoCientificoNivel;
    }

    public void setPensamientoCientificoNivel(String pensamientoCientificoNivel) {
        this.pensamientoCientificoNivel = pensamientoCientificoNivel;
    }

    public int getDisenoSoftware() {
        return disenoSoftware;
    }

    public void setDisenoSoftware(int disenoSoftware) {
        this.disenoSoftware = disenoSoftware;
    }

    public String getDisenoSoftwareNivel() {
        return disenoSoftwareNivel;
    }

    public void setDisenoSoftwareNivel(String disenoSoftwareNivel) {
        this.disenoSoftwareNivel = disenoSoftwareNivel;
    }



}
