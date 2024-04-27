package com.estructurasdatos.proyecto_U3y4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
    ArrayList<String> palabras = new ArrayList<>();
    Tokenizer tokenizer = new Tokenizer("src\\main\\resources\\medline_CDs.txt");

    palabras = tokenizer.Tokenize();

    System.out.println("Palabras:" + tokenizer.getPalabrasSize());
    // System.out.println(tokenizer);

    // try {
    // tokenizer.createFile("palabrasSinRepetir", palabras);
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    Internos internos = new Internos();
    internos.SetPalabras(palabras);
    long start = System.currentTimeMillis();
    internos.ordenaMerge(palabras);
    long finish = System.currentTimeMillis();
    long timeElapsed = finish - start;
    // System.out.println("Arreglo ordenado");
    // internos.Imprimir();
    System.out.println("El tiempo total es de " + timeElapsed);

    if (internos.busquedaBinaria("Lunares")) {
      System.out.println("Se encontro la palabra");
    } else {
      System.out.println("No se encontro la palabra");
    }
  }

  @GetMapping("/hello")
  public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
    return String.format("Hello %s!", name);
  }
}