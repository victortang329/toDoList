package model;

import java.util.Observable;
import java.util.Observer;

public class ObserverNumberTask implements Observer{
    private ToDoList addNotify;
    private int addCount = 0;

    public void update(Observable observable, Object arg){
        addNotify = (ToDoList) observable;
        addCount++;
        System.out.println("This session added " + this.addCount + " item\n");
    }
}
