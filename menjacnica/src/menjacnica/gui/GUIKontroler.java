package menjacnica.gui;

import java.awt.EventQueue;
import java.io.File;
import java.util.LinkedList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import menjacnica.Menjacnica;
import menjacnica.MenjacnicaInterface;
import menjacnica.Valuta;

public class GUIKontroler {

	public static MenjacnicaGUI glavniProzor;
	public static MenjacnicaInterface menjacnica;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					glavniProzor = new MenjacnicaGUI();
					glavniProzor.setVisible(true);
					glavniProzor.setLocationRelativeTo(null);
					menjacnica = new Menjacnica();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void ugasiAplikaciju() {
		int opcija = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(),
				"Da li ZAISTA zelite da izadjete iz apliacije", "Izlazak",
				JOptionPane.YES_NO_OPTION);

		if (opcija == JOptionPane.YES_OPTION)
			System.exit(0);
	}
	
	public static void prikaziDodajKursGUI() {
		DodajKursGUI prozor = new DodajKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	public static void unesiValutu(String naziv, String skraceniNaziv, int sifra, String prodajni, String kupovni, String srednji){
		Valuta valuta = new Valuta();
		valuta.setNaziv(naziv);
		valuta.setSkraceniNaziv(skraceniNaziv);
		valuta.setSifra(sifra);
		valuta.setProdajni(Double.parseDouble(prodajni));
		valuta.setKupovni(Double.parseDouble(kupovni));
		valuta.setSrednji(Double.parseDouble(srednji));
		menjacnica.dodajValutu(valuta);
		glavniProzor.prikaziSveValute();
	}
	
	public static LinkedList<Valuta> vratiKursnuListu(){
		return menjacnica.vratiKursnuListu();
	}
	
	public static void pokreniObrisiKursGUI(){
		ObrisiKursGUI prozor = new ObrisiKursGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
	public static Valuta prikaziIzabranuValutu(){
		return glavniProzor.vratiIzabranuValutu();
	}
	
	public static void obrisiValutu(){
		menjacnica.obrisiValutu(glavniProzor.vratiIzabranuValutu());
		glavniProzor.prikaziSveValute();
	}
	
	public static void prikaziIzvrsiZamenuProzor(){
		IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI();
		prozor.setLocationRelativeTo(null);
		prozor.setVisible(true);
	}
	
	public static void ucitajIzFajla() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showOpenDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				menjacnica.ucitajIzFajla(file.getAbsolutePath());
				glavniProzor.prikaziSveValute();
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void sacuvajUFajl() {
		try {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(glavniProzor.getContentPane());

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();

				menjacnica.sacuvajUFajl(file.getAbsolutePath());
			}
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor.getContentPane(), e1.getMessage(), "Greska",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	public static void prikaziAboutDijalog(){
		JOptionPane.showMessageDialog(glavniProzor.getContentPane(),
				"Autor: Bojan Tomic, Verzija 1.0", "O programu Menjacnica",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static double izvrsiTransakciju(boolean prodaja, String broj){
		Valuta v = glavniProzor.vratiIzabranuValutu();
		double iznos = Double.parseDouble(broj);
		
		return menjacnica.izvrsiTransakciju(v, prodaja, iznos);
	}
}
