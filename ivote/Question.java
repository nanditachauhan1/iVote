package edu.csupomona.cs356.ivote;
import java.util.List;

public abstract class Question {

    protected String q1;
    protected List<String> choices;
    protected List<String> correct;

    public Question(String q1, List<String> choices, List<String> correct) {
        this.q1 = q1;
        this.choices = choices;
        this.correct = correct;
    }

    public String getQuestion() {
        return this.q1;
    }

    public List<String> getChoices() {
        return this.choices;
    }

    public boolean inChoices(String sub) {
        return this.choices.contains(sub);
    }

    public abstract String checkA(List<String> subAnswers);
}
