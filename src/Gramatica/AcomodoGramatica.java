package Gramatica;

import Lectura.LeerArchivo_1;
import Lectura.NoEsDeString;

public class AcomodoGramatica {
    
    private String[] gramaticaCompleta;
    private String[][] todasLasProducciones;
    private String[] ladosDerechos;
    public String[] simbolosNoTerminales;
    public String[] simbolosTerminales;
    private String[] temp;
    private final LeerArchivo_1 leerA;
    private final NoEsDeString nes;
    
    public AcomodoGramatica() {
        leerA = new LeerArchivo_1();
        nes = new NoEsDeString();
    }

    private void leerGramatica() {
        leerA.leerArchivo();
    }
    
    public void ini() {
        leerGramatica();
        separarProducciones();
        definirArreglos();
        todasLasProducciones();
        ladosDerechos();
        simbolosNoTerminales();
        simbolosTerminales();
        //System.out.println(nes.cantidadDeCadenas());
        //System.out.println(nes.NoEsTrim(gramatica));
    }
    
    private void definirArreglos() {
        todasLasProducciones = new String[nes.cantidadDeCadenas()][nes.cantidadDeCadenas()];
        ladosDerechos = new String[nes.cantidadDeCadenas()];
        simbolosNoTerminales = new String[nes.cantidadDeCadenas()];
        simbolosTerminales = new String[nes.cantidadDeCadenas()];
        temp = new String[2];
    }
    
    private void todasLasProducciones() {
        for (int i = 0; i < gramaticaCompleta.length-1; i++) {
            temp = nes.noEsEsplit(gramaticaCompleta[i], ">");
            todasLasProducciones[i][0] = nes.NoEsTrim(temp[0]); // lado izquierdo
            todasLasProducciones[i][1] = nes.NoEsTrim(temp[1]); // lado derecho
        }
    }
    
    private void ladosDerechos() {
        for (int i = 0; i < todasLasProducciones.length; i++) {
            ladosDerechos[i] = todasLasProducciones[i][1];
        }
    }
    
    private void simbolosNoTerminales() {
        int i = 0, j = 0;
        while (j < todasLasProducciones.length) {
            if (!existeEnArreglo(simbolosNoTerminales, todasLasProducciones[j][0])) {
                simbolosNoTerminales[i] = todasLasProducciones[j][0];
                i++;
            }
            j++;
        }
    }
    
    private void simbolosTerminales() {
        int i = 0, j = 0;
        while (j < todasLasProducciones.length) {
            temp = nes.noEsEsplit(todasLasProducciones[j][1], " ");
            for (String tempDerecha : temp) {           //  949 - vacio - 'ε'
                if (!existeEnArreglo(simbolosNoTerminales, tempDerecha)&& ((int)tempDerecha.charAt(0) != 949)) {
                    simbolosTerminales[i] = todasLasProducciones[j][1];
                    i++;
                }
            }
            j++;
        }
    }
    
    private boolean existeEnArreglo(String[] arreglo, String buscar) {
        for (String buscarEn : arreglo) {
            if (buscarEn != null)
                if (buscarEn.equals(buscar))
                    return true;
        }
        return false;
    }
    
    public int indiceNoTerminal(String buscar) {
        for (int i = 0; i < simbolosNoTerminales.length; i++) {
            if (simbolosNoTerminales[i].equals(buscar)){
                System.out.println("encontrado");
                return i;
            }
        }
        return 0;
    }
    
    public int indiceTerminal(String buscar) {
        for (int i = 0; i < simbolosTerminales.length; i++) {
            if (simbolosTerminales[i].equals(buscar))
                return i;
        }
        return 0;
    }
    
    public String[] produccionDerecha(int produccion) {
        if (todasLasProducciones[produccion - 1][1] != null) {
            return nes.noEsEsplit(todasLasProducciones[produccion - 1][1], " ");
        }
        return null;
    }
    
    private void imprimeGramaticaCompleta() {
        for (String[] prod : todasLasProducciones)
            System.out.println(prod[0] + "\t-->\t" + prod[1]);
    }
    
    public void imprime() {
        System.out.println("\tGramática completa");
        imprimeGramaticaCompleta();
        System.out.println("\n\tLados Derechos");
        imprime(ladosDerechos);
        System.out.println("\n\tSímbolos No Terminales");
        imprime(simbolosNoTerminales);
        System.out.println("\n\tSímbolos Terminales");
        imprime(simbolosTerminales);
    }
    
    private void separarProducciones() {
        gramaticaCompleta = nes.noEsEsplit(leerA.datos(), "\n");
    }
    
    private void imprime(String[] arreglo) {
        for (String prod : arreglo) {
            if (prod != null)
                System.out.println(prod);
        }
    }
    
    public String simboloInicial() {
        return simbolosNoTerminales[0];
    }
}

class test {
    public static void main(String[] args) {
        AcomodoGramatica ag = new AcomodoGramatica();
        ag.ini();
        ag.imprime();
    }
}