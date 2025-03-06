import java.io.*; // Uvoz vseh razredov iz knjižnice java.io, ki omogoča delo z vhodno-izhodnimi operacijami
import java.util.*; // Uvoz vseh razredov iz knjižnice java.util

/**
 * Glavni razred, ki demonstrira uporabo razreda Omara in njegovih podrazredov.
 * Omogoča uporabniku, da dinamično dodaja, upravlja in pregleduje omare.
 * Implementirane so preveritve dimenzij, odprtosti in shranjevanja predmetov.
 *
 * @author Domen Gorišek
 * @version Vaja 34 - Implementacija vmesnika ali abstraktnega razreda
 */
public class Main {
    
    /**
     * Statični BufferedReader za branje podatkov iz konzole.
     * Ta del omogoča uporabniku, da vnese podatke preko ukazne vrstice.
     */
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Metoda za varen vnos celega števila.
     * Če uporabnik vnese napačno vrednost, se zahteva ponovni vnos.
     * 
     * @param sporocilo Besedilo, ki se prikaže uporabniku
     * @param min Najmanjša dovoljena vrednost
     * @param max Največja dovoljena vrednost
     * @return Veljavno celo število, ki ustreza določenim kriterijem
     */
    private static int preberiInt(String sporocilo, int min, int max) {
        while (true) {
            try {
                System.out.print(sporocilo);
                int vrednost = Integer.parseInt(in.readLine()); // Pretvori vnos v celo število
                if (vrednost < min || vrednost > max) {
                    System.out.println("Napaka: Vnesite število med " + min + " in " + max + ".");
                    continue; // Ponovno zahteva vnos
                }
                return vrednost; // Vrne veljavno vneseno število
            }
			// Če uporabnik vnese nekaj, kar ni številka, ali pride do napake pri branju
			catch (NumberFormatException | IOException e) {
                System.out.println("Napaka: Vnesite veljavno celo število.");
            }
        }
    }
    
