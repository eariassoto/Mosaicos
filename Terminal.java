/*
 * La clase Terminal procesa los comandos que recibe y ejecuta las ordenes
 * establecidas.
 * 
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class Terminal{

    /** El objeto almacenamiento. */
    private Almacenamiento almacenamiento;
    
    /** El objeto mosaico. */
    private Mosaico mosaico;
    
    /** El objeto interfaz. */
    private Interfaz interfaz;
    
    /** El objeto piso. */
    private Piso piso;
    
    /** El objeto patron. */
    private Pattern patron;
    
    /** El objeto matcher. */
    private Matcher matcher;
    
    /** Vector con las expresiones regulares para validar comandos. */
    private String[] tipoComando;
    
    /** Hilera para informar de la lista de comandos. */
    private String listaComandos;
    
    /** Usados para enviar parametros al separar el comando. */
    private int p1, p2;

    /**
     * Instancia una nueva terminal.
     *
     * @param mosaico el objeto mosaico
     * @param piso el objeto piso
     * @param interfaz el objeto interfaz
     * @param almacenamiento el objeto almacenamiento
     */
    public Terminal(Mosaico mosaico, Piso piso, Interfaz interfaz, Almacenamiento almacenamiento){
        this.almacenamiento = almacenamiento;
        this.mosaico = mosaico;
        this.piso = piso;
        this.interfaz = interfaz;
        tipoComando = new String[]{"mos[ ]{1}[1-4]{1}[ ]{1}[1-9]{1}[0-9]{0,1}",
            "rot[ ]{1}[+]{1}90",
            "rot[ ]{1}[-]{1}90",
            "rot[ ]{1}[+]{1}180",
            "rot[ ]{1}[-]{1}180",
            "col[ ]{1}[1-2]{1}[ ]{1}[0-9]{1,3}",
            "pis[ ]{1}[1-9]{1}[0-9]{0,2}[ ]{1}[1-9]{1}[0-9]{0,2}",
            "gen",
            "exp"};
        p1 = 0;
        p2 = 0;
        setComandos();
    }
    
    /**
     * Crea la lista con los comandos.
     */
    private void setComandos(){
        listaComandos = "1. mos p t --> Crea un nuevo mosaico con el \npatron[1-4] y con un tamano[1-99]." +
        "\n2. rot a --> Rota el mosaico en un \nangulo[+90,-90,+180,-180]." +
        "\n3. col # c --> Establece el color #[1,2] con un \ncolor[1-"+mosaico.getCantidadColores()+"] de la lista de la izquierda."+
        "\n4. pis n m --> Crea un piso n[1-499] x m[1-499]"+
        "\n5. gen --> Mostrar el piso generado en una \nventana nueva."+
        "\n6. exp --> Exporta el piso como una imagen PNG.";
    }

    /**
     * Informa sobre los comandos existentes.
     *
     * @return hilera con la lista.
     */
    public String getListaComandos(){
        return listaComandos;
    }

    /**
     * Procesa comandos.
     *
     * @param s comando recibido
     * @return hilera con mensaje de resultado
     */
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

    /**
     * Ejecuta comandos.
     *
     * @param o opcion de comando
     * @param p1 parametro 1
     * @param p2 parametro 2 
     * @return hilera con resultado
     */
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
            if(p2 > 0 && p2 <= mosaico.getCantidadColores()){
                mosaico.setColor(p1, p2);
                return "Color #"+p1+" cambiado correctamente.";
            }else
                return "Codigo de color no reconocido.";
            case 6:
            // cambiar dimensiones 
            piso.setTamano(p1, p2);
            piso.generarPiso();
            return "Piso generado.";
            case 7:
            // generar piso
            piso.generarPiso();
            interfaz.mostrarPiso();
            return "Piso generado correctamente.";
            case 8:
            //guardar
            piso.generarPiso();
            return almacenamiento.exportarImagen(interfaz.getSuperficie());
        }
        return "";
    }
}
