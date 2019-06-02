package Estructuras;

public class Pila {
    Nodo inicio, fin;
    
    public Pila() {
        inicio = null;
    }
    
    public class Nodo {
        Nodo sig;
        String dato;
        
        public Nodo(String dato) {
            this.dato = dato;
        }
        
        @Override
        public String toString() {//conversion de posicion de memoria a string
            return dato.toString();
        }
    }
    
    public void push(String dato) {
        Nodo nuevo = new Nodo(dato);
        push(nuevo);
    }
    
    private void push(Nodo dato) {
        if (inicio != null) {
            dato.sig = inicio;
            inicio = dato;
        } else
            inicio = fin = dato;
    }
    
    public void pop() {
        Nodo temp = inicio;
        if (inicio != null) {
            inicio = temp.sig;
            temp.sig = null;
        } else 
            System.out.println("Pila vacia");
    }
    
    public String peak() {
        if (inicio != null) {
            return  inicio.dato;
        } else
            return null;
    }
    
    public boolean isEmpty() {
        return inicio != null;
    }
    
    public void imprime() {
        Nodo temp = inicio;
        while (temp != null) {
            System.out.println(temp);
            temp = temp.sig;
        }
    }
}

class test {
//    public static void main(String[] args) {
//        Pila p = new Pila();
//        p.push("1");
//        p.push("2");
//        p.push("3");
//        p.push("4");
//        System.out.println(":" + p.peak());
//        p.pop();
//        p.imprime();
//    }
}