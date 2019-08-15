package model.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import model.db.DbException;
import model.db.dataSource.DataSourceEnum;
import model.db.txt.TxtArtikelDb;
import model.domain.artikel.Artikel;
import model.domain.rekeningElement.RekeningElement;
import view.KassaView;
import view.KlantView;
import view.panels.KassaPane;
import view.panels.Subject;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.getProperties;
import static java.lang.System.out;
import static java.lang.System.setProperty;

public class Controller {

    private KassaView kassaView;
    private KlantView klantView;

    private TxtArtikelDb ArtikelDb;

    private KassaPane kassaPane;
    private ObservableList<Artikel> onHold;


    public Controller(){
        this.kassaView = new KassaView();
        this.klantView  = new KlantView();
        ArtikelDb = new TxtArtikelDb();
        this.onHold = FXCollections.observableArrayList();
    }

    public void start(Controller controller){
        this.kassaView.start(controller);
        this.klantView.start(controller);
    }


    public ObservableList<Artikel> getArtikels() {
        return ArtikelDb.getArtikels();
        /*Artikel art1 = new Artikel("a", "b", "c", 1.5, 2);
        Artikel art2 = new Artikel("d", "e", "f", 3.5, 6);
        Artikel art3 = new Artikel("g", "h", "i", 5.5, 4);
        ObservableList<Artikel> arts = FXCollections.observableArrayList(art1, art2, art3);
        return arts;*/
    }

    public List<Object> getDataSources() {
        return Arrays.asList(DataSourceEnum.values());
    }

    public DataSourceEnum getDataSource() {
        return DataSourceEnum.valueOf(getProperties().getProperty("data.Source"));
    }

    public void setDataSource(Object selectedItem) {
        DataSourceEnum dataSource;
        if (selectedItem instanceof DataSourceEnum) {
            dataSource = (DataSourceEnum) selectedItem;
            setProperty("data.Source", dataSource.toString());
        } else throw new DbException("selectedItem is not in TestEnum "+selectedItem);
    }

    public Artikel scanArtikel(String code) {
        //System.out.println(code);
        return ArtikelDb.getArtikelByCode(code);
    }

    public RekeningElement scanRekeningElement(String code){
        RekeningElement rekeningElement = new RekeningElement(ArtikelDb.getArtikelByCode(code).getCode(),
                ArtikelDb.getArtikelByCode(code).getOmschrijving(),
                ArtikelDb.getArtikelByCode(code).getVerkoopprijs(), 1);
        return rekeningElement;
    }

    public String getTotaalPrijs(ObservableList<Artikel> gescandeArtikels) {
        double totaalPrijs = 0;
        for (Artikel artikel : gescandeArtikels){
            totaalPrijs += artikel.getVerkoopprijs();
        }

        return Double.toString(totaalPrijs);
    }

    public boolean IsArtikelInDb(String artikelCode) {
        for (Artikel artikel : getArtikels()){
            if (artikel.getCode().equals(artikelCode)){
                return true;
            }
        }

        return false;

    }

    public Subject getRekeningData() {
        return kassaPane;
    }

    public void setRekeningData(KassaPane kassaPane) {
        this.kassaPane = kassaPane;
    }

    public String getRekening(ObservableList<RekeningElement> rekeningElementen) {
        double totaalPrijs = 0;
        for (RekeningElement rekeningElement : rekeningElementen){
            totaalPrijs += rekeningElement.getTotaleVerkoopPrijs();
        }

        return Double.toString(totaalPrijs);
    }

    public void setOnHold(ObservableList<Artikel> gescandeArtikels) {
        for (Artikel artikel: gescandeArtikels){
            onHold.add(artikel);
        }
    }

    public ObservableList<Artikel> getOnHold() {
        ObservableList<Artikel> output = FXCollections.observableArrayList();;
        for (Artikel artikel: onHold){
            output.add(artikel);
        }
        while (onHold.size() != 0){
            onHold.remove(0);
        }
        return output;
    }
}
