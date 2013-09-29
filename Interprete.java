
/**
 * Write a description of class Interprete here.
 * 
 * @author Emmanuel Arias Soto
 * @version (a version number or a date)
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class Interprete{
    private Pattern patron;
    private Matcher matcher;
    private Comando comando;

    public Interprete(Comando c){
        this.comando = c;
        patron = Pattern.compile("[a-z]{3}[(]{1}[0-9]{1}[,]{1}[0-9]{1}[,]{1}[0-9]{1}[)]{1}");
    }

    public boolean procesarComando(String c){
        String com = c.replaceAll("\\s+","");
        matcher = patron.matcher(com);
        if(matcher.matches()){
             System.out.println(com);
            String id = "col";
            String[] tipoComando = comando.getTipos();
            for(int i = 0; i < 3; i++){
                //id+=com.charAt(i);
            }
            
            for(int i = 0; i < tipoComando.length; i++){
                if(id == tipoComando[i]){ 
                    System.out.println("ola");//comando.substring(4,5));
                }
            }
            return true;
        }else{
            return false;
        }
    }

    public static void main(String emma[]){
        Comando c = new Comando();
        Interprete i = new Interprete(c);
        System.out.println(i.procesarComando("col(3, 4, 4)"));
    }
}
