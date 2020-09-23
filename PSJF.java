/**
 * PSJF simulates the preemptive shortest job first CPU scheduling method 
 * preemptive shortest job first meaning that taking into account arrival times a process can interrupt another process if it has a shorter burst time
 * This code was also adapted from my FCFS code
 * @author Gregory
 */
import java.util.ArrayList;

public class PSJF {
	public static void main(String[] args)
	{
		//create array list of processes
		ArrayList<Process> processes = new ArrayList<Process>();
		
		//hard code the processes
		processes.add(new Process(0, 0, 24));
		processes.add(new Process(1, 2, 3));
		processes.add(new Process(2, 5, 4));
		processes.add(new Process(3, 1, 1));
		
		//total number of processes
		int numProcesses = processes.size();
		//number of current processes
		int numProc = processes.size();
		//total wait time
		int waitTime = 0;
		//total active CPU time
		int cpuTime = 0;
		//index to access processes later in the program
		int index = 0;
		// variable to hold the arrival time of a given process
		int a = 0;
		// variable to hold the burst time of a given process
		int b = 0;
		//setup output
		System.out.println("PID    |  CPU   |   WT   |   BT");
		System.out.println("-------------------------------");
		
		//while loop will keep going until the array list processes is empty		
		while(! processes.isEmpty())
		{
			
			//nest if statements and for loops to perform the preemptive switching
			//check if the array list is empty 
			if(numProc > 1)
			{
			//check if the array list is empty	
			if(processes.size() > 0)
			{
					//Set of variables that reset max and min each time we enter the if statement
					// B being burst time, P being process and A being arrival time
					int minB = 999;
					int minP = 0;
					int maxB = 0;
					int maxP = 0;
					int maxA = 0;
					int minA = 999;
					
					//for loop to go through the array to find the minimum arrival time and maximum arrival time
					for(int i=0; i < numProc; i++)
					{
						//get a process object from the array list 
						Process pro = processes.get(i);
						//set a to the arrival time of the current process
						a = pro.getArrival();
						//compare the arrival time to the max and min 
						if(a > maxA)
						{
							maxA = a;
							//maxP = i;
						}
						if(a < minA)
						{
							minA = a;
							//minP = i;
						}
								
					}
					
					//perform the preemption by finding the "max" process and "min" process
					for(int i=0; i < numProc; i++)
					{
						//get a process from the array list
						Process pro = processes.get(i);
						//set b to the burst time of the current process
						b = pro.getBurst();
						//set a to the burst time of the current process
						a = pro.getArrival();
						
						//compare the burst times for max
						if(b > maxB)
						{
							
							maxB = b;
							//set "maxP" to the process with the process with the highest burst time and lowest arrival time
							if(a == minA)
							{
								maxP = i;
							}
							
						}
						//compare the burst times for min
						if(b < minB)
						{
							minB = b;
							//set "minP" to the process with the process with the lowest burst time and highest arrival time
							if(a == maxA)
							{
								minP = i;
							}
							
						}
					}
			//get "Minimum" and "Maximum" processes		
			Process p = processes.get(maxP);
			Process pc = processes.get(minP);
			
			//while loop will keep going until the array list processes is empty
			if(!processes.isEmpty())
			{
				//Compare the burst times and arrival times of the min and max process
				//process the shortest job (should be pc
				if(p.getBurst() > pc.getBurst() && p.getBurst() > pc.getArrival() && p.getArrival() > pc.getArrival())
				{
					//get process object by removing the max process
					Process p2 = processes.remove(maxP);
					//calculate the new burst time of the "max process" to show that it had used up its time and was cut off
					int burst = p2.getBurst() - pc.getArrival();
					//re-add "max process" to the array list
					processes.add(maxP, new Process(p2.getIndex(), p2.getArrival(), burst));
				}
				// if else statement checks the total wait time to the arrival time of the current process
				if(pc.arrivalTime >= waitTime)
				{
					//if the processes arrival time is larger than the total wait time, add it to the total wait time
					//total wait time is calculated by adding the existing wait time and the current processes burst time
					waitTime += waitTime + pc.arrivalTime + pc.burstTime;
				}else if(p.arrivalTime <= waitTime)
				{
					// if the arrival time of the current process is less than the total wait time, subtract it from the total wait time
					//total wait time is calculated from the difference of arrival and wait time plus the current processes burst time
					waitTime += (waitTime - pc.arrivalTime) + pc.burstTime;
				}
				//remove the min process
				processes.remove(minP);
				//decrement numProc due to removal of process
				numProc = numProc -1;
				
			}
			//calculate total cpu usage time by adding both the min processes wait time and burst time
			cpuTime += pc.burstTime + pc.arrivalTime;
			//process output for each process
			System.out.println("P"+ p.index + "     |   " + cpuTime + "   |   " + waitTime + "   |   " + p.burstTime);
			}
		}else
		{
			//process the remaining processes
			//check to see if array list is empty 
			if(processes.size() > 0)
			{
				//if the current number of processes is less than the total number of processes reset the index
				if(numProc < numProcesses)
				{
					index = 0;
				}
				
			//get process from the array lis at index 
			Process p = processes.get(index);
			
			//check if the arraylist is empty
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
				//remove the processed process
				processes.remove(index);
			
			}
			//add the processes burst time to the total cpu time
			cpuTime += p.burstTime;
			//process output for each process
			System.out.println("P"+ p.index + "     |   " + cpuTime + "   |   " + waitTime + "  |   " + p.burstTime);
			//increment index
			index++;
			}
		}
	}
		//calculate average wait time
		double avgWaitTime = waitTime/numProcesses;
		//setup output
		System.out.println("-------------------------------");
		//display the average wait time
		System.out.println("Average wait time : " + avgWaitTime + " ms");
		//display the total cpuTime
		System.out.println("Total Cpu Usage time : " + cpuTime + " ms");
	}
	}

