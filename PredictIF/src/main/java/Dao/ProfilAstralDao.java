/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Client;
import Metiers.Modeles.ProfilAstral;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Romain
 */
public class ProfilAstralDao {
    public void create(ProfilAstral profilAstral)
    {
        JpaUtil.obtenirContextePersistance().persist(profilAstral);
    }
    
    public void delete(ProfilAstral profilAstral)
    {
        JpaUtil.obtenirContextePersistance().persist(profilAstral);
    }
    
    public ProfilAstral modify(ProfilAstral profilAstral)
    {
        return JpaUtil.obtenirContextePersistance().merge(profilAstral);
    }
    
    public ProfilAstral Find(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(ProfilAstral.class, id);
    }
    
    public List<ProfilAstral> FindAll()
    {
        String req = "select p from ProfilAstral desc";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<ProfilAstral>)query.getResultList();
    }
    
    public ProfilAstral Find(Client client)
    {
        return null;
    }
}
