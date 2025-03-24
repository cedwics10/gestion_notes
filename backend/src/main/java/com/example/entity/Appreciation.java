// default package
// Generated 24 mars 2025, 21:05:34 by Hibernate Tools 5.1.2.Final



/**
 * Appreciation generated by hbm2java
 */
public class Appreciation  implements java.io.Serializable {


     private int id;
     private Eleve eleve;
     private Matiere matiere;
     private Integer trimestre;
     private String contenu;

    public Appreciation() {
    }

	
    public Appreciation(int id) {
        this.id = id;
    }
    public Appreciation(int id, Eleve eleve, Matiere matiere, Integer trimestre, String contenu) {
       this.id = id;
       this.eleve = eleve;
       this.matiere = matiere;
       this.trimestre = trimestre;
       this.contenu = contenu;
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
    public String getContenu() {
        return this.contenu;
    }
    
    public void setContenu(String contenu) {
        this.contenu = contenu;
    }




}


