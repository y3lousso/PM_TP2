package com.arnaud.zinflou.test.spring;

public class Implementation_du_Compte implements Compte {
	
	private final String numero;
	private final String Nom;
	private final String Telephone;
	private final String service;
	private double solde;
	private String Message;
	
	public Implementation_du_Compte( String numero, String nom, String telephone,String service ) {
		this.numero = numero;
		this.Nom= nom;
		this.Telephone=telephone;
		this.service=service;
		}
	@Override
	public void deposer(double montant) {
			
		this.setSolde( this.getSolde() + montant );

	}
	public String getService()
	{
		return this.service;
	}
	public String getNom()
	{
		return this.Nom;
	}
	public String getNumeroTel()
	{
		return this.Telephone;
	}
	@Override
	public String getNumero() {
		// TODO Auto-generated method stub
		return this.numero;
	}

	@Override
	public double getSolde() {
		return this.solde;
	}

	@Override
	public String retirer(double montant) {
		if(this.getSolde() - montant<0)
		{
			Message= "Solde insuffisant pour effectuer cette opération !";
		}
		else
		{
			this.setSolde( this.getSolde() - montant );
			Message = "ok";
		}
		return Message;

	}
	
	private void setSolde( double nouveauSolde ) {
		this.solde = nouveauSolde;
	}

}
