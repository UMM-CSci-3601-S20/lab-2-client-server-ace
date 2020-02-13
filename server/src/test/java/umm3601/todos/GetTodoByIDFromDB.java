package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.todos.Database getTodo functionality
 */
public class GetTodoByIDFromDB {

  @Test
  public void getTodoTest() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo todo = db.getTodo("5889598555fbbad472586a56");
    assertEquals("Aliqua esse aliqua veniam id nisi ea. Ullamco Lorem ex aliqua aliquip cupidatat incididunt reprehenderit voluptate ad nisi elit dolore laboris.",
      todo.body, "Incorrect body");
  }

  @Test
  public void getTodoTestAgain() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo todo = db.getTodo("58895985ba6d35a801f171ac");
    assertEquals("Aliquip dolor cupidatat incididunt mollit commodo aliqua aute amet reprehenderit incididunt excepteur ipsum reprehenderit. Consectetur est velit aute proident occaecat exercitation exercitation.",
      todo.body, "Incorrect body");
  }
}
