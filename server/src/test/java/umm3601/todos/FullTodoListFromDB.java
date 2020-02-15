package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class FullTodoListFromDB{
  @Test
  public void totaTodoCount() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    int expectedLength = db.size();
    assertEquals(expectedLength, allTodos.length, "Incorrect total number of users");
  }
  @Test
  public void firstTodoInFullList()throws IOException{
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    Todo firstTodo = allTodos[0];
    String testBody = "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.";
    assertEquals("Blanche", firstTodo.owner, "Incorrect owner");
    assertEquals(false, firstTodo.status, "Incorrect status");
    assertEquals(testBody, firstTodo.body, "Incorrect body");
    assertEquals("software design", firstTodo.category, "Incorrect category");
  }
}
