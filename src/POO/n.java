
package POO;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.highgui.HighGui;

public class n{
    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        VideoCapture capture = new VideoCapture(0); // Abre la cámara, 0 para la cámara predeterminada
        if (!capture.isOpened()) {
            System.out.println("No se pudo abrir la cámara.");
            return;
        }

        Mat frame = new Mat();
        Mat blurred = new Mat();
        Mat hsvImage = new Mat();
        Mat threshold = new Mat();

        while (true) {
            capture.read(frame);
            if (frame.empty()) {
                break;
            }

            // Aplicar un filtro gaussiano para reducir el ruido
            Imgproc.GaussianBlur(frame, blurred, new Size(5, 5), 0);

            // Convertir la imagen a espacio de color HSV
            Imgproc.cvtColor(blurred, hsvImage, Imgproc.COLOR_BGR2HSV);

            // Definir los rangos de colores para la detección de la piel
            Scalar lowerBound = new Scalar(0, 20, 70);
            Scalar upperBound = new Scalar(20, 255, 255);

            // Aplicar un umbral para detectar la piel
            Core.inRange(hsvImage, lowerBound, upperBound, threshold);

            // Realizar operaciones de morfología para eliminar el ruido
            Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
            Imgproc.morphologyEx(threshold, threshold, Imgproc.MORPH_OPEN, kernel);
            Imgproc.morphologyEx(threshold, threshold, Imgproc.MORPH_CLOSE, kernel);

            // Encontrar contornos en la imagen umbral
            Mat hierarchy = new Mat();
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(threshold, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

            // Dibujar contornos en la imagen original
            Imgproc.drawContours(frame, contours, -1, new Scalar(0, 255, 0), 2);

            // Mostrar la imagen resultante
            HighGui.imshow("Hand Gesture Detection", frame);

            if (HighGui.waitKey(10) == 27) {
                break; // Salir si se presiona la tecla Esc
            }
        }

        capture.release();
        HighGui.destroyAllWindows();
    }
}