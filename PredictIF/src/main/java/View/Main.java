/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao.JpaUtil;
import Metiers.Modeles.Client;
import Metiers.Modeles.Consultation;
import Metiers.Modeles.Employee;
import Metiers.Modeles.Medium;
import Metiers.Modeles.ProfilAstral;
import Metiers.services.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Romain
 */
public class Main {
    
    //Input methods
    
    public static String lireChaine(String invite) {
        String chaineLue = null;
        System.out.print(invite);
        try {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);
            chaineLue = br.readLine();
        } catch (IOException ex) {
            ex.printStackTrace(System.err);
        }
        return chaineLue;
    }

    public static Integer lireInteger(String invite) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
        }
        return valeurLue;
    }

    public static Integer lireInteger(String invite, List<Integer> valeursPossibles) {
        Integer valeurLue = null;
        while (valeurLue == null) {
            try {
                valeurLue = Integer.parseInt(lireChaine(invite));
            } catch (NumberFormatException ex) {
                System.out.println("/!\\ Erreur de saisie - Nombre entier attendu /!\\");
            }
            if (!(valeursPossibles.contains(valeurLue))) {
                System.out.println("/!\\ Erreur de saisie - Valeur non-autorisée /!\\");
                valeurLue = null;
            }
        }
        return valeurLue;
    }
    
    public static void main(String args[]) {
        JpaUtil.init();
        
        boolean loop = true;
        
        Long idClient = null;
        Long idEmployee = null;
        
        //initialiseBDD();
        
        //main loop => front end
        while(loop)
        {
            int selection;
        
            System.out.println("\n-----Predict'if-----");
            System.out.println("1 - Log in as client");
            System.out.println("2 - Register as client");
            System.out.println("3 - Log in as employee");
            System.out.println("4 - CLIENT - display connected client profil");
            System.out.println("5 - CLIENT - get medium List");
            System.out.println("6 - CLIENT - Ask for a consultation");
            System.out.println("7 - EMPLOYEE - Get client profile for the actual consultation");
            System.out.println("8 - EMPLOYEE - Begin consultation");
            System.out.println("9 - EMPLOYEE - Get predictions for the actual consultation");
            System.out.println("10 - EMPLOYEE - End consultation");
            System.out.println("11 - EMPLOYEE - Statistics");
            System.out.println("12 - Log out");
            System.out.println("0 - Quit");
        
            selection = lireInteger("choix : ");
            
            switch(selection)
            {
                case 1:
                    idClient = authentifyClientService();
                    break;
                case 2:
                    registerClientService();
                    break;
                case 3:
                    idEmployee = authentifyEmployeeService();
                    break;
                case 4:
                    if(idClient != null)
                        displayClientProfileService(idClient);
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 5:
                    if(idClient != null)
                        consultMediumListService();
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 6:
                    if(idClient != null)
                        askConsultationService(idClient);
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 7:
                    if(idEmployee != null)
                        getClientProfilFromConsultationService(idEmployee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 8:
                    if(idEmployee != null)
                        beginConsultationService(idEmployee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 9:
                    if(idEmployee != null)
                        generatePredictionsService(idEmployee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 10:
                    if(idEmployee != null)
                        endConsultationService(idEmployee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 11:
                    if(idEmployee != null)
                        getStatisticsService();
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 12:
                    idEmployee = null;
                    idClient = null;
                    System.out.println("You have been deconnected");
                    break;
                case 0:
                    loop = false;
                    break;
                default:
                    break;    
            }
        }
        
        JpaUtil.destroy();
    }
    
    public static void initialiseBDD()
    {
        Service service = new Service();
        if(service.initialise())
            System.out.println("Init BDD : Success");
        else
            System.out.println("Init BDD : Failure");
    }
    
    public static void registerClientService()
    {
        Service service = new Service();
        
        String firstname = lireChaine("firstname ? : ");
        String lastname = lireChaine("lastname ? : ");
        Client client = new Client(firstname, lastname, firstname+"@gmail.com", "1234", "06", Date.from(LocalDate.of(2000,8,13).atStartOfDay(ZoneId.systemDefault()).toInstant()), "rue de", "Lyon", 69100, Client.Civilite.M, true);
   
        boolean inscription = service.registerClient(client);
        if(inscription)
        {
            System.out.println("inscription success");
        }
        else
        {
            System.out.print("inscription failure");
        }
    }
    
    public static Long authentifyClientService()
    {
        Service service = new Service();
        String mail, password;
        
        mail = lireChaine("mail ? : ");
        password = lireChaine("password ? : ");
        
        Long tmpIdClient = service.authenticateClient(mail, password);
        
        if(tmpIdClient != null)
        {
            System.out.println("Connexion success");
        }
        else
        {
            System.out.println("Connexion failure");
        }
        
        return tmpIdClient;
    }
    
    public static Long authentifyEmployeeService()
    {
        Service service = new Service();
        String mail, password;
        
        mail = lireChaine("mail ? : ");
        password = lireChaine("password ? : ");
        
        Long tmpIdEmployee = service.authenticateEmployee(mail, password);
        
        if(tmpIdEmployee != null)
        {
            System.out.println("Connexion success");
        }
        else
        {
            System.out.println("Connexion failure");
        }
        
        return tmpIdEmployee;
    }
    
    public static void displayClientProfileService(Long idClient)
    {      
        Service service = new Service();
        Client client = service.getClient(idClient);
        
        if(client != null)
        {
            System.out.println("client profile : " + client.getFirstName() + " " + client.getLastName());
            ProfilAstral profilAstral = client.getProfilAstral();
            if(profilAstral != null)
            {
                System.out.println(profilAstral.toString());
            }
            else
                System.out.println("no astral profile for this client => error");

            List<Consultation> consultations = client.getConsultations();
            if(consultations.size() > 0)
            {
                System.out.println("consultation history : ");
                for(Consultation c : consultations)
                {
                     System.out.println(c.toString());
                }
            }
            else
                System.out.println("no consultation yet");
        }
    }
    
    public static void consultMediumListService()
    {
        Service service = new Service();
        
        List<Medium> mediumList = service.consultMediumList();
   
        if(mediumList != null)
        {
            System.out.println("");
            System.out.println("Medium list " + mediumList.size());
            for(Medium m : mediumList)
            {
               System.out.println(m.getId() + ". " + m.getType() + " " + m.getDenomination());
            }
            System.out.println("");
        }
        else
        {
            System.out.println("Failure. Can't get medium list");
        }
    }
    
    public static void askConsultationService(Long idClient)
    {
        Service service = new Service();
        
        consultMediumListService();
        int id = lireInteger("id medium ? : ");
        if(service.askConsultation(id, idClient))
        {
            System.out.println("Consultation created");
        }
        else
        {
           System.out.println("error during the creation of the consultation"); 
        }
    }
    
    public static void getClientProfilFromConsultationService(Long idEmployee)
    {      
        Service service = new Service();
        Client client = service.getClientFromEmployee(idEmployee);
        
        if(client != null)
            displayClientProfileService(client.getId());
    }
    
    public static void beginConsultationService(Long idEmployee)
    {        
        Service service = new Service();
        if(service.beginConsultation(idEmployee))
        {
            System.out.println("Consultation has beginned. Wait for the client call");
        }
        else
        {
            System.out.println("An error occured. Please try again or contact the support");
        }
    }
    
    public static void generatePredictionsService(Long idEmployee)
    {
        Service service = new Service();
        
        int love = lireInteger("love : ");
        int health = lireInteger("health : ");
        int work = lireInteger("work : ");
        
        if((love > 4 || love < 0) ||  (health > 4 || health < 0) || (work > 4 || work < 0))
        {
            System.out.println("Wrong input");
        }
        else
        {
            List<String> predictions = service.generatePrediction(idEmployee, love, health, work);
        
            if(predictions != null)
            {
                System.out.println("");
                System.out.println("~<[ Prédictions ]>~");
                System.out.println("[ love ] " + predictions.get(0));
                System.out.println("[ health ] " + predictions.get(1));
                System.out.println("[ work ] " + predictions.get(2));
                System.out.println("");
            }
        }
    }
    
    public static void endConsultationService(Long idEmployee)
    {
        Service service = new Service();
        
        String comment = lireChaine("Comment about the consultation : ");
        
        if(service.endConsultation(idEmployee, comment))
        {
            System.out.println("Consultation has ended");
        }
        else
        {
            System.out.println("An error occured. Please try again or contact the support");
        }
    }
    
    public static void getStatisticsService()
    {
        Service service = new Service();
        
        System.out.println("Statistiques :");
        
        LinkedHashMap<Medium, Integer> statsMediums = service.getStatisticsMedium();
        LinkedHashMap<Employee, LinkedHashMap<Client, Integer>> statsEmployee = service.getStatisticsEmployee();
        if(statsMediums == null || statsEmployee == null)
        {
            System.out.println("An error occured. Please try to contact the support");
            return;
        }
        
        System.out.println("Number of consultations per medium");
        
        Set<Medium> keysMedium = statsMediums.keySet();
        for(Medium m : keysMedium)
        {
            System.out.println(m.getDenomination() + " : " + statsMediums.get(m) + " consultations");
        }
        
        System.out.println("\nBreakdown of customers by employee");
        
        Set<Employee> keysEmployee = statsEmployee.keySet();
        for(Employee e : keysEmployee)
        {
            System.out.println("Employee : " + e.getFirstName() + " " + e.getLastName() + " : ");
            Set<Client> keysClient = statsEmployee.get(e).keySet();
            for(Client c : keysClient)
            {
                System.out.println("Client " + c.getFirstName() + " " + c.getLastName() + " : " + statsEmployee.get(e).get(c) + " consultations");
            }
        }
    }
    
}
