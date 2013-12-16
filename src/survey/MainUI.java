package survey;

//import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Main UI Class for console use of the Survey System
 * @author stevecalabro
 *
 */
public class MainUI 
{
	private static Survey mainObj;
	private static HashMap<String, Survey> surveys = new HashMap<String, Survey>();
	private static HashMap<String, Survey> tests = new HashMap<String, Survey>();
	
	/**
	 * Main UI menu
	 * @param args
	 */
	public static void main(String[] args)
	{	
		boolean status = true; 
		while(status)
		{	
		    System.out.println("1) Create a new Survey");
			System.out.println("2) Create a new Test");
			System.out.println("3) Display Survey");
			System.out.println("4) Display a Test");
			System.out.println("5) Save a Survey");
			System.out.println("6) Save a Test");
			System.out.println("7) Modify an Existing Survey");
			System.out.println("8) Modify an Existing Test");
			System.out.println("9) Load a Survey");
			System.out.println("10) Load a Test");
			System.out.println("11) Take a Survey");
			System.out.println("12) Take a Test");
			System.out.println("13) Quit");
			
			System.out.println("Choice: ");
			Scanner in = new Scanner(System.in);
			in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
			
			int input = in.nextInt();
			
			switch(input)
			{
				case 1:
					//no grading
					System.out.println("What is the name of this survey? ");
					String surName = in.next();
					
					if(surName != null)
					{
						mainObj = createSurvey();
						mainObj.setName(surName);
						surveys.put(surName, mainObj);
					}
					break;
				case 2:
					//double of survey with grading
					System.out.println("What is the name of this survey? ");
					String testName = in.next();
					if(testName != null)
					{
						mainObj = createTest();
						mainObj.setName(testName);
						tests.put(testName, mainObj);
					}
					break;
				case 3:
					System.out.println("------------------------------------------------------------------");
					System.out.println("Survey: "+mainObj.getName());
					displaySurvey(mainObj);
					System.out.println("------------------------------------------------------------------");
					break;
				case 4:
					System.out.println("------------------------------------------------------------------");
					System.out.println("Test: "+mainObj.getName());
					displayTest(mainObj);
					System.out.println("------------------------------------------------------------------");
					break;
				case 5:
					FileSystem file = new FileSystem();
					file.setFile(mainObj.getName()+"_Survey.ser");
					file.saveSurvey(mainObj);
					break;
				case 6:
					FileSystem file2 = new FileSystem();
					file2.setFile(mainObj.getName()+"_Test.ser");
					file2.saveSurvey(mainObj);
					break;
				case 7:
					System.out.println("What is the name of this survey? ");
					surName = in.next();
					
					mainObj = surveys.get(surName);
					
					editSurvey(mainObj);
					break;
				case 8:
					System.out.println("What is the name of this test? ");
					testName = in.next();
					
					mainObj = tests.get(testName);
					
					editTest(mainObj);
					break;
				case 9:
					System.out.println("What is the name of the survey? ");
					surName = in.next();
					
					FileSystem loadSurvey = new FileSystem();
					loadSurvey.setFile(surName+"_Survey.ser");
					mainObj = loadSurvey.loadSurvey();
					break;
				case 10:
					System.out.println("What is the name of the test? ");
					testName = in.next();
					
					FileSystem loadTest = new FileSystem();
					loadTest.setFile(testName+"_Test.ser");
					mainObj = loadTest.loadSurvey();
					break;
				case 11:
					System.out.println("What is the name of the survey? ");
					surName = in.next();
					
					FileSystem loadSurveyForTake = new FileSystem();
					loadSurveyForTake.setFile(surName+"_Survey.ser");
					mainObj = loadSurveyForTake.loadSurvey();
					
					//take survey
					takeSurvey(mainObj);
					
					FileSystem fileTakeS = new FileSystem();
					fileTakeS.setFile(mainObj.getName()+"_Survey.ser");
					fileTakeS.saveSurvey(mainObj);
					break;
				case 12:
					System.out.println("What is the name of the test? ");
					testName = in.next();
					
					FileSystem loadTestForTake = new FileSystem();
					loadTestForTake.setFile(testName+"_Test.ser");
					mainObj = loadTestForTake.loadSurvey();
					
					//take test
					takeTest(mainObj);
					
					FileSystem fileTakeT = new FileSystem();
					fileTakeT.setFile(mainObj.getName()+"_Test.ser");
					fileTakeT.saveSurvey(mainObj);
					break;
				case 13:
					System.out.println("Thank you!Goodbye!");
					status = false;
					break;
			}
		}
	}
	
