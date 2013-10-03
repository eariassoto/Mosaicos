/*
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;

// TODO: Auto-generated Javadoc
/**
 * The Class VentanaPrincipal.
 */
@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame {

	/** El panel contenedor. */
	private JPanel contentPane;

	/** Se encarga de manejar la entrada de comandos. */
	private JTextField txtEntrada;

	/** Etiquetas para mostrar la vista previa y mensajes. */
	private JLabel lblPreview, lblMsj;

	/**
	 * Vector de etiquetas con texto y vector con etiquetas que van a mostrar
	 * vistas previas.
	 */
	private JLabel[] lbl, lblPatron;

	/** Areas de texto con texto. */
	private JTextArea txtEstado, txtComandos, txtColores;

	/** Clases para dibujar las vistas previas. */
	private CanvasPreview canvasPreview, canvasPatron;

	/** Los objetos mosaico. */
	private Mosaico mosaico, mosaicoPatron;

	/**
	 * Crea una nueva ventana.
	 * 
	 * @param txtE
	 *            campo de texto para entrada
	 * @param txtA
	 *            area de texto
	 * @param txtC
	 *            area de texto
	 * @param txtCol
	 *            area de texto
	 * @param lblMsj
	 *            etiqueta para mensajes
	 * @param m
	 *            objeto mosaico
	 */
	public VentanaPrincipal(JTextField txtE, JTextArea txtA, JTextArea txtC,
			JTextArea txtCol, JLabel lblMsj, Mosaico m) {
		super("Mosaicos");
		this.txtEntrada = txtE;
		this.txtComandos = txtC;
		this.txtEstado = txtA;
		this.txtColores = txtCol;
		this.lblMsj = lblMsj;
		this.mosaico = m;

		this.lbl = new JLabel[10];
		this.lblPatron = new JLabel[4];

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 450);
		crearVentana();
	}

	/**
	 * Agrega componentes graficos a la ventana.
	 */
	public void crearVentana() {
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

		mosaicoPatron = new Mosaico(7, 1, 1, 2);
		canvasPatron = new CanvasPreview(mosaicoPatron, 50);
		lblPatron[0] = canvasPatron.view;
		lblPatron[0].setBounds(30, 220, 65, 50);
		contentPane.add(lblPatron[0]);

		mosaicoPatron = new Mosaico(7, 2, 1, 2);
		canvasPatron = new CanvasPreview(mosaicoPatron, 50);
		lblPatron[1] = canvasPatron.view;
		lblPatron[1].setBounds(120, 220, 65, 50);
		contentPane.add(lblPatron[1]);

		mosaicoPatron = new Mosaico(7, 3, 1, 2);
		canvasPatron = new CanvasPreview(mosaicoPatron, 50);
		lblPatron[2] = canvasPatron.view;
		lblPatron[2].setBounds(30, 285, 65, 50);
		contentPane.add(lblPatron[2]);

		mosaicoPatron = new Mosaico(7, 4, 1, 2);
		canvasPatron = new CanvasPreview(mosaicoPatron, 50);
		lblPatron[3] = canvasPatron.view;
		lblPatron[3].setBounds(120, 285, 65, 50);
		contentPane.add(lblPatron[3]);

		canvasPreview = new CanvasPreview(mosaico, 160);
		lblPreview = canvasPreview.view;
		lblPreview.setBounds(10, 35, 189, 160);
		contentPane.add(lblPreview);

		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * La clase interna CanvasPiso se encarga de crear la representacion grafica
	 * del mosaico para mostrar vistas previas al usuario.
	 */
	private class CanvasPreview {

		/** El objeto mosaico. */
		private Mosaico mosaico;

		/** El tamano del mosaico y el lado de cada celda. */
		private int lado;

		/** The matriz mosaico. */
		private int[][] matrizMosaico;

		/** La superficie donde se va a dibujar el mosaico. */
		private BufferedImage superficie;

		/** El JLabel que desplegara el BufferedImage. */
		private JLabel view;

		/**
		 * Instancia un nuevo canvasPreview.
		 * 
		 * @param m
		 *            the m
		 * @param tamano
		 *            tamano del mosaico
		 */
		public CanvasPreview(Mosaico mos, int tamano) {
			this.mosaico = mos;
			matrizMosaico = mosaico.getMatrizMosaico();
			lado = (int) tamano / matrizMosaico.length;
			superficie = new BufferedImage(tamano, tamano,
					BufferedImage.TYPE_INT_RGB);
			view = new JLabel(new ImageIcon(superficie));
			Graphics g = superficie.getGraphics();

			g.setColor(Color.WHITE);
			g.fillRect(0, 0, tamano, tamano);

			int coordX = 0, coordY = 0;
			for (int i = 0; i < matrizMosaico.length; i++) {
				coordX = 0;
				for (int j = 0; j < matrizMosaico.length; j++) {
					if (matrizMosaico[i][j] == 1) {
						g.setColor(mosaico.getColor(1));
						g.fillRect(coordX, coordY, lado, lado);
					} else {
						g.setColor(mosaico.getColor(2));
						g.fillRect(coordX, coordY, lado, lado);
					}
					coordX += lado;
				}
				coordY += lado;
			}
			g.dispose();
			view = new JLabel(new ImageIcon(superficie));
		}
	}
}
