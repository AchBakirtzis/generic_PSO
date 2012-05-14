
import java.util.*;

public class bird {

    public static double gbestx,gbesty,gbest=-1;
    public static final double X_max=10000;
    public static final double Y_max=10000;
    private double x,y,vx=0,vy=0,pbestx,pbesty,pbest,r,r1,r2,temp;

    Random random=new Random();

    private double utility_fun(double x, double y){
        return x+y;
    }

    public bird(){
        r = random.nextDouble();
        x=r*X_max;
        r = random.nextDouble();
        y=r*Y_max;
        pbestx=x;
        pbesty=y;
        pbest=utility_fun(x,y);

    }
    public void calculate_speed(){
        r1 = random.nextDouble();
        r2 = random.nextDouble();
        vx=vx+(2*r1*(pbestx-x))+(2*r2*(gbestx-x));
        r1 = random.nextDouble();
        r2 = random.nextDouble();
        vy=vy+(2*r1*(pbesty-y))+(2*r2*(gbesty-y));
    }
    public void move(){
        x=x+vx;
        y=y+vy;
        check_constraints();
    }
    
    public void check_constraints(){
    	if (x>X_max){
            x=X_max;
        }
        else if(x<-X_max){
            x=-X_max;
        }
        if (y>Y_max){
            y=Y_max;
        }
        else if(y<-Y_max){
            y=-Y_max;
        }
    }

    public void evaluate_best(){
        temp=utility_fun(x,y);
        if(temp>pbest){
            pbest=temp;
            pbestx=x;
            pbesty=y;
            if(temp>gbest){
                gbest=temp;
                gbestx=x;
                gbesty=y;
            }
        }
        
    }




	public static void main(String[] args) {
		
        int swarm_size=10;
        bird[] swarm = new bird[swarm_size];
        int i,moves;
        for(i=0;i<swarm_size;i++){
            swarm[i]= new bird();
        }
        for(moves=0;moves<100;moves++){
            for(i=0;i<swarm_size;i++){
                swarm[i].calculate_speed();
                swarm[i].move();
                swarm[i].evaluate_best();
            }
        }
        System.out.println(bird.gbest);
        System.out.println(bird.gbestx);
        System.out.println(bird.gbesty);
        
    }

}


