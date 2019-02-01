package model;

import Interface.Reader;
import Interface.loadable;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderLoader implements loadable, Reader {
    static List<String> lines;
    PrintWriter writer;

    public FileReaderLoader(String path) throws IOException{
        lines = Files.readAllLines(Paths.get(path));
        writer = new PrintWriter(path, "UTF-8");
    }

    //for testing
    public void fileReader(){
        for (String s : lines){
            System.out.print(s);
            writer.println(s);
        }
    }

    public void loadFromFile(ToDoList toDoList){
        for (String line : lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            String taskName = partsOfLine.get(0);
            int type = taskType(partsOfLine.get(1));
            if (type == 1){
                Tasks newtask = new UrgentImportantTask(taskName);
                toDoList.addTask(newtask);
            } else if (type == 2) {
                Tasks newtask = new ImportantTask(taskName);
                toDoList.addTask(newtask);

            } else if (type == 3) {
                Tasks newtask = new UrgentTask(taskName);
                toDoList.addTask(newtask);

            } else {
                Tasks newtask = new RegularTask(taskName);
                toDoList.addTask(newtask);
            }
        }
    }

     private ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    private int taskType(String inputType){
        if (inputType.equals("urgent_important")){
            return 1;
        } else if (inputType.equals("not-urgent_important")){
            return 2;
        } else if (inputType.equals("urgent_not-important")){
            return 3;
        } else {
            return 4;
        }
    }


}
