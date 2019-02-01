//package Test;
//
//import model.Tasks;
//import model.ToDoList;
//import java.util.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
//
//import java.util.List;
//
//public class TestToDoList {
//    private ToDoList testToDoList;
//    private Tasks testTask1;
//    private Tasks testTask2;
//    private Tasks testTask3;
//    private Tasks testTask4;
//
//    @BeforeEach
//    public void runBefore(){
//        testToDoList = new ToDoList();
//        testTask1 = new Tasks("Giraffe", false, false);
//        testTask2 = new Tasks("Cat", true, false);
//        testTask3 = new Tasks("Dolphin", true, false);
//        testTask4 = new Tasks("CharlieRabbit", true, false);
//    }
//
//    @Test
//    public void testAddTask(){
//        add4Tasks();
//        Tasks testTaskCheetah = new Tasks("Cheetah", true, true);
//        Tasks testTask1Duplicate = new Tasks("Giraffe", false, true);
//        testToDoList.addTask(testTaskCheetah);
//        assertTrue(testToDoList.containsTask("Cheetah"));
//        testToDoList.addTask(testTask1Duplicate);
//        assertFalse(testToDoList.containsTask("Giraffe"));
//
//    }
//
//    private void add4Tasks(){
//        testToDoList.addTask(testTask1);
//        testToDoList.addTask(testTask2);
//        testToDoList.addTask(testTask3);
//        testToDoList.addTask(testTask4);
//    }
//}
//
