package survey;

import java.util.ArrayList;

/**
 * New type of question that emulates True/False and Multiple choice questions in a survey/test
 * @author stevecalabro
 *
 */
public class TrueFalseMC extends Question 
{
	//options
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> options = new ArrayList<String>();
	
	//getters
	public ArrayList<String> getOptions()
	{
		return options;
	}
	//setters
	public void setOptions(ArrayList<String> _options)
	{
		options = _options;
	}
	
	//methods
	/**
	 * Used to edit an options in the question
	 * @param index
	 * @param newOp
	 */
	public void editOption(int index, String newOp)
	{
		options.set(index, newOp);
	}
	/**
	 * Used to set the question to default style of True/False by making the choices respectively
	 */
	public void createTrueFalse()
	{
		options.add("False");
		options.add("True");	
	}
}
