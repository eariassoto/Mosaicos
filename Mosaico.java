/*
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.Color;
import java.io.Serializable;

/**
 * The Class Mosaico.
 */
public class Mosaico implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The cantidadpatrones. */
    private final int MAXIMO_LADO = 99, CANTIDADPATRONES = 4;

    /** Variables para manejar el tamano del mosaico y el tipo de patron. */
    private int lado, patron;

    /**
     * Los colores del mosaico (se podria implementar un vector de Color para
     * manejar mas colores).
     */
    private Color[] color;

    /**
     * La matriz del mosaico, una vez que se crea guarda los colores
     * respectivos.
     */
    private Color[][] mosaico;

    /**
     * Instancia un nuevo mosaico "en blanco".
     */
    public Mosaico() {
        lado = 0;
        patron = 0;
        mosaico = new Color[lado][lado];
        inicializarColores();
        generarMosaico();
    }

    /**
     * Instancia un nuevo mosaico con los parametros indicados.
     *
     * @param l tamano de la matriz
     * @param p patron deseado
     */
    public Mosaico(int l, int p, Color c1, Color c2) {
        setLado(l);
        inicializarColores(c1, c2);
        setPatron(p);
        generarMosaico();
    }

    /**
     * Inicializa los colores, pone gris y negro por default.
     */
    private void inicializarColores() {
        color = new Color[2];
        color[0] = new Color(51, 102, 255);
        color[1] = new Color(0, 0, 204);
    }

    /**
     * Inicializa los colores determinados por los parametros.
     * @param c1 color #1
     * @param c2 color #2
     */
    private void inicializarColores(Color c1, Color c2) {
        color = new Color[2];
        color[0] = c1;
        color[1] = c2;
    }

    /**
     * Devuelve el codigo del color.
     *
     * @param c color
     * @return codigo
     */
    public String getCodigoColor(int c){

        return "["+String.valueOf(color[c-1].getRed())+", "+String.valueOf(color[c-1].getGreen())+", "+String.valueOf(color[c-1].getBlue())+"]";
    }

    /**
     * @return cantidad de colores
     * 
     */
    public int getCantidadColores(){
        return color.length;
    }

    /**
     * Establece un color. El -1 es para que concuerde con el indice del vector.
     *
     * @param c color deseado
     * @param opcion the opcion
     */
    public void setColor(int c, Color opcion) {
        color[c - 1] = opcion;
    }

    /**
     * Sets the patron.
     *
     * @param o the new patron
     */
    public void setPatron(int o){
        patron = o;
    }

    /**
     * Crea un patron en la matriz.
     *
     */
    public void generarMosaico() {
        int mitad = (lado - 1) / 2;
        switch (patron) {
            case 1:
            // diagonal de izq a der
            for (int i = 0; i < mosaico.length; i++)
                for (int j = 0; j < mosaico.length; j++)
                    mosaico[i][j] = (i == j) ? color[0] : color[1];
            break;
            case 2:
            // cuadros sup izq e inf der
            for (int i = 0; i < mosaico.length; i++)
                for (int j = 0; j < mosaico.length; j++)
                    mosaico[i][j] = ((i <= mitad && j <= mitad) || (i > mitad && j > mitad)) ? color[0]
                    : color[1];
            break;
            case 3:
            // uno si uno no, triang superior
            for (int i = 0; i < mosaico.length; i++)
                for (int j = 0; j < mosaico.length; j++)
                    mosaico[i][j] = ((i <= j && j % 2 == 0 && i % 2 == 0)) ? color[0]
                    : color[1];
            break;
            case 4:
            //diagonales
            for (int i = 0; i < mosaico.length; i++)
                for (int j = 0; j < mosaico.length; j++)
                    mosaico[i][j] = ((i == j && i % 2 == 0 && j % 2 == 0) || i
                        + j == lado - 1) ? color[0] : color[1];
            break;
        }
    }

    /**
     * Indica cual patron tiene el mosaico actual.
     * 
     * @return numero de patron
     */
    public int getNumPatron() {
        return patron;
    }

    /**
     * Indica el nombre del patron que tiene el mosaico.
     * 
     * @return hilera con el nombre.
     */
    public String getNomPatron() {
        switch (patron) {
            case 1:
            return "Diagonal";
            case 2:
            return "Cuadros";
            case 3:
            return "Cuadricula";
            case 4:
            return "Cruz";
        }
        return "";
    }

    /**
     * Establece el tamano de la matriz y la inicializa.
     * 
     * @param l
     *            el tamano del mosaico.
     */
    public void setLado(int l) {
        this.lado = l;
        mosaico = new Color[lado][lado];
    }

    /**
     * Devuelve el tamano del mosaico.
     * 
     * @return tamano de matriz.
     */
    public int getLado() {
        return lado;
    }

    /**
     * Devuelve la matriz con el mosaico.
     * 
     * @return the patron
     */
    public Color[][] getMatrizMosaico() {
        return mosaico;
    }

    /* Magia matricial. */
    /**
     * Transpone una matriz.
     * 
     * @param m
     *            matriz
     * @return matriz transpuesta
     */
    private Color[][] transponerMatriz(Color[][] m) {
        Color[][] temp = new Color[m.length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m.length; j++)
                temp[j][i] = m[i][j];
        return temp;
    }

    /**
     * Revierte las filas de una matriz.
     * 
     * @param m
     *            matriz
     * @return nueva matriz con filas revertidas
     */
    private Color[][] revertirFilasMatriz(Color[][] m) {
        Color[][] temp = new Color[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            int c = 0;
            for (int j = m.length - 1; j >= 0; j--)
                temp[i][c++] = mosaico[i][j];
        }
        return temp;
    }

    /**
     * Revierte las columnas de una matriz.
     * 
     * @param m
     *            matriz
     * @return nueva matriz con columnas revertidas
     */
    private Color[][] revertirColumnasMatriz(Color[][] m) {
        Color[][] temp = new Color[m.length][m.length];
        for (int i = 0; i < m.length; i++) {
            int c = 0;
            for (int j = m.length - 1; j >= 0; j--)
                temp[c++][i] = mosaico[j][i];
        }
        return temp;
    }

    /* Fin de magia matricial. */

    /**
     * Gira la matriz con el mosaico.
     *
     * @param o the o
     */
    public void girarMosaico(int o) {
        switch (o) {
            case 0:
            // girar 90 a la derecha
            mosaico = transponerMatriz(mosaico);
            mosaico = revertirFilasMatriz(mosaico);
            break;
            case 1:
            // girar 90 a la izquierda
            mosaico = transponerMatriz(mosaico);
            mosaico = revertirColumnasMatriz(mosaico);
            break;
            case 2:
            // girar 180 a la derecha
            mosaico = revertirFilasMatriz(mosaico);
            mosaico = revertirColumnasMatriz(mosaico);
            break;
            case 3:
            // girar 180 a la izquierda
            mosaico = revertirColumnasMatriz(mosaico);
            mosaico = revertirFilasMatriz(mosaico);
            break;
        }
    }

    /**
     * Indica que tan grande puede ser el mosaico.
     *
     * @return maximo de lado
     */
    public int getMaxLado() {
        return MAXIMO_LADO;
    }

    /**
     * Devuelv la cantidad de patrones disponibles.
     *
     * @return cantidad de patrones
     */
    public int getCantidadPatrones() {
        return CANTIDADPATRONES;
    }
}
