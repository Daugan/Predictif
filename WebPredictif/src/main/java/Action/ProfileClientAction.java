/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Client;
import Metiers.services.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class ProfileClientAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idClient = (Long)session.getAttribute("idClient");
        
        if(idClient != null)
        {
            Service service = new Service();
        
            Client client = service.getClient(idClient);

            request.setAttribute("client", client);
        }
    }
    
}
