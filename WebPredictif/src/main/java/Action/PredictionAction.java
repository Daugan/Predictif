/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.services.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class PredictionAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        int love = Integer.parseInt(request.getParameter("love"));
        int health = Integer.parseInt(request.getParameter("health"));
        int work = Integer.parseInt(request.getParameter("work"));
        
        HttpSession session = request.getSession();
        Long idEmployee = (Long)session.getAttribute("idEmployee");
        
        if(idEmployee != null)
        {
            Service service = new Service();
            List<String> predictions = service.generatePrediction(idEmployee, love, health, work);
        
            request.setAttribute("predictions", predictions);
        }   

    }
    
}
