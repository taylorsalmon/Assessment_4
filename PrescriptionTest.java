import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

    /**
     * Test that a valid prescription is added successfully.
     */
    @Test
    public void testAddPrescription_ValidData() {
        Prescription prescription = new Prescription();
        prescription.firstName = "John";
        prescription.lastName = "Doe";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date(); // Assuming today's date
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertTrue(result, "Valid prescription should return true");
    }

    /**
     * Test that addPrescription returns false when the first name is too short.
     */
    @Test
    public void testAddPrescription_InvalidFirstNameTooShort() {
        Prescription prescription = new Prescription();
        prescription.firstName = "as"; // Too short
        prescription.lastName = "as";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "Prescription with short first name should return false");
    }

    /**
     * Test that addPrescription returns false when the last name is too long.
     */
    @Test
    public void testAddPrescription_InvalidLastNameTooLong() {
        Prescription prescription = new Prescription();
        prescription.firstName = "John";
        prescription.lastName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // too long
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "Prescription with long last name should return false");
    }

    /**
     * Test that addPrescription returns false when the address is too short.
     */
    @Test
    public void testAddPrescription_InvalidAddressTooShort() {
        Prescription prescription = new Prescription();
        prescription.firstName = "John";
        prescription.lastName = "Doe";
        prescription.address = "Short Address"; // Less than 20 characters
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "Prescription with short address should return false");
    }

    /**
     * Test that addPrescription returns false when the sphere value is out of range.
     */
    @Test
    public void testAddPrescription_InvalidSphereOutOfRange() {
        Prescription prescription = new Prescription();
        prescription.firstName = "John";
        prescription.lastName = "Doe";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 25.0f; // Out of range
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "Prescription with sphere out of range should return false");
    }

    /**
     * Test that addPrescription returns false when the optometrist's name is too short.
     */
    @Test
    public void testAddPrescription_InvalidOptometristNameTooShort() {
        Prescription prescription = new Prescription();
        prescription.firstName = "John";
        prescription.lastName = "Doe";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr."; // Too short

        boolean result = prescription.addPrescription();
        assertFalse(result, "Prescription with short optometrist name should return false");
    }

    @Test
public void testAddRemark_ValidRemark() {
    Prescription prescription = new Prescription();

    // Adding a valid remark
    String remark = "This is a valid remark with more than six words.";
    String category = "client";
    boolean result = prescription.addRemark(remark, category);

    // Assert the remark was added successfully
    assertTrue(result, "Valid remark should return true");
}
}