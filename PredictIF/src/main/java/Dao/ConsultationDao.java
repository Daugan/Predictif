/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Consultation;
import java.util.List;
import javax.persistence.Query;

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
}
