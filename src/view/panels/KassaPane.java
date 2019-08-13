package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import model.controller.Controller;
import model.domain.artikel.Artikel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;


public class KassaPane extends GridPane implements Subject{
    private ArrayList<Observer> observers;

    private Controller controller;
    private TextField artikelCodeField;

    private TableView prijs;
    private TableView table;
    private ObservableList<Artikel> gescandeArtikels = FXCollections.observableArrayList();

    private Label totaalPrijs = new Label(Double.toString(0));
    private Label verkeerdeCode = new Label("");

    public KassaPane(Controller controller) {

        observers = new ArrayList<Observer>();
        this.controller = controller;
        controller.setRekeningData(this);


        this.add(new Label("Artikelcode: "), 0, 0, 1, 1);
        artikelCodeField = new TextField();
        add(artikelCodeField, 1, 0, 2, 1);
        artikelCodeField.setOnKeyPressed(new EnterHandler(this));

        this.add(new Label("Gescande artikels:"), 0, 3, 1, 1);

        //prijs = new TableView<>();
        //prijs.setItems(controller.getTotaalPrijs(gescandeArtikels));
        //table.setPrefWidth(REMAINING);

        table = new TableView<>();

        table.setItems(gescandeArtikels);
        table.setPrefWidth(REMAINING);

        // TABLE HEADERS
        TableColumn<Artikel, String> codeColumn = new TableColumn("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Artikel, String> omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<Artikel, Double> verkoopprijsColumn = new TableColumn("Verkoopprijs");
        verkoopprijsColumn.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs"));

        table.getColumns().addAll(codeColumn, omschrijvingColumn, verkoopprijsColumn);
        this.add(table, 0, 5, 5, 6);

        this.add(new Label("Totaal te betalen:"), 3, 0, 1, 1);
        this.add(totaalPrijs, 4, 0, 1, 1);
        this.add(verkeerdeCode, 0, 1, 1, 1);


    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0){
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer: observers){
            observer.update(gescandeArtikels);
        }
    }

    private class EnterHandler implements EventHandler<KeyEvent> {

        private KassaPane kassaPane;

        public EnterHandler(KassaPane kassaPane) {
            this.kassaPane = kassaPane;
        }

        @Override
        public void handle(KeyEvent ke)
        {
            if (ke.getCode().equals(KeyCode.ENTER))
            {
                //System.out.println("geluktt");
                if (controller.IsArtikelInDb(artikelCodeField.getText())){
                    gescandeArtikels.add(controller.scanArtikel(artikelCodeField.getText()));
                    //totaalPrijs = new Label(controller.getTotaalPrijs(gescandeArtikels));
                    kassaPane.getChildren().remove(totaalPrijs);
                    totaalPrijs = new Label(controller.getTotaalPrijs(gescandeArtikels));
                    //kassaPane.add(new Label(controller.getTotaalPrijs(gescandeArtikels)), 4, 0, 1, 1);
                    kassaPane.add(totaalPrijs, 4, 0, 1, 1);
                    notifyObservers();
                }
                else{
                    kassaPane.getChildren().remove(verkeerdeCode);
                    verkeerdeCode = new Label("niet bestaande code (" + artikelCodeField.getText() + ")");
                    kassaPane.add(verkeerdeCode, 0, 1, 1, 1);

                }

            }
        }
    }


}




