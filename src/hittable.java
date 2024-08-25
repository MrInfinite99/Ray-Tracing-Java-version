public abstract class hittable {
    
    public abstract boolean hit(ray r, double tParaMin, double tParaMax, hitRecord rec);
}
