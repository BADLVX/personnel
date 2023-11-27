package testsUnitaires;

public class ExceptionArrivee extends Exception {
     	public ExceptionArrivee()
	    {
	        System.out.println("Exception ExceptionArrivee has been raised...");
	    }
     	@Override
     	public String toString()
        {
          return "La date depart ne peut pas etre avant la date d'arrivee ";
        }
	

}
