import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;
    private JTextField txtEntrada;
    private JLabel lblPreview;
    private JButton[] btn;
    private JTextArea txtEstado;
    private CanvasPreview canvasPreview;

    /**
     * Create the frame.
     */
    public VentanaPrincipal(JTextField txtE, JButton[] btn, JTextArea txtA, Mosaico mosaico) {
        this.txtEntrada = txtE;
        this.txtEstado = txtA;
        this.btn = btn;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 285);

        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtEntrada.setBounds(218, 212, 157, 20);
        contentPane.add(txtEntrada);
        txtEntrada.setColumns(10);

        btn[0].setBounds(385, 211, 89, 23);
        contentPane.add(btn[0]);

        btn[1].setBounds(218, 178, 116, 23);
        contentPane.add(btn[1]);

        btn[2].setBounds(385, 176, 89, 23);
        contentPane.add(btn[2]);
        
        btn[3].setBounds(385, 142, 89, 23);
        contentPane.add(btn[3]);
        
        btn[4].setBounds(218, 142, 89, 23);
        contentPane.add(btn[4]);
        
        txtEstado.setEditable(false);
        txtEstado.setBounds(253, 25, 221, 110);
        contentPane.add(txtEstado);

        canvasPreview = new CanvasPreview(mosaico, 160);
        lblPreview = canvasPreview.getView();
        lblPreview.setBounds(25, 25, 189, 160);
        contentPane.add(lblPreview);

        this.setVisible(true);
    }

}
