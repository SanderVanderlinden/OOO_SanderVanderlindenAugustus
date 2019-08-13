package model.db;

import jxl.read.biff.BiffException;

import java.io.IOException;

public interface LoadSaveStrategy {

    void load() throws IOException, BiffException;

    void save() throws IOException;
}
