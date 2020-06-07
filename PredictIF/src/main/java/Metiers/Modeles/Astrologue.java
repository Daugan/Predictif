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
public class Astrologue extends Medium {

    private String formation;
    private String promotion;

    public Astrologue(String denomination, String presentation, Gender gender, String formation, String promotion) {
        super(denomination, presentation, gender);
        this.formation = formation;
        this.promotion = promotion;
    }
    
    public Astrologue()
    {
    }
    
    @Override
    public String getType()
    {
        return "Astrologue";
    }
    
    //getters and setters

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
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
        if (!(object instanceof Astrologue)) {
            return false;
        }
        Astrologue other = (Astrologue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Metiers.Modeles.Astrologue[ id=" + id + " ]";
    }
    
}
