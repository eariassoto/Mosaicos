/*
 * La clase Terminal procesa los comandos que recibe y ejecuta las ordenes
 * establecidas.
 * 
 * @author Emmanuel Arias Soto
 * @version 0.2
 */
import java.awt.Color;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.swing.JColorChooser;

public class Terminal {

	/** El objeto almacenamiento. */
	private Almacenamiento almacenamiento;

	/** El objeto mosaico. */
	private Mosaico mosaico;

	/** El objeto interfaz. */
	private Interfaz interfaz;

	/** El objeto piso. */
	private Piso piso;

	/** El objeto matcher. */
	private Matcher matcher;

	/** Vector con las expresiones regulares para validar comandos. */
	private String[] tipoComando;

	/**
	 * Instancia una nueva terminal.
	 * 
	 * @param mosaico
	 *            el objeto mosaico
	 * @param piso
	 *            el objeto piso
	 * @param interfaz
	 *            el objeto interfaz
	 * @param almacenamiento
	 *            el objeto almacenamiento
	 */
	public Terminal(Mosaico mosaico, Piso piso, Interfaz interfaz,
			Almacenamiento almacenamiento) {
		this.almacenamiento = almacenamiento;
		this.mosaico = mosaico;
		this.piso = piso;
		this.interfaz = interfaz;
		tipoComando = new String[] {
				"mos[ ]{1}[1-" + mosaico.getCantidadPatrones()
						+ "]{1}[ ][0-9]{1,3}", "rot[ ]{1}[+]{1}90",
				"rot[ ]{1}[-]{1}90", "rot[ ]{1}[+]{1}180",
				"rot[ ]{1}[-]{1}180",
				"col[ ]{1}[1-" + mosaico.getCantidadColores() + "]{1}",
				"pis[ ]{1}[0-9]{1,3}[ ]{1}[0-9]{1,3}", "gen", "exp" };
	}

	/**
	 * Informa sobre los comandos existentes.
	 * 
	 * @return hilera con la lista.
	 */
	public String getListaComandos() {
		return "1. mos p{1..."
				+ mosaico.getCantidadPatrones()
				+ "} t{1..."
				+ mosaico.getMaxLado()
				+ "} --> Crea un nuevo mosaico \ncon el patron(p) y tamano(t)."
				+ "\n2. rot a{+90,-90,+180,-180} --> Rota el mosaico en \nun angulo(a)."
				+ "\n3. col c{1,"
				+ mosaico.getCantidadColores()
				+ "} --> Cambia el color #(c)"
				+ "\n4. pis n{1..."
				+ String.valueOf(piso.getMaxLado())
				+ "} m{1..."
				+ String.valueOf(piso.getMaxLado())
				+ "} --> Crea un piso de \ntamano (n) x (m)"
				+ "\n5. gen --> Mostrar el piso generado en una \nventana nueva."
				+ "\n6. exp --> Exporta el piso como una imagen PNG.";
	}

	/**
	 * Procesa comandos.
	 * 
	 * @param s
	 *            comando recibido
	 * @return hilera con mensaje de resultado
	 */
	public String procesarComando(String s) {
		for (int i = 0; i < tipoComando.length; i++) 
			if (Pattern.compile(tipoComando[i]).matcher(s).matches()) 
				return ejecutarComando(i, (s.split(" ").length > 1) ? Integer.parseInt(s.split(" ")[1]) : 0, (s.split(" ").length > 2) ? Integer.parseInt(s.split(" ")[2]) : 0);
		return "Comando no reconocido.";
	}

	/**
	 * Ejecuta comandos.
	 * 
	 * @param o
	 *            opcion de comando
	 * @param p1
	 *            parametro 1
	 * @param p2
	 *            parametro 2
	 * @return hilera con resultado
	 */
	private String ejecutarComando(int o, int p1, int p2) {
		switch (o) {
		case 0:
			// cambiar patron y dimension mosaico
			if ((p2 > 0) && (p2 <= mosaico.getMaxLado())) {
				mosaico.setLado(p2);
				mosaico.setPatron(p1);
				mosaico.generarMosaico();
				piso.generarPiso();
				return "Mosaico generado.";
			} else
				return "Comando no reconocido.";
		case 1:
			// girar 90 der
			mosaico.girarMosaico(0);
			piso.generarPiso();
			return "Mosaico girado 90 grados a la derecha.";
		case 2:
			// girar 90 izq
			mosaico.girarMosaico(1);
			piso.generarPiso();
			return "Mosaico girado 90 grados a la izquierda.";
		case 3:
			// girar 180 der
			mosaico.girarMosaico(2);
			piso.generarPiso();
			return "Mosaico girado 180 grados a la derecha.";
		case 4:
			// girar 180 izq
			mosaico.girarMosaico(3);
			piso.generarPiso();
			return "Mosaico girado 180 grados a la izquierda.";
		case 5:
			// cambiar colores
			if (p1 > 0 && p1 <= mosaico.getCantidadColores()) {
				Color color = JColorChooser.showDialog(null, "Seleccione un color", null);
				if(color != null){
					mosaico.setColor(p1, color);
					mosaico.generarMosaico();
					piso.generarPiso();
					return "Color #" + p1 + " cambiado correctamente.";
				}else
					return "Color no seleccionado.";	
			} else
				return "Codigo de color no reconocido.";
		case 6:
			// cambiar dimensiones
			if ((p1 > 0) && (p1 <= piso.getMaxLado()) && (p2 > 0)
					&& (p2 <= piso.getMaxLado())) {
				piso.setTamano(p1, p2);
				piso.generarPiso();
				return "Piso generado.";
			} else
				return "Comando no reconocido.";
		case 7:
			// generar piso
			piso.generarPiso();
			interfaz.mostrarPiso();
			return "Piso generado correctamente.";
		case 8:
			// guardar
			piso.generarPiso();
			return almacenamiento.exportarImagen(interfaz.getSuperficie());
		}
		return "";
	}
}
