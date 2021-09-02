package server_javacard;

import service.BD;
import service.Service;

public class Client {
    /* attribut */

    private String nom;
    private String prenom;
    private String numCompt;
    private String sexe;
    private String cni;
    private String code;
    private int solde;
    private int etat;
    private int etatSupp=0;
    private BD bd;

    public Client( String numCompt,String nom, String prenom, String sexe, String cni, String code, int solde, int etat,int etatSupp) {
        this.nom = nom;
        this.prenom = prenom;
        this.numCompt = numCompt;
        this.sexe = sexe;
        this.cni = cni;
        this.code = code;
        this.solde = solde;
        this.etat = etat;
        this.etatSupp = etatSupp;
    }

    public Client() {
    }

    /*méthodes*/
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumCompt() {
        return numCompt;
    }

    public void setNumCompt(String numCompt) {
        this.numCompt = numCompt;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
    

    public int getEtatSupp() {
		return etatSupp;
	}

	public void setEtat_supp(int etat_supp) {
		this.etatSupp = etat_supp;
	}

	void save() {
            bd = new BD();
            bd.saveClient(this);
    }
void update() {
            bd = new BD();
            bd.saveClient1(this);
    }
    public void read(String numCompte) {
        Client cr = new Client();
        bd = new BD();
        cr = bd.lireClient(numCompte);
        if (cr != null) {
            this.nom = cr.getNom();
            this.prenom = cr.getPrenom();
            this.numCompt = cr.getNumCompt();
            this.sexe = cr.sexe;
            this.cni = cr.getCni();
            this.code = cr.getCode();
            this.solde = cr.getSolde();
            this.etat = cr.getEtat();
        }
    }

    public void saveSolde() {
        bd = new BD();
        bd.saveSolde(solde, numCompt);
        Service.messageRendu="le solde de"+this.getNom()+" "+this.getPrenom()+"a été crédité de "+solde+" FCFA";
    }

}
