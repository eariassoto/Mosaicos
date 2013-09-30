
/**
 * Write a description of class Interfaz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JFrame;

public class Interfaz{   
    private int largo, ancho;
    private Piso piso;
    private VentanaPrincipal ventanaPrincipal;
    private CanvasPiso canvasPiso;

    public Interfaz(JTextField txtE, JTextArea txtA, JTextArea txtC, JLabel lblMsj, Mosaico mosaico, Piso piso){
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        largo = (int)tamanoPantalla.getWidth()-250;
        ancho = (int)tamanoPantalla.getHeight()-150;

        ventanaPrincipal = new VentanaPrincipal(txtE, txtA, txtC, lblMsj, mosaico);
        this.piso = piso;
    }

    public void mostrarPiso(){
        CanvasPiso canvasPiso = new CanvasPiso(piso, largo, ancho);
        JFrame frame = new JFrame("Piso Generado");
        frame.setSize(canvasPiso.getLargo(), canvasPiso.getAncho());
        frame.setResizable(false);
        frame.getContentPane().add(canvasPiso.getView());
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public BufferedImage getSuperficie(){
        CanvasPiso canvas = new CanvasPiso(piso, largo, ancho);
        return canvas.getSuperficie();
    }

    public void cargarVentana(){
        ventanaPrincipal.crearVentana();
    }
}
