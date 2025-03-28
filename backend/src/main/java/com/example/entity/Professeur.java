// default package
// Generated 24 mars 2025, 21:05:34 by Hibernate Tools 5.1.2.Final


import java.util.HashSet;
import java.util.Set;

/**
 * Professeur generated by hbm2java
 */
public class Professeur  implements java.io.Serializable {


     private int id;
     private String nom;
     private String prenom;
     private Set affectationProfesseurs = new HashSet(0);

    public Professeur() {
    }

	
    public Professeur(int id, String nom, String prenom) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }
    public Professeur(int id, String nom, String prenom, Set affectationProfesseurs) {
       this.id = id;
       this.nom = nom;
       this.prenom = prenom;
       this.affectationProfesseurs = affectationProfesseurs;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getNom() {
        return this.nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return this.prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public Set getAffectationProfesseurs() {
        return this.affectationProfesseurs;
    }
    
    public void setAffectationProfesseurs(Set affectationProfesseurs) {
        this.affectationProfesseurs = affectationProfesseurs;
    }




}


