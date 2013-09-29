
/**
 * Write a description of class Piso here.
 * 
 * @author Emmanuel Arias
 * @version 0.1
 */
import java.awt.Color;
public class Piso{
    private Mosaico mosaico;
    private int[][] piso;
    private int n, m;
    public Piso(Mosaico m){
        this.mosaico = m;
        n = 0;
        this.m = 0;
    }

    public void setDimensiones(int n, int m){
        this.n = n;
        this.m = m;
    }

    public int getN(){
        return n;
    }

    public int getM(){
        return m;
    }

    public void generarPiso(){
        piso = new int[n][m];
        int[][] patron = mosaico.getPatron();
        int ladoMos = mosaico.getLado();
        for(int i = 0; i < piso.length; i++){
            for(int j = 0; j < piso[0].length; j++){
                piso[i][j] = patron[i % ladoMos][j % ladoMos];
            }
        }
    }

    public int[][] getPiso(){
        return piso;
    }

    public Color getColor(int c){
        return mosaico.getColor(c);
    }

    public void imprimir(){
        for(int i = 0; i < piso.length; i++){
            for(int j = 0; j < piso[0].length; j++){
                System.out.print(piso[i][j]);
            }
            System.out.print("\n");
        }
    }
}
