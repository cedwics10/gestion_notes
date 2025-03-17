package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import dao.Note;

public class DaoNote 
{
  int ITEM_PER_PAGE = 20;

  public ArrayList<Note> getAll() throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Note> recordings = new ArrayList<Note>();

    try {    
        Statement queryAll = connectionDatabase.createStatement();

        ResultSet results = queryAll.executeQuery("SELECT * FROM note");

        boolean noResultExists = !results.next();

        if (noResultExists)
          return new ArrayList<Note>();

        do {
          Note recording = new Note();

          recording.setNoteId(results.getInt("note_id"));
          
          recording.setEleveId(results.getInt("eleve_id"));
          recording.setMatiereId(results.getInt("matiere_id"));
          recording.setNote(results.getDouble("note"));
          recording.setDateEval(results.getString("date_eval"));
          
          recordings.add(recording);
        } while (results.next());
        return recordings;

    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Note>();
    }
  }

  public ArrayList<Note> getPage(int page) throws SQLException
  {
    Database.makeConnection();
    Connection connectionDatabase = Database.getConnection();

    ArrayList<Note> recordings = new ArrayList<Note>();

    try
    {
      Statement queryAll = connectionDatabase.createStatement();
      ResultSet results = queryAll.executeQuery("SELECT * FROM note ORDER BY note_id ASC LIMIT " + (page * ITEM_PER_PAGE) + ", " + ITEM_PER_PAGE);

      boolean noResultExists = !results.next();

      if (noResultExists)
        return new ArrayList<Note>();

      do {
        Note recording = new Note();

        recording.setNoteId(results.getInt("note_id"));
        
        recording.setEleveId(results.getInt("eleve_id"));
        recording.setMatiereId(results.getInt("matiere_id"));
        recording.setNote(results.getDouble("note"));
        recording.setDateEval(results.getString("date_eval"));
        
        recordings.add(recording);
      } while (results.next());
      return recordings;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new ArrayList<Note>();
    }

  }

  public Note getById(int id) throws SQLException {

    Connection connectionDatabase = Database.getConnection();

    try
    {
        PreparedStatement queryById = connectionDatabase.prepareStatement(
          "SELECT * FROM note WHERE note_id = ?"
        );
    
        queryById.setInt(1, id);

        ResultSet recordings = queryById.executeQuery();

        boolean noRecordingFound = !recordings.next();

        Note recording = new Note();

        if (noRecordingFound) return recording;

        recording.setNoteId(recordings.getInt("note_id"));

        recording.setEleveId(recordings.getInt("eleve_id"));
        recording.setMatiereId(recordings.getInt("matiere_id"));
        recording.setNote(recordings.getDouble("note"));
        recording.setDateEval(recordings.getString("date_eval"));
        
        return recording;
    }
    catch(SQLException e)
    {
        System.out.println("Error : " + e.getMessage());
        return new Note();
    }
  }

  public void save(Note recording) throws SQLException {
    Connection connectionDatabase = Database.getConnection();

    boolean weEditRecording = recording.getNoteId() != 0;

    String queryToExecute = weEditRecording
      ? "UPDATE note SET eleve_id = ?, matiere_id = ?, note = ?, date_eval = ? WHERE note_id = ?"
      : "INSERT INTO note(eleve_id,matiere_id,note,date_eval) VALUES(?,?,?,?)";

    try
    {

        PreparedStatement querySave = connectionDatabase.prepareStatement(queryToExecute);
        querySave.setInt(1, recording.getEleveId());
        querySave.setInt(2, recording.getMatiereId());
        querySave.setDouble(3, recording.getNote());
        querySave.setString(4, recording.getDateEval());
        
        if (weEditRecording)
        querySave.setInt(5, recording.getNoteId());

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
      "DELETE FROM note WHERE note_id = ?"
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