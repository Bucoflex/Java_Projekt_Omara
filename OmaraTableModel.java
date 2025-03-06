import javax.swing.table.DefaultTableModel; // Uvoz razreda za upravljanje tabelnih modelov
import java.util.ArrayList; // Uvoz razreda za dinamično shranjevanje podatkov

/**
 * Razred OmaraTableModel razširi DefaultTableModel in omogoča upravljanje podatkov omar
 * Omogoča dodajanje, urejanje in pridobivanje podatkov iz seznama omar
 * Ta model se uporablja v grafičnem uporabniškem vmesniku (GUI) za prikaz seznama omar
 *
 * @author Domen Gorišek
 * @version Vaja 36 - Grafični uporabniški vmesnik (GUI) in spreminjanje lastnosti objektov
 */
public class OmaraTableModel extends DefaultTableModel {
    
    /**
     * Seznam omar, ki jih model upravlja.
	 * Ta seznam vsebuje vse omare, ki jih uporabnik doda preko GUI-ja
     */
    private ArrayList<Omara> seznamOmar;
    
    /**
     * Stolpci tabele, ki prikazujejo lastnosti omar
	 * Vsaka omara ima višino, širino in vrsto
     */
    private final String[] stolpci = {"Višina", "Širina", "Vrsta"};
    
    /**
     * Konstruktor za inicializacijo modela tabele
     * Ustvari prazen seznam omar in nastavi imena stolpcev
     */
    public OmaraTableModel() {
        super(); // Kliče konstruktor nadrazreda DefaultTableModel
        seznamOmar = new ArrayList<>(); // Inicializira seznam omar
        setColumnIdentifiers(stolpci); // Nastavi imena stolpcev v tabeli
    }
    
    /**
     * Doda novo omaro v seznam in tabelo.
     *
     * @param omara Nova omara, ki jo želimo dodati.
     */
    public void dodajOmara(Omara omara) {
        seznamOmar.add(omara); // Doda omaro v seznam
		// Doda vrstico v tabelo GUI-ja
        addRow(new Object[]{omara.getVisina(), omara.getSirina(), omara.getVrstaOmare()});
    }
    
    /**
     * Posodobi podatke obstoječe omare na določenem indeksu.
     * Spremeni vrednosti v objektu omare in posodobi tabelo.
     *
     * @param index Indeks omare v tabeli (od 0 naprej).
     * @param visina Nova višina omare.
     * @param sirina Nova širina omare.
     * @param vrsta Nova vrsta omare.
     */
    public void posodobiOmara(int index, int visina, int sirina, String vrsta) {
        // Preveri, ali indeks obstaja v seznamu
		if (index >= 0 && index < seznamOmar.size()) {
            Omara omara = seznamOmar.get(index); // Pridobi omaro iz seznama
            
			// Posodobi lastnosti omare z novimi vrednostmi
			omara.setVisina(visina);
            omara.setSirina(sirina);
            omara.setVrstaOmare(vrsta);
            
            // Posodobi tudi tabelo v GUI-ju
            setValueAt(visina, index, 0); // Posodobi stolpec "Višina"
            setValueAt(sirina, index, 1);  // Posodobi stolpec "Širina"
            setValueAt(vrsta, index, 2); // Posodobi stolpec "Vrsta"
        }
    }
    
    /**
     * Vrne omaro na določenem indeksu.
	 * Uporablja se pri kliku na vrstico v tabeli, da pridobimo njene podatke.
     *
     * @param rowIndex Indeks vrstice v tabeli.
     * @return Omara na določenem indeksu.
     */
    public Omara getOmaraAt(int rowIndex) {
        return seznamOmar.get(rowIndex); // Vrne omaro iz seznama
    }
    
    /**
     * Prepreči urejanje celic tabele
	 * Podatki v tabeli se lahko spreminjajo samo preko GUI-ja in ne neposredno
     *
     * @param rowIndex Indeks vrstice
     * @param columnIndex Indeks stolpca
     * @return Vedno false, ker podatki niso neposredno urejani v tabeli
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false; // Onemogoči urejanje celic v tabeli
    }
}