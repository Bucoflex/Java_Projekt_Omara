/**
 * Izjema, ki se sproži, ko uporabnik doda preveč fasciklov v omaro.
 * Ta izjema je namenjena nadzoru nad pravilnim shranjevanjem fasciklov v pisarniški omari.
 *
 * @author Domen Gorišek
 * @version Vaja 33 - Izpis vseh objektov v seznamu in prekinitev zanke
 */
public class PrevelikoSteviloFasciklovException extends Exception {
	/**
     * Konstruktor, ki prejme opis sporočila napake
     * 
     * @param message Opis napake pri dodajanju fasciklov
     */
    public PrevelikoSteviloFasciklovException(String message) {
        super(message);
    }
}