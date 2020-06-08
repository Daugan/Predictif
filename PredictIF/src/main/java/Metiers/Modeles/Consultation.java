/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.Modeles;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Romain
 */
@Entity
public class Consultation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String comment;
    @Temporal(TemporalType.DATE)
    private Date hourAskConsultation;
    @Temporal(TemporalType.DATE)
    private Date hourBeginConsultation;
    @Temporal(TemporalType.DATE)
    private Date hourEndConsultation;
    
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;
    @ManyToOne
    private Employee employee;

    public Consultation(Client client) {
        this.hourAskConsultation = new Date();
        this.client = client;
    }
    
    public Consultation()
    {
        this.hourAskConsultation = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getHourAskConsultation() {
        return hourAskConsultation;
    }

    public void setHourAskConsultation(Date hourAskConsultation) {
        this.hourAskConsultation = hourAskConsultation;
    }

    public Date getHourBeginConsultation() {
        return hourBeginConsultation;
    }

    public void setHourBeginConsultation(Date hourBeginConsultation) {
        this.hourBeginConsultation = hourBeginConsultation;
    }

    public Date getHourEndConsultation() {
        return hourEndConsultation;
    }

    public void setHourEndConsultation(Date hourEndConsultation) {
        this.hourEndConsultation = hourEndConsultation;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Medium getMedium() {
        return medium;
    }

    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        if (!(object instanceof Consultation)) {
            return false;
        }
        Consultation other = (Consultation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String res = "Metiers.Modeles.Consultation[ id=" + id + " ]\n";
        res += "comment : " + this.getComment() + "\n";
        res += "hour ask : " + this.getHourAskConsultation().toString() + "\n";
        if(this.getHourBeginConsultation() != null)
            res += "hour begin: " + this.getHourBeginConsultation().toString() + "\n";
        if(this.getHourEndConsultation() != null)
            res += "hour end : " + this.getHourEndConsultation().toString() + "\n";
        return res;
    }
    
}
