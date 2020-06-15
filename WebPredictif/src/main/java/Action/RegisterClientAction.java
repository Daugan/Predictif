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
public class RegisterClientAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        Service service = new Service();
        
        //parameters
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        //client
        Client client = new Client();
        boolean success = service.registerClient(client);

        request.setAttribute("register", success);
        request.setAttribute("client", client);

    }
    
}
