/**
 * FCFS simulates a first come first serve CPU scheduling method
 * First come first serve meaning which ever process arrives first gets to utilize the CPU burst first 
 * @author Gregory
 */
import java.util.ArrayList;


public class FCFS {
	public static void main(String[] args)
	{
		// create array to hold our processes
		ArrayList<Process> processes = new ArrayList<Process>();
		
		//hardcode the processes by index(number), arrivalTime, and burstTime
		processes.add(new Process(0, 0, 24));
		processes.add(new Process(1, 2, 3));
		processes.add(new Process(2, 3, 3));
		
		
		//total number of processes 
		int numProcesses = processes.size();
		//variable to hold the total waitTime
		int waitTime = 0;
		//variable to hold the active CPU time
		int cpuTime = 0;
		
		//setup output
		System.out.println("PID    |  CPU   |   WT   |   BT");
		System.out.println("-------------------------------");
		
		
		//while loop will keep going until the array list processes is empty
		while(! processes.isEmpty())
		{
			//create new process object by removing the first element in our process array
			Process p = processes.remove(0);
			
			//check to see if the array list is empty
			if(!processes.isEmpty())
			{
				// if else statement checks the total wait time to the arrival time of the current process
				if(p.arrivalTime >= waitTime)
				{
					//if the processes arrival time is larger than the total wait time, add it to the total wait time
					//total wait time is calculated by adding the existing wait time and the current processes burst time
					waitTime += waitTime + p.arrivalTime + p.burstTime;
				}else if(p.arrivalTime <= waitTime)
				{
					// if the arrival time of the current process is less than the total wait time, subtract it from the total wait time
					//total wait time is calculated from the difference of arrival and wait time plus the current processes burst time
					waitTime += (waitTime - p.arrivalTime) + p.burstTime;
				}
				
			
			}	
			//cpu time is incremented by the burst time of each process
			cpuTime += p.burstTime;
			//process output for each process
			System.out.println("P"+ p.index + "     |   " + cpuTime + "   |   " + waitTime + "   |   " + p.burstTime);
		}
		//calculate the average wait time by dividing total wait time by the total number of processes
		double avgWaitTime = waitTime/numProcesses;
		//set up output
		System.out.println("-------------------------------");
		//display the average wait time
		System.out.println("Average wait time : " + avgWaitTime + " ms");
		//display the total cpuTime
		System.out.println("Total Cpu Usage time : " + cpuTime + " ms");
		
	}
	
	

}
