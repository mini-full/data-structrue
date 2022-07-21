public class Planet{

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
    public double calcDistance(Planet other){
        return Math.sqrt((this.xxPos - other.xxPos)*(this.xxPos - other.xxPos)
                        + (this.yyPos - other.yyPos)*(this.yyPos - other.yyPos));
    }

    public double calcForceExertedBy(Planet other){
        double distance = this.calcDistance(other);
        return this.mass * other.mass * G / (distance * distance);
    }

    public double calcForceExertedByX(Planet other){
        double distance = calcDistance(other);
        double cos = Math.abs(this.xxPos - other.xxPos) / distance;
        return this.mass * other.mass * G / (distance * distance) * cos;
    }

    

    

    public double calcForceExertedByY(Planet other) {
        double distance = calcDistance(other);
        double sin = Math.abs(this.yyPos - other.yyPos) / distance;
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

    public void update(double dt, double fX, double fY){
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
}

    