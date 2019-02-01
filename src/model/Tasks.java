package model;

public abstract class Tasks {
    ToDoList toDoList;
    String name;
    boolean urgency = false;
    boolean importance = false;

    //REQUIRES: Name of task, Importance, eventually urgency
    //MODIFIES: This
    //EFFECTS: Creates and returns a new instance of Tasks
//    public Tasks(String name, boolean importance, boolean urgency){
//        this.name = name;
//        this.importance = importance;
//        this.urgency = urgency;
//    }


    //EFFECTS: return importance
    public boolean getImportance(){
        return this.importance;
    }
    //EFFECTS: return urgency
    public boolean getUrgency(){
        return this.urgency;
    }
    //EFFECTS: return task name
    public String getName(){
        return name;
    }

    //EFFECTS: return true if task name matches, else return false
    public boolean isName(String name){
        return (this.name == name);
    }

    // Deliverable 8
    public void addToList(ToDoList toDoList){
        if (!toDoList.containsTask(this)){
            toDoList.addTask(this);
            this.toDoList = toDoList;
        }
    }

}
