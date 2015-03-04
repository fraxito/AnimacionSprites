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
     int contador = 0;
     
     public Link (){
        try {
            link = ImageIO.read((getClass().getResource("/imagenes/link.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dibuja (Graphics2D g2){
        g2.drawImage(link,
                   100,  //posición x dentro del buffer
                   100,  //posición y dentro del buffer
                   120,  //tamaño en el eje x del frame que quiero pintar
                   130,  //tamaño en el eje y del frame que quiero pintar
                   contador*120, //posicion inicial x dentro del SPRITESHEET
                    7*130, // posicion inicial y dentro del spritesheet
                   contador*120 + 120, //tamaño del tile (ancho)
                   7*130 + 130, //tamaño del tile (alto)
                   null
                   );
        contador++;
        if (contador == 10) contador = 0; 
     }
}
