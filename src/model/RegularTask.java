package model;

public class RegularTask extends Tasks {

    public RegularTask(String name){
        this.name = name;
        this.importance = false;
        this.urgency = false;
    }
}
