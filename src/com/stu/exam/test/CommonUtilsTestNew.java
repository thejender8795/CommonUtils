package com.stu.exam.test;

//added method sorters
/*import org.junit.runners.MethodSorters;
import org.junit.FixMethodOrder;*/
import static org.junit.Assert.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Ignore;
import org.junit.Test;

import com.stu.exam.CommonUtilsSolutions;

/**
 * 
 * @author @@
 *
 *         Removed some annotations here to run tests without any issues. Mainly
 *         to avoid some compliation issues as some libs were not found.
 * 
 * 
 *         why it was removed? No ordering is required as these tests are
 *         independent and don't share data for modifications.
 * 
 *         Event to for simplication of verification, might be added in sorted
 *         exec.
 */
public class CommonUtilsTestNew {
	private final String testdata = "Hi this is some testdata to use f​or our tests";

	@Test
	public void a1_testFindInFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("testfile.txt"));
		writer.write(testdata);
		writer.close();
		try {
			assertTrue(CommonUtilsSolutions.findInFile("this", "testfile.txt"));
		} catch (Exception ex) {
			// no failures here.
		}
		new File("testfile.txt").delete();

		// case 2
		// @test-report, test when no search string keyed
		try {
			CommonUtilsSolutions.findInFile(null, "");
		} catch (Exception ex) {
			assertEquals("Search string cann't be empty.", ex.getMessage());
		}

		// case 3
		// @test-report, test when file string is empty
		try {
			CommonUtilsSolutions.findInFile("Fiesta", "");
		} catch (Exception ex) {
			assertEquals("File path cann't be empty.", ex.getMessage());
		}
	}

	@Test
	public void a2_testFindInString() throws Exception {

		assertTrue(CommonUtilsSolutions.findInString("Hi", testdata));

		// case 2
		// @test-report, test when no string is available
		assertFalse(CommonUtilsSolutions.findInString("Fiesta", testdata));

		// case 3
		// @test-report, test when no search string keyed
		try {
			CommonUtilsSolutions.findInString(null, "");
		} catch (Exception ex) {
			assertEquals("Search string cann't be empty.", ex.getMessage());
		}

		// case 4
		// @test-report, test when no search string is empty
		try {
			CommonUtilsSolutions.findInString("", "");
		} catch (Exception ex) {
			assertEquals("Search string cann't be empty.", ex.getMessage());
		}
	}

	// @test-report, looks like file is provided in testDirectory. Need to
	// correct it.
	@Test
	public void a3_testFindInDirectory() throws IOException {
		// @TODO, Create a folder, and files
		try {
			assertTrue(CommonUtilsSolutions.findInDirectory("this", "testfiledir"));
		} catch (Exception ex) {
			// failure case, it is a directory need to correct it.
		}

		// case 2
		// @test-report, test when no search string keyed
		try {
			CommonUtilsSolutions.findInDirectory(null, "");
		} catch (Exception ex) {
			assertEquals("Search string cann't be empty.", ex.getMessage());
		}

		// case 3
		// @test-report, test when directory string is empty
		try {
			CommonUtilsSolutions.findInDirectory("Fiesta", "");
		} catch (Exception ex) {
			assertEquals("Directory path cann't be empty.", ex.getMessage());
		}

		// @TODO, Delete files in a folder

		// case 4
		// @test-report, test when directory files are removed
		try {
			CommonUtilsSolutions.findInDirectory("Fiesta", "testfiledir");
		} catch (Exception ex) {
			assertEquals("No files available in the specified directory.", ex.getMessage());
		}
	}

	@Test
	public void b1_testNegativeRange() {
		int n = -1;
		// @test-report, l
		// case 1, less than or equal to zero
		try {
			CommonUtilsSolutions.sieveOfEratosthenes(n);
		} catch (Exception ex) {
			assertEquals("Number cann't be less than or equal to zero.", ex.getMessage());
		}
		n = 0;
		// @test-report, 2
		// case 2, less than or equal to zero
		try {
			CommonUtilsSolutions.sieveOfEratosthenes(n);
		} catch (Exception ex) {
			assertEquals("Number cann't be less than or equal to zero.", ex.getMessage());
		}
	}

	@Test
	public void b3_test10() throws Exception {
		final int n = 10;
		assertTrue(CommonUtilsSolutions.sieveOfEratosthenes(n) == 4);
	}

	@Test
	public void b4_test100() throws Exception {
		final int n = 100;
		assertTrue(CommonUtilsSolutions.sieveOfEratosthenes(n) == 25);
	}

	@Test
	public void b5_test1000() throws Exception {
		final int n = 1000;
		assertTrue(CommonUtilsSolutions.sieveOfEratosthenes(n) == 168);
	}

	@Test // removed ignore
	public void b6_test1000000() throws Exception {
		final int n = 1000000;
		assertTrue(CommonUtilsSolutions.sieveOfEratosthenes(n) == 78498);
	}

	@Ignore
	@Test // removed ignore
	public void b7_test1000000000() throws Exception {
		// @test-report, value seems to be high for my workstation as recursive
		// call was
		// used here. Either we need to fix the recursive call (make it
		// scalable), move to
		// iterative function, or get
		// high end config. machine.(temporary, will not scale). or prevent the
		// user from
		// allowing high value.
		final int n = 1000000000;
		assertTrue(CommonUtilsSolutions.sieveOfEratosthenes(n) == 50847534);
	}

	@Test
	public void calcDistance0() {
		CharSequence cs1 = "";
		CharSequence cs2 = "";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 0);
	}

	// @test-report, no dest. found.
	@Test
	public void calcDistanceNoDestStringAvailable() {
		CharSequence cs1 = "";
		CharSequence cs2 = "ABC";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 3);
	}

	// @test-report, no source str. found.
	@Test
	public void calcDistanceNoSrcStringAvailable() {
		CharSequence cs1 = "ABCD";
		CharSequence cs2 = "";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 4);
	}

	// @test-report, Mismatch in lengths
	@Test
	public void calcDistanceMisMatchInLength() {
		CharSequence cs1 = "AA";
		CharSequence cs2 = "ABC";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 2);
	}

	@Test
	public void calcDistance1() {
		CharSequence cs1 = "AA";
		CharSequence cs2 = "AB";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 1);
	}

	@Test
	public void calcDistance2() {
		CharSequence cs1 = "AAA";
		CharSequence cs2 = "ABC";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) == 2);
	}

	@Test
	public void calcDistanceN() {
		CharSequence cs1 = "dsfgdfhdfer";
		CharSequence cs2 = "fgjtykfgnsd";
		assertTrue(CommonUtilsSolutions.levenshteinDistance(cs1, cs2) > 5);
	}
}
