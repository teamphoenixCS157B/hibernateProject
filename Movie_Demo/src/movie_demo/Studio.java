package movie_demo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

@Entity
public class Studio 
{
    private int Studioid;
    private String name;
    private String website;
	private double income;
	private int year;
        private List<Movie> Movies;

    
    public Studio () {}
    
    public Studio(String name, String website, double income, int year)
    {
        this.name = name;
		this.website = website;
		this.income = income;
		this.year = year;
    }
   
    @Id
    @GeneratedValue
    @Column(name="StudioID")
    public int getId() { return Studioid; }
    public void setId(int id) { this.Studioid = id; }
    
    @Column(name="StudioName")
    public String getStudioName() { return name; }
    public void setStudioName(String name) { this.name = name; }
	    
    @Column(name="Website")
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    
    @Column(name="Income")
    public double getIncome() { return income; }
    public void setIncome(double income) { this.income = income; }
	
    @Column(name="Year")
    public int getyear() { return year; }
    public void setyear(int year) { this.year = year; }
    
      /**
     * @return the Movies
     */
    @OneToMany(mappedBy="Studio", targetEntity=Movie.class,
               cascade=CascadeType.ALL, fetch=FetchType.EAGER)
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
       
       
      void print()
      {
          
      }
      
        public static void load()
    {
        Session session = HibernateContext.getSession();
        
        // Load the Student table in a transaction.
        Transaction tx = session.beginTransaction();
        {
            session.save(new Studio("20th Centrury Fox", "www.foxmovies.com" , 100000 , 1935));
            session.save(new Studio("CBS Films", "www.CBSFilms.com" , 2000000 , 2007));
            session.save(new Studio("Columbia Pictures", "www.Sonypictures.com" , 50000000 , 1918));
            session.save(new Studio("Lionsgate", "www.lionsgate.com" , 63000000 , 1997));
            session.save(new Studio("Paramount Pictures", "www.paramount.com" , 300000000 , 1912));
            session.save(new Studio("Universal Pictures", "www.universalstudios.com" , 27000000 , 1912));
            session.save(new Studio("Warner Bros. Pictures", "www.warnerbros.com" , 1200000000 , 1918));
        }
        tx.commit();
        session.close();

           System.out.println("Actor table loaded.");
    }
           
        public static Studio getStudio(String StudioName){
             Session session = HibernateContext.getSession();
        Query query = session.createQuery("from Studio where StudioName = :name");
        
        query.setString("name", StudioName);
        Studio stud = (Studio) query.uniqueResult();
        
        session.close();
        return stud;
        }


   
}