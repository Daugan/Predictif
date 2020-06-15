/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Action.Action;
import Action.AuthenticateClientAction;
import Action.AuthenticateEmployeeAction;
import Action.ListMediumAction;
import Action.RegisterClientAction;
import Dao.JpaUtil;
import Serialization.AuthenticateClientSerialization;
import Serialization.AuthenticateEmployeeSerialization;
import Serialization.ListMediumSerialization;
import Serialization.RegisterClientSerialization;
import Serialization.Serialization;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Romain
 */
@WebServlet(name = "ActionServlet", urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // session initialisation
        HttpSession session = request.getSession(true);
        //encoding parameters
        request.setCharacterEncoding("UTF-8");
        
        //GET or POST parameter
        String todo = request.getParameter("todo");
        
        Action action = null;
        Serialization serialization = null;
        
        if(todo != null)
        {
            switch(todo)
            {
                case "connect":
                    action = new AuthenticateClientAction();
                    serialization = new AuthenticateClientSerialization();
                    break;
                case "register":
                    action = new RegisterClientAction();
                    serialization = new RegisterClientSerialization();
                case "authenticateClient":
                    break;
                case "intraConnect":
                    action = new AuthenticateEmployeeAction();
                    serialization = new AuthenticateEmployeeSerialization();
                    break;
                case "medium":
                    action = new ListMediumAction();
                    serialization = new ListMediumSerialization();
                    break;
                default:
                    break;
            }
        }
        
        if (action != null) {
            action.execute(request);
            serialization.serialize(request, response);
        }
        else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erreur dans les paramètres de la requête");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
