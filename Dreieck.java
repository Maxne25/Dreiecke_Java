public class Dreieck {

    private final Punkt ecke3 = new Punkt(0, 0);
    private Punkt ecke1, ecke2;

    public static void main(String[] args) {
        System.out.println(newDreieck(0, 1, 0, 0));
    }

    /**
     * Erstellt eine neue Instanz vom Typ Dreieck mit den gegebenen Koordinaten
     *
     * @param x1 die X Koordinate der Ecke 1
     * @param y1 die Y Koordinate der Ecke 1
     * @param x2 die X Koordinate der Ecke 2
     * @param y2 die Y Koordinate der Ecke 2
     * @return neues Dreieck mit den gegebenen Koordinaten
     */
    public static Dreieck newDreieck(double x1, double y1, double x2, double y2) {
        if ((x1 == 0 && x2 == 0) || (y1 == 0 && y2 == 0) || x1 / y1 == x2 / y2) {
            Utils.error("Die Punkte dürfen nicht auf einer Geraden liegen.");
            return null;
        }

        return new Dreieck(new Punkt(x1, y1), new Punkt(x2, y2));
    }

    /**
     * Erstellt eine neue Instanz vom Typ Dreieck mit den selben Daten wie das übergebene Dreieck
     *
     * @param toCopy das Dreieck welches kopiert werden soll
     * @return ein neues Dreieck mit den gleichen Eckpunkten
     */
    public static Dreieck copy(Dreieck toCopy) {
        Punkt ce1 = new Punkt(toCopy.getEcke1().getX(), toCopy.getEcke1().getY());
        Punkt ce2 = new Punkt(toCopy.getEcke2().getX(), toCopy.getEcke2().getY());

        return new Dreieck(ce1, ce2);
    }

    /**
     * Berechnet den Gesamtumfang der übergebenen Dreiecke
     *
     * @param dreiecke Die zu verwendenen Dreiecke
     * @return der Umfang
     */
    public static double perimeter(Dreieck... dreiecke) {
        double perimeter = 0;

        for (Dreieck d : dreiecke) {
            perimeter += d.getEcke3().distanz(d.getEcke2())
                    + d.getEcke3().distanz(d.getEcke1())
                    + d.ecke1.distanz(d.getEcke2());
        }

        return perimeter;
    }

    private Dreieck(Punkt ecke1, Punkt ecke2) {
        this.ecke1 = ecke1;
        this.ecke2 = ecke2;
    }

    /**
     * Gibt das Dreieck als String zurück
     *
     * @return Dreieck als String
     */
    @Override
    public String toString() {
        return toString(false);
    }

    /**
     * Gibt den Inkreis des Dreiecks zurück
     *
     * @return Inkreis des Dreiecks
     */
    public Kreis inkreis() {
        double s = perimeter(this);
        double a = getEcke2().distanz(getEcke3());
        double b = getEcke1().distanz(getEcke3());
        double c = getEcke1().distanz(getEcke2());

        double x1 = getEcke1().getX();
        double x2 = getEcke2().getX();
        double y1 = getEcke1().getY();
        double y2 = getEcke2().getY();

        double x = (a * x1 + b * x2) / s;
        double y = (a * y1 + b * y2) / s;

        double r = (2 * area()) / (a + b + c);

        return Kreis.newKreis(x, y, r);
    }

    /**
     * Führt eine Aktion auf dem Dreieck aus
     *
     * @param action Auszuführende Aktion
     */
    public void performAction(DreieckAction action) {
        switch (action) {
            case ROTATE_LEFT:
                rotiere(-Utils.PI / 18);
                break;
            case ROTATE_RIGHT:
                rotiere(Utils.PI / 18);
                break;
            case NARROW:
                movePointPercentage(getEcke1(), getEcke1().getX() < getEcke2().getX() ? 10 : -10, 0);
                movePointPercentage(getEcke2(), getEcke2().getX() < getEcke2().getX() ? 10 : -10, 0);
                break;
            case WIDEN:
                movePointPercentage(getEcke1(), getEcke1().getX() < getEcke2().getX() ? -10 : 10, 0);
                movePointPercentage(getEcke2(), getEcke2().getX() < getEcke2().getX() ? -10 : 10, 0);
                break;
            case ENLARGE:
                movePointPercentage(getEcke1(), 10, 10);
                movePointPercentage(getEcke2(), 10, 10);
                break;
            case SHRINK:
                if (area() >= 200) {
                    movePointPercentage(getEcke1(), -10, -10);
                    movePointPercentage(getEcke2(), -10, -10);
                }
        }
    }

    /**
     * Rotiert das Dreieck um die angegebenen Winkel
     *
     * @param angles Zu rotierende Winkel
     * @return Dreieck 
     */
    public Dreieck rotiere(double... angles) {
        for (double angle : angles) {
            getEcke1().rotiere(angle);
            getEcke2().rotiere(angle);
        }

        return this;
    }

    /**
     * @return Die Ecke 1 des Dreiecks
     */
    public Punkt getEcke1() {
        return ecke1;
    }

    /**
     * @return Die Ecke 2 des Dreiecks
     */
    public Punkt getEcke2() {
        return ecke2;
    }

    /**
     * @return Die Ecke 3 des Dreiecks
     */
    public Punkt getEcke3() {
        return ecke3;
    }

    private String toString(boolean doubleY) {
        StringBuilder builder = new StringBuilder();

        double x1 = getEcke1().getX();
        double x2 = getEcke2().getX();
        double x3 = getEcke3().getX();
        double y1 = getEcke1().getY();
        double y2 = getEcke2().getY();
        double y3 = getEcke3().getY();

        double maxX = Utils.max(x1, x2, x3);
        double maxY = Utils.max(y1, y2, y3);
        double minX = Utils.min(x1, x2, x3);
        double minY = Utils.min(y1, y2, y3);

        for (double y = minY; y <= maxY; y += (doubleY ? 2 : 1)) {
            for (double x = minX; x <= maxX; x++) {
                if (inDreieck(new Punkt(x, y))) {
                    builder.append('#');
                } else {
                    builder.append('_');
                }
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    private boolean inDreieck(Punkt p) {
        double A = area();
        double A1 = area(p, getEcke2(), getEcke3());
        double A2 = area(getEcke1(), p, getEcke3());
        double A3 = area(getEcke1(), getEcke2(), p);

        return (A == A1 + A2 + A3);
    }

    private double area() {
        return area(getEcke1(), getEcke2(), getEcke3());
    }

    private double area(Punkt p1, Punkt p2, Punkt p3) {
        return Utils.abs((p1.getX() * (p2.getY() - p3.getY())
                + p2.getX() * (p3.getY() - p1.getY())
                + p3.getX() * (p1.getY() - p2.getY())) / 2d);
    }

    private void movePointPercentage(Punkt p, int percentX, int percentY) {
        double cx = p.getX() * (percentX / 100d);
        double cy = p.getY() * (percentY / 100d);

        p.verschiebe(cx, cy);
    }
}
