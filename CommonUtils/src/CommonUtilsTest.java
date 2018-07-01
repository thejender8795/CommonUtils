import static org.junit.Assert.*;
//added method sorters
import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;


//method for sorting based on tasks
@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CommonUtilsTest 
{
	private final String testdata = "Hi this is some testdata to use f​or our tests";
	
	@Test
	public void a1_testFindInFile() throws IOException 
	{
		BufferedWriter writer = new BufferedWriter( new FileWriter("testfile.txt"));
		writer.write(testdata);
		writer.close();
		assertTrue(CommonUtils.findInFile("this", "testfile.txt"));
	
		new File("testfile.txt").delete();
	}
	
	@Test
	public void a2_testFindInString() throws IOException 
	{		
		assertTrue(CommonUtils.findInString("Hi", testdata));
	}
	
	
	
	@Test
	public void a3_testFindInDirectory() throws IOException 
	{
		BufferedWriter writer = new BufferedWriter( new FileWriter("testfile.txt"));
		writer.write(testdata);
		writer.close();
		assertTrue(CommonUtils.findInFile("this", "testfile.txt"));
	
		new File("testfile.txt").delete();
	}
	
	@Test
	public void b1_testNegative()  
	{
		final int n = -1;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 0);
	}
	
	
	@Test
	public void b2_testZero() // modified from teszero to testZero 
	{
		final int n = 0;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 0);
	}
	
	@Test
	public void b3_test10()  
	{
		final int n = 10;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 4);
	}
	
	@Test
	public void b4_test100()  
	{
		final int n = 100;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 25);
	}
	
	
	@Test
	public void b5_test1000()  
	{
		final int n = 1000;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 168);
	}
	
	
	@Test //removed ignore
	public void b6_test1000000()  
	{
		final int n = 1000000;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 78498);
	}
	
	@Ignore 
	@Test  //removed ignore
	public void b7_test1000000000()  
	{
		final int n = 1000000000;
		assertTrue(CommonUtils.sieveOfEratosthenes(n) == 50847534);
	}
	
	
	
	@Test
	public void calcDistance0()
	{
		CharSequence cs1 = "";
		CharSequence cs2 = "";
		
		assertTrue(CommonUtils.levenshteinDistance(cs1, cs2) == 0);
	}
	
	@Test
	public void calcDistance1()
	{
		CharSequence cs1 = "AA";
		CharSequence cs2 = "AB";
		
		assertTrue(CommonUtils.levenshteinDistance(cs1, cs2) == 1);
	}
	
	@Test
	public void calcDistance2()
	{
		CharSequence cs1 = "AAA";
		CharSequence cs2 = "ABC";
		
		assertTrue(CommonUtils.levenshteinDistance(cs1, cs2) == 2);
	}
	
	@Test
	public void calcDistanceN()
	{
		CharSequence cs1 = "dsfgdfhdfer";
		CharSequence cs2 = "fgjtykfgnsd";
		// changed the value by putting == instead of > 
		assertTrue(CommonUtils.levenshteinDistance(cs1, cs2) == 11);
	}
}
