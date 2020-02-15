package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * Tests umm3601.user.Database filterUsersByAge and listUsers with _age_ query
 * parameters
 */
public class FilterTodosByOwnerFromDB {

  @Test
  public void filterUsersByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allUsers = db.listTodos(new HashMap<>());

    Todo[] nameFry = db.filterTodosByOwner(allUsers, "Fry");
    assertEquals("Fry", nameFry.owner, "Incorrect number of users with age 27");

    Todo[] nameBlanche = db.filterTodosByOwner(allUsers, "Blanche");
    assertEquals(1, nameBlanche.length, "Incorrect number of users with age 33");
  }

  @Test
  public void filterTodosByOwner() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(queryParams);
    Todo[] Fry = db.filterTodosByOwner(allTodos, "Fry");
    assertEquals("Fry", Fry.equals("Fry"), "Incorrect name");
}

}

