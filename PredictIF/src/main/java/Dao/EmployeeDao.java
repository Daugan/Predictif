/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
import Util.Gender;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Romain
 */
public class EmployeeDao {
    
    public void create(Employee employe)
    {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
    public void delete(Employee employe)
    {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    
    public Employee modify(Employee employe)
    {
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }
    
    public Employee find(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(Employee.class, id);
    }
    
    public List<Employee> findAll()
    {
        String req = "select e from Employee e order by e.lastname desc";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<Employee>)query.getResultList();
    }
    
    public Employee find(String mail)
    {
        String req = "select e from Employee e where e.mail = :mail";
        TypedQuery<Employee> query = JpaUtil.obtenirContextePersistance().createQuery(req, Employee.class);
        query.setParameter("mail", mail);
        List<Employee> employees = query.getResultList();
        Employee result = null;
        if(!employees.isEmpty())
        {
            result = employees.get(0);
        }
        return result;
    }
    
    public Employee findAvailable(Gender gender)
    {
        String req = "select e from Employee e where e.disponibility = :dispo and e.gender = :gender";
        TypedQuery<Employee> query = JpaUtil.obtenirContextePersistance().createQuery(req, Employee.class);
        query.setParameter("dispo", true);
        query.setParameter("gender", gender);
        List<Employee> employees = query.getResultList();
        Employee result = null;
        if(!employees.isEmpty())
        {
            result = employees.get(0);
        }
        return result;
    }
}
