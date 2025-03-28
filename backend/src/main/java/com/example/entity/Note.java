// default package
// Generated 24 mars 2025, 21:05:34 by Hibernate Tools 5.1.2.Final



/**
 * Note generated by hbm2java
 */
public class Note  implements java.io.Serializable {


     private int id;
     private Eleve eleve;
     private Matiere matiere;
     private Integer trimestre;
     private Double coefficient;
     private Double valeur;
     private String typeNote;

    public Note() {
    }

	
    public Note(int id) {
        this.id = id;
    }
    public Note(int id, Eleve eleve, Matiere matiere, Integer trimestre, Double coefficient, Double valeur, String typeNote) {
       this.id = id;
       this.eleve = eleve;
       this.matiere = matiere;
       this.trimestre = trimestre;
       this.coefficient = coefficient;
       this.valeur = valeur;
       this.typeNote = typeNote;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Eleve getEleve() {
        return this.eleve;
    }
    
    public void setEleve(Eleve eleve) {
        this.eleve = eleve;
    }
    public Matiere getMatiere() {
        return this.matiere;
    }
    
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public Integer getTrimestre() {
        return this.trimestre;
    }
    
    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }
    public Double getCoefficient() {
        return this.coefficient;
    }
    
    public void setCoefficient(Double coefficient) {
        this.coefficient = coefficient;
    }
    public Double getValeur() {
        return this.valeur;
    }
    
    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }
    public String getTypeNote() {
        return this.typeNote;
    }
    
    public void setTypeNote(String typeNote) {
        this.typeNote = typeNote;
    }




}


