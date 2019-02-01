package model;

import ui.Main;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class ToDoList extends Observable{
    private List<Tasks> tasksList;

    public ToDoList(){
        this.tasksList = new ArrayList<>();
    }

    public boolean isEmpty(){
        return (tasksList.isEmpty());
    }

    // MODIFIES: this
    // EFFECTS: add task to task list if it does not already contain task with same name
    // part of Deliverable 8
    public void addTask(Tasks task){
        if (!tasksList.contains(task)){
            tasksList.add(task);
            task.toDoList = this;
            setChanged();
            notifyObservers();
        }
    }

    // MODIFIES: this
    // EFFECTS: remove task with same name from list if exists in list
    public void removeTask(String taskName){
        List<Tasks> removeList = new ArrayList<>();
        for (Tasks t : tasksList){
            if (t.getName().equals(taskName)){
                removeList.add(t);
            }
        }
        tasksList.removeAll(removeList);
        System.out.println("Task removed\n");
    }

    // EFFECTS: prints out all the tasks in the list
    public void printAllTasks(){
        //System.out.println("entered method");
        // does not enter loop
        for (Tasks t : tasksList){
            //System.out.println("entered loop");
            System.out.println(t.getName());
        }
        System.out.println("\n");
    }

    public boolean containsTaskName(String name){
        for (Tasks t: tasksList){
            if (t.isName(name)){
                return true;
            }
        }
        return false;
    }

    public boolean containsTask(Tasks task){
        for (Tasks t: tasksList){
            if(tasksList.contains(t)){
                return true;
            }
        }
        return false;
    }

    public void getSaveData(ArrayList<String> saveData){
        //System.out.println("in getSaveData method");
        for (Tasks t : tasksList){
            //System.out.println("in for-each loop");
            String name = t.getName();
            String type = printType(taskType(t));
            String currentTask = name + " " + type;// + "/n";
            saveData.add(currentTask);
        }
    }

    private int taskType(Tasks t){
        if (t.getImportance() && t.getUrgency()){
            return 1;
        } else if (t.getImportance() && !t.getUrgency()){
            return 2;
        } else if (!t.getImportance() && t.getUrgency()){
            return 3;
        } else {
            return 4;
        }
    }

    private String printType(int i){
        if (i == 1){
            return "urgent_important";
        } else if (i == 2){
            return "not-urgent_important";
        } else if (i == 3) {
            return "urgent_not-important";
        } else {
            return "not-urgent_not-important";
        }
    }
}
