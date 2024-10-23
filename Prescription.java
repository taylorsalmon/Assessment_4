import java.util.ArrayList;
import java.util.Date;

public class Prescription {
    public int prescID;
    public String firstName;
    public String lastName;
    public String address;
    public float sphere;
    public float axis;
    public float cylinder;
    public Date examinationDate;
    public String optometrist;
    private String[] remarkTypes = {"client", "optometrist"};
    private ArrayList<String> postRemarks = new ArrayList<>();

    public boolean addPrescription() {
        // condition 1: First Name and Last Name length checking
        if (firstName == null || firstName.length() < 4 || firstName.length() > 15) {
            System.out.println("First name must be between 4 and 15 characters long.");
            return false;
        }

        if (lastName == null || lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Last Name must be between 4 and 15 characters.");
            return false;
        }

        // Condition 2: Address length checks
        if (address == null || address.length() < 20) {
            System.out.println("Address must be at least 20 characters.");
            return false;
        }

        //Condition 3: Sphere, Cylinder, Axis ranges permameters
        if (sphere < -20.00f || sphere > 20.00f) {
            System.out.println("Sphere must be between -20.00 and +20.00.");
            return false;
        }

        if (cylinder < -4.00f || cylinder > 4.00f) {
            System.out.println("Cylinder must be between -4.00 and +4.00.");
            return false;
        }

        if (axis < 0.0f || axis > 180.0f) {
            System.out.println("Axis must be between 0 and 180.");
            return false;
        }

        //condition 4: Date in DD/MM/YY format
        if (examinationDate == null) {
            System.out.println("Examination date is required.");
            return false;
        }

        //ondition 5: Optometrist name length
        if (optometrist == null || optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Optometrist's name must be between 8 and 25 characters.");
            return false;
        }

        // If all conditions are met, write to presc.txt (simulated here with a print statement)
        System.out.println("Prescription added successfully.");

        return true;
    }

    public boolean addRemark(String remark, String category) {
        // Condition 1: Remark text word count and first character uppercase
        if (remark == null) {
            System.out.println("Remark cannot be null.");
            return false;
        }
    
        String[] words = remark.trim().split("\\s+");
        if (words.length < 6 || words.length > 20) {
            System.out.println("Remark must have between 6 and 20 words.");
            return false;
        }
    
        if (!Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("The first character of the remark must be uppercase.");
            return false;
        }
    
        // Condition 2: Category must be "client" or "optometrist"
        if (category == null || (!category.equalsIgnoreCase(remarkTypes[0]) && !category.equalsIgnoreCase(remarkTypes[1]))) {
            System.out.println("Category must be 'client' or 'optometrist'.");
            return false;
        }
    
        // Check if there are already 2 remarks
        if (postRemarks.size() >= 2) {
            System.out.println("Cannot add more than 2 remarks.");
            return false;
        }
    
        // Add the remark to the list
        postRemarks.add(remark);
    
        // Write to the appropriate file based on category
        String filename = category.equalsIgnoreCase("client") ? "review_client.txt" : "review_optometrist.txt";
    
        try {
            // Use basic FileWriter with append mode to write remarks
            java.io.FileWriter writer = new java.io.FileWriter(filename, true);
            writer.write(remark + "\n");
            writer.close();
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            return false;
        }
    
        return true;
    }

}