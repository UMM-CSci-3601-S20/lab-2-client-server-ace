package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import com.google.gson.Gson;


public class TodoDatabase {

//an array of all the todos
  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

//returns the size of all todos (quantity) in the database
  public int size() {
    return allTodos.length;
  }

  /**
   * Get the single todo specified by the given ID. Return `null` if there is no
   * todo with that ID.
   *
   * @param id the ID of the desired todo
   * @return the todo with the given ID, or null if there is no todo with that ID
   */


  public Todo getTodo(String id) {
    return Arrays.stream(allTodos).filter(x -> x._id.equals(id)).findFirst().orElse(null);
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  //A query is a request for data or information from a database table or combination of tables

  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    if (queryParams.containsKey("owner")) {
      String ownerString = queryParams.get("owner").get(0);
      filteredTodos = filterTodosByOwner(filteredTodos, ownerString);
    }

    if (queryParams.containsKey("category")){
      String categoryString = queryParams.get("category").get(0);
      filteredTodos = filterTodosByCategory(filteredTodos,categoryString);
    }

    return filteredTodos;
  }

//filtering the todos by owner method
  public Todo[] filterTodosByOwner(Todo[] todos, String ownerString) {
    return Arrays.stream(todos).filter(x -> x.owner.equals(ownerString)).toArray(Todo[]::new);
  }

  //filtering the todos by category method
  public Todo[] filterTodosByCategory(Todo[] todos, String categoryString) {
    return Arrays.stream(todos).filter(x -> x.category.equals(categoryString)).toArray(Todo[]::new);
  }



}
