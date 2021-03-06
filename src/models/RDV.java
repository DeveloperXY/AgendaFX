package models;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class RDV {
    private LocalDate mDate;
    private Duration mDuration;
    private List<Participant> mParticipants;
    private String mAddress;

    public RDV(LocalDate date, Duration duration, List<Participant> participants, String address) {
        mDate = date;
        mDuration = duration;
        mParticipants = participants;
        mAddress = address;
    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setDate(LocalDate date) {
        mDate = date;
    }

    public Duration getDuration() {
        return mDuration;
    }

    public void setDuration(Duration duration) {
        mDuration = duration;
    }

    public List<Participant> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(List<Participant> participants) {
        mParticipants = participants;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public boolean isParticipant(String participantName) {
        return mParticipants.stream()
                .map(Participant::getFirstname)
                .anyMatch(name -> name.equals(participantName));
    }

    @Override
    public String toString() {
        return "RDV{" +
                "mDate=" + mDate +
                ", mDuration=" + mDuration.toMinutes() +
                ", mParticipants=" + mParticipants +
                ", mAddress='" + mAddress + '\'' +
                '}';
    }
}
