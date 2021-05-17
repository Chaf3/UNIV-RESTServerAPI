/**
 *
 * @author alexandre chafaux
 * DataError représente le code d'erreur envoyé si la reqûete demandé n'est pas valide. 
 * J'ai choisi 404 par défaut car c'est une erreur qu'on retrouve de manière récurrente. 
 * 
 */
package client_and_data;


public class DataError {
    protected int error = 404; 
    
    public int getError(){
        return error; 
    }
    
}
