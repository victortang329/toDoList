package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;

public class FileReaderSaver extends Observable{
    PrintWriter writer;

    public FileReaderSaver(String path) throws IOException {
        writer = new PrintWriter(path, "UTF-8");
    }

    public void SaveData(ArrayList<String> saveData) throws IOException{
        for (String s : saveData){
            writer.println(s);
        }
        writer.close();
        setChanged();
        notifyObservers();
    }

}
