/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metiers.services;

import Dao.ClientDao;
import Dao.EmployeeDao;
import Dao.JpaUtil;
import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
import Metiers.Modeles.Medium;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Romain
 */
public class Service {
    
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
    public boolean checkClientInput(String lastName, String firstName, Date birthDate, String street, String city, int cp, String phone, String mail, String password)
    {
        //TODO
        return false;
    }
    
    /**
     * create the corresponding client
     * @param lastName lastName of the client
     * @param firstName firstName
     * @param birthDate birthday date of the client
     * @param street street address
     * @param city city address
     * @param cp cp address
     * @param phone phone of the client
     * @param mail mail of the client
     * @param password password of the client
     * @return boolean True if client has been correctly created
    */
    public boolean registerClient(String firstName, String lastName, String mail, String password, String phone, Date birthDate, String street, String city, int cp)
    {
        boolean res = true;
        ClientDao clientdao = new ClientDao();
        
        JpaUtil.creerContextePersistance();
        try
        {
            JpaUtil.ouvrirTransaction();
            
            Client client = new Client(firstName, lastName, mail, password, phone, birthDate, street, city, cp, true);
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
        
        return res;
    }
    
    /**
     * Send a mail with the client's mail as destination
     * @param client client to send mail to
     * @param message message to display
     * @return void
    */
    private void SendMail(Client client, String message)
    {
        //TODO 
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
            JpaUtil.annulerTransaction();
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
                if(employee.getPassword().equals(password))
                {
                    employee = null;
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
        
        return employee;
    }
    
    /**
     * Identify a client with his mail and password
     * @param client client to get data from
     * @return void.
    */
    public void displayClientProfile(Client client)
    {
        //TODO : get astral profile
        //       get all consultation (historic)
    }
    
    /**
     * Get the list of all the medium
     * @return List<Medium> the list of all the medium
    */
    public List<Medium> consultMedium()
    {
        //TODO : call method FindAll of MediumDao
        return null;
    }
    
    /**
     * Check if an employee with the same gender as the medium is available.
     * @param medium selected medium
     * @return boolean True if an employee is available
    */
    public boolean employeeReady(Medium medium)
    {
        //TODO check if an employee with the same gender as the selected medium is available
        return false;
    }
    
    /**
     * Create a consultation
     * @param medium selected medium for the consultation
     * @param client client for the consultation
     * @return boolean True consultation has been created
    */
    public boolean askConsultation(Medium medium, Client client)
    {
        //TODO create a consultation with specific client and medium and employee
        return false;
    }
    
    /**
     * Display all the statistics regarding the company
     * @return void
    */
    public List<String> getStatistics()
    {
        //TODO
        return null;
    }
    
    /**
     * begin a consultation
     * @param employee employee concerned by this consultation
     * @return void
    */
    public void beginConsultation(Employee employee)
    {
        //TODO : set the consultation status as : STARTED
        //       update bdd field => date debut heure voyance
    }
    
    /**
     * End a consultation
     * @param employee employee concerned by this consultation
     * @return void
    */
    public void EndConsultation(Employee employee)
    {
        //TODO : set the consultation status as : ENDED
        //      update bdd field => date fin heure voyance
    }
    
    /**
     * begin a consultation
     * @param love mark for love prediction
     * @param health mark for love prediction
     * @param work mark for love prediction
     * @return List<String> generated predictions
    */
    public List<String> GeneratePrediction(int love, int health, int work)
    {
        //TODO : 1. check parameters values 
        //       2. generate prediction a partir de ifastronet
        return null;
    }
    
    
}
