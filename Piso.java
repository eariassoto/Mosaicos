
/**
 * Write a description of class Piso here.
 * 
 * @author Emmanuel Arias
 * @version 0.1
 */
public class Piso{
    private Mosaico mosaico;
    private String[][] piso;
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

    public void crearPiso(){
        piso = new String[n][m];
    }

    public void generarPiso(){
        String[][] patron = mosaico.getPatron();
        int ladoMos = mosaico.getLado();
        for(int i = 0; i < piso.length; i++){
            for(int j = 0; j < piso[0].length; j++){
                piso[i][j] = patron[i % ladoMos][j % ladoMos];
            }
        }
    }

    public void imprimir(){
        for(int i = 0; i < piso.length; i++){
            for(int j = 0; j < piso[0].length; j++){
                System.out.print(piso[i][j]);
            }
            System.out.print("\n");
        }
    }

    public static void main(String emma[]){
        Mosaico m = new Mosaico();
        m.setLado(4);
        m.crearMosaico();
        m.setPatron(2);
        Piso p = new Piso(m);
        p.setDimensiones(20,10);
        p.crearPiso();
        p.generarPiso();
        p.imprimir();
    }
}
