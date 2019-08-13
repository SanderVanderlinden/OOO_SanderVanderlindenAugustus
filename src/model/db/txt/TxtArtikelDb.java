package model.db.txt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.domain.artikel.Artikel;

public class TxtArtikelDb extends TxtFileTemplate {
    private final String filePath;
    private ObservableList<Artikel> artikels;


    public TxtArtikelDb() {
        this.filePath = System.getProperty("user.dir")+"/resources/db/artikel.txt";
        this.artikels = FXCollections.observableArrayList();
        this.load();
    }

    public ObservableList<Artikel> getArtikels() {
        return this.artikels;
    }

    @Override
    protected void stringToObject(String line) {
        String[] words = line.split(",");
        String code = words[0];
        String omschrijving = words[1];
        String artikelgroep = words[2];
        double verkoopprijs = Double.parseDouble(words[3]);
        int actueleVoorraad = Integer.parseInt(words[4]);
        Artikel artikel = new Artikel(code, omschrijving, artikelgroep, verkoopprijs, actueleVoorraad);
        addArtikel(artikel);
    }

    public void addArtikel(Artikel artikel) {
        if (artikels.contains(artikel)) {
            throw new IllegalArgumentException(artikel.getOmschrijving() + " is already in the list.");
        }
        if (artikel != null) {
            this.artikels.add(artikel);
        } else {
            throw new IllegalArgumentException("Artikel cannot be null.");
        }
    }

    public Artikel getArtikelByCode(String code) {
        for (Artikel artikel : artikels) {
            if (artikel.getCode().equals(code)) {
                return artikel;
            }
        }
        throw new IllegalArgumentException("Artikel with code '" + code + "' not found.");
    }

    @Override
    protected String objectToString(int index) {
        if(artikels.size() <= index){
            return null;
        }
        Artikel artikel = artikels.get(index);
        String result = artikel.toString();
        return result;
    }

    @Override
    protected String getFilePath() {
        return filePath;
    }

}
