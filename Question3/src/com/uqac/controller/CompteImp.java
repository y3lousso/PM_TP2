package com.uqac.controller;

import com.uqac.Compte;

public class CompteImp implements Compte {

    private final String numero;
    private final String Nom;
    private final String Telephone;
    private final String service;
    private double solde;

    public CompteImp(String numero, String nom, String telephone, String service, double solde) {
        this.numero = numero;
        this.Nom = nom;
        this.Telephone = telephone;
        this.service = service;
        this.solde = solde;
    }

    @Override
    public void deposer(double montant) {

        this.setSolde(this.getSolde() + montant);

    }

    @Override
    public String getService() {
        return this.service;
    }

    @Override
    public String getNom() {
        return this.Nom;
    }

    @Override
    public String getNumeroTel() {
        return this.Telephone;
    }

    @Override
    public String getNumero() {
        return this.numero;
    }

    @Override
    public double getSolde() {
        return this.solde;
    }

    private void setSolde(double nouveauSolde) {
        this.solde = nouveauSolde;
    }

    @Override
    public String retirer(double montant) {
        String message;
        if (this.getSolde() - montant < 0) {
            message = "Solde insuffisant pour effectuer cette op�ration !";
        } else {
            this.setSolde(this.getSolde() - montant);
            message = "ok";
        }
        return message;

    }

}
