package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Eleve;

public class DaoEleve 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Eleve> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Eleve> recordings = new ArrayList<Eleve>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM eleve");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Eleve>();

        do {
          Eleve recording = new Eleve();

          recording.setEleveId(results.getInt("eleve_id"));
          
          recording.setNom(results.getString("nom"));
          recording.setPrenom(results.getString("prenom"));
          recording.setDateNaissance(results.getString("date_naissance"));
          recording.setAdresse(results.getString("adresse"));
          recording.setTelephone(results.getString("telephone"));
          recording.setEmail(results.getString("email"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Eleve>();
    }
  }

  public ArrayList<Eleve> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Eleve> recordings = new ArrayList<Eleve>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM eleve ORDER BY eleve_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Eleve>();

      do {
        Eleve recording = new Eleve();

        recording.setEleveId(results.getInt("eleve_id"));
        
        recording.setNom(results.getString("nom"));
        recording.setPrenom(results.getString("prenom"));
        recording.setDateNaissance(results.getString("date_naissance"));
        recording.setAdresse(results.getString("adresse"));
        recording.setTelephone(results.getString("telephone"));
        recording.setEmail(results.getString("email"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Eleve>();
    }

  }

  public Eleve getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM eleve WHERE eleve_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Eleve recording = new Eleve();

        if (noRecordingFound) return recording;

        recording.setEleveId(recordings.getInt("eleve_id"));

        recording.setNom(recordings.getString("nom"));
        recording.setPrenom(recordings.getString("prenom"));
        recording.setDateNaissance(recordings.getString("date_naissance"));
        recording.setAdresse(recordings.getString("adresse"));
        recording.setTelephone(recordings.getString("telephone"));
        recording.setEmail(recordings.getString("email"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Eleve();
    }
  }

  public void save(Eleve recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getEleveId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE eleve SET nom = ?, prenom = ?, date_naissance = ?, adresse = ?, telephone = ?, email = ? WHERE eleve_id = ?"
      : "INSERT INTO eleve(nom,prenom,date_naissance,adresse,telephone,email) VALUES(?,?,?,?,?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setString(1, recording.getNom());
        querySave.setString(2, recording.getPrenom());
        querySave.setString(3, recording.getDateNaissance());
        querySave.setString(4, recording.getAdresse());
        querySave.setString(5, recording.getTelephone());
        querySave.setString(6, recording.getEmail());
        
        if (weEditRecording)
        querySave.setInt(7, recording.getEleveId());

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
      "DELETE FROM eleve WHERE eleve_id = ?"
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