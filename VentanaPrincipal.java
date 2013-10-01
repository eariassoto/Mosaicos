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
    private JLabel lblPreview, lblMsj;
    private JLabel[] lbl, lblPatron;
    private JTextArea txtEstado, txtComandos, txtColores;
    private CanvasPreview canvasPreview, canvasPatron;
    private Mosaico mosaico;
    private Mosaico[] mosaicoPatron;

    /**
     * Create the frame.
     */
    public VentanaPrincipal(JTextField txtE, JTextArea txtA, JTextArea txtC, JTextArea txtCol, JLabel lblMsj, Mosaico mosaico){
        super("Mosaicos");
        this.txtEntrada = txtE;
        this.txtComandos = txtC;
        this.txtEstado = txtA;
        this.txtColores = txtCol;
        this.lblMsj = lblMsj;
        this.mosaico = mosaico;
        this.mosaicoPatron = new Mosaico[4];
        for(int i = 0; i < 4; i++){
            mosaicoPatron[i] = new Mosaico(7, i+1, 1, 2);
        }
        this.lbl = new JLabel[10];
        this.lblPatron = new JLabel[4];

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 450);
        crearVentana();
    }

    public void crearVentana(){
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtEntrada.setBounds(235, 140, 221, 20);
        contentPane.add(txtEntrada);
        txtEntrada.setColumns(10);

        txtEstado.setEditable(false);
        txtEstado.setBounds(235, 25, 221, 80);
        contentPane.add(txtEstado);

        txtComandos.setEditable(false);
        txtComandos.setBounds(235, 215, 250, 100);
        contentPane.add(txtComandos);

        txtColores.setEditable(false);
        txtColores.setBounds(15, 360, 250, 100);
        contentPane.add(txtColores);
        
        lblMsj.setBounds(235, 165, 221, 14);
        contentPane.add(lblMsj);

        lbl[0] = new JLabel("Mosaico seleccionado:");
        lbl[0].setBounds(10, 10, 140, 14);
        contentPane.add(lbl[0]);

        lbl[1] = new JLabel("Configuraciones actuales:");
        lbl[1].setBounds(235, 10, 160, 14);
        contentPane.add(lbl[1]);

        lbl[2] = new JLabel("Terminal:");
        lbl[2].setBounds(235, 120, 100, 14);
        contentPane.add(lbl[2]);

        lbl[3] = new JLabel("Comandos:");
        lbl[3].setBounds(235, 200, 100, 14);
        contentPane.add(lbl[3]);

        lbl[4] = new JLabel("Patrones disponibles:");
        lbl[4].setBounds(10, 200, 140, 14);
        contentPane.add(lbl[4]);

        lbl[5] = new JLabel("#1");
        lbl[5].setBounds(15, 220, 14, 14);
        contentPane.add(lbl[5]);
        
        lbl[6] = new JLabel("#2");
        lbl[6].setBounds(105, 220, 14, 14);
        contentPane.add(lbl[6]);
       
        lbl[7] = new JLabel("#3");
        lbl[7].setBounds(15, 285, 14, 14);
        contentPane.add(lbl[7]);
        
        lbl[8] = new JLabel("#4");
        lbl[8].setBounds(105, 285, 14, 14);
        contentPane.add(lbl[8]);
        
        lbl[9] = new JLabel("Colores Disponibles: ");
        lbl[9].setBounds(15, 345, 140, 14);
        contentPane.add(lbl[9]);
        
        canvasPatron = new CanvasPreview(mosaicoPatron[0], 50);
        lblPatron[0] = canvasPatron.getView();
        lblPatron[0].setBounds(30, 220, 65, 50);
        contentPane.add(lblPatron[0]);

        canvasPatron = new CanvasPreview(mosaicoPatron[1], 50);
        lblPatron[1] = canvasPatron.getView();
        lblPatron[1].setBounds(120, 220, 65, 50);
        contentPane.add(lblPatron[1]);

        canvasPatron = new CanvasPreview(mosaicoPatron[2], 50);
        lblPatron[2] = canvasPatron.getView();
        lblPatron[2].setBounds(30, 285, 65, 50);
        contentPane.add(lblPatron[2]);

        canvasPatron = new CanvasPreview(mosaicoPatron[3], 50);
        lblPatron[3] = canvasPatron.getView();
        lblPatron[3].setBounds(120, 285, 65, 50);
        contentPane.add(lblPatron[3]);

        canvasPreview = new CanvasPreview(mosaico, 160);
        lblPreview = canvasPreview.getView();
        lblPreview.setBounds(10, 35, 189, 160);
        contentPane.add(lblPreview);

        this.setResizable(false);
        this.setVisible(true);
    }
}
