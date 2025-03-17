package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Classe;

public class DaoClasse 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Classe> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Classe> recordings = new ArrayList<Classe>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM classe");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Classe>();

        do {
          Classe recording = new Classe();

          recording.setClasseId(results.getInt("classe_id"));
          
          recording.setNomClasse(results.getString("nom_classe"));
          recording.setNiveau(results.getString("niveau"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Classe>();
    }
  }

  public ArrayList<Classe> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Classe> recordings = new ArrayList<Classe>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM classe ORDER BY classe_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Classe>();

      do {
        Classe recording = new Classe();

        recording.setClasseId(results.getInt("classe_id"));
        
        recording.setNomClasse(results.getString("nom_classe"));
        recording.setNiveau(results.getString("niveau"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Classe>();
    }

  }

  public Classe getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM classe WHERE classe_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Classe recording = new Classe();

        if (noRecordingFound) return recording;

        recording.setClasseId(recordings.getInt("classe_id"));

        recording.setNomClasse(recordings.getString("nom_classe"));
        recording.setNiveau(recordings.getString("niveau"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Classe();
    }
  }

  public void save(Classe recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getClasseId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE classe SET nom_classe = ?, niveau = ? WHERE classe_id = ?"
      : "INSERT INTO classe(nom_classe,niveau) VALUES(?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setString(1, recording.getNomClasse());
        querySave.setString(2, recording.getNiveau());
        
        if (weEditRecording)
        querySave.setInt(3, recording.getClasseId());

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
      "DELETE FROM classe WHERE classe_id = ?"
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