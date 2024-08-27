import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class camera {

    camera() {
    };

    public void render(hittable world) {
        initialize();
        try (FileWriter fileWriter = new FileWriter("output.ppm");
                PrintWriter printWriter = new PrintWriter(fileWriter)) {

            printWriter.println("P3\n" + imageWidth + " " + imageHeight + "\n255\n");

            for (int i = 0; i < imageHeight; i++) {
                System.out.println("scanlines remaining" + (imageHeight - i) + "\n");
                for (int j = 0; j < imageWidth; j++) {
                    // setup ray
            

                    vec3  pixelColor =new vec3(0,0,0);
                    for(int sample =0;sample<samplesPerPixel;sample++){
                    ray mainRay =getRay(j,i);
                    pixelColor =pixelColor.add(rayColor(mainRay,maxDepth,world));
                    }
                    double r = pixelSamplesScale*pixelColor.x();
                    double g = pixelSamplesScale*pixelColor.y();
                    double b = pixelSamplesScale*pixelColor.z();

                    int ir = (int) (256 * clamp(r,0.000,0.999));
                    int ig = (int) (256 * clamp(g,0.000,0.999));
                    int ib = (int) (256 * clamp(b,0.000,0.999));

                    printWriter.println(ir + " " + ig + " " + ib + "\n");
                    
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Done");

    }

    private void initialize() {
        imageHeight = (int) (imageWidth / aspectRatio);
        imageHeight = (imageHeight < 1) ? 1 : imageHeight;

        pixelSamplesScale = 1.0 / samplesPerPixel;

        cameraCenter = new vec3(0.0, 0.0, 0.0);

        // Determine viewpiort dimensions
        double focalLength = 1.0;
        double viewportHeight = 2.0;
        double viewportWidth = viewportHeight * ((double) imageWidth / imageHeight);

        // Calculate the vectors across the horizontal and down the vertical viewport
        // edges.
        vec3 viewportU = new vec3(viewportWidth, 0.0, 0.0);
        vec3 viewportV = new vec3(0.0, -viewportHeight, 0.0);

        // Calculate the horizontal and vertical delta vectors from pixel to pixel.
        pixelDeltaU = viewportU.scale(1.0 / imageWidth);
        pixelDeltaV = viewportV.scale(1.0 / (double) imageHeight);

        // Calculate the location of the upper left pixel.
        vec3 viewportUpperLeft = cameraCenter.add(viewportU.scale(-0.5)).add(viewportV.scale(-0.5));

        viewportUpperLeft = viewportUpperLeft.translate(0.0, 0.0, -focalLength);

        pixel00location = viewportUpperLeft.add(pixelDeltaU.scale(0.5)).add(pixelDeltaV.scale(0.5));

    }

    public vec3 rayColor(ray r,int depth, hittable world) {
        if (depth <= 0)
            return new vec3(0,0,0);
        hitRecord rec = new hitRecord();

        if (world.hit(r, 0, Double.POSITIVE_INFINITY, rec)) {
vec3 direction  = new vec3(0,0,0);
     direction =direction.randomOnHemisphere(rec.normal);
            //return rec.normal.add(new vec3(1, 1, 1)).scale(0.5);
             return rayColor(new ray(rec.point,direction),depth-1,world).scale(0.5); 
        }

        vec3 unitDirection = r.direction().unitVector();
        double a = 0.5 * (unitDirection.y() + 1.0);
        return new vec3(1.0, 1.0, 1.0).scale(1.0 - a).add(new vec3(0.5, 0.7, 1.0).scale(a));
    }

    private ray getRay(int j, int i) {
        vec3 offset = sampleSquare();
        vec3 pixelSample = pixel00location.add(pixelDeltaU.scale(j + offset.x()))
                .add(pixelDeltaV.scale(i+ offset.y()));
        vec3 rayOrigin = cameraCenter;
        vec3 rayDirection = pixelSample.add(rayOrigin.scale(-1.0));

        return new ray(rayOrigin, rayDirection);

    }

    private vec3 sampleSquare() {
        return new vec3(Math.random() - 0.5, Math.random() - 0.5, 0);
    }

    private double clamp(double x, double min, double max) {
        if (x < min)
            return min;
        if (x > max)
            return max;
        return x;
    }

    public double aspectRatio;
    public int imageWidth;
    public int samplesPerPixel = 10;
    public int maxDepth =10;

    private int imageHeight;
    private vec3 cameraCenter;
    private vec3 pixel00location;
    private vec3 pixelDeltaU;
    private vec3 pixelDeltaV;
    private double pixelSamplesScale;

}
