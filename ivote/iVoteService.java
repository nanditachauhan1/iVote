package edu.csupomona.cs356.ivote;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class iVoteService implements IVote {

    private Hashtable<String, List<String>> sub;
    private Question q1;
    private boolean subEnd;
    private Integer numCorrect;

    public iVoteService(Question q1) {
        this.q1 = q1;
        this.sub = new Hashtable<String, List<String>>();
        this.subEnd = false;
        this.numCorrect = 0;
    }

    
    @Override
	public boolean submit(String studentID, List<String> submission) {
        if (this.subEnd) {
            return false;
        }
        if (sub == null) {
            return false;
        }

        final List<String> uniqueSubmission = makeUnique(submission);
        final List<String> validSubmission = new ArrayList<String>();
        for (String sub : uniqueSubmission) {
            if (this.q1.inChoices(sub)) {
                validSubmission.add(sub);
            }
        }
        this.sub.put(studentID, validSubmission);
        return true;
    }

 
    private List<String> makeUnique(List<String> submission) {
        final Set<String> temp = new HashSet<String>(submission);
        final List<String> uq = new ArrayList<String>();
        uq.addAll(temp);
        return uq;
    }

    @Override
	public String showStats() {
        answerCheck();
        String stats = "Question: ";
        stats += this.q1.getQuestion();
        List<String> choices = this.q1.getChoices();
        for (String choice : choices) {
            Integer numChoice = 0;
            for (String studentID : sub.keySet()) {
                if (this.sub.get(studentID).contains(choice)) {
                    numChoice++;
                }
            }
            stats += choice + "\t\t\t" + numChoice.toString() + "\n";
        }
        stats += "Submissions: " + this.totalSub().toString();
        stats += "\nCorrect: " + this.numCorrect;
        stats += "\n\n";
        return stats;
    }

    
    @Override
	public Integer totalSub() {
        return this.sub.size();
    }

    
    @Override
	public void endSub() {
        this.subEnd = true;
    }

    private void answerCheck() {
        if (!this.subEnd) {
            return;
        }
        this.numCorrect = 0;
        for (Entry<String, List<String>> entry : sub.entrySet()) {
            final String check = q1.checkA(entry.getValue());
            if (check.compareTo("Your answer is correct!") == 0) {
                this.numCorrect++;
            }
        }
    }
}
