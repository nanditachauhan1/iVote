package edu.csupomona.cs356.ivote;

import java.util.ArrayList;
import java.util.List;

public class MCQuestion extends Question {

    public MCQuestion(String description, List<String> choices, List<String> correct) {
        super(description, choices, correct);
    }

    public String checkA(List<String> subAnswers) {
        if (subAnswers.size() < 1) {
            return "Please submit at least one answer.";
        } else {
            boolean correct = true;
            final List<String> temp = new ArrayList<String>(subAnswers);
            for (Integer i = 0; i < this.correct.size(); i++) {
                correct = temp.remove(this.correct.get(i));
                if (!correct) {
                    break;
                }
            }
            if (!correct || temp.size() != 0) {
                return "Your answer is incorrect. The correct answer is "+this.correct.toString();
            } else {
                return "Your answer is correct!";
            }
        }
    }
}
