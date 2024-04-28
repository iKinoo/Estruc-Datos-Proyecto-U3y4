package com.estructurasdatos.proyecto_U3y4.ordenamientos;
import java.io.*;

/**
 * Integrantes
 * - Rodrigo Joaquín Pacab Canul
 * - Orlando Isaías Rodríguez Couoh
 * - Rubén Moisés Loría Pech
 */

public class MezclaEquilibrada {
    
    public static void main(String[] args) {
        String F = "input.txt"; // Nombre del archivo principal
        String F1 = "aux1.txt"; // Nombre del archivo auxiliar 1
        String F2 = "aux2.txt"; // Nombre del archivo auxiliar 2
        String F3 = "aux3.txt"; // Nombre del archivo auxiliar 3
        
        mezclaEquilibrada(F, F1, F2, F3);
    }
    
    public static void mezclaEquilibrada(String F, String F1, String F2, String F3) {
        particionInicial(F, F2, F3);
        boolean BAND = true;
        
        do {
            if (BAND) {
                particionFusion(F2, F3, F, F1);
                BAND = false;
            } else {
                particionFusion(F, F1, F2, F3);
                BAND = true;
            }
        } while (!isEmpty(F1) && !isEmpty(F3));
    }
    
    public static void particionInicial(String F, String F2, String F3) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(F));
            BufferedWriter bw1 = new BufferedWriter(new FileWriter(F2));
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(F3));
            
            int R = Integer.parseInt(br.readLine());
            bw1.write(String.valueOf(R));
            bw1.newLine();
            
            int AUX = R;
            boolean B = true;
            
            String line;
            while ((line = br.readLine()) != null) {
                R = Integer.parseInt(line);
                if (R >= AUX) {
                    AUX = R;
                    if (B) {
                        bw1.write(String.valueOf(R));
                        bw1.newLine();
                    } else {
                        bw2.write(String.valueOf(R));
                        bw2.newLine();
                    }
                } else {
                    AUX = R;
                    if (B) {
                        bw2.write(String.valueOf(R));
                        B = false;
                    } else {
                        bw1.write(String.valueOf(R));
                        B = true;
                    }
                }
            }
            
            br.close();
            bw1.close();
            bw2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void particionFusion(String FA, String FB, String FC, String FD) {
        try {
            BufferedReader bra = new BufferedReader(new FileReader(FA));
            BufferedReader brb = new BufferedReader(new FileReader(FB));
            BufferedWriter bwc = new BufferedWriter(new FileWriter(FC));
            BufferedWriter bwd = new BufferedWriter(new FileWriter(FD));
            
            boolean B = true;
            int R1 = Integer.parseInt(bra.readLine());
            int R2 = Integer.parseInt(brb.readLine());
            int AUX;
            boolean DELE1 = false;
            boolean DELE2 = false;
            
            while (true) {
                if (DELE1 || (R1 < R2 && !DELE2)) {
                    AUX = R1;
                    if (DELE1) {
                        R1 = Integer.parseInt(bra.readLine());
                        DELE1 = false;
                    }
                } else {
                    AUX = R2;
                    if (DELE2) {
                        R2 = Integer.parseInt(brb.readLine());
                        DELE2 = false;
                    }
                }
                
                if ((bra.ready() || DELE1) && (brb.ready() || DELE2)) {
                    if (R1 < R2) {
                        if (R1 > AUX) {
                            ayuda1(AUX, R1, bwc, bwd, B);
                            DELE1 = true;
                        } else {
                            if (R2 > AUX) {
                                ayuda1(AUX, R2, bwc, bwd, B);
                                DELE2 = true;
                            } else {
                                ayuda2(AUX, R1, bwc, bwd, B);
                                DELE1 = true;
                            }
                        }
                    } else {
                        if (R2 > AUX) {
                            ayuda1(AUX, R2, bwc, bwd, B);
                            DELE2 = true;
                        } else {
                            if (R1 > AUX) {
                                ayuda1(AUX, R1, bwc, bwd, B);
                                DELE1 = true;
                            } else {
                                ayuda2(AUX, R2, bwc, bwd, B);
                                DELE2 = true;
                            }
                        }
                    }
                } else {
                    if (DELE1 && !bra.ready()) {
                        ayuda3(R2, FB, FC, FD, B);
                    }
                    if (DELE2 && !brb.ready()) {
                        ayuda3(R1, FA, FC, FD, B);
                    }
                    break;
                }
            }
            
            bra.close();
            brb.close();
            bwc.close();
            bwd.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void ayuda1(int AUX, int R, BufferedWriter FC, BufferedWriter FD, boolean B) throws IOException {
        AUX = R;
        if (B) {
            FC.write(String.valueOf(R));
            FC.newLine();
        } else {
            FD.write(String.valueOf(R));
            FD.newLine();
        }
    }
    
    public static void ayuda2(int AUX, int R, BufferedWriter FC, BufferedWriter FD, boolean B) throws IOException {
        AUX = R;
        if (B) {
            FD.write(String.valueOf(R));
            B = false;
        } else {
            FD.write(String.valueOf(R));
            B = true;
        }
    }
    
    public static void ayuda3(int R, String F, String FC, String FD, boolean B) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(F));
            BufferedWriter bw = new BufferedWriter(new FileWriter(B ? FC : FD, true));
            
            int AUX = R;
            String line;
            while ((line = br.readLine()) != null) {
                R = Integer.parseInt(line);
                if (R > AUX) {
                    ayuda1(AUX, R, bw, null, B);
                } else {
                    ayuda2(AUX, R, bw, null, B);
                }
            }
            
            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isEmpty(String file) {
        File f = new File(file);
        return f.length() == 0;
    }
}