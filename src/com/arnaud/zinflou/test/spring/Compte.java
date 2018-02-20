package com.arnaud.zinflou.test.spring;

public interface Compte {
	
	String getNumero();
	String getService();
	String getNom();
	String getNumeroTel();
	double getSolde();
	void deposer( double montant );
	String retirer( double montant );

}
