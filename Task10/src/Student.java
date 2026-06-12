public class Student {

    private Integer studentId;
    private String studentName;
    private String courseName;

    public Student(Integer studentId, String studentName, String courseName) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public String getCourseName() {
        return courseName;
    }
    public String getStudentName() {
        return studentName;
    }
}