
/**
 * Write a description of class CanvasPreview here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class CanvasPreview{
    private int tamano, lado;
    private int[][] m;
    private JLabel view;
    private BufferedImage superficie;
    private Mosaico mosaico;

    public CanvasPreview(Mosaico mosaico, int tamano){
        this.mosaico = mosaico;
        m = mosaico.getPatron();
        this.tamano = tamano;
        lado = (int)tamano / m.length;

        superficie = new BufferedImage(tamano, tamano, BufferedImage.TYPE_INT_RGB);
        view = new JLabel(new ImageIcon(superficie));

        Graphics g = superficie.getGraphics();
        pintar(g);
    }

    private void pintar(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(0,0,tamano,tamano);

        int coordX = 0, coordY = 0;
        for(int i = 0; i < m.length; i++){
            coordX = 0;
            for(int j = 0; j < m.length; j++){
                if(m[i][j] == 1){
                    g.setColor( mosaico.getColor(1) );
                    g.fillRect( coordX, coordY, lado, lado );
                }else{
                    g.setColor( mosaico.getColor(2) );
                    g.fillRect( coordX, coordY, lado, lado );
                }
                coordX += lado;
            }
            coordY += lado;
        }
        g.dispose();
    }

    public JLabel getView(){
        return view;
    }
}
