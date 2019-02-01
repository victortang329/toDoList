package model;

public class UrgentImportantTask extends UrgentTask {

    public UrgentImportantTask(String name){
        super(name);
        this.importance = true;
    }

    @Override
     protected void urgentTaskWarning(){
        System.out.println(this.name + " is an urgent important task");
    }
}
