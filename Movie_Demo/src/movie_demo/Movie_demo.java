/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package movie_demo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 *
 * @author enrique
 */
public class Movie_demo {

    private static final String HELP_MESSAGE = 
        "*** Commands: create, load, find <n>, add, delete, teachers, quit\n" +
        "***           students, classes";
    
    public static void main(String args[]) 
    {
        BufferedReader stdin = 
                new BufferedReader(new InputStreamReader(System.in));
        String command;
                                    
        Class klasses[] = {Actor.class, ContactInfo.class, 
                           Movie.class, Studio.class, ActorInMovie.class};
        HibernateContext.addClasses(klasses);
        do {
            System.out.print("\nCommand? ");
            
            try {
                command = stdin.readLine();
            }
            catch (java.io.IOException ex) {
                command = "?";
            }
            
            String parts[] = command.split(" ");
            
            if (command.equalsIgnoreCase("create")) {
                HibernateContext.createSchema();
            }
            else if (command.equalsIgnoreCase("load")) {
                Actor.load();
        //        Movie.load();
          //      Studio.load();
            }
            else if (command.equalsIgnoreCase("add")) {
            //    Actor.add();
            }
            else if (command.equalsIgnoreCase("delete")) {
             //   Actor.delete();
            }
            else if (command.equalsIgnoreCase("teachers")) {
              //  Movie.list();
            }
            else if (command.equalsIgnoreCase("students")) {
             //   Actor.list();
            }
            else if (command.equalsIgnoreCase("classes")) {
             //   Movie.list(); 
            }
//            else if (parts[0].equalsIgnoreCase("find") &&
//                    (parts.length >= 2)) {
//                long id = Long.parseLong(parts[1]);
//                Student student = Student.find(id);
//                
//                // Because the Student object is fetched from the database,
//                // we can access its fields only within a session. 
//                // Otherwise, we'll get a LazyInitializationException.
//                if (student != null) {
//                    student.printInSession();
//                }
//                else {
//                    System.out.printf("*** No student with id %d\n", id);
//                }
//            }
            else if (!command.equalsIgnoreCase("quit")) {
                System.out.println(HELP_MESSAGE);
            }
        } while (!command.equalsIgnoreCase("quit"));
    }
}
