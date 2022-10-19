package ar.com.eduit.curso.java.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalTime;

import ar.com.eduit.curso.java.connectors.Connector;

public class TestConnector {
    public static void main(String[] args) {
        LocalTime ltInicial=LocalTime.now();
        System.out.println(LocalTime.now());
        try (Connection conn=Connector.getConnection()){
            System.out.println(LocalTime.now());
            ResultSet rs=conn.createStatement().executeQuery("select version()");
            if(rs.next()) System.out.println(rs.getString(1));
            System.out.println(LocalTime.now());
        } catch (Exception e) {
            System.out.println();
        }
        System.out.println(LocalTime.now());
        LocalTime ltFinal=LocalTime.now();
    }
}
