package entities;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author bini
 */
@Entity
@Table(name = "compte")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c")
    , @NamedQuery(name = "Compte.findByCompteId", query = "SELECT c FROM Compte c WHERE c.compteId = :compteId")
    , @NamedQuery(name = "Compte.findByNom", query = "SELECT c FROM Compte c WHERE c.nom = :nom")
    , @NamedQuery(name = "Compte.findByPrenom", query = "SELECT c FROM Compte c WHERE c.prenom = :prenom")
    , @NamedQuery(name = "Compte.findByMotDePasse", query = "SELECT c FROM Compte c WHERE c.motDePasse = :motDePasse")
    , @NamedQuery(name = "Compte.findByNumeroCompte", query = "SELECT c FROM Compte c WHERE c.numeroCompte = :numeroCompte")
    , @NamedQuery(name = "Compte.findBySolde", query = "SELECT c FROM Compte c WHERE c.solde = :solde")
    , @NamedQuery(name = "Compte.findByDateCreation", query = "SELECT c FROM Compte c WHERE c.dateCreation = :dateCreation")})
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "compte_id")
    private Integer compteId;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "mot_de_passe")
    private String motDePasse;
    @Basic(optional = false)
    @Column(name = "numero_compte")
    private String numeroCompte;
    @Basic(optional = false)
    @Column(name = "solde")
    private double solde;
    @Basic(optional = false)
    @Column(name = "date_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    public Compte() {
        this.numeroCompte =  "10"+ System.currentTimeMillis();
        this.dateCreation = new Date();
    }

    public Compte(String nom, String prenom, String motDePasse, double solde) {
        this.nom = nom;
        this.prenom = prenom;
        this.motDePasse = motDePasse;
        this.solde = solde;
        
        this.numeroCompte =  "10"+ System.currentTimeMillis();
        this.dateCreation = new Date();
    }

    public Integer getCompteId() {
        return compteId;
    }

    public void setCompteId(Integer compteId) {
        this.compteId = compteId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }
    
    public String getDateCreation() {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy à H:mm:ss");
        return date.format(dateCreation);
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compteId != null ? compteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        return !((this.compteId == null && other.compteId != null) || (this.compteId != null && !this.compteId.equals(other.compteId)));
    }

    @Override
    public String toString() {
        return "Compte{" + "compteId=" + compteId + ", nom=" + nom + ", prenom=" + prenom + ", motDePasse=" + motDePasse + ", numeroCompte=" + numeroCompte + ", solde=" + solde + ", dateCreation=" + dateCreation + '}';
    }
    
}
