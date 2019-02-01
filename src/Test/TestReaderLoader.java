package Test;

import model.FileReaderLoader;
import model.FileReaderSaver;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestReaderLoader {

    @BeforeEach


    @Test
    public void TestReadFile() throws IOException{
        FileReaderLoader testLoader = new FileReaderLoader("savefile.txt");
        testLoader.fileReader();
    }

    @Test
    public void TestLoadFromFile() throws IOException{
        ToDoList testToDoList = new ToDoList();
        FileReaderLoader testLoader = new FileReaderLoader("savefile.txt");
        testLoader.loadFromFile(testToDoList);
        testToDoList.printAllTasks();
    }
}
