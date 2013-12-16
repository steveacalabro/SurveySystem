package survey;

import java.io.*;

/**
 * Creates a file system to save the Surveys/Tests
 * @author stevecalabro
 *
 */
public class FileSystem 
{
	private String file;
	
	//getters
	public String getFile()
	{
		return file;
	}
	//setters
	public void setFile(String _file)
	{
		file = _file;
	}
	//methods
	/**
	 * Method to save the survey object serialized to the desktop
	 * @param s
	 */
	public void saveSurvey(Survey s)
	{
		try
		{  
			FileOutputStream fout = new FileOutputStream(file);  
			ObjectOutputStream out = new ObjectOutputStream(fout);  
			out.writeObject(s);  
			out.close();
		}
		catch(Exception e)
		{  
			e.printStackTrace();  
		}
	}
	public Survey loadSurvey()
	{
		Survey returnSurvey = null;
		
		try
		{
			FileInputStream f_in = new FileInputStream(file);
	
			ObjectInputStream obj_in = new ObjectInputStream (f_in);
	
			Object obj = obj_in.readObject();
	
			if (obj instanceof Survey)
			{
				// Cast object to a Vector
				returnSurvey = (Survey) obj;
	
				// Do something with vector....
			}
			obj_in.close();
		}
		catch(Exception e)
		{  
			e.printStackTrace();  
		}
		
		return returnSurvey;
	}
}
