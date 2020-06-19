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
public class EmployeeMainPageSerialization extends Serialization {

    @Override
    public void serialize(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation consultation = (Consultation) request.getAttribute("consultation");

        JsonObject container = new JsonObject();

        if (consultation != null) {
            //a consultation is in progress
            container.addProperty("consultation", true);
            container.addProperty("disponibility", consultation.getEmployee().isDisponibility());

            //set client json object
            Client client = consultation.getClient();
            List<Consultation> consultations = client.getConsultations();

            JsonObject jsonClient = new JsonObject();
            jsonClient.addProperty("lastname", client.getLastName());
            jsonClient.addProperty("firstname", client.getFirstName());
            JsonObject jsonProfilAstral = new JsonObject();
            jsonProfilAstral.addProperty("zodiac", client.getProfilAstral().getZodiacSymbol());
            jsonProfilAstral.addProperty("color", client.getProfilAstral().getLuckyColor());
            jsonProfilAstral.addProperty("animal", client.getProfilAstral().getTotemAnimal());
            jsonProfilAstral.addProperty("chinese", client.getProfilAstral().getChineseSign());

            jsonClient.add("profilAstral", jsonProfilAstral);
            container.add("client", jsonClient);

            JsonObject jsonConsultations = new JsonObject();

            for (Consultation c : consultations) {
                JsonObject jsonConsultation = new JsonObject();
                jsonConsultation.addProperty("askH", c.getHourAskConsultation().toString());
                jsonConsultation.addProperty("medium", c.getMedium().getDenomination());
                if (c.getHourBeginConsultation() != null) {
                    jsonConsultation.addProperty("beginH", c.getHourBeginConsultation().toString());
                } else {
                    jsonConsultation.addProperty("beginH", "");
                }

                if (c.getHourEndConsultation() != null) {
                    jsonConsultation.addProperty("endH", c.getHourEndConsultation().toString());
                } else {
                    jsonConsultation.addProperty("endH", "");
                }

                if (c.getComment() != null) {
                    jsonConsultation.addProperty("comment", c.getComment());
                } else {
                    jsonConsultation.addProperty("comment", "");
                }

                jsonConsultations.add(c.getId().toString(), jsonConsultation);
            }

            jsonClient.add("consultations", jsonConsultations);
            container.add("client", jsonClient);

            //set other json data
            container.addProperty("medium", consultation.getMedium().getDenomination());
            if (consultation.getHourBeginConsultation() != null) {
                container.addProperty("beginH", consultation.getHourBeginConsultation().toString());
            } else {
                container.addProperty("beginH", "");
            }
            
        } else {
            container.addProperty("consultation", false);
        }

        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }
}
