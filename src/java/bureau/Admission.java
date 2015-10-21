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
public class Admission implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int iep;
    
    @Column
    private int ipp;
    
    @Column
    private int type;
    // 1 pour hospitalisation progr, 2 pour urgence, 3 consultation externe

    // GETTER & SETTER
    public int getIep() {
        return iep;
    }

    public void setIep(int iep) {
        this.iep = iep;
    }

    public int getIpp() {
        return ipp;
    }

    public void setIpp(int ipp) {
        this.ipp = ipp;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iep;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admission)) {
            return false;
        }
        Admission other = (Admission) object;
        if (this.iep != other.iep) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bureau.Admission[ id=" + iep + " ]";
    }
    
}
