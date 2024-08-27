                               
public class vec3 {
    private double[] e;

vec3(){};

    public vec3(double x,double y,double z){
        e =new double[]{x,y,z};
    }

    public double x(){
        return e[0];
    }

    public double y(){
        return e[1];
    }

    public double z(){
        return e[2];
    }
    public vec3 translate(double x,double y,double z){
        return new vec3(
            this.e[0]+x,
            this.e[1]+y,
            this.e[2]+z
        );
    }

    public vec3 add(vec3 other){
        return new vec3(
        this.e[0]+other.e[0],
        this.e[1]+other.e[1],
        this.e[2]+other.e[2]
        );
    }

    public vec3 scale(double t){
        return new vec3(
        this.e[0] *t,
        this.e[1] *t,
        this.e[2] *t
        );
    }

    public double magnitude(){
        return Math.sqrt(e[0]*e[0]+e[1]*e[1]+e[2]*e[2]);
    }

    public double dot(vec3 v) {
        return this.x() * v.x() + this.y() * v.y() + this.z() * v.z();
    }

    public vec3 cross(vec3 vector1,vec3 vector2){
        return new vec3(
        vector1.y()*vector2.z()-vector1.z()*vector2.y(),
        -vector1.x()*vector2.z()+vector1.z()*vector2.x(),
        vector1.x()*vector2.y()-vector1.y()*vector2.x()

        );
    }

    public vec3 unitVector(){
        return this.scale(1.0/magnitude());
    }

    public vec3 random(){
        return new vec3(Math.random(),Math.random(),Math.random());
    }

    public vec3 random(double min,double max){
        return new vec3(Math.random()*(max-min)+min,Math.random()*(max-min)+min,Math.random()*(max-min)+min);
    }
     public vec3 randomInUnitSphere(){
        while(true){
         vec3 p =random( -1.0,1.0);
         if (p.dot(p) < 1.0) {
            return p;
        }
        }
     }

     public vec3 randomUnitVector(){
     return randomInUnitSphere().unitVector(); 
    
    }

    public vec3 randomOnHemisphere(vec3 normal){
        vec3 onUnitSphere =randomUnitVector();
        if(onUnitSphere.dot(normal)>0.0){
            return onUnitSphere;
        }
        else
        return onUnitSphere;
    }
}
