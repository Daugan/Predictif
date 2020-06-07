/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.Modeles;

import Util.Gender;
import javax.persistence.Entity;

/**
 *
 * @author Romain
 */
@Entity
public class Spirite extends Medium {

    private String support;
     
    public Spirite(String denomination, String presentation, Gender gender, String support) {
        super(denomination, presentation, gender);
        this.support = support;
    }
    
    public Spirite()
    {
    }
    
    @Override
    public String getType()
    {
        return "Spirite";
    }
    
    //getters and setters

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spirite)) {
            return false;
        }
        Spirite other = (Spirite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Metiers.Modeles.Spirite[ id=" + id + " ]";
    }
    
}
