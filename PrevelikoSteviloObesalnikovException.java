/**
 * Izjema, ki se sproži, ko uporabnik doda preveč obešalnikov v omaro.
 * Ta izjema zagotavlja, da omara ne presega svoje kapacitete za obešalnike.
 *
 * @author Domen Gorišek
 * @version Vaja 33 - Izpis vseh objektov v seznamu in prekinitev zanke
 */
 public class PrevelikoSteviloObesalnikovException extends Exception {
	/**
	 * Konstruktor, ki prejme opis napake.
	 * 
	 * @param message Opis napake pri dodajanju obešalnikov
	 */
    public PrevelikoSteviloObesalnikovException(String message) {
        super(message);
    }
}