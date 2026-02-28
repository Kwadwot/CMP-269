import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    @DisplayName("Grade 70 should return true for passing")
    void testPassingGrade() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertTrue(gb.isPassing(70), "A grade of 70 should pass.");
    }

    @Test
    @DisplayName("Grade 95 should return 'A' for getLetterGrade")
    void testGetLetterGradeIsA () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(95));
    }

    @Test
    @DisplayName("Grade 64 should return 'C' for getLetterGrade")
    void testGetLetterGradeIsC () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('C', gb.getLetterGrade(72));
    }

    @Test
    @DisplayName("Grade 50 should return 'F' for getLetterGrade")
    void testGetLetterGradeIsF () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('F', gb.getLetterGrade(50));
    }

    // --- Boundary Testing --- //
    @Test
    @DisplayName("Grade 90 should return 'A' for getLetterGrade boundary score 90")
    void testGetLetterGradeBoundaryA () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(90));
    }

    @Test
    @DisplayName("Grade 90 should return 'B' for getLetterGrade boundary score 80")
     void testGetLetterGradeBoundaryB () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('B', gb.getLetterGrade(80));
    }

    @Test
    @DisplayName("Grade 70 should return 'C' for getLetterGrade boundary score 70")
    void testGetLetterGradeBoundaryC () {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('C', gb.getLetterGrade(70));
    }
    // --- Boundary Testing --- //

    @Test
    void testInvalidGradeThrowsException() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertThrows(IllegalArgumentException.class, () -> {
            gb.isPassing(150);
        });
    }

    /*
        NOTE: I had issues with running the tests because I kept getting the following error:
        java.io.IOException: Unable to delete directory 'C:\Users\Savag\OneDrive\Desktop\CMP 269\java\tdd\exercise6\build\test-results\test\binary'

        I tried to find a solutions online. And after excluding the build directory, restarting my computer,
        deleting the build directory multiple times, and trying to run ./gradlew clean among other commands,
        nothing seemed to work if I wanted to freely rerun tests more than once. I don't know if this is an
        issue with the project structure, permissions, or just an IntelliJ IDE issue.

        To conclude, I confirmed that the tests run successfully with the ./gradlew test command; however,
        I can only run it once with a successful build before having to delete my build directory to rerun it.
     */


}
