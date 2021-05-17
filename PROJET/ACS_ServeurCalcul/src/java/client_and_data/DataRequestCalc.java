/**
 *
 * @author alexandre chafaux
 * DataRequest_Calculatrice correspond à la classe qui va envoyé la demande de requête. Elle hérite de DataError_Calculatrice. 
 * La valeur 0 signifie qu'il ni y'a pas eu d'erreur pendant la réception de l'objet. 
 * isPureInt est là pour la précision du type. 
 * 
 */

package client_and_data;

public class DataRequestCalc extends DataError{

    private final double res; 
    private final boolean isPureInt; 
    
    public DataRequestCalc(double R, boolean B){
        error = 0; 
        res = R; 
        isPureInt = B; 
    }
    
     public double getRes() {
        return res;
    }

    public boolean isTrue_type() {
        return isPureInt;
    }
    
}
