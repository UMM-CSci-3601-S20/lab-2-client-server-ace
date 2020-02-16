package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;


public class FilterTodosByCategoryFromDB {


  @Test
  public void filterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    Todo[] softwareD = db.filterTodosByCategory(allTodos, "Moo");
    assertEquals(0, softwareD.length, "Incorrect length");
}

@Test
public void firstTodoCategory() throws IOException {
  TodoDatabase db = new TodoDatabase("/todos.json");
  Todo[] allTodos = db.listTodos(new HashMap<>());
  String testCategory = allTodos[0].category;
  Todo[] actualCategory = db.filterTodosByCategory(allTodos, "software design");
  assertEquals(testCategory, actualCategory[0].category, "First category does not match");
}


}

