package pl.droidsonroids.berlinclock.ui;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import pl.droidsonroids.berlinclock.model.BerlinClock;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterImplTest {

	private MainPresenterImpl mainPresenter;
	@Mock BerlinClock berlinClock;
	@Mock MainView mainView;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mainPresenter = new MainPresenterImpl(berlinClock);
		mainPresenter.setView(mainView);
	}

	@Test
	public void testUpdateTime_PassProperValue() throws Exception {
		when(berlinClock.convertToBerlinTime("13:36:48")).thenReturn(new String[]{"Y", "RROO", "RRRO", "YYRYYRYOOOO", "YOOO"});
		mainPresenter.updateTime(new Date(946730208000l));
		verify(mainView).showSecondsOn();
		verify(mainView).showHourUpOn(0);
		verify(mainView).showHourUpOn(1);
		verify(mainView).showHourUpOff(2);
		verify(mainView).showHourUpOff(3);
		verify(mainView).showMinutesUpYellowOn(0);
		verify(mainView).showMinutesUpYellowOn(1);
		verify(mainView).showMinutesUpYRedOn(2);
		verify(mainView).showMinutesUpYellowOn(3);
		verify(mainView).showMinutesUpYellowOn(4);
		verify(mainView).showMinutesUpYRedOn(5);
		verify(mainView).showMinutesUpYellowOn(6);
		verify(mainView).showMinutesUpOff(7);
		verify(mainView).showMinutesUpOff(8);
		verify(mainView).showMinutesUpOff(9);
		verify(mainView).showMinutesUpOff(10);
	}

	@Test
	public void testSetCurrentTime_PassProperValue() throws Exception {
		when(berlinClock.convertToBerlinTime("13:36:48")).thenReturn(new String[]{"Y", "RROO", "RRRO", "YYRYYRYOOOO", "YOOO"});
		mainPresenter.setCurrentTime("13:36:48");
		verify(mainView).showSecondsOn();
	}

	@Test
	public void testManageSeconds_PassEvenValue() throws Exception {
		mainPresenter.manageSeconds("Y");
		verify(mainView).showSecondsOn();
	}

	@Test
	public void testManageUpHours_PassRROO() throws Exception {
		mainPresenter.manageUpHours("RROO");
		verify(mainView).showHourUpOn(0);
		verify(mainView).showHourUpOn(1);
		verify(mainView).showHourUpOff(2);
		verify(mainView).showHourUpOff(3);
		Mockito.verifyNoMoreInteractions(mainView);
	}

	@Test
	public void testManageUpMinutes_PassYYRYYRYOOOO() throws Exception {
		mainPresenter.manageUpMinutes("YYRYYRYOOOO");
		verify(mainView).showMinutesUpYellowOn(0);
		verify(mainView).showMinutesUpYellowOn(1);
		verify(mainView).showMinutesUpYRedOn(2);
		verify(mainView).showMinutesUpYellowOn(3);
		verify(mainView).showMinutesUpYellowOn(4);
		verify(mainView).showMinutesUpYRedOn(5);
		verify(mainView).showMinutesUpYellowOn(6);
		verify(mainView).showMinutesUpOff(7);
		verify(mainView).showMinutesUpOff(8);
		verify(mainView).showMinutesUpOff(9);
		verify(mainView).showMinutesUpOff(10);
		Mockito.verifyNoMoreInteractions(mainView);
	}

}