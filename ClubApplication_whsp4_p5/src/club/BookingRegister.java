package club;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.time.LocalDateTime;;

public class BookingRegister {
    private HashMap<Facility, ArrayList<Booking>> bookings;

    public BookingRegister() {
        bookings = new HashMap<Facility, ArrayList<Booking>> ();
    }

    public void addBooking (Member member, Facility facility, LocalDateTime startDate, LocalDateTime endDate)
				throws BadBookingException {
        Booking b = new Booking (member, facility, startDate, endDate);
        ArrayList<Booking> bookingList = bookings.get (facility);
        if (bookingList == null) {
        	bookingList = new ArrayList<Booking> ();
            bookings.put (facility, bookingList);
        } else {
            Iterator<Booking> i = bookingList.iterator ();
            while (i.hasNext ()) {
                Booking other = i.next();
                if (b.overlaps(other)) {
                    throw new BadBookingException ("New booking overlaps with existing one");
                }
            }
        }
        bookingList.add (b);
    }

    public void removeBooking (Booking booking) {
    	ArrayList<Booking> bookingList = bookings.get (booking.getFacility());
        if (bookingList != null) {
        	bookingList.remove (booking);
        }
    }

    public ArrayList<Booking> getBookings (Facility facility,
            LocalDateTime startDate, LocalDateTime endDate) {
    	ArrayList<Booking> selected = new ArrayList<Booking> ();
        ArrayList<Booking> bookingList = bookings.get (facility);
        if (bookingList != null) {
            Iterator<Booking> i = bookingList.iterator ();
            while (i.hasNext ()) {
                Booking b = i.next();
                boolean earlier = startDate.isAfter (b.getEndDate());
                boolean later = endDate.isBefore (b.getStartDate());
                if (!(earlier || later)) {
                	selected.add (b);
                }
            }
        }
        return selected;
    }

}
