/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.services.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class AskConsultationAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idClient = (Long)session.getAttribute("idClient");
        
        Long idMedium = Long.parseLong(request.getParameter("idMedium"));
        boolean success = false;
        
        if(idClient != null)
        {
            Service service = new Service();
            success = service.askConsultation(idMedium, idClient);
        }
        request.setAttribute("success", success);
        

    }
    
}
