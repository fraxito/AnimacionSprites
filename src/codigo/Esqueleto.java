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
public class Esqueleto {
     Image esqueleto;
        //nº de frame que le toca pintar
     private int contador = 0;
        //dirección en la que se mueve
     int dir = 1;
        //indica si esta parado o se está moviendo
     boolean parado = true;
        //coordenadas de esqueleto
      int x = 0, y = 0;
     private int anchoSprite = 64;
     private int altoSprite = 64;

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }
     
     public Esqueleto (){
        try {
            esqueleto = ImageIO.read((getClass().getResource("/imagenes/esqueletos.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
     public void dibuja (Graphics2D g2){
         int fila = 0;
         // si no está parado, hace lo que hacía antes
         if (!parado){
            switch (dir){
                case 1: fila = 2;  break;  //izquierda
                case 2: fila = 6;  break;  //derecha
                case 3: fila = 4;  break;  //arriba
                case 4: fila = 0;  break;  //abajo  
            }
            contador++;
         }
         else { // en este caso es que está parado, y tengo que dejar
             // el contador a 7 para que se vea sentado
            switch (dir){
                case 1: fila = 2; break;  //izquierda
                case 2: fila = 6; break;  //derecha
                case 3: fila = 4; break;  //arriba
                case 4: fila = 0; break;  //abajo  
                
            }
            contador = 6;
         }
        g2.drawImage(esqueleto,
                   x,  //posición x dentro del buffer
                   y,  //posición y dentro del buffer
                   x+ anchoSprite,  //tamaño en el eje x del frame que quiero pintar
                   y+ altoSprite,  //tamaño en el eje y del frame que quiero pintar
                   contador*anchoSprite, //posicion inicial x dentro del SPRITESHEET
                    fila*altoSprite, // posicion inicial y dentro del spritesheet
                   contador*anchoSprite + anchoSprite, //tamaño del tile (ancho)
                   fila*altoSprite + altoSprite, //tamaño del tile (alto)
                   null
                   );
        
        if (contador == 6) contador = 0; 
     }
     
     private void mueve (int dx, int dy){
         x = x + dx;
         //con este if controlo si ha tocado la pared izquierda
         if (x < 0) {x = 0;}
         
         y = y + dy;
         //con este if controlo si ha tocado el techo
         if (y < 0) {y = 0;}
     }
}
