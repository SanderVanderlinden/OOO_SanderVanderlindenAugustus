package view.panels;

import javafx.beans.property.IntegerProperty;
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

import java.util.ArrayList;

public class KlantPane extends GridPane implements Observer{
    private Subject rekeningData;

    private Controller controller;

    private TableView table;
    private ArrayList<RekeningElement> rekeningElementArrayList = new ArrayList<RekeningElement>();
    private ObservableList<RekeningElement> rekeningElementen = FXCollections.observableArrayList();

    private Label totaalPrijs = new Label(Double.toString(0));


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
        this.add(totaalPrijs, 4, 0, 1, 1);
    }


    @Override
    public void update(ObservableList<Artikel> gescandeArtikels) {

        //System.out.println("size voor removeAll: " + rekeningElementen.size());
        //rekeningElementen.removeAll();

        while (rekeningElementen.size() != 0){
            rekeningElementen.remove(0);
        }

        while (rekeningElementArrayList.size() != 0){
            rekeningElementArrayList.remove(0);
        }

        for (Artikel gescandArtikel : gescandeArtikels){
            boolean bevat = false;
            for(int i = 0; i < rekeningElementArrayList.size(); i++){
                if (gescandArtikel.getCode().equals(rekeningElementArrayList.get(i).getCode())){
                    rekeningElementArrayList.get(i).verhoogAantal();
                    bevat = true;
                }
            }
            if (bevat == false){
                rekeningElementArrayList.add(new RekeningElement(gescandArtikel));
            }
        }

        for (RekeningElement rekeningElement : rekeningElementArrayList){
            rekeningElementen.add(rekeningElement);
        }

        this.getChildren().remove(totaalPrijs);
        totaalPrijs = new Label(controller.getRekening(rekeningElementen));
        this.add(totaalPrijs, 4, 0, 1, 1);

        updateList();
    }

    private void updateList() {
        ObservableList<RekeningElement> dummy = FXCollections.observableArrayList();
        while (rekeningElementen.size() != 0){
            dummy.add(rekeningElementen.get(0));
            rekeningElementen.remove(0);
        }
        while (dummy.size() != 0){
            rekeningElementen.add(dummy.get(dummy.size() - 1));
            dummy.remove(dummy.size() - 1);
        }
    }
}




