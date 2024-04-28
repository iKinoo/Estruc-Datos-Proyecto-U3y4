package com.estructurasdatos.proyecto_U3y4.ordenamientos;

import java.util.ArrayList;
import java.util.Stack;
import java.text.Collator;

public class Internos {
    protected ArrayList<String> palabras;

    public Internos() {
        this.palabras = new ArrayList<>();
    }

    public void SetPalabras(ArrayList<String> palabras) {
        this.palabras = palabras;
    }

    public void Imprimir() {
        int i = 0;
        System.out.println("Palabras:");
        for (String palabra : palabras) {
            System.out.println(i + " " + palabra);
            i++;
        }
    }

    public void burbuja() {
        for (int i = 0; i < palabras.size(); i++) {
            for (int j = 0; j < palabras.size() - 1; j++) {
                if (palabras.get(j).compareTo(palabras.get(j + 1)) > 0) {
                    String aux = palabras.get(j);
                    palabras.set(j, palabras.get(j + 1));
                    palabras.set(j + 1, aux);
                }
            }
        }
    }

    public void insercion() {
        for (int i = 1; i < palabras.size(); i++) {
            String aux = palabras.get(i);
            int j = i - 1;
            while (j >= 0 && palabras.get(j).compareTo(aux) > 0) {
                palabras.set(j + 1, palabras.get(j));
                j--;
            }
            palabras.set(j + 1, aux);
        }
    }

    public void shellsort() {
        int intervalo, i;
        String aux;
        boolean band;
        intervalo = palabras.size();
        while (intervalo > 1) {
            intervalo /= 2;
            band = true;
            while (band) {
                band = false;
                i = 0;
                while ((intervalo + i) < palabras.size()) {
                    if (palabras.get(i).compareTo(palabras.get(i + intervalo)) > 0) {
                        aux = palabras.get(i);
                        palabras.set(i, palabras.get(i + intervalo));
                        palabras.set(i + intervalo, aux);
                        band = true;
                    }
                    i++;
                }
            }
        }
    }

    public void imprimir() {
        for (String palabra : palabras) {
            System.out.println(palabra);
        }
    }

    public void QuickSort() {
        int ini = 0;
        int fin = palabras.size() - 1;
        Stack<Integer> pilaMenor = new Stack<>();
        Stack<Integer> pilaMayor = new Stack<>();
        pilaMenor.push(ini);
        pilaMayor.push(fin);
        while (!pilaMenor.isEmpty()) {
            ini = pilaMenor.pop();
            fin = pilaMayor.pop();
            int pos = posicionaQuickSort(ini, fin);
            if (ini < pos - 1) {
                pilaMenor.push(ini);
                pilaMayor.push(pos - 1);
            }
            if (fin > pos + 1) {
                pilaMenor.push(pos + 1);
                pilaMayor.push(fin);
            }
        }
    }

    public int posicionaQuickSort(int ini, int fin) {
        int pos, izq, der;
        String aux;
        boolean band;
        izq = ini;
        der = fin;
        pos = ini;
        band = true;
        while (band) {
            while (palabras.get(pos).compareTo(palabras.get(der)) <= 0 && pos != der)
                der--;
            if (pos == der)
                band = false;
            else {
                aux = palabras.get(pos);
                palabras.set(pos, palabras.get(der));
                palabras.set(der, aux);
                pos = der;
                while (palabras.get(pos).compareTo(palabras.get(izq)) >= 0 && pos != izq)
                    izq++;
                if (pos == izq)
                    band = false;
                else {
                    aux = palabras.get(pos);
                    palabras.set(pos, palabras.get(izq));
                    palabras.set(izq, aux);
                    pos = izq;
                }
            }
        }
        return pos;
    }

    public ArrayList<String> ordenaMerge(ArrayList<String> palabras) {
        int n = palabras.size(), i, m;
        ArrayList<String> L1 = new ArrayList<>();
        ArrayList<String> L2 = new ArrayList<>();
        if (n > 1) {
            m = n / 2;
            for (i = 0; i < m; i++)
                L1.add(palabras.get(i));
            for (i = m; i < n; i++)
                L2.add(palabras.get(i));
            palabras = merge(ordenaMerge(L1), ordenaMerge(L2));
        }
        return palabras;
    }

    public ArrayList<String> merge(ArrayList<String> L1, ArrayList<String> L2) {
        ArrayList<String> lista = new ArrayList<>();
        while (!L1.isEmpty() && !L2.isEmpty()) {
            if (L1.get(0).compareTo(L2.get(0)) < 0) {
                lista.add(L1.get(0));
                L1.remove(0);
                if (L1.isEmpty()) {
                    lista.addAll(L2);
                    L2.clear();
                }
            } else {
                lista.add(L2.get(0));
                L2.remove(0);
                if (L2.isEmpty()) {
                    lista.addAll(L1);
                    L1.clear();
                }
            }
        }
        palabras = new ArrayList<>(lista);
        return lista;
    }
    // public ArrayList ordenaMerge(ArrayList palabras) {
    // int n = palabras.size(), i, m;
    // ArrayList L1 = new ArrayList(), L2 = new ArrayList();
    // if (n > 1) {
    // m = n / 2;
    // for (i = 0; i < m; i++)
    // L1.add(palabras.get(i));
    // for (i = m; i < n; i++)
    // L2.add(palabras.get(i));
    // palabras = merge(ordenaMerge(L1), ordenaMerge(L2));
    // }
    // return palabras;
    // }

    // public ArrayList merge(ArrayList L1, ArrayList L2) {
    // ArrayList lista = new ArrayList();
    // while (!L1.isEmpty() && !L2.isEmpty()) {
    // if ((Integer) L1.get(0) < (Integer) L2.get(0)) {
    // lista.add(L1.get(0));
    // L1.remove(0);
    // if (L1.isEmpty()) {
    // lista.addAll(L2);
    // L2.clear();
    // }
    // } else {
    // lista.add(L2.get(0));
    // L2.remove(0);
    // if (L2.isEmpty()) {
    // lista.addAll(L1);
    // L1.clear();
    // }
    // }
    // }
    // return lista;
    // }

    public boolean busquedaBinaria(String palabra) {
        int inicio = 0;
        int fin = palabras.size() - 1;
    
        while (inicio <= fin) {
            int medio = (inicio + fin) / 2;
            int comparacion = palabra.compareToIgnoreCase(palabras.get(medio));
    
            if (comparacion == 0) {
                return true; // La palabra se encontró en el ArrayList
            } else if (comparacion < 0) {
                fin = medio - 1; // La palabra está en la mitad inferior
            } else {
                inicio = medio + 1; // La palabra está en la mitad superior
            }
        }
    
        return false; // La palabra no se encontró en el ArrayList
    }

}
