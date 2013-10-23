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
	private JTextArea txtEstado, txtComandos;

	/** Etiqueta para mensajes. */
	private JLabel lblMsj;

	/**
	 * Controla si ya se ha hecho al menos un cambio, para evitar ventana
	 * molesta de guardar al final.
	 */
	private boolean algunCambio;

	/**
	 * Instancia un nuevo controlador.
	 */
	public Controlador() {
		algunCambio = false;
		almacenamiento = new Almacenamiento();
		if (cargarAnterior()) {
			// si hay datos guardados, lealos
			piso = almacenamiento.leerSesion();
			mosaico = piso.getMosaico();
			if (piso == null) {
				//hubo un error en la lectura
				mosaico = new Mosaico();
				piso = new Piso(mosaico);
				setDefaults();
			}
		} else {
			// sesion nueva
			mosaico = new Mosaico();
			piso = new Piso(mosaico);
			this.setDefaults();
		}

		txtEntrada = new JTextField();
		txtEstado = new JTextArea();
		txtComandos = new JTextArea();
		lblMsj = new JLabel();

		ventanaPrincipal = new VentanaPrincipal(txtEntrada, txtEstado,
				txtComandos, lblMsj, mosaico);
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
		return (almacenamiento.haySesionGuardada() && JOptionPane
				.showOptionDialog(
						null,
						"Desea cargar el mosaico creado en la sesion anterior?",
						"Mosaicos", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, new Object[] { "Si", "No" }, null) == 0) ? true
				: false;
	}

	/**
	 * Establece valos predefinidos para el mosaico y el piso, en caso que el
	 * usuario desee una sesion nueva.
	 */
	private void setDefaults() {
		mosaico.setLado(7);
		mosaico.setPatron(4);
		mosaico.generarMosaico();
		piso.setTamano(21, 28);
		piso.generarPiso();
	}

	/**
	 * Agrega los escuchadores tanto del campo de texto como el de cierre de la
	 * VentanaPrincipal.
	 */
	private void setListeners() {
		txtEntrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				algunCambio = true	;
				// aqui procesa el comando
				txtEntrada.setEnabled(false);
				setTextos(terminal.procesarComando(txtEntrada.getText()));
				txtEntrada.setEnabled(true);
				// refresca la venta principal
				interfaz.cargarVentana();
				txtEntrada.setText("");
				txtEntrada.requestFocus();
			}
		});

		ventanaPrincipal.addWindowListener(new WindowAdapter() {
			public void windowOpened(WindowEvent e) {
				txtEntrada.requestFocus();
			}
		});

		WindowListener exitListener = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (algunCambio) {
					int decisionSalida = interfaz
							.dialogoOpcion(
									"Desea guardar el mosaico creado para usarlo en la proxima sesion?",
									"Mosaicos", new Object[] { "Si", "No" }, JOptionPane.QUESTION_MESSAGE);
					if (decisionSalida == 0) {
						boolean sesionGuardada = almacenamiento
								.guardarSesion(piso);
						if (!sesionGuardada)
							interfaz.dialogoMensaje(
									"Lo sentimos, hubo un error al intentar guardar la sesion",
									"Mosaicos", JOptionPane.ERROR_MESSAGE);
					} else if (decisionSalida == 1)
						almacenamiento.borrarSesion();
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
		return "Dimensiones del mosaico: " + mosaico.getLado() + "x"
				+ mosaico.getLado() + "\nPatron del mosaico: #"
				+ String.valueOf(mosaico.getNumPatron()) + " ("
				+ mosaico.getNomPatron() + ")."
				+ "\nColor #1: " + mosaico.getCodigoColor(1) + "."
				+ "\nColor #2: " + mosaico.getCodigoColor(2) + "."
				+ "\nDimensiones del piso: " + piso.getN() + "x" + piso.getM()
				+ ".";
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
