
/**
 * Write a description of class Comando here.
 * 
 * @author Emmanuel Arias Soto 
 * @version 0.1
 */
/**
 * Poner aqui mae listas con los comandos para que 
 * Interprete los recupere.
 */
public class Comando{
    private int[] comando;
    private String[] tipoComando;
    public Comando(){
        comando = new int[3];
       tipoComando = new String[]{"col","mos","pis"};
    }

    public void setComando(int c, int p1, int p2){
        comando[0] = c;
        comando[1] = p1;
        comando[2] = p2;
    }

    public int[] getComando(){
        return comando;
    }

    public String[] getTipos(){
        return tipoComando;
    }
}
