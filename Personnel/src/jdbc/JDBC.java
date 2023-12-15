package jdbc;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.sql.Date;

import personnel.*;

public class JDBC implements Passerelle 
{
	Connection connection;

	public JDBC()
	{
		try
		{
			Class.forName(Credentials.getDriverClassName());
			connection = DriverManager.getConnection(Credentials.getUrl(), Credentials.getUser(), Credentials.getPassword());
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Pilote JDBC non installé.");
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
	}
	
	@Override
	public GestionPersonnel getGestionPersonnel() 
	{
		GestionPersonnel gestionPersonnel = new GestionPersonnel();
		try 
		{
			String requete = "select * from ligue";
			Statement instruction = connection.createStatement();
			ResultSet ligues = instruction.executeQuery(requete);
			while (ligues.next())
				gestionPersonnel.addLigue(ligues.getInt(1), ligues.getString(2));
			
		}
		catch (SQLException e)
		{
			System.out.println(e);
		}
		return gestionPersonnel;
	}

	@Override
	public void sauvegarderGestionPersonnel(GestionPersonnel gestionPersonnel) throws SauvegardeImpossible 
	{
		close();
	}
	
	public void close() throws SauvegardeImpossible
	{
		try
		{
			if (connection != null)
				connection.close();
		}
		catch (SQLException e)
		{
			throw new SauvegardeImpossible(e);
		}
	}
	// Modifier l'employé :Nicolas
	// Supprimer Ligue, Supprimer Employé : Hugo
	// Ajouter un employé, Modifier Ligue : Alex
	// Changer le type de l'ID ligue et employé pour un int auto incrémenté
	@Override
	public int insert(Ligue ligue) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("insert into ligue (Nom_Ligue) values(?)", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, ligue.getNom());		
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
	
	@Override
	public int insert(Employe employe) throws SauvegardeImpossible 
	{
		try {
			System.out.println("HELLLO STP FONCTIONNE");
			Date dateArriveeSQL = Date.valueOf(employe.getDateArrivee());
			Date dateDepartSQL = Date.valueOf(employe.getDateDepart());
			PreparedStatement instruction;
			System.out.println("HELLLO STP FONCTIONNE");
			instruction = connection.prepareStatement("insert into employe (Nom, Prenom, Mdp, Date_Arrivee, Date_Depart, Mail) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			System.out.println("HELLLO STP FONCTIONNE");
			instruction.setString(1, employe.getNom());
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setDate(4, dateArriveeSQL);
			instruction.setDate(5, dateDepartSQL);
			instruction.setString(6, employe.getMail());
			instruction.executeUpdate();
			System.out.println("HELLLO STP FONCTIONNE");
			ResultSet id = instruction.getGeneratedKeys();
			System.out.println("HELLLO STP FONCTIONNE");
			id.next();
			return id.getInt(1);
		 }
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}
	}	
	
	@Override
	public int update(Employe employe) throws SauvegardeImpossible 
	{
		try 
		{
			PreparedStatement instruction;
			instruction = connection.prepareStatement("update utilisateur set Nom = ?, Prenom = ?, Mdp = ?, Date_Arrivee = ?, Date_Depart = ?, Mail = ?  where User_Id = ? ", Statement.RETURN_GENERATED_KEYS);
			instruction.setString(1, employe.getNom()); 
			instruction.setString(2, employe.getPrenom());
			instruction.setString(3, employe.getPassword());
			instruction.setString(4, employe.getDateArrivee().toString());
			instruction.setString(5, employe.getDateDepart().toString());
			instruction.executeUpdate();
			ResultSet id = instruction.getGeneratedKeys();
			id.next();
			return id.getInt(1);
		} 
		catch (SQLException exception) 
		{
			exception.printStackTrace();
			throw new SauvegardeImpossible(exception);
		}		
	}
}
	
