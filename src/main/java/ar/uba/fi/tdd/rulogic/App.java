package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;
import ar.uba.fi.tdd.rulogic.model.Parser;

import java.util.Scanner;


/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args)  {
		KnowledgeBase kb = new KnowledgeBase("rules.db");
		System.out.println("I shall answer all your questions!");
		System.out.println("To quit press *");
		String text = null;
		while (true){
			System.out.println("Make your question: \n");
			Scanner scan= new Scanner(System.in);
			text= scan.nextLine();
			if (text.compareTo("*") == 0) break;
			System.out.println(kb.answer(text));
		}

	}
}
