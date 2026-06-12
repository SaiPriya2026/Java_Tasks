import java.util.*;
public class Student {
    private Integer studentId;
    private String studentName;
    private String courseName;
    private Integer graduationYear;
    private Double percentage;
    private Integer backlogs;
    private Double mockRating;
    private List<String> skills;
    private Boolean resumeAvailable;
    private Boolean feePaid;

    public Student()
    {

    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Integer getBacklogs() {
        return backlogs;
    }

    public void setBacklogs(Integer backlogs) {
        this.backlogs = backlogs;
    }

    public Double getMockRating() {
        return mockRating;
    }

    public void setMockRating(Double mockRating) {
        this.mockRating = mockRating;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public Boolean getResumeAvailable() {
        return resumeAvailable;
    }

    public void setResumeAvailable(Boolean resumeAvailable) {
        this.resumeAvailable = resumeAvailable;
    }

    public Boolean getFeePaid() {
        return feePaid;
    }

    public void setFeePaid(Boolean feePaid) {
        this.feePaid = feePaid;
    }
}
