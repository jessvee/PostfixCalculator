import java.util.Scanner;
public class infixPostfixCalc {
	/**A program that converts mathematical expressions entered by the user in infix form,
	 * changes it to postfix, and then simplifies it to a number, and prints it.
	 * 
	 * Preconditions:
	 * The string is a syntactically correct infix expression.
	 * No unary operators are present. 
	 * No exponentiation operators are present.
	 * Parenthesis cannot stand in for a multiplication sign, and are only 
	 * 		used to set precedence within the expression.
	 * 
	 * Postconditions: 
	 * A simplified answer will be printed to the screen. The answer will be of type double,
	 * unless all numbers in the entered expression are of type int, in which case so will be
	 * the answer.
	 */
	public static void main(String[] args) {
	//getting the statement from the user:
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("Choose a mathematical expression with a space between each operator"
				+ " and operand. For example, ( 2 + 3 ) * 10 - 1.");
		System.out.print("Enter that expression, here: ");
		String postfix = "";
		String infix = keyboard.nextLine();
		keyboard.close();
	//processing the statement from infix to postfix form:
		String[] infixChars = infix.split(" ");
		
		Stack operators = new Stack();
		
		for (int j = 0; j < infixChars.length; j++)
		{
			String nextChar = infixChars[j];
			
			switch (nextChar)
			{
				case ("*"):
				case ("/"):
				case ("+"): 
				case ("-"):
				{
					String prevChar = (String)operators.peek();
					
						while (!operators.isEmpty() && !isPrecedent(nextChar, prevChar))
						{
							//if (!prevChar.equals("(")) no change with the addition of this statement
								postfix += operators.pop() + " ";
						}
					operators.push(nextChar);
					prevChar = (String)operators.peek();
					break;
				}
				case ("("): 
					operators.push(nextChar);
					break;
				case (")"):
					while (!operators.isEmpty() && !operators.peek().equals("("))
					{
						postfix = postfix + operators.pop()+" ";
					}
					operators.pop(); //removing open parenthesis
					break; 
				
				default: //if it is not an above case, it is a number (int or double)
					postfix = postfix + nextChar + " ";
					break;			
			}
		}
		
		while (!operators.isEmpty())
		{
			postfix = postfix + operators.pop() + " ";
		}
		System.out.println("The resulting postfix is: " + postfix); //want abcd*+e/- current: 1234*+-5/, it looks as if the 
																		//last parenthesis is not terminating the popping of op's
	//calculating from the resulting postfix:
		
		String[] postfixChars = postfix.split(" ");

		Stack operands = new Stack();
		double result = -999;
		
		for (int j = 0; j < postfixChars.length; j++)
		{
			String nextChar = postfixChars[j];
			
			switch (nextChar)
			{
				case ("*"):
				{
					
					double op1 = (double)operands.pop();
					double op2 = (double)operands.pop();
							//(int)operands.pop();
					//int op2 = (int)operands.pop();
					
					result = op1 * op2;
					operands.push(result);
					break;
				}
				case ("/"):
				{

					double op1 = (double)operands.pop();
					double op2 = (double)operands.pop();
					
					result = op2 / op1;
					operands.push(result);
					break;
				}
				case ("+"): 
				{

					double op1 = (double)operands.pop();
					double op2 = (double)operands.pop();
					
					result = op1 + op2;
					operands.push(result);
					break;
				}
				case ("-"):
				{
					
					double op1 = (double)operands.pop();
					double op2 = (double)operands.pop();
					
					result = op2 - op1;
					operands.push(result);
					break;
				}
							
				default: //if it is not an above case, it is a number (int or double
					operands.push(Double.parseDouble(nextChar));
					break;			
			}
		}
		System.out.println("The result is " + result);
	}
	public static boolean isPrecedent(String op1, String op2)
	{
		int prec1 = 0;
		int prec2 = 0;
		switch (op1)
		{
		case("*"):
		case("/"):
			prec1 = 2;
			break;
		case("+"):
		case("-"):
			prec1 = 1;
			break;
		default:
			prec1 = 0; //might want to change this so it isn't default
						//but instead is for parenthesis with same prec
			break;
		}
		
		switch(op2)
		{
			case("*"):
			case("/"):
				prec2 = 2;
				break;
			case("+"):
			case("-"):
				prec2 = 1;
				break;
			default:
				prec2 = 0; //might want to change this so it isn't default
								//but instead is for parenthesis with same prec
				break;
		}
		return (prec1 >= prec2);
	}
}
