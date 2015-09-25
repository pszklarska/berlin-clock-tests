package pl.droidsonroids.berlinclock.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BerlinClockTest {

	private BerlinClock berlinClock;

	@Before
	public void setUp() throws Exception {
		berlinClock = new BerlinClock();
	}

	@Test
	public void testGetSeconds_PassEvenNumber() throws Exception {
		assertEquals("Y", berlinClock.getSeconds(2));
	}

	@Test
	public void testGetTopMinutes_PassOddNumber() throws Exception {
		assertEquals("YYROOOOOOOO", berlinClock.getTopMinutes(15));
	}

	@Test
	public void testConvertToBerlinTime_PassSomeHour() throws Exception {
		String[] expected = new String[]{"Y", "RROO", "RRRO", "YYRYYRYOOOO", "YOOO"};
		String[] actual = berlinClock.convertToBerlinTime("13:36:48");
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
		assertEquals(expected[2], actual[2]);
		assertEquals(expected[3], actual[3]);
		assertEquals(expected[4], actual[4]);
	}
}