package finalLab;

//Represents the number of votes a candidate has per state
public class CandidateState {

	private int votes;
	
	//constructor
	public CandidateState(int _votes)
	{
		votes = _votes;
	}
	
	public void setVotes (int votes)
	{
		this.votes = votes;
	}
	
	public void addVotes (int votes)
	{
		this.votes += votes;
	}
	
	public int getVotes()
	{
		return this.votes;
	}
}
