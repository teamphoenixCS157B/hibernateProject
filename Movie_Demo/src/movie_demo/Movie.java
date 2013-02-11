package movie_demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;


@Entity
public class Movie 
{
    private int Movieid;	  
    private String title;
    private String genre;
	private int Mlength;
	private Studio studio;  // (FK)
    private List<Actor> Actors = new ArrayList<>();
    
    public Movie () {}
    
    public Movie(String title, String genre, int length, Studio studio)
    {
        this.title = title;
		this.genre = genre;
		this.Mlength = length;
		this.studio = studio;
    }
   
    @Id
    @GeneratedValue
    @Column(name="MovieID")
    public int getId() { return Movieid; }
    public void setId(int id) { this.Movieid = id; }
    
    @Column(name="Title")
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    @Column(name="Genre")
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    @Column(name="MLength")
    public int getMLength() { return Mlength; }
    public void setLength(int length) { this.Mlength = length; }

	@ManyToOne
	@JoinColumn(name="StudioID")  // b/c (FK)
    public Studio getStudio() { return studio; }
    public void setStudioID(Studio studioID) { this.studio = studioID; }
    
      /**
     * @return the Actors
     */
     @ManyToMany
     @JoinTable(name="ActorInMovie", 
      joinColumns={@JoinColumn(name="MovieID")},
     inverseJoinColumns={@JoinColumn(name="ActorID")})
    public List<Actor> getActors() {
        return Actors;
    }

    /**
     * @param Actors the Actors to set
     */
    public void setActors(List<Actor> Actors) {
        this.Actors = Actors;
    }
    
       public void printInSession()
    {
        Session session = HibernateContext.getSession();
        session.update(this);
        print();
        session.close();
    }
       
         
      void print()
      {
          
      }
      
    public static void load() {
        Session session = HibernateContext.getSession();
        
        //create movie objects?
        
        Movie pitchPerfect = new Movie("Pitch Perfect", "Comedy", 112, Studio.getStudio("Universal Pictures"));
        Movie theDarkKnightRises = new Movie("The Dark Knight Rises", "Drama", 165, Studio.getStudio("Warner Bros. Pictures"));
        Movie batmanBegins = new Movie("Batman Begins", "Action", 140, Studio.getStudio("Warner Bros. Pictures"));
        Movie harryPotter = new Movie("Harry Potter and the Goblet of Fire", "Action", 157, Studio.getStudio("Warner Bros. Pictures"));
        Movie womanInBlack = new Movie("The Woman in Black", "Thiller", 95, Studio.getStudio("CBS Films"));
        Movie premiumRush = new Movie("Premium Rush", "Action", 91, Studio.getStudio("Columbia Pictures"));
        Movie oneForTheMoney = new Movie("One for the Money", "Crime", 91, Studio.getStudio("Lionsgate"));
        Movie theDictator = new Movie("The Dictator", "Satire", 83, Studio.getStudio("Paramount Pictures"));
        Movie menInBlack3 = new Movie("Men in Black 3", "Fantasy", 106, Studio.getStudio("Columbia Pictures"));
        
        Actor adam = Actor.find("Adam Devine");
        Actor christian = Actor.find("Christian Bale");
        Actor daniel = Actor.find("Daniel Radcliffee");
        Actor joseph = Actor.find("Joseph Gordon Levitt");
        Actor katherine = Actor.find("Katherine Heigel");
        Actor sacha = Actor.find("Sacha baron Cohen");
        Actor will = Actor.find("Will Smith");
        
        pitchPerfect.getActors().add(adam);
        theDarkKnightRises.getActors().add(christian);
        theDarkKnightRises.getActors().add(joseph);
        batmanBegins.getActors().add(christian);
        womanInBlack.getActors().add(daniel);
        harryPotter.getActors().add(daniel);
        premiumRush.getActors().add(joseph);
        oneForTheMoney.getActors().add(katherine);
        theDictator.getActors().add(sacha);
        menInBlack3.getActors().add(will);
        
        
        

        // Load the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            session.save(new Movie("Flight", "Drama", 139, Studio.getStudio("Paramount Pictures")));
            //session.save(new Movie("The Dictator", "Satire", 83, Studio.getStudio("Paramount Pictures")));
            session.save(new Movie("Big Miracle", "Drama", 107, Studio.getStudio("Universal Pictures")));
            session.save(new Movie("Contraband", "Crime", 110, Studio.getStudio("Universal Pictures")));
            //session.save(new Movie("Pitch Perfect", "Comedy", 112, Studio.getStudio("Universal Pictures")));
            session.save(new Movie("Wanderlust", "Comedy", 98, Studio.getStudio("Universal Pictures")));
            //session.save(new Movie("Batman Begins", "Action", 140, Studio.getStudio("Warner Bros. Pictures")));
            //session.save(new Movie("Harry Potter and the Goblet of Fire", "Action", 157, Studio.getStudio("Warner Bros. Pictures")));
            //session.save(new Movie("The Dark Knight Rises", "Drama", 165, Studio.getStudio("Warner Bros. Pictures")));
            session.save(new Movie("Good Deeds", "Romance", 111, Studio.getStudio("Lionsgate")));
            //session.save(new Movie("One for the Money", "Crime", 91, Studio.getStudio("Lionsgate")));
            session.save(new Movie("The Cabin in the Woods", "Horror", 138, Studio.getStudio("Lionsgate")));
            session.save(new Movie("The Hunger Games", "Action", 142, Studio.getStudio("Lionsgate")));
            session.save(new Movie("Here comes the Boom", "Comedy", 105, Studio.getStudio("Columbia Pictures")));
            //session.save(new Movie("Men in Blasck 3", "Fantasy", 106, Studio.getStudio("Columbia Pictures")));
            //session.save(new Movie("Premium Rush", "Action", 91, Studio.getStudio("Columbia Pictures")));
            session.save(new Movie("That's My Boy", "Comedy", 114, Studio.getStudio("Columbia Pictures")));
            session.save(new Movie("Seven Psychopaths", "Crime", 110, Studio.getStudio("CBS Films")));
            //session.save(new Movie("The Woman in Black", "Thiller", 95, Studio.getStudio("CBS Films")));
            session.save(new Movie("Chronicle", "Sci-Fi", 83, Studio.getStudio("20th Century Fox")));
            session.save(new Movie("Diary of a Wimpy Kid: Dog Days", "Comedy", 94, Studio.getStudio("20th Century Fox")));
            session.save(new Movie("Prometheus", "Sci-Fi", 124, Studio.getStudio("20th Century Fox")));
            session.save(new Movie("This Means War", "Romance", 97, Studio.getStudio("20th Century Fox")));
            session.save(pitchPerfect);
            session.save(theDarkKnightRises);
            session.save(batmanBegins);
            session.save(harryPotter);
            session.save(womanInBlack);
            session.save(premiumRush);
            session.save(oneForTheMoney);
            session.save(theDictator);
            session.save(menInBlack3);
            
        }
        

        
        
        tx.commit();
        session.close();

        System.out.println("Movies table loaded.");
    }
    
    public static Movie getMovie(String name) {
        Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Movie where title = :title");

        query.setString("title", name);
        Movie move = (Movie) query.uniqueResult();

        session.close();
        return move;
    }

  
}