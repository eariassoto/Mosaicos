/*
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.Color;
import java.io.Serializable;

/**
 * The Class Piso.
 */
public class Piso implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** Constante para indcar el mayor tamano permitido para el piso. */
    private final int MAXIMO_LADO = 499;

    /** El objeto de donde se obtenda la informacion del mosaico. */
    private Mosaico mosaico;

    /** La matriz usada para generar el piso de mosaicos. */
    private Color[][] piso;

    /** Los tamanos n y m de la matriz. */
    private int n, m;

    /**
     * Instancia un nuevo piso.
     *
     * @param mosaico the mosaico
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
     * Devuelve la informacion del mosaico.
     * 
     * @return objeto mosaico.
     */
    public Mosaico getMosaico(){
        return mosaico;
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
        piso = new Color[n][m];
        Color[][] patron = mosaico.getMatrizMosaico();
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
    public Color[][] getPiso() {
        return piso;
    }

	/**
	 * Indica que tan grande puede ser el piso.
	 *
	 * @return maximo de lado
	 */
	public int getMaxLado() {
		return MAXIMO_LADO;
	}
}
