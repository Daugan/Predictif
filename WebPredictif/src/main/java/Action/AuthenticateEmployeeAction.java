/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Employee;
import Metiers.services.Service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
public class AuthenticateEmployeeAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {
        
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");

        Service service = new Service();
        Employee employee = service.authenticateEmployee(mail, password);

        request.setAttribute("employee", employee);
        
        // Session management : save client ID
        HttpSession session = request.getSession();
        if (employee != null) {
            session.setAttribute("idEmployee", employee.getId());
        }
        else {
            session.removeAttribute("idEmployee");
        }
    }
}
