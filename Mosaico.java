
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
    public Mosaico(){
        lado = 0;
        patron = 0;
        mosaico = new int[lado][lado];
    }

    public void setColor(int c, int o){
        if(c == 1){
            color1 = elegirColor(o);
        }else{
            color2 = elegirColor(o);
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
        if(c == 1){
            return nombreColor(color1);
        }else{
            return nombreColor(color2);
        }
    }

    private Color elegirColor(int o){
        switch(o){
            case 1:
            return Color.WHITE;
            case 2:
            return Color.BLACK;
            case 3:
            return Color.BLUE;
            case 4:
            return Color.RED;
            case 5:
            return 	Color.ORANGE;
        }
        return Color.WHITE;
    }

    private String nombreColor(Color c){
        if(c == Color.WHITE){
            return "blanco";
        }else{
            if(c == Color.BLACK){
                return "negro";
            }else{
                if(c == Color.BLUE){
                    return "azul";
                }else{
                    if(c == Color.RED){
                        return "rojo";
                    }else{
                        if(c == Color.ORANGE){
                            return "naranja";
                        }else{
                            return "";
                        }
                    }
                }
            }
        }
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

    public void setLado(int l){
        this.lado = l;
        mosaico = new int[lado][lado];
        setPatron(patron);
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

    /**
     * Codigo algo cerdo, tratar de hacer mas bonito
     */
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

    public void imprimir(){
        for(int i = 0; i < mosaico.length; i++){
            for(int j = 0; j < mosaico.length; j++){
                System.out.print(mosaico[i][j]);
            }
            System.out.print("\n");
        }
    }
}
