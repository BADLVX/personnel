package testsUnitaires;

public class ExceptionDepart extends Exception{
     	public ExceptionDepart()
	    {
	        System.out.println("Exception ExceptionDepart has been raised...");
	    }
     	@Override
     	public String toString()
        {
          return "La date d'arrivee ne peut pas etre apres la date de depart ";
        }
	

}