package service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import server_javacard.Client;

public class Service {
    public static String messageRendu;
	
	public static void erreur(String message) {
		JOptionPane jop3 = new JOptionPane();
        jop3.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
	}

	public static String byteToHexString(byte b) {
		StringBuffer s = new StringBuffer();
		s.append("0x");
		if ((b & 0xff) < 0x10)
			s.append("0");
		s.append(Integer.toHexString(b & 0xff).toUpperCase());
		return s.toString();
	}

	public static String byteArrayToHexString(byte[] buffer, String separator) {
		StringBuffer s = new StringBuffer();
		int i;
		for (i = 0; i < buffer.length - 1; i++)
			s.append(byteToHexString(buffer[i]) + separator);
		if (i >= 0)
			s.append(byteToHexString(buffer[i]));
		return s.toString();
	}

	public static byte[] hexStringToByteArray(String s) {
		if (s == null)
			return null;
		s = s.replaceAll(" ", "").replaceAll(":", "").replaceAll("0x", "")
				.replaceAll("0X", "");
		if (s.length() % 2 != 0)
			throw new IllegalArgumentException("The length cannot be odd.");
		byte[] output = new byte[s.length() / 2];
		for (int i = 0; i < s.length(); i += 2)
			output[i / 2] = (byte) Integer.parseInt(s.substring(i, i + 2), 16);
		return output;
	}

	public static String hexToASCII(String s)
			throws UnsupportedEncodingException {
		byte[] buffer = hexStringToByteArray(s);
		return new String(buffer, "ASCII");
	}

	public static String ASCIIToHex(String s) {
		byte[] buffer = s.getBytes();
		return byteArrayToHexString(buffer, ":");
	}

	public static String motDePasse(String codeSec) {
		String x = "";
		for(int i = 0;i<codeSec.length();i++){
			x=x.concat("*");
		}
		return x.trim();
	}

    public static ArrayList<Client> listeClient() {
        return (new BD()).listeClientsBanque();
    }
    
    public static int genererNumCompte() {
        return (new BD()).genereNumCompte();
    }

    public static void saveEtat(String trim, boolean p) {
        if(p==true){(new BD()).verrouCarte(trim);}else{(new BD()).deverrouCarte(trim);}
        
    }

    public static void suppClient(String trim) {
        (new BD()).supprimerClient(trim);
    }
}
