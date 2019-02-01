package ui;

import Exceptions.NameTooLongException;
import Exceptions.NameTooShortException;
import GUI.EnterButtonNotifier;
import GUI.ToDoListGUI;
import Interface.loadable;
import model.*;

import javax.swing.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class Main implements Observer{

    static ToDoList toDoList;
    static boolean run; //program will cease if run becomes false
    static String scannerInput; //the input string
    static Scanner scanner; //ask for clarification
    static JFrame toDoListGUI;
    static String GUIinputString;
    static EnterButtonNotifier enterButtonNotifier;
    static boolean resume;
    static GUIInputText guiInputText;


    public static void main(String[] args) throws IOException, MalformedURLException{

        toDoListGUI = new ToDoListGUI();
        toDoListGUI.setVisible(true);
        toDoListGUI.setSize(600,400);

    }

    private static void original() throws IOException{
        // import from url
        BufferedReader br = null;

        try {
            String theURL = "https://www.ugrad.cs.ubc.ca/~cs210/2018w1/welcomemsg.html";
            URL url = new URL(theURL);
            br = new BufferedReader(new InputStreamReader(url.openStream()));

            String line;

            StringBuilder sb = new StringBuilder();

            while ((line = br.readLine())!= null){
                sb.append(line);
                sb.append(System.lineSeparator());
            }

            System.out.println(sb);
        } finally {
            if(br!=null){
                br.close();
            }
        }


        // Ask TA what is meant unhandled exception. to
        toDoList = new ToDoList();
        loadable loader = new FileReaderLoader("savefile.txt");
        loader.loadFromFile(toDoList);
        ObserverNumberTask addTask = new ObserverNumberTask();
        toDoList.addObserver(addTask);
        run = true; //program will cease if run becomes false
        scannerInput = ""; //the input string
        scanner = new Scanner(System.in); //ask for clarification

        resume = false;
        guiInputText = new GUIInputText();

        programInstructions();

        //whileLoop();

        exitMessage();
        ArrayList<String> saveData = new ArrayList<>();
        //System.out.println("arraylist created");
        toDoList.getSaveData(saveData);
        //System.out.println("saveData ran");
        FileReaderSaver saver = new FileReaderSaver("savefile.txt");
        ObserverLoadSave saverObserver = new ObserverLoadSave();
        saver.addObserver(saverObserver);
        saver.SaveData(saveData);
    }

    private static void setRunToFalse(){
        run = false;
    }

    private static void whileLoop(){
        System.out.println("first level");
        while(run){
            if (resume){
                System.out.println("crapshoot");
                //programInstructions(); //prints program home intro
                //scannerInput = scanner.nextLine();
                if (GUIinputString.equals("exit")){ //this will stop the program
                    run = false;
                } else if (GUIinputString.equals("1")){
                    addItem();
                } else if (scannerInput.equals("2")){
                    removeItem();
                } else if (scannerInput.equals("3")){
                    showItems();
                } else {
//                exitMessage();
//                ArrayList<String> saveData = new ArrayList<>();
//                System.out.println("arraylist created");
//                toDoList.getSaveData(saveData);
//                System.out.println("saveData ran");
//                FileReaderSaver saver = new FileReaderSaver("savefile.txt");
//                saver.SaveData(saveData);
                }
                resumeFalse();
            }
        }
    }

    private static void programInstructions(){ //prints instructions for program home
        System.out.println("Enter '1' to add an item");
        System.out.println("Enter '2' to remove an item");
        System.out.println("Enter '3' to show items");
        System.out.println("Enter 'exit' to save before quitting program");
    }

    public static void addItem(){
        System.out.println("Add item selected\n");
        System.out.println("Enter name of task\n");
        scannerInput = scanner.nextLine();
        String taskName = scannerInput;
        System.out.println("If task is important, enter 'yes'. Enter anything else if unimportant\n");
        scannerInput = scanner.nextLine();
        boolean importance = false;
        if (scannerInput.equals("yes")){
            importance = true;
        }
        System.out.println("If task is urgent, enter 'yes'. Enter anything else if not urgent\n");
        scannerInput = scanner.nextLine();
        boolean urgency = false;
        if (scannerInput.equals("yes")){
            urgency = true;
        }
        try {
            Tasks newTask = new RunningTasks(taskName, importance, urgency);
            toDoList.addTask(newTask);
        } catch (NameTooLongException e) {
            System.out.println("Name is too long, try again");
            //Tasks newTask = new RegularTask("xxx");
        } catch (NameTooShortException e){
            System.out.println("Name is too short, try again");
        //} finally {
        //    System.out.println("Name must be between 3 and 10 characters");
        }


    }

    private static void removeItem(){
        System.out.println("Remove item selected\n");
        if (toDoList.isEmpty()){
            System.out.println("List is empty\n");
        } else {
            System.out.println("Enter task name you would like to remove\n");
            scannerInput = scanner.nextLine();
            String taskName = scannerInput;
            toDoList.removeTask(taskName);
        }
    }

    private static void showItems(/*String args[]*/){
        System.out.println("Show items selected\n");
        if (toDoList.isEmpty()){
            System.out.println("List is empty\n");
        } else {
            toDoList.printAllTasks();
        }
    }

    private static void exitMessage(){
        //System.out.println("Please enter a valid response\n");
    }

    public static void getGUItext(String string){
        guiInputText.updateInputText(string);
        System.out.println(guiInputText.returnString());
    }

    public void update(Observable observable, Object arg){
        enterButtonNotifier = (EnterButtonNotifier) observable;
        if(GUIinputString.equals("1")){
            addItem();
        }
    }

    public static void resumeTrue(String string){
        resume = true;
        System.out.println("poop");
        GUIinputString = string;
    }

    public static void resumeFalse(){
        resume = false;
    }
}
