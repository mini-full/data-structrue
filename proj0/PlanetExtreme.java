public class PlanetExtreme {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }
<<<<<<< HEAD

    public double calcDistance(Planet other) {
        return Math.sqrt((this.xxPos - other.xxPos) * (this.xxPos - other.xxPos)
                + (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos));
    }

    public double calcForceExertedBy(Planet other) {
=======
    public double calcDistance(Planet other){
        return Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos)
                        + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
    }

    public double calcForceExertedBy(Planet other){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
        double distance = this.calcDistance(other);
        return this.mass * other.mass * G / (distance * distance);
    }

<<<<<<< HEAD
    public double calcForceExertedByX(Planet other) {
=======
    public double calcForceExertedByX(Planet other){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
        double distance = calcDistance(other);
        double cos = -(this.xxPos - other.xxPos) / distance;
        return this.mass * other.mass * G / (distance * distance) * cos;
    }

    public double calcForceExertedByY(Planet other) {
        double distance = calcDistance(other);
        double sin = -(this.yyPos - other.yyPos) / distance;
        return this.mass * other.mass * G / (distance * distance) * sin;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double ret = 0;
        for (int i = 0; i < planets.length; i++) {
            if (!this.equals(planets[i])) {
                ret += calcForceExertedByX(planets[i]);
            }
        }
        return ret;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double ret = 0;
        for (int i = 0; i < planets.length; i++) {
            if (!this.equals(planets[i])) {
                ret += calcForceExertedByY(planets[i]);
            }
        }
        return ret;
    }

<<<<<<< HEAD
    public void update(double dt, double fX, double fY) {
=======
    public void update(double dt, double fX, double fY){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
        // Calculate the acceleration
        double ax = fX / mass;
        double ay = fY / mass;

        // Calculate the new velocity
        xxVel += dt * ax;
        yyVel += dt * ay;

        // Calculate thenew position
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

<<<<<<< HEAD
    public void draw() {
        String fileName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, fileName);
    }

=======
    public void draw(){
        String fileName = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, fileName);
    }
    
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
    // Spacecraft class
    public static class Spacecraft {
        public double xxPos;
        public double yyPos;
        public double xxVel;
        public double yyVel;
        public double mass;
        public String imgFileName;
<<<<<<< HEAD

        public Spacecraft(double xP, double yP, double xV, double yV, double m, String img) {
=======
        public Spacecraft(double xP, double yP, double xV, double yV, double m, String img){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            this.xxPos = xP;
            this.yyPos = yP;
            this.xxVel = xV;
            this.yyVel = yV;
            this.mass = m;
            this.imgFileName = img;
        }
<<<<<<< HEAD

        public Spacecraft(Spacecraft s) {
=======
        public Spacecraft(Spacecraft s){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            this.xxPos = s.xxPos;
            this.yyPos = s.yyPos;
            this.xxVel = s.xxVel;
            this.yyVel = s.yyVel;
            this.mass = s.mass;
            this.imgFileName = s.imgFileName;
        }
<<<<<<< HEAD

        public double calcDistance(Spacecraft other) {
            return Math.sqrt((this.xxPos - other.xxPos) * (this.xxPos - other.xxPos)
                    + (this.yyPos - other.yyPos) * (this.yyPos - other.yyPos));
        }

        public double calcForceExertedBy(Spacecraft other) {
            double distance = this.calcDistance(other);
            return this.mass * other.mass * G / (distance * distance);
        }

        public double calcForceExertedByX(Spacecraft other) {
=======
        public double calcDistance(Spacecraft other){
            return Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos)
                            + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
        }
        public double calcForceExertedBy(Spacecraft other){
            double distance = this.calcDistance(other);
            return this.mass * other.mass * G / (distance * distance);
        }
        public double calcForceExertedByX(Spacecraft other){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            double distance = calcDistance(other);
            double cos = -(this.xxPos - other.xxPos) / distance;
            return this.mass * other.mass * G / (distance * distance) * cos;
        }
<<<<<<< HEAD

=======
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
        public double calcForceExertedByY(Spacecraft other) {
            double distance = calcDistance(other);
            double sin = -(this.yyPos - other.yyPos) / distance;
            return this.mass * other.mass * G / (distance * distance) * sin;
        }
<<<<<<< HEAD

=======
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
        public double calcNetForceExertedByX(Spacecraft[] spacecrafts) {
            double ret = 0;
            for (int i = 0; i < spacecrafts.length; i++) {
                if (!this.equals(spacecrafts[i])) {
                    ret += calcForceExertedByX(spacecrafts[i]);
                }
            }
            return ret;
        }

        public double calcNetForceExertedByY(Spacecraft[] spacecrafts) {
            double ret = 0;
            for (int i = 0; i < spacecrafts.length; i++) {
                if (!this.equals(spacecrafts[i])) {
                    ret += calcForceExertedByY(spacecrafts[i]);
                }
            }
            return ret;
        }

<<<<<<< HEAD
        public void update(double dt, double fX, double fY){
=======
        public void update(double dt, double fX, double fY){
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            // Calculate the acceleration
            double ax = fX / mass;
            double ay = fY / mass;

            // Calculate the new velocity
            xxVel += dt * ax;
            yyVel += dt * ay;

            // Calculate thenew position
            xxPos += dt * xxVel;
            yyPos += dt * yyVel;
        }

<<<<<<< HEAD
        public void draw() {
=======
        public void draw() {
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            String fileName = "images/" + imgFileName;
            StdDraw.picture(xxPos, yyPos, fileName);
        }

<<<<<<< HEAD
        public void setPosition(double x, double y) {
=======
        public void setPosition(double x, double y) {
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            this.xxPos = x;
            this.yyPos = y;
        }

<<<<<<< HEAD
        public void setVelocity(double x, double y) {
=======
        public void setVelocity(double x, double y) {
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
            this.xxVel = x;
            this.yyVel = y;
        }

<<<<<<< HEAD
        public void setMass(double m) {
            this.mass = m;
        }

        public void setImage(String img) {
            this.imgFileName = img;
        }

        public double getXPosition() {
            return this.xxPos;
        }

        public double getYPosition() {
            return this.yyPos;
        }

        public double getXVelocity() {
            return this.xxVel;
        }

        public double getYVelocity() {
            return this.yyVel;
        }

        public double getMass() {
            return this.mass;
        }

        public String getImage() {
            return this.imgFileName;
        }

    }
=======
        public void setMass(double m) {
            this.mass = m;
        }

        public void setImage(String img) {
            this.imgFileName = img;
        }

        public double getXPosition() {
            return this.xxPos;
        }

        public double getYPosition() {
            return this.yyPos;
        }

        public double getXVelocity(){
            return this.xxVel;
        }

        public double getYVelocity() {
            return this.yyVel;
        }

        public double getMass() {
            return this.mass;
        }

        public String getImage() {
            return this.imgFileName;
        }

        
>>>>>>> 53dda389b90fe7cef862692518266393a64bec44
}
