package edu.csupomona.cs356.ivote;

import java.util.List;

public interface IVote {

	boolean submit(String uuid, List<String> submission);
	
	String showStats();


	Integer totalSubmissions();

	void endSubmissions();

}
