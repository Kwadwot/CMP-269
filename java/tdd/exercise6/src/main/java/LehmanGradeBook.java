public class LehmanGradeBook {

    public boolean isPassing(int grade) throws IllegalArgumentException {
        // ENSURES A VALID GRADE IS PASSED
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException();
        }
        return grade >= 70;
    }

    /*
        THIS METHOD CHECKS IF THE GRADE SATISFIES THE
        APPROPRIATE BOUNDARY CONDITIONS TO RETURN THE
        CORRECT GRADE.
     */
    public char getLetterGrade(int score) {
        if (score >= 90) return 'A';
        if (score >= 80) return 'B';
        if (score >= 70) return 'C';
        if (score >= 60) return 'D';

        return 'F';
    }

    public static void main(String[] args) {

        System.out.println(new LehmanGradeBook().getLetterGrade(70));
    }
}
