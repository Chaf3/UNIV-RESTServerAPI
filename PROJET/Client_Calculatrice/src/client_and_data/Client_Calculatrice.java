/**
 *
 * @author alexandre chafaux
 * Classe Client_Calculatrice : Correspond au client qui envoie une requête de calcul vers la servlet.
 *
 */
package client_and_data;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static java.lang.System.out;
import java.util.Scanner;

public class Client_Calculatrice {

    public static void main(String[] args) throws MalformedURLException {

        try {

            String url_link = "http://"+args[0]+"/ACS_ServeurCalcul/calculatrice";
            String url_complete;

            String command;
            String[] command_tab;

            String v1, v2, operator;

            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Saissez l'operation à suivre");
                command = scanner.nextLine();
                System.out.println();
                command_tab = command.split(" ");

                if (command_tab.length != 3) {
                    if (command_tab.length == 1) {
                        if (command_tab[0].equals("stop")) {
                            System.out.println("Fin de la communication");
                            System.exit(0);
                        }
                    }

                    System.out.println("Veuillez entrer au moins 3 arguments (v1 operator v2). Mettez 0 pour v2 si vous voulez réaliser une operation unaire.");
                    System.out.println(command);
                    System.out.println(command_tab[0]);

                } else {
                    if (command_tab[1].equals("+")) {
                        command_tab[1] = "%2B";
                    }

                    v1 = "?v1=" + command_tab[0];
                    operator = "&operator=" + command_tab[1];
                    v2 = "&v2=" + command_tab[2];

                    url_complete = url_link + v1 + operator + v2;

                    System.out.println("Transmission par URL vers le serveur ...");
                    System.out.println(url_complete);

                    URL url = new URL(url_complete);
                    HttpURLConnection link = (HttpURLConnection) url.openConnection();
                    link.setRequestMethod("GET");
                    System.out.println("Connexion au serveur ... ");
                    link.connect(); // Connexion au serveur

                    System.out.println("Creation des flux ... ");

                    InputStream input = link.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
                    String json_text = "";
                    String json_line;

                    while ((json_line = reader.readLine()) == null) {
                        Thread.sleep(200);
                    }

                    json_text += json_line;
                    while ((json_line = reader.readLine()) != null) {
                        json_text += json_line;
                    }

                    System.out.println("Récupération du code d'erreur ... ");

                    DataError error = gson.fromJson(json_text, DataRequestCalc.class);

                    if (error.getError() == 404) {
                        System.out.println("Erreur 404. La requête n'est pas valide.");

                    } else {
                        DataRequestCalc data = (DataRequestCalc) error;
                        System.out.print("Votre résultat vaut : ");
                        out.println(json_text); 
                    }

                }

            }

        } catch (Exception e) {
            System.out.println("Exception levée \n");
            e.printStackTrace();
        }

    }

}
