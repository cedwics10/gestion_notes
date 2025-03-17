package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Matiere;

public class DaoMatiere 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Matiere> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Matiere> recordings = new ArrayList<Matiere>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM matiere");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Matiere>();

        do {
          Matiere recording = new Matiere();

          recording.setMatiereId(results.getInt("matiere_id"));
          
          recording.setNomMatiere(results.getString("nom_matiere"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Matiere>();
    }
  }

  public ArrayList<Matiere> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Matiere> recordings = new ArrayList<Matiere>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM matiere ORDER BY matiere_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Matiere>();

      do {
        Matiere recording = new Matiere();

        recording.setMatiereId(results.getInt("matiere_id"));
        
        recording.setNomMatiere(results.getString("nom_matiere"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Matiere>();
    }

  }

  public Matiere getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM matiere WHERE matiere_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Matiere recording = new Matiere();

        if (noRecordingFound) return recording;

        recording.setMatiereId(recordings.getInt("matiere_id"));

        recording.setNomMatiere(recordings.getString("nom_matiere"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Matiere();
    }
  }

  public void save(Matiere recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getMatiereId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE matiere SET nom_matiere = ? WHERE matiere_id = ?"
      : "INSERT INTO matiere(nom_matiere) VALUES(?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setString(1, recording.getNomMatiere());
        
        if (weEditRecording)
        querySave.setInt(2, recording.getMatiereId());

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
      "DELETE FROM matiere WHERE matiere_id = ?"
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