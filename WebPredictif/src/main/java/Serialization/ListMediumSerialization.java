/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

import Metiers.Modeles.Medium;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Romain
 */
public class ListMediumSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> mediums = (List<Medium>)request.getAttribute("mediums");
        
        JsonObject container = new JsonObject();

        for(Medium m : mediums)
        {
            JsonObject jsonMedium = new JsonObject();
            
            jsonMedium.addProperty("denomination", m.getDenomination());
            jsonMedium.addProperty("presentation", m.getPresentation());
            jsonMedium.addProperty("type", m.getType());

            container.add(m.getId().toString(), jsonMedium);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
    
}
