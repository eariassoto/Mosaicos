/*
 * La clase Piso contiene la representacion logica de la matriz de mosaicos
 * 
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.Color;
import java.io.Serializable;

public class Piso implements Serializable {

	private static final long serialVersionUID = 1L;

	/** El objeto de donde se obtenda la informacion del mosaico. */
	private Mosaico mosaico;

	/** La matriz usada para generar el piso de mosaicos. */
	private int[][] piso;

	/** Los tamanos n y m de la matriz. */
	private int n, m;

	/**
	 * Instancia un nuevo piso.
	 * 
	 * @param m
	 *            objeto mosaico que el usuario esta o ha definido.
	 */
	public Piso(Mosaico mosaico) {
		this.mosaico = mosaico;
		this.n = 0;
		this.m = 0;
	}

	/**
	 * Establece el tamano de la matriz.
	 * 
	 * @param n
	 *            tamano de las filas.
	 * @param m
	 *            tamano de las columnas.
	 */
	public void setTamano(int n, int m) {
		this.n = n;
		this.m = m;
	}

	/**
	 * Devuelve la cantidad de filas de la matriz.
	 * 
	 * @return cantidad filas
	 */
	public int getN() {
		return n;
	}

	/**
	 * Devuelve la cantidad de columnas de la matriz.
	 * 
	 * @return cantidad columnas
	 */
	public int getM() {
		return m;
	}

	/**
	 * Genera una matriz nxm y la rellena con la matriz obtenida de mosaico.
	 */
	public void generarPiso() {
		piso = new int[n][m];
		int[][] patron = mosaico.getMatrizMosaico();
		int ladoMos = mosaico.getLado();
		for (int i = 0; i < piso.length; i++)
			for (int j = 0; j < piso[0].length; j++)
				piso[i][j] = patron[i % ladoMos][j % ladoMos];
	}

	/**
	 * Devuelve la matriz con la representacion del piso.
	 * 
	 * @return the piso
	 */
	public int[][] getPiso() {
		return piso;
	}

	/**
	 * Devuelve un color del mosaico.
	 * 
	 * @param c
	 *            color deseado
	 * @return Color correspondiente
	 */
	public Color getColor(int c) {
		return mosaico.getColor(c);
	}
}
