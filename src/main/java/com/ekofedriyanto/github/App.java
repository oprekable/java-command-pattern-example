package com.ekofedriyanto.github;

import com.ekofedriyanto.github.command.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    @SuppressWarnings("InfiniteLoopStatement")
	public static void main(String[] args )
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

		final InputStream in = System.in;
		final Scanner scanner = new Scanner(in);

		while (true) {
			commandControl.printMenu();
			String menuString = scanner.nextLine();
			commandControl.executeCommand(menuString, in);
		}
    }
}
