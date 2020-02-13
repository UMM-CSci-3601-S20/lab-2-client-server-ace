package umm3601.todos;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class TodoController {

  private TodoDatabase database;

  public TodoController(TodoDatabase database) {
    this.database = database;
  }

  /**
   * Get the single todo specified by the `id` parameter in the request.
   *
   * @param ctx a Javalin HTTP context
   */
  public void getTodo(Context ctx) {
    String id = ctx.pathParam("id", String.class).get();
    Todo todo = database.getTodo(id);
    if (todo != null) {
      ctx.json(todo);
      ctx.status(201);
    } else {
      throw new NotFoundResponse("No user with id " + id + " was found.");
    }
  }
}
