package edu.csupomona.cs356.ivote;

import java.util.List;

public interface IVote {

	boolean submit(String studentID, List<String> sub);

	String showStats();

	Integer totalSub();

	void endSub();

}