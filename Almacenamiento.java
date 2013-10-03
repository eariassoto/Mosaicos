/*
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

public class Almacenamiento {

	/** Ayuda al usuario a elegir una ruta para almacenar la imagen. */
	private JFileChooser fc;

	/** El objeto mosaico a serializar. */
	private Mosaico mosaico;

	/** El objeto piso a serializar. */
	private Piso piso;

	/** Las rutas de los archivos a serializar. */
	private final String rutaMosaico = "archivo.ser", rutaPiso = "piso.ser";

	/**
	 * Determina si hay una sesion guardada.
	 * 
	 * @return true, si la hay
	 */
	public boolean haySesionGuardada() {
		File archivoPiso = new File(rutaPiso);
		File archivoMosaico = new File(rutaMosaico);
		// evalua con un "and" para evitar errores.
		return (archivoPiso.exists() && archivoMosaico.exists()) ? true : false;
	}

	/**
	 * Exporta la imagen a un formado png.
	 * 
	 * @param bi
	 *            BufferedImage a exportar
	 * @return hilera con un mensaje para el usuario.
	 */
	public String exportarImagen(BufferedImage bi) {
		String s = "";
		try {
			fc = new JFileChooser("$HOME");
			int ruta = fc.showSaveDialog(null);
			if (ruta == JFileChooser.APPROVE_OPTION)
				if (fc.getSelectedFile().getPath() != null) {
					File archivoSalida = new File(fc.getSelectedFile()
							.getPath() + ".png");
					ImageIO.write(bi, "png", archivoSalida);
					s = "Imagen guardada exitosamente.";
				} else
					s = "Error al guardar la imagen.";
		} catch (IOException ioe) {
			s = "Error al guardar la imagen.";
			return s;
		}
		return (s=="")?"Error al guardar la imagen.":s;
	}

	/**
	 * Lee un archivo serializado y hace el cast a Mosaico, en caso algun error
	 * devuelve objetos nuevos.
	 * 
	 * @return el objeto mosaico
	 */
	public Mosaico leerMosaico() {
		try {
			FileInputStream archivoEntrada = new FileInputStream(rutaMosaico);
			ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
			mosaico = (Mosaico) entrada.readObject();
			entrada.close();
			archivoEntrada.close();
		} catch (IOException i) {
			mosaico = new Mosaico();
			return mosaico;
		} catch (ClassNotFoundException c) {
			mosaico = new Mosaico();
			return mosaico;
		}
		return mosaico;
	}

	/**
	 * Lee un archivo serializado y hace el cast a Piso, en caso algun error
	 * devuelve objetos nuevos.
	 * 
	 * @return el objeto piso
	 */
	public Piso leerPiso() {
		try {
			FileInputStream archivoEntrada = new FileInputStream(rutaPiso);
			ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
			piso = (Piso) entrada.readObject();
			entrada.close();
			archivoEntrada.close();
		} catch (IOException i) {
			return new Piso(mosaico);
		} catch (ClassNotFoundException c) {
			return new Piso(mosaico);
		}
		return piso;
	}

	/**
	 * Guardar los objetos en archivos serializados.
	 * 
	 * @param m
	 *            el objeto mosaico
	 * @param p
	 *            el objeto piso
	 * @return true, si se logro
	 */
	public boolean guardar(Mosaico m, Piso p) {
		try {
			FileOutputStream archivoSalida = new FileOutputStream(rutaMosaico);
			ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
			salida.writeObject(m);
			salida.close();
			archivoSalida.close();

			archivoSalida = new FileOutputStream(rutaPiso);
			salida = new ObjectOutputStream(archivoSalida);
			salida.writeObject(p);
			salida.close();
			archivoSalida.close();
			return true;
		} catch (IOException i) {
			return false;
		}
	}

	/**
	 * Borra la sesion por decision del usuario.
	 * 
	 */
	public void borrarSesion() {
		File archivoPiso = new File(rutaPiso);
		File archivoMosaico = new File(rutaMosaico);
		if (archivoPiso.exists())
			archivoPiso.delete();
		if (archivoMosaico.exists())
			archivoMosaico.delete();
	}
}
