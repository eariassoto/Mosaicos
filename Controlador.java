/*
 * La clase Controlador se encarga de inicializar y manejar a todas las demas clases
 * 
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;


public class Controlador {

	/** El objeto mosaico. */
	private Mosaico mosaico;

	/** El objeto piso. */
	private Piso piso;

	/** El objeto interfaz. */
	private Interfaz interfaz;

	/** El objeto ventana principal. */
	private VentanaPrincipal ventanaPrincipal;

	/** El objeto terminal. */
	private Terminal terminal;

	/** El objeto almacenamiento. */
	private Almacenamiento almacenamiento;

	/** Campo de texto para entrada. */
	private JTextField txtEntrada;

	/** Areas de texto. */
	private JTextArea txtEstado, txtComandos, txtColores;

	/** Etiqueta para mensajes. */
	private JLabel lblMsj;

	/**
	 * Instancia un nuevo controlador.
	 */
	public Controlador() {
		almacenamiento = new Almacenamiento();
		if (cargarAnterior()) {
			// si hay datos guardados, lealos
			mosaico = almacenamiento.leerMosaico();
			piso = almacenamiento.leerPiso();
		} else {
			mosaico = new Mosaico();
			piso = new Piso(mosaico);
			this.setDefaults();
		}

		txtEntrada = new JTextField();
		txtEstado = new JTextArea();
		txtComandos = new JTextArea();
		txtColores = new JTextArea();
		lblMsj = new JLabel();

		ventanaPrincipal = new VentanaPrincipal(txtEntrada, txtEstado,
				txtComandos, txtColores, lblMsj, mosaico);
		interfaz = new Interfaz(ventanaPrincipal, piso);
		terminal = new Terminal(mosaico, piso, interfaz, almacenamiento);
		this.setTextos("Ingresa un comando en la terminal.");
		this.setListeners();
	}

	/**
	 * Pregunta al usuario si desea cargar los datos de la sesion anterior.
	 * 
	 * @return true, si el usuario decide que si
	 */
	private boolean cargarAnterior() {
		Object[] o = new Object[] { "Si", "No" };
		return (almacenamiento.haySesionGuardada() && JOptionPane
				.showOptionDialog(
						null,
						"�Desea cargar el mosaico creado en la sesion anterior?",
						"Mosaicos", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, o, null) == 0) ? true
				: false;
	}

	/**
	 * Establece valos predefinidos para el mosaico y el piso, en caso que el usuario
	 * desee una sesion nueva.
	 */
	private void setDefaults() {
		mosaico.setLado(4);
		mosaico.setColor(1, 3);
		mosaico.setColor(2, 4);
		mosaico.setPatron(1);
		piso.setTamano(24, 32);
		piso.generarPiso();
	}

	/**
	 * Agrega los escuchadores tanto del campo de texto como el de cierre de la VentanaPrincipal.
	 */
	private void setListeners() {
		txtEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				// aqui procesa el comando
				setTextos(terminal.procesarComando(txtEntrada.getText()));
				// refresca la venta principal
				interfaz.cargarVentana();
				txtEntrada.setText("");
			}
		});

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				Object[] o = new Object[] { "Si", "No" };
				int decisionSalida = interfaz
						.dialogoOpcion(
								"�Desea guardar el mosaico creado para usarlo en la proxima sesion?",
								"Mosaicos", o, JOptionPane.QUESTION_MESSAGE);
				if (decisionSalida == 0) {
					boolean b = almacenamiento.guardar(mosaico, piso);
					String msj = (b) ? "La sesion se ha guardado exitosamente"
							: "Lo sentimos, hubo un error al intentar guardar la sesion";
					int tipo = (b) ? JOptionPane.INFORMATION_MESSAGE
							: JOptionPane.ERROR_MESSAGE;
					interfaz.dialogoMensaje(msj, "Mosaicos", tipo);
				} else {
					if (decisionSalida == 1) {
						almacenamiento.borrarSesion();
					}
				}
			}
		};
		ventanaPrincipal.addWindowListener(exitListener);
	}

	/**
	 * Genera el estado actual del programa.
	 * 
	 * @return hilera con el resultado
	 */
	private String generarEstado() {
		String estado = "";
		estado += "Dimensiones del mosaico: " + mosaico.getLado();
		estado += "\nPatron del mosaico: #"
				+ String.valueOf(mosaico.getNumPatron()) + " ("
				+ mosaico.getNomPatron() + ").";
		estado += "\nColor #1: " + mosaico.getNombreColor(1) + ".";
		estado += "\nColor #2: " + mosaico.getNombreColor(2) + ".";
		estado += "\nDimensiones del piso: " + piso.getN() + "x" + piso.getM()
				+ ".";
		return estado;
	}

	/**
	 * Genera una hilera con la lista de colores disponibles.
	 * 
	 * @return resultado
	 */
	private String generarListaColores() {
		String l = "";
		int cantCol = mosaico.getCantidadColores();
		int n = 1;
		while (n <= cantCol) {
			l += String.valueOf(n) + ". " + mosaico.getNombre(n - 1);
			l += (n % 3 == 0) ? "\n" : "  ";
			n++;
		}
		return l;
	}

	/**
	 * Establece los mensajes de los campos de texto y la etiqueta.
	 * 
	 * @param s
	 *            hilera de la etiqueta
	 */
	private void setTextos(String s) {
		txtEstado.setText(generarEstado());
		txtComandos.setText(terminal.getListaComandos());
		txtColores.setText(generarListaColores());
		lblMsj.setText(s);
	}

	/**
	 * Metodo main.
	 * 
	 * @param emma
	 *            argumentos
	 */
	public static void main(String emma[]) {
		@SuppressWarnings("unused")
		Controlador c = new Controlador();
	}
}
