
/**
 * Write a description of class Controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
public class Controlador{

    private Mosaico mosaico;
    private Piso piso;
    private Interfaz interfaz;

    private JTextField txtEntrada;
    private JButton[] btn;
    private JTextArea txtEstado;

    public Controlador(){
        txtEntrada = new JTextField();
        btn = new JButton[5];
        btn[0] = new JButton("Ayuda");
        btn[1] = new JButton("Generar Piso");
        btn[2] = new JButton("Exportar");
        btn[3] = new JButton("Guardar");
        btn[4] = new JButton("Cargar");
        txtEstado = new JTextArea();

        mosaico = new Mosaico();
        piso = new Piso(mosaico);
        setDefaults();
        
        interfaz = new Interfaz(txtEntrada, btn, txtEstado, mosaico, piso);
        setEstado();
    }

    private void setDefaults(){
        mosaico.setLado(11);
        mosaico.setColor(1,1);
        mosaico.setColor(2,3);
        mosaico.setPatron(4);
        piso.setDimensiones(100,100);
    }

    private String generarEstado(){
        String estado = "";
        estado += "Dimensiones del mosaico: " + mosaico.getLado();
        estado += "\nColor #1: " + mosaico.getNombreColor(1) + ".";
        estado += "\nColor #2: " + mosaico.getNombreColor(2) + ".";
        estado += "\nDimensiones del piso: " + piso.getN() + "x" + piso.getM() +".";
        return estado;
    }

    private void setEstado(){
        txtEstado.setText(generarEstado());
    }

    public static void main(String emma[]){
        Controlador c = new Controlador();
    }
}
