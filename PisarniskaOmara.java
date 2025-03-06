/**
 * Razred PisarniskaOmara, ki razširi razred Omara in implementira vmesnik ShranjevalnoMesto.
 * Razred za prikaz uporabe dedovanja
 * Razred PisarniskaOmara, ki razširi razred Omara in vsebuje fascikle
 * Dodana je poslovna logika za določitev največjega števila fasciklov, ki jih lahko shrani glede na dimenzije.
 *
 * @author Domen Gorišek
 * @version Vaja 34 - Implementacija vmesnika ali abstraktnega razreda
 */
 public class PisarniskaOmara extends Omara implements ShranjevalnoMesto {

	/**
     * Število fasciklov v omari
     */
    private int steviloFasciklov;

	/**
	 * Največje dovoljeno število fasciklov, ki jih lahko omara shrani,
     * glede na dimenzije (fascikel meri 30 cm v višino in 10 cm v širino).
	 */
	private int maxFascikli;
    
    /**
     * Konstruktor za inicializacijo pisarniške omare
	 * Izračuna največje možno število fasciklov in preveri, ali je vnos veljaven.
	 *
     * @param v Višina omare
     * @param s Širina omare
     * @param f Začetno število fasciklov
	 * @param vrsta Vrsta omare
	 * @throws PrevelikoSteviloFasciklovException Če je število fasciklov večje od največjega dovoljenega
     */
    public PisarniskaOmara(int v, int s, int f, String vrsta) throws PrevelikoSteviloFasciklovException {
        super(v, s, vrsta);
		
		// Izračun največjega dovoljenega števila fasciklov na podlagi dimenzij omare
        // Kliče konstruktor nadrazreda Omara
		this.maxFascikli = (v / 30) * (s / 10);
		
		// Preverimo, ali uporabnik vnese več fasciklov, kot jih omara lahko shrani
        if (f > maxFascikli) {
            throw new PrevelikoSteviloFasciklovException("Napaka: Omara lahko shrani največ " + maxFascikli + " fasciklov.");
        }
		// Shrani začetno število fasciklov
        this.steviloFasciklov = f;
    }
	
	/**
     * Vrne lepši izpis podatkov o pisarniški omari.
     * 
     * @return String s podatki o omari in fasciklih.
     */
    @Override
    public String toString() {
        return super.toString() + ", Število fasciklov: " + steviloFasciklov + " (maksimalno: " + maxFascikli + ")";
    }
	
	/**
     * Metoda za dodajanje fascikla v omaro.
     * Če je omara zaprta, je potrebno najprej odpreti.
     *
     * @param predmet Ime fascikla, ki ga dodajamo (ne uporablja se neposredno, ker se dodaja kot število)
     */
	@Override
    public void dodajPredmet(String predmet) {
        // Preveri, ali je omara odprta
		if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden dodaš fascikel.");
            return;
        }
        // Preveri, ali je omara že polna
        if (steviloFasciklov >= maxFascikli) {
            System.out.println("Napaka: Omara je polna, ne moreš dodati več fasciklov.");
            return;
        }
        // Poveča število fasciklov v omari
        steviloFasciklov++;
        System.out.println("Dodali ste fascikel: " + predmet + ". V omari jih je zdaj " + steviloFasciklov + ".");
    }
	
	
    /**
     * Metoda za odstranjevanje fascikla iz omare.
     * Če je omara zaprta, je potrebno najprej odpreti.
     *
     * @param predmet Ime fascikla, ki ga odstranjujemo. Ne uporablja se neposredno, ker se odstranjuje kot število
     */
	@Override
    public void odstraniPredmet(String predmet) {
        // Preveri, ali je omara odprta
		if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden odstraniš fascikel.");
            return;
        }
        // Preveri, ali v omari sploh še ostajajo fascikli
        if (steviloFasciklov == 0) {
            System.out.println("Napaka: Ni več fasciklov za odstraniti.");
            return;
        }
        // Zmanjša število fasciklov
        steviloFasciklov--;
        System.out.println("Odstranili ste fascikel: " + predmet + ". Ostalo jih je " + steviloFasciklov + ".");
    }
	
	/**
     * Metoda, ki vrne število fasciklov v omari.
     *
     * @return Število fasciklov v omari.
     */
	@Override
    public int steviloPredmetov() {
        return steviloFasciklov;
    }
	
	/**
     * Metoda za praznjenje določene količine fasciklov iz omare.
     * Če je omara zaprta, jo je treba najprej odpreti.
     *
     * @param k Število fasciklov, ki jih želimo odstraniti
     * @throws PrevelikoSteviloFasciklovException Če poskusimo odstraniti več fasciklov, kot jih je na voljo
	 * iz našega implemeniranega razreda Exception
     */
	public void izprazniFascikle(int k) throws PrevelikoSteviloFasciklovException {
        // Preveri, ali je omara odprta
		if (!jeOmaraOdprta()) {
            System.out.println("Omara je zaprta! Odpri jo, preden odstraniš fascikle.");
            return;
        }

        // Preverimo, ali je zahteva za odstranitev veljavna
        if (k <= 0 || steviloFasciklov < k) {
            throw new PrevelikoSteviloFasciklovException("Napaka: Ne morete odstraniti " + k + " fasciklov. Trenutno jih je na voljo: " + steviloFasciklov + ".");
        }
        // Odstrani željeno število fasciklov
        steviloFasciklov -= k;
        System.out.println("Odstranili smo " + k + " fasciklov. Ostalo jih je " + steviloFasciklov + ".");
    }
}