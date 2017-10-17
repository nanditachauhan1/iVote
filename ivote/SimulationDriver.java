package edu.csupomona.cs356.ivote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SimulationDriver {

    public static void main(String[] args) {

        List<String> singleChoice = new ArrayList<String>();
        List<String> multiChoice = new ArrayList<String>();
        List<String> singleAnswer = new ArrayList<String>();
        List<String> multiAnswer = new ArrayList<String>();

        singleChoice.add("True");
        singleChoice.add("False");
        singleAnswer.add(singleChoice.get(0));

        multiChoice.add("Yes");
        multiChoice.add("No");
        multiChoice.add("Maybe");
        multiAnswer.add(multiChoice.get(0));
        multiAnswer.add(multiChoice.get(2));

        SimulationRun("SingleChoice", "Do you like food?\n", singleChoice, singleAnswer, 25);

        SimulationRun("MultipleChoice", "Do you eat meat?\n", multiChoice, multiAnswer, 45);
    }

  
    private static void SimulationRun(String qType,
            String q, List<String> choices, List<String> answers, Integer totalStudents) {
        final Question question;
        final IVote iVote;
        final Student[] students = new Student[totalStudents];


        switch(qType) {
            case "MultipleChoice":
                question = new MCQuestion(q, choices, answers);
                break;
            default:
                question = new SCQuestion(q, choices, answers);
                break;
        }

        
        iVote = new iVoteService(question);

        
        for (Integer i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].enterAnswers(randGenAnswers(choices, qType));
            iVote.submit(students[i].getstudentID(), students[i].getAnswers());
        }
        if (iVote.totalSub() != totalStudents) {
            System.err.println("Number of submissions: " + iVote.totalSub().toString());
        }
        System.out.println("Before submissions:");
        System.out.println(iVote.showStats());

        for (Integer i = 0; i < students.length; i += 5) {
            students[i].enterAnswers(randGenAnswers(choices, qType));
            iVote.submit(students[i].getstudentID(), students[i].getAnswers());
        }
        if (iVote.totalSub() != totalStudents) {
            System.err.println("Number of submissions: " + iVote.totalSub().toString());
        }

        iVote.endSub();
        System.out.println("Answers submitted: ");
        System.out.println(iVote.showStats());
    }

        private static List<String> randGenAnswers(List<String> choices, String type) {
        Integer numAnswers = 1;
        final Random rand = new Random();
        ArrayList<String> answers = new ArrayList<String>();
        if (type == "MultipleChoice") {
            numAnswers = rand.nextInt(choices.size() - 1) + 1;
        }
        for (Integer i = 0; i < numAnswers; i++) {
            answers.add(choices.get(rand.nextInt(choices.size())));
        }
        return answers;
    }
};
