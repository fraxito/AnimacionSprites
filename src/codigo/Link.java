package codigo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author xp
 */
public class Link {
     Image link;
     private int contador = 0;
     int dir = 0;
     boolean parado = true;

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
     
     public Link (){
        try {
            link = ImageIO.read((getClass().getResource("/imagenes/link.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dibuja (Graphics2D g2){
         int fila = 0;
         // si no está parado, hace lo que hacía antes
         if (!parado){
            switch (dir){
                case 1: fila = 5; break;  //izquierda
                case 2: fila = 7; break;  //derecha
                case 3: fila = 6; break;  //arriba
                case 4: fila = 4; break;  //abajo  
            }
            contador++;
         }
         else { // en este caso es que está parado, y tengo que dejar
             // el contador a 0 , y mostrar la fila correspondiente
            switch (dir){
                case 1: fila = 1; break;  //izquierda
                case 2: fila = 3; break;  //derecha
                case 3: fila = 2; break;  //arriba
                case 4: fila = 0; break;  //abajo  
            }
            contador = 0;
         }
        g2.drawImage(link,
                   100,  //posición x dentro del buffer
                   100,  //posición y dentro del buffer
                   2*120,  //tamaño en el eje x del frame que quiero pintar
                   2*130,  //tamaño en el eje y del frame que quiero pintar
                   contador*120, //posicion inicial x dentro del SPRITESHEET
                    fila*130, // posicion inicial y dentro del spritesheet
                   contador*120 + 120, //tamaño del tile (ancho)
                   fila*130 + 130, //tamaño del tile (alto)
                   null
                   );
        
        //else { contador = 0;} // si está parado, reseteo el contador
        if (contador == 10) contador = 0; 
     }
}
