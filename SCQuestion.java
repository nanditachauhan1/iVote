package edu.csupomona.cs356.ivote;
import java.util.List;

public class SCQuestion extends Question {

    public SCQuestion(String description, List<String> choices, List<String> correct) {
        super(description, choices, correct);

        if (correct.size() != 1) {
            throw new IllegalArgumentException("The correct answers array must contain only one answer.");
        }
    }

    public String checkA(List<String> submittedAnswers) {
        if (submittedAnswers.size() != 1) {
            return "This is a single choice answer, please submit one answer.";
        } else {
            if (this.correct.get(0).compareTo(submittedAnswers.get(0)) == 0) {
                return "Your answer is correct!";
            } else {
                return "Your answer is incorrect. The correct answer is: "+this.correct.get(0);
            }
        }
    }
}
