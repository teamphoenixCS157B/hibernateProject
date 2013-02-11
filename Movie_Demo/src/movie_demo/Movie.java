package movie_demo;

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
    private List<Actor> Actors;
    
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
      
         public static void load()
    {
        Session session = HibernateContext.getSession();
        
        // Load the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            session.save(new Movie("Transformer", "Drama" , 138 ,Studio.getStudio("DreamWorks")));
        }
        tx.commit();
        session.close();

           System.out.println("Actor table loaded.");
}

  
}