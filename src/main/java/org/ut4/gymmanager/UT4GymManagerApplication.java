package org.ut4.gymmanager;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.context.annotation.ApplicationScope;
import org.ut4.gymmanager.model.Ejercicio;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
@SpringBootApplication
public class UT4GymManagerApplication {
    public static void main(String[] args) {
       SpringApplication.run(UT4GymManagerApplication.class,args);
        System.out.println("http://localhost:8081/");
    }
}
