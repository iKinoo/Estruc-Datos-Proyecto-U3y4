package com.estructurasdatos.proyecto_U3y4.busquedas;

import java.util.ArrayList;
import java.util.List;


import com.estructurasdatos.proyecto_U3y4.Tokenizer;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ArbolB {
    int t;  
    List<String> keys;  
    List<ArbolB> children; 
    boolean leaf;  

    public ArbolB(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new ArrayList<>(2 * t - 1);
        this.children = new ArrayList<>(2 * t);
    }

    public void insertNonFull(String key) {
        int i = keys.size() - 1;
        if (leaf) {
            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                i--;
            }
            keys.add(i + 1, key);
        } else {
            while (i >= 0 && keys.get(i).compareTo(key) > 0) {
                i--;
            }
            ArbolB child = children.get(i + 1);
            if (child.keys.size() == 2 * t - 1) {
                splitChild(i + 1, child);
                if (key.compareTo(keys.get(i + 1)) > 0) {
                    i++;
                }
            }
            children.get(i + 1).insertNonFull(key);
        }
    }

    public void splitChild(int i, ArbolB y) {
        ArbolB z = new ArbolB(y.t, y.leaf);
        for (int j = 0; j < t - 1; j++) {
            z.keys.add(y.keys.remove(t));
        }
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.children.add(y.children.remove(t));
            }
        }
        children.add(i + 1, z);
        keys.add(i, y.keys.remove(t - 1));
    }

    public boolean search(String key) {
        int i = 0;
        while (i < keys.size() && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        if (i < keys.size() && keys.get(i).equals(key)) {
            return true;
        }
        if (leaf) {
            return false;
        } else {
            return children.get(i).search(key);
        }
    }
}

class BTree {
    ArbolB root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void insert(String key) {
        if (root == null) {
            root = new ArbolB(t, true);
            root.keys.add(key);
        } else {
            if (root.keys.size() == 2 * t - 1) {
                ArbolB s = new ArbolB(t, false);
                s.children.add(root);
                s.splitChild(0, root);
                root = s;
            }
            root.insertNonFull(key);
        }
    }

    public boolean search(String key) {
        if (root == null) {
            return false;
        } else {
            return root.search(key);
        }
    }



    public static void main(String[] args) {
        
        BTree t = new BTree(3);
        ArrayList<String> palabras = new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer("src\\main\\resources\\medline_CDs.txt");
        ArbolB arbol = new ArbolB(3,true);
        palabras = tokenizer.Tokenize();

        
        for (String palabra : palabras) {
            t.insert(palabra);
        }

        /* 
        String searchWord = "banana";
        if (t.search(searchWord)) {
            System.out.println("La palabra '" + searchWord + "' se encontró en el árbol.");
        } else {
            System.out.println("La palabra '" + searchWord + "' no se encontró en el árbol.");
        }
        */
    }
}
