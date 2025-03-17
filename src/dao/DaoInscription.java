package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Inscription;

public class DaoInscription 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Inscription> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Inscription> recordings = new ArrayList<Inscription>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM inscription");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Inscription>();

        do {
          Inscription recording = new Inscription();

          recording.setInscriptionId(results.getInt("inscription_id"));
          
          recording.setEleveId(results.getInt("eleve_id"));
          recording.setClasseId(results.getInt("classe_id"));
          recording.setDateInscription(results.getString("date_inscription"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Inscription>();
    }
  }

  public ArrayList<Inscription> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Inscription> recordings = new ArrayList<Inscription>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM inscription ORDER BY inscription_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Inscription>();

      do {
        Inscription recording = new Inscription();

        recording.setInscriptionId(results.getInt("inscription_id"));
        
        recording.setEleveId(results.getInt("eleve_id"));
        recording.setClasseId(results.getInt("classe_id"));
        recording.setDateInscription(results.getString("date_inscription"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Inscription>();
    }

  }

  public Inscription getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM inscription WHERE inscription_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Inscription recording = new Inscription();

        if (noRecordingFound) return recording;

        recording.setInscriptionId(recordings.getInt("inscription_id"));

        recording.setEleveId(recordings.getInt("eleve_id"));
        recording.setClasseId(recordings.getInt("classe_id"));
        recording.setDateInscription(recordings.getString("date_inscription"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Inscription();
    }
  }

  public void save(Inscription recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getInscriptionId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE inscription SET eleve_id = ?, classe_id = ?, date_inscription = ? WHERE inscription_id = ?"
      : "INSERT INTO inscription(eleve_id,classe_id,date_inscription) VALUES(?,?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setInt(1, recording.getEleveId());
        querySave.setInt(2, recording.getClasseId());
        querySave.setString(3, recording.getDateInscription());
        
        if (weEditRecording)
        querySave.setInt(4, recording.getInscriptionId());

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
      "DELETE FROM inscription WHERE inscription_id = ?"
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