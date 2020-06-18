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
public class BeginConsultationAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idEmployee = (Long)session.getAttribute("idEmployee");
        
        boolean success = false;
        
        if(idEmployee != null)
        {
            Service service = new Service();

            success = service.beginConsultation(idEmployee);
        }
        request.setAttribute("success", success);
    }
}
