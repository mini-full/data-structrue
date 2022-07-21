public class NBody {
    
    public static double readRadius(String filename){
        double radius = 0;
        In in = new In(filename);
        if(!in.isEmpty()){
            in.readInt();
            radius = in.readDouble();
        }
        return radius;
    }

    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numsOfPlanets = in.readInt();
        in.readDouble();
        Planet[] ret = new Planet[numsOfPlanets];
        int i = 0;
        while(numsOfPlanets != 0){
            numsOfPlanets --;
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            ret[i++] = new Planet(xpos, ypos, xvel, yvel, mass, imgFileName);
        }
        return ret;
    }

    public static void main(String[] args) {
        // Collect all needed input
        assert args.length > 2;
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        // Drawing the background
        
    }
}
