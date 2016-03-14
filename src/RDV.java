import java.time.Duration;
import java.util.Date;
import java.util.List;

/**
 * Created by Mohammed Aouf ZOUAG on 04/03/2016.
 */
public class RDV {
    private Date mDate;
    private Duration mDuration;
    private List<Participant> mParticipants;
    private String mAddress;

    public RDV(Date date, Duration duration, List<Participant> participants, String address) {
        mDate = date;
        mDuration = duration;
        mParticipants = participants;
        mAddress = address;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
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
