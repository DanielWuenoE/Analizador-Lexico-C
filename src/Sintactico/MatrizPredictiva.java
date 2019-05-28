package Sintactico;

public class MatrizPredictiva {
    
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
    
    public void obtenProduccionMatrizP() {
        int produccion = matriz(6, 5);
        System.out.println(":" + produccion);
    }
}

class test {
    public static void main(String[] args) {
        MatrizPredictiva m = new MatrizPredictiva();
        m.obtenProduccionMatrizP();
    }
}