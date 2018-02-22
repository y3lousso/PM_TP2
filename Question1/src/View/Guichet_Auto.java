package View;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.JToolBar;

import Model.Compte;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Guichet_Auto {
	private JFrame ecran;
	private JLabel soldeEcran;
	private JTextField montantEcran;
	private Compte compte;
	public Guichet_Auto( Compte compte ) {
		this.compte = compte;
		this.construireEcran();
		this.ecran.setTitle( "Banque 8INF956, service " +  this.compte.getService() );
		this.setSoldeEcran( compte.getSolde() );
		}
		private void construireEcran() {
		this.ecran = new JFrame();
		ecran.setSize( 470, 460 );
		// Fermeture de la fenêtre
		ecran.addWindowListener( new WindowAdapter() {
		public void windowClosing( WindowEvent we ) {
		Guichet_Auto.this.terminer();
		}
		} );
		// Le panneau central
		JPanel panneauCentral = new JPanel( new BorderLayout() );
		ecran.add( panneauCentral );
		//nom 
		JPanel panneauNom = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		panneauNom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder()));
		ImageIcon icon = new ImageIcon("banque2.jpg");
		JLabel monlabel=new JLabel("Numéro compte:" + this.compte.getNumero()+ "   Titulaire du compte: " + this.compte.getNom() + "        Téléphone :" + this.compte.getNumeroTel(),icon,JLabel.CENTER );
		monlabel.setVerticalTextPosition(JLabel.BOTTOM);
		monlabel.setHorizontalTextPosition(JLabel.CENTER);
		panneauNom.add(monlabel);
		
		//panneauNom.add( new JLabel( "Titulaire du compte: " + this.compte.getNom() + "        Téléphone :" + this.compte.getNumeroTel() ));
		panneauCentral.add( panneauNom, BorderLayout.NORTH );
				// Le montant
		JPanel panneauMontant = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		panneauMontant.add( new JLabel( "Montant: " ) );
		this.montantEcran = new JTextField( "0,00", 10 );
		panneauMontant.add( this.montantEcran );
		panneauCentral.add( panneauMontant, BorderLayout.LINE_START );
		// Le solde
		this.soldeEcran = new JLabel( " Solde du compte: ", JLabel.CENTER );
		panneauCentral.add( soldeEcran, BorderLayout.CENTER );
		// Les boutons
		JToolBar panneauBouton = new JToolBar();
		panneauBouton.add(Box.createGlue());
		panneauBouton.add(Box.createGlue());
		//JPanel panneauBouton = new JPanel();
		JButton deposer = new JButton( "Dépôt" );
		deposer.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
		Guichet_Auto.this.deposer();
		}
		});
		panneauBouton.add( deposer,panneauBouton.getComponentCount() - 1 );
		//panneauBouton.add(Box.createGlue());
		JButton retirer = new JButton( "Retrait" );
		retirer.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
		Guichet_Auto.this.retirer();
		}
		});
		panneauBouton.add( retirer,panneauBouton.getComponentCount() - 1 );
		//panneauBouton.add(Box.createGlue());
		JButton obtenirSoldeCourant = new JButton( "Consultation solde " );
		obtenirSoldeCourant.addActionListener( new ActionListener() {
		public void actionPerformed( ActionEvent e ) {
		Guichet_Auto.this.obtenirSoldeCourant();
		}
		});
		panneauBouton.add( obtenirSoldeCourant,panneauBouton.getComponentCount() - 1 );
		//panneauBouton.add(Box.createGlue());
		panneauCentral.add( panneauBouton, BorderLayout.SOUTH );
		ecran.setVisible( true );
		}
		private void deposer() {
		try {
			this.compte.deposer( this.getMontant() );
			this.setSoldeEcran( compte.getSolde() );
		} catch ( ParseException e ) {
			JOptionPane.showMessageDialog( this.ecran, "Le montant saisi est invalide" );
		}
		}
		private void retirer() {
		try {
			String message=this.compte.retirer( this.getMontant() );
		if(!message.equals("ok"))
			JOptionPane.showMessageDialog( this.ecran, message );
		this.setSoldeEcran(compte.getSolde());
		} catch ( ParseException e ) {
			JOptionPane.showMessageDialog( this.ecran, "Le montant saisi est invalide" );
		}
		}
		private void obtenirSoldeCourant() {
			this.setSoldeEcran( compte.getSolde() );
		}
		private double getMontant() throws ParseException {
		String montant = this.montantEcran.getText();
		if ( !montant.matches( "\\d{0,7},\\d{0,2}") )
			throw new ParseException( "Erreur", 0 );
		return NumberFormat.getInstance().parse( montant ).doubleValue();
		}
		private void setSoldeEcran( double solde ) {
			this.soldeEcran.
			setText( "Solde du compte:  " + NumberFormat.getCurrencyInstance().format( solde ) );
		}
		private void terminer() {
		
			this.ecran.dispose();			
			System.exit( 0 );
		}

}
