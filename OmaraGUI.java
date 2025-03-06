import javax.swing.*; // Uvoz knjižnice za ustvarjanje GUI elementov
import java.awt.*; // Uvoz knjižnice za delo s postavitvijo in barvami
import java.awt.event.ActionEvent; // Uvoz dogodkov za gumb
import java.awt.event.ActionListener; // Vmesnik za obdelavo dogodkov
import java.awt.event.MouseAdapter; // Adapter za dogodke miške
import java.awt.event.MouseEvent; // Dogodek miške

/**
 * Razred OmaraGUI predstavlja grafični uporabniški vmesnik za upravljanje omar.
 * Omogoča dodajanje novih omar v seznam ter prikazovanje omar v tabeli.
 * Dodana je možnost urejanja obstoječih omar.
 * Implementira vmesnik ActionListener za upravljanje dogodkov.
 *
 * @author Domen Gorišek
 * @version Vaja 36 - Grafični uporabniški vmesnik (GUI) in spreminjanje lastnosti objektov
 */
public class OmaraGUI extends JFrame implements ActionListener {
    
    /**
     * Tabela za prikaz podatkov o omarah
     */
    private JTable tabela;
    
    /**
     * Model tabele za dinamično dodajanje podatkov
     */
    private OmaraTableModel model;
    
    /**
     * Vnosna polja za dodajanje in urejanje omar
     */
    private JTextField vnosVisina, vnosSirina, vnosVrsta;
    
    /**
     * Gumbi za dodajanje in urejanje omar
     */
    private JButton dodajButton, urediButton;
    
    /**
     * Indeks trenutno izbrane vrstice za urejanje
     */
    private int izbranIndex = -1;
    
    /**
     * Konstruktor za inicializacijo grafičnega vmesnika
	 * Ustvari osnovno okno, tabelo in vnosna polja
     */
    public OmaraGUI() {
        super("Upravljanje omar"); // Nastavi naslov okna
        setDefaultCloseOperation(EXIT_ON_CLOSE); // Zapre program, ko uporabnik zapre okno
        setSize(600, 400); // Nastavi velikost okna
        setLayout(new BorderLayout()); // Uporabi osnovno postavitev BorderLayout
        
        // Inicializacija modela tabele, ki hrani podatke o omarah
        model = new OmaraTableModel();
        tabela = new JTable(model); // Ustvari tabelo na podlagi modela
        add(new JScrollPane(tabela), BorderLayout.CENTER); // Doda tabelo v okno
        
        // Ustvarimo panel za vnose podatkov
        JPanel vnosPanel = new JPanel(new GridLayout(5, 2)); // Uporabi mrežno postavitev (5 vrstic, 2 stolpca)
        
        // Polja za vnos podatkov o omari (višina, širina, vrsta)
        vnosPanel.add(new JLabel("Višina omare med 20 in 300 cm:")); // Oznaka za višino
        vnosVisina = new JTextField(); // Ustvari vnosno polje za višino
        vnosPanel.add(vnosVisina);
        
        vnosPanel.add(new JLabel("Širina omare med 20 in 500 cm:")); // Oznaka za širino
        vnosSirina = new JTextField(); // Ustvari vnosno polje za širino
        vnosPanel.add(vnosSirina);
        
        vnosPanel.add(new JLabel("Vrsta omare:")); // Oznaka za vrsto omare
        vnosVrsta = new JTextField(); // Ustvari vnosno polje za vrsto omare
        vnosPanel.add(vnosVrsta);
        
        // Gumb za dodajanje omar v tabelo
        dodajButton = new JButton("Dodaj omaro"); // Ustvari gumb
        dodajButton.addActionListener(this); // Doda poslušalca dogodkov
        vnosPanel.add(dodajButton);
        
        // Gumb za urejanje obstoječih omar
        urediButton = new JButton("Uredi omaro"); // Ustvari gumb
        urediButton.addActionListener(this); // Doda poslušalca dogodkov
		// Gumb onemogočimo, dokler ni izbrane vrstice
        urediButton.setEnabled(false);
        vnosPanel.add(urediButton);
        
		// Doda panel z gumbi in vnosnimi polji na dno okna
        add(vnosPanel, BorderLayout.SOUTH);
        
        // Omogočimo klik na vrstico v tabeli za urejanje
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                izbranIndex = tabela.getSelectedRow(); // Shrani indeks izbrane vrstice
                if (izbranIndex >= 0) {
					// Pridobi podatke iz modela
                    Omara izbranaOmara = model.getOmaraAt(izbranIndex);
					
					// Prikaz obstoječih vrednosti v vnosnih poljih
                    vnosVisina.setText(String.valueOf(izbranaOmara.getVisina()));
                    vnosSirina.setText(String.valueOf(izbranaOmara.getSirina()));
                    vnosVrsta.setText(izbranaOmara.getVrstaOmare());
                    urediButton.setEnabled(true); // Omogoči gumb za urejanje
                }
            }
        });
        
        setVisible(true); // Prikaže okno
    }
    
    /**
     * Metoda, ki obdela dogodke ob kliku na gumba "Dodaj omaro" in "Uredi omaro".
     * Preveri pravilnost vnosa in doda ali posodobi obstoječo omaro v tabeli.
     * 
     * @param e Dogodek klika na gumb.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
			// Pridobi vnesene vrednosti in jih pretvori v števila
            int visina = Integer.parseInt(vnosVisina.getText());
            int sirina = Integer.parseInt(vnosSirina.getText());
            String vrsta = vnosVrsta.getText().trim(); // Odstrani odvečne presledke
            
            // Preverimo, ali so vnesene vrednosti v dovoljenem obsegu
            if (visina < 20 || visina > 300 || sirina < 20 || sirina > 500 || vrsta.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Napaka: Neveljavni podatki.", "Napaka", JOptionPane.ERROR_MESSAGE);
                // Prekine izvajanje metode, če so podatki napačni
				return;
            }
            // Če je pritisnjen gumb "Dodaj omaro"
            if (e.getSource() == dodajButton) {
                // Ustvarimo nov objekt omare z vnesenimi podatki
                Omara novaOmara = new Omara(visina, sirina, vrsta); // Ustvari nov objekt omare
                model.dodajOmara(novaOmara); // Doda novo omaro v tabelo
            }
			// Če je pritisnjen gumb "Uredi omaro"
            else if (e.getSource() == urediButton && izbranIndex >= 0) {
                // Posodobimo obstoječo omaro v tabeli
                model.posodobiOmara(izbranIndex, visina, sirina, vrsta);
                urediButton.setEnabled(false); // Po urejanju onemogočimo gumb
            }
            
            // Po uspešnem dodajanju ali urejanju počistimo vnosna polja
            vnosVisina.setText("");
            vnosSirina.setText("");
            vnosVrsta.setText("");
            izbranIndex = -1; // Ponastavi izbrano vrstico
            
        } 
		catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Napaka: Višina in širina morata biti številki.", "Napaka", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Glavna metoda za zagon GUI aplikacije.
     * 
     * @param args Argumenti ukazne vrstice.
     */
    public static void main(String[] args) {
        new OmaraGUI(); // Ustvari in prikaže GUI aplikacijo
    }
}