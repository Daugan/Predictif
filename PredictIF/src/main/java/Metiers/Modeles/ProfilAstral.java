/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.Modeles;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Romain
 */
@Entity
public class ProfilAstral implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String zodiacSymbol;
    private String chineseSign;
    private String totemAnimal;
    private String luckyColor;
    
    //empty constructor
    public ProfilAstral()
    {
    }
    
    //constructor
    public ProfilAstral(String zodiacSymbol, String chineseSign, String totemAnimal, String luckyColor)
    {
        this.zodiacSymbol = zodiacSymbol;
        this.chineseSign = chineseSign;
        this.totemAnimal = totemAnimal;
        this.luckyColor = luckyColor;
    }
    
    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getZodiacSymbol() {
        return this.zodiacSymbol;
    }

    public void setZodiacSymbol(String zodiacSymbol) {
        this.zodiacSymbol = zodiacSymbol;
    }
    
    public String getChineseSign() {
        return this.chineseSign;
    }

    public void setChinesSign(String chineseSign) {
        this.chineseSign = chineseSign;
    }
    
    public String getTotemAnimal() {
        return this.totemAnimal;
    }

    public void setTotemAnimal(String totemAnimal) {
        this.totemAnimal = totemAnimal;
    }
    
    public String getLuckyColor() {
        return this.luckyColor;
    }

    public void setLuckyColor(String luckyColor) {
        this.luckyColor = luckyColor;
    }
    
    //override for hashcode, equals and string

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfilAstral)) {
            return false;
        }
        ProfilAstral other = (ProfilAstral) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "Metiers.Modeles.ProfilAstral[ id=" + id + " ]\n";
        res += "Chinese sign : " + this.getChineseSign() + "\n";
        res += "Lucky color : " + this.getLuckyColor() + "\n";
        res += "Animal totem : " + this.getTotemAnimal() + "\n";
        res += "Zodiac symbol : " + this.getZodiacSymbol() + "\n";
        return res;
    }
    
}
