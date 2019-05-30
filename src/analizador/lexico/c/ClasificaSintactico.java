package analizador.lexico.c;

import Estructuras.Listas;
import Lectura.LeerArchivo;

public class ClasificaSintactico {

    private String caso;
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    Listas list = new Listas();
    PalabraReservada pr = new PalabraReservada();
    String archivo = "esta eS    una prueba a ", token;
    int actual = 0;

    public String pedirToken() {

        q0(archivo);
        System.out.println(";" + token + ";");
        return token;
    }

    public void q0(String archivo) {
        System.out.println(actual);
        if (actual < archivo.length()) {
            conv.convertirCaracter(archivo.charAt(actual));
            if (tipo.esEspacio(conv.getAscii())) {
                if (actual++ < archivo.length()) {
                    actual++;
                    q0(archivo);
                }
            } else if (tipo.esMinuscula(conv.getAscii()) == true) {
                actual++;
                q1Identificador(archivo);
            } else {
                q2ErrorLexico(archivo, actual);
            }
        }
    }

    public void q1Identificador(String archivo) {
        int movs = 1;
        //System.out.println("Actual: " + actual);
        for (int i = actual; i < archivo.length(); i++) {
            //System.out.println("Caracter actual: " + archivo.charAt(i));
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii())) {
                System.out.println("identificador");
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs;
                break;
            } else if (tipo.esMinuscula(conv.getAscii()) == true) {
                movs++;
            } else {
                q2ErrorLexico(archivo, movs);
                break;
            }
        }
    }

    public void q2ErrorLexico(String archivo, int movs) {
        for (int i = actual + movs; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii()) == true) {
                System.out.println("error");
                movs++;
                token = crearCadena(actual - 1, actual + movs, archivo);
                actual = actual + movs;
                break;
            } else {
                movs++;
            }
        }

    }

    public String crearCadena(int i, int f, String archivo) {
        String cad = "";

        for (int j = i; j < f - 1; j++) {
            cad = cad + archivo.charAt(j);
        }

        return cad;
    }

    public static void main(String[] args) {
        ClasificaSintactico obj = new ClasificaSintactico();
//        LeerArchivo leer = new LeerArchivo();
//        leer.leerArchivo();

        String archivo = "Este .9 Este 0 00 .9 es 002 00t un Ar rA rA3 rA3.s 4.3e4 archivo archivo2 archi. 0. de prueba 12 12.12 0 0 00 edwsd 12 12.1 . . .. edsd # # ##  ";
        String archivo2 = ";";
        //String archivo3 = leer.datos();
        //obj.q0(archivo2);
        //        obj.im(archivo);
        obj.pedirToken();
        obj.pedirToken();
        obj.pedirToken();
        obj.pedirToken();
    }
}
