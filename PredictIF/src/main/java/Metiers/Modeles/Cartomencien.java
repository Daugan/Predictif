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
public class Cartomencien extends Medium {

    public Cartomencien(String denomination, String presentation, Gender gender) {
        super(denomination, presentation, gender);
    }
    
    public Cartomencien()
    { 
    }
    
    @Override
    public String getType()
    {
        return "Cartomencien";
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
        if (!(object instanceof Cartomencien)) {
            return false;
        }
        Cartomencien other = (Cartomencien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Metiers.Modeles.Cartomencien[ id=" + id + " ]";
    }
    
}
