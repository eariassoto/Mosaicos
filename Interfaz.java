/*
 * @author Emmanuel Arias Soto
 * @version 0.1
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * The Class Interfaz.
 */
public class Interfaz {

    /**
     * Ayudaran a crear una ventana que muestre el piso lo mas grande posible
     * considerando la pantalla en la que se esta ejecutando.
     */
    private int dimLargo, dimAncho;

    /** El objeto del cual se obtendra la informacion del piso. */
    private Piso piso;

    /** La ventana principal de la interaccion con el usuario. */
    private VentanaPrincipal ventanaPrincipal;

    /** El objeto que creara la representacion grafica del piso. */
    private CanvasPiso canvasPiso;

    /**
     * Instancia una nueva interfaz.
     * 
     * @param ventanaPrincipal
     *            ventana Principal
     * @param piso
     *            the piso
     */
    public Interfaz(VentanaPrincipal ventanaPrincipal, Piso piso) {
        Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.dimLargo = (int) tamanoPantalla.getWidth() - 250;
        this.dimAncho = (int) tamanoPantalla.getHeight() - 150;
        this.ventanaPrincipal = ventanaPrincipal;
        this.piso = piso;
    }

    /**
     * Crea un nuevo JFrame y un nuevo CanvasPiso que vuelve a dibujar el
     * diseno.
     */
    public void mostrarPiso() {
        canvasPiso = new CanvasPiso(piso.getPiso());
        JFrame frame = new JFrame("Piso Generado");
        frame.setSize(this.dimLargo, this.dimAncho);
        frame.setResizable(false);
        frame.getContentPane().add(canvasPiso.view);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Devuelve el objeto BufferedImage para ser procesado y guardado como un
     * archivo de imagen.
     * 
     * @return superficie del piso
     */
    public BufferedImage getSuperficie() {
        canvasPiso = new CanvasPiso(piso.getPiso());
        return canvasPiso.superficie;
    }

    /**
     * Refresca la ventana principal, debido a cambios en las hileras de texto.
     */
    public void cargarVentana() {
        ventanaPrincipal.crearVentana();
    }

    /**
     * Crea un dialogo con opciones.
     * 
     * @param s
     *            hilera de mensaje
     * @param t
     *            hilera con el titulo
     * @param opciones
     *            opciones del dialog
     * @param tipo
     *            tipo de dialogo
     * @return resultado del dialogo
     */
    public int dialogoOpcion(String s, String t, Object[] opciones, int tipo) {
        return JOptionPane.showOptionDialog(null, s, t,
            JOptionPane.YES_NO_OPTION, tipo, null, opciones, null);
    }

    /**
     * Crea un dialogo con un mensaje.
     * 
     * @param s
     *            hilera mensaje
     * @param t
     *            hilera titulo
     * @param tipo
     *            tipo de mensaje
     */
    public void dialogoMensaje(String s, String t, int tipo) {
        JOptionPane.showMessageDialog(null, s, t, tipo, null);
    }

    /**
     * La clase interna CanvasPiso se encarga de crear la representacion grafica
     * del piso en un objeto BufferedImage.
     */
    private class CanvasPiso {

        /** El tamano de las celdas de la matriz, tamanos de largo y ancho de ventana. */
        private int lado, largo, ancho;

        /** La superficie donde se va a dibujar el piso. */
        private BufferedImage superficie;

        /** El JLabel que desplegara el BufferedImage */
        private JLabel view;

        /**
         * Instancia un nuevo canvasPiso.
         * 
         * @param matrizPiso
         *            la matriz a dibujar
         */
        public CanvasPiso(Color[][] matrizPiso) {
            // determina el tamano de las celdas dependiendo del ancho y del largo, esto para evitar celdas "recortadas".
            lado = (dimAncho / matrizPiso.length < dimLargo / matrizPiso[0].length) ? (int) (dimAncho / matrizPiso.length)
            : (int) (dimLargo / matrizPiso[0].length);

            // redimensiona los tamanos, esto para que no sobren espacion vacios en la ventana
            largo = matrizPiso[0].length * lado;
            ancho = matrizPiso.length * lado;
            superficie = new BufferedImage(largo, ancho,
                BufferedImage.TYPE_INT_RGB);
            view = new JLabel(new ImageIcon(superficie));

            Graphics g = superficie.getGraphics();
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, largo, ancho);

            int coordX = 0, coordY = 0;
            for (int i = 0; i < matrizPiso.length; i++) {
                coordX = 0;
                for (int j = 0; j < matrizPiso[0].length; j++) {
                        g.setColor(matrizPiso[i][j]);
                        g.fillRect(coordX, coordY, lado, lado); 
                    coordX += lado;
                }
                coordY += lado;
            }
            g.dispose();
        }
    }
}
