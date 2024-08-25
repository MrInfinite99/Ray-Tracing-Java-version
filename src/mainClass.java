import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class mainClass {
    public static void main(String[] args) {
        int imageWidth = 400;
        // int imageHeight=256;

        double aspectRatio = 16.0 / 9.0;

        // calculate image height
        int imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = (imageHeight < 1) ? 1 : imageHeight;

        //world
        hittableList world =new hittableList();

        world.addObject(new sphere(new vec3(0,0,-1),0.5));
        world.addObject(new sphere(new vec3(0,-100.5,-1),100));




        // Camera
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);
        vec3 cameraCenter = new vec3(0.0, 0.0, 0.0);

        // calculate the vectors across the horizontal and down the vertical viewport
        // edges
        vec3 viewportU = new vec3(viewportWidth, 0.0, 0.0);
        vec3 viewportV = new vec3(0.0, -viewportHeight, 0.0);
        // System.out.println(viewportV.y());
        // Calculate the horizontal and vertical delta vectors from pixel to pixel
        vec3 pixelDeltaU = viewportU.scale(1.0 / imageWidth);
        vec3 pixelDeltaV = viewportV.scale(1.0 / (double) imageHeight);
        // System.out.println(pixelDeltaV.x());
        // Calculate the location of the upper left pixel, no overloading sucks!!!
        vec3 viewportUpperLeft = cameraCenter.add(viewportU.scale(-0.5)).add(viewportV.scale(-0.5));

        viewportUpperLeft = viewportUpperLeft.translate(0.0, 0.0, -focalLength);
        // System.out.println(viewportUpperLeft.x());
        // System.out.println(viewportUpperLeft.y());
        vec3 pixel00location = viewportUpperLeft.add(pixelDeltaU.scale(0.5)).add(pixelDeltaV.scale(0.5));
        // System.out.println(pixel00location.y());

        // Rendering
        try (FileWriter fileWriter = new FileWriter("output.ppm");
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int i = 0; i < imageHeight; i++) {
                System.out.println("scanlines remaining" + (imageHeight - i) + "\n");
                for (int j = 0; j < imageWidth; j++) {
                    // setup ray
                    vec3 pixelCenter = pixel00location.add(pixelDeltaU.scale((double) j))
                            .add(pixelDeltaV.scale((double) i));

                    vec3 rayDirection = pixelCenter.add(cameraCenter.scale(-1.0));

                    ray mainRay = new ray(cameraCenter, rayDirection);
                    // write color
                    vec3 pixelColor = mainRay.rayColor(mainRay,world);
                    double r = pixelColor.x();
                    double g = pixelColor.y();
                    double b = pixelColor.z();

                    int ir = (int) (255.999 * r);
                    int ig = (int) (255.999 * g);
                    int ib = (int) (255.999 * b);

                    printWriter.println(ir + " " + ig + " " + ib + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Done");

    }
}
