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
public class EndConsultationAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idEmployee = (Long)session.getAttribute("idEmployee");
        
        String comment = request.getParameter("comment");
        boolean success = false;
        
        if(idEmployee != null)
        {
            Service service = new Service();

            success = service.endConsultation(idEmployee, comment);
        }
        request.setAttribute("success", success);
    }
}
