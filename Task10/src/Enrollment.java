public class Enrollment {

    private Integer studentId;
    private String courseName;

    public Enrollment(Integer studentId, String courseName) {
        this.studentId = studentId;
        this.courseName = courseName;
    }

    public String getCourseName() { return courseName; }
}