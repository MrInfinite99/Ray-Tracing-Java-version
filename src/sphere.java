public class sphere extends hittable {
    private vec3 center;
    private double radius;

    sphere() {
    }

    public sphere(vec3 center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    public boolean hit(ray r, double tParaMin, double tParaMax, hitRecord rec) {
        vec3 radiusVector = center.add(r.origin().scale(-1.0));// the oc vector
        double a = r.direction().dot(r.direction());
        double h = r.direction().dot(radiusVector);
        double c = radiusVector.dot(radiusVector) - radius * radius;
        double discrimant = h * h - a * c;

        if (discrimant < 0) {
            return false;
        }

        double sqrtd = Math.sqrt(discrimant);
        double root = (h - sqrtd) / a;

        if (root <= tParaMin || tParaMax <= root) {// outside the range
            root = (h + sqrtd) / a;
            if (root <= tParaMin || tParaMax <= root) {
                return false;
            }
        }

        rec.t = root;
        rec.point = r.position(rec.t);// intersection point

        vec3 outwardNormal = rec.point.add(center.scale(-1.0)).scale(1 / radius);// caalculate normal
        rec.setFaceNormal(r, outwardNormal);//set normal direction to outward
        return true;
    }
}
