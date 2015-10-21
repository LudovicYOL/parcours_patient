/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kurasawa
 */
@Entity
@XmlRootElement
public class UniteFonctionnelle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_uf;
    
    @Column
    private String nom;
    
    @Column
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Lit> lits;
    
    public int getId_uf() {
        return id_uf;
    }

    public void setId_uf(int id_uf) {
        this.id_uf = id_uf;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Lit> getLits() {
        return lits;
    }

    public void setLits(List<Lit> lits) {
        this.lits = lits;
    }
    
    
  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_uf;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UniteFonctionnelle)) {
            return false;
        }
        UniteFonctionnelle other = (UniteFonctionnelle) object;
        if (this.id_uf != other.id_uf) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.UniteFonctionnelle[ id=" + id_uf + " ]";
    }
    
}
