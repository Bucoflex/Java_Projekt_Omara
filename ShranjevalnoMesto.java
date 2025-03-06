/**
 * Vmesnik ShranjevalnoMesto določa osnovne operacije za shranjevanje in upravljanje predmetov.
 * Vsak razred, ki implementira ta vmesnik, mora definirati način dodajanja, odstranjevanja
 * in preverjanja števila shranjenih predmetov.
 *
 * Ta vmesnik je primeren za razrede, kot so PisarniskaOmara in GarderobnaOmara,
 * kjer omare shranjujejo določene vrste predmetov (npr. fascikli, obešalniki).
 *
 * Primer:
 * PisarniskaOmara implementira ShranjevalnoMesto in uporablja dodajPredmet() za fascikle.
 * GarderobnaOmara uporablja odstraniPredmet() za obešalnike.
 *
 * @author Domen Gorišek
 * @version Vaja 34 - Implementacija vmesnika ali abstraktnega razreda
 */
public interface ShranjevalnoMesto {
    
    /**
     * Metoda za dodajanje predmeta v shranjevalno mesto.
     * Implementacija mora poskrbeti, da se predmet lahko doda le, če je prostor na voljo.
     * 
     * @param predmet Ime predmeta, ki ga dodajamo
     */
    void dodajPredmet(String predmet);
    
    /**
     * Metoda za odstranjevanje predmeta iz shranjevalnega mesta.
     * Implementacija mora zagotoviti, da se predmet ne odstranjuje, če ni več na voljo.
     * 
     * @param predmet Ime predmeta, ki ga odstranjujemo
     */
    void odstraniPredmet(String predmet);
    
    /**
     * Metoda, ki vrne število predmetov, ki jih trenutno vsebuje shranjevalno mesto.
     * 
     * @return Število shranjenih predmetov
     */
    int steviloPredmetov();
}