/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bureau;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kurasawa
 */
@Entity
@XmlRootElement
public class Lit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_lit;
    
    @Column
    private String chambre;
    
    @Column
    private Boolean occupe;

    public int getId_lit() {
        return id_lit;
    }

    public void setId_lit(int id_lit) {
        this.id_lit = id_lit;
    }

    public String getChambre() {
        return chambre;
    }

    public void setChambre(String chambre) {
        this.chambre = chambre;
    }

    public Boolean getOccupe() {
        return occupe;
    }

    public void setOccupe(Boolean occupe) {
        this.occupe = occupe;
    }

   
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id_lit;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lit)) {
            return false;
        }
        Lit other = (Lit) object;
        if (this.id_lit != other.id_lit) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.Lit[ id=" + id_lit + " ]";
    }
    
}
