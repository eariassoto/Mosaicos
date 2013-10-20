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

	/** El objeto piso a serializar. */
	private Piso piso;

	/** Las rutas de los archivos a serializar. */
	private final String rutaSesion = "sesion.ser";

	/**
	 * Determina si hay una sesion guardada.
	 * 
	 * @return true, si la hay
	 */
	public boolean haySesionGuardada() {
		File archivoSesion = new File(rutaSesion);
		return(archivoSesion.exists()) ? true : false;
	}

	/**
	 * Exporta la imagen a un formado png.
	 * 
	 * @param bi
	 *            BufferedImage a exportar
	 * @return hilera con un mensaje para el usuario.
	 */
	public String exportarImagen(BufferedImage bi) {
		try {
			fc = new JFileChooser("$HOME");
			int ruta = fc.showSaveDialog(null);
			if (ruta == JFileChooser.APPROVE_OPTION)
				if (fc.getSelectedFile().getPath() != null) {
					File archivoSalida = new File(fc.getSelectedFile()
							.getPath() + ".png");
					ImageIO.write(bi, "png", archivoSalida);
					return "Imagen guardada exitosamente.";
				} else
					return "Error al guardar la imagen.";
			else
				return "Error al guardar la imagen.";
		} catch (IOException ioe) {
			return "Error al guardar la imagen.";
		}
	}

	/**
	 * Lee un archivo serializado y hace el cast a Piso, en caso algun error
	 * devuelve objetos nuevos.
	 * 
	 * @return el objeto piso
	 */
	public Piso leerSesion() {
		try {
			FileInputStream archivoEntrada = new FileInputStream(rutaSesion);
			ObjectInputStream entrada = new ObjectInputStream(archivoEntrada);
			piso = (Piso) entrada.readObject();
			entrada.close();
			archivoEntrada.close();
		} catch (IOException i) {
			return null;
		} catch (ClassNotFoundException c) {
			return null;
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
	public boolean guardarSesion(Piso p) {
		try {
			FileOutputStream archivoSalida = new FileOutputStream(rutaSesion);
			ObjectOutputStream salida = new ObjectOutputStream(archivoSalida);
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
		File archivoSesion = new File(rutaSesion);
		if (archivoSesion.exists())
			archivoSesion.delete();
	}
}
