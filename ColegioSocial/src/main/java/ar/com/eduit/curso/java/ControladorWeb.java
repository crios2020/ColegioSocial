package ar.com.eduit.curso.java;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.com.eduit.curso.java.connectors.Connector;
import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.enums.Dia;
import ar.com.eduit.curso.java.enums.Turno;
import ar.com.eduit.curso.java.repositories.interfaces.I_AlumnoRepository;
import ar.com.eduit.curso.java.repositories.interfaces.I_CursoRepository;
import ar.com.eduit.curso.java.repositories.jdbc.AlumnoRepository;
import ar.com.eduit.curso.java.repositories.jdbc.CursoRepository;
import ar.com.eduit.curso.java.util.configuraciones.Configuraciones;

@Controller
public class ControladorWeb {

    private I_CursoRepository cr = new CursoRepository(Connector.getConnection());
    private I_AlumnoRepository ar = new AlumnoRepository(Connector.getConnection());
    private String mensajeCurso = "Ingrese un nuevo curso!";
    private String mensajeAlumno = "Ingrese un nuevo alumno";

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/alumnos")
    public String alumnos(@RequestParam(name="buscarApellido",required = false, defaultValue = "") String buscarApellido,
            Model model) {
        model.addAttribute("alumno", new Alumno());
        model.addAttribute("cursos", cr.getAll());
        model.addAttribute("mensajeAlumno", mensajeAlumno);
        //model.addAttribute("likeApellido", ar.getLikeApellido(buscarApellido));
        model.addAttribute("startApellido", ar.getStartWith(buscarApellido));
        return "alumnos";
    }

    @GetMapping("/cursos")
    public String cursos(@RequestParam(name = "buscarTitulo", required = false, defaultValue = "") String buscarTitulo,
            Model model) {
        model.addAttribute("dias", List.of(Dia.values()));
        model.addAttribute("turnos", List.of(Turno.values()));
        model.addAttribute("mensajeCurso", mensajeCurso);
        model.addAttribute("curso", new Curso());
        // model.addAttribute("cursos", cr.getAll());
        model.addAttribute("likeTitulo", cr.getLikeTitulo(buscarTitulo));
        return "cursos";
    }

    @PostMapping("/saveCurso")
    public String saveCurso(@ModelAttribute Curso curso) {
        cr.save(curso);
        if (curso.getId() <= 0) {
            mensajeCurso = "No se pudo guardar el curso";
        } else {
            mensajeCurso = "Se guardo el curso id: " + curso.getId();
        }
        return "redirect:cursos";
    }

    @PostMapping("saveAlumno")
    public String saveAlumno(@ModelAttribute Alumno alumno) {
        System.out.println("******************************");
        System.out.println(alumno);
        System.out.println("******************************");
        ar.save(alumno);
        if (alumno.getId() <= 0) {
            mensajeAlumno = "No se pudo guardar el alumno";
        } else {
            mensajeAlumno = "Se guardo el alumno id: " + alumno.getId();
        }
        return "redirect:alumnos";
    }

    @GetMapping("/test")
    public String test(
            @RequestParam(name = "nombre", required = false, defaultValue = "") String nombre,
            Model model) {
        LocalDate ld = LocalDate.now();
        String saludo = "";
        if (nombre != null && !nombre.isEmpty())
            saludo = "Hola " + nombre + "!";
        model.addAttribute("fecha", ld);
        model.addAttribute("saludo", saludo);
        return "test";
    }

    @GetMapping("/config")
    public String config(Model model){
        model.addAttribute("ubicacion", Configuraciones.getUbicacion());
        model.addAttribute("fecha",     Configuraciones.getFecha());
        model.addAttribute("so",        Configuraciones.getSistema());
        model.addAttribute("java",      Configuraciones.getJava());
        model.addAttribute("usuario",   Configuraciones.getUsuario());
        return "config";
    }
}
