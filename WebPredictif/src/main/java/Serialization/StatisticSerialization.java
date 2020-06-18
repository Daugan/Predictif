/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
import Metiers.Modeles.Medium;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Romain
 */
public class StatisticSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        //get request parameter => stats from services
        LinkedHashMap<Medium, Integer> statsMediums = (LinkedHashMap<Medium, Integer>)request.getAttribute("statsMediums");
        LinkedHashMap<Employee, LinkedHashMap<Client, Integer>> statsEmployee = (LinkedHashMap<Employee, LinkedHashMap<Client, Integer>>)request.getAttribute("statsEmployee");
        int i = 0;
        
        //create container json object
        JsonObject container = new JsonObject();
        JsonObject jsonStatsMediums = new JsonObject();
        JsonObject jsonStatsEmployee = new JsonObject();
        
        //loop throw stats and create json objects
        Set<Medium> keysMedium = statsMediums.keySet();
        for(Medium m : keysMedium)
        {
            JsonObject jsonMedium = new JsonObject();
            jsonMedium.addProperty("denomination", m.getDenomination());
            jsonMedium.addProperty("nbConsultations", statsMediums.get(m));
            jsonStatsMediums.add(String.valueOf(i++), jsonMedium);
        }
        
        i = 0;
        //loop throw stats and create json objects
        Set<Employee> keysEmployee = statsEmployee.keySet();
        for(Employee e : keysEmployee)
        {
            JsonObject jsonEmployee = new JsonObject();
            jsonEmployee.addProperty("firstname", e.getFirstName());
            jsonEmployee.addProperty("lastname", e.getLastName());
            
            JsonArray jsonClients = new JsonArray();
            
            Set<Client> keysClient = statsEmployee.get(e).keySet();
            for(Client c : keysClient)
            {
                JsonObject jsonClient = new JsonObject();
                jsonClient.addProperty("firstname", c.getFirstName());
                jsonClient.addProperty("lastname", c.getLastName());
                jsonClient.addProperty("nbConsultations", statsEmployee.get(e).get(c));
                
                jsonClients.add(jsonClient);
            }
            
            jsonEmployee.add("clients", jsonClients);
            jsonStatsEmployee.add(String.valueOf(i++),jsonEmployee);
        }
       
        container.add("statsMediums", jsonStatsMediums);
        container.add("statsEmployee", jsonStatsEmployee);

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
