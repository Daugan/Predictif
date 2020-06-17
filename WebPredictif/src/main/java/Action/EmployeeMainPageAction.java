/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Consultation;
import Metiers.services.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class EmployeeMainPageAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long idEmployee = (Long)session.getAttribute("idEmployee");
        Consultation consultation = null;
        
        if(idEmployee != null)
        {
            Service service = new Service();
            consultation = service.getConsultationFromEmployee(idEmployee);
        }
        
        request.setAttribute("consultation", consultation);
    }
}
