/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

import Metiers.Modeles.Employee;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Romain
 */
public class AuthenticateEmployeeSerialization extends Serialization {
    
    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Employee employee = (Employee)request.getAttribute("employee");
        
        JsonObject container = new JsonObject();

        Boolean connexion = (employee != null);
        container.addProperty("connexion", connexion);

        if (employee != null) {
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("id", employee.getId());
            jsonClient.addProperty("nom", employee.getLastName());
            jsonClient.addProperty("prenom", employee.getFirstName());

            container.add("employee", jsonClient);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
