
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
public class Controlador{

    private Mosaico mosaico;
    private Piso piso;
    private Interfaz interfaz;
    private Terminal terminal;

    private JTextField txtEntrada;
    private JTextArea txtEstado, txtComandos;

    public Controlador(){
        mosaico = new Mosaico();
        piso = new Piso(mosaico);
        setDefaults();
        
        txtEntrada = new JTextField();
        txtEstado = new JTextArea();
        txtComandos = new JTextArea();
        
        interfaz = new Interfaz(txtEntrada, txtEstado, txtComandos, mosaico, piso);
        terminal = new Terminal(mosaico, piso, interfaz);
        setTextos();

        setListeners();
    }

    private void setDefaults(){
        mosaico.setLado(5);
        mosaico.setColor(1,1);
        mosaico.setColor(2,3);
        mosaico.setPatron(1);
        piso.setDimensiones(100,100);
        piso.generarPiso();
    }

    private void setListeners(){
        txtEntrada.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    if(terminal.procesarComando(txtEntrada.getText())){
                        setTextos();
                        interfaz.cargarVentana();
                    }

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

    private void setTextos(){
        txtEstado.setText(generarEstado());
        txtComandos.setText(terminal.getListaComandos());
    }

    public static void main(String emma[]){
        Controlador c = new Controlador();
    }
}
