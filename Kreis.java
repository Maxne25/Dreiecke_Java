/**
 * Ein Objekt der Klasse Kreis repraesentiert einen Kreis.
 */
public class Kreis {

    private Punkt mittelpunkt;
    private double radius;

    /**
     * Erzeugt einen neuen Kreis.
     * @param x der x-Anteil des Mittelpunktes
     * @param y der y-Anteil des Mittelpunktes
     * @param radius der nicht negative Radius
     * @return den neuen Kreis  
     */
    public static Kreis newKreis(double x, double y, double radius) {
        if (radius < 0) {
            Utils.error("Trying to create kreis with negative radius " + radius + "!");
            return null;
        }
        Kreis res = new Kreis();
        res.mittelpunkt = new Punkt(x,y);
        res.radius = radius;
        return res;
    }

    /**
     * Liefert den Radius dieses Kreises.
     * @return den Radius dieses Kreises
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Liefert den x-Anteil des Mittelpunktes dieses Kreises.
     * @return den x-Anteil des Mittelpunktes dieses Kreises
     */
    public double getX() {
        return mittelpunkt.getX();
    }

    /**
     * Liefert den y-Anteil des Mittelpunktes dieses Kreises.
     * @return den y-Anteil des Mittelpunktes dieses Kreises
     */
    public double getY() {
        return mittelpunkt.getY();
    }

}
