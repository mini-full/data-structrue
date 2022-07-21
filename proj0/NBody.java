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
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        // Drawing the planets
        for(Planet planet : planets){
            planet.draw();
        }

        // Calculate the forces and update the planets
        for(double time = 0; time < T; time += dt){
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for(int i = 0; i < planets.length; i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for(int i = 0; i < planets.length; i++){
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            // Drawing the planets
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for(Planet planet : planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}
