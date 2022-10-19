package ar.com.eduit.curso.java.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;

import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;

public interface I_AlumnoRepository {
    void save(Alumno alumno);
    void remove(Alumno alumno);
    void update(Alumno alumno);
    default Alumno getById(int id){
        return getAll()
                .stream()
                .filter(a->a.getId()==id)
                .findAny()
                .orElse(new Alumno());
    }
    List<Alumno> getAll();
    default List<Alumno> getLikeApellido(String apellido){
        if(apellido==null) return new ArrayList();
        return getAll()
                .stream()
                .filter(a->a.getApellido()!=null)
                .filter(a->a.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .toList();
    }
    default List<Alumno> getStartWith(String apellido){
        if(apellido==null) return new ArrayList();
        return getAll()
                .stream()
                .filter(a->a.getApellido()!=null)
                .filter(a->a.getApellido().toLowerCase().startsWith(apellido.toLowerCase()))
                .toList();
    }
    default List<Alumno> getByCurso(Curso curso){
        if(curso==null) return new ArrayList();
        return getAll()
                .stream()
                .filter(a->a.getIdCurso()==curso.getId())
                .toList();
    }
}
