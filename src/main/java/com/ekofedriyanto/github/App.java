package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
		Command sumCommand = new SumCommand();
		Command multiplyCommand = new MultiplyCommand();
		Command primeNumberCommand = new PrimeNumberCommand();
		Command fibonacciCommand = new FibonacciCommand();
		Command exitCommand = new ExitCommand();

		List<Command> commands = new ArrayList<>();
		commands.add(sumCommand);
		commands.add(multiplyCommand);
		commands.add(primeNumberCommand);
		commands.add(fibonacciCommand);
		commands.add(exitCommand);

		CommandControl commandControl = new CommandControl(commands);

        commandControl.printMenu();
    }
}
