/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao.JpaUtil;
import Metiers.Modeles.Client;
import Metiers.Modeles.Employee;
import Metiers.services.Service;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Romain
 */
public class Main {
    
    public static void main(String args[]) {
        JpaUtil.init();
        
        boolean loop = true;
        
        Client client = null;
        Employee employee = null;
        
        //main loop => front end
        while(loop)
        {
            int selection;
            Scanner input = new Scanner(System.in);
            
            System.out.println("Predict'if");
            System.out.println("-------------------------\n");
            System.out.println("1 - Connect as client");
            System.out.println("2 - Register as client");
            System.out.println("3 - Connect as employee");
            System.out.println("4 - Quit");
        
            selection = input.nextInt();
            
            switch(selection)
            {
                case 1:
                    authentifyClientService();
                    break;
                case 2:
                    registerClientService();
                    break;
                case 3:
                    authentifyEmployeeService();
                    break;
                case 4:
                    loop = false;
                    break;
                default:
                    break;    
            }
        }
        
        JpaUtil.destroy();
    }
    
    public static void registerClientService()
    {
        Service service = new Service();
        boolean inscription = service.registerClient("Romain", "Chikirou", "romain.chikirou@gmail.com", "1234", "06", new Date(), "rue de", "Lyon", 69100);
        if(inscription)
        {
            System.out.println("Succès inscription");
        }
        else
        {
            System.out.print("Echec inscription");
        }
    }
    
    public static void authentifyClientService()
    {
        Service service = new Service();
        String mail, password;
        
        
        System.out.println("mail ? : ");
        Scanner input = new Scanner(System.in);
        mail = input.nextLine();
        
        System.out.println("password ? : ");
        password = input.nextLine();
        
        Client tmpClient = service.authenticateClient(mail, password);
        
        if(tmpClient != null)
        {
            System.out.println("Connexion réussie");
        }
        else
        {
            System.out.println("erreur lors de la connexion");
        }
    }
    
    public static void authentifyEmployeeService()
    {
        Service service = new Service();
        String mail, password;
        
        
        System.out.println("mail ? : ");
        Scanner input = new Scanner(System.in);
        mail = input.nextLine();
        
        System.out.println("password ? : ");
        password = input.nextLine();
        
        Employee tmpEmployee = service.authenticateEmployee(mail, password);
        
        if(tmpEmployee != null)
        {
            System.out.println("Connexion réussie");
        }
        else
        {
            System.out.println("erreur lors de la connexion");
        }
    }
    
}
