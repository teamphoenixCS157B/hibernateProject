package movie_demo;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Entity
public class Actor 
{
    private int Actorid;	  
    private String actorName;
    private int income;
	private ContactInfo contact;   // (FK)
        private List<Movie> Movies;
    
    public Actor () {}
    
    public Actor(String actorName, int income, ContactInfo contact)
    {
        this.actorName = actorName;
        this.income = income;
		this.contact = contact;
    }
   
    @Id
    @GeneratedValue
    @Column(name="ActorID")
    public int getActorId() { return Actorid; }
    public void setId(int id) { this.Actorid = id; }
    
    @Column(name="ActorName")
    public String getActorName() { return actorName; }
    public void setActorName(String actorName) { this.actorName = actorName; }
    
    @Column(name="Income")
    public double getIncome() { return income; }
    public void setIncome(int income) { this.income = income; }

	// OneToOne
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name="ContactID")  // b/c (FK)
    public ContactInfo getContactID() { return contact; }
    public void setContactID(ContactInfo contactID) { this.contact = contactID; }
    
     /**
     * @return the Movies
     */
     @ManyToMany
     @JoinTable(name="ActorInMovie", 
      joinColumns={@JoinColumn(name="ActorID")},
     inverseJoinColumns={@JoinColumn(name="MovieID")})
    public List<Movie> getMovies() {
        return Movies;
    }

    /**
     * @param Movies the Movies to set
     */
    public void setMovies(List<Movie> Movies) {
        this.Movies = Movies;
    }
       public void printInSession()
    {
        Session session = HibernateContext.getSession();
        session.update(this);
        print();
        session.close();
    }
       
         /**
     * Print teacher attributes.
     */
    private void print()
    {
        System.out.printf("%d: %s $ %d (%s)\n", Actorid, actorName, income,
                                              contact.getEmail());
    }
    
     public static void load()
    {
        Session session = HibernateContext.getSession();
        
        // Load the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            session.save(new Actor("Mary Jane", 1200000, 
                                     new ContactInfo("Miami, Florida", new Date(1992,5,13), "mjane@sjsu.edu")));
        }
        tx.commit();
        session.close();

           System.out.println("Actor table loaded.");
}

   
}