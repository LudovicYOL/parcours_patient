/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kurasawa
 */
@Entity
@XmlRootElement
public class Mouvement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_mouv;
    
    @Column
    private Date date_entree;
    
    @Column
    private Date date_sortie;
    
    @OneToOne
    private Admission admission;
    
    @OneToOne 
    private UniteFonctionnelle uf;
    
    @OneToOne 
    private Lit lit;

    public int getId_mouv() {
        return id_mouv;
    }

    public void setId_mouv(int id_mouv) {
        this.id_mouv = id_mouv;
    }

    public Date getDate_entree() {
        return date_entree;
    }

    public void setDate_entree(Date date_entree) {
        this.date_entree = date_entree;
    }

    public Date getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(Date date_sortie) {
        this.date_sortie = date_sortie;
    }

    public Admission getAdmission() {
        return admission;
    }

    public void setAdmission(Admission admission) {
        this.admission = admission;
    }

    public UniteFonctionnelle getUf() {
        return uf;
    }

    public void setUf(UniteFonctionnelle uf) {
        this.uf = uf;
    }

    public Lit getLit() {
        return lit;
    }

    public void setLit(Lit lit) {
        this.lit = lit;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_mouv;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouvement)) {
            return false;
        }
        Mouvement other = (Mouvement) object;
        if (this.id_mouv != other.id_mouv) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.Mouvement[ id=" + id_mouv + " ]";
    }
    
}
