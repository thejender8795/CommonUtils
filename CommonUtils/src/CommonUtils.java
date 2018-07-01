import java.io.File;
import java.io.IOException;

import org.bitbucket.kienerj.io.OptimizedRandomAccessFile;
/**
 * Class that implements different Find functions
 *
 */
public class CommonUtils 
{

	/**
	 * Function that will look for a string in a file
	 * @param needle the string to look for
	 * @param haystack the file we want to search in
	 * @return if it found the string we are looking for
	 * @throws IOException
	 */
	public static boolean findInFile(String needle, String haystack) throws IOException
	{
		OptimizedRandomAccessFile raf = new OptimizedRandomAccessFile(haystack, "rw");
		long offset = raf.getFilePointer();
		int nl = needle.length();
		byte tbuf[] = new byte[nl];
		boolean returnvalue = false;
		while(true)
		{
			raf.read(tbuf);
			String cmp = new String(tbuf, "UTF-8");
			if(needle.equals(cmp))
			{
				returnvalue = true;
				break;
			}
			offset++;
			raf.seek(offset);
		}
		raf.close();
		System.out.println(""+raf.read(tbuf));
		System.out.println(""+raf.getFilePointer());
		return returnvalue;
		
	}

	/**
	 * searches for a string in another string
	 * @param needle the string we want to look for
	 * @param haystack the string we want to search in
	 * @return If we found it in the string
	 */
	public static boolean findInString(String needle, String haystack)
	{
		
		int offset = 0;
		int nl = needle.length();
		boolean returnvalue = false;
		while(true)
		{	
			String cmp = haystack.substring(offset, nl);
			if(needle.equals(cmp))
			{
				returnvalue = true;
				break;
			}			
			offset++;
		}
		System.out.println(""+ nl);
		System.out.println(haystack.contains("Hi"));
		System.out.println(haystack.contains("madara"));
		return returnvalue;
	}

	/**
	 * searches for a string in a directory
	 * @param needle the string we want to look for
	 * @param haystack the directory we want to search in
	 * @return If we found it in the string
	 * @throws IOException 
	 */
	public static boolean findInDirectory(String needle, String haystackdirectory) throws IOException
	{
		File dir = new File(haystackdirectory); 	
        File[] files = dir.listFiles();
    
        boolean returnvalue = false;
        for (File file : files) 
        {     
            if (file.isFile()) 
            {
            	if(findInFile(needle, file.getAbsolutePath()))
            	{
            		returnvalue = true;
            		return returnvalue;
            	}
            }
         
            if(file.isDirectory()) {
            	returnvalue = findInDirectory(needle, file.getAbsolutePath());
            }
        }
      
		return true;		
	}

	/**
	 * Computes the number of primes less than or equal to n using the Sieve of Eratosthenes algorithm
	 * From: https://introcs.cs.princeton.edu/java/14array/PrimeSieve.java.html
	 * @param n the upper limit to search for primes
	 * @return number of primes less than or equal to n
	 */
	public static int sieveOfEratosthenes(int n)
    {
		// initially assume all integers are prime
        boolean[] isPrime = new boolean[n+1];
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= n using Sieve of Eratosthenes
        for (int factor = 2; factor*factor <= n; factor++) {

            // if factor is prime, then mark multiples of factor as nonprime
            // suffices to consider mutiples factor, factor+1, ...,  n/factor
            if (isPrime[factor]) {
                for (int j = factor; factor*j <= n; j++) {
                    isPrime[factor*j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) primes++;
        }

        System.out.println("The number of primes <= " + n + " is " + primes); 
		return primes;
		
        //return primes;
    }
	
	/**
	 * Calculates the Levenshtein distance between two char sequences
	 * @param lhs first char sequence
	 * @param rhs second char sequence
	 * @return Levenshtein distance
	 */
	public static int levenshteinDistance (CharSequence lhs, CharSequence rhs) {                          
	    int len0 = lhs.length() + 1;                                                     
	    int len1 = rhs.length() + 1;                                                     
	                                                                                    
	    // the array of distances                                                       
	    int[] cost = new int[len0];                                                     
	    int[] newcost = new int[len0];                                                  
	                                                                                    
	    // initial cost of skipping prefix in String s0                                 
	    for (int i = 0; i < len0; i++) cost[i] = i;                                     
	                                                                                    
	    // dynamically computing the array of distances                                  
	                                                                                    
	    // transformation cost for each letter in s1                                    
	    for (int j = 1; j < len1; j++) {                                                
	        // initial cost of skipping prefix in String s1                             
	        newcost[0] = j;                                                             
	                                                                                    
	        // transformation cost for each letter in s0                                
	        for(int i = 1; i < len0; i++) {                                             
	            // matching current letters in both strings                             
	            int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;             
	                                                                                    
	            // computing cost for each transformation                               
	            int cost_replace = cost[i - 1] + match;                                 
	            int cost_insert  = cost[i] + 1;                                         
	            int cost_delete  = newcost[i - 1] + 1;                                  
	                                                                                    
	            // keep minimum cost                                                    
	            newcost[i] = Math.min(Math.min(cost_insert, cost_delete), cost_replace);
	        }                                                                           
	                                                                                    
	        // swap cost/newcost arrays                                                 
	        int[] swap = cost; cost = newcost; newcost = swap;                          
	    }                                                                               
	                                                                                    
	    // the distance is the cost for transforming all letters in both strings        
	    System.out.println(cost[len0-1]);
	    return cost[len0 - 1];                                                          
	}
}
