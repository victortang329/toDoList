package model;

import Exceptions.NameTooLongException;
import Exceptions.NameTooShortException;

public class RunningTasks extends Tasks{

    public RunningTasks(String name, boolean importance, boolean urgency) throws NameTooLongException, NameTooShortException {
        this.name = name;
        this.importance = importance;
        this.urgency = urgency;
        if (this.name.length() > 10){
            throw new NameTooLongException();
        }
        if (this.name.length() < 3){
            throw new NameTooShortException();
        }
    }
}
