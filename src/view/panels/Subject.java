package view.panels;

import javafx.collections.ObservableList;
import model.domain.artikel.Artikel;

public interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void NotifyObservers();

    /*
    public void addNotifyObservers(Artikel artikel);
    public void removeNotifyObservers(Artikel artikel);
    */
}
