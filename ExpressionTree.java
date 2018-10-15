import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

import osu.cse2123.TreeNode;

public class ExpressionTree {

	public static void main(String[] args) {
		TreeNode<String> tree = null;
		String pref = "p";
		String expr = "";
		String input = "";
		Scanner in = new Scanner(System.in);
		
		if (expr.isEmpty()){
			System.out.println("No expression in memory \n");
		} else {
			System.out.println("Expression: " + expr);
		}
		
		while (!input.toLowerCase().equals("q")){
			System.out.println("\nEnter your choice: ");
			System.out.println("[S]et the display format");
			System.out.println("[E]nter a new expression");
			System.out.println("[Q]uit");
			input = in.nextLine();
			
			if (!input.toLowerCase().equals("q")){
				if (input.toLowerCase().equals("e")){
					while (tree == null){
						System.out.println("Enter your expression in postfix notation: ");
						expr = in.nextLine();
						tree = buildTreeFromString(expr);
						if (tree == null){
							System.out.println("ERROR: Please enter expression in postfix notation");
							System.out.println("Enter your expression in postfix notation: ");
							expr = in.nextLine();
						}
					}
					
					if (pref.toLowerCase().equals("p")){
						System.out.println("\n" + toPostfixString(tree) + " = " + evaluate(tree));
					} else if (pref.toLowerCase().equals("i") ){
						System.out.println("\n" + toInfixString(tree) + " = " + evaluate(tree));
					} else if (pref.toLowerCase().equals("r")){
						System.out.println("\n" + toPrefixString(tree) + " = " + evaluate(tree));
					}
					
				} else if (input.toLowerCase().equals("s") && !expr.isEmpty()){
					
					while (!input.toLowerCase().equals("p") && 
							!input.toLowerCase().equals("i") && 
							!input.toLowerCase().equals("r")){
						System.out.println("\nEnter your preferred output display: ");
						System.out.println("[P]ostfix");
						System.out.println("[I]nfix");
						System.out.println("p[R]efix");
						input = in.nextLine();
						
						if (!input.toLowerCase().equals("p") && 
							!input.toLowerCase().equals("i") && 
							!input.toLowerCase().equals("r")){
							System.out.println("ERROR: Please enter valid choice [P], [I], [R]");
						}
					} // end while
					
					if (input.toLowerCase().equals("p")){
						pref = "p";
						System.out.println("\n" + toPostfixString(tree) + " = " + evaluate(tree));
					} else if (input.toLowerCase().equals("i") ){
						pref = "i";
						System.out.println("\n" + toInfixString(tree) + " = " + evaluate(tree));
					} else if (input.toLowerCase().equals("r")){
						pref = "r";
						System.out.println("\n" + toPrefixString(tree) + " = " + evaluate(tree));
					}
					
				} else if (input.toLowerCase().equals("s") && expr.isEmpty()) {
					System.out.println("\nError: No expression in memory. Please enter expression");
				} else {
					System.out.println("ERROR: Please enter valid choice [E], [S], [Q]");
				}
			} 
			// This point reached if Input = q
		}
		
		System.out.println("\nGoodbye");
		in.close();
	}
	
	public static TreeNode<String> buildTreeFromString(String expr){
		String[] exprArr = expr.split("\\s+");
		Stack<TreeNode<String>> exprStack = new Stack<TreeNode<String>>();
		String[] ops = {"+", "-", "*", "/", "%"};
		
		for (String s : exprArr){
			if (Arrays.asList(ops).contains(s)){ // If it is an operator
				TreeNode<String> t = new TreeNode<String>(s);
				TreeNode<String> right;
				TreeNode<String> left;
				if (exprStack.size() < 2){ // Need 2 nodes to pop
					return null;
				} else {
					right = exprStack.pop();
					left = exprStack.pop();
				}
				t.setRightChild(right);
				t.setLeftChild(left);
				exprStack.push(t);
			} else {
				TreeNode<String> t = new TreeNode<String>(s);
				exprStack.push(t);
			}
			
		}
		
		if (exprStack.size() > 1){ // Improper formatting. Return False
			return null;
		}
		
		
		return exprStack.pop();
	}
	
	public static String toPostfixString(TreeNode<String> expr){
		TreeNode<String> right;
		TreeNode<String> left;
		String fin = "";
		
		if (expr != null){
			
			if (expr.getLeftChild() != null){
				left = expr.getLeftChild();
				fin += toPostfixString(left);
			} else {fin = expr.getData();}
			
			if (expr.getRightChild() != null){
				right = expr.getRightChild();
				fin += " " + toPostfixString(right);
				fin += " " + expr.getData();
			}
		}
		
		return fin;
	}
	
	public static String toInfixString(TreeNode<String> expr){
		TreeNode<String> right;
		TreeNode<String> left;
		String fin = "";
		
		if (expr != null){
			
			if (expr.getLeftChild() != null){
				left = expr.getLeftChild();
				fin += "(" + toInfixString(left);
			} else {fin = expr.getData();}
			
			if (expr.getRightChild() != null){
				right = expr.getRightChild();
				fin += " " + expr.getData();
				fin += " " + toInfixString(right) + ")";
			}
		}
		
		return fin;
	}
	
	public static String toPrefixString(TreeNode<String> expr){
		TreeNode<String> right;
		TreeNode<String> left;
		String fin = expr.getData();;
		
		if (expr != null){
			
			if (expr.getLeftChild() != null){
				left = expr.getLeftChild();
				fin += " " + toPrefixString(left);
			} else {fin = expr.getData();}
			
			if (expr.getRightChild() != null){
				right = expr.getRightChild();
				fin += " " + toPrefixString(right);
			}
		}
		
		return fin;
	}
	
	public static int evaluate(TreeNode<String> expr){
		String[] ops = {"+", "-", "*", "/", "%"};
		String s = expr.getData();
		int value = 0;
		
		if (Arrays.asList(ops).contains(s)){ // If it is an operator
			int left = evaluate(expr.getLeftChild());
			int right = evaluate(expr.getRightChild());
			
			if (s.equals("+")){value = left + right;
			} else if (s.equals("-")){value = left - right;
			} else if (s.equals("*")){value = left * right;
			} else if (s.equals("/")){value = left / right;
			} else if (s.equals("%")){value = left % right;}
		} else {
			value = Integer.parseInt(s);
		}
		
		return value;
	}

}
