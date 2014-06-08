package dataStruct;

import logout.LogOutStream;

/**
* 一些简单算法的实现集合
* @author jack
* 
*/

public class ClassicalCollection{
	
	static int fibonacci_called;
	
	/**
	* Fibonacci数列（即1，1，2，3，5，8……）的第n项递归实现，
	* 第n项的递推式为F(n)=F(n-1)+F(n-2) (n>2)
	* 
	* @param Fibonacci数列的第n项
	* @return Fibonacci数列第n项的值；如果n<1，则返回0；
	* 
	*/
	public static int fibonacci(int n)
	{
		
		//System.out.println(LogOutStream.INFO_LOG + "going to claculate fibonacci(" + n + ")...");
		fibonacci_called ++;
		if(n<1)
		{
			return 0;
		}
		if(n<3)
		{
			return 1;
		}
		int mth_result = fibonacci(n-1) + fibonacci(n-2);
		
		//System.out.println("[Process]fibonacci(" + n + ")=" + mth_result);
		return mth_result;
	}
	
	/**
	* Fibonacci数列（即1，1，2，3，5，8……）的前n项递归实现，
	* 第n项的递推式为F(n)=F(n-1)+F(n-2) (n>2)
	* 
	* @param n，表示Fibonacci数列的前n项
	* @return Fibonacci数列的前n项；如果n<1，则返回null；
	* @see fibonacci(int)
	*/
	
	public static int[] fibonacci_n(int n)
	{
		
		
		if(n < 1)
		{
			return null;
		}
		
		int[] mth_result = new int[n];
		
		//n-1的原因时此时第二个参数的意义是数组下标
		fibonacci_I_(mth_result, n-1);
		
		// /*
		// 这种方法过于“血腥”，每递归一次，需要新建一个数组和拷贝一次数组
		// int i = 0;
		// for(int cell : fibonacci_n(n-1))
		// {
			// mth_result[i] = cell;
			// i++;
		// }
		// */		
		return mth_result;
	}
	
	static void fibonacci_I_(int[] result_N,int I)
	{
		if(I < 2)
		{
			result_N[I] = 1;
			result_N[I-1] = 1;
			return;
		}
		fibonacci_I_(result_N,I-1);
		
		result_N[I] = result_N[I-1] + result_N[I-2];
	}
	
	
	public static long fibonacciL(int n)
	{		
		fibonacci_called ++;
		if(n<1)
		{
			return 0L;
		}
		if(n<3)
		{
			return 1L;
		}
		long mth_result = fibonacci(n-1) + fibonacci(n-2);
		
		return mth_result;
	}
	
		
	public static long[] fibonacci_nL(int n)
	{
		
		
		if(n < 1)
		{
			return null;
		}
		
		long[] mth_result = new long[n];
		
		fibonacci_I_(mth_result, n-1);
		
		return mth_result;
	}
	
	static void fibonacci_I_(long[] result_N,int I)
	{
		if(I < 2)
		{
			result_N[I] = 1L;
			result_N[I-1] = 1L;
			return;
		}
		fibonacci_I_(result_N,I-1);
		
		result_N[I] = result_N[I-1] + result_N[I-2];
	}
}