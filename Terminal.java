
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

    private Escribir escribir;
    private Mosaico mosaico;
    private Interfaz interfaz;
    private Piso piso;
    private Pattern patron;
    private Matcher matcher;
    private String[] tipoComando;
    private String listaComandos;
    private int p1, p2;

    public Terminal(Mosaico mosaico, Piso piso, Interfaz interfaz, Escribir escribir){
        this.escribir = escribir;
        this.mosaico = mosaico;
        this.piso = piso;
        this.interfaz = interfaz;
        tipoComando = new String[]{"mos[ ]{1}[1-4]{1}[ ]{1}[1-9]{1}[0-9]{0,1}",
            "rot[ ]{1}[+]{1}90",
            "rot[ ]{1}[-]{1}90",
            "rot[ ]{1}[+]{1}180",
            "rot[ ]{1}[-]{1}180",
            "col[ ]{1}[1-2]{1}[ ]{1}[1-5]{1}",
            "pis[ ]{1}[1-9]{1}[0-9]{0,2}[ ]{1}[1-9]{1}[0-9]{0,2}",
            "gen",
            "exp"};
        p1 = 0;
        p2 = 0;
        setComandos();
    }

    private void setComandos(){
        listaComandos = "mos p l --> mosaico patron[1-4], lado[1-99]" +
        "\nrot a --> rotar angulo[+90,-90,+180,-180]" +
        "\ncol # c --> establecer color #[1,2] color[1-5]"+
        "\npis n m --> piso n[1-499] x m[1-499]"+
        "\ngen --> generar piso"+
        "\nexp --> exportar imagen como png";
    }

    public String getListaComandos(){
        return listaComandos;
    }

    public String procesarComando(String s){
        for(int i = 0; i < tipoComando.length; i++){
            patron = Pattern.compile(tipoComando[i]);
            matcher = patron.matcher(s);
            if(matcher.matches()){
                String[] param = s.split(" ");
                p1 = (param.length > 1)?Integer.parseInt(param[1]):0;
                p2 = (param.length > 2)?Integer.parseInt(param[2]):0;
                return  ejecutarComando(i, p1, p2);
            }
        }
        return "Comando no reconocido.";
    }

    private String ejecutarComando(int o, int p1, int p2){
        switch(o){
            case 0:
            // cambiar patron y dimension mosaico
            mosaico.setLado(p2);
            mosaico.setPatron(p1);
            piso.generarPiso();
            return "Mosaico generado.";
            case 1:
            // girar 90 der
            mosaico.girarMosaico(0);
            piso.generarPiso();
            return "Mosaico girado 90° a la derecha.";
            case 2:
            // girar 90 izq
            mosaico.girarMosaico(1);
            piso.generarPiso();
            return "Mosaico girado 90° a la izquierda.";
            case 3:
            // girar 180 der
            mosaico.girarMosaico(2);
            piso.generarPiso();
            return "Mosaico girado 180° a la derecha.";
            case 4:
            // girar 180 izq
            mosaico.girarMosaico(3);
            piso.generarPiso();
            return "Mosaico girado 180° a la izquierda.";
            case 5:
            // cambiar colores
            mosaico.setColor(p1, p2);
            return "Color #"+p1+" cambiado correctamente.";
            case 6:
            // cambiar dimensiones 
            piso.setDimensiones(p1, p2);
            piso.generarPiso();
            return "Piso generado.";
            case 7:
            // generar piso
            interfaz.mostrarPiso();
            return "Piso generado correctamente.";
            case 8:
            //guardar
            piso.generarPiso();
            return escribir.guardarImagen(interfaz.getSuperficie());
        }
        return "";
    }
}
