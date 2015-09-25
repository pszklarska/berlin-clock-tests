package pl.droidsonroids.berlinclock.ui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import pl.droidsonroids.berlinclock.model.BerlinClock;

public class MainPresenterImpl implements MainPresenter {

    private MainView mainView = new MainView.EmptyMainView();

    private final BerlinClock berlinClock;

    public MainPresenterImpl(BerlinClock berlinClock) {
        this.berlinClock = berlinClock;
    }

    @Override
    public void setView(MainView view) {
        mainView = view;
        startClock();
    }

    @Override
    public void clearView() {
        mainView = new MainView.EmptyMainView();
    }

    public void startClock() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateTime(new Date());
            }
        }, 1000, 1000);
    }

    void updateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String currentTime = sdf.format(date);
        setCurrentTime(currentTime);
    }

    void setCurrentTime(String currentTime) {
        String[] berlinTime = berlinClock.convertToBerlinTime(currentTime);

        manageSeconds(berlinTime[0]);
        manageUpHours(berlinTime[1]);
        manageBottomHours(berlinTime[2]);
        manageUpMinutes(berlinTime[3]);
        manageBottomMinutes(berlinTime[4]);
    }

    void manageSeconds(String time) {
        if(time.equals("Y")) {
            mainView.showSecondsOn();
        }else {
            mainView.showSecondsOff();
        }
    }

    void manageUpHours(String time) {
        for(int i = 0; i < time.length(); i++) {
            if(time.substring(i, i+1).equals("R")) {
                mainView.showHourUpOn(i);
            }else {
                mainView.showHourUpOff(i);
            }
        }
    }

    private void manageBottomHours(String time) {
        for(int i = 0; i < time.length(); i++) {
            if(time.substring(i, i+1).equals("R")) {
                mainView.showHourBottomOn(i);
            }else {
                mainView.showHourBottomOff(i);
            }
        }
    }

    void manageUpMinutes(String time) {
        for(int i = 0; i < time.length(); i++) {
            if(time.substring(i, i+1).equals("Y")) {
                mainView.showMinutesUpYellowOn(i);
            }else if(time.substring(i, i+1).equals("R")) {
                mainView.showMinutesUpYRedOn(i);
            }else {
                mainView.showMinutesUpOff(i);
            }
        }
    }

    private void manageBottomMinutes(String time) {
        for(int i = 0; i < time.length(); i++) {
            if(time.substring(i, i+1).equals("Y")) {
                mainView.showMinutesBottomOn(i);
            }else {
                mainView.showMinutesBottomOff(i);
            }
        }
    }
}
