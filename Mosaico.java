
/**
 * Este mae tiene que saber hacer un tipo de mosaico y ademas girarlo
 * Tiene que saber las opciones de mosaico
 * 
 * @author Emmanuel Arias Soto
 * @version 0.0
 */
public class Mosaico{

    private String[][] mosaico;
    private int lado;

    public Mosaico(){
        lado = 0;
    }

    /**
     * Considerar hacerlo private y llamarlo desde otro interno
     */
    public void setLado(int l){
        this.lado = l;
    }

    public int getLado(){
        return lado;
    }

    public void crearMosaico(){
        mosaico = new String[lado][lado];
    }

    public void setPatron(int o){
        int mitad = (lado - 1) / 2;
        for(int i = 0; i < mosaico.length; i++){
            for(int j = 0; j < mosaico.length; j++){
                /*Recuerde quitar las cond al final*/
                switch(o){
                    case 1:
                    //diagonal de izq a der
                    if(i==j){
                        mosaico[i][j] = "* ";
                    }else{
                        mosaico[i][j] = "  ";
                    }
                    break;
                    case 2:
                    // cuadros sup izq e inf der
                    if( (i <= mitad && j <= mitad) || (i > mitad && j > mitad) ){
                        mosaico[i][j] = "* ";
                    }else{
                        mosaico[i][j] = "  ";
                    }
                    break;
                    case 3:
                    if( j <= mitad ){
                        mosaico[i][j] = "* ";
                    }else{
                        mosaico[i][j] = "  ";
                    }
                    break;
                    case 4:
                    if(j == 0 || j == mosaico.length-1 || i == 0 || i == mosaico.length-1 ){
                        mosaico[i][j] = "* ";
                    }else{
                        mosaico[i][j] = "  ";
                    }
                    break;
                    case 5:
                    if( (i%2 == 0 && j%2 != 0) || (i%2 != 0 && j%2 == 0) ){
                        mosaico[i][j] = "* ";
                    }else{
                        mosaico[i][j] = "  ";
                    }
                    break;
                }

            }
        }
    }

    public String[][] getPatron(){
        return mosaico;
    }

    /* Magia matricial. */
    private String[][] transponerMatriz(String[][] m){
        String[][] temp = new String[m.length][m.length];
        for (int i = 0; i < m.length; i++){
            for (int j = 0; j < m.length; j++){
                temp[j][i] = m[i][j];
            }
        }
        return temp;
    }

    private String[][] revertirFilasMatriz(String[][] m){
        String[][] temp = new String[m.length][m.length];
        for(int i = 0; i < m.length; i++){
            int c = 0;
            for(int j = m.length-1; j >= 0; j--){
                temp[i][c++] = mosaico[i][j];
            }
        }
        return temp;
    }

    private String[][] revertirColumnasMatriz(String[][] m){
        String[][] temp = new String[m.length][m.length];
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
