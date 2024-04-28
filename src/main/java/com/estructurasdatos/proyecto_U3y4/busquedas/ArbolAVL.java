package com.estructurasdatos.proyecto_U3y4.busquedas;

import java.util.ArrayList;

import com.estructurasdatos.proyecto_U3y4.Tokenizer;
import com.estructurasdatos.proyecto_U3y4.excepciones.ItemNotFoundException;

public class ArbolAVL {
    protected NodoAVL raíz;

    public ArbolAVL() {
        this(null);
    }

    public ArbolAVL(Object o) {
        raíz = new NodoAVL(o);
    }

    public void inOrden() {
        if (raíz != null)
            raíz.inOrden();
    }

    public void preOrden() {
        if (raíz != null)
            raíz.preOrden();
    }

    public void posOrden() {
        if (raíz != null)
            raíz.posOrden();
    }

    public void insertar(Object o) {
        insertarOrdenado(raíz, o);
    }

    public void insertarOrdenado(NodoAVL n, Object o) {
        if (n.getDato() != null) {
            if (o.toString().compareTo(n.getDato().toString()) < 0) {
                if (n.getIzq() == null)
                    n.setIzq(new NodoAVL(o));
                else
                    insertarOrdenado(n.getIzq(), o);
            } else {
                if (o.toString().compareTo(n.getDato().toString()) > 0) {
                    if (n.getDer() == null)
                        n.setDer(new NodoAVL(o));
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

    // public void insertarOrdenado(NodoAVL n, Object o) {
    // if (n == null) {
    // // Si el nodo es null, creamos un nuevo nodo con el objeto 'o'
    // n = new NodoAVL(o, null, null, null); // Aquí debes proporcionar el padre
    // adecuado si es necesario
    // recalcularFE(n);
    // } else {
    // if (o.toString().compareTo(n.getDato().toString()) < 0) {
    // if (n.getIzq() == null) {
    // n.setIzq(new NodoAVL(o, null, null, n));
    // recalcularFE(n);
    // } else
    // insertarOrdenado(n.getIzq(), o);
    // } else {
    // if (o.toString().compareTo(n.getDato().toString()) > 0) {
    // if (n.getDer() == null) {
    // n.setDer(new NodoAVL(o, null, null, n));
    // recalcularFE(n);
    // } else
    // insertarOrdenado(n.getDer(), o);
    // }
    // }
    // }
    // }

    // public void insertarOrdenado(NodoAVL n, Object o) {
    // if ((int) o < (int) n.getDato()) {
    // if (n.getIzq() == null) {
    // n.setIzq(new NodoAVL(o, null, null, n));
    // recalcularFE(n);
    // } else
    // insertarOrdenado((NodoAVL) n.getIzq(), o);
    // } else {
    // if ((int) o > (int) n.getDato()) {
    // if (n.getDer() == null) {
    // n.setDer(new NodoAVL(o, null, null, n));
    // recalcularFE(n);
    // } else
    // insertarOrdenado((NodoAVL) n.getDer(), o);
    // }
    // }
    // }

    public void recalcularFE(NodoAVL nodo) {
        if (nodo != null) {
            nodo.setFe(NodoAVL.altura((NodoAVL) nodo.getDer()) -
                    NodoAVL.altura((NodoAVL) nodo.getIzq()));
            if (nodo.getFe() == 2 || nodo.getFe() == -2)
                balancear(nodo);
            else
                recalcularFE(nodo.getPadre());
        }
    }

    public void balancear(NodoAVL nodo) {
        int feActual = nodo.getFe();
        if (feActual == 2) {
            switch (((NodoAVL) nodo.getDer()).getFe()) {
                case 0:
                case 1:
                    rotacionDD(nodo);
                    System.out.println("Rotación DD");
                    break;
                case -1:
                    rotacionDI(nodo);
                    System.out.println("Rotación DI");
                    break;
            }
        } else {
            switch (((NodoAVL) nodo.getIzq()).getFe()) {
                case 0:
                case -1:
                    rotacionII(nodo);
                    System.out.println("Rotación II");
                    break;
                case 1:
                    rotacionID(nodo);
                    System.out.println("Rotación ID");
                    break;
            }
        }
    }

    public void rotacionDD(NodoAVL nodo) {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getDer();
        NodoAVL B = (NodoAVL) Q.getIzq();

        if (Padre != null)
            if (Padre.getDer() == P)
                Padre.setDer(Q);
            else
                Padre.setIzq(Q);
        else
            raíz = Q;

        // Reconstruir el árbol
        P.setDer(B);
        Q.setIzq(P);

        // Reasignación de Padres
        P.setPadre(Q);
        if (B != null)
            B.setPadre(P);
        Q.setPadre(Padre);
        // Ajustar los Factores de Equilibrio
        P.setFe(0);
        Q.setFe(0);
    }

    public void rotacionII(NodoAVL nodo) {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getIzq();
        NodoAVL B = (NodoAVL) Q.getDer();
        if (Padre != null)
            if (Padre.getIzq() == P)
                Padre.setIzq(Q);
            else
                Padre.setDer(Q);
        else
            raíz = Q;
        // Recontruir el árbol
        P.setIzq(B);
        Q.setDer(P);
        // Reasignar a los papás
        P.setPadre(Q);
        if (B != null)
            B.setPadre(P);
        Q.setPadre(Padre);
        // Ajustar los FE
        P.setFe(0);
        Q.setFe(0);
    }

    public void rotacionID(NodoAVL nodo) {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getIzq();
        NodoAVL R = (NodoAVL) Q.getDer();
        NodoAVL B = (NodoAVL) R.getIzq();
        NodoAVL C = (NodoAVL) R.getDer();
        if (Padre != null)
            if (Padre.getDer() == nodo)
                Padre.setDer(R);
            else
                Padre.setIzq(R);
        else
            raíz = R;

        // Reconstruir el árbol
        Q.setDer(B);
        P.setIzq(C);
        R.setIzq(Q);
        R.setDer(P);

        // Reasignar los padres
        R.setPadre(Padre);
        P.setPadre(R);
        Q.setPadre(R);
        if (B != null)
            B.setPadre(Q);
        if (C != null)
            C.setPadre(P);

        // Reasignar los FE
        switch (R.getFe()) {
            case -1:
                Q.setFe(0);
                P.setFe(1);
                break;
            case 0:
                Q.setFe(0);
                P.setFe(0);
                break;
            case 1:
                Q.setFe(-1);
                P.setFe(0);
                break;
        }
    }

    public void rotacionDI(NodoAVL nodo) {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getDer();
        NodoAVL R = (NodoAVL) Q.getIzq();
        NodoAVL B = (NodoAVL) R.getDer();
        NodoAVL C = (NodoAVL) R.getIzq();

        if (Padre != null)
            if (Padre.getIzq() == nodo)
                Padre.setIzq(R);
            else
                Padre.setDer(R);
        else
            raíz = R;

        // Reconstuir el árbol
        Q.setIzq(B);
        P.setDer(C);
        R.setDer(Q);
        R.setIzq(P);

        // Reasignar padres
        R.setPadre(Padre);
        P.setPadre(R);
        Q.setPadre(R);
        if (B != null)
            B.setPadre(Q);
        if (C != null)
            C.setPadre(P);

        // Asignar valores de los FE
        switch (R.getFe()) {
            case -1:
                Q.setFe(0);
                P.setFe(1);
                break;
            case 0:
                Q.setFe(0);
                P.setFe(0);
                break;
            case 1:
                Q.setFe(-1);
                P.setFe(0);
                break;
        }
    }

    public void eliminar(int valor) {
        raíz = eliminar(raíz, valor);
    }

    private NodoAVL eliminar(NodoAVL nodo, int valor) {
        if (nodo == null) {
            return nodo;
        }

        if (valor < (int) nodo.getDato()) {
            nodo.setIzq(eliminar((NodoAVL) nodo.getIzq(), valor));
        } else if (valor > (int) nodo.getDato()) {
            nodo.setDer(eliminar((NodoAVL) nodo.getDer(), valor));
        } else {
            if (nodo.getIzq() == null || nodo.getDer() == null) {
                NodoAVL temp = null;
                if (temp == nodo.getIzq()) {
                    temp = (NodoAVL) nodo.getDer();
                } else {
                    temp = (NodoAVL) nodo.getIzq();
                }

                if (temp == null) {
                    nodo = null;
                } else {
                    nodo = temp;
                }
            } else {
                NodoAVL temp = minValorNodo((NodoAVL) nodo.getDer());
                nodo.setDato(temp.getDato());
                nodo.setDer(eliminar((NodoAVL) nodo.getDer(), (int) temp.getDato()));
            }
        }

        if (nodo == null) {
            return nodo;
        }

        recalcularFE(nodo);
        balancear(nodo);

        return nodo;
    }

    private NodoAVL minValorNodo(NodoAVL nodo) {
        NodoAVL actual = nodo;
        while (actual.getIzq() != null) {
            actual = (NodoAVL) actual.getIzq();
        }
        return actual;
    }

    public void buscar(Object o) {
        buscar(raíz, o);
    }

    public void buscar(NodoAVL nodoAVL, Object o) throws ItemNotFoundException {
        if (nodoAVL == null) {
            throw new ItemNotFoundException("El árbol está vacío"); // Si el nodo es null, lanzamos una excepción
                                                                    // indicando que el árbol está vacío
        }

        if (o.toString().compareTo(nodoAVL.getDato().toString()) < 0) {
            if (nodoAVL.getIzq() == null)
                throw new ItemNotFoundException("El elemento no se encuentra"); // Si el hijo izquierdo es null, el
                                                                                // elemento no se encuentra
            else
                buscar(nodoAVL.getIzq(), o);
        } else if (o.toString().compareTo(nodoAVL.getDato().toString()) > 0) {
            if (nodoAVL.getDer() == null)
                throw new ItemNotFoundException("El elemento no se encuentra"); // Si el hijo derecho es null, el
                                                                                // elemento no se encuentra
            else
                buscar(nodoAVL.getDer(), o);
        } else {
            System.out.println("El elemento si está en el árbol"); // Si la comparación es igual, el elemento está
                                                                   // presente en el árbol
        }
    }

    public static void main(String[] args) {
        ArrayList<String> palabras = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer("src\\main\\resources\\medline_CDs.txt");
        ArbolAVL arbol = new ArbolAVL();
        palabras = tokenizer.Tokenize();
        // System.out.println(palabras);
        for (String palabra : palabras) {
            arbol.insertar(palabra);
        }

        arbol.posOrden();
        // System.out.println("Buscando datos en el árbol");
        // try {
        // arbol.buscar("óvulo");
        // } catch (ItemNotFoundException e) {
        // System.out.println("Error: " + e.getMessage());
        // }
    }

}
