/**
 * This Class is to set up the processes to be put through the various schedulers
 * @author Gregory
 *
 */
public class Process {
	//each process will have an index, burst time and arrival time
	public int index;
	public int burstTime;
	public int arrivalTime;
	
	//set a process object by index, arrival time, and burst tim
	public Process(int i, int a, int b)
	{
		index = i;
		burstTime = b;
		arrivalTime = a;
	}
	
	//the get methods are used to get the processes burst time arrival time and index
	public int getBurst()
	{
		return burstTime;
	}
	public int getArrival()
	{
		return arrivalTime;
	}
	public int getIndex()
	{
		return index;
	}
}
