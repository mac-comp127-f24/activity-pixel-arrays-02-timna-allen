import java.util.Scanner;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;

public class ImageTransform {

    public static Image lighten(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.valueOf("RGB"));
        for (int i=0; i< pixels.length; i++){
            pixels[i] *= 1.75;
        }

        Image transImg = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, Image.PixelFormat.valueOf("RGB"));
        return transImg;
    }

    public static Image makeGray(Image srcImage){
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.valueOf("GRAYSCALE"));
        Image transImg = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, Image.PixelFormat.valueOf("GRAYSCALE"));
        return transImg;
    }

    public static Image makeLightGray(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.valueOf("GRAYSCALE"));
        for (int i=0; i< pixels.length; i++){
            pixels[i] *= 2.5;
        }

        Image transImg = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, Image.PixelFormat.valueOf("GRAYSCALE"));
        return transImg;
    }

    


    public static Image greenShift(Image srcImage) {
        float[] pixels = srcImage.toFloatArray(Image.PixelFormat.valueOf("RGB"));
        for (int i=1; i< pixels.length; i+=3){
            pixels[i] += .25;
            
        }

        Image transImg = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, Image.PixelFormat.valueOf("RGB"));
        return transImg;
        }

    public static Image invert(Image srcImage) {
        byte[] pixels = srcImage.toByteArray(Image.PixelFormat.valueOf("RGB"));
        for (int i=0; i< pixels.length; i++){
            pixels[i]^=255;
            
        }

        Image transImg = new Image(srcImage.getImageWidth(), srcImage.getImageHeight(), pixels, Image.PixelFormat.valueOf("RGB"));
        return transImg;
    }

    public static void main(String[] args) {
        Image srcImage = new Image("mscs-shield.png");
    
        Scanner scan = new Scanner(System.in);
        System.out.println("How would you like to transform your image?");
        System.out.println("1. Lighten");
        System.out.println("2. Green Shift");
        System.out.println("3. Invert");
        System.out.println("4. Convert to grayscale");
        System.out.println("5. Convert to grayscale and lighten too much");

        System.out.print("> ");
        int choice = scan.nextInt();

        Image transformed = switch(choice) {
            default -> srcImage; // If no matching choice, display original image
            case 1 -> lighten(srcImage);
            case 2 -> greenShift(srcImage);
            case 3 -> invert(srcImage);
            case 4 -> makeGray(srcImage);
            case 5 -> makeLightGray(srcImage);
        };

        CanvasWindow canvas = new CanvasWindow("img", 500, 500);
        canvas.add(transformed);
        transformed.setCenter(canvas.getCenter());



        scan.close();
    }
    
}
