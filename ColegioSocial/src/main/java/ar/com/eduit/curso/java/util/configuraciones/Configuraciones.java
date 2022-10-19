package ar.com.eduit.curso.java.util.configuraciones;

import java.time.LocalDate;
import java.util.Calendar;

public class Configuraciones {
    public static String getUbicacion(){
        //Calendar cal=Calendar.getInstance();
        //String tz=cal.getTimeZone().getID();
        //tz=tz.replace("/", " ");
        //tz=tz.replace("_", " ");
        //return tz;
        return Calendar
                        .getInstance()
                        .getTimeZone()
                        .getID()
                        .replace("/", " ")
                        .replace("_", " ");
    }
    public static String getFecha(){
        return LocalDate.now().toString().replace("-", "/");
    }
    public static String getSistema(){
        System.out.println(System.getProperties());
        return System.getProperty("os.name")+" "+System.getProperty("os.version")+" "+System.getProperty("os.arch");
    }
    public static String getJava(){
        return  System.getProperty("java.specification.vendor")+" "+
                System.getProperty("java.vm.name")+" "+
                System.getProperty("java.version");
    }
    public static String getUsuario(){
        return System.getProperty("user.name");
    }
}