    /**
     * Glavna metoda programa.
     * Omogoča uporabniku, da doda različne vrste omar in upravlja z njimi.
     *
     * @param args Seznam argumentov iz ukazne vrstice
     */
    public static void main(String[] args) {
        
        // Seznam za shranjevanje omar
        ArrayList<Omara> seznamOmar = new ArrayList<>();
        boolean nadaljuj = true; // Kontrolna spremenljivka za zanko, ki določa, ali naj se program nadaljuje
        
		// Zanka se izvaja, dokler uporabnik ne odloči za izhod
        while (nadaljuj) {
            try {
                // Prikaz izbire vrste omare (uporabnik izbere med 3 vrstami)
                System.out.println("\nIzberite vrsto omare:");
                System.out.println("1 - Osnovna omara");
                System.out.println("2 - Pisarniška omara");
                System.out.println("3 - Garderobna omara");
                int izbira = preberiInt("Vnesite izbiro (1/2/3): ", 1, 3); // Metoda zahteva pravilno številko
                
                // Določitev vrste omare na podlagi izbire
                String vrsta = (izbira == 1) ? "Osnovna omara" : 
								(izbira == 2) ? "Pisarniška omara" : 
								"Garderobna omara"; // Če je izbira 3, potem je garderobna omara
                
                /// Vnos dimenzij omare (višina in širina, preverjanje v metodi preberiInt)
                int v = preberiInt("\nVnesite višino omare (20 - 300 cm): ", 20, 300);
                int s = preberiInt("Vnesite širino omare (20 - 500 cm): ", 20, 500);
                
                // Ustvarjanje objekta glede na izbiro uporabnika
                Omara novaOmara = null; // Spremenljivka za shranjevanje nove omare
				
				// Če je izbrana osnovna omara
                if (izbira == 1) {
                    novaOmara = new Omara(v, s, vrsta); // Ustvari osnovno omaro
                    System.out.print("\nŽelite odpreti omaro, da dodate predmet? (da/ne): ");
                    String odpiranje = in.readLine().trim().toLowerCase(); // Prebere odgovor uporabnika
                    
					// Če uporabnik želi dodati predmet
					if (odpiranje.equals("da")) {
                        novaOmara.odpri(); // Odpre omaro
                        System.out.print("\nVnesite predmet, ki ga želite dodati v omaro: ");
                        String predmet = in.readLine().trim(); // Prebere ime predmeta
                        
						if (!predmet.isEmpty()) {
                            novaOmara.dodajPredmet(predmet); // Doda predmet v omaro
                        }
						// Če je predmet prazen niz, javi napako
						else {
                            System.out.println("Napaka: Predmet ne sme biti prazen.");
                        }
                        novaOmara.zapri(); // Po dodajanju zapre omaro
                    }
                }
				// Če je izbrana pisarniška omara
                else if (izbira == 2) {
					// Uporabnik vnese število fasciklov, a ne sme presegati največje dovoljene vrednosti
                    int f = preberiInt("\nVnesite število fasciklov (Fascikel višine 30 cm in širine 10 cm): ", 0, (v / 30) * (s / 10));
                    // Ustvari pisarniško omaro
					PisarniskaOmara pisarniskaOmara = new PisarniskaOmara(v, s, f, vrsta);
                    System.out.print("\nŽelite odstraniti fascikle? (da/ne): ");
                    String odgovor = in.readLine().trim().toLowerCase(); // Prebere uporabnikov odgovor
                    
					// Če uporabnik želi odstraniti fascikle
					if (odgovor.equals("da")) { 
                        pisarniskaOmara.odpri(); // Odpre omaro
                        int kolicina = preberiInt("\nKoliko fasciklov želite odstraniti? ", 1, f); // Prebere število fasciklov za odstranitev
                        pisarniskaOmara.izprazniFascikle(kolicina); // Odstrani fascikle
                        pisarniskaOmara.zapri(); // Zapre omaro
                    }
					// Omara se shrani v spremenljivko
                    novaOmara = pisarniskaOmara; // Ustvari pisarniško omaro
                }
				// Če je izbrana garderobna omara
                else if (izbira == 3) {
					// Uporabnik vnese število obešalnikov, ki jih lahko shrani v omaro
                    int o = preberiInt("Vnesite število obešalnikov (Obešalnik je širok 5cm): ", 0, s / 5);
					// Ustvari garderobno omaro
                    GarderobnaOmara garderobnaOmara = new GarderobnaOmara(v, s, o, vrsta);
                    System.out.print("\nŽelite odstraniti obešalnik? (da/ne): ");
                    String odgovor = in.readLine().trim().toLowerCase(); // Prebere uporabnikov odgovor
                    
					// Če želi uporabnik odstraniti obešalnik
					if (odgovor.equals("da")) {
                        garderobnaOmara.odpri(); // Odpre omaro
                        System.out.println("\nIzvlek oblačila iz garderobne omare...");
                        garderobnaOmara.izvleciOblacilo(); // Izvede izvlek oblačila
                        garderobnaOmara.zapri(); // Zapre omaro
                    }
                    novaOmara = garderobnaOmara; // Shrani oz. ustvari garderobno omaro
                }
                
                // Dodamo omaro v seznam
                seznamOmar.add(novaOmara); // Shrani novo omaro v seznam, ki vsebuje vse ustvarjene omare
                System.out.println("Omara uspešno dodana!");
                
            } 
			// Če pride do napake pri vhodu ali pri ustvarjanju omare zaradi napačnih vrednosti
			catch (IOException | PrevelikoSteviloFasciklovException | PrevelikoSteviloObesalnikovException e) {
                // Izpiše sporočilo napake, ki je definirano v izjemah. Naši Exception pogoji
				System.out.println("Napaka: " + e.getMessage());
				// Možne napake:
				// - IOException: Težava pri branju uporabnikovega vnosa
				// - PrevelikoSteviloFasciklovException: Vnesti poskuša več fasciklov, kot jih omara lahko shrani
				// - PrevelikoSteviloObesalnikovException: Vnesti poskuša več obešalnikov, kot jih omara lahko shrani
			}
            
            // Uporabniku damo možnost nadaljevanja ali izhoda
            try {
                System.out.print("\nPritisnite Enter za konec ali vnesite karkoli za nadaljevanje: ");
                String odgovor = in.readLine(); // Prebere uporabnikov vnos iz konzole
                // Če uporabnik samo pritisne Enter, se program konča
				if (odgovor.trim().isEmpty()) break;
            }
			// Napaka pri branju vnosa iz konzole
			catch (IOException e) {
                System.out.println("Napaka pri branju vnosa. Program se končuje.");
                break; // Zaključi program, ker ne moremo pridobiti vnosa
            }
        }
        
        // Izpis vseh vnesenih omar
        System.out.println("\nSeznam vseh omar:"); // Obvestilo uporabniku z seznamom vseh omar
        for (Omara omara : seznamOmar) {
			// Prikaže podatke o vsaki omari, ki je bila dodana v seznam
            System.out.println(omara.toString());
			// Metoda toString() poskrbi za lepši izpis podatkov o posamezni omari
        }
    }
}