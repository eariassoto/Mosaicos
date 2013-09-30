
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Terminal{

    private Mosaico mosaico;
    private Interfaz interfaz;
    private Piso piso;
    private Pattern patron;
    private Matcher matcher;
    private String[] tipoComando;
    private String listaComandos;
    private int p1, p2;

    public Terminal(Mosaico mosaico, Piso piso, Interfaz interfaz){
        this.mosaico = mosaico;
        this.piso = piso;
        this.interfaz = interfaz;
        tipoComando = new String[]{"mos[ ]{1}[1-4]{1}[ ]{1}[1-9]{1}[0-9]{0,1}",
            "rot[ ]{1}[+]{1}90",
            "rot[ ]{1}[-]{1}90",
            "rot[ ]{1}[+]{1}180",
            "rot[ ]{1}[-]{1}180",
            "col[ ]{1}[1-2]{1}[ ]{1}[1-5]{1}",
            "gen[ ]{1}[1-9]{1}[0-9]{0,2}[ ]{1}[1-9]{1}[0-9]{0,2}"};
        p1 = 0;
        p2 = 0;
        setComandos();
    }

    private void setComandos(){
        listaComandos = "mos p l --> mosaico patron[1-4], lado[1-99]" +
        "\nrot a --> rotar angulo[+90,-90,+180,-180]" +
        "\ncol # c --> establecer color #[1,2] color[1-5]"+
        "\ngen n m --> generar piso n[1-999] x m[1-999]";
    }

    public String getListaComandos(){
        return listaComandos;
    }

    public boolean procesarComando(String s){
        for(int i = 0; i < tipoComando.length; i++){
            patron = Pattern.compile(tipoComando[i]);
            matcher = patron.matcher(s);
            if(matcher.matches()){
                String[] param = s.split(" ");
                p1 = Integer.parseInt(param[1]);
                if(param.length > 2){
                    p2 = Integer.parseInt(param[2]);
                }
                ejecutarComando(i, p1, p2);
                return true;
            }
        }
        return false;
    }

    private void ejecutarComando(int o, int p1, int p2){
        switch(o){
            case 0:
            // cambiar patron y dimension mosaico
            mosaico.setLado(p2);
            mosaico.setPatron(p1);
            piso.generarPiso();
            break;
            case 1:
            // girar 90 der
            mosaico.girarMosaico(0);
            piso.generarPiso();
            break;
            case 2:
            // girar 90 izq
            mosaico.girarMosaico(1);
            piso.generarPiso();
            break;
            case 3:
            // girar 180 der
            mosaico.girarMosaico(2);
            piso.generarPiso();
            break;
            case 4:
            // girar 180 izq
            mosaico.girarMosaico(3);
            piso.generarPiso();
            break;
            case 5:
            // cambiar colores
            mosaico.setColor(p1, p2);
            break;
            case 6:
            // cambiar dimensiones y generar piso
            piso.setDimensiones(p1, p2);
            piso.generarPiso();
            interfaz.mostrarPiso();
            break;
        }

    }
}
