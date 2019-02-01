package GUI;

import ui.Main;

import java.util.Observable;

public class EnterButtonNotifier extends Observable {

    public EnterButtonNotifier(){
//        this.addObserver(Main);
    }

    public void notifyEnterButtonPressed(){
        setChanged();
        notifyObservers();
    }
}
