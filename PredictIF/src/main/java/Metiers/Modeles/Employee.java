/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.Modeles;

import Util.Gender;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Romain
 */
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 private String firstName;
    private String lastName;
    @Column(unique = true)
    private String mail;
    private String password;
    private String address;
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private String phone;
    private boolean disponibility;
    protected Gender gender;
    
    //empty constructor
    public Employee()
    {
    }
    
    //constructor
    public Employee(String firstname, String lastname, String mail, String password, String address, Date birthDate, String phone,Gender gender, boolean disponibility)
    {
        this.firstName = firstname;
        this.lastName = lastname;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.birthDate = birthDate;
        this.phone = phone;
        this.gender = gender;
        this.disponibility = disponibility;
    }
    
    //getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName()
    {
        return this.firstName;
    }
    
    void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    
    public String getLastName()
    {
        return this.lastName;
    }
    
    void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public String getMail()
    {
        return this.mail;
    }
    
    void setMail(String mail)
    {
        this.mail = mail;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
    void setPassword(String password)
    {
        this.password = password;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    void setAddress(String address)
    {
        this.address = address;
    }
    
    public Date getBirthDate()
    {
        return this.birthDate;
    }
    
    void setBirthDate(Date birthDate)
    {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender geder) {
        this.gender = gender;
    }

    public boolean isDisponibility() {
        return disponibility;
    }

    public void setDisponibility(boolean disponibility) {
        this.disponibility = disponibility;
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
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Metiers.Employe[ id=" + id + " ]";
    }
    
}
