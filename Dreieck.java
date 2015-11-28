public class Dreieck()
{

  Punkt ecke1 = new Punkt(0,0);
  Punkt ecke2 = new Punkt();
  Punkt ecke3 = new Punkt();
  
  public Dreieck new Dreieck(double x1, double y1, double x2, double y2)
  {
    if( (y1-y2)*0 + (-(x1-x2) * 0 )==(y1-y2)*x1 + (-(x1-x2)*y1)){
       Utils.error("Die Punkte liegen auf einer Graden, Kein Dreieck möglich!")
       return null;
    }else{

    //?
    this.ecke2 = (x1,y1);
    this.ecke3 = (x2,y2);
    }
  }

  public Dreieck copy(Dreieck toCopy)

   {
    //Punkte von toCopy.ecke2 und 3 auslesen
    
    return new Dreieck(x1,x2,y1,y2);    


   }

}
