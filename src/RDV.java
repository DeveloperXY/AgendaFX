import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class RDV {
    private LocalDateTime mDate;
    private Duration mDuration;
    private List<Participant> mParticipants;
    private String mAddress;

    public RDV(LocalDateTime date, Duration duration, List<Participant> participants, String address) {
        mDate = date;
        mDuration = duration;
        mParticipants = participants;
        mAddress = address;
    }

    public LocalDateTime getDate() {
        return mDate;
    }

    public void setDate(LocalDateTime date) {
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
}
