
/**
 * Write a description of class EscribirArchivos here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Escribir{

    private JFileChooser fc;
    public Escribir(){

    }

    public String guardarImagen(BufferedImage bi){
        try{
            fc = new JFileChooser("$HOME");
            int retrival = fc.showSaveDialog(null);
            if (retrival == fc.APPROVE_OPTION){
                if(fc.getSelectedFile().getPath() != null){
                    File outputfile = new File(fc.getSelectedFile().getPath() + ".png");
                    ImageIO.write(bi, "png", outputfile);
                }
            }else{
                return "Error al guardar la imagen.";
            }

        }catch(IOException ioe){
            return "Error al guardar la imagen.";
        }
        return "Imagen guardada exitosamente.";
    }

}
