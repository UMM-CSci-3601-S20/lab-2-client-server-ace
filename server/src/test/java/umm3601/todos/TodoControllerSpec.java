package umm3601.todos;

import umm3601.Server;

import io.javalin.http.Context;
import io.javalin.core.validation.Validator;
import io.javalin.http.NotFoundResponse;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.ArgumentCaptor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Tests the logic of the TodoController
 *
 * @throws IOException
 */
public class TodoControllerSpec {

  private Context ctx = mock(Context.class);

  private TodoController todoController;
  private static TodoDatabase db;

  @BeforeEach
  public void setUp() throws IOException {
    ctx.clearCookieStore();

    db = new TodoDatabase(Server.TODO_DATA_FILE);
    todoController = new TodoController(db);
  }

  /**
   * Tests the limiting of the number of todos that are displayed
   */
  @Test
  public void testLimit() throws IOException {
    int randomLimit = (int) (Math.random() * 100) + 1;
    String limitAsString = Integer.toString(randomLimit);

    Map<String, List<String>> queryParams = new HashMap<>();
    queryParams.put("limit", Arrays.asList(new String[] { limitAsString }));
    when(ctx.queryParamMap()).thenReturn(queryParams);

    todoController.getTodos(ctx);

    ArgumentCaptor<Todo[]> argument = ArgumentCaptor.forClass(Todo[].class);
    verify(ctx).json(argument.capture());
    assertEquals(randomLimit, argument.getValue().length);

  }

  /**
   * Tests GET request with existent id
   */
  @Test
  public void GET_to_request_todo_with_existent_id() throws IOException {
    when(ctx.pathParam("id", String.class)).thenReturn(new Validator<String>("58895985ae3b752b124e7663", ""));
    todoController.getTodo(ctx);
    verify(ctx).status(201);
  }

  @Test
  public void GET_to_request_todo_with_nonexistent_id() throws IOException {
    when(ctx.pathParam("id", String.class)).thenReturn(new Validator<String>("nonexistent", ""));
    Assertions.assertThrows(NotFoundResponse.class, () -> {
      todoController.getTodo(ctx);
    });
  }
}
