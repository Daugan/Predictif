/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
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
        String req = "select c from Employe c order by c.lastname desc";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<Employee>)query.getResultList();
    }
    
    public Employee find(String mail)
    {
        String req = "select c from Employe c where c.mail = '" + mail + "'";
        TypedQuery<Employee> query = JpaUtil.obtenirContextePersistance().createQuery(req, Employee.class);
        List<Employee> employees = query.getResultList();
        Employee result = null;
        if(!employees.isEmpty())
        {
            result = employees.get(0);
        }
        return result;
    }
}
