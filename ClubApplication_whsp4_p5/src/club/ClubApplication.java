package club;

public class ClubApplication {

   public static void main(String args[]) {
      Club club = new Club();
      
      // Members
      club.addMember("Einstein", "Albert", null);
      club.addMember("Picasso", "Pablo", "Ruiz");
      club.addMember("Webber", "Andrew", "Lloyd");
      club.addMember("Einstein", "Hans", "Albert");
      club.addMember("Baggio", "Roberto", null);
      club.addMember("Einstein", "Lieserl", null);
      club.addMember("Raffles", "Stamford", null);
      club.addMember("Einstein", "Maja", null);
      
      System.out.println("Showing members sorted");
      club.showMembersSorted();
      System.out.println();
      
      System.out.println("Showing members normally");
      club.showMembers();
      System.out.println();
      

      // Facility
      club.addFacility("Lab", "Einstein's Experiment Area");
      club.addFacility("Studio", "Picasso's Work Place");
      club.addFacility("Room1", "Conference Room on Level 2");
      club.addFacility("Room2", "Meeting Room on Level 3");

      System.out.println("Showing facilities sorted by name");
      club.showFacilitiesSortedByName();
      System.out.println();

      System.out.println("Showing facilities sorted by description");
      club.showFacilitiesSortedByDescription();
      System.out.println();

   }

}
