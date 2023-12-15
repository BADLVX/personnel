package commandLine;

import static commandLineMenus.rendering.examples.util.InOut.getString;
import java.time.LocalDate;
	
import personnel.SauvegardeImpossible;
import commandLineMenus.ListOption;
import commandLineMenus.Menu;
import commandLineMenus.Option;
import personnel.Employe;	
import personnel.ExceptionArrivee;
import personnel.ExceptionDepart;
import personnel.Ligue;
import personnel.GestionPersonnel;

public class EmployeConsole 
{
	private Option afficher(final Employe employe)
	{
		return new Option("Afficher l'employé", "l", () -> {System.out.println(employe);});
	}

	ListOption<Employe> editerEmploye()
	{
		return (employe) -> editerEmploye(employe);		
	}

	Option editerEmploye(Employe employe)
	{
			Menu menu = new Menu("Gérer le compte " + employe.getNom(), "c");
			menu.add(afficher(employe));
			/*menu.add(changerNom(employe));
			menu.add(changerPrenom(employe));
			menu.add(changerMail(employe));
			menu.add(changerPassword(employe)); */
		
			//ajout des options pour modifier et supprimer un employé
			menu.add(modifierEmploye(employe));
			menu.add(GererLesDates(employe));
			menu.add(supprimerEmploye(employe));
			menu.addBack("q");
			return menu;
	}
	
	//ajout d'un menu pour modifier les informations d'un employé
	Option modifierEmploye(Employe employe) {
		Menu menu = new Menu("Modifier l'employé " + employe.getNom(), "m");
		menu.add(changerNom(employe));
		menu.add(changerPrenom(employe));
		menu.add(changerMail(employe));
		menu.add(changerPassword(employe));
	//	menu.add(enregistrerModifications(employe));
		menu.addBack("q");
		return menu;
	}
	
	//ajout d'une option pour supprimer un employe
	private Option supprimerEmploye(Employe employe)
	{
		return new Option("Supprimer", "d", () -> {employe.remove();});
	}

	private Option changerNom(final Employe employe)
	{
		return new Option("Changer le nom", "n", 
				() -> {employe.setNom(getString("Nouveau nom : "));}
			);
	}
	
	private Option changerPrenom(final Employe employe)
	{
		return new Option("Changer le prénom", "p", () -> {employe.setPrenom(getString("Nouveau prénom : "));});
	}
	
	private Option changerMail(final Employe employe)
	{
		return new Option("Changer le mail", "e", () -> {employe.setMail(getString("Nouveau mail : "));});
	}
	
	private Option changerPassword(final Employe employe)
	{
		return new Option("Changer le password", "x", () -> {employe.setPassword(getString("Nouveau password : "));});
	}
	
	//private Option enregistrerModifications(final Employe employe) throws SauvegardeImpossible {
	//	return new Option("Enregistrer les modifications effectuées", "e", () -> {employe.getGestionPersonnel().update(employe);} );
	//}
	
	Option GererLesDates(Employe employe)
	{
		Menu menu = new Menu ("Gérer les dates de l'employé " + employe.getNom(), "g" );
		menu.add(AfficherDates(employe));
		menu.add(ChangerDateArrivee(employe));
		menu.add(ChangerDateDepart(employe));
		menu.addBack("q");
		return menu;
	}
	
	private Option AfficherDates(Employe employe) {
		return new Option("Afficher les dates de l'employé " + employe.getNom(), "a", ()-> {System.out.println("La date d'arrivée est : " + employe.getDateArrivee() + "\n" + "La Date de départ(null si toujours employé) est : " + employe.getDateDepart());});
	}
	
	private Option ChangerDateArrivee(Employe employe) {
		
			return new Option("Changer la date d'arrivée :", "r", () -> {
				try {
				employe.setDateArrivee(LocalDate.parse( getString("Entrez la date de départ au format aaaa-mm-jj:") ) );
				} catch (ExceptionArrivee e) {
					System.out.println(e);
				} });


	}
	
	private Option ChangerDateDepart(Employe employe) {

		return new Option("Changer la date de départ :", "d", () -> {
			try {
			employe.setDateDepart(LocalDate.parse( getString("Entrez la date d'arrivée au format aaaa-mm-jj:") ) );
			}
			catch (ExceptionDepart e) {
				System.out.println(e);
			}
		});
	}

}
