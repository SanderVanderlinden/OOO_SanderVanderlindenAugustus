package model.db.txt;

import model.db.LoadSaveStrategy;

import java.io.*;
import java.util.Scanner;

public abstract class TxtFileTemplate implements LoadSaveStrategy {
    protected abstract void stringToObject(String string);
    protected abstract String objectToString(int index);
    protected abstract String getFilePath();


    public final void save() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(this.getFilePath()));
        int index = 0;
        String lijn = this.objectToString(index);
        while(lijn != null){
            writer.append(lijn);
            writer.newLine();
            index ++;
            lijn = this.objectToString(index);
        }
        writer.close();
    }

    public final void load(){
        String path = this.getFilePath();
        File file = new File(path);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.trim().isEmpty()) {
                    this.stringToObject(line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }


}
