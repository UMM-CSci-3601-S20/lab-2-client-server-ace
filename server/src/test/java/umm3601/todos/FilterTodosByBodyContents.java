package umm3601.todos;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.junit.jupiter.api.Test;
public class FilterTodosByBodyContents{
    @Test
    public void filterDbCheck() throws IOException {
        TodoDatabase db = new TodoDatabase("/todos.json");
        Todo[] allTodos = db.listTodos(new HashMap<>());

        Todo [] filteredCheck = db.filterTodosByContents(allTodos,"I really hope this string does not appear anywhere");
        int testFilterSize = filteredCheck.length;
        assertEquals(0,testFilterSize,"That string managed to appear");
    }
    @Test
    public void checkFirstTodo() throws IOException{
        TodoDatabase db = new TodoDatabase("/todos.json");
        Todo[] allTodos = db.listTodos(new HashMap<>());
        String testString = "In sunt ex non tempor cillum commodo amet incididunt anim qui commodo quis. Cillum non labore ex sint esse.";
        Todo[] filteredTodos = db.filterTodosByContents(allTodos,testString);
        assertEquals(1, filteredTodos.length, "There is more than one result when there shoulf be 0");
        assertEquals(allTodos[0].body,filteredTodos[0].body, "Wrong body message");

    }
}