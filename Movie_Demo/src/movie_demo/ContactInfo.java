package movie_demo;

import java.util.Date;
import javax.persistence.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;

@Entity
@Table(name="ContactInfo")
public class ContactInfo 
{
    private int Contactid;
    private String hometown;
    private Date dob;
    private String email;
    
    public ContactInfo () {}
    
    public ContactInfo(String hometown, Date dob, String Email)
    {
        this.hometown = hometown;
        this.dob = dob;
        this.email = Email;
    }
   
    @Id
    @GeneratedValue
    @Column(name="ContactId")
    public int getId() { return Contactid; }
    public void setId(int id) { this.Contactid = id; }
    
    @Column(name="Hometown")
    public String getHometown() { return hometown; }
    public void setHometown(String hometown) { this.hometown = hometown; }
    
    @Column(name="DOB")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getDOB() { return dob; }
    public void setDOB(Date dob) { this.dob = dob; }

    /**
     * @return the email
     */
    @Column(name="Email")
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    
}