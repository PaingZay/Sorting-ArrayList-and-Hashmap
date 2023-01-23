package club;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking {
	public static final String DATE_FORMAT = "d-MMM-yyyy";

	public static final String TIME_FORMAT = "d-MMM-yyyy H:mm";

	private Member member;

	private Facility facility;

	private LocalDateTime startDate;

	private LocalDateTime endDate;

	public Booking(Member member, Facility facility, LocalDateTime startDate,
	        LocalDateTime endDate) throws BadBookingException {
		String error = null;
		if (member == null)
			error = "No member specified";
		else if (facility == null)
			error = "No facility specified";
		else if ((startDate == null) || (endDate == null))
			error = "Missing date";
		else if (startDate.isAfter(endDate))
			error = "Start date is later than end date";
		if (error != null)
			throw new BadBookingException (error);
		this.member = member;
		this.facility = facility;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public Member getMember() {
		return member;
	}

	public Facility getFacility() {
		return facility;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public boolean overlaps(Booking other) {
		boolean status = true;
		if (this.facility != other.getFacility()) {
			status = false;
		} else if (startDate.isAfter(other.getEndDate())) {
			status = false;
		} else if (other.getStartDate().isAfter(endDate)) {
			status = false;
		}
		return (status);
	}

	private static DateTimeFormatter tf = DateTimeFormatter.ofPattern(TIME_FORMAT);

	public String toString() {
		return "Booking: " + facility.getName() + " (" + member + "): "
				+ startDate.format(tf) + " to " + endDate.format(tf);
	}

	public void show() {
		System.out.println(this);
	}
}
