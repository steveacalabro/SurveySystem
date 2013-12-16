package survey;

import java.util.ArrayList;

/**
 * New type of question that emulates ranking and matching questions in a survey/test
 * @author stevecalabro
 *
 */
public class RankingMatching extends Question 
{	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> options = new ArrayList<String>();
	private ArrayList<String> choices = new ArrayList<String>();
	
	//getters
	public ArrayList<String> getOptions()
	{
		return options;
	}
	public ArrayList<String> getChoices()
	{
		return choices;
	}
	
	//setters
	public void setOptions(ArrayList<String> _options)
	{
		options = _options;
	}
	public void setChoices(ArrayList<String> _choices)
	{
		choices = _choices;
	}
	
	//methods
	/**
	 * Used to create a ranking question which sets the choices the numbers of ranking options
	 * @param _options
	 */
	public void createRanking(ArrayList<String> _options)
	{
		options = _options;
		for(int i=0; i < _options.size(); i++)
		{
			choices.add(Integer.toString(i));
		}
	}
	/**
	 * Used to edit an option in the question
	 * @param index
	 * @param newOp
	 */
	public void editOption(int index, String newOp)
	{
		options.set(index, newOp);
	}
	/**
	 * Used to edit a choice in the question
	 * @param index
	 * @param newChoice
	 */
	public void editChoice(int index, String newChoice)
	{
		choices.set(index, newChoice);
	}
}
