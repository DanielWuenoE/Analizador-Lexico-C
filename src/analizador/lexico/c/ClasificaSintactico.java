package analizador.lexico.c;

import Lectura.LeerArchivo;

public class ClasificaSintactico {

    Tipos tipo = new Tipos();
    ConversionCaracter conv = new ConversionCaracter();
//    String archivo = "el e perro $ la$dra eS una  prueba   a ", token;
    String archivo = "", token;
    LeerArchivo leer = new LeerArchivo();
    int actual = 0;

    public ClasificaSintactico() {
        leer.leerArchivo();
        archivo = leer.datos();
    }

    public String pedirToken() {
        q0(archivo);
        return token;
    }

    public void q0(String archivo) {
        if (actual < archivo.length()) {
            conv.convertirCaracter(archivo.charAt(actual));
            if (tipo.esEspacio(conv.getAscii())) {
                if (actual+1 < archivo.length()) {
                    actual++;
                    q0(archivo);
                }
            } else if (tipo.esMinuscula(conv.getAscii()) == true) {
                actual++;
                q1Identificador(archivo);
            } else {
                actual++;
                q2ErrorLexico(archivo, 0);
            }
        }
    }

    public void q1Identificador(String archivo) {
        int movs = 1;
        for (int i = actual; i < archivo.length(); i++) {
            conv.convertirCaracter(archivo.charAt(i));
            if (tipo.esEspacio(conv.getAscii())) {
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
        obj.pedirToken();
    }
}
