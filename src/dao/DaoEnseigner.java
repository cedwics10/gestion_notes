package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Enseigner;

public class DaoEnseigner 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Enseigner> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Enseigner> recordings = new ArrayList<Enseigner>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM enseigner");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Enseigner>();

        do {
          Enseigner recording = new Enseigner();

          recording.setEnseignerId(results.getInt("enseigner_id"));
          
          recording.setEnseignantId(results.getInt("enseignant_id"));
          recording.setMatiereId(results.getInt("matiere_id"));
          recording.setClasseId(results.getInt("classe_id"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Enseigner>();
    }
  }

  public ArrayList<Enseigner> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Enseigner> recordings = new ArrayList<Enseigner>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM enseigner ORDER BY enseigner_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Enseigner>();

      do {
        Enseigner recording = new Enseigner();

        recording.setEnseignerId(results.getInt("enseigner_id"));
        
        recording.setEnseignantId(results.getInt("enseignant_id"));
        recording.setMatiereId(results.getInt("matiere_id"));
        recording.setClasseId(results.getInt("classe_id"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Enseigner>();
    }

  }

  public Enseigner getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM enseigner WHERE enseigner_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Enseigner recording = new Enseigner();

        if (noRecordingFound) return recording;

        recording.setEnseignerId(recordings.getInt("enseigner_id"));

        recording.setEnseignantId(recordings.getInt("enseignant_id"));
        recording.setMatiereId(recordings.getInt("matiere_id"));
        recording.setClasseId(recordings.getInt("classe_id"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Enseigner();
    }
  }

  public void save(Enseigner recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getEnseignerId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE enseigner SET enseignant_id = ?, matiere_id = ?, classe_id = ? WHERE enseigner_id = ?"
      : "INSERT INTO enseigner(enseignant_id,matiere_id,classe_id) VALUES(?,?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setInt(1, recording.getEnseignantId());
        querySave.setInt(2, recording.getMatiereId());
        querySave.setInt(3, recording.getClasseId());
        
        if (weEditRecording)
        querySave.setInt(4, recording.getEnseignerId());

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
      "DELETE FROM enseigner WHERE enseigner_id = ?"
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