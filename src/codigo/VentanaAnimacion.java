package codigo;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;

/**
 *
 * @author Jorge Cisneros
 */
public class VentanaAnimacion extends javax.swing.JFrame {
    //buffer para pintar sobre el jPanel
    BufferedImage buffer = null;
    //ancho y alto de la pantalla
    int anchoPantalla = 700;
    int altoPantalla = 700;
    
    //temporizador para crear la animación
    Timer temporizador = new Timer( 30, new ActionListener(){
        public void actionPerformed(ActionEvent e){
            bucleJuego();
        }
    });

    Link link = new Link();
    int spawn = 0;
    
    ArrayList <Esqueleto> listaEsqueletos = new ArrayList();
    ArrayList <Disparo> listaDisparos = new ArrayList();
    
    Random aleatorio = new Random();
    
    // variable para guardar la direccion
    //si vale 0 => parado
    //si vale 1 => izquierda
    //si vale 2 => derecha
    //si vale 3 => arriba
    //si vale 4 => abajo
    int direccion = 0;
    
    /**
     * Creates new form VentanaAnimacion
     */
    public VentanaAnimacion() {
        initComponents();
        this.setSize(anchoPantalla, altoPantalla);
        buffer = (BufferedImage) jPanel1.createImage(anchoPantalla, altoPantalla);
        temporizador.start();
    }

    private void creaEsqueleto(){
        Esqueleto e = new Esqueleto();
        e.x = aleatorio.nextInt(anchoPantalla);
        e.y = aleatorio.nextInt(altoPantalla);
        e.setDir(0);
        e.parado = false;
        
        listaEsqueletos.add(e);
    }
    
    private void dibujaListaEsqueletos(Graphics2D g2){
        for (int i=0; i < listaEsqueletos.size(); i++){
            Esqueleto e = listaEsqueletos.get(i);
            //actualizo la posicion del esqueleto en funcion de la posicion de link
            if (e.x > link.x){e.x--; e.dir = 1;}
            else if (e.x < link.x){e.x++; e.dir = 2;} 
            
            if (e.y > link.y){e.y--; e.dir = 3;}
            else if (e.y < link.y) {e.y++; e.dir = 4;}            
            
            e.dibuja(g2);
        }
    }
 
    private void dibujaListaDisparos(Graphics2D g2){
        for (int i=0; i < listaDisparos.size(); i++){
            Disparo d = listaDisparos.get(i);
            d.dibuja(g2);
        }
    }
        
    private void dispara(){
        Disparo d = new Disparo(link.x+16, link.y+16, link.dir);
        listaDisparos.add(d);
    }
    
    private void chequeaColision(){
        //creo un marco para guardar el borde de la imagen del marciano
    Rectangle2D.Double rectanguloEsqueleto = new Rectangle2D.Double();
        //creo un marco para guardar el borde de la imagen del disparo
    Rectangle2D.Double rectanguloDisparo = new Rectangle2D.Double(); 
    
        //ahora leo la lista de disparos 
    for (int j=0; j<listaDisparos.size(); j++){
        Disparo d = listaDisparos.get(j);
        //asigno al rectángulo las dimensiones del disparo y su posicion
        rectanguloDisparo.setFrame(d.x, d.y, 48/3, 48/3);
        boolean disparoABorrar = false;
        //leo la lista de marcianos y comparo uno a uno con el disparo
        for (int i=0; i< listaEsqueletos.size(); i++){
            Esqueleto e = listaEsqueletos.get(i);
            rectanguloEsqueleto.setFrame(e.x, e.y, 64/3, 64/3);
            if (rectanguloDisparo.intersects(rectanguloEsqueleto)){
                listaEsqueletos.remove(i);
                //no borro aqui el disparo para evitar que se cuelgue 
                //listaDisparos.remove(j);
                disparoABorrar = true;
            }
        }
        if (disparoABorrar){
            
            listaDisparos.remove(j);
        }
    }
    
}
    
    private void bucleJuego(){
        //primero apunto al buffer
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();
        //borro la pantalla
        g2.setColor(Color.black);
        g2.fillRect(0, 0, anchoPantalla, altoPantalla);
        ///////////////////// dibujo a link //////////////
        //incrementa el contador para saber si hay que añadir esqueleto
        spawn++;
        if (spawn == 100){
            creaEsqueleto();
            spawn = 0;
        }
        chequeaColision();
        
        link.setDir(direccion);
        link.dibuja(g2);

        
        dibujaListaEsqueletos(g2);
        dibujaListaDisparos(g2);
        
        
        
        /////////////////////////////////////////////////
        //apunto al jPanel y repinto con el nuevo buffer
        g2 = (Graphics2D) jPanel1.getGraphics();
        g2.drawImage(buffer , 0, 0 , null);
        
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                formKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        switch (evt.getKeyCode()){
            case KeyEvent.VK_LEFT   : direccion = 1; break;
            case KeyEvent.VK_RIGHT  : direccion = 2; break;
            case KeyEvent.VK_UP     : direccion = 3; break;
            case KeyEvent.VK_DOWN   : direccion = 4; break;
            case KeyEvent.VK_SPACE  : dispara(); break;    
        }
        link.parado = false;
    }//GEN-LAST:event_formKeyPressed

    private void formKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyReleased
        link.parado = true;
//direccion = 0;
    }//GEN-LAST:event_formKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaAnimacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaAnimacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
