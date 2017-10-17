package edu.csupomona.cs356.ivote;
import java.util.List;

/**
  * A student has a studentID, and an answer. To make things simple my studentID
  * is just a number which is derived from count which keeps track
  * of the number of instances created. A student also has answers.
  * As a student you can enter answers, submit answers, and check
  * answers.
  */

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

    /** Store generated answers (can be random or manually input) */
    public boolean enterAnswers(List<String> input) {
        this.answers = input;
        return true;
    }

    public List<String> getAnswers() {
        return answers;
    }
}
