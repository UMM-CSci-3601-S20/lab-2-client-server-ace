package umm3601.todos;

import java.io.IOException;
import java.io.InputStreamReader;
//import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Map;
import java.util.Arrays;

import com.google.gson.Gson;

//import io.javalin.http.BadRequestResponse;


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
  public Todo[] listTodos(Map<String, List<String>> queryParams) {
    Todo[] filteredTodos = allTodos;

    //TODO: Add filters for query parameters here
    if(queryParams.containsKey("status")){
      String targetStatus = queryParams.get("status").get(0);
      boolean _targetStatus;
      if(targetStatus.equals("complete")){
        _targetStatus = true;
      }
      else{
        _targetStatus = false;
      }
      filteredTodos = filterTodosByStatus(filteredTodos, _targetStatus);
      }

    if (queryParams.containsKey("contains")){
      String targetBody = queryParams.get("contains").get(0);
      filteredTodos = filterTodosByContents(filteredTodos, targetBody);
    }
  
    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }
  
  public Todo[] filterTodosByContents(Todo[] todos,String targetBody){
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }
}
