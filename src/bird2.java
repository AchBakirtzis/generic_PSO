
import java.util.*;
import java.lang.*;


public class bird2 {
	public static final int dimensions=3;
    public static final double[] X_max= new double[] {10000, 2000, 20};
    public static final double[] X_min= new double[] {0, 0, 0};
    private double[] x,v,pbestx;
    private double r,r2,pbest,k1=2,k2=2,k3=2;
    private static double gbest=Double.MIN_VALUE; //Gia elaxistopoiisi PREPEI na ginei Double.MAX_VALUE
    private static double[] gbestx;

    Random random=new Random();
    
    public double utility_fun(double[] x){
        double temp=0;
        for(int i=0;i<dimensions;i++){
        	temp+=Math.pow(Math.sin(x[i]), x[i]);
        }
        return temp;
    }
    
    public bird2(){
    	for(int i=0;i<dimensions;i++){
    		r = random.nextDouble();
    		x[i]=(r*(X_max[i]-X_min[i]))+X_min[i];
    		r = random.nextDouble();
    		v[i]=r*(X_max[i]-X_min[i])+X_min[i];
    	}
		pbestx=x.clone();
    	pbest=utility_fun(x);
    	if(pbest>gbest){
            gbest=pbest;
            gbestx=x.clone();
        }
    	
    }
    
    public void calculate_speed(){
    	for(int i=0;i<dimensions;i++){
    		r = random.nextDouble();
    		r2 = random.nextDouble();
    		v[i]=k1*v[i]+k2*r*(pbestx[i]-x[i])+k3*r2*(gbestx[i]-x[i]);
    	}
    }
    
    public void move(){
    	for(int i=0;i<dimensions;i++){
    		x[i]+=v[i];
    	}
        check_constraints();
    }
    
    public void check_constraints(){
    	for(int i=0;i<dimensions;i++){
	    	if (x[i]>X_max[i]){
	            x[i]=X_max[i];
	        }else if(x[i]<X_min[i]){
	        	x[i]=X_min[i];
	        }
    	}
    }

    public void evaluate_best(){
        double temp=utility_fun(x);
        if(temp>pbest){
            pbest=temp;
            pbestx=x.clone();
            if(temp>gbest){
                gbest=temp;
                gbestx=x.clone();
            }
        }
        
    }




	public static void main(String[] args) {
		
        int swarm_size=10;
        bird2[] swarm = new bird2[swarm_size];
        int i,moves;
        for(i=0;i<swarm_size;i++){
            swarm[i]= new bird2();
        }
        for(moves=0;moves<100;moves++){
            for(i=0;i<swarm_size;i++){
                swarm[i].calculate_speed();
                swarm[i].move();
                swarm[i].evaluate_best();
            }
        }
        System.out.println("PSOd");
        System.out.println(bird2.gbest);
    }

}


