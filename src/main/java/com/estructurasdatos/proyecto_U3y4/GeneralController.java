package com.estructurasdatos.proyecto_U3y4;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.estructurasdatos.proyecto_U3y4.ordenamientos.Internos;

import org.springframework.ui.Model;
import com.estructurasdatos.proyecto_U3y4.busquedas.*;
import com.estructurasdatos.proyecto_U3y4.excepciones.ItemNotFoundException;
import java.util.Scanner;

@Controller
public class GeneralController {

    @SuppressWarnings("unchecked")
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
        long start;
        long finish;

        // Burbuja
        internos.SetPalabras((ArrayList<String>)palabras.clone());
        start = System.currentTimeMillis();
        internos.burbuja();
        finish = System.currentTimeMillis();
        tiempoBurbuja = finish - start;
        model.addAttribute("tiempoBurbuja", tiempoBurbuja);

        // Insercion
        internos.SetPalabras((ArrayList<String>)palabras.clone());
        start = System.currentTimeMillis();
        internos.insercion();
        finish = System.currentTimeMillis();
        tiempoInsercion = finish - start;
        model.addAttribute("tiempoInsercion", tiempoInsercion);

        // ShellSort
        internos.SetPalabras((ArrayList<String>)palabras.clone());
        start = System.currentTimeMillis();
        internos.shellsort();
        finish = System.currentTimeMillis();
        tiempoShell = finish - start;
        model.addAttribute("tiempoShell", tiempoShell);

        // Quicksort
        internos.SetPalabras((ArrayList<String>)palabras.clone());
        start = System.currentTimeMillis();
        internos.QuickSort();
        finish = System.currentTimeMillis();
        tiempoQuick = finish - start;
        model.addAttribute("tiempoQuick", tiempoQuick);
        
        // MergeSort
        // internos.SetPalabras((ArrayList<String>)palabras.clone());
        start = System.currentTimeMillis();
        internos.ordenaMerge((ArrayList<String>)palabras.clone());
        finish = System.currentTimeMillis();
        tiempoMerge = finish - start;
        model.addAttribute("tiempoMerge", tiempoMerge);

        // ArbolABB
        ArbolABB arbol = new ArbolABB();
        
        for (String palabra : palabras) {
            arbol.insertar(palabra);
        }

        ArbolAVL arbolAVL = new ArbolAVL();
        for (String palabra : palabras) {
            arbolAVL.insertar(palabra);
        }

        System.out.println("INGRESE UNA PALABRA A BUSCAR");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        sc.close();

        
        // ABB
        start = System.currentTimeMillis();
        try {
            arbol.buscar(palabra);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }
        finish = System.currentTimeMillis();
        tiempoABB = finish - start;
        model.addAttribute("tiempoABB", tiempoABB);

        //AVL
        try {
            start = System.currentTimeMillis();
            arbolAVL.buscar(palabra);
            finish = System.currentTimeMillis();
            tiempoAVL = finish - start;
            model.addAttribute("tiempoAVL", tiempoAVL);
        } catch (ItemNotFoundException e) {
            System.out.println(e.getMessage());
        }

                
        // System.out.println("Arreglo ordenado");
        // internos.Imprimir();
        System.out.println("El tiempo total es de " + tiempoMerge);

        if (internos.busquedaBinaria(palabra)) {
            System.out.println("Se encontro la palabra");
        } else {
            System.out.println("No se encontro la palabra");
        }
        model.addAttribute("palabras", palabras);

        return "Landing";
    }
}
