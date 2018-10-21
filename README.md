# Cody Rhodes

## Ohio State University Student

### Skills
- C#, C++
- Java
- Python
- Database Management

### Projects

[String Reassembly](https://github.com/Rhodes355/Portfolio/blob/master/StringReassembly.java)

[Map With Hashing](https://github.com/Rhodes355/Portfolio/blob/master/MapWithHashing.java)

[Expression Tree](https://github.com/Rhodes355/Portfolio/blob/master/ExpressionTree.java)

### Current Project
[Pong](https://github.com/Rhodes355/Pong)

I am working with a fellow OSU student on a version of Pong developed in C# using MonoGame. The goal of this collaboration is to practice software design and working with git on a team project. Currently we have some basic controls and a method for initializing the ball position and a random velocity for the ball to start the game. This section will be updated as the project continues.

### Example Code
This code example is from a school project named [Map With Hashing](https://github.com/Rhodes355/Portfolio/blob/master/MapWithHashing.java), in which I worked with a partner to implement a map class using hashing.
```markdown
@Override
    public final void add(K key, V value) {
        assert key != null : "Violation of: key is not null";
        assert value != null : "Violation of: value is not null";
        assert !this.hasKey(key) : "Violation of: key is not in DOMAIN(this)";

        int hashCode = key.hashCode();
        int bucketNumber = mod(hashCode, this.hashTable.length());

        Map<K, V> storage = this.hashTable.entry(bucketNumber);
        storage.add(key, value);
        this.hashTable.setEntry(bucketNumber, storage);

    }
```

Example Python code that takes a rectangle of format ( (min X, max X), (min Y, max Y) ) and returns an array of points within the rectangle.
```markdown
def search_all(points, rect):
    found = []
    for p in points:
        x,y = p.x, p.y
        if not(rect[0][0]>x or rect[0][1]<x or rect[1][0]>y or rect[1][1]<y):
            found.append(p)
    return found
```
C# example showing a function to return the factorial of a number (ex. 4! = 24). Could be done recursively however recursive functions are generally less readable in my experience and slower than iterative solutions in many cases.
```markdown
public static int FirstFactorial(int num) { 
  
    int factorial = 1;
    for (int i = 1;i <= num;i++){
        factorial = factorial * i;
    }

    return factorial;
            
  }
```
Java code from project [Expression Tree](https://github.com/Rhodes355/Portfolio/blob/master/ExpressionTree.java). This snippet of code is a function that takes a string expression of the format "5 10 15 - * 7 +" and creates a tree capable of returning "5 * 10 - 15 + 7". Other functions in the project can convert to prefix, infix, or postfix notation.

```markdown
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
```

### Contact

Email: Rhodes.355@osu.edu
