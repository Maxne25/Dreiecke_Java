public class Punkt{
  double x;
  double y;

  /**
    * Konstruktor eines Punktes.
    * @param x der x-Anteil des Punktes
    * @param y der y-Anteil des Punktes
    * @return den neuen Punkt
    */
  public Punkt(double x, double y) {
    this.x=x;
    this.y=y;
  }

  private Punkt() {
  }

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }
  
  /**
    * Berechnet die Entfernung zu einem anderen Punkt
    * @param p der andere Punkt
    * @return Abstand zum anderen Punkt
    */
  public double distanz(Punkt p) {
    return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
  }
  
  /**
    * Verschiebt den Punkt um einen Vektor
    * @param x der x-Anteil der Verschiebung
    * @param y der y-Anteil der Verschiebung
    * @return nichts
    */
  public void verschiebe(double x, double y) {
    this.x += x;
    this.y += y;
  }
  
  /**
    * Rotiert den Punkt um den Ursprung
    * @param winkel der Drehung (int: Grad-Zahl)
    * @return nichts
    */
  public void rotiere(int winkel){
    double sina = Math.sin( Math.toRadians( winkel ) ) ;
    double cosa = Math.cos( Math.toRadians( winkel ) ) ;
    double tempx = x*cosa - y*sina;
    y = x*sina + y*cosa;
    x = tempx;
  }

  /**
    * Rotiert den Punkt um den Ursprung
    * @param winkel der Drehung (double: rad Wert)
    * @return nichts
    */
  public void rotiere(double winkel){
    double sina = Math.sin( winkel ) ;
    double cosa = Math.cos( winkel ) ;
    double tempx = x*cosa - y*sina;
    y = x*sina + y*cosa;
    x = tempx;
  }

  private void eingabe() {
    System.out.print("Geben Sie die X-Koordinate ein: ");
    x = Double.parseDouble(System.console().readLine());
    System.out.print("Geben Sie die Y-Koordinate ein: ");
    y = Double.parseDouble(System.console().readLine());
  }

  private void ausgabe() {
    System.out.println("Punkt ("+x+"|"+y+")");
  }

  /**
    * Erzeugt eine textuelle Beschreibung der Form "(x|y)"
    * @return String mit den Koordinaten des Punktes
    */
  public String toString() {
    return "("+x+"|"+y+")";
  }

  public static void main(String[] args) {
    Punkt p1 = new Punkt();
    System.out.println("Geben Sie die Koordinaten eines Punktes ein.");
    p1.eingabe();
    System.out.println("Geben Sie die Koordinaten eines weiteren Punktes ein: ");
    Punkt p2 = new Punkt();
    p2.eingabe();
    double dist = p1.distanz(p2);
    System.out.print("Der erste Punkt hat eine Entfernung von ");
    System.out.println(dist + " zum zweiten Punkt.");
  }

}
