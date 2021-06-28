public class NBody {
    public static double readRadius(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        double number = in.readDouble();
        double Radius = in.readDouble();
        return Radius;
    }

    public static Body[] readBodies(String planetsTxtPath) {
        In in = new In(planetsTxtPath);
        int number = in.readInt();
        double Radius = in.readDouble();
        Body[] bodies = new Body[number];
        /** draw the file and store value into the body class*/
        for (int i = 0; i < number; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String name = in.readString();
            bodies[i] = new Body(xp, yp, xv, yv, m, name);
        }
        return bodies;
    }

    public static void main(String[] arg) {
        double T = Double.parseDouble(arg[0]);
        double dt = Double.parseDouble(arg[1]);
        String filename = arg[2];
        double Radius = readRadius(filename);
        Body[] bodies = readBodies(filename);

        StdAudio.play("audio/2001.mid");
        double time = 0;
        while(time<T){
            double xForces[] = new double[5];
            double yForces[]= new double[5];
            for(int i=0; i< bodies.length; i++){
                xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForces[i], yForces[i]);
            }
            /** draw the background*/
            StdDraw.setScale(-Radius, Radius);
            StdDraw.clear();
            StdDraw.picture(0, 0, "/images/starfield.jpg");
            /**draw all the planets on the background */
            for(int i=0; i< bodies.length; i++){
                bodies[i].draw();
                StdDraw.enableDoubleBuffering();
            }
            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", Radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
