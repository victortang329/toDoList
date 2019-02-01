package model;

import java.util.Observable;
import java.util.Observer;

public class ObserverLoadSave implements Observer {
    private FileReaderSaver saveNotify;

    public void update(Observable observable, Object arg){
        saveNotify = (FileReaderSaver) observable;
        System.out.println("Data Saved");
    }
}
