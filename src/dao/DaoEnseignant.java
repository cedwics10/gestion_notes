package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Enseignant;

public class DaoEnseignant 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Enseignant> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Enseignant> recordings = new ArrayList<Enseignant>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM enseignant");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Enseignant>();

        do {
          Enseignant recording = new Enseignant();

          recording.setEnseignantId(results.getInt("enseignant_id"));
          
          recording.setNom(results.getString("nom"));
          recording.setPrenom(results.getString("prenom"));
          recording.setTelephone(results.getString("telephone"));
          recording.setEmail(results.getString("email"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Enseignant>();
    }
  }

  public ArrayList<Enseignant> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Enseignant> recordings = new ArrayList<Enseignant>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM enseignant ORDER BY enseignant_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Enseignant>();

      do {
        Enseignant recording = new Enseignant();

        recording.setEnseignantId(results.getInt("enseignant_id"));
        
        recording.setNom(results.getString("nom"));
        recording.setPrenom(results.getString("prenom"));
        recording.setTelephone(results.getString("telephone"));
        recording.setEmail(results.getString("email"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Enseignant>();
    }

  }

  public Enseignant getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM enseignant WHERE enseignant_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Enseignant recording = new Enseignant();

        if (noRecordingFound) return recording;

        recording.setEnseignantId(recordings.getInt("enseignant_id"));

        recording.setNom(recordings.getString("nom"));
        recording.setPrenom(recordings.getString("prenom"));
        recording.setTelephone(recordings.getString("telephone"));
        recording.setEmail(recordings.getString("email"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Enseignant();
    }
  }

  public void save(Enseignant recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getEnseignantId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE enseignant SET nom = ?, prenom = ?, telephone = ?, email = ? WHERE enseignant_id = ?"
      : "INSERT INTO enseignant(nom,prenom,telephone,email) VALUES(?,?,?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setString(1, recording.getNom());
        querySave.setString(2, recording.getPrenom());
        querySave.setString(3, recording.getTelephone());
        querySave.setString(4, recording.getEmail());
        
        if (weEditRecording)
        querySave.setInt(5, recording.getEnseignantId());

      querySave.executeUpdate();
    }
    catch(SQLException e)
    {
      System.out.println("Error : " + e.getMessage());
    }
  }

  public void delete(int id) throws SQLException {
    Connection connectionDatabase = Database.getConnection();



    try 
    {

      PreparedStatement query = connectionDatabase.prepareStatement(
      "DELETE FROM enseignant WHERE enseignant_id = ?"
      );

       query.setInt(1, id); 

      query.executeUpdate();
    }
    catch(SQLException e)
    {
      System.out.println("Error : " + e.getMessage());
    }

  }
}