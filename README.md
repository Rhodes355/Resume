# Cody Rhodes

## Ohio State University Student

### Skills
- C#, C++
- Java
- Python
- Database Management

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
### Projects

[String Reassembly](https://github.com/Rhodes355/Portfolio/blob/master/StringReassembly.java)

[Map With Hashing](https://github.com/Rhodes355/Portfolio/blob/master/MapWithHashing.java)

### Contact

Email: Rhodes.355@osu.edu
