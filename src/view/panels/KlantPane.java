package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.domain.artikel.Artikel;
import model.domain.rekeningElement.RekeningElement;
import javafx.scene.layout.GridPane;
import model.controller.Controller;

public class KlantPane extends GridPane implements Observer{
    private Subject rekeningData;

    private Controller controller;

    private TableView table;
    private ObservableList<RekeningElement> rekeningElementen = FXCollections.observableArrayList();

    public KlantPane(Controller controller, Subject rekeningData) {
        this.rekeningData = rekeningData;
        rekeningData.registerObserver(this);

        this.controller = controller;

        this.add(new Label("Rekening: "), 0, 0, 1, 1);

        table = new TableView<>();

        table.setItems(rekeningElementen);
        table.setPrefWidth(REMAINING);

        // TABLE HEADERS
        TableColumn<RekeningElement, String> codeColumn = new TableColumn("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<RekeningElement, String> omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<RekeningElement, Double> verkoopprijsColumn = new TableColumn("Eenheidsprijs");
        verkoopprijsColumn.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs"));

        TableColumn<RekeningElement, Integer> aantalColumn = new TableColumn("Aantal");
        aantalColumn.setCellValueFactory(new PropertyValueFactory<>("aantal"));

        TableColumn<RekeningElement, Double> totaalprijsColumn = new TableColumn("Verkoopprijs");
        totaalprijsColumn.setCellValueFactory(new PropertyValueFactory<>("totaleVerkoopPrijs"));

        table.getColumns().addAll(codeColumn, omschrijvingColumn, verkoopprijsColumn, aantalColumn, totaalprijsColumn);
        this.add(table, 0, 5, 5, 6);

        this.add(new Label("Totaal te betalen:"), 3, 0, 1, 1);
        //this.add(totaalPrijs, 4, 0, 1, 1);

    }


    @Override
    public void update(ObservableList<Artikel> gescandeArtikels) {
        rekeningElementen.removeAll();
        for (Artikel artikel : gescandeArtikels){
            if (rekeningElementen.contains(controller.scanRekeningElement(artikel.getCode()))){
                for (RekeningElement rekeningElement: rekeningElementen){
                    if (rekeningElement.getCode() == artikel.getCode()){
                        rekeningElement.verhoogAantal();
                    }
                }
            }
            else{
                rekeningElementen.add(new RekeningElement(artikel.getCode(), artikel.getOmschrijving(), artikel.getVerkoopprijs(), 1));
            }
        }
    }
}




