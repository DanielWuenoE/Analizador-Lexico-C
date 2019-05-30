package Sintactico;

import Estructuras.Pila;
import Gramatica.AcomodoGramatica;

public class MatrizPredictiva {
    
    Pila pila = new Pila();
    AcomodoGramatica gramatica;
    
    MatrizPredictiva() {
        gramatica = new AcomodoGramatica();
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
    
    public int obtenProduccionMatrizP(String x, String y) {
        int posX = gramatica.indiceNoTerminal(x);
        int posY = gramatica.indiceTerminal(y);
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
    
    public void LlDiver(String a) { //a sera el lexema enviado del analizador lexico
        pila.push(gramatica.simboloInicial());
        String x = pila.peak(); // tope de la pila
        while(!pila.isEmpty()) {
            if (noEsTerminal(x)) {
                if(obtenProduccionMatrizP(x, a) != 0) {
                    //remplazar x con la producción(obtenProduccionMatrizP(x, a));
                    pila.pop(); //y un siclo push();
                    cicloPush(obtenProduccionMatrizP(x, a));
                } else {
                    errorSintactico(x);
                }
            } else {
                if(x.equals(a)) {
                    pila.pop();
                    //a = lectura(); //siguiente lexema ? : fin, en espera del analizador lexico ?
                } else {
                    errorSintactico(x);
                }
            }
        }
    }
    
    private void cicloPush(int produccion) {
        String[] deriva = gramatica.produccionDerecha(0);    // la produccion a ingresar
        for (String derivacion : deriva) {
            pila.push(derivacion);
        }
    }
    
    private void errorSintactico(String x) {
        System.out.println("Error sintactico en: " + x);
    }
}

class test {
    public static void main(String[] args) {
        MatrizPredictiva m = new MatrizPredictiva();
        //m.obtenProduccionMatrizP(0, 7);
    }
}