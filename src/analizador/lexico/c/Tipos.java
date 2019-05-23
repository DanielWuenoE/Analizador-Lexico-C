package analizador.lexico.c;

public class Tipos {
    
    public boolean esPunto(int ascii){
        if(ascii == 46 )
            return true;
        else
            return false;
    }
    
    public boolean esEspacio(int ascii){
        if(ascii == 32 || ascii == 3 || ascii == 9)
            return true;
        else
            return false;
    }
    
    public boolean esCero(int ascii){
        if(ascii == 48 )
            return true;
        else
            return false;
    }
    
    public boolean esMayuscula(int ascii){              //verifica si el caracter es mayuscula
        if(ascii >= 65 && ascii <= 90)
            return true;
        else
            return false;
    }
    
    public boolean esMinuscula(int ascii){              //verifica si el caracter es minuscula
        if(ascii >= 97 && ascii <= 122)
            return true;
        else
            return false;
    }
    
    public boolean esNumero(int ascii){                 //verifica si el caracter es numero
        if(ascii >= 49 && ascii <= 57)
            return true;
        else
            return false;
    }
    
    public boolean esCaracter(int ascii){               //verifica si el caracter es caracater especial
        if((ascii >= 33 && ascii <= 47) || (ascii >= 58 && ascii <= 64) ||
                (ascii >= 91 && ascii <= 96) || (ascii >= 123 && ascii <= 126))
            return true;
        else
            return false;
    }
}
