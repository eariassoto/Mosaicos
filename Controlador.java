
/**
 * Write a description of class Controlador here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JLabel;

public class Controlador{

    private Mosaico mosaico;
    private Piso piso;
    private Interfaz interfaz;
    private Terminal terminal;
    private Escribir escribir;

    private JTextField txtEntrada;
    private JTextArea txtEstado, txtComandos;
    private JLabel lblMsj;

    public Controlador(){
        escribir = new Escribir();
        mosaico = new Mosaico();
        piso = new Piso(mosaico);
        setDefaults();

        txtEntrada = new JTextField();
        txtEstado = new JTextArea();
        txtComandos = new JTextArea();
        lblMsj = new JLabel();

        interfaz = new Interfaz(txtEntrada, txtEstado, txtComandos, lblMsj, mosaico, piso);
        terminal = new Terminal(mosaico, piso, interfaz, escribir);
        setTextos("Ingresa un comando en la terminal.");

        setListeners();
    }

    private void setDefaults(){
        mosaico.setLado(5);
        mosaico.setColor(1,1);
        mosaico.setColor(2,3);
        mosaico.setPatron(4);
        piso.setDimensiones(100,100);
        piso.generarPiso();
    }

    private void setListeners(){
        txtEntrada.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {            
                    setTextos(terminal.procesarComando(txtEntrada.getText()));
                    interfaz.cargarVentana();   
                    txtEntrada.setText("");
                }
            });
    }

    private String generarEstado(){
        String estado = "";
        estado += "Dimensiones del mosaico: " + mosaico.getLado();
        estado += "\nPatron del mosaico: #" + String.valueOf(mosaico.getNumPatron()) + " (" + mosaico.getNomPatron() + ").";
        estado += "\nColor #1: " + mosaico.getNombreColor(1) + ".";
        estado += "\nColor #2: " + mosaico.getNombreColor(2) + ".";
        estado += "\nDimensiones del piso: " + piso.getN() + "x" + piso.getM() +".";
        return estado;
    }

    private void setTextos(String s){
        txtEstado.setText(generarEstado());
        txtComandos.setText(terminal.getListaComandos());
        lblMsj.setText(s);
    }

    public static void main(String emma[]){
        Controlador c = new Controlador();
    }
}
