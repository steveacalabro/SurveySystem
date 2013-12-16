package survey;

import java.io.*;

/**
 * 
 * Question class for methods and options available to all question types
 * @author stevecalabro
 *
 */
public class Question implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	//Object Options
	private String prompt;
	private boolean isMultiAnswer;
	private Answer correctAnswer;
	private Answer userAnswer;
	private QuestionType type;
	
	//getters
	public Answer getCorrectAnswer()
	{
		return correctAnswer;
	}
	public boolean getIsMultiAnswer()
	{
		return isMultiAnswer;
	}
	public Answer getUserAnswer()
	{
		return userAnswer;
	}
	public String getPrompt()
	{
		return prompt;
	}
	public QuestionType getType()
	{
		return type;
	}
	
	//setters
	public void setCorrectAnswer(Answer _correctAnswer)
	{
		correctAnswer = _correctAnswer;
	}
	public void setMultiAnswer(boolean _isMultiAnswer)
	{
		isMultiAnswer = _isMultiAnswer;
	}
	public void setUserAnswer(Answer _userAnswer)
	{
		userAnswer = _userAnswer;
	}
	public void setType(QuestionType _type)
	{
		type = _type;
	}
	public void setPrompt(String _prompt)
	{
		prompt = _prompt;
	}
	
	//Methods
	/**
	 * Used for creating a question when all options are available no need for setters
	 * @param _prompt
	 * @param _answer
	 * @param _userAnswer
	 * @param _isMultiAnswer
	 */
	public void createQuestion(String _prompt, Answer _answer, Answer _userAnswer, boolean _isMultiAnswer)
	{
		prompt = _prompt;
		correctAnswer = _answer;
		userAnswer = _userAnswer;
		isMultiAnswer = _isMultiAnswer;
	}
	/**
	 * Used for editing all options of a question
	 * @param _prompt
	 * @param _answer
	 * @param _userAnswer
	 * @param _isMultiAnswer
	 */
	public void editQuestion(String _prompt, Answer _answer, Answer _userAnswer, boolean _isMultiAnswer)
	{
			prompt = _prompt;
			correctAnswer = _answer;
			userAnswer = _userAnswer;
			isMultiAnswer = _isMultiAnswer;
	}
}
