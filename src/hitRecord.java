public class hitRecord {
hitRecord(){
     
};

    public vec3 normal;
    public double t;
    public vec3 point;
    public boolean frontFace;

    public void setFaceNormal(ray r, vec3 outwardNormal){
        // Sets the hit record normal vector.
        // NOTE: the parameter `outward_normal` is assumed to have unit length.

        frontFace=r.direction().dot(outwardNormal)<0;
        //System.out.println(frontFace);
        normal =frontFace ? outwardNormal : outwardNormal.scale(-1.0);
       // System.out.println(normal.x());
    }
}
