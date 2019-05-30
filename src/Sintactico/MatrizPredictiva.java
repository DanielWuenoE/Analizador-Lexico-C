package Sintactico;

import Estructuras.Pila;
import Gramatica.AcomodoGramatica;
import analizador.lexico.c.ClasificaSintactico;

public class MatrizPredictiva {
    
    Pila pila;
    AcomodoGramatica gramatica;
    ClasificaSintactico lexico;
    
    MatrizPredictiva() {
        pila = new Pila();
        gramatica = new AcomodoGramatica();
        lexico = new ClasificaSintactico();
        gramatica.ini();
    }
    
    public int matriz(int x, int y) {
        int[][] matrizPredictiva = {{1, 1, 1,  1,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    {2, 2, 2,  2,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    {3, 3, 3,  3,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    {0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  5,  5,  5,  5,  5,  5,  4,  4,  4,  4,  4,  4,  0},
                                    {0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  6,  6,  6,  6,  6,  6,  6,  6},
                                    {7, 8, 9, 10,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    {0, 0, 0,  0, 11, 12, 13, 14, 15, 16, 17,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0},
                                    {0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0, 18, 19, 20, 21, 22, 23,  0,  0,  0,  0,  0,  0,  0},
                                    {0, 0, 0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0,  0, 24, 25, 26, 27, 28, 29,  0}};
        return matrizPredictiva[x][y];
    }
    
    public int obtenProduccionMatrizP(String x, String a) {
        int posX = gramatica.indiceNoTerminal(x); // 0
        int posY = gramatica.indiceTerminal(a);  // 0
        return matriz(posX, posY);
    }
    
    public boolean noEsTerminal(String buscar) {
        for (String buscarEn : gramatica.simbolosNoTerminales) {
            if (buscarEn != null)
                if (buscarEn.equals(buscar))
                    return true;
        }
        return false;
    }
    
    public void LlDiver() { //a sera el lexema enviado del analizador lexico
        pila.push(gramatica.simboloInicial());
        String x = pila.peak(); // tope de la pila
        String a = lexico.pedirToken(); // pedir la primer palabra
        while(pila.isEmpty()) {
            if (noEsTerminal(x)) {
                if(obtenProduccionMatrizP(x, a) != 0) {
                    //remplazar x con la producción(obtenProduccionMatrizP(x, a));
                    System.out.println(":****:");
                    pila.pop(); //y un siclo push();
                    cicloPush(obtenProduccionMatrizP(x, a)); // derecha a izquierda
                } else {
                    errorSintactico(x);
                }
            } else {
                if(x.equals(a)) {
                    pila.pop();
                    //a = lectura(); //siguiente lexema ? : fin, en espera del analizador lexico ?
                    a = lexico.pedirToken();
                } else {
                    errorSintactico(x);
                }
            }
        }
    }
    
    private void cicloPush(int produccion) {
        String[] deriva = gramatica.produccionDerecha(produccion);    // la produccion a ingresar
        //for (String derivacion : deriva) {
        for (int i = deriva.length; i == 0; i--) {
            System.out.println("-." + i);
            pila.push(deriva[i]);
            System.out.println(deriva[i] + "--");
        }
    }
    
    private void errorSintactico(String x) {
        System.out.println("Error sintactico en: " + x);
    }
}

class test {
    public static void main(String[] args) {
        MatrizPredictiva m = new MatrizPredictiva();
        m.LlDiver();
        //m.obtenProduccionMatrizP(0, 7);
    }
}