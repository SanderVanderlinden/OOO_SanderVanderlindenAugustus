package view.panels;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.controller.Controller;
import model.domain.artikel.Artikel;

import java.util.Collections;

public class ArtikelOverviewPane extends GridPane {
    private TableView table;


    public ArtikelOverviewPane(Controller controller) {
        this.add(new Label("Artikels:"), 0, 0, 1, 1);

        /*table = new TableView<>();
        table.setItems(controller.getArtikels();
        table.setPrefWidth(REMAINING);*/

        table = new TableView<>();

        ObservableList<Artikel> artikels = controller.getArtikels();
        Collections.sort(artikels);   // Sorts the array list

        table.setItems(artikels);
        table.setPrefWidth(REMAINING);




        // TABLE HEADERS
        TableColumn<Artikel, String> codeColumn = new TableColumn("Code");
        codeColumn.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Artikel, String> omschrijvingColumn = new TableColumn("Omschrijving");
        omschrijvingColumn.setCellValueFactory(new PropertyValueFactory<>("omschrijving"));

        TableColumn<Artikel, String> artikelgroepColumn = new TableColumn("Artikelgroep");
        artikelgroepColumn.setCellValueFactory(new PropertyValueFactory<>("artikelgroep"));

        TableColumn<Artikel, Double> verkoopprijsColumn = new TableColumn("Verkoopprijs");
        verkoopprijsColumn.setCellValueFactory(new PropertyValueFactory<>("verkoopprijs"));

        TableColumn<Artikel, Integer> actueleVoorraadColumn = new TableColumn("Actuele Voorraad");
        actueleVoorraadColumn.setCellValueFactory(new PropertyValueFactory<>("actueleVoorraad"));

        table.getColumns().addAll(codeColumn, omschrijvingColumn, artikelgroepColumn, verkoopprijsColumn, actueleVoorraadColumn);
        this.add(table, 0, 1, 5, 6);

    }
}
