package finalLab;

/**
 * This class represents the number of votes a candidate has per state. 
 * @author carlo
 *
 */
public class CandidateState {

	private int votes;
	
	/**
	 * This is the constructor that initialized CandidateState parameters.
	 * @param _votes
	 */
	public CandidateState(int _votes)
	{
		this.votes = _votes;
	}
	
	/**
	 * Overwrites votes.
	 * @param votes
	 */
	public void setVotes (int votes)
	{
		this.votes = votes;
	}
	
	/**
	 * Adds more votes to current vote count.
	 * @param votes
	 */
	public void addVotes (int votes)
	{
		this.votes += votes;
	}
	
	/**
	 * Retrieve votes.
	 * @return int
	 */
	public int getVotes()
	{
		return this.votes;
	}
}
