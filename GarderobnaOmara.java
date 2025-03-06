/**
 * Razred GarderobnaOmara razširi razred Omara in implementira vmesnik ShranjevalnoMesto.
 * Omogoča shranjevanje in upravljanje obešalnikov.
 * Dodana je poslovna logika za največje dovoljeno število obešalnikov.
 *
 * @author Domen Gorišek
 * @version Vaja 34 - Implementacija vmesnika ali abstraktnega razreda
 */
public class GarderobnaOmara extends Omara implements ShranjevalnoMesto {

    /**
     * Trenutno število obešalnikov v omari.
     */
    private int steviloObesalnikov;

    /**
     * Največje dovoljeno število obešalnikov, ki jih lahko omara shrani,
     * glede na širino omare (obesalnik meri 5 cm).
     */
    private int maxObesalniki;
    
    /**
     * Konstruktor za inicializacijo garderobne omare.
     * Izračuna največje možno število obešalnikov in preveri, če je vnos veljaven.
     *
     * @param v Višina omare (v cm)
     * @param s Širina omare (v cm)
     * @param o Začetno število obešalnikov v omari
     * @param vrsta Vrsta omare
     * @throws PrevelikoSteviloObesalnikovException Če je število obešalnikov večje od največjega dovoljenega
     */
    public GarderobnaOmara(int v, int s, int o, String vrsta) throws PrevelikoSteviloObesalnikovException {
        super(v, s, vrsta); // Kliče konstruktor nadrazreda Omara
        
        // Izračunamo največje dovoljeno število obešalnikov na podlagi širine omare
        this.maxObesalniki = s / 5;
        
        // Preverimo, ali uporabnik vnese več obešalnikov, kot jih omara lahko shrani
        if (o > maxObesalniki) {
            throw new PrevelikoSteviloObesalnikovException("Napaka: Omara lahko shrani največ " + maxObesalniki + " obešalnikov.");
        }
        // Shrani začetno število obešalnikov
        this.steviloObesalnikov = o;
    }
    
    /**
     * Vrne lepši izpis podatkov o garderobni omari.
     *
     * @return String s podatki o omari in obešalnikih.
     */
    @Override
    public String toString() {
        return super.toString() + ", Število obešalnikov: " + steviloObesalnikov + " (maksimalno: " + maxObesalniki + ")";
    }
    
    /**
     * Metoda za dodajanje obešalnika v omaro.
     * Če je omara zaprta, jo je potrebno najprej odpreti.
     *
     * @param predmet Ime obešalnika, ki ga dodajamo. Ne uporablja se neposredno, ker se dodaja kot število
     */
    @Override
    public void dodajPredmet(String predmet) {
		// Preveri, ali je omara odprta
		if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden dodaš obešalnik.");
            return;
        }
        // Preveri, ali je omara že polna
        if (steviloObesalnikov >= maxObesalniki) {
            System.out.println("Napaka: Omara je polna, ne moreš dodati več obešalnikov.");
            return;
        }
        // Poveča število obešalnikov v omari
        steviloObesalnikov++;
        System.out.println("Dodali ste obešalnik: " + predmet + ". V omari jih je zdaj " + steviloObesalnikov + ".");
    }
    
    /**
     * Metoda za odstranjevanje obešalnika iz omare.
     * Če je omara zaprta, jo je potrebno najprej odpreti.
     *
     * @param predmet Ime obešalnika, ki ga odstranjujemo (ne uporablja se neposredno, ker se odstranjuje kot število)
     */
    @Override
    public void odstraniPredmet(String predmet) {
		// Preveri, ali je omara odprta
        if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden odstraniš obešalnik.");
            return;
        }
        // Preveri, ali v omari sploh še ostajajo obešalniki
        if (steviloObesalnikov == 0) {
            System.out.println("Napaka: Ni več obešalnikov za odstraniti.");
            return;
        }
        // Zmanjša število obešalnikov
        steviloObesalnikov--;
        System.out.println("Odstranili ste obešalnik: " + predmet + ". Ostalo jih je " + steviloObesalnikov + ".");
    }
    
    /**
     * Metoda, ki vrne število obešalnikov v omari.
     *
     * @return Število obešalnikov v omari.
     */
    @Override
    public int steviloPredmetov() {
        return steviloObesalnikov;
    }
    
    /**
     * Metoda za izvlek oblačila iz garderobne omare.
     * Preveri, ali je omara odprta in ali so na voljo obešalniki.
     *
     * @throws PrevelikoSteviloObesalnikovException Če ni več obešalnikov na voljo.
     */
    public void izvleciOblacilo() throws PrevelikoSteviloObesalnikovException {
		// Preveri, ali je omara odprta
        if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden vzameš oblačilo.");
            return;
        }
        
		// Preveri, ali so v omari še obešalniki
        if (steviloObesalnikov == 0) {
            throw new PrevelikoSteviloObesalnikovException("Napaka: Ni več obešalnikov! Dodajte jih lahko največ " + maxObesalniki + ".");
        }
        
		// Zmanjša število obešalnikov, ko izvlečemo oblačilo
        steviloObesalnikov--;
        System.out.println("Izvlekli smo eno oblačilo. Obešalnikov je še " + steviloObesalnikov + ".");
    }
}
