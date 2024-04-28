package com.estructurasdatos.proyecto_U3y4;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estructurasdatos.proyecto_U3y4.ordenamientos.Internos;

import org.springframework.ui.Model;

@Controller
public class GeneralController {

    @GetMapping("/proyecto3y4")
    public String landingPage(Model model) {

        long tiempoBurbuja = 0;
        long tiempoInsercion = 0;
        long tiempoShell = 0;
        long tiempoQuick = 0;
        long tiempoMerge = 0;
        long tiempoABB = 0;
        long tiempoAVL = 0;

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
        tiempoMerge = finish - start;

        model.addAttribute("tiempoMerge", tiempoMerge);
        // System.out.println("Arreglo ordenado");
        // internos.Imprimir();
        System.out.println("El tiempo total es de " + timeElapsed);

        if (internos.busquedaBinaria("tamaño")) {
            System.out.println("Se encontro la palabra");
        } else {
            System.out.println("No se encontro la palabra");
        }
        model.addAttribute("palabras", palabras);

        return "Landing";
    }
}
