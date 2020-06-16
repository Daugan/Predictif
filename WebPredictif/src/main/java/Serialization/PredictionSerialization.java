/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

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
public class PredictionSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        List<String> predictions = (List<String>)request.getAttribute("predictions");
        
        JsonObject container = new JsonObject();
        
        if(predictions != null)
        {
            container.addProperty("success", true);
            container.addProperty("love", predictions.get(0));
            container.addProperty("health", predictions.get(1));
            container.addProperty("work", predictions.get(2));
        }
        else
        {
            container.addProperty("success", false);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
