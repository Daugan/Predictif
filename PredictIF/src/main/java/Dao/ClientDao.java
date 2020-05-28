/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Metiers.Modeles.Client;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Romain
 */
public class ClientDao {
    
    public void create(Client client)
    {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    public void delete(Client client)
    {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    
    public Client modify(Client client)
    {
        return JpaUtil.obtenirContextePersistance().merge(client);
    }
    
    public Client find(Long id)
    {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    
    public List<Client> findAll()
    {
        String req = "select c from Client c order by c.lastname desc";
        Query query = JpaUtil.obtenirContextePersistance().createQuery(req);
        return (List<Client>)query.getResultList();
    }
    
    public Client find(String mail)
    {
        String req = "select c from Client c where c.mail = '" + mail + "'";
        TypedQuery<Client> query = JpaUtil.obtenirContextePersistance().createQuery(req, Client.class);
        List<Client> clients = query.getResultList();
        Client result = null;
        if(!clients.isEmpty())
        {
            result = clients.get(0);
        }
        return result;
    }
    
}
