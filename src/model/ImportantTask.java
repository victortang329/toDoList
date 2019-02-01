package model;

public class ImportantTask extends Tasks {

    public ImportantTask(String name){
        this.name = name;
        this.importance = true;
        this.urgency = false;
    }
}
