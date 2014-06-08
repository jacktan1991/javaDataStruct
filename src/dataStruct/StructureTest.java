package dataStruct;

import static constantManager.ConstantManager.STRUCTURETEST.*;
import static dataStruct.ClassicalCollection.*;

import logout.LogOutStream;

public class StructureTest{
	
	public static void main(String[] args) throws Exception
	{
		
		
		try
		{
			//System.setOut(new LogOutStream(LOGFILE));
			LogOutStream.getInstance(LOGFILE);
		}catch(java.io.FileNotFoundException fioe)
		{
			throw fioe;
		}
		//testFibonacci_I_();
		
		//testFibonacci_n();
		compareTest();
	}
	
	static void compareTest()
	{
				
		int N = 30;
		long f_n = 0L;
		long zero_time = System.currentTimeMillis();
		
		f_n = fibonacci_nL(N)[N-1];
		System.out.println("when N = 30 after fibonacc_() time past:" + (System.currentTimeMillis() - zero_time) + "\nand f_n=" + f_n);
		
		f_n = fibonacciL(N);
		System.out.println("when N = 30 after fibonacci() time past:" + (System.currentTimeMillis() - zero_time) + "\nand f_n=" + f_n);
	}
	
	static void testFibonacci()
	{		
		System.out.println(LogOutStream.PROCESS_LOG + "Going to caculate the value of " + FIBONACCI_N + " in fibonacci...");
		int f_value = fibonacci(FIBONACCI_N);
		System.out.println(LogOutStream.PROCESS_LOG + "fibonacci(" + FIBONACCI_N + ")=" + f_value);
		System.out.println(LogOutStream.PROCESS_LOG + "and fibonacci method has been called " + fibonacci_called + "times");
	}
	
	static void testFibonacci_I_()
	{
		System.out.println(LogOutStream.INFO_LOG + "testFibonacci_I_() begin...");
		
		int[] result_N = new int[7];
		
		StringBuilder for_process = new StringBuilder("int[] result_N inited as\n(");
		for(int cell : result_N)
		{
			for_process.append(cell + ",");
		}
		for_process.append(")");
		System.out.println(LogOutStream.PROCESS_LOG + for_process);
		
		System.out.println(LogOutStream.PROCESS_LOG + "fibonacci_I_() begin... ");
		fibonacci_I_(result_N, result_N.length-4);
		
		for_process.delete(0, for_process.length());
		for_process.append("after fibonacci_I_()ed,result_N as\n(");
		for(int cell : result_N)
		{
			for_process.append(cell + ",");
		}
		for_process.append(")");
		
		System.out.println(LogOutStream.PROCESS_LOG + for_process);
		
		fibonacci_I_(result_N, result_N.length-2);
		fibonacci_I_(result_N, result_N.length-2);
		
		for_process.delete(0, for_process.length());
		for_process.append("after fibonacci_I_()ed,result_N as\n(");
		for(int cell : result_N)
		{
			for_process.append(cell + ",");
		}
		for_process.append(")");
		
		System.out.println(LogOutStream.PROCESS_LOG + for_process);
		
		fibonacci_I_(result_N, result_N.length-1);
		
		for_process.delete(0, for_process.length());
		for_process.append("after fibonacci_I_()ed,result_N as\n(");
		for(int cell : result_N)
		{
			for_process.append(cell + ",");
		}
		for_process.append(")");
		
		System.out.println(LogOutStream.PROCESS_LOG + for_process);
	}
	
	static void testFibonacci_n()
	{
		int[] result_N = fibonacci_n(7);
		
		StringBuilder for_process = new StringBuilder("after fibonacci_n()ed,result_N as\n(");
		
		for(int cell : result_N)
		{
			for_process.append(cell + ",");
		}
		for_process.append(")");
		System.out.println(LogOutStream.PROCESS_LOG + for_process);
	}
}