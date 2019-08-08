package finalLab;

/**
 * This is a collection of methods for processing (sorting/searching) candidate vote data.
 * @author carlo
 *
 */
public class ElectionResult {

	/**
	 * This method sorts a given Candidate array in ascending order based off
	 * their total votes.
	 * @param list
	 * @param length
	 */
	public static void sortResults (Candidate[] list, int length)
	{
		//Algorithm post condition: list objects are in ascending order.
		/**
		 * Merge Sort algorithm:
		 * Since it's fast for any data set at O(nlogn) time, and more practical than a heap sort (data is
		 * not organized in a binary tree). 
		 * ---
		 * Call mergeSort for the given Candidate array.
		 */
		
		mergeSort(list, 0, list.length);
	}
	
	/**
	 * This method sorts a given Candidate array using the merge sort algorithm. Only the elements within the given start and end bounds are sorted.
	 * @param candidates
	 * @param subArrayStart
	 * @param subArrayEnd
	 */
	private static void mergeSort (Candidate[] candidates, int subArrayStart, int subArrayEnd)
	{
		/**
		 * Note: Sub array endpoints begin as the endpoints of the entire array to sort.
		 * 
		 * If the current sub array endpoints ever make up an array of size 1:
		 * break the recursive loop as arrays of size one are already sorted. Return.
		 * 
		 * Divide the current sub array in half.
		 * Do another merge sort on each half to break them down even further (until they're 1-element arrays) and then merge and sort themselves.
		 * Merge together the two halves (using the midpoint to differentiate the two). 
		 * Note: Sub arrays of size 1 are never merged.
		 */
		
		if (subArrayEnd - subArrayStart < 2) return; // breaks recursion
		
		int midpoint = (subArrayStart + subArrayEnd) / 2;
		mergeSort(candidates, subArrayStart, midpoint);
		mergeSort(candidates, midpoint, subArrayEnd);
		mergeAndSort(candidates, subArrayStart, midpoint, subArrayEnd);
	}
	
	/**
	 * This method extends from mergeSort. It combines two sub arrays, left and right, divided by the midpoint, and sorts them in ascending order. 
	 * @param candidates
	 * @param startIndex
	 * @param midpoint
	 * @param endIndex
	 */
	private static void mergeAndSort(Candidate[] candidates, int startIndex, int midpoint, int endIndex)
	{
		/**
		 *If the end of the left array is smaller or equal to the beginning of the right array, return (the array is already sorted).
		 *EX: Two arrays of size 1: 5, 7... Already sorted.
		 *
		 *Create a temporary array made up of the length of the start and end indexes.
		 *Initialize index pointer variables for the sub arrays and temp array.
		 *
		 *Begin Loop
		 *As long as the left or right arrays are still being traversed:
		 *Compare the current left sub array index with the right sub array index,
		 *if the left index is smaller then put its value into the temp array,
		 *otherwise put the right index into the temp array.
		 *(The temp array will have elements sorted in ascending order)
		 *End Loop
		 *
		 *Move over elements from the left array that were not transferred over yet.
		 *Copy the elements that were inserted in the temp array to the candidates array.
		 */
		
		if (candidates[midpoint - 1].getTotalVotes() <= candidates[midpoint].getTotalVotes())
		{
			return; // the array is already sorted
		}
		
		int leftSubArrayIndex = startIndex;
		int rightSubArrayIndex = midpoint;
		int tempIndex = 0;
		
		Candidate[] tempArray = new Candidate[endIndex - startIndex];
		
		while (leftSubArrayIndex < midpoint && rightSubArrayIndex < endIndex)
		{
			//compare left sub array index with right sub array index
			if (candidates[leftSubArrayIndex].getTotalVotes() <= candidates[rightSubArrayIndex].getTotalVotes())
			{
				//if left index is the smaller value, put it into the temp array
				tempArray[tempIndex++] = candidates[leftSubArrayIndex++];
			}
			else
			{
				//otherwise put the right index into the temp array
				tempArray[tempIndex++] = candidates[rightSubArrayIndex++];
			}
		}
		
		//copies leftover elements from the left sub array, if any, from the leftSubArrayIndex to the startIndex + tempIndex. The # of elements to copy over is the # of elements in the left sub array that haven't been copied into the temp array yet.
		//EX: leftSubArrayIndex = 2. startIndex + tempIndex = 0 + 5 = 5. Moves the element from index 2 to index 5, or the next empty position in the temp array.
		System.arraycopy(candidates, leftSubArrayIndex, candidates, startIndex + tempIndex, midpoint - leftSubArrayIndex);
		
		// copy the elements currently stored in the tempArray into the candidates array
		System.arraycopy(tempArray, 0, candidates, startIndex, tempIndex);
	}
	
	/**
	 * Looks for a requested Candidate in a given Candidate array. 
	 * @param list
	 * @param length
	 * @param searchItem
	 * @return Either the found index of the requested Candidate, or -1 if the Candidate isn't in the array.
	 */
	public static int findCandidate (Candidate[] list, int length, String searchItem)
	{
		//Algorithm post condition: If searchItem is found in the list,it returns the location of searchItem; 
		//otherwise it returns -1.
		/**
		 * Linear Search algorithm:
		 * Chosen because it doesn't require sorting beforehand (which needs the candidates to have
		 * their votes initialized already, which could need several sorts for several searches). O(n) time
		 * complexity.
		 * ---
		 * 
		 * Begin loop.
		 * Loop through the given array.
		 * Check each element if it equals the search item. If it does, return its index.
		 * End loop.
		 * 
		 * At this point, return -1. The search item is not in the array.
		 */
		
		int index = 0;
		for (Candidate candidate : list)
		{
			if (candidate.getName().equals(searchItem)) return index;
			index++;
		}
		
		//entire array's been searched with no results
		return -1;
	}
}
