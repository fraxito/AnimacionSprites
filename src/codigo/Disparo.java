/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class Disparo {
    //imagen de la flecha
    Image imagen;
    //posición de la flecha
     int x,y;
    //dirección de la flecha
    private int dir;
    
    public Disparo (int _x, int _y, int _dir){
        try {
            imagen = ImageIO.read((getClass().getResource("/imagenes/Flecha_Zelda.png")));
        } catch (IOException ex) {
            Logger.getLogger(VentanaAnimacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        x = _x;
        y = _y;
        dir = _dir;
    }
    
    public void dibuja (Graphics2D g2){
        int fila = 0;
        int columna = 0;
            switch (dir){
                case 1: fila = 1; columna = 1;  x-=3; break;  //izquierda
                case 2: fila = 1; columna = 0;  x+=3; break;  //derecha
                case 3: fila = 0; columna = 0;  y-=3; break;  //arriba
                case 4: fila = 0; columna = 1;  y+=3; break;  //abajo  
            }
        g2.drawImage(imagen,
               x,  //posición x dentro del buffer
               y,  //posición y dentro del buffer
               x+ 48/3,  //tamaño en el eje x del frame que quiero pintar
               y+ 48/3,  //tamaño en el eje y del frame que quiero pintar
               columna*48, //posicion inicial x dentro del SPRITESHEET
                fila*48, // posicion inicial y dentro del spritesheet
               columna*48 + 48, //tamaño del tile (ancho)
               fila*48 + 48, //tamaño del tile (alto)
               null
               );
        
    }
}
