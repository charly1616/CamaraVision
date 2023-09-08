
package Model;

import javax.swing.JFrame;
import org.opencv.core.Core;
public class Camara extends JFrame{
    public Camara(){
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        System.out.println("Exitooooo");
        
    }
    
}
