/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Consultation;
import Metiers.Modeles.Employee;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Romain
 */
public class ConsultationDao {
    
    public void create(Consultation consultation)
    {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public void delete(Consultation consultation)
    {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    public Consultation modify(Consultation consultation)
    {
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    public Consultation find(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    public List<Consultation> findAll()
    {
        String req = "select c from Consultation c";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<Consultation>)query.getResultList();
    }
    
    public Consultation findCurrent(Employee employee)
    {
        String req = "select c from Consultation c where c.employee = :employee and (c.hourBeginConsultation IS NULL or c.hourEndConsultation IS NULL)";
        TypedQuery<Consultation> query = JpaUtil.obtenirContextePersistance().createQuery(req, Consultation.class);
        query.setParameter("employee", employee);
        List<Consultation> consultations = query.getResultList();
        Consultation result = null;
        if(!consultations.isEmpty())
        {
            result = consultations.get(0);
        }
        return result;
    }
    
    public List<Object[]> getEmployeeStats()
    {
        String q = new StringBuilder()
                       .append("select c.employee, c.client, count(c.client) ")
                       .append("from Consultation c ")
                       .append("group by c.employee, c.client ")
                       .append("order by count(c.client) desc")
                       .toString();
        
        Query query = JpaUtil.obtenirContextePersistance().createQuery(q);
        
        List<Object[]> list = query.getResultList();
        
        return list;
    }
}
