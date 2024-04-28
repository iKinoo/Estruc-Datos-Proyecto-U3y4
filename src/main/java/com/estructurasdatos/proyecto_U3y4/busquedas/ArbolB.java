package com.estructurasdatos.proyecto_U3y4.busquedas;

import java.util.ArrayList;
import java.util.List;


import com.estructurasdatos.proyecto_U3y4.Tokenizer;


public class ArbolB {
     int t;  
    List<Integer> keys;  
    List<ArbolB> children; 
    boolean leaf;  

    public ArbolB(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new ArrayList<>(2 * t - 1);
        this.children = new ArrayList<>(2 * t);
    }

    public void insertNonFull(int key) {
        int i = keys.size() - 1;
        if (leaf) {
            while (i >= 0 && keys.get(i) > key) {
                i--;
            }
            keys.add(i + 1, key);
        } else {
            while (i >= 0 && keys.get(i) > key) {
                i--;
            }
            ArbolB child = children.get(i + 1);
            if (child.keys.size() == 2 * t - 1) {
                splitChild(i + 1, child);
                if (key > keys.get(i + 1)) {
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
}

class BTree {
    ArbolB root;
    int t;

    public BTree(int t) {
        this.root = null;
        this.t = t;
    }

    public void insert(int key) {
        if (root == null) {
            root = new ArbolB(t, true);
            root.keys.add(key);
        } else {
            if (root.keys.size() == 2 * t - 1) {
                ArbolB s = new ArbolB(t, false);
                s.children.add(root);
                s.splitChild(0, root);
                root = s;
                root.insertNonFull(key);
            } else {
                root.insertNonFull(key);
            }
        }
    }
}

class MergeSort {
    void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void sort(int arr[], int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;
            sort(arr, l, m);
            sort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }


    public static void main(String[] args) {

        ArrayList<String> palabras =  new ArrayList<>();
        Tokenizer tokenizer = new Tokenizer("src\\main\\resources\\medline_CDs.txt");
        ArbolB arbol = new ArbolB(0, false);
        palabras = tokenizer.Tokenize(); 
        
      
        //MergeSort mergeSort = new MergeSort();
        //mergeSort.sort(palabras, 0, palabras. - 1);
        for (@SuppressWarnings("unused") String palabra : palabras) {
            arbol.insertNonFull(-1);
        }




    }

}

    
