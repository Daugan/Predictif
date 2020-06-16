/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Action;

import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
import Metiers.Modeles.Medium;
import Metiers.services.Service;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Romain
 */
public class StatisticAction extends Action {
    
    @Override
    public void execute(HttpServletRequest request) {

        Service service = new Service();
        LinkedHashMap<Medium, Integer> statsMediums = service.getStatisticsMedium();
        
        LinkedHashMap<Employee, LinkedHashMap<Client, Integer>> statsEmployee = service.getStatisticsEmployee();
        
        request.setAttribute("statsMediums", statsMediums);
        request.setAttribute("statsEmployee", statsEmployee);
    }
    
}
