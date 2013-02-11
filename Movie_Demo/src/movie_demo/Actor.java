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
        
        Actor adam = new Actor("Adam DeVine", 1200000, 
                                     new ContactInfo("Waterloo, Iowa", new Date(1984,11,7), "aDeVine@moviestar.com"));
        Actor christian = new Actor("Christian Bale", 20000000, 
                                     new ContactInfo("Pembrokeshire, Wales", new Date(1974,1,30), "cBale@moviestar.com"));
        Actor daniel = new Actor("Daniel Radcliffe", 2200000, 
                                     new ContactInfo("London, England", new Date(1989,7,23), "dRadcliffe@moviestar.com"));
        Actor joseph = new Actor("Joseph Gordon Levitt", 3200000, 
                                     new ContactInfo("Los Angeles, CA", new Date(1981,2,17), "JGL@moviestar.com"));
        Actor katherine = new Actor("Katherine Heigl", 1200000, 
                                     new ContactInfo("Washington, DC", new Date(1978,11,24), "kHeigel@moviestar.com"));
        Actor sacha = new Actor("Sacha Baron Cohen", 2000000, 
                                     new ContactInfo("London, England", new Date(1971,10,13), "sCohen@moviestar.com"));
        Actor will = new Actor("Will Smith", 50000000, 
                                     new ContactInfo("Philadelphia, Pennsylvania", new Date(1968,9,24), "wSmith@moviestar.com"));
        
        
        Movie pitchPerfect = Movie.getMovie("Pitch Perfect");
        Movie TDKR = Movie.getMovie("The Dark Knight Rises");
        Movie batmanBegins = Movie.getMovie("Batman Begins");
        Movie harryPotter = Movie.getMovie("Harry Potter and the Goblet of Fire");
        Movie womanInBlack = Movie.getMovie("Woman In Black");
        Movie premiumRush = Movie.getMovie("Premium Rush");
        Movie onefortheMONEY = Movie.getMovie("One for the Money");
        Movie theDictator = Movie.getMovie("The Dictator");
        Movie menInBlack3 = Movie.getMovie("Men in Black 3");
        
        adam.getMovies().add(pitchPerfect);
        christian.getMovies().add(TDKR);
        christian.getMovies().add(batmanBegins);
        daniel.getMovies().add(harryPotter);
        daniel.getMovies().add(womanInBlack);
        joseph.getMovies().add(TDKR);
        joseph.getMovies().add(premiumRush);
        katherine.getMovies().add(onefortheMONEY);
        sacha.getMovies().add(theDictator);
        will.getMovies().add(menInBlack3);
        
        // Load the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            /*session.save(new Actor("Adam DeVine", 1200000, 
                                     new ContactInfo("Waterloo, Iowa", new Date(1984,11,7), "aDeVine@moviestar.com")));
            session.save(new Actor("Christian Bale", 20000000, 
                                     new ContactInfo("Pembrokeshire, Wales", new Date(1974,1,30), "cBale@moviestar.com")));
            session.save(new Actor("Daniel Radcliffe", 2200000, 
                                     new ContactInfo("London, England", new Date(1989,7,23), "dRadcliffe@moviestar.com")));
            session.save(new Actor("Joseph Gordon Levitt", 3200000, 
                                     new ContactInfo("Los Angeles, CA", new Date(1981,2,17), "JGL@moviestar.com")));
            session.save(new Actor("Katherine Heigl", 1200000, 
                                     new ContactInfo("Washington, DC", new Date(1978,11,24), "kHeigel@moviestar.com")));
            session.save(new Actor("Sacha Baron Cohen", 2000000, 
                                     new ContactInfo("London, England", new Date(1971,10,13), "sCohen@moviestar.com")));
            session.save(new Actor("Will Smith", 50000000, 
                                     new ContactInfo("Philadelphia, Pennsylvania", new Date(1968,9,24), "wSmith@moviestar.com")));*/
            
            session.save(adam);
            session.save(christian);
            session.save(daniel);
            session.save(joseph);
            session.save(katherine);
            session.save(sacha);
            session.save(will);
        }
        tx.commit();
        session.close();

           System.out.println("Actor table loaded.");
}
     public static Actor find(String name){
         
        Session session = HibernateContext.getSession();
        org.hibernate.Query query = session.createQuery("from Actor where actorName = :name");

        query.setString("name", name);
        Actor act = (Actor) query.uniqueResult();

        session.close();
        return act;
     }

   
}