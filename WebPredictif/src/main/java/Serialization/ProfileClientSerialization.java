/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Serialization;

import Metiers.Modeles.Client;
import Metiers.Modeles.Consultation;
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
public class ProfileClientSerialization extends Serialization {
    
    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client client = (Client)request.getAttribute("client");
        
        JsonObject container = new JsonObject();

        if (client != null) {
            
            List<Consultation> consultations = client.getConsultations();
            
            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("nom", client.getLastName());
            jsonClient.addProperty("prenom", client.getFirstName());
            JsonObject jsonProfilAstral = new JsonObject();
            jsonProfilAstral.addProperty("zodiac", client.getProfilAstral().getZodiacSymbol());
            jsonProfilAstral.addProperty("color", client.getProfilAstral().getLuckyColor());
            jsonProfilAstral.addProperty("animal", client.getProfilAstral().getTotemAnimal());
            jsonProfilAstral.addProperty("chinese", client.getProfilAstral().getChineseSign());

            jsonClient.add("profilAstral", jsonProfilAstral);
            container.add("client", jsonClient);
            
            JsonObject jsonConsultations = new JsonObject();
            
            
            for(Consultation c : consultations )
            {
                JsonObject jsonConsultation = new JsonObject();
                jsonProfilAstral.addProperty("askH", c.getHourAskConsultation().toString());
                jsonProfilAstral.addProperty("medium", c.getMedium().getDenomination());
                jsonProfilAstral.addProperty("beginH", c.getHourBeginConsultation().toString());
                jsonProfilAstral.addProperty("endH", c.getHourEndConsultation().toString());
                
                jsonConsultations.add(c.getId().toString(),jsonConsultation);
            }
            
            jsonClient.add("consultations", jsonConsultations);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
