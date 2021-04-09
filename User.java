package application;

public class User {
 private String username = "";
 private String password = "";
 private Habit[] ListOfHabits; 
 
 public static void main(String [] args) { 
	 
	 
 }
 
 public void createHabit(Habit habit) { 
	 if (ListOfHabits[0] == null) ListOfHabits[0] = habit; 
	 
	 for (int i = 1; i <= ListOfHabits.length; i++) {
		 if( (ListOfHabits[i] == null) && (ListOfHabits[i - 1] != null) ) {
			 ListOfHabits[i] = habit;
		 }
	 }
	 
 }
 
 
}
