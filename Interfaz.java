
/**
 * Write a description of class Interfaz here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.Dimension;
import java.awt.Toolkit;
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

    public Interfaz(JTextField txtE, JTextArea txtA, JTextArea txtC, Mosaico mosaico, Piso piso){
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        largo = (int)tamanoPantalla.getWidth();
        ancho = (int)tamanoPantalla.getHeight();

        ventanaPrincipal = new VentanaPrincipal(txtE, txtA, txtC, mosaico);
        this.piso = piso;
    }

    public void mostrarPiso(){
        CanvasPiso canvasPiso = new CanvasPiso(piso, 800, 600);
        JFrame frame = new JFrame();
        int vertexes = 0;
        // Change this next part later to be dynamic.
        vertexes = 10;
        int canvasSize = vertexes * vertexes;
        frame.setSize(canvasSize, canvasSize);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(canvasPiso.getView());
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public void cargarVentana(){
        ventanaPrincipal.crearVentana();
    }
}
