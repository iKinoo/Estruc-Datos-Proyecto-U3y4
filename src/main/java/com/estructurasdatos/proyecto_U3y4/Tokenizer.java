package com.estructurasdatos.proyecto_U3y4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Tokenizer {

    private String ruta;
    private ArrayList<String> palabras;

    public Tokenizer(String ruta) {
        this.ruta = ruta;
        this.palabras = new ArrayList<>();
    }

    public ArrayList<String> Tokenize() {

        try {
            FileReader fr = new FileReader(this.ruta);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(linea, " ");
                while (st.hasMoreTokens()) {
                    palabras.add(st.nextToken());
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        return palabras;
    }
    public int getPalabrasSize() {
        return palabras.size();
    }

    @SuppressWarnings("unused")
    public void Imprimir() {
        int i = 0;
        System.out.println("Palabras:");
        for (String palabra : palabras) {
            System.out.println(i);
            i++;
        }
    }
    @Override
    public String toString() {
        ArrayList<String> primeras15Palabras =  new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            primeras15Palabras.add(palabras.get(i));
        }
        return "Tokenizer [palabras = " + primeras15Palabras + "..., ruta=" + ruta + "]";
    }
}
