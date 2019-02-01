package GUI;

import Exceptions.NameTooLongException;
import Exceptions.NameTooShortException;
import Interface.loadable;
import jdk.nashorn.internal.runtime.linker.NashornBeansLinker;
import model.*;
import ui.Main;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class ToDoListGUI extends JFrame implements ActionListener{
    public JLabel label;
    private JTextField field;
    JTextArea consoleTexts;
    JScrollPane sp;
    String inputText;
    Console console;
    EnterButtonNotifier enterButtonNotifier;
    MyThread myThread;
    ToDoList toDoList;
    boolean run;
    boolean playIntroMusic;
    AudioInputStream audioInputStream;


    public ToDoListGUI(){
        super("ToDoList");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(600, 400));
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13) );
        setLayout(new FlowLayout());

        JPanel optionChunk = new JPanel();

        JButton addbtn = new JButton(new AbstractAction("Add Task") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("add button pressed");
                addItem();
            }
        });
        addbtn.setActionCommand("AddCommand");
        addbtn.addActionListener(this);
        //addbtn.setBounds(550, 50, 70, 20);
        //this.add(addbtn);
        optionChunk.add(addbtn);
        JButton rmbtn = new JButton(new AbstractAction("Remove Task") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("remove button pressed");
                removeItem();
            }
        });
        rmbtn.setActionCommand("RemoveCommand");
        rmbtn.addActionListener(this);
        //this.add(rmbtn);
        optionChunk.add(rmbtn);
        JButton shwbtn = new JButton(new AbstractAction("Show Tasks") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("Show button pressed");
                showItems();
            }
        });
        shwbtn.setActionCommand("ShowCommand");
        shwbtn.addActionListener(this);
        //this.add(shwbtn);
        //setVisible(true);
        optionChunk.add(shwbtn);
        JButton exitbtn = new JButton(new AbstractAction("Save") {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                //System.out.println("save button pressed");
                saveItems();
            }
        });
        exitbtn.setActionCommand("SaveCommand");
        exitbtn.addActionListener(this);
        //this.add(exitbtn);
        optionChunk.add(exitbtn);

//        enterButtonNotifier = new EnterButtonNotifier();
//        enterButtonNotifier.addObserver(Main);


        JButton enterbtn = new JButton(new AbstractAction("Help") {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getActionCommand().equals("HelpCommand")){
                    programInstructions();
                    switchMusic();
                }
            }
        });

        enterbtn.setActionCommand("HelpCommand");
        enterbtn.addActionListener(this);
        //this.add(enterbtn);
        optionChunk.add(enterbtn);

        myThread = new MyThread();

        label = new JLabel("label");
        field = new JTextField(50);
        consoleTexts = new JTextArea(15,45);
        sp = new JScrollPane(consoleTexts);
        DefaultCaret caret = (DefaultCaret)consoleTexts.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        sp.add(consoleTexts);
        sp.setViewportView(consoleTexts);
        PrintStream printStream = new PrintStream(new ConsoleOutput(consoleTexts));
        System.setOut(printStream);
        System.setErr(printStream);

        this.getContentPane().add(sp);
        //this.add(consoleTexts);
        consoleTexts.setEditable(false);
        this.add(optionChunk);
        this.add(field);
        this.add(label);
        pack();

        File MMI = new File("MeMyself&I.wav");
        File IAM = new File("ImAMess.wav");


        playIntroMusic = true;


        run();
        playIntro(MMI);
    }


    public void actionPerformed(ActionEvent e){
        //resume = true;
    }

    private void run(){
        try {
            webInput();
        } catch (IOException e){
        }
        try{
            setUpToDoList();
        } catch (IOException e){
        }
        programInstructions();


    }

    private void webInput()throws IOException{

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
    }

    private void setUpToDoList() throws IOException {
        toDoList = new ToDoList();
        loadable loader = new FileReaderLoader("savefile.txt");
        loader.loadFromFile(toDoList);
        ObserverNumberTask addTask = new ObserverNumberTask();
        toDoList.addObserver(addTask);
        run = true;
    }

    private static void programInstructions(){ //prints instructions for program home
        System.out.println(" To add task, enter format:");
        System.out.println(" taskName *space* importance *space* urgency");
        System.out.println(" 'important' if important, 'urgent' if urgent");
        System.out.println(" To remove task, enter name of task you want removed");
        System.out.println(" Shows list of all tasks in list");
        System.out.println(" Help shows instructions\n");
    }

    private void addItem(){
        inputText = field.getText();
        field.setText("");
        ArrayList<String> partsOfLine = splitOnSpace(inputText);
        String taskName = partsOfLine.get(0);
        String inputImportance = partsOfLine.get(1);
        String inputUrgency = partsOfLine.get(2);
        boolean importance = false;
        boolean urgency = false;
        if (inputImportance.equals("important")){
            importance = true;
        }
        if (inputUrgency.equals("urgent")){
            urgency = true;
        }
        try {
            Tasks newTask = new RunningTasks(taskName, importance, urgency);
            toDoList.addTask(newTask);
        } catch (NameTooLongException e){
            System.out.println("Name is too long, try again");
        } catch (NameTooShortException e){
            System.out.println("Name is too short, try again");
        }
    }

    private void removeItem(){
        System.out.println("Remove item selected");
        if (toDoList.isEmpty()){
            System.out.println("List is empty\n");
            field.setText("");
        } else {
            String taskName = inputText;
            toDoList.removeTask(taskName);
            field.setText("");
        }
    }

    private void showItems(){
        System.out.println("Show items selected");
        if(toDoList.isEmpty()){
            System.out.println("List is empty\n");
        } else {
            toDoList.printAllTasks();
        }
    }

    private void saveItems(){
        ArrayList<String > saveData = new ArrayList<>();
        toDoList.getSaveData(saveData);
        try {
            FileReaderSaver saver = new FileReaderSaver("savefile.txt");
            ObserverLoadSave saverObserver = new ObserverLoadSave();
            saver.addObserver(saverObserver);
            saver.SaveData(saveData);
        } catch (IOException e){
            System.out.println("cannot save");
        }
    }

    private ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private void playIntro(File Sound){
        if (playIntroMusic){
            try{
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(Sound));
                clip.start();
            } catch (Exception e){
            }
        }
    }

    private void playHelpMusic(File Sound){
        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Sound));
            clip.start();
        } catch (Exception e){
        }
    }

    private void switchMusic(){
        playIntroMusic = false;
        File IAM = new File("ImAMess.wav");
        playHelpMusic(IAM);
    }
}
