import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;
    private JTextField txtEntrada;
    private JLabel lblPreview;
    private JLabel[] lbl;
    private JTextArea txtEstado, txtComandos;
    private CanvasPreview canvasPreview;
    private Mosaico mosaico;

    /**
     * Create the frame.
     */
    public VentanaPrincipal(JTextField txtE, JTextArea txtA, JTextArea txtC, Mosaico mosaico){
        super("Mosaicos");
        this.txtEntrada = txtE;
        this.txtComandos = txtC;
        this.txtEstado = txtA;
        this.mosaico = mosaico;
        this.lbl = new JLabel[4];

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 500, 285);
        crearVentana();
    }

    public void crearVentana(){
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtEntrada.setBounds(235, 130, 221, 20);
        contentPane.add(txtEntrada);
        txtEntrada.setColumns(10);

        txtEstado.setEditable(false);
        txtEstado.setBounds(235, 25, 221, 80);
        contentPane.add(txtEstado);

        lbl[0] = new JLabel("Mosaico seleccionado:");
        lbl[0].setBounds(25, 20, 140, 14);
        contentPane.add(lbl[0]);

        lbl[1] = new JLabel("Configuraciones actuales:");
        lbl[1].setBounds(235, 10, 160, 14);
        contentPane.add(lbl[1]);

        lbl[2] = new JLabel("Terminal:");
        lbl[2].setBounds(235, 110, 100, 14);
        contentPane.add(lbl[2]);

        lbl[3] = new JLabel("Comandos:");
        lbl[3].setBounds(235, 155, 100, 14);
        contentPane.add(lbl[3]);
 
        txtComandos.setEditable(false);
        txtComandos.setBounds(235, 175, 250, 80);
        contentPane.add(txtComandos);

        canvasPreview = new CanvasPreview(mosaico, 160);
        lblPreview = canvasPreview.getView();
        lblPreview.setBounds(25, 45, 189, 160);
        contentPane.add(lblPreview);

        this.setResizable(false);
        this.setVisible(true);
    }
}
