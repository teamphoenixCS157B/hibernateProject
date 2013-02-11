package movie_demo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

public class ActorInMovie
{
    private int ActorMovieid;
    private int actorID;
    private int movieID;
	
    public ActorInMovie() {}
    
    public ActorInMovie (int actorID, int movieID)
    {
        this.actorID = actorID;
        this.movieID = movieID;
    }
	
    @Id
    @GeneratedValue
    @Column(name="ActorMovieId")
    public int getId() { return ActorMovieid; }
    public void setId(int id) { this.ActorMovieid = id; }
    
    @Column(name="ActorID")
    public int getActorID() { return actorID; }
    public void setActorID(int actorID) { this.actorID = actorID; }
    
    @Column(name="MovieID")
    public int getMovieID() { return movieID; }
    public void setMovieID(int movieID) { this.movieID = movieID; }
}