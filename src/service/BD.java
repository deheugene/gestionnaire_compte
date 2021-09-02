/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import server_javacard.Client;

/**
 *
 * @author DEH EUGENE
 */
public class BD {

    String user = "root";
    String passwd = "";
    String url = "jdbc:mysql://localhost:3306/banque";
    String strClassName = "com.mysql.jdbc.Driver";
    Connection conn = null;
    java.sql.Statement stmt = null;
    private Client client = null;
    private String requete;

    public BD() {
        try {
            Class.forName(strClassName);
        } catch (ClassNotFoundException ex) {
            Service.erreur("Veuiller vérifer la config de la base de données de vvotre application");
        }

        try {
            if (conn == null) {
                conn = DriverManager.getConnection(url, user, passwd);
            }

        } catch (SQLException ex) {
            Service.erreur("Le driver n'a pas pu être chargé");
        }

        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Service.erreur("erreur : connection non établie");
        }
    }

    public ResultSet executeQuerry(String requete) {
        try {
            stmt.executeQuery(requete);
        } catch (SQLException ex) {
            Service.erreur(ex.getMessage());
        }
        ResultSet rs = null;
        try {
            rs = stmt.getResultSet();
        } catch (SQLException ex) {
            Service.erreur("erreur : résutats non retourné");
        }
        return rs;
    }

    void close() {
        try {
            conn.close();
        } catch (SQLException ex) {
            Service.erreur("erreur 7");
        }
    }

    public Client lireClient(String numcompte) {
        requete = "SELECT * FROM CLIENTS WHERE numcompte='" + numcompte.trim() + "'";
        // String requete = "SELECT * FROM CARTE";
        ResultSet rs = executeQuerry(requete);
        if (rs != null) {
            try {
                while (rs.next()) {
                    client = new Client(
                            rs.getString("numcompte").trim(),
                            rs.getString("nom").trim(),
                            rs.getString("prenom").trim(),
                            rs.getString("sexe").trim(),
                            rs.getString("cni").trim(),
                            rs.getString("code").trim(),
                            rs.getInt("solde"),
                            rs.getInt("etat"),
                            rs.getInt("etat_supp"));
                }
            } catch (SQLException ex) {
                Service.erreur("erreur : acces aux champ");
            }
        }
        close();
        return client;
    }

    public void saveClient(Client client) {
        requete = "INSERT INTO CLIENTS  VALUES('" + client.getNumCompt() + "','" + client.getNom() + "','" + client.getPrenom() + "'," + client.getSolde() + ",'" + client.getCode() + "'," + 1 + ",'" + client.getSexe() + "','" + client.getCni()+ "','" +client.getEtatSupp()+ "')";
        executeUpdate(requete);
        
    }
public void saveClient1(Client client) {
       requete = "UPDATE CLIENTS SET nom='"+client.getNom()+"',prenom='" + client.getPrenom()+"',sexe='" + client.getSexe()+"',cni='" + client.getCni()+"',solde=" + client.getSolde() + " WHERE numcompte='"
                + client.getNumCompt()+ "'";
       //Service.erreur(requete);
       executeUpdate(requete);
        
    }
    public void saveSolde(int solde, String numCompt) {
        requete = "UPDATE CLIENTS SET solde='" + solde + "' WHERE numcompte='"
                + numCompt + "'";
        executeUpdate(requete);
    }

    private void executeUpdate(String requete2) {
        try {
            stmt.executeUpdate(requete2);
            Service.messageRendu="Mise à jour effectuée avec succès";
        } catch (SQLException e) {
            Service.erreur("Errreur de mise à jour");
        }
        close();
    }

    public void deverrouCarte(String numCompt) {
        requete = "UPDATE CLIENTS SET etat='" + 1 + "' WHERE numcompte='" + numCompt + "'";
        executeUpdate(requete);

    }
    
    public void verrouCarte(String numCompt) {
        requete = "UPDATE CLIENTS SET etat='" + 0 + "' WHERE numcompte='" + numCompt + "'";
        executeUpdate(requete);

    }

    ArrayList<Client> listeClientsBanque() {
        requete = "SELECT * FROM CLIENTS WHERE etat_supp='"+ 0 + "'ORDER BY nom";
        ArrayList<Client> array = new ArrayList<Client>();
        ResultSet rs = executeQuerry(requete);
        if (rs != null) {
            try {
                while (rs.next()) {
                    array.add(new Client(
                            rs.getString("numcompte").trim(),
                            rs.getString("nom").trim(),
                            rs.getString("prenom").trim(),
                            rs.getString("sexe").trim(),
                            rs.getString("cni").trim(),
                            rs.getString("code").trim(),
                            rs.getInt("solde"),
                            rs.getInt("etat"),
                            rs.getInt("etat_supp")));
                }
            } catch (SQLException ex) {
                Service.erreur("erreur : acces aux champ");
            }
        }
        close();
        return array;
    }

    int genereNumCompte() {
        requete = "SELECT * FROM CLIENTS ORDER BY numcompte";
        int ret = 0;
        ResultSet rs = executeQuerry(requete);
        if (rs != null) {
            try {
                if (rs.last()) {
                    ret = rs.getInt("numcompte");
                }
                }  catch (SQLException ex) {
                Service.erreur("erreur : acces aux champ");
            }
        }
        close();
        return ret;
    }

    void supprimerClient(String numCompt) {
    	requete = "UPDATE CLIENTS SET etat_supp='" + 1 + "' WHERE numcompte='" + numCompt + "'";
        executeUpdate(requete);
    }
}
