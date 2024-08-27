 
public class ray {

    private vec3 direction;
    private vec3 origin;
     
    public ray(vec3 orig,vec3 dir){
           origin =orig;
           direction=dir;
         
    }

    public vec3 origin(){
        return origin;
    }

    public vec3 direction(){
        return direction;
    }

    public vec3 position(double t){
       
        return origin.add(direction.scale(t));
    }

   // public vec3 rayColor(ray r,hittable world){
    //hitRecord rec =new hitRecord();
    //  double t_param =sphereCollision(new vec3(0.0,0.0,-1.0), 0.5, r);

    //  if(t_param>0.0){
    //     vec3 normalVector= r.position(t_param).add(new vec3(0,0,-1).scale(-1)).unitVector();

    //     return new vec3(normalVector.x()+1,normalVector.y()+1,normalVector.z()+1).scale(0.5);
    //  }
    //if(world.hit(r,0, Double.POSITIVE_INFINITY,rec)){
        
      // return rec.normal.add(new vec3(1,1,1)).scale(0.5);
    //}
         
    
       //vec3 unitDirection =this.direction().unitVector();
       //double a =0.5*(unitDirection.y()+1.0);
       //return new vec3(1.0,1.0,1.0).scale(1.0-a).add(new vec3(0.5,0.7,1.0).scale(a));
    //}

   /*  public double sphereCollision(vec3 center,double radius,ray r){
        vec3 radiusVector =center.add(r.origin().scale(-1.0));// the oc vector
        double a = r.direction().dot(r.direction());
        double b =r.direction().scale(-2).dot(radiusVector);
        double c =radiusVector.dot(radiusVector)-radius*radius;
        double discrimant = b*b-4*a*c;

        if(discrimant <0){
        return -1.0;
        }
        else{
            return (-b-Math.sqrt(discrimant))/(2.0*a);
        }
         
    }*/
}
