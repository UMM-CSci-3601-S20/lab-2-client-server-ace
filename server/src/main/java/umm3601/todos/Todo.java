package umm3601.todos;

public class Todo{
  public String _id;
  public String owner;
  public boolean status;
  public String body;
  public String category;

  public String statusAsString() {
    if(status) {
      return "complete";
    } else {
      return "incomplete";
    }
  }
}
//defining the types necessary for each section of the webpage

