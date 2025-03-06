import java.io.*; // Uvoz vseh razredov iz knjižnice java.io, ki omogoča delo z vhodno-izhodnimi operacijami
import java.util.*; // Uvoz vseh razredov iz knjižnice java.util

/**
 * Razred Omara predstavlja model osnovne omare, ki jo lahko odpremo, zapremo in vanjo dodajamo predmete.
 * Ta razred služi kot osnova za podrazrede PisarniskaOmara in GarderobnaOmara.
 * Dodana so preverjanja za dimenzije omare in metanje izjem.
 * Omogoča preverjanje, ali je omara polna.
 *
 * @author Domen Gorišek
 * @version Vaja 36 - Grafični uporabniški vmesnik (GUI) in spreminjanje lastnosti objektov
 */
public class Omara {
    
    /**
     * Višina omare v centimetrih.
     */
    private int visina;
    
    /**
     * Širina omare v centimetrih.
     */
    private int sirina;
    
    /**
     * Stanje odprtosti omare (true - odprta, false - zaprta).
     */
    private boolean jeOdprta;
    
    /**
     * Vrsta omare (opis).
     */
    private String vrstaOmare;
    
    /**
     * Konstruktor za inicializacijo omare.
     * Preveri, ali so dimenzije omare v dovoljenem obsegu.
     *
     * @param v Višina omare (cm)
     * @param s Širina omare (cm)
     * @param vrsta Vrsta omare
     * @throws IllegalArgumentException Če so dimenzije izven dovoljenega obsega
     */
    public Omara(int v, int s, String vrsta) {
		// Preverimo, ali višina ustreza dovoljenim mejam
        if (v < 20 || v > 300) {
            throw new IllegalArgumentException("Napaka: Višina omare mora biti med 20 in 300 cm.");
        }
		// Preverimo, ali širina ustreza dovoljenim mejam
        if (s < 20 || s > 500) {
            throw new IllegalArgumentException("Napaka: Širina omare mora biti med 20 in 500 cm.");
        }
        
        this.visina = v;
        this.sirina = s;
        this.jeOdprta = false; // Privzeto je omara zaprta
        this.vrstaOmare = vrsta;
        System.out.println("\nUstvarjena " + vrstaOmare + " velikosti  " + v + "cm X " + s +  "cm.");
    }
    
    /**
     * Vrne višino omare.
     *
     * @return Višina omare v cm.
     */
    public int getVisina() {
        return visina;
    }
    
    /**
     * Nastavi novo višino omare.
     *
     * @param visina Nova višina omare.
     */
    public void setVisina(int visina) {
        this.visina = visina;
    }
    
    /**
     * Vrne širino omare.
     *
     * @return Širina omare v cm.
     */
    public int getSirina() {
        return sirina;
    }
    
    /**
     * Nastavi novo širino omare.
     *
     * @param sirina Nova širina omare.
     */
    public void setSirina(int sirina) {
        this.sirina = sirina;
    }
    
    /**
     * Vrne vrsto omare.
     *
     * @return Vrsta omare kot niz.
     */
    public String getVrstaOmare() {
        return vrstaOmare;
    }
    
    /**
     * Nastavi novo vrsto omare.
     *
     * @param vrstaOmare Nova vrsta omare.
     */
    public void setVrstaOmare(String vrstaOmare) {
        this.vrstaOmare = vrstaOmare;
    }
    
    /**
     * Vrne niz, ki predstavlja omaro.
     *
     * @return Podatki o omari.
     */
    @Override
    public String toString() {
        return "Omara [" + vrstaOmare + "] - Višina: " + visina + " cm, Širina: " + sirina + " cm";
    }
    
    /**
     * Metoda za odpiranje omare.
     *
     * @return true, če je bila uspešno odprta, false če je bila že odprta
     */
    public boolean odpri() {
        if (jeOdprta) {
			// Preveri, ali je omara že odprta
            System.out.println(vrstaOmare + " je že odprta.");
            return false;
        }
        jeOdprta = true; // Nastavi omaro na odprto
        System.out.println("Odpiram " + vrstaOmare + ".");
        return true;
    }
    
    /**
     * Metoda za zapiranje omare.
     *
     * @return true, če je bila uspešno zaprta, false če je bila že zaprta
     */
    public boolean zapri() {
        if (!jeOdprta) {
			// Preveri, ali je omara že zaprta
            System.out.println(vrstaOmare + " je že zaprta.");
            return false;
        }
        jeOdprta = false; // Nastavi omaro na zaprto
        System.out.println("Zapiram " + vrstaOmare + ".");
        return true;
    }
    
    /**
     * Metoda za preverjanje, ali je omara odprta.
     *
     * @return true, če je omara odprta, false če je zaprta
     */
    public boolean jeOmaraOdprta() {
        return jeOdprta;
    }
    
    /**
     * Metoda za dodajanje predmeta v omaro.
     * Predmet se lahko doda samo, če je omara odprta.
     *
     * @param predmet Ime predmeta, ki ga dodajamo
     */
    public void dodajPredmet(String predmet) {
		// Če omara ni odprta, ne moremo dodati predmeta
        if (!jeOdprta) {
            System.out.println("Omara je zaprta! Odpri jo, preden dodaš " + predmet + ".");
            return;
        }
		// Preverimo, ali je predmet prazen niz
        if (predmet.trim().isEmpty()) {
            System.out.println("Napaka: Predmet ne sme biti prazen!");
            return;
        }
		// Izpis dodanega predmeta
        System.out.println("Dodajam " + predmet + " v " + vrstaOmare + ".");
    }
}