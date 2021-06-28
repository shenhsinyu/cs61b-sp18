public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Body(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Body(Body b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    }

    public double calcDistance(Body b) {
        double distance = Math.sqrt(Math.pow(this.xxPos - b.xxPos, 2) + Math.pow(this.yyPos - b.yyPos, 2));
        return distance;
    }

    public double calcForceExertedBy(Body b) {
        double G = 6.67 * Math.pow(10, -11);
        double F = G * this.mass * b.mass / Math.pow(this.calcDistance(b), 2);
        return F;
    }

    public double calcForceExertedByX(Body b) {
        double dx = (b.xxPos - this.xxPos) / this.calcDistance(b);
        return this.calcForceExertedBy(b) * dx;
    }

    public double calcForceExertedByY(Body b) {
        double dy = (b.yyPos - this.yyPos) / this.calcDistance(b);
        return this.calcForceExertedBy(b) * dy;
    }

    public double calcNetForceExertedByX(Body[] bodies) {
        double TotalForce_X = 0;
        for (int i = 0; i < bodies.length; i++) {
            if (this.equals(bodies[i]))
                continue;
            else
                TotalForce_X = TotalForce_X + this.calcForceExertedByX(bodies[i]);
        }
        return TotalForce_X;
    }

    public double calcNetForceExertedByY(Body[] bodies) {
        double TotalForce_Y = 0;
        for (int i = 0; i < bodies.length; i++) {
            if (this.equals(bodies[i]))
                continue;
            else
                TotalForce_Y = TotalForce_Y + this.calcForceExertedByY(bodies[i]);
        }
        return TotalForce_Y;
    }

    public void update(double dt, double fX, double fY) {
        double ax = fX / this.mass;
        double ay = fY / this.mass;
        this.xxVel = this.xxVel + dt * ax;
        this.yyVel = this.yyVel + dt * ay;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
