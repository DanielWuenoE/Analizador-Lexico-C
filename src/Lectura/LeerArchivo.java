package Lectura;

import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class LeerArchivo extends javax.swing.JFrame {
    
    private JFileChooser ventana = null;
    private File archivo = null;
    private String cadena = "";
        
    public void leerArchivo(){
        String bfRead;
        ventana = new JFileChooser();
        ventana.setCurrentDirectory(new File("src/Archivos/"));
        FileNameExtensionFilter filtroTxt = new FileNameExtensionFilter("TXT", "txt");
        ventana.setFileFilter(filtroTxt);
        ventana.setDialogTitle("Abrir archivo");
        if (ventana.showOpenDialog(ventana) == JFileChooser.APPROVE_OPTION) {
            archivo = ventana.getSelectedFile();
            try{
                BufferedReader bf = new BufferedReader(new FileReader(archivo));
                while ((bfRead = bf.readLine()) != null)
                    cadena += bfRead + ' ';
            } catch (HeadlessException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    
    public String rutaArchivo() {
        return ventana.getSelectedFile().toString();
    }
    
    public String datos() {
        return cadena + ' ';
    }
}