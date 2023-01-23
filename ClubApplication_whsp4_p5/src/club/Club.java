package club;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Club {

	private int currentNumber = 1;
	private ArrayList<Member> memberList = new ArrayList<>();
	private HashMap<String, Facility> nameToFacilityMap = new HashMap<>();

	public Member addMember(String surname, String firstName, String secondName) {
		Member m = new Member(surname, firstName, secondName, currentNumber);
		memberList.add(m);
		currentNumber++;

		return m;
	}

	public void showMembers() {
		for (Member member : memberList) {
			member.show();
		}
	}
	
	public void showMembersSorted() {
		// We should not directly sort the memberList if we want to reserve the current element positions
		// Instead, create and sort the copy
		List<Member> listToSort = new ArrayList<Member>(memberList);
		
		// Using Collections.sort(List<T>, Comparator)
		// Here, creating a Comparator object using Comparator.comparing()...
		// Of course, alternatively, can create a Comparator anonymous class, as in
		// showFacilitiesSortedByName() method
		Collections.sort(listToSort, Comparator.comparing(Member::getSurname)
												.thenComparing(Member::getFirstName)
												.thenComparing(Member::getSecondName));
		
		for (Member member : listToSort) {
			member.show();
		}
	}

	public Member findMember(int memberNumber) {
		for (Member member : memberList) {
			if (member.getMemberNumber() == memberNumber) {
				return member;
			}
		}

		return null;
	}

	public void removeMember(int memberNumber) {
		Member memberToRemove = findMember(memberNumber);

		if (memberToRemove != null) {
			memberList.remove(memberToRemove);
		}
	}

	public Facility getFacility(String name) {
		return nameToFacilityMap.get(name);
	}

	public Facility addFacility(String name, String description) {
		Facility facility = new Facility(name, description);

		nameToFacilityMap.put(name, facility);

		return facility;
	}

	public void removeFacility(String name) {
		nameToFacilityMap.remove(name);
	}

	public Collection<Facility> getFacilities() {
		return nameToFacilityMap.values();
	}

	public void showFacilities() {
		Collection<Facility> facilities = getFacilities();

		for (Facility facility : facilities) {
			facility.show();
		}
	}

	public void showFacilitiesSortedByName() {
		// Because Colllections.sort(List<T>) needs a list, we create a 
		// list from the Collection<Facility> return from getFacilities() 
		List<Facility> facilities = new ArrayList<>(getFacilities());
		
		// Here using Comparator to compare to Facility objects
		// We can use either an anonymous class
		Collections.sort(facilities, new Comparator<Facility>() {
			@Override
			public int compare(Facility facility1, Facility facility2) {
				return facility1.getName().compareTo(facility2.getName());
			}
		});
		
		// OR a lambda
//		Collections.sort(facilities, (facility1, facility2) -> facility1.getName().compareTo(facility2.getName()));
		
		for (Facility facility : facilities) {
			facility.show();
		}
	}
	
	
	public void showFacilitiesSortedByDescription() {
		// Because Colllections.sort(List<T>) needs a list, we create a 
		// list from the Collection<Facility> return from getFacilities() 
		List<Facility> facilities = new ArrayList<>(getFacilities());
		
		Collections.sort(facilities, (facility1, facility2) -> facility1.getDescription().compareTo(facility2.getDescription()));

		for (Facility facility : facilities) {
			facility.show();
		}
	}

	public void show() {
		System.out.println("Current Members:");
		showMembers();
		System.out.println();
		System.out.println("Facilities:");
		showFacilities();
	}

	private BookingRegister bookingRegister = new BookingRegister();

	public void addBooking(int memberNumber, String facilityName, LocalDateTime startDate, LocalDateTime endDate)
			throws BadBookingException {
		Member member = findMember(memberNumber);
		Facility facility = getFacility(facilityName);

		bookingRegister.addBooking(member, facility, startDate, endDate);
	}

	public ArrayList<Booking> getBookings(String facilityName, LocalDateTime startDate, LocalDateTime endDate) {
		Facility facility = getFacility(facilityName);
		return bookingRegister.getBookings(facility, startDate, endDate);
	}

	public void showBookings(String facilityName, LocalDateTime startDate, LocalDateTime endDate) {
		ArrayList<Booking> bookingList = getBookings(facilityName, startDate, endDate);

		for (Booking booking : bookingList) {
			booking.show();
		}
	}
}
