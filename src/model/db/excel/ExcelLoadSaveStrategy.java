package model.db.excel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jxl.read.biff.BiffException;
import model.db.LoadSaveStrategy;

import java.io.File;
import java.io.IOException;

import model.db.excel.ExcelPlugin;
import model.domain.artikel.Artikel;


public class ExcelLoadSaveStrategy implements LoadSaveStrategy {

    File excelFile;
    private ObservableList<Artikel> artikels;


    public ExcelLoadSaveStrategy() throws IOException, BiffException {
        excelFile  = new File(System.getProperty("user.dir")+"/resources/db/artikel.txt");
        this.artikels = FXCollections.observableArrayList();
        this.load();
    }

    //Dit kan misschien een interface worden - TB
    public final void load() throws IOException, BiffException {
        ExcelPlugin.read(excelFile);
    }

    @Override
    public void save() throws IOException {
        //Er moeten geen artikelen kunnen worden toegevoegd, verwijderd of worden aangepast via je app .
    }
}