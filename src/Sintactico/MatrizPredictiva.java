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
        String a = " "; // pedir la primer palabra
        while(!pila.isEmpty()) {
            if (noEsTerminal(x)) {
                if(obtenProduccionMatrizP(x, a) != 0) {
                    //remplazar x con la producción(obtenProduccionMatrizP(x, a));
                    x = //frase
                    pila.pop(); //y un siclo push();
                    cicloPush(obtenProduccionMatrizP(x, a)); // derecha a izquierda
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
    
//    public void llDriver() {
//        String x = "";
//        String a = l.generarToken();
//        do {
//            x = pila.top();
//            JOptionPane.showMessageDialog(null, "a = " + a + " - x = " + x);
//            if (!esTerminal(x)) {
//                if (!x.equals("")) {
//                    System.out.println(x + " es no terminal..");
//                    if (g.getNoProd(x, a) != 0) {
//                        System.out.println("Sale " + pila.pop() + " de la pila");
//
//                        ArrayList<String> elementos = g.getProduccion(x, a);
//
//                        for (int i = 0; i < elementos.size(); i++)
//                            pila.push(elementos.get(elementos.size() - i - 1));
//
//                        x = pila.top();
//                    } else {
//                        System.out.println(ERROR_MATRIZ_PREDICTIVA);
//                        pila.vaciar();
//                    }
//                } else {
//                    pila.pop();
//                    x = pila.top();
//                }
//            } else {
//                if (x.equals(a)) {
//                    System.out.println("x = a");
//                    pila.pop();
//                    System.out.println("PilaVacia = " + pila.estaVacia());
//                    a = l.generarToken();
//                    x = pila.top();
//                }
//            }
//        } while (!pila.estaVacia() && !x.equals("$"));
//
//        System.out.println("x = " + x);
//        System.out.println(pila.tamaño());
//        System.out.println(pila.pop());
//    }
    
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