import java.util.ArrayList;

public class hittableList extends hittable{
    hittableList() {
    };

    hittableList(hittable object) {
        objects.add(object);
    }

    public ArrayList<hittable> objects =new ArrayList<>();

    public void clearList() {
        objects.clear();
    }

    public void addObject(hittable object) {
        objects.add(object);
    }

    public boolean hit(ray r, double tParaMin, double tParaMax, hitRecord rec) {
        hitRecord tempRec = new hitRecord();
        boolean hitAnything = false;
        double closestSoFar = tParaMax;

        for (hittable object : objects) {
            if (object.hit(r, tParaMin, closestSoFar, tempRec)) {
                hitAnything = true;
                closestSoFar = tempRec.t;
                rec.t = tempRec.t;
                rec.normal = tempRec.normal;
                rec.point =tempRec.point;

                 
            }

        }

        return hitAnything;
    }

}
