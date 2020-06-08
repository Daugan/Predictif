/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.services;

import Dao.ClientDao;
import Dao.ConsultationDao;
import Dao.EmployeeDao;
import Dao.JpaUtil;
import Dao.MediumDao;
import Metiers.Modeles.Astrologue;
import Metiers.Modeles.Cartomancien;
import Metiers.Modeles.Client;
import Metiers.Modeles.Consultation;
import Metiers.Modeles.Employee;
import Metiers.Modeles.Medium;
import Metiers.Modeles.ProfilAstral;
import Metiers.Modeles.Spirite;
import Util.AstroTest;
import Util.Gender;
import Util.Message;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Romain
 */
public class Service {
    
    public boolean initialise()
    {
        boolean res = false;
        ClientDao clientdao = new ClientDao();
        EmployeeDao employeedao = new EmployeeDao();
        MediumDao mediumdao = new MediumDao();
        ConsultationDao consultationdao = new ConsultationDao();
        
        //clients
        List<Client> clients = new ArrayList<Client>();
        clients.add(new Client("Romain", "Chikirou", "romain@gmail.com", "1234", "0651895860", Date.from(LocalDate.of(1999,11,22).atStartOfDay(ZoneId.systemDefault()).toInstant()), "205 rue de la vierge", "Villeurbanne", 69100, Client.Civilite.M, true));
        clients.add(new Client("Kesly", "Gassanr", "kesly@gmail.com", "1234", "0651895860", Date.from(LocalDate.of(1999,11,17).atStartOfDay(ZoneId.systemDefault()).toInstant()), "13 avenue de la republique", "Lyon", 69003, Client.Civilite.M, true));
        clients.add(new Client("Carmen", "Prévot", "carmen@gmail.com", "1234", "0751895860", Date.from(LocalDate.of(1997,11,16).atStartOfDay(ZoneId.systemDefault()).toInstant()), "43 route de genas", "Villeurbanne", 69100, Client.Civilite.MME, true));
        
        //employees
        Employee employee1 = new Employee("Mathieu", "Maranzana", "mathieu@gmail.com", "1234", "25 rue de l'étoile, Lyon", Date.from(LocalDate.of(1896, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()), "0651879852", Gender.M, true);
        Employee employee2 = new Employee("Frédérique", "Biennier", "frederique@gmail.com", "1234", "1 route éléctrique, Paris", Date.from(LocalDate.of(1760, 01, 01).atStartOfDay(ZoneId.systemDefault()).toInstant()), "0745452154", Gender.F, true);
        
        //mediums
        List<Medium> mediums = new ArrayList<Medium>();
        mediums.add(new Cartomancien("Madame Irma", "Medium de grande renom", Gender.F));
        mediums.add(new Cartomancien("Madame Pristina", "Medium des balkans", Gender.F));
        mediums.add(new Cartomancien("Monsieur Lebar", "Cartomencien depuis l'age de 8 ans", Gender.M));
        mediums.add(new Astrologue("Monsieur Gripay", "Astrologue passionné par la grande ours", Gender.M, "INSA de Lyon", "Promo 51"));
        mediums.add(new Spirite("Madame Tchounik", "Aime le sprite", Gender.F, "Table de Ouija"));
        
        //consultations
        
        try
        {
            //call existing service to create object
            
            for(Client c : clients)
            {
                registerClient(c);   
            }
            
            JpaUtil.creerContextePersistance();
            //create other stuff
            JpaUtil.ouvrirTransaction();
            
            employeedao.create(employee1);
            employeedao.create(employee2);
            
            for(Medium m : mediums)
            {
                mediumdao.create(m);
                System.out.println("id : " + m.getId());
            }

            
            JpaUtil.validerTransaction();
            
            res = true;
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return res;
    }
    
    /**
     * Check that all the user input are correct
     * @param lastName lastName of the client
     * @param firstName firstName
     * @param birthDate birthday date of the client
     * @param street street address
     * @param city city address
     * @param cp cp address
     * @param phone phone of the client
     * @param mail mail of the client
     * @param password password of the client
     * @return boolean True if all the input are correct
    */
    public boolean checkClientInput(String firstName, String lastName, String mail, String password, String phone, Date birthDate, String street, String city, int cp)
    {
        //TODO
        return true;
    }
    
    /**
     * create the corresponding client and send him a mail (success or failure)
     * @param client client to register
     * @return boolean True if client has been correctly created
    */
    public boolean registerClient(Client client)
    {
        boolean res = true;
        ClientDao clientdao = new ClientDao();
        
        //create profil
        AstroTest astroApi = new AstroTest();
        try
        {
            List<String> profil = astroApi.getProfil(client.getFirstName(), client.getBirthDate());
            ProfilAstral profilA = new ProfilAstral(profil.get(0),profil.get(1),profil.get(3),profil.get(2));
            client.setProfilAstral(profilA);
        }
        catch(Exception ex)
        {
            client = null;
        }
        
        JpaUtil.creerContextePersistance();
        try
        {
            JpaUtil.ouvrirTransaction();
            
            clientdao.create(client);
            
            JpaUtil.validerTransaction();
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
            res = false;
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        //Mail generation
        StringWriter corps = new StringWriter();
        PrintWriter mailWriter = new PrintWriter(corps);
        if(res)
        {
            mailWriter.println("Bonjour,");
            mailWriter.println();
            mailWriter.println("Votre compte à été crée .. vous pouvez vous connecter");
            mailWriter.println();
            mailWriter.println("Cordialement,");
            mailWriter.println();
            mailWriter.println("L'équipe Predict'IF");

            Message.envoyerMail(
                    "equipe.predictif@gmail.com",
                    client.getMail(),
                    "Confirmation inscritpion Predict'IF",
                    corps.toString()
                );
        }
        else
        {
            mailWriter.println("Bonjour,");
            mailWriter.println();
            mailWriter.println("Une erreur est survenue durant la création de votre compte Predict'if");
            mailWriter.println("Veuillez réessayer");
            mailWriter.println();
            mailWriter.println("Cordialement,");
            mailWriter.println();
            mailWriter.println("L'équipe Predict'IF");

            Message.envoyerMail(
                    "equipe.predictif@gmail.com",
                    client.getMail(),
                    "Echec inscritpion Predict'IF",
                    corps.toString()
                );
        }
        
        return res;
    }

    /**
     * Identify a client with his mail and password
     * @param mail mail of the client
     * @param password password of the client that correspond to the mail
     * @return Client The authenticate client.
    */
    public Client authenticateClient(String mail, String password)
    {
        ClientDao clientdao = new ClientDao();
        Client client = null;
        
        JpaUtil.creerContextePersistance();
        try
        {
            client = clientdao.find(mail);
            
            if(client != null)
            {
                if(!client.getPassword().equals(password))
                {
                    client = null;
                }
            }
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return client;
    }
    
    /**
     * Identify an employee with his mail and password
     * @param mail mail of the employee
     * @param password password of the employee that correspond to the mail
     * @return Employee The authenticate employee.
    */
    public Employee authenticateEmployee(String mail, String password)
    {
        EmployeeDao employedao = new EmployeeDao();
        Employee employee = null;
        
        JpaUtil.creerContextePersistance();
        try
        {
            employee = employedao.find(mail);
            
            if(employee != null)
            {
                if(!employee.getPassword().equals(password))
                {
                    employee = null;
                }
            }
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return employee;
    }
    
    /**
     * Get the list of all the medium
     * @return List<Medium> the list of all the medium
    */
    public List<Medium> consultMediumList()
    {
        MediumDao mediumdao = new MediumDao();
        List<Medium> mediumList = null;
        
        JpaUtil.creerContextePersistance();
        try
        {
            mediumList = mediumdao.findAll();
        }
        catch(Exception ex)
        {
            
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return mediumList;
    }
    
    /**
     * Create a consultation
     * @param idMedium if of the selected medium for the consultation
     * @param client client for the consultation
     * @return boolean True consultation has been created
    */
    public boolean askConsultation(int idMedium, Client client)
    {
        boolean res = false;
        
        MediumDao mediumdao = new MediumDao();
        ClientDao clientdao = new ClientDao();
        EmployeeDao employeedao = new EmployeeDao();
        ConsultationDao consultationdao = new ConsultationDao();
        
        Medium medium = null;
        Employee employee = null;
        Consultation consultation = new Consultation(client);
        client.addConsultation(consultation);
        
        JpaUtil.creerContextePersistance();
        
        try
        {
            medium = mediumdao.find(Long.valueOf(idMedium));
            employee = employeedao.findAvailable(medium.getGender());
            if(employee != null && medium != null)
            {
                consultation.setMedium(medium);
                consultation.setEmployee(employee);
                employee.setDisponibility(false);
                
                JpaUtil.ouvrirTransaction();
            
                consultationdao.create(consultation);
                clientdao.modify(client);
                employeedao.modify(employee);
                
                JpaUtil.validerTransaction();
            
                res = true;
            }
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        if(res)
        {
            String msg = "Bonjour " + employee.getFirstName() + ". Consultation requise pour " + client.getCivilite() + " " + client.getFirstName() + " " + client.getLastName() + ". Médium à incarner : " + medium.getGender() + " " + medium.getDenomination();
            Message.envoyerNotification(employee.getPhone(), msg);
        }
        
        return res;
    }
    
    /**
     * begin a consultation
     * @param employee employee concerned by this consultation
     * @return boolean True if update worked, false either
    */
    public boolean beginConsultation(Employee employee)
    {
        boolean res = false;
        ConsultationDao consultationdao = new ConsultationDao();
        
        Consultation consultation = null;
        JpaUtil.creerContextePersistance();
        
        try
        {
            consultation = consultationdao.findCurrent(employee);
            if(consultation != null)
            {
                if(consultation.getHourBeginConsultation() == null)
                {
                    consultation.setHourBeginConsultation(new Date());

                    JpaUtil.ouvrirTransaction();

                    consultationdao.modify(consultation);

                    JpaUtil.validerTransaction();

                    res = true;   
                }
            }
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        if(res)
        {
            String msg = "Bonjour " + consultation.getClient().getFirstName() + ". J’ai bien reçu votre demande de consultation du " + consultation.getHourAskConsultation().toString() + ". Vous pouvez dès à présent me contacter au " + consultation.getEmployee().getPhone() + ".  A tout de suite ! Médiumiquement vôtre, " + consultation.getMedium().getGender() + " " + consultation.getMedium().getDenomination();
            Message.envoyerNotification(consultation.getClient().getPhone(), msg);
        }
        
        return res;
    }
    
    /**
     * End a consultation
     * @param employee employee concerned by this consultation
     * @param comment comment of the consultation
     * @return boolean True if update worked, false either
    */
    public boolean endConsultation(Employee employee, String comment)
    {
        boolean res = false;
        EmployeeDao employeedao = new EmployeeDao();
        ConsultationDao consultationdao = new ConsultationDao();
        
        JpaUtil.creerContextePersistance();
        
        try
        {
            Consultation consultation = consultationdao.findCurrent(employee);
            if(consultation != null)
            {
                if(consultation.getHourBeginConsultation() != null && consultation.getHourEndConsultation()== null)
                {
                    consultation.setHourEndConsultation(new Date());
                    consultation.setComment(comment);
                    employee.setDisponibility(true);

                    JpaUtil.ouvrirTransaction();

                    consultationdao.modify(consultation);
                    employeedao.modify(employee);

                    JpaUtil.validerTransaction();

                    res = true;   
                }
            }
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return res;
    }
    
    /**
     * Get the client from the current consultation of an employee
     * @param employee employee concerned by this consultation
     * @return Client
    */
    public Client getClientFromEmployee(Employee employee)
    {
        boolean res = false;
        ConsultationDao consultationdao = new ConsultationDao();
        Client client = null;
        
        JpaUtil.creerContextePersistance();
        
        try
        {
            Consultation consultation = consultationdao.findCurrent(employee);
            if(consultation != null)
            {
                client = consultation.getClient();
            }
        }
        catch(Exception ex)
        {
            JpaUtil.annulerTransaction();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        return client;
    }
    
    /**
     * begin a consultation
     * @param employee employee concerned by the consultation
     * @param love mark for love prediction
     * @param health mark for love prediction
     * @param work mark for love prediction
     * @return List<String> generated predictions
    */
    public List<String> generatePrediction(Employee employee, int love, int health, int work)
    {
        if((love > 4 || love < 0) ||  (health > 4 || health < 0) || (work > 4 || work < 0))
            return null;
        
        Client client = getClientFromEmployee(employee);
        
        AstroTest astroApi = new AstroTest();
        List<String> predictions = null;
        try
        {
            if(client != null)
            {
                predictions = astroApi.getPredictions(client.getProfilAstral().getLuckyColor(), client.getProfilAstral().getTotemAnimal(), love, health, work);
            }
        }
        catch(Exception ex)
        {
            
        }
        
        return predictions;
    }
    
        
    /**
     * Display all the statistics regarding the company
     * @return LinkedHashMap<Medium, Integer>
    */
    public LinkedHashMap<Medium, Integer> getStatisticsMedium()
    {
        MediumDao mediumdao = new MediumDao();
        
        List<Object[]> mediumsList = null;
        
        LinkedHashMap<Medium, Integer> statsMedium = null;
        
        JpaUtil.creerContextePersistance();
        
        try
        {
            mediumsList = mediumdao.getMediumAndCountConsultation();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        if(mediumsList != null)
        {
            statsMedium = new LinkedHashMap<Medium, Integer>();
            for (Object[] ob : mediumsList)
            {
                statsMedium.put((Medium) ob[0], (int) (long) ob[1]);
            }
        }
        
        return statsMedium;
    }
    
        
    /**
     * Display all the statistics regarding the company
     * @return LinkedHashMap<Medium, Integer>
    */
    public LinkedHashMap<Employee, LinkedHashMap<Client, Integer>> getStatisticsEmployee()
    {
        ConsultationDao consultationdao = new ConsultationDao();
        
        List<Object[]> statsEmployeeList = null;
        
        LinkedHashMap<Employee, LinkedHashMap<Client, Integer>> statsEmployee = null;
        
        JpaUtil.creerContextePersistance();
        
        try
        {
            statsEmployeeList = consultationdao.getEmployeeStats();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            JpaUtil.fermerContextePersistance();
        }
        
        if(statsEmployeeList != null)
        {
            statsEmployee = new LinkedHashMap<Employee, LinkedHashMap<Client, Integer>>();
            for (Object[] ob : statsEmployeeList)
            {
                if(!statsEmployee.containsKey((Employee) ob[0]))
                {
                    LinkedHashMap<Client, Integer> value = new LinkedHashMap<Client, Integer>();
                    value.put((Client)ob[1], (int)(long)ob[2]);
                    statsEmployee.put((Employee)ob[0], value);
                }
                else
                {
                    statsEmployee.get((Employee) ob[0]).put((Client)ob[1], (int)(long)ob[2]);
                }
            }
        }
        
        return statsEmployee;
    }
    
}
