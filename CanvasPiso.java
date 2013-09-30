import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class CanvasPiso{
    private int largo, ancho, lado;
    private int[][] p;
    private JLabel view;
    private BufferedImage superficie;
    private Piso piso;

    public CanvasPiso(Piso piso, int largo, int ancho){
        this.piso = piso;
        p = piso.getPiso();
        this.largo = largo;
        this.ancho = ancho;
        medidas();

        superficie = new BufferedImage(this.largo, this.ancho, BufferedImage.TYPE_INT_RGB);
        view = new JLabel(new ImageIcon(superficie));

        Graphics g = superficie.getGraphics();
        g.setColor(piso.getColor(1));
        g.fillRect(0,0,largo,ancho);

        int coordX = 0, coordY = 0;
        for(int i = 0; i < p.length; i++){
            coordX = 0;
            for(int j = 0; j < p[0].length; j++){
                if(p[i][j] == 1){
                    g.setColor( piso.getColor(1) );
                    g.fillRect( coordX, coordY, lado, lado );
                }else{
                    g.setColor( piso.getColor(2) );
                    g.fillRect( coordX, coordY, lado, lado );
                }
                coordX += lado;
            }
            coordY += lado;
        }
        g.dispose();
    }

    public void medidas(){
        lado = (ancho / p.length < largo / p[0].length)?(int)(ancho / p.length):(int)(largo / p[0].length);          
        largo = p[0].length * lado;
        ancho = p.length * lado;
    }

    public JLabel getView(){
        return view;
    }

    public BufferedImage getSuperficie(){
        return superficie;
    }

    public int getLargo(){
        return largo;
    }   

    public int getAncho(){
        return ancho;
    }
}