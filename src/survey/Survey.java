package survey;

import java.util.*;
import java.io.*;

/**
 * Survey Object that contains list of questions and controls the system
 * @author stevecalabro
 *
 */
public class Survey implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Question> questions = new ArrayList<Question>();
	private FileSystem fileSystem;
	private String name;
	
	//getters
	public ArrayList<Question> getQuestions()
	{
		return 	questions;
	}
	public FileSystem getFileSystem()
	{
		return fileSystem;
	}
	public String getName()
	{
		return name;
	}
	
	//setters
	public void setName(String _name)
	{
		name = _name;
	}
	public void setQuestions(ArrayList<Question> _questions)
	{
		questions = _questions;
	}
	public void setFileSystem(FileSystem _fileSystem)
	{
		fileSystem = _fileSystem;
	}
	
	//methods
	/**
	 * Adds a question the the Survey question ArrayList
	 * @param _question
	 */
	public void addQuestion(Question _question)
	{
		questions.add(_question);
	}
	/**
	 * Edits a question in the Survey question ArrayList
	 * @param _question
	 * @param newQuestion
	 */
	public void editQuestion(Question _question, Question newQuestion)
	{
		int questionIndex = questions.indexOf(_question);
		questions.set(questionIndex, newQuestion);
	}
	/**
	 * Removes a question in th Survey question ArrayList
	 * @param _question
	 */
	public void deleteQuestion(Question _question)
	{
		questions.remove(_question);
	}
	public void loadSurvey()
	{
		
	}
	public void gradeSurvey()
	{
		
	}
	public void tabulateSurvey()
	{
		
	}
}
