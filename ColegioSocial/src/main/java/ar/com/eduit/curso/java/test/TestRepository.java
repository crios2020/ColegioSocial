package ar.com.eduit.curso.java.test;

import java.time.LocalTime;

import ar.com.eduit.curso.java.connectors.Connector;
import ar.com.eduit.curso.java.entities.Alumno;
import ar.com.eduit.curso.java.entities.Curso;
import ar.com.eduit.curso.java.enums.Dia;
import ar.com.eduit.curso.java.enums.Turno;
import ar.com.eduit.curso.java.repositories.interfaces.I_AlumnoRepository;
import ar.com.eduit.curso.java.repositories.interfaces.I_CursoRepository;
import ar.com.eduit.curso.java.repositories.jdbc.AlumnoRepository;
import ar.com.eduit.curso.java.repositories.jdbc.CursoRepository;

public class TestRepository {
    public static void main(String[] args) {
        System.out.println(LocalTime.now());
        I_CursoRepository cursoRepository=new CursoRepository(Connector.getConnection());
        System.out.println(LocalTime.now());
        Curso curso=new Curso("Jardineria","Marioti",Dia.MARTES,Turno.MAÑANA);
        cursoRepository.save(curso);
        System.out.println(curso);

        cursoRepository.remove(cursoRepository.getById(17));

        curso=cursoRepository.getById(18);
        curso.setDia(Dia.VIERNES);
        curso.setTurno(Turno.TARDE);
        cursoRepository.update(curso);

        cursoRepository.getAll().forEach(System.out::println);
        
        System.out.println("************************************************");
        cursoRepository.getLikeTitulo("jard").forEach(System.out::println);
        System.out.println("************************************************");
        cursoRepository.getLikeProfesor("rio").forEach(System.out::println);
        
        System.out.println("************************************************");
        System.out.println(LocalTime.now());
        I_AlumnoRepository alumnoRepository=new AlumnoRepository(Connector.getConnection());
        System.out.println(LocalTime.now());
        Alumno alumno=new Alumno("Micaela", "Marti", 34, 1);
        alumnoRepository.save(alumno);
        System.out.println(alumno);

        alumnoRepository.remove(alumnoRepository.getById(6));

        alumno=alumnoRepository.getById(7);
        alumno.setApellido("Jerez");
        alumno.setEdad(22);
        alumnoRepository.update(alumno);

        System.out.println("************************************************");
        alumnoRepository.getAll().forEach(System.out::println);
        
        System.out.println("************************************************");
        alumnoRepository.getLikeApellido("so").forEach(System.out::println);
        System.out.println("************************************************");
        alumnoRepository.getByCurso(cursoRepository.getById(2)).forEach(System.out::println);
        System.out.println(LocalTime.now());
    }
}
