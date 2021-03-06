package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


public class FilterTodosByOwnerFromDB {


  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    Todo[] Fry = db.filterTodosByOwner(allTodos, "Quack");
    assertEquals(0, Fry.length, "Incorrect length");
}

@Test
public void firstTodoOwner() throws IOException {
  TodoDatabase db = new TodoDatabase("/todos.json");
  Todo[] allTodos = db.listTodos(new HashMap<>());
  String testOwner = allTodos[0].owner;
  Todo[] actualOwner = db.filterTodosByOwner(allTodos, "Blanche");
  assertEquals(testOwner, actualOwner[0].owner, "First owner does not match");
}


}

