/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author fetiveau
 */
public class Game {

	private static final String END_OF_THE_LINE_CHAR = ";";
	
	private final List<Location> locations = new ArrayList<>();
	private Hero hero;
	
	public Game()
	{
		locations.add(new Location("Room A", "This is an empty room"));
		locations.add(new Location("Room B", "This is an empty room"));
    	
        String HeroName = "Ardbert";
        int heroHP = 15;
        int heroAttack = 6;
        this.hero = new Hero(HeroName, heroHP, heroAttack, locations.get(0));
	}
	
	public String[] parsedInput(Scanner scanner)
	{
		String[] commandAndArgs = {"", "", ""};
		
		int i = 0;
		boolean isLineFinished = false;
		while (!isLineFinished)
		{
			// We get the next word
			String currentWord = scanner.next();
			
			// We're splitting the word if there is the END_OF_THE_LINE_CHAR
			String[] splitedString = currentWord.split(END_OF_THE_LINE_CHAR);
			
			// If there is enough room in commandAndArgs AND splitedString is not 0 (which happen if currentWord = END_OF_THE_LINE_CHAR)
			// We add the word to the commandAndArgs array
			if (i < commandAndArgs.length && splitedString.length != 0)
				commandAndArgs[i] = splitedString[0];			
			
			// We verify if there was the END_OF_THE_LINE_CHAR 
			// If splitedString.length == 0 then the END_OF_THE_LINE_CHAR was there alone
			// If !currentWord.equals(splitedString[0]) then the END_OF_THE_LINE_CHAR was somewhere in the word
			isLineFinished = (splitedString.length == 0 || !currentWord.equals(splitedString[0]));		
			i++;
		}

		return commandAndArgs;
	}
	
	public void gameStart() throws Exception
	{
		boolean isFinished = false;
		Scanner scanner = new Scanner(System.in);
        
		System.out.println("A new adventure begins.");
		
        while (!isFinished)
        {
        	System.out.println("Please input a command : ");
        	String[] commandAndArgs = parsedInput(scanner); 
        	
        	try
        	{
        		hero.doTheCommand(commandAndArgs);
			}
			catch(IncorrectCommandException err)
        	{
				System.out.println("This command doesn't exist.");
			}
        	
        	isFinished = hero.getIsGameFinished();
        }
        
        System.out.println("The game ends there.");
        
        scanner.close();
	}

    public static void main(String[] args) throws Exception
    {
    	Game game = new Game();
    	game.gameStart();
    }
    
}
