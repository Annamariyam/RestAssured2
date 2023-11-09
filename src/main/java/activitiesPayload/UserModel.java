package activitiesPayload;

// Data model for user information.

public class UserModel {
   private int id;               // User ID
   private String title;         // User's title
   private String dueDate;       // Task due date
   private boolean completed=true; // Task completion status
   
   // Getter and Setter methods for the class fields
   public int getId() {
       return id;  // Get the user's ID
   }

   public void setId(int id) {
       this.id = id;  // Set the user's ID
   }

   public String getTitle() {
       return title;  // Get the user's title
   }

   public void setTitle(String title) {
       this.title = title;  // Set the user's title
   }

   public String getDueDate() {
       return dueDate;  // Get the task due date
   }

   public void setDueDate(String dueDate) {
       this.dueDate = dueDate;  // Set the task due date
   }

   public boolean isCompleted() {
       return completed;  // Check if the task is completed
   }

   public void setCompleted(boolean completed) {
       this.completed = completed;  // Set the task completion status
   }
}
	
	
	