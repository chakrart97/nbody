public class Planet {
    double myXPos;            // current x position
    double myYPos;            // current y position
    double myXVel;            // current velocity in x direction
    double myYVel;            // current velocity in y direction
    double myMass;            // mass of planet
    String myFileName;        // file name (in images folder)
    final double G = 6.67 * Math.pow(10, -11);

    public Planet(double xp, double yp, double xv,
                  double yv, double mass, String filename) {
        myXPos = xp;
        myYPos = yp;
        myXVel = xv;
        myYVel = yv;
        myMass = mass;
        myFileName = filename;
    }

    public Planet(Planet p) {
        this.myXPos = p.myXPos;
        this.myYPos = p.myYPos;
        this.myXVel = p.myXVel;
        this.myYVel = p.myYVel;
        this.myMass = p.myMass;
        this.myFileName = p.myFileName;
    }

    public double calcDistance(Planet p) {
        double dist = Math.sqrt(Math.pow(p.myXPos - this.myXPos, 2) + Math.pow(p.myYPos - this.myYPos, 2));
        return dist;
    }

    public double calcForceExertedBy(Planet p) {
        double force = G * this.myMass * p.myMass / Math.pow(this.calcDistance(p), 2);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double force = G * this.myMass * p.myMass / Math.pow(this.calcDistance(p), 2);
        double forceX = (p.myXPos - this.myXPos) / this.calcDistance(p) * force;
        return forceX;
    }

    public double calcForceExertedByY(Planet p) {
        double force = G * this.myMass * p.myMass / Math.pow(this.calcDistance(p), 2);
        double forceY = (p.myYPos - this.myYPos) / this.calcDistance(p) * force;
        return forceY;
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double netForceX = 0.0;
        for (Planet p : planets) {
            if (!p.equals(this)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double netForceY = 0.0;
        for (Planet p : planets) {
            if (!p.equals(this)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public boolean equals(Planet p) {
        return this.myXPos == p.myXPos && this.myYPos == p.myYPos && this.myFileName == p.myFileName;
    }

    public void update(double seconds, double xforce, double yforce) {
        double xAccel = xforce/this.myMass;
        double yAccel = yforce/this.myMass;
        this.myXVel += xAccel * seconds;
        this.myYVel += yAccel * seconds;
        this.myXPos += myXVel * seconds;
        this.myYPos += myYVel * seconds;
    }

    public void draw() {
        StdDraw.picture(myXPos, myYPos, "images/"+myFileName);
    }

    public static void main(String[] args) {

    }

}
