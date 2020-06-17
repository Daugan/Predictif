/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class DisconnectAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        
        // first check if user is client
        Long idClient = (Long)session.getAttribute("idClient");
        Long idEmployee = (Long)session.getAttribute("idEmployee");
        
        if (idClient != null) 
        {
            request.setAttribute("user", "client");
        }
        else if(idEmployee != null)
        {
            request.setAttribute("user", "employee");
        }
            
        session.removeAttribute("idClient");
        session.removeAttribute("idEmployee");
        

    }
}
