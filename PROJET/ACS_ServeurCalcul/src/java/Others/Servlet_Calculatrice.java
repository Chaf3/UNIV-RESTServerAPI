/**
 *
 * @author alexandre chafaux
 * Servlet_Calculatrice : Représente la servlet qui va gérer le traitement de la calculatrices
 */
package Others;

import client_and_data.DataRequestCalc;
import client_and_data.DataError;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet(name = "Servlet_Calculatrice", urlPatterns = {"/calculatrice"})
public class Servlet_Calculatrice extends HttpServlet {

    private String jsonSender;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

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
        
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
          

            // On récupère les paramètres envoyés 
            String value1 = request.getParameter("v1");
            String value2 = request.getParameter("v2");
            String operator = request.getParameter("operator");

            if (checkArguments(value1, value2, operator) || checkArgumentsValue(value1, value2, operator)) {
                DataError error = new DataError();
                jsonSender = gson.toJson(error);
            } else {

                double v1 = Double.valueOf(value1);
                double v2 = Double.valueOf(value2);

                if (checkDivisionParZero(operator, v2)) {
                    DataError error = new DataError();
                    jsonSender = gson.toJson(error);
                } else {

                    DataRequestCalc responseData = doOperation(v1, v2, operator);
                    jsonSender = gson.toJson(responseData);
                }

            }
           
            out.println(jsonSender);
            
        }
    }

    
    // Fonction qui vérifie les arguments entrés par l'utilisateur
    
    private boolean checkArguments(String value1, String value2, String operator) {

        return (value1 == null || value2 == null || operator == null);
    }

    // Fonction qui vérifie les valeurs des attributs 
    
    private boolean checkArgumentsValue(String value1, String value2, String operator) {

        boolean check = false;

        try {
            Double.valueOf(value1);
        } catch (Exception e) {
            System.out.println("Erreur argument");
            check = true;
        }

        try {
            Double.valueOf(value2);
        } catch (Exception e) {
            System.out.println("Erreur argument");
            check = true;
        }

        switch (operator) {
            case "+":
            case "-":
            case "/":
            case "*":
            case "inverse":
            case "racine":
                break;
            default:
                check = true;
        }

        return check;
    }

    // Fonction pour la division par 0
    
    private boolean checkDivisionParZero(String operator, double value) {
        return (operator.equals("/") && value == 0);
    }

    // Fonction qui effectue le calcul et renvoie une réponse 
    
    private DataRequestCalc doOperation(double value1, double value2, String operator) {

        // On déclare 3 booleans qui sont là pour assurer le contrôle du type de la valeur de retour. 
        boolean checkIntV1 = false;
        boolean checkIntV2 = false;
        boolean isInt = false;
        double res = 0;

        // On teste les valeurs en paramètres pour savoir si ce sont des entiers.
        
        if ((value1 % 1) == 0) {
            checkIntV1 = true;
        }

        if ((value2 % 1) == 0) {
            checkIntV2 = true;
        }

        // Si les deux paramètres sont des entiers, on passe le boolean Int à vrai. 
        if (checkIntV1 && checkIntV2) {
            isInt = true;
        }

        // On effectue l'opération désormais 
        switch (operator) {
            case ("+"):
                res = value1 + value2;
                break;
            case ("-"):
                res = value1 - value2;
                break;
            case ("*"):
                res = value1 * value2;
                break;
            case ("/"):
                res = value1 / value2;
                break;
            case ("inverse"):
                if (value2 == 0) {
                    res = 1 / value1;
                }
                break;
            case ("racine"):
                if (value2 == 0) {
                    res = Math.sqrt(value1);
                }
                break;

        }

        DataRequestCalc retData = new DataRequestCalc(res, isInt);

        return retData;
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
