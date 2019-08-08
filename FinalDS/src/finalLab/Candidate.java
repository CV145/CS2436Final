package finalLab;

import MessageBoxes.MessageBox;

/**
 * Represents a presidential candidate, storing information such as name and an array of CandidateStates, which hold the number of votes per state.
 * This is a node whose key field is the total number of votes.
 * @author carlo
 *
 */
public class Candidate {

	private String name;
	private int totalVotes = 0; // key field
	
	
	
	/**
	 * Returns the candidate's name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Overwrites the candidate's name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	private CandidateState[] states;
	
	/**
	 * Constructor initializes a new candidate's name and votes per state to zero. 
	 * @param _name
	 */
	public Candidate(String _name)
	{
		name = _name;
		states = new CandidateState[4];
		
		for (int index = 0; index < 4; index++)
		{
			states[index] = new CandidateState(0);
		}
	}
	
	/**
	 * Overwrites the votes the candidate has for a particular state.
	 * @param state
	 * @param votes
	 */
	public void setVotes (int state, int votes)
	{
		states[state - 1].setVotes(votes);
	}
	
	/**
	 * Retrieves the total number of votes a candidate has earned. Make sure to call determineTotalVotes() beforehand,
	 * otherwise an inaccurate amount will be returned.
	 * @return totalVotes
	 */
	public int getTotalVotes()
	{
		return totalVotes;
	}
	
	/**
	 * Adds more votes to the current vote count for a particular state.
	 * @param state
	 * @param votes
	 */
	public void updateVotes (int state, int votes)
	{
		try {
		states[state].addVotes(votes); //causing null pointer error
		}
		catch (NullPointerException n)
		{
			MessageBox.displayMessage("There's been an error trying to add votes to states.");
			
			MessageBox.displayMessage("Problem candidate: " + name);
		}
	}
	
	/**
	 * Determines if two candidates have the same name.
	 * @param otherCan
	 * @return A boolean for whether this candidate has the same name as the given candidate.
	 */
	public boolean isEqual (Candidate otherCan)
	{
		//Returns true if the name of this candidate is the same as the name of the candidate specified by the parameter otherCan.
		return this.name.equals(otherCan.getName());
	}
	
	/**
	 * Compares the names between two candidates in terms of length.
	 * @param otherCan
	 * @return The difference between this candidate's name and the other candidate's name.
	 */
	public int compareTo (Candidate otherCan)
	{
		//Returns 0 if the name of this candidate is the same as the name of otherCan; returns < 0 if the name of this candidate is less
		//then the name of otherCan; returns > 0 if the name of this candidate is greater than the name of otherCan.
		int nameLength = name.length();
		int otherNameLength = otherCan.getName().length();
		return nameLength - otherNameLength;
	}
	
	/**
	 * Adds up all the votes from each of the Candidate's CandidateStates and
	 * sets the result to the Candidate's totalVotes.
	 * @return void
	 */
	public void determineTotalVotes()
	{
		/**
		 * Reset the current total vote count to zero.
		 * Loop through the CandidateState array. (O(n) time, n = # of states)
		 * Add the votes from each CandidateState to the totalVotes.
		 */
		
		totalVotes = 0;
		for (CandidateState state: states)
		{
			totalVotes += state.getVotes();
		}
	}
}
