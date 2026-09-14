import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

    public static double readRadius(String fname) {
        try {
            Scanner scan = new Scanner(new File(fname));
            int numPlanets = scan.nextInt();
            double radius = scan.nextDouble();
            scan.close();
            return radius;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file");
            System.exit(10000001);
        }
        return 0.0;
    }

    public static Planet[] readPlanets(String fname) {
        try {
            Scanner scan = new Scanner(new File(fname));
            int numPlanets = scan.nextInt();
            double trash = scan.nextDouble();

            Planet[] planets = new Planet[numPlanets];
            for (int i = 0; i < numPlanets; ++i) {
                double xPos, yPos, xVel, yVel, mass;
                String imgName;
                xPos = scan.nextDouble();
                yPos = scan.nextDouble();
                xVel = scan.nextDouble();
                yVel = scan.nextDouble();
                mass = scan.nextDouble();
                imgName = scan.next();
                Planet current = new Planet(xPos, yPos, xVel, yVel, mass, imgName);
                planets[i] = current;
            }
            scan.close();
            return planets;
        } catch (FileNotFoundException e) {
            System.out.println("Couldn't find the file");
            System.exit(10000001);
        }
        return new Planet[0];
    }

    public static void main(String[] args) {
        double time = 0.0;
        double totalTime = 157788000.0;
        double dt = 25000.0;
        String pfile = "data/planets.txt";
        if (args.length > 2) {
            totalTime = Double.parseDouble(args[0]);
            dt = Double.parseDouble(args[1]);
            pfile = args[2];
        }

        String fname = "./data/planets.txt";


		Planet[] planets = readPlanets(fname); // readPlanets(fname);


        double radius = readRadius(fname); // readRadius(fname);


		System.out.printf("%d\n", planets.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
		    System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
		   		              planets[i].myXPos, planets[i].myYPos,
		                      planets[i].myXVel, planets[i].myYVel,
		                      planets[i].myMass, planets[i].myFileName);
		}

        StdDraw.setScale(-radius, radius);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for (int i = 0; i < planets.length; ++i) {
            planets[i].draw();
        }

        for (double t = 0.0; t <= totalTime; t += dt) {
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            for (int i = 0; i < planets.length; ++i) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; ++i) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (int i = 0; i < planets.length; ++i) {
                planets[i].draw();
            }
            StdDraw.show(10);
        }

        System.out.printf("%d\n", planets.length);
        System.out.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].myXPos, planets[i].myYPos,
                    planets[i].myXVel, planets[i].myYVel,
                    planets[i].myMass, planets[i].myFileName);
        }
    }
}
