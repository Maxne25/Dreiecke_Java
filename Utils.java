import java.awt.*;

public class Utils {

    private Utils(){}

    /**
     * die Konstante Pi
     */
    public static double PI = Math.PI;

    /**
     * Berechnet den Abstand zwischen den Punkten (xa,ya) und (xb,yb).
     * @param xa der x-Anteil des ersten Punktes
     * @param ya der y-Anteil des ersten Punktes
     * @param xb der x-Anteil des zweiten Punktes
     * @param yb der y-Anteil des zweiten Punktes
     * @return den Abstand der beiden Punkte
     */
    public static double dist(int xa, int ya, int xb, int yb) {
        return new Point(xa, ya).distance(new Point(xb, yb));
    }

    /**
     * Gibt eine Fehlermeldung aus.
     * @param msg die Fehlermeldung, die ausgegeben werden soll
     */
    public static void error(String msg) {
        System.err.println(msg);
    }

    /**
     * Berechnet den Betrag einer Zahl
     * @param value Zahl deren Betrag berechnet werden soll.
     */
    public static double abs(double value) {
        return Math.abs(value);
    }

    /**
     * Berechnet das Maximum der Argumente.
     * @param args die Werte, deren Maximum bestimmt werden soll
     * @return den maximalen Wert aller Argumente
     */
    public static int max(int... args) {
        if (args.length == 0) {
            error("Called max without arguments!");
        }
        int res = args[0];
        for (int i = 1; i < args.length; i++) {
            res = Math.max(res, args[i]);
        }
        return res;
    }

    public static double max(double... args) {
        if (args.length == 0) {
            error("Called max without arguments!");
        }
        double res = args[0];
        for (int i = 1; i < args.length; i++) {
            res = Math.max(res, args[i]);
        }
        return res;
    }

    /**
     * Berechnet das Minimum der Argumente.
     * @param args die Werte, deren Minimum bestimmt werden soll
     * @return den minimalen Wert aller Argumente
     */
    public static int min(int... args) {
        if (args.length == 0) {
            error("Called min without arguments!");
        }
        int res = args[0];
        for (int i = 1; i < args.length; i++) {
            res = Math.min(res, args[i]);
        }
        return res;
    }
    public static double min(double... args) {
        if (args.length == 0) {
            error("Called min without arguments!");
        }
        double res = args[0];
        for (int i = 1; i < args.length; i++) {
            res = Math.min(res, args[i]);
        }
        return res;
    }
}
