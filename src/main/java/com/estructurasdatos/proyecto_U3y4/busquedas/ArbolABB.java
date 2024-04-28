package com.estructurasdatos.proyecto_U3y4.busquedas;

import java.util.ArrayList;

import com.estructurasdatos.proyecto_U3y4.Tokenizer;
import com.estructurasdatos.proyecto_U3y4.excepciones.ItemNotFoundException;

public class ArbolABB {

    protected NodoBin raiz;

    public ArbolABB() {
        this(null);
    }

    public ArbolABB(Object o) {
        raiz = new NodoBin(o);
    }

    // TAREA!!
    public void recorridoEnAnchura() {

    }

    public void inOrden() {
        if (raiz != null)
            raiz.inOrden();
    }

    public void posOrden() {
        if (raiz != null)
            raiz.posOrden();
    }

    public void preOrden() {
        if (raiz != null)
            raiz.preOrden();
    }

    public void insertar(Object o) {
        insertarOrdenado(raiz, o);
    }

    public void insertarOrdenado(NodoBin n, Object o) {
        if (n.getDato() != null) {
            if (o.toString().compareTo(n.getDato().toString()) < 0) {
                if (n.getIzq() == null)
                    n.setIzq(new NodoBin(o));
                else
                    insertarOrdenado(n.getIzq(), o);
            } else {
                if (o.toString().compareTo(n.getDato().toString()) > 0) {
                    if (n.getDer() == null)
                        n.setDer(new NodoBin(o));
                    else
                        insertarOrdenado(n.getDer(), o);
                }
            }
        } else {
            // Si el dato del nodo es null, aquí puedes decidir cómo manejar este caso
            // Por ejemplo, podrías insertar el nuevo objeto directamente en este nodo
            n.setDato(o);
        }
    }

    public void buscar(Object o) {
        buscar(raiz, o);
    }

    public void buscar(NodoBin n, Object o) throws ItemNotFoundException {
        if (o.toString().compareTo(n.getDato().toString()) < 0) {
            if (n.getIzq() == null)
                throw new ItemNotFoundException("El elemento no se encuentra");
            else
                buscar(n.getIzq(), o);
        } else if (o.toString().compareTo(n.getDato().toString()) > 0) {
            if (n.getDer() == null)
                throw new ItemNotFoundException("El elemento no se encuentra");
            else
                buscar(n.getDer(), o);
        } else
            System.out.println("El elemento si está en el árbol");
    }

    public void borrar(Object o) {
        borrar(raiz, o);
    }

    public NodoBin borrar(NodoBin n, Object o) throws ItemNotFoundException {
        if (n == null)
            throw new ItemNotFoundException("Elemento no encontrado");
        else {
            if (o.toString().compareTo(n.getDato().toString()) > 0)
                n.setDer(borrar(n.getDer(), o));
            else {
                if (o.toString().compareTo(n.getDato().toString()) < 0)
                    n.setIzq(borrar(n.getIzq(), o));
                else {
                    if (n.getDer() != null && n.getIzq() != null) {// Aplicar alguno de los 2 criterios
                        // Aplicamos el criterio del hijo más a la izquierda del árbol derecho
                        NodoBin minimo = buscarMin(n.getDer());
                        n.setDato(minimo.getDato());
                        n.setDer(borrarMin(n.getDer()));
                    } else // Los criterios cuando se tiene 1 de los 2 hijos o es hoja
                        n = (n.getIzq() != null) ? n.getIzq() : n.getDer();
                }
            }

        }
        return n;
    }

    public NodoBin buscarMin(NodoBin n) {
        while (n.getIzq() != null)
            n = n.getIzq();
        return n;
    }

    public NodoBin borrarMin(NodoBin n) {
        if (n.getIzq() != null) {
            n.setIzq(borrarMin(n.getIzq()));
            return n;
        } else
            return n.getDer();
    }

    // public static void main(String[] args) {
    //     ArrayList<String> palabras = new ArrayList<>();
    //     Tokenizer tokenizer = new Tokenizer("src\\main\\resources\\medline_CDs.txt");
    //     ArbolABB arbol = new ArbolABB();
    //     palabras = tokenizer.Tokenize();

    //     for (String palabra : palabras) {
    //         arbol.insertar(palabra);
    //     }

    //     System.out.println("Buscando datos en el árbol");
    //     try {
    //         arbol.buscar("óvulo");
    //     } catch (ItemNotFoundException e) {
    //         System.out.println("Error: " + e.getMessage());
    //     }
    // }
}
