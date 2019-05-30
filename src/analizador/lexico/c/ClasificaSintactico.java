package analizador.lexico.c;

import Estructuras.Listas;
import Lectura.LeerArchivo;

public class ClasificaSintactico {

    private String caso;
    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
    Listas list = new Listas();
    PalabraReservada pr = new PalabraReservada();

    public void q0(String archivo) {
        conv.convertirCaracter(archivo.charAt(0));
        if (tipo.esMinuscula(conv.getAscii()) == true || tipo.esMayuscula(conv.getAscii()) == true) {
            q1Identificador(archivo);
        } else {
            q2ErrorLexico(archivo);

        }
    }

    public void q1Identificador(String archivo) {
        int movs = 1;
        while (movs < archivo.length()) {
            conv.convertirCaracter(archivo.charAt(movs));
            if (tipo.esMayuscula(conv.getAscii()) == true || tipo.esMinuscula(conv.getAscii()) == true) {
                movs++;
            } else {
                q2ErrorLexico(archivo);
                break;
            }
        }
    }

    public void q2ErrorLexico(String archivo) {
        //deberia recibir el movs???
        System.out.println(archivo + "\tError Léxico");
    }

    public static void main(String[] args) {
        ClasificaSintactico obj = new ClasificaSintactico();
//        LeerArchivo leer = new LeerArchivo();
//        leer.leerArchivo();

        String archivo = "Este .9 Este 0 00 .9 es 002 00t un Ar rA rA3 rA3.s 4.3e4 archivo archivo2 archi. 0. de prueba 12 12.12 0 0 00 edwsd 12 12.1 . . .. edsd # # ##  ";
        String archivo2 = ";";
        //String archivo3 = leer.datos();
        obj.q0(archivo2);
        //        obj.im(archivo);
    }
}
