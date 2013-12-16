package survey;

/**
 * New type of question that emulates Short Answer and Essay questions in a survey/test
 * @author stevecalabro
 *
 */
public class SAEssay extends Question 
{	
	private static final long serialVersionUID = 1L;
	private int length;
	
	//getters
	public int getLength()
	{
		return length;
	}
	//setters
	public void setLength(int _length)
	{
		length = _length;
	}
	//methods
	/**
	 * Used to set the length to default 500 for Short Answer Questions
	 */
	public void createShortAnswer()
	{
		length = 500;
	}
}
