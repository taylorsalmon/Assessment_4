import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

    private static int prescriptionCounter = 0; // static counter to track and increment the prescID

    @BeforeEach
    public void setUp() {
        prescriptionCounter++; // increment the prescription ID for each test
    }

    // test valid prescription addition
    @Test
    public void testAddPrescription_ValidData() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "John";
        prescription.lastName = "Smith";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertTrue(result, "valid prescription should return true");
    }

    // test prescription fails with short first name
    @Test
    public void testAddPrescription_InvalidFirstNameTooShort() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "as"; // too short
        prescription.lastName = "Smith";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "prescription with short first name should return false");
    }

    // test prescription fails with long last name
    @Test
    public void testAddPrescription_InvalidLastNameTooLong() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "John";
        prescription.lastName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; // too long
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "prescription with long last name should return false");
    }

    // test prescription fails with short address
    @Test
    public void testAddPrescription_InvalidAddressTooShort() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "John";
        prescription.lastName = "Smith";
        prescription.address = "Short Address"; // less than 20 characters
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "prescription with short address should return false");
    }

    // test prescription fails with sphere value out of range
    @Test
    public void testAddPrescription_InvalidSphereOutOfRange() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "John";
        prescription.lastName = "Smith";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 25.0f; // out of range
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr. William Smith";

        boolean result = prescription.addPrescription();
        assertFalse(result, "prescription with sphere out of range should return false");
    }

    // test prescription fails with short optometrist name
    @Test
    public void testAddPrescription_InvalidOptometristNameTooShort() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter;
        prescription.firstName = "John";
        prescription.lastName = "Smith";
        prescription.address = "123 Main Street, Suburbia, 12345, Country";
        prescription.sphere = 0.0f;
        prescription.cylinder = 0.0f;
        prescription.axis = 90.0f;
        prescription.examinationDate = new Date();
        prescription.optometrist = "Dr."; // too short

        boolean result = prescription.addPrescription();
        assertFalse(result, "prescription with short optometrist name should return false");
    }

    // test valid remark addition
    @Test
    public void testAddRemark_ValidRemark() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = "This is a valid remark with more than six words.";
        String category = "client";
        boolean result = prescription.addRemark(remark, category);

        assertTrue(result, "valid remark should return true");
    }

    // test addRemark fails with null remark
    @Test
    public void testAddRemark_NullRemark() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = null;
        String category = "client";
        boolean result = prescription.addRemark(remark, category);

        assertFalse(result, "null remark should return false");
    }

    // test addRemark fails with remark too short
    @Test
    public void testAddRemark_RemarkTooShort() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = "Too short";
        String category = "client";
        boolean result = prescription.addRemark(remark, category);

        assertFalse(result, "remark with less than 6 words should return false");
    }

    // test addRemark fails with remark too long
    @Test
    public void testAddRemark_RemarkTooLong() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = "This remark has way too many words and should definitely be longer than twenty words so that it fails the test case for remarks that are too long.";
        String category = "client";
        boolean result = prescription.addRemark(remark, category);

        assertFalse(result, "remark with more than 20 words should return false");
    }

    // test addRemark fails when first character is not uppercase
    @Test
    public void testAddRemark_FirstCharacterNotUppercase() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = "this remark starts with a lowercase letter.";
        String category = "client";
        boolean result = prescription.addRemark(remark, category);

        assertFalse(result, "remark starting with lowercase letter should return false");
    }

    // test addRemark fails with invalid category
    @Test
    public void testAddRemark_InvalidCategory() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark = "This is a valid remark with more than six words.";
        String category = "invalid";
        boolean result = prescription.addRemark(remark, category);

        assertFalse(result, "remark with invalid category should return false");
    }

    // test addRemark fails when more than 2 remarks are added
    @Test
    public void testAddRemark_MoreThanTwoRemarks() {
        Prescription prescription = new Prescription();
        prescription.prescID = prescriptionCounter; // make sure prescription ID is included

        String remark1 = "This is the first valid remark.";
        String remark2 = "This is the second valid remark.";
        String remark3 = "This is the third valid remark.";
        String category = "client";

        boolean result1 = prescription.addRemark(remark1, category);
        boolean result2 = prescription.addRemark(remark2, category);
        boolean result3 = prescription.addRemark(remark3, category);

        assertTrue(result1, "first valid remark should return true");
        assertTrue(result2, "second valid remark should return true");
        assertFalse(result3, "third remark should return false as maximum of 2 remarks allowed");
    }
}