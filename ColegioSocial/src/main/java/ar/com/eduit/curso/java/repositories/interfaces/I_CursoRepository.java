package ar.com.eduit.curso.java.repositories.interfaces;

import java.util.ArrayList;
import java.util.List;

import ar.com.eduit.curso.java.entities.Curso;

public interface I_CursoRepository {
    void save(Curso curso);
    void remove(Curso curso);
    void update(Curso curso);
    default Curso getById(int id){
        //select * from cursos where id=id
        // Curso curso=new Curso();
        // for(Curso c:getAll()){
        //     if(c.getId()==id){
        //         curso=c;
        //         break;
        //     }
        // }
        // return curso;
        return getAll()
                .stream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Curso());
    }
    List<Curso>getAll();
    default List<Curso>getLikeTitulo(String titulo){
        if(titulo==null) return new ArrayList();
        return getAll()
                    .stream()
                    .filter(c->c.getTitulo()!=null)
                    .filter(c->c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .toList();
    }
    default List<Curso>getLikeProfesor(String profesor){
        if(profesor==null) return new ArrayList();
        return getAll()
                    .stream()
                    .filter(c->c.getProfesor()!=null)
                    .filter(c->c.getProfesor().toLowerCase().contains(profesor.toLowerCase()))
                    .toList();
    }
    default List<Curso>getLikeTituloProfesor(String titulo, String profesor){
        if(titulo==null || profesor == null) return new ArrayList();
        return  getAll()
                    .stream()
                    .filter(c->c.getTitulo()!=null)
                    .filter(c->c.getProfesor()!=null)
                    .filter(c->c.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                    .filter(c->c.getProfesor().toLowerCase().contains(profesor.toLowerCase()))
                    .toList();
    }
}
