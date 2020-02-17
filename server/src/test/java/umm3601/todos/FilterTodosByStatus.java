package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

public class FilterTodosByStatus{
    @Test
    public void filterTodosByStatus() throws IOException {
        TodoDatabase db = new TodoDatabase("/todos.json");
        Todo[] allTodos = db.listTodos(new HashMap<>());
        int dbSize = allTodos.length;

        Todo[] trueTodos = db.filterTodosByStatus(allTodos, true );

        Todo[] falseTodos = db.filterTodosByStatus(allTodos, false);
        assertEquals(dbSize, trueTodos.length + falseTodos.length,"Wrong number of statuses" );
    }
}