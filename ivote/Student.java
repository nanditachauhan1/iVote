package edu.csupomona.cs356.ivote;
import java.util.List;


public class Student {

    private static int count = 0;

    private String studentID;
    private List<String> answers;

    public Student() {
        count += 1;
        this.studentID = "" + count;
    }

    public String getstudentID() {
        return studentID;
    }

    public boolean enterAnswers(List<String> input) {
        this.answers = input;
        return true;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
