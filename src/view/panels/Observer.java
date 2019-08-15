package view.panels;

import javafx.collections.ObservableList;
import model.domain.artikel.Artikel;

public interface Observer {
    public void update(ObservableList<Artikel> gescandeArtikels);

    /*
    public void updateAdd(Artikel gescandeArtikels);
    public void updateRemove(Artikel artikel);
    */
}
