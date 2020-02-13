package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class TodoDatabase {

  private Todo[] allTodos;

  public TodoDatabase(String todoDataFile) throws IOException {
    Gson gson = new Gson();
    InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(todoDataFile));
    allTodos = gson.fromJson(reader, Todo[].class);
  }

  public int size() {
    return allTodos.length;
  }

  /**
   * Get an array of all the todos satisfying the queries in the params.
   *
   * @param queryParams map of key-value pairs for the query
   * @return an array of all the todos matching the given criteria
   */
  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredUsers = allTodos;

    //TODO: Add filters for query parameters here

    return filteredUsers;
  }
}
