package analizador.lexico.c;

//**********            Enumerado Palabras Reservadas            **********//
    
enum PalabrasReservadas { // lista de palabras reservadas a usar
        BOOLEAN (200),
        BREAK (201),
        BYTE (203),
        CASE (204),
        CHAR (205),
        CLASS (206),
        DO (207),
        DOUBLE (208),
        ELSE (209),
        EXTENDS (210),
        FINAL (211),
        FLOAT (212),
        FOR (213),
        IF (214),
        IMPORT (215),
        INT (216),
        INTERFACE (217),
        LONG (218),
        NEW (219),
        PACKAGE (220),
        PRIVATE (221),
        PROTECTED (222),
        PUBLIC (223),
        RETURN (224),
        SHORT (225),
        STATIC (226),
        SWITCH (227),
        THIS (228),
        TRY (229),
        VOID (230),
        WHILE (231);
    
    private final int valor;  // valor numérico de cada palabra reservada
    
    private PalabrasReservadas (int valor){  // constructor de la clase enum PalabrasReservadas
        this.valor = valor;
    }
    
    public int getValor(){ // método para conseguir el valor numérico de cada palabrareservada
        return valor;
    }
}

public class PalabraReservada {
    // método que recibe un string (palabra reservada) y verifica si existe en el enumerado
    public boolean ExistePalabraReservada(String pReservada) {
        // ciclo que recorre el enumerado hasta encontrar la palabra recibida
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
                if (pr.name().toLowerCase().equals(pReservada))
                    return true; // si se encuentra regresa un true
        }
        return false; // en caso de que no existe se regresa un false
    }
    // método que devuelve el valor numerico de una palabra reservada
    public int getValorPalabraReservada(String pReservada) {
        // ciclo que recorre el enumerado hasta encontrar la palabra recibida
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
                if (pr.name().toLowerCase().equals(pReservada))
                    return pr.getValor(); // si la encuentra devuelve su valor numérico
        }
        return -1; // en caso contrario masna un -1 que seria el caso de error (no encontrado)
    }
    
    public void reservadas() {
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
            System.out.println(pr.name() + "\t" + pr.getValor());
        }
    }
}

class test {
    public static void main(String[] args) {
        PalabraReservada pr = new PalabraReservada();
        System.out.println("Existe: " + pr.ExistePalabraReservada("boolean"));
        System.out.println("Palabra: " + pr.getValorPalabraReservada("boolean"));
        
    }
}