	//survey methods
	/**
	 * Creates a new Survey object by taking in user input
	 * @return Survey
	 */
	public static Survey createSurvey()
	{ 
		Survey newSurvey = new Survey();
		
		boolean status = true;
		
		while(status)
		{
			System.out.println("1) Add a new T/F question ");
			System.out.println("2) Add a new multiple choice question ");
			System.out.println("3) Add a new short answer question ");
			System.out.println("4) Add a new essay question ");
			System.out.println("5) Add a new ranking question ");
			System.out.println("6) Add a new matching question ");
			System.out.println("7) Exit to Main Menu");
			
			System.out.println("Choice: ");
			Scanner in = new Scanner(System.in);
			in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
			
			int input = in.nextInt();
			
			switch(input)
			{
				case 1:
					Question tf = createQuestion(QuestionType.trueFalse);
					tf.setType(QuestionType.trueFalse);
					newSurvey.addQuestion(tf);
					
					break;
				case 2:
					Question mc = createQuestion(QuestionType.multipleChoice);
					mc.setType(QuestionType.multipleChoice);
					newSurvey.addQuestion(mc);
					break;
				case 3:
					Question sa = createQuestion(QuestionType.shortAnswer);
					sa.setType(QuestionType.shortAnswer);
					newSurvey.addQuestion(sa);
					break;
				case 4:
					Question essay = createQuestion(QuestionType.essay);
					essay.setType(QuestionType.essay);
					newSurvey.addQuestion(essay);
					break;
				case 5:
					Question rank = createQuestion(QuestionType.ranking);
					rank.setType(QuestionType.ranking);
					newSurvey.addQuestion(rank);
					break;
				case 6:
					Question match = createQuestion(QuestionType.matching);
					match.setType(QuestionType.matching);
					newSurvey.addQuestion(match);
					break;
				case 7:
					status = false;
					break;	
			}
		}
		return newSurvey;
	}
	/**
	 * Edits a Survey object by taking in user input
	 * @param survey
	 */
	public static void editSurvey(Survey survey)
	{
		Scanner in = new Scanner(System.in);
		in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		
		System.out.println("What question number do you wish to modify?");
		displaySurvey(survey);
		int qMod = in.nextInt();
		
		ArrayList<Question> _questions = new ArrayList<Question>();
		_questions = survey.getQuestions();
		Question origQuestion = _questions.get(qMod);
		
		System.out.println("Do you wish to modify the prompt?(1-yes, 0-no)");
		int promptYN = in.nextInt();
		
		if(promptYN == 1)
		{
			System.out.println("Original: "+origQuestion.getPrompt());
			System.out.println("Enter a new prompt: ");
			String newPrompt = in.next();
			origQuestion.setPrompt(newPrompt);
			System.out.println("New Prompt has been Set");
		}
		
		System.out.println("Would you like to edit more information on the question?(1-yes, 0-no)");
		int quesYN = in.nextInt();

		if(quesYN == 1)
		{
			QuestionType qType;
			qType = origQuestion.getType();
			
			switch(qType)
			{
				case trueFalse:
					ArrayList<String> _options = new ArrayList<String>();
					TrueFalseMC tfQuestion = new TrueFalseMC();
					tfQuestion = (TrueFalseMC) origQuestion;
					
					_options = tfQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < _options.size(); i++)
					{
						System.out.println("Options #"+i+" : "+_options.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOp = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newString = in.next();
					
					tfQuestion.editOption(edOp, newString);
					break;
				case multipleChoice:
					ArrayList<String> _optionsMC = new ArrayList<String>();
					TrueFalseMC mcQuestion = new TrueFalseMC();
					mcQuestion = (TrueFalseMC) origQuestion;
					
					_optionsMC = mcQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < _optionsMC.size(); i++)
					{
						System.out.println("Options #"+i+" : "+_optionsMC.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpMC = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringMC = in.next();
					
					mcQuestion.editOption(edOpMC, newStringMC);
					break;
				case shortAnswer:
					System.out.println("Would you like to edit the new Length?(1-yes, 0-no)");
					int newSA = in.nextInt();
					
					if(newSA == 1)
					{
						System.out.println("Enter the new length for the Short Answer: ");
						int newLength = in.nextInt();
						
						SAEssay saQuestion = new SAEssay();
						saQuestion = (SAEssay) origQuestion;
						
						saQuestion.setLength(newLength);
					}
					break;
				case essay:
					System.out.println("Would you like to edit the new Length?(1-yes, 0-no)");
					int newES = in.nextInt();
					
					if(newES == 1)
					{
						System.out.println("Enter the new length for the Essay Question: ");
						int newLengthES = in.nextInt();
						
						SAEssay esQuestion = new SAEssay();
						esQuestion = (SAEssay) origQuestion;
						
						esQuestion.setLength(newLengthES);
					}
					break;
				case ranking:
					//Options
					ArrayList<String> optionsRank = new ArrayList<String>();
					RankingMatching rankQuestion = new RankingMatching();
					rankQuestion = (RankingMatching) origQuestion;
					
					optionsRank = rankQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < optionsRank.size(); i++)
					{
						System.out.println("Options #"+i+" : "+optionsRank.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpRank = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringRank = in.next();
					
					rankQuestion.editOption(edOpRank, newStringRank);
					
					//Choices
					ArrayList<String> choicesRank = new ArrayList<String>();
					choicesRank = rankQuestion.getChoices();
					
					System.out.println("Current Choices: ");
					for(int i = 0; i < choicesRank.size(); i++)
					{
						System.out.println("Choice #"+i+" : "+choicesRank.get(i));
					}
					
					System.out.println("Which choice would you like to edit?");
					int edChoice = in.nextInt();
					
					System.out.println("Enter a new String for the choice: ");
					String newChoiceRank = in.next();
					
					rankQuestion.editChoice(edChoice, newChoiceRank);
					break;
				case matching:
					//Options
					ArrayList<String> optionsMatch = new ArrayList<String>();
					RankingMatching matchQuestion = new RankingMatching();
					matchQuestion = (RankingMatching) origQuestion;
					
					optionsMatch = matchQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < optionsMatch.size(); i++)
					{
						System.out.println("Options #"+i+" : "+optionsMatch.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpMatch = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringMatch = in.next();
					
					matchQuestion.editOption(edOpMatch, newStringMatch);
					
					//Choices
					ArrayList<String> choicesMatch = new ArrayList<String>();
					choicesMatch = matchQuestion.getChoices();
					
					System.out.println("Current Choices: ");
					for(int i = 0; i < choicesMatch.size(); i++)
					{
						System.out.println("Choice #"+i+" : "+choicesMatch.get(i));
					}
					
					System.out.println("Which choice would you like to edit?");
					int edChoiceMatch = in.nextInt();
					
					System.out.println("Enter a new String for the choice: ");
					String newChoiceMatch = in.next();
					
					matchQuestion.editChoice(edChoiceMatch, newChoiceMatch);
					break;
			}
		}
	}
	/**
	 * Displays a Survey object to the console
	 * @param survey
	 */
	public static void displaySurvey(Survey survey)
	{
		ArrayList<Question> questions = new ArrayList<Question>();
		questions = survey.getQuestions();
		
		for(int i = 0; i < questions.size(); i++)
		{
			Question q = questions.get(i);
			System.out.println("Question #"+i+" : "+q.getPrompt());
			
			QuestionType qType = q.getType();
			switch(qType)
			{
				case trueFalse:
					TrueFalseMC tf = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> tfOptions = new ArrayList<String>();
					tfOptions = tf.getOptions();
						
					System.out.println("Options: "+tfOptions.toString());
					
					if(tf.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+tf.getUserAnswer().getAnswer());
					}
					break;
				case multipleChoice:
					TrueFalseMC mc = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> mcOptions = new ArrayList<String>();
					mcOptions = mc.getOptions();
						
					System.out.println("Options: "+mcOptions.toString());
					
					if(mc.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+mc.getUserAnswer().getAnswer());
					}
					break;
				case shortAnswer:
					SAEssay sa = (SAEssay) questions.get(i);
					
					//THere is no second part
					if(sa.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+sa.getUserAnswer().getAnswer());
					}
					break;
				case essay:
					SAEssay es = (SAEssay) questions.get(i);
					
					//THere is no second part
					if(es.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+es.getUserAnswer().getAnswer());
					}
					break;
				case ranking:
					RankingMatching rank = (RankingMatching) questions.get(i);
					
					ArrayList<String> rankOptions = new ArrayList<String>();
					rankOptions = rank.getOptions();
					
					ArrayList<String> rankChoices = new ArrayList<String>();
					rankChoices = rank.getChoices();
						
					System.out.println("Options: "+rankOptions.toString());
					System.out.println("Choices: "+rankChoices.toString());
					
					if(rank.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+rank.getUserAnswer().getAnswer());
					}
					break;
				case matching:
					RankingMatching match = (RankingMatching) questions.get(i);
					
					ArrayList<String> matchOptions = new ArrayList<String>();
					matchOptions = match.getOptions();
					
					ArrayList<String> matchChoices = new ArrayList<String>();
					matchChoices = match.getChoices();
						
					System.out.println("Options: "+matchOptions.toString());
					System.out.println("Choices: "+matchChoices.toString());
					
					if(match.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+match.getUserAnswer().getAnswer());
					}
					break;		
			}
			System.out.println("");
		}
	}
	/**
	 * Method used to take a Survey/Test and save User answers
	 * @param survey
	 */
	public static void takeSurvey(Survey survey)
	{
		Scanner in = new Scanner(System.in);
		in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		
		ArrayList<Question> questions = new ArrayList<Question>();
		questions = survey.getQuestions();
		
		for(int i = 0; i < questions.size(); i++)
		{
			Question q = questions.get(i);
			System.out.println("Question #"+i+" : "+q.getPrompt());
			
			QuestionType qType = q.getType();
			switch(qType)
			{
				case trueFalse:
					TrueFalseMC tf = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> tfOptions = new ArrayList<String>();
					tfOptions = tf.getOptions();
						
					System.out.println("Options: "+tfOptions.toString());
					
					//User Answer
					Answer tfAnswer = new Answer();
					tfAnswer.setType(AnswerType.text);
					
					System.out.println("Enter your answer(As full text): ");
					String tfInput = in.next();
					
					tfAnswer.setSingleAnswer(tfInput);
					tf.setUserAnswer(tfAnswer);
					break;
				case multipleChoice:
					TrueFalseMC mc = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> mcOptions = new ArrayList<String>();
					mcOptions = mc.getOptions();
						
					System.out.println("Options: "+mcOptions.toString());
					
					//User Answer
					Answer mcAnswer = new Answer();
					mcAnswer.setType(AnswerType.letter);
					
					System.out.println("Enter your answer(As a letter or number): ");
					String mcInput = in.next();
					
					mcAnswer.setSingleAnswer(mcInput);
					mc.setUserAnswer(mcAnswer);
					break;
				case shortAnswer:
					SAEssay sa = (SAEssay) questions.get(i);
					
					//User Answer
					Answer saAnswer = new Answer(); 
					saAnswer.setType(AnswerType.text);
					
					System.out.println("Enter your answer(As full text): ");
					String saInput = in.next();
					
					saAnswer.setSingleAnswer(saInput);
					sa.setUserAnswer(saAnswer);
					break;
				case essay:
					SAEssay es = (SAEssay) questions.get(i);
					
					//User Answer
					Answer esAnswer = new Answer();
					esAnswer.setType(AnswerType.text);
					
					System.out.println("Enter your answer(As full text): ");
					String esInput = in.next();
					
					esAnswer.setSingleAnswer(esInput);
					es.setUserAnswer(esAnswer);
					break;
				case ranking:
					RankingMatching rank = (RankingMatching) questions.get(i);
					
					ArrayList<String> rankOptions = new ArrayList<String>();
					rankOptions = rank.getOptions();
					
					ArrayList<String> rankChoices = new ArrayList<String>();
					rankChoices = rank.getChoices();
						
					System.out.println("Options: "+rankOptions.toString());
					System.out.println("Choices: "+rankChoices.toString());
					
					//User Answer
					Answer rankAnswer = new Answer();
					rankAnswer.setType(AnswerType.letterArray);
					
					ArrayList<String> letterArray = new ArrayList<String>();
					
					for(int j = 0; j < rankChoices.size(); j++)
					{
						System.out.println("Enter Answer for Option #"+j+": ");
						String rankInput = in.next();
						letterArray.add(rankInput);
					}
					
					rankAnswer.setAnswer(letterArray);
					rank.setUserAnswer(rankAnswer);
					break;
				case matching:
					RankingMatching match = (RankingMatching) questions.get(i);
					
					ArrayList<String> matchOptions = new ArrayList<String>();
					matchOptions = match.getOptions();
					
					ArrayList<String> matchChoices = new ArrayList<String>();
					matchChoices = match.getChoices();
						
					System.out.println("Options: "+matchOptions.toString());
					System.out.println("Choices: "+matchChoices.toString());
					
					//User Answer
					Answer matchAnswer = new Answer();
					matchAnswer.setType(AnswerType.letterArray);
					
					ArrayList<String> matchletterArray = new ArrayList<String>();
					
					for(int j = 0; j < matchChoices.size(); j++)
					{
						System.out.println("Enter Answer for Option #"+j+": ");
						String matchInput = in.next();
						matchletterArray.add(matchInput);
					}
					
					matchAnswer.setAnswer(matchletterArray);
					match.setUserAnswer(matchAnswer);
					break;		
			}
			System.out.println("");
		}
	}
	//Test questions
	/**
	 * Creates a new Test(Survey) object by taking in user input
	 * @return Survey
	 */
	public static Survey createTest()
	{
		Survey newTest = new Survey();
		
		boolean status = true;
		
		while(status)
		{
			System.out.println("1) Add a new T/F question ");
			System.out.println("2) Add a new multiple choice question ");
			System.out.println("3) Add a new short answer question ");
			System.out.println("4) Add a new essay question ");
			System.out.println("5) Add a new ranking question ");
			System.out.println("6) Add a new matching question ");
			System.out.println("7) Exit to Main Menu");
			
			System.out.println("Choice: ");
			Scanner in = new Scanner(System.in);
			in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
			
			int input = in.nextInt();
			
			switch(input)
			{
				case 1:
					Question tf = createQuestion(QuestionType.trueFalse);
					tf.setType(QuestionType.trueFalse);
					
					//Answers
					Answer tfAnswer = createAnswer(AnswerType.text);
					tf.setCorrectAnswer(tfAnswer);
					
					newTest.addQuestion(tf);
					break;
				case 2:
					Question mc = createQuestion(QuestionType.multipleChoice);
					mc.setType(QuestionType.multipleChoice);
					
					//Answers
					Answer mcAnswer = new Answer();
					System.out.println("Will this Question have multiple Answers(1-yes, 0-no)?");
					int mult = in.nextInt();
					
					if(mult==1)
					{
						mcAnswer = createAnswer(AnswerType.letterArray);
					}
					else if(mult==0)
					{
						mcAnswer = createAnswer(AnswerType.letter);
					}
					
					mc.setCorrectAnswer(mcAnswer);
					
					newTest.addQuestion(mc);
					break;
				case 3:
					Question sa = createQuestion(QuestionType.shortAnswer);
					sa.setType(QuestionType.shortAnswer);
					
					//Answers
					Answer saAnswer = createAnswer(AnswerType.text);
					sa.setCorrectAnswer(saAnswer);
					
					newTest.addQuestion(sa);
					break;
				case 4:
					Question essay = createQuestion(QuestionType.essay);
					essay.setType(QuestionType.essay);
					
					//Answers
					Answer esAnswer = createAnswer(AnswerType.text);
					essay.setCorrectAnswer(esAnswer);
					
					newTest.addQuestion(essay);
					break;
				case 5:
					Question rank = createQuestion(QuestionType.ranking);
					rank.setType(QuestionType.ranking);
					
					//Answers
					Answer rankAnswer = createAnswer(AnswerType.numArray);
					rank.setCorrectAnswer(rankAnswer);
					
					newTest.addQuestion(rank);
					break;
				case 6:
					Question match = createQuestion(QuestionType.matching);
					match.setType(QuestionType.matching);
					
					//Answers
					Answer matchAnswer = createAnswer(AnswerType.letterArray);
					match.setCorrectAnswer(matchAnswer);
					
					newTest.addQuestion(match);
					break;
				case 7:
					status = false;
					break;	
			}
		}
		return newTest;
	}
	/**
	 * Displays a Test in console
	 * @param survey
	 */
	public static void displayTest(Survey survey)
	{
		ArrayList<Question> questions = new ArrayList<Question>();
		questions = survey.getQuestions();
		
		for(int i = 0; i < questions.size(); i++)
		{
			Question q = questions.get(i);
			System.out.println("Question #"+i+" : "+q.getPrompt());
			
			QuestionType qType = q.getType();
			switch(qType)
			{
				case trueFalse:
					TrueFalseMC tf = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> tfOptions = new ArrayList<String>();
					tfOptions = tf.getOptions();
						
					System.out.println("Options: "+tfOptions.toString());
					
					//Answer
					System.out.println("Answer: "+tf.getCorrectAnswer().getAnswer().toString());
					
					//User Answer
					if(tf.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+tf.getUserAnswer().getAnswer());
					}
					break;
				case multipleChoice:
					TrueFalseMC mc = (TrueFalseMC) questions.get(i);
					
					ArrayList<String> mcOptions = new ArrayList<String>();
					mcOptions = mc.getOptions();
						
					System.out.println("Options: "+mcOptions.toString());
					
					//Answer
					System.out.println("Answer: "+mc.getCorrectAnswer().getAnswer().toString());
					
					//User Answer
					if(mc.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+mc.getUserAnswer().getAnswer());
					}
					break;
				case shortAnswer:
					SAEssay sa = (SAEssay) questions.get(i);
					
					//User Answer
					if(sa.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+sa.getUserAnswer().getAnswer());
					}
					break;
				case essay:
					SAEssay es = (SAEssay) questions.get(i);
					
					//User Answer
					if(es.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+es.getUserAnswer().getAnswer());
					}
					break;
				case ranking:
					RankingMatching rank = (RankingMatching) questions.get(i);
					
					ArrayList<String> rankOptions = new ArrayList<String>();
					rankOptions = rank.getOptions();
					
					ArrayList<String> rankChoices = new ArrayList<String>();
					rankChoices = rank.getChoices();
						
					System.out.println("Options: "+rankOptions.toString());
					System.out.println("Choices: "+rankChoices.toString());
					
					//Answer
					System.out.println("Answer: "+rank.getCorrectAnswer().getAnswer().toString());
					
					//User Answer
					if(rank.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+rank.getUserAnswer().getAnswer());
					}
					break;
				case matching:
					RankingMatching match = (RankingMatching) questions.get(i);
					
					ArrayList<String> matchOptions = new ArrayList<String>();
					matchOptions = match.getOptions();
					
					ArrayList<String> matchChoices = new ArrayList<String>();
					matchChoices = match.getChoices();
						
					System.out.println("Options: "+matchOptions.toString());
					System.out.println("Choices: "+matchChoices.toString());
					
					//Answer
					System.out.println("Answer: "+match.getCorrectAnswer().getAnswer().toString());

					//User Answer
					if(match.getUserAnswer() != null)
					{
						System.out.println("UserAnswer: "+match.getUserAnswer().getAnswer());
					}
					break;		
			}
			System.out.println("");
		}
	}
	/**
	 * Edits a test object by taking in user input
	 * @param survey
	 */
	public static void editTest(Survey survey)
	{
		Scanner in = new Scanner(System.in);
		in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		
		System.out.println("What question number do you wish to modify?");
		displaySurvey(survey);
		int qMod = in.nextInt();
		
		ArrayList<Question> _questions = new ArrayList<Question>();
		_questions = survey.getQuestions();
		
		Question origQuestion = _questions.get(qMod);
		
		System.out.println("Do you wish to modify the prompt?(1-yes, 0-no)");
		int promptYN = in.nextInt();
		
		if(promptYN == 1)
		{
			System.out.println("Original: "+origQuestion.getPrompt());
			System.out.println("Enter a new prompt: ");
			String newPrompt = in.next();
			origQuestion.setPrompt(newPrompt);
			System.out.println("New Prompt has been Set");
		}
		
		System.out.println("Would you like to edit more information on the question?(1-yes, 0-no)");
		int quesYN = in.nextInt();

		if(quesYN == 1)
		{
			QuestionType qType;
			qType = origQuestion.getType();
			
			switch(qType)
			{
				case trueFalse:
					ArrayList<String> _options = new ArrayList<String>();
					TrueFalseMC tfQuestion = new TrueFalseMC();
					tfQuestion = (TrueFalseMC) origQuestion;
					
					_options = tfQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < _options.size(); i++)
					{
						System.out.println("Options #"+i+" : "+_options.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOp = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newString = in.next();
					
					tfQuestion.editOption(edOp, newString);
					break;
				case multipleChoice:
					ArrayList<String> _optionsMC = new ArrayList<String>();
					TrueFalseMC mcQuestion = new TrueFalseMC();
					mcQuestion = (TrueFalseMC) origQuestion;
					
					_optionsMC = mcQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < _optionsMC.size(); i++)
					{
						System.out.println("Options #"+i+" : "+_optionsMC.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpMC = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringMC = in.next();
					
					mcQuestion.editOption(edOpMC, newStringMC);
					break;
				case shortAnswer:
					System.out.println("Would you like to edit the new Length?(1-yes, 0-no)");
					int newSA = in.nextInt();
					
					if(newSA == 1)
					{
						System.out.println("Enter the new length for the Short Answer: ");
						int newLength = in.nextInt();
						
						SAEssay saQuestion = new SAEssay();
						saQuestion = (SAEssay) origQuestion;
						
						saQuestion.setLength(newLength);
					}
					break;
				case essay:
					System.out.println("Would you like to edit the new Length?(1-yes, 0-no)");
					int newES = in.nextInt();
					
					if(newES == 1)
					{
						System.out.println("Enter the new length for the Essay Question: ");
						int newLengthES = in.nextInt();
						
						SAEssay esQuestion = new SAEssay();
						esQuestion = (SAEssay) origQuestion;
						
						esQuestion.setLength(newLengthES);
					}
					break;
				case ranking:
					//Options
					ArrayList<String> optionsRank = new ArrayList<String>();
					RankingMatching rankQuestion = new RankingMatching();
					rankQuestion = (RankingMatching) origQuestion;
					
					optionsRank = rankQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < optionsRank.size(); i++)
					{
						System.out.println("Options #"+i+" : "+optionsRank.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpRank = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringRank = in.next();
					
					rankQuestion.editOption(edOpRank, newStringRank);
					
					//Choices
					ArrayList<String> choicesRank = new ArrayList<String>();
					choicesRank = rankQuestion.getChoices();
					
					System.out.println("Current Choices: ");
					for(int i = 0; i < choicesRank.size(); i++)
					{
						System.out.println("Choice #"+i+" : "+choicesRank.get(i));
					}
					
					System.out.println("Which choice would you like to edit?");
					int edChoice = in.nextInt();
					
					System.out.println("Enter a new String for the choice: ");
					String newChoiceRank = in.next();
					
					rankQuestion.editChoice(edChoice, newChoiceRank);
					break;
				case matching:
					//Options
					ArrayList<String> optionsMatch = new ArrayList<String>();
					RankingMatching matchQuestion = new RankingMatching();
					matchQuestion = (RankingMatching) origQuestion;
					
					optionsMatch = matchQuestion.getOptions();
					
					System.out.println("Current Options: ");
					for(int i = 0; i < optionsMatch.size(); i++)
					{
						System.out.println("Options #"+i+" : "+optionsMatch.get(i));
					}
					
					System.out.println("Which option would you like to edit?");
					int edOpMatch = in.nextInt();
					
					System.out.println("Enter a new String for the option: ");
					String newStringMatch = in.next();
					
					matchQuestion.editOption(edOpMatch, newStringMatch);
					
					//Choices
					ArrayList<String> choicesMatch = new ArrayList<String>();
					choicesMatch = matchQuestion.getChoices();
					
					System.out.println("Current Choices: ");
					for(int i = 0; i < choicesMatch.size(); i++)
					{
						System.out.println("Choice #"+i+" : "+choicesMatch.get(i));
					}
					
					System.out.println("Which choice would you like to edit?");
					int edChoiceMatch = in.nextInt();
					
					System.out.println("Enter a new String for the choice: ");
					String newChoiceMatch = in.next();
					
					matchQuestion.editChoice(edChoiceMatch, newChoiceMatch);
					
					
					
					break;
			}
			
			Answer oldCorrectAnswer = origQuestion.getCorrectAnswer();
			System.out.println("Answer: "+oldCorrectAnswer.getAnswer().toString());
			
			//Edit correct answer
			System.out.println("Would you like to edit the correct answer?(1-yes, 0-no): ");
			int newCorrect = in.nextInt();
			
			if(newCorrect == 1)
			{
				Answer newCorrectAnswer = new Answer();
				newCorrectAnswer = createAnswer(oldCorrectAnswer.getType());
				
				origQuestion.setCorrectAnswer(newCorrectAnswer);
				
				System.out.println("New answer set");
			}
			
		}
	}
	/**
	 * Taking a Survey and taking a Test use the same UI so it calls the Survey UI
	 * @param survey
	 */
	public static void takeTest(Survey survey)
	{
		takeSurvey(survey);
	}

	//Question methods
	/**
	 * Creates a new Question object by taking in user input
	 * @param questionType
	 * @return Question
	 */
	public static Question createQuestion(QuestionType questionType)
	{
		Question returnQuestion = new Question();
		
		Scanner in = new Scanner(System.in);
		in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		
		System.out.println("Enter the question prompt: ");
		String questionPrompt = in.next();
		
		switch(questionType)
		{
			case trueFalse:
				TrueFalseMC tf = new TrueFalseMC();
				tf.setPrompt(questionPrompt);
				tf.createTrueFalse();
				
				returnQuestion = tf;
				break;
			case multipleChoice:
				TrueFalseMC mc = new TrueFalseMC();
				mc.setPrompt(questionPrompt);
				
				//get num options
				System.out.println("How many choices will you have?");
				int numChoices = in.nextInt();
				
				ArrayList<String> options = new ArrayList<String>();
				for(int i = 0; i < numChoices; i++)
				{
					System.out.println("Enter choice #"+i+": ");
					String choice = in.next();
					options.add(choice);
				}
				
				mc.setOptions(options);
				
				returnQuestion = mc;
				break;
			case ranking:
				RankingMatching rank = new RankingMatching();
				rank.setPrompt(questionPrompt);
				
				//get num options
				System.out.println("How many choices will you have?");
				numChoices = in.nextInt();
				
				ArrayList<String> rankOptions = new ArrayList<String>();
				for(int i = 0; i < numChoices; i++)
				{
					System.out.println("Enter choice #"+i+": ");
					String choice = in.next();
					rankOptions.add(choice);
				}
				rank.createRanking(rankOptions);
				
				returnQuestion = rank;
				break;
			case matching:
				RankingMatching match = new RankingMatching();
				match.setPrompt(questionPrompt);
				
				//get num options
				System.out.println("How many choices will you have?");
				numChoices = in.nextInt();
				
				ArrayList<String> matchOptions = new ArrayList<String>();
				for(int i = 0; i < numChoices; i++)
				{
					System.out.println("Enter choice #"+i+": ");
					String choice = in.next();
					matchOptions.add(choice);
				}
				
				//get num options
				System.out.println("How many answers will you have?");
				numChoices = in.nextInt();
				
				ArrayList<String> matchAnswers = new ArrayList<String>();
				for(int i = 0; i < numChoices; i++)
				{
					System.out.println("Enter choice #"+i+": ");
					String choice = in.next();
					matchAnswers.add(choice);
				}
				match.setOptions(matchOptions);
				match.setChoices(matchAnswers);
				
				returnQuestion = match;
				break;
			case shortAnswer:
				SAEssay sa = new SAEssay();
				sa.setPrompt(questionPrompt);
				
				sa.createShortAnswer();
				
				returnQuestion=sa;
				break;
			case essay:
				SAEssay es = new SAEssay();
				es.setPrompt(questionPrompt);
				
				//question limit
				System.out.println("What is the limit on your essay answer?");
				int limit = in.nextInt();
				
				es.setLength(limit);
				
				returnQuestion=es;
				break;
		}
		
		return returnQuestion;
	}
	//Answer methods
	/**
	 * Creates a new Answer object by taking in user input
	 * @param type
	 * @return Answer
	 */
	public static Answer createAnswer(AnswerType type)
	{
		Scanner in = new Scanner(System.in);
		in.useDelimiter(Pattern.compile("[\\r\\n;]+"));
		
		Answer returnAnswer = new Answer();
		
		returnAnswer.setType(type);
		
		switch(type)
		{
			case text:
				System.out.println("Enter the Correct Answer: ");
				String correctAnswer = in.next();
				
				returnAnswer.setSingleAnswer(correctAnswer);
				break;
			case num:
				System.out.println("Enter the Correct Answer: ");
				correctAnswer = in.next();
				
				returnAnswer.setSingleAnswer(correctAnswer);
				break;
			case letter:
				System.out.println("Enter the Correct Answer: ");
				correctAnswer = in.next();
				
				returnAnswer.setSingleAnswer(correctAnswer);
				break;
			case numArray:
				ArrayList<String> answer = new ArrayList<String>();
				
				System.out.println("Enter amount of numbers there will be: ");
				int numArrayLength = in.nextInt();
				
				for(int i=0; i < numArrayLength; i++)
				{
					System.out.println("Enter the #"+i+" Answer: ");
					String curAnswer = in.next();
					answer.add(curAnswer);
				}
				
				returnAnswer.setAnswer(answer);
				break;
			case letterArray:
				ArrayList<String> answerLet = new ArrayList<String>();
				
				System.out.println("Enter amount of letters there will be: ");
				int letterArrayLength = in.nextInt();
				
				for(int i=0; i < letterArrayLength; i++)
				{
					System.out.println("Enter the #"+i+" Answer: ");
					String curAnswer = in.next();
					answerLet.add(curAnswer);
				}
				
				returnAnswer.setAnswer(answerLet);
				break;
				
		}
		
		return returnAnswer;
	}
	public static void editAnswer()
	{
		
	}
}
