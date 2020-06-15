/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Medium;
import Metiers.services.Service;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Romain
 */
public class ListMediumAction extends Action {

    @Override
    public void execute(HttpServletRequest request) {

        Service service = new Service();
        List<Medium> mediums = service.consultMediumList();

        request.setAttribute("mediums", mediums);
    }
    
}
