/**
 * 
 * RR simulates a Round Robin cpu scheduling method 
 * Round Robin meaning that each process will run for a predetermined time (a time quantum ) set by the user
 * Each process will run in "rounds" consuming their burst time in segments of this quantum until they finish
 * I adapted this code from my FCFS code 
 * @author Gregory
 */
import java.util.ArrayList;
import java.util.Scanner;
public class RR {
	public static void main(String[] args)
	{
		//create scanner object to allow user input
		Scanner in = new Scanner(System.in);
		//create array list of process objects
		ArrayList<Process> processes = new ArrayList<Process>();
		
		//hard code our processes
		processes.add(new Process(0, 0, 20));
		processes.add(new Process(1, 0, 5));
		processes.add(new Process(2, 0, 10));
		processes.add(new Process(3, 0, 2));
		
		// index is a variable to access processes in the array later in the program  
		int index = 0;
		//the time that is chosen by the user 
		int quantum = 0;
		
		//total number of processes in the array list
		int numProcesses = processes.size();
		//secondary number variable to hold the current number of processes in the array
		int numProc = processes.size();
		//total wait time 
		int waitTime = 0;
		//total active cpu time
		int cpuTime = 0;
		
		//prompt the user to enter in the desired time quantum
		System.out.println("Enter the length of the time Quantum");
		//use the scanner object to get the time quantum determined by the user 
		quantum = in.nextInt();
		
		//setup output
		System.out.println("PID    |  CPU   |   WT   |   BT");
		System.out.println("-------------------------------");
		
		
		//while loop will keep going until the array list processes is empty
		while(! processes.isEmpty())
		{
			
				//nested if statements to actual perform the rounds, first checks if we have reached the limit of our array
				if(index == numProc)
				{
					//for loop to perform each round of the time quantum
					for(int i = 0; i <= numProc - 1; i++)
					{
						//begin by removing a process from the array and putting it in new a new process object
						Process pc = processes.remove(i);
						//decrement numProc due to removed process
						numProc = numProc - 1;
						
						//check  to see if the current processes burst time is either greater than the quantum or greater than 0
						if(pc.burstTime >= quantum || pc.burstTime > 0)
						{
							//new variable burst subtracts the current processes burst time by the quantum
							int burst = pc.getBurst() - quantum;
							//if the burst time is positive add the process back into the array list from where it came from
							if(burst > 0)
							{
								//add the process back to index i with new burst time equal to burst
								processes.add(i, new Process(pc.getIndex(), pc.getArrival(), burst));
								//increment numProc because we added a process
								numProc = numProc + 1;
								
							}
							
							
						}
						
					}	
				//reset index to zero and continue with the running of processes 
				index = 0;
				
			}
				//check to see if we still have processes in the array list 
			if(processes.size() > 0)
			{
				
			//create process object at array index "index" 
			Process p = processes.get(index);
			
			//if the process has a burst time less than the quantum remove it from the list 
			if(p.getBurst() < quantum )
			{
				//remove the process at array index "index"
				processes.remove(index);
				//decrement numProce due to removed process
				numProc = numProc - 1;
				//decrement index because of consumed process
				index = index - 1;
				
			}
		
			//check to see if the arraylist is empty
			if(!processes.isEmpty())
			{
				// if else statement checks the total wait time to the arrival time of the current process
				if(p.arrivalTime >= waitTime)
				{
					//if the processes arrival time is larger than the total wait time, add it to the total wait time
					//total wait time is calculated by adding the existing wait time and the current processes burst time
					waitTime = waitTime + p.arrivalTime + p.burstTime;
				}else if(p.arrivalTime <= waitTime)
				{
					// if the arrival time of the current process is less than the total wait time, subtract it from the total wait time
					//total wait time is calculated from the difference of arrival and wait time plus the current processes burst time
					waitTime = (waitTime - p.arrivalTime) + p.burstTime;
				}
				
			
			}
			//cpue time is incremented by the burst time of each process
			cpuTime += p.burstTime;
			//process output for each process
			System.out.println("P"+ p.index + "     |   " + cpuTime + "   |   " + waitTime + "   |   " + p.burstTime);
			//increment index to access the next process
			index++;
			
		}	
			
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
