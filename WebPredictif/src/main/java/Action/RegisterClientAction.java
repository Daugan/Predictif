/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Client;
import Metiers.Modeles.Client.Civilite;
import Metiers.services.Service;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date birthDate = sdf.parse(request.getParameter("birthDate"));
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        int cp = Integer.valueOf(request.getParameter("cp"));
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String strCivilite = request.getParameter("civilite");
        Civilite civilite = Client.Civilite.M;
        
        if("MME".equals(strCivilite))
            civilite = Client.Civilite.MME;
            
        Date birthDate = Date.from(LocalDate.of(1999,11,22).atStartOfDay(ZoneId.systemDefault()).toInstant());
        Client client = new Client(surname, name, email, password, phone, birthDate, street, city, cp, civilite, true);
        boolean success = service.registerClient(client);
        
        request.setAttribute("register", success);
        request.setAttribute("client", client);

    }
    
}
