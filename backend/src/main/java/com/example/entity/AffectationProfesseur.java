// default package
// Generated 24 mars 2025, 21:05:34 by Hibernate Tools 5.1.2.Final



/**
 * AffectationProfesseur generated by hbm2java
 */
public class AffectationProfesseur  implements java.io.Serializable {


     private int id;
     private Classe classe;
     private Matiere matiere;
     private Professeur professeur;
     private Integer trimestre;

    public AffectationProfesseur() {
    }

	
    public AffectationProfesseur(int id) {
        this.id = id;
    }
    public AffectationProfesseur(int id, Classe classe, Matiere matiere, Professeur professeur, Integer trimestre) {
       this.id = id;
       this.classe = classe;
       this.matiere = matiere;
       this.professeur = professeur;
       this.trimestre = trimestre;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Classe getClasse() {
        return this.classe;
    }
    
    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    public Matiere getMatiere() {
        return this.matiere;
    }
    
    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }
    public Professeur getProfesseur() {
        return this.professeur;
    }
    
    public void setProfesseur(Professeur professeur) {
        this.professeur = professeur;
    }
    public Integer getTrimestre() {
        return this.trimestre;
    }
    
    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }




}


