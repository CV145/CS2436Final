package finalLab;

//Implements a candidate as an object, storing information such as name and votes per state
public class Candidate {

	private String name;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CandidateState[] getStates() {
		return states;
	}

	public void setStates(CandidateState[] states) {
		this.states = states;
	}

	private CandidateState[] states = new CandidateState[4];
	
	//constructor initializes states array and name
	public Candidate(String _name)
	{
		name = _name;
		
		for (CandidateState state : states)
		{
			state = new CandidateState(0);
		}
	}
	
	// sets the votes of a candidate for a particular state
	public void setVotes (int state, int votes)
	{
		states[state - 1].setVotes(votes);
	}
	
	// changes the votes of a candidate for a particular state
	public void updateVotes (int state, int votes)
	{
		states[state - 1].addVotes(votes);
	}
	
	// determines if two candidates have the same name
	public boolean isEqual (Candidate otherCan)
	{
		//Returns true if the name of this candidate is the same as the name of the candidate specified by the parameter otherCan.
		return this.name.equals(otherCan.getName());
	}
	
	// compares the names of two candidates
	public int compareTo (Candidate otherCan)
	{
		//Returns 0 if the name of this candidate is the same as the name of otherCan; returns < 0 if the name of this candidate is less
		//then the name of otherCan; returns > 0 if the name of this candidate is greater than the name of otherCan.
		int nameLength = name.length();
		int otherNameLength = otherCan.getName().length();
		return nameLength - otherNameLength;
	}
}
