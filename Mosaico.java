
/**
 * Este mae tiene que saber hacer un tipo de mosaico y ademas girarlo
 * Tiene que saber las opciones de mosaico
 * 
 * @author Emmanuel Arias Soto
 * @version 0.0
 */
import java.awt.Color;
public class Mosaico{
    private int[][] mosaico;
    private int lado, patron;
    private Color color1, color2;
    private Color[] color = new Color[]{Color.WHITE, Color.BLACK, Color.CYAN, Color.BLUE, Color.RED, Color.ORANGE, Color.PINK, Color.GRAY, Color.MAGENTA};
    private String[] nombreColor = new String[]{"blanco", "negro", "celeste", "azul", "rojo", "naranja", "rosado", "gris", "magenta"};

    public Mosaico(){
        lado = 0;
        patron = 0;
        mosaico = new int[lado][lado];
    }

    public Mosaico(int l, int p, int c1, int c2){
        setLado(l);
        setPatron(p);
        setColor(1, c1);
        setColor(2, c2);
    }

    public void setColor(int c, int o){
        if(c == 1){
            color1 = color[o-1];
        }else{
            color2 = color[o-1];
        }
    }

    public Color getColor(int c){
        if(c == 1){
            return color1;
        }else{
            return color2;
        }
    }

    public String getNombreColor(int c){
        Color col = (c == 1)?color1:color2;
        String s = "";
        for(int i = 0; i < color.length; i++){
            if(col == color[i]){
                s = nombreColor[i];
            }
        }
        return s;
    }

    public String getNombre(int c){
        return nombreColor[c];
    }

    public int getCantidadColores(){
        return color.length;
    }

    public void setPatron(int o){
        patron = o;
        int mitad = (lado - 1) / 2;
        for(int i = 0; i < mosaico.length; i++){
            for(int j = 0; j < mosaico.length; j++){
                switch(o){
                    case 1:
                    //diagonal de izq a der
                    if(i==j){
                        mosaico[i][j] = 1;
                    }else{
                        mosaico[i][j] = 0;
                    }
                    break;
                    case 2:
                    // cuadros sup izq e inf der
                    if( (i <= mitad && j <= mitad) || (i > mitad && j > mitad) ){
                        mosaico[i][j] = 1;
                    }else{
                        mosaico[i][j] = 0;
                    }
                    break;
                    case 3:
                    // uno si uno no
                    if( (j % 2 == 0 && i % 2 == 0) ){
                        mosaico[i][j] = 1;
                    }else{
                        mosaico[i][j] = 0;
                    }
                    break;
                    case 4:
                    if( (i == j && i%2==0 && j%2==0) || i+j == lado-1){
                        mosaico[i][j] = 1;
                    }else{
                        mosaico[i][j] = 0;
                    }
                    break;
                }

            }
        }
    }

    public int getNumPatron(){
        return patron;
    }

    public String getNomPatron(){
        switch(patron){
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

    public void setLado(int l){
        this.lado = l;
        mosaico = new int[lado][lado];
    }

    public int getLado(){
        return lado;
    }

    public int[][] getPatron(){
        return mosaico;
    }

    /* Magia matricial. */
    private int[][] transponerMatriz(int[][] m){
        int[][] temp = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m.length; j++){
                temp[j][i] = m[i][j];
            }
        }
        return temp;
    }

    private int[][] revertirFilasMatriz(int[][] m){
        int[][] temp = new int[m.length][m.length];
        for(int i = 0; i < m.length; i++){
            int c = 0;
            for(int j = m.length-1; j >= 0; j--){
                temp[i][c++] = mosaico[i][j];
            }
        }
        return temp;
    }

    private int[][] revertirColumnasMatriz(int[][] m){
        int[][] temp = new int[m.length][m.length];
        for(int i = 0; i < m.length; i++){
            int c = 0;
            for(int j = m.length-1; j >= 0; j--){
                temp[c++][i] = mosaico[j][i];
            }
        }
        return temp;
    }

    /* Fin de magia matricial. */

    public void girarMosaico(int o){
        switch(o){
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
        }//para 90 derecha 
    }
}
