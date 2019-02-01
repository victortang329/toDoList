package Test;

import Exceptions.NameTooLongException;
import Exceptions.NameTooShortException;
import model.RegularTask;
import model.RunningTasks;
import model.Tasks;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class TestTasks {
    Tasks testTask;

    @BeforeEach
    public void TestConstructor(){
        testTask  = new RegularTask("TestName");
        assertEquals(testTask.getName(), "TestName");
        assertFalse(testTask.getImportance());
    }

    @Test
    public void testGetName(){
        assertEquals("TestName", testTask.getName());
    }

    @Test
    public void testGetImportance(){
        assertTrue(!testTask.getImportance());
    }

    @Test
    public void testIsName(){
        assertFalse(testTask.isName("betty"));
        assertTrue(testTask.isName("TestName"));
    }

    @Test
    public void testNameExceptions(){
        try{
            Tasks longName = new RunningTasks("LongNameCannot", true,true);
        } catch (NameTooShortException e){
            System.out.println("name too short exception");
        } catch (NameTooLongException e){
            System.out.println("name too long exception");
        }
    }

    @Test
    public void testAddToList(){
        ToDoList testList = new ToDoList();
        testTask.addToList(testList);
        assertTrue(testList.containsTask(testTask));
        Tasks testTask2 = new RegularTask("testTask2");
        testList.addTask(testTask2);
        assertTrue(testList.containsTask(testTask2));
    }


}
