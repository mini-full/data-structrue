public class NBodyExtreme {
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

    // Collision detection
    public static boolean detectCollision(Planet planet1, Planet planet2){
        double distance = planet1.calcDistance(planet2);
        double radius = planet1.radius + planet2.radius;
        if(distance < radius){
            return true;
        }
        return false;
    }

    // Collision handling
    public static void handleCollision(Planet planet1, Planet planet2){
        double m1 = planet1.mass;
        double m2 = planet2.mass;
        double v1x = planet1.xxVel;
        double v1y = planet1.yyVel;
        double v2x = planet2.xxVel;
        double v2y = planet2.yyVel;
        double x1 = planet1.xxPos;
        double y1 = planet1.yyPos;
        double x2 = planet2.xxPos;
        double y2 = planet2.yyPos;
        double v1x2 = v1x * (m1 - m2) / (m1 + m2) + v2x * 2 * m2 / (m1 + m2);
        double v1y2 = v1y * (m1 - m2) / (m1 + m2) + v2y * 2 * m2 / (m1 + m2);
        double v2x2 = v2x * (m2 - m1) / (m1 + m2) + v1x * 2 * m1 / (m1 + m2);
        double v2y2 = v2y * (m2 - m1) / (m1 + m2) + v1y * 2 * m1 / (m1 + m2);
        planet1.xxVel = v1x2;
        planet1.yyVel = v1y2;
        planet2.xxVel = v2x2;
        planet2.yyVel = v2y2;
        double x12 = x1 - x2;
        double y12 = y1 - y2;
        double x122 = x12 * (m1 - m2) / (m1 + m2) + x2 * 2 * m2 / (m1 + m2);
        double y122 = y12 * (m1 - m2) / (m1 + m2) + y2 * 2 * m2 / (m1 + m2);
        double x22 = x2 - x1;
        double y22 = y2 - y1;
        double x222 = x22 * (m2 - m1) / (m1 + m2) + x1 * 2 * m1 / (m1 + m2);
        double y222 = y22 * (m2 - m1) / (m1 + m2) + y1 * 2 * m1 / (m1 + m2);
        planet1.xxPos = x122;
        planet1.yyPos = y122;
        planet2.xxPos = x222;
        planet2.yyPos = y222;
    }

    // Generate the planets
    public static Planet[] generatePlanets(int numsOfPlanets, double radius, double T, double dt){
        Planet[] ret = new Planet[numsOfPlanets];
        for(int i = 0; i < numsOfPlanets; i++){
            double xpos = StdRandom.uniform(-radius, radius);
            double ypos = StdRandom.uniform(-radius, radius);
            double xvel = StdRandom.uniform(-100, 100);
            double yvel = StdRandom.uniform(-100, 100);
            double mass = StdRandom.uniform(0.0001, 100);
            String imgFileName = "images/sun.gif";
            ret[i] = new Planet(xpos, ypos, xvel, yvel, mass, imgFileName);
        }
        return ret;
    }

    // Read the radius
    public static double readRadius(String filename){
        In in = new In(filename);
        int numsOfPlanets = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    // Read the planets
    public static Planet[] readPlanets(String filename){
        In in = new In(filename);
        int numsOfPlanets = in.readInt();
        double radius = in.readDouble();
        Planet[] ret = new Planet[numsOfPlanets];
        for(int i = 0; i < numsOfPlanets; i++){
            double xpos = in.readDouble();
            double ypos = in.readDouble();
            double xvel = in.readDouble();
            double yvel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            ret[i] = new Planet(xpos, ypos, xvel, yvel, mass, imgFileName);
        }
        return ret;
    }

    // Main method

    public static void main(String[] args) {
        String filename = args[0];
        double T = Double.parseDouble(args[1]);
        double dt = Double.parseDouble(args[2]);
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        run(planets, radius, T, dt);
    }

    // Test the class
    public static void test(){
        Planet[] planets = new Planet[4];
        planets[0] = new Planet(0, 0, 0, 0, 1, "images/sun.gif");
        planets[1] = new Planet(1, 0, 0, 1, 2, "images/earth.gif");
        planets[2] = new Planet(4, 0, 0, 0, 3, "images/moon.gif");
        planets[3] = new Planet(0, 5, 0, 0, 4, "images/redplanet.gif");
        run(planets, 5, 100, 0.01);
    }
}
