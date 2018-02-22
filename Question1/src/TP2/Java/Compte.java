package TP2.Java;

public interface Compte {
	
	String getNumero();
	String getService();
	String getNom();
	String getNumeroTel();
	double getSolde();
	void deposer( double montant );
	String retirer( double montant );

}
