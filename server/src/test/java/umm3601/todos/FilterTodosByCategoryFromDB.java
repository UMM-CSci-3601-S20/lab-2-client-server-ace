package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;


public class FilterTodosByCategoryFromDB {

//Defines the database to be used. Creates an array for all the todos as a hashmap

  @Test
  public void filterTodosByCategory() throws IOException {
    TodoDatabase db = new TodoDatabase("/todos.json");
    Todo[] allTodos = db.listTodos(new HashMap<>());
    Todo[] softwareD = db.filterTodosByCategory(allTodos, "Moo");
    assertEquals(0, softwareD.length, "Incorrect length");
}

@Test
public void firstTodoCategory3() throws IOException {
  TodoDatabase db = new TodoDatabase("/todos.json");
  Todo[] allTodos = db.listTodos(new HashMap<>());
  String testCategory = allTodos[0].category;
  Todo[] actualCategory = db.filterTodosByCategory(allTodos, "software design");
  assertEquals(testCategory, actualCategory[0].category, "First category does not match");
}

@Test
public void firstTodoCategory() throws IOException {
  TodoDatabase db = new TodoDatabase("/todos.json");
  Todo[] allTodos = db.listTodos(new HashMap<>());
  String testCategory = allTodos[4].category;
  Todo[] actualCategory = db.filterTodosByCategory(allTodos, "groceries");
  assertEquals(testCategory, actualCategory[4].category, "First category does not match");
}

//tests to see if the "homework" is the category of the third todo in the list of all todos
@Test
public void firstTodoCategory2() throws IOException {
  TodoDatabase db = new TodoDatabase("/todos.json");
  Todo[] allTodos = db.listTodos(new HashMap<>());
  String testString = allTodos[2].category;
  Todo[] actualCategory = db.filterTodosByCategory(allTodos, "homework");
  assertEquals(testString, actualCategory[2].category, "Category doesn't match");
}

}

