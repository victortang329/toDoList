package model;

public class UrgentTask extends Tasks {

    public UrgentTask(String name){
        this.name = name;
        this.importance = false;
        this.urgency = true;
        urgentTaskWarning();
    }

    protected void urgentTaskWarning(){
        System.out.println(this.name + " is an urgent task");
    }
}
