/*
 * La clase Mosaico contiene la representacion logica del mosaico
 * ademas de los tipos de patrones y colores disponibles.
 * 
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.Color;
import java.io.Serializable;

public class Mosaico implements Serializable {

    private static final long serialVersionUID = 1L;

    /** La matriz de enteros mosaico, donde se guardar el mosaico actual. */
    private int[][] mosaico;

    /** Variables para manejar el tamano del mosaico y el tipo de patron. */
    private int lado, patron;

    /**
     * Los colores del mosaico (se podria implementar un vector de Color para
     * manejar mas colores).
     */
    private Color color1, color2;

    /**
     * El vector con los colores que se pueden hacer (se pueden agregar mas sin
     * modificar mas codigo, excepto el tamano del JFrame de VentanaPrincipal y
     * su respectivo nombre.
     */
    private Color[] color;

    private int[][] codRGB = new int[][]{
            {255, 255, 255}, //blanco
            {  0,   0,   0}, //negro
            { 50, 149, 255}, //celeste
            { 33,  66, 255}, //azul
            {  0, 173,  17}, //verde osc
            {255,  52,  45}, //rojo
            {  0, 211,  24}, //verde clar
            {255, 103,  28}, //naranja
            {242, 234,  16}, //amarillo
            {255, 135, 247}, //rosado
            {128, 128, 128}, //gris
            {178,   0, 255}, //magenta
        };

    /** Los nombres de los colores. */
    private String[] nombreColor = new String[] { "blanco", "negro", "celeste",
            "azul", "verde oscuro", "rojo", "verde claro", "naranja", "amarillo", "rosado", "gris", "magenta" };

    /**
     * Instancia un nuevo mosaico "en blanco".
     */
    public Mosaico() {
        lado = 0;
        patron = 0;
        mosaico = new int[lado][lado];
        inicializarColores();
    }

    /**
     * Instancia un nuevo mosaico con los parametros indicados.
     * 
     * @param l
     *            tamano de la matriz
     * @param p
     *            patron deseado
     * @param c1
     *            color 1
     * @param c2
     *            color 2
     */
    public Mosaico(int l, int p, int c1, int c2) {
        inicializarColores();
        setLado(l);
        setPatron(p);
        setColor(1, c1);
        setColor(2, c2);
    }

    private void inicializarColores(){
        color = new Color[codRGB.length];
        for(int i = 0; i < codRGB.length; i++)
            color[i] = new Color(codRGB[i][0], codRGB[i][1], codRGB[i][2]);
    }

    /**
     * Establece un color. El -1 es para que concuerde con el indice del vector.
     * 
     * @param c
     *            color deseado
     * @param o
     *            opcion de color
     */
    public void setColor(int c, int opcion) {
        if (c == 1)
            color1 = color[opcion - 1];
        else
            color2 = color[opcion - 1];
    }

    /**
     * Devuelve un color.
     * 
     * @param c
     *            numero de color deseado
     * @return Color correspondiente
     */
    public Color getColor(int c) {
        return (c == 1) ? color1 : color2;
    }

    /**
     * Devuelve el nombre ya sea del color 1 o del 2 en especifico.
     * 
     * @param c
     *            numero de color deseado
     * @return hilera con el nombre
     */
    public String getNombreColor(int c) {
        Color col = (c == 1) ? color1 : color2;
        String s = "";
        for (int i = 0; i < color.length; i++)
            if (col == color[i])
                s = nombreColor[i];
        return s;
    }

    /**
     * Devuelve el nombre de cualquier color del vector de colores.
     * 
     * @param c
     *            indice del color
     * @return nombre respectivo
     */
    public String getNombre(int c) {
        return nombreColor[c];
    }

    /**
     * Indica cuantos colores disponibles hay.
     * 
     * @return cantidad de colores
     */
    public int getCantidadColores() {
        return color.length;
    }

    /**
     * Crea un patron en la matriz.
     * 
     * @param o
     *            opcion de patron deseada
     */
    public void setPatron(int o) {
        patron = o;
        int mitad = (lado - 1) / 2;
        for (int i = 0; i < mosaico.length; i++)
            for (int j = 0; j < mosaico.length; j++)
                switch (o) {
                    case 1:
                    // diagonal de izq a der
                    mosaico[i][j] = (i == j) ? 1 : 2;
                    break;
                    case 2:
                    // cuadros sup izq e inf der
                    mosaico[i][j] = ((i <= mitad && j <= mitad) || (i > mitad && j > mitad)) ? 1
                    : 2;
                    break;
                    case 3:
                    // uno si uno no
                    mosaico[i][j] = ((j % 2 == 0 && i % 2 == 0)) ? 1 : 2;
                    break;
                    case 4:
                    mosaico[i][j] = ((i == j && i % 2 == 0 && j % 2 == 0) || i
                        + j == lado - 1) ? 1 : 2;
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
        mosaico = new int[lado][lado];
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
    public int[][] getMatrizMosaico() {
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
    private int[][] transponerMatriz(int[][] m) {
        int[][] temp = new int[m.length][m.length];
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
    private int[][] revertirFilasMatriz(int[][] m) {
        int[][] temp = new int[m.length][m.length];
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
    private int[][] revertirColumnasMatriz(int[][] m) {
        int[][] temp = new int[m.length][m.length];
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
     * @param opcion de grados a girar
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
}
