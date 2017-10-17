package edu.csupomona.cs356.ivote;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationDriver {

    public static void main(String[] args) {

        List<String> singleChoices = new ArrayList<String>();
        List<String> multiChoices = new ArrayList<String>();
        List<String> answer = new ArrayList<String>();
        List<String> answers = new ArrayList<String>();

        singleChoices.add("True");
        singleChoices.add("False");
        answer.add(singleChoices.get(0));

        multiChoices.add("Yes");
        multiChoices.add("No");
        multiChoices.add("Maybe");
        answers.add(multiChoices.get(0));
        answers.add(multiChoices.get(2));

        SimulationRun("SingleChoice", "Is OOP fun?", singleChoices, answer, 25);

        // Throws an exception
        //SimulationRun("SingleChoice", "Is OOP fun?", singleChoices, answers, 25);
        SimulationRun("MultipleChoice", "Is this an awesome program?", multiChoices, answers, 45);
    }

    /** Start a simulation, this is a wrapper so you can have multiple runs. */
    private static void SimulationRun(String qType,
            String q, List<String> choices, List<String> answers, Integer numStudents) {
        final Question question;
        final IVote iClicker;
        final Student[] students = new Student[numStudents];

        // 1)
        // create a question type and configure the answers
        switch(qType) {
            case "MultipleChoice":
                question = new MCQuestion(q, choices, answers);
                break;
            default:
                question = new SCQuestion(q, choices, answers);
                break;
        }
     
        iClicker = new IClickerService(question);

      
        for (Integer i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].enterAnswers(randGenAnswers(choices, qType));
            // submit
            iClicker.submit(students[i].getstudentID(), students[i].getAnswers());
        }
        if (iClicker.totalSubmissions() != numStudents) {
            System.err.println("Number of submissions is " + iClicker.totalSubmissions().toString());
        }
        System.out.println("Before submissions are over.");
        System.out.println(iClicker.showStats());

        // for some students submit a 2nd answer
        for (Integer i = 0; i < students.length; i += 5) {
            students[i].enterAnswers(randGenAnswers(choices, qType));
            // submit
            iClicker.submit(students[i].getstudentID(), students[i].getAnswers());
        }
        if (iClicker.totalSubmissions() != numStudents) {
            System.err.println("Number of submissions is " + iClicker.totalSubmissions().toString());
        }

        // end submissions
        iClicker.endSubmissions();

 
        System.out.println("After answers have been checked.");
        System.out.println(iClicker.showStats());
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
