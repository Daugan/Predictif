/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Medium;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Romain
 */
public class MediumDao {
    
    public void create(Medium medium)
    {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    public void delete(Medium medium)
    {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    public Medium modify(Medium medium)
    {
        return JpaUtil.obtenirContextePersistance().merge(medium);
    }
    
    public Medium find(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    public List<Medium> findAll()
    {
        String req = "select m from Medium m";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<Medium>)query.getResultList();
    }
    
    public List<Object[]> getMediumAndCountConsultation()
    {
        String q = new StringBuilder()
                       .append("select m, count(c) as total ")
                       .append("from Medium m ")
                       .append("left join Consultation c on m = c.medium ")
                       .append("group by m ")
                       .append("order by count(c) desc")
                       .toString();
        
        Query query = JpaUtil.obtenirContextePersistance().createQuery(q);
        
        List<Object[]> list = query.getResultList();
        
        return list;
    }
}
