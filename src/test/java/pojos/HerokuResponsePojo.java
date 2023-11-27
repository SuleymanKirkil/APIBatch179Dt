package pojos;

public class HerokuResponsePojo {
    private Integer bookingid;
    private HerokuPojo booking;

    public HerokuResponsePojo() {
    }

    public HerokuResponsePojo(Integer bookingid, HerokuPojo booking) {
        this.bookingid = bookingid;
        this.booking = booking;
    }

    public Integer getBookingid() {
        return bookingid;
    }

    public void setBookingid(Integer bookingid) {
        this.bookingid = bookingid;
    }

    public HerokuPojo getBooking() {
        return booking;
    }

    public void setBooking(HerokuPojo booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "HerokuResponsePojo{" +
                "bookingid=" + bookingid +
                ", booking=" + booking +
                '}';
    }
}
