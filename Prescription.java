
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

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
        // heck first name length
        if (firstName == null || firstName.length() < 4 || firstName.length() > 15) {
            System.out.println("First name must be between 4 and 15 characters long.");
            return false;
        }

        //check last name length
        if (lastName == null || lastName.length() < 4 || lastName.length() > 15) {
            System.out.println("Last name must be between 4 and 15 characters.");
            return false;
        }

        // check address length
        if (address == null || address.length() < 20) {
            System.out.println("Address must be at least 20 characters.");
            return false;
        }

        //check sphere range
        if (sphere < -20.00f || sphere > 20.00f) {
            System.out.println("Sphere must be between -20.00 and +20.00.");
            return false;
        }

        // check cylinder range
        if (cylinder < -4.00f || cylinder > 4.00f) {
            System.out.println("Cylinder must be between -4.00 and +4.00.");
            return false;
        }

        // heck axis range
        if (axis < 0.0f || axis > 180.0f) {
            System.out.println("Axis must be between 0 and 180.");
            return false;
        }

        // check examination date
        if (examinationDate == null) {
            System.out.println("Examination date is required.");
            return false;
        }

        // check optometrist name length
        if (optometrist == null || optometrist.length() < 8 || optometrist.length() > 25) {
            System.out.println("Optometrist's name must be between 8 and 25 characters.");
            return false;
        }

        // write prescription to file
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
            String formattedDate = sdf.format(examinationDate);

            String prescriptionData = "Prescription ID: " + prescID + "\n" +
                    "First Name: " + firstName + "\n" +
                    "Last Name: " + lastName + "\n" +
                    "Address: " + address + "\n" +
                    "Sphere: " + sphere + "\n" +
                    "Cylinder: " + cylinder + "\n" +
                    "Axis: " + axis + "\n" +
                    "Examination Date: " + formattedDate + "\n" +
                    "Optometrist: " + optometrist + "\n\n";

            java.io.FileWriter writer = new java.io.FileWriter("presc.txt", true);
            writer.write(prescriptionData);
            writer.close();

            System.out.println("Prescription added successfully.");
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            return false;
        }
    }

    public boolean addRemark(String remark, String category) {
        // check remark is not null and word count
        if (remark == null) {
            System.out.println("Remark cannot be null.");
            return false;
        }

        String[] words = remark.trim().split("\\s+");
        if (words.length < 6 || words.length > 20) {
            System.out.println("Remark must have between 6 and 20 words.");
            return false;
        }

        // check first character is uppercase
        if (!Character.isUpperCase(words[0].charAt(0))) {
            System.out.println("The first character of the first word must be uppercase.");
            return false;
        }

        // check category is valid
        if (category == null || (!category.equalsIgnoreCase(remarkTypes[0]) && !category.equalsIgnoreCase(remarkTypes[1]))) {
            System.out.println("Category must be 'client' or 'optometrist'.");
            return false;
        }

        //check if maximum remarks reached
        if (postRemarks.size() >= 2) {
            System.out.println("Cannot add more than 2 remarks.");
            return false;
        }

        // add remark to list
        postRemarks.add(remark);

        // write remark to file
        try {
            java.io.FileWriter writer = new java.io.FileWriter("review.txt", true);
            writer.write("Category: " + category + "\n");
            writer.write("Remark: " + remark + "\n\n");
            writer.close();
            return true;
        } catch (Exception e) {
            System.out.println("An error occurred while writing to the file.");
            return false;
        }
    }
}