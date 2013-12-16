package survey;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Creates options for an Answer to a question for tests
 * @author stevecalabro
 *
 */
public class Answer  implements Serializable
{	
	private static final long serialVersionUID = 1L;
	
	private AnswerType type;
	private ArrayList<String> answer = new ArrayList<String>();
	
	//getters
	public AnswerType getType()
	{
		return type;
	}
	public ArrayList<String> getAnswer()
	{
		return answer;
	}
	public String getSingleAnswer()
	{
		return answer.get(0);
	}
	
	//setters
	public void setType(AnswerType _type)
	{
		type = _type;
	}
	public void setAnswer(ArrayList<String> _answer)
	{
		answer = _answer;
	}
	public void setSingleAnswer(String _answerText)
	{
		answer.add(_answerText);
	}
	
	//methods
	/**
	 * Method used when both the type and the answer Array list can be set at the same time
	 * @param _type
	 * @param _answer
	 */
	public void createAnswer(AnswerType _type, ArrayList<String> _answer)
	{
		type = _type;
		answer = _answer;
	}
}
