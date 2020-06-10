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
public class AuthenticateClientAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        Service service = new Service();
        Client client = service.authenticateClient(mail, password);

        request.setAttribute("client", client);
        
        // Session management : save client ID
        HttpSession session = request.getSession();
        if (client != null) {
            session.setAttribute("idClient", client.getId());
        }
        else {
            session.removeAttribute("idClient");
        }
    }
}
