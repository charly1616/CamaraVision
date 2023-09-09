
package Model;

import java.awt.Dimension;
import javax.swing.JFrame;
import org.opencv.core.Core;

public class Camara extends JFrame{
    public Camara(){
        this.setSize(new Dimension(600,600));
        setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }
    
}
