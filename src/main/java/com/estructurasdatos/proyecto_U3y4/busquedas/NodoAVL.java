package com.estructurasdatos.proyecto_U3y4.busquedas;

public class NodoAVL {
    protected int fe;
    protected NodoAVL padre;
    protected Object dato;
    protected NodoAVL izq;
    protected NodoAVL der;

    public NodoAVL() {
    }

    public NodoAVL(Object dato) {
        this.dato = dato;
    }

    public NodoAVL(Object dato, NodoAVL izq, NodoAVL der) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public NodoAVL(Object dato, NodoAVL izq, NodoAVL der, NodoAVL padre) {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
        this.padre = padre;
    }

    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }

    public static int altura(NodoAVL a) {
        if (a == null)
            return -1;
        else
            return 1 + Math.max(altura((NodoAVL) a.getIzq()),
                    altura((NodoAVL) a.getDer()));
    }

    public void inOrden() {
        if (izq != null)
            izq.inOrden();
        System.out.println(dato + " FE:" + getFe());
        if (der != null)
            der.inOrden();
    }

    public void preOrden() {
        System.out.println(dato + " FE:" + getFe());
        if (izq != null)
            izq.preOrden();
        if (der != null)
            der.preOrden();
    }

    public void posOrden() {
        if (izq != null)
            izq.posOrden();
        if (der != null)
            der.posOrden();
        System.out.println(dato + " FE:" + getFe());
    }

    /**
     * @return the fe
     */
    public int getFe() {
        return fe;
    }

    /**
     * @param fe the fe to set
     */
    public void setFe(int fe) {
        this.fe = fe;
    }

    /**
     * @return the padre
     */
    public NodoAVL getPadre() {
        return padre;
    }

    /**
     * @param padre the padre to set
     */
    public void setPadre(NodoAVL padre) {
        this.padre = padre;
    }
}
