import java.util.*;

public class ContactManager {

    public static void main(String[] args) {

        HashMap<String, Contact> contacts = new HashMap<>();

        //Step 4: Add Your Contacts
        contacts.put("Ada Lovelace",
                new Contact("Ada Lovelace", "+1 617 555 0101"));
        contacts.put("Alan Turing",
                new Contact("Alan Turing", "+44 20 7946 0958"));
        contacts.put("Grace Hopper",
                new Contact("Grace Hopper", "+1 212 555 0199"));
        contacts.put("Katherine Johnson",
                new Contact("Katherine Johnson", "+1 757 555 0110"));
        contacts.put("Tim Berners-Lee",
                new Contact("Tim Berners-Lee", "+44 1223 555 0200"));

        //Step 5: Look up a Contact by Name 
        Contact found = contacts.get("Ada Lovelace");
        if (found == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found contact: " + found);
        }

        // Test with a name that does not exist
        Contact missing = contacts.get("Nonexistent Person");
        if (missing == null) {
            System.out.println("Contact not found.");
        } else {
            System.out.println("Found contact: " + missing);
        }

        // Step 6: print sorted list
        ArrayList<Contact> sorted = new ArrayList<>(contacts.values());
        sorted.sort((a, b) -> a.getName().compareTo(b.getName()));

        System.out.println("=== All Contacts ===");
        for (Contact c : sorted) {
            System.out.println(c);
        }
    }
}
