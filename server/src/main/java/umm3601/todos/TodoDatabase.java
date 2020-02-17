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

    //Order the list of todos by a given category
    //Note: should always be the second to last filter
    if(queryParams.containsKey("orderBy")) {
      String category = queryParams.get("orderBy").get(0).toLowerCase();
      filteredTodos = sortByCategory(filteredTodos, category);
    }

    //Limit the size of the returned list
    //Note: should always be the last filter
    if(queryParams.containsKey("limit")) {
      String limit = queryParams.get("limit").get(0);
      Todo[] truncatedArray = Arrays.copyOf(filteredTodos, Integer.parseInt(limit));
      for(int i = 0; i < truncatedArray.length; i++) {
        truncatedArray[i] = filteredTodos[i];
      }
      filteredTodos = truncatedArray;
    }

    return filteredTodos;
  }

  public Todo[] filterTodosByStatus(Todo[] todos, boolean targetStatus) {
    return Arrays.stream(todos).filter(x -> x.status == targetStatus).toArray(Todo[]::new);
  }

  public Todo[] filterTodosByContents(Todo[] todos,String targetBody){
    return Arrays.stream(todos).filter(x -> x.body.contains(targetBody)).toArray(Todo[]::new);
  }


  /**
   * Sort the array of todos alphabetically based on a given category
   *
   * @param todos the array of todos to be sorted
   * @param categoryToSortBy the category to sort by
   * @return the sorted array of todos
   */
  private Todo[] sortByCategory(Todo[] todos, String categoryToSortBy) {

    switch(categoryToSortBy) {
      //Sort by owner
      case "owner":
        return Arrays.stream(todos).sorted((x1,x2) -> x1.owner.compareTo(x2.owner)).toArray(Todo[]::new);

      //Sort by status
      case "status":
        return Arrays.stream(todos).sorted((x1,x2) -> x1.statusAsString().compareTo(x2.statusAsString())).toArray(Todo[]::new);

      //Sort by body
      case "body":
        return Arrays.stream(todos).sorted((x1,x2) -> x1.body.compareTo(x2.body)).toArray(Todo[]::new);

      //Sort by category
      case "category":
        return Arrays.stream(todos).sorted((x1,x2) -> x1.category.compareTo(x2.category)).toArray(Todo[]::new);

      default:
        return todos;
    }
  }
}
