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
import java.util.Date;
import java.util.List;

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
        
        Client client = null;
        Employee employee = null;
        
        initialiseBDD();
        
        //main loop => front end
        while(loop)
        {
            int selection;
        
            System.out.println("\n-----Predict'if-----");
            System.out.println("1 - Connect as client");
            System.out.println("2 - Register as client");
            System.out.println("3 - Connect as employee");
            System.out.println("4 - CLIENT - display connected client profil");
            System.out.println("5 - CLIENT - get medium List");
            System.out.println("6 - CLIENT - Ask for a consultation");
            System.out.println("7 - EMPLOYEE - Get client profile for the actual consultation");
            System.out.println("8 - EMPLOYEE - Begin consultation");
            System.out.println("9 - EMPLOYEE - Get predictions for the actual consultation");
            System.out.println("10 - EMPLOYEE - End consultation");
            System.out.println("11 - EMPLOYEE - Statistics");
            System.out.println("0 - Quit");
        
            selection = lireInteger("choix : ");
            
            switch(selection)
            {
                case 1:
                    client = authentifyClientService();
                    break;
                case 2:
                    registerClientService();
                    break;
                case 3:
                    employee = authentifyEmployeeService();
                    break;
                case 4:
                    if(client != null)
                        getClientProfileService(client);
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 5:
                    if(client != null)
                        consultMediumListService();
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 6:
                    if(client != null)
                        askConsultationService(client);
                    else
                        System.out.println("You must be connected as client to use this fonctionnality");
                    break;
                case 7:
                    if(employee != null)
                        getClientProfilFromConsultationService(employee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 8:
                    if(employee != null)
                        beginConsultationService(employee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 9:
                    if(employee != null)
                        generatePredictionsService(employee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 10:
                    if(employee != null)
                        endConsultationService(employee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
                    break;
                case 11:
                    if(employee != null)
                        getStatisticsService(employee);
                    else
                        System.out.println("You must be connected as employee to use this fonctionnality");
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
        
        Client client = new Client("Romain", "test", "test", "test", "06", new Date(1999,11,22), "rue de", "Lyon", 69100, Client.Civilite.M, true);
   
        boolean inscription = service.registerClient(client);
        if(inscription)
        {
            System.out.println("Succès inscription");
        }
        else
        {
            System.out.print("Echec inscription");
        }
    }
    
    public static Client authentifyClientService()
    {
        Service service = new Service();
        String mail, password;
        
        mail = lireChaine("mail ? : ");
        password = lireChaine("password ? : ");
        
        Client tmpClient = service.authenticateClient(mail, password);
        
        if(tmpClient != null)
        {
            System.out.println("Connexion success");
        }
        else
        {
            System.out.println("Connexion failure");
        }
        
        return tmpClient;
    }
    
    public static Employee authentifyEmployeeService()
    {
        Service service = new Service();
        String mail, password;
        
        mail = lireChaine("mail ? : ");
        password = lireChaine("password ? : ");
        
        Employee tmpEmployee = service.authenticateEmployee(mail, password);
        
        if(tmpEmployee != null)
        {
            System.out.println("Connexion success");
        }
        else
        {
            System.out.println("Connexion failure");
        }
        
        return tmpEmployee;
    }
    
    public static void getClientProfileService(Client client)
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
    
    public static void askConsultationService(Client client)
    {
        Service service = new Service();
        
        consultMediumListService();
        int id = lireInteger("Numero du medium ? : ");
        if(service.askConsultation(id, client))
        {
            System.out.println("Consultation created");
        }
        else
        {
           System.out.println("error during the creation of the consultation"); 
        }
    }
    
    public static void getClientProfilFromConsultationService(Employee employee)
    {
        
    }
    
    public static void beginConsultationService(Employee employee)
    {
        
    }
    
    public static void generatePredictionsService(Employee employee)
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
            //List<String> predictions = service.GeneratePrediction(client, love, health, work);
            List<String> predictions = null;
        
            if(predictions != null)
            {
                System.out.println("");
                System.out.println("~<[ Prédictions ]>~");
                System.out.println("[ Amour ] " + predictions.get(0));
                System.out.println("[ Santé ] " + predictions.get(1));
                System.out.println("[Travail] " + predictions.get(2));
                System.out.println("");
            }
        }
    }
    
    public static void endConsultationService(Employee employee)
    {
        
    }
    
    public static void getStatisticsService(Employee employee)
    {
        
    }
    
}
