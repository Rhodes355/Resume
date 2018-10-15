# Cody Rhodes

## Ohio State University Student

### Skills
- C#, C++
- Java
- Python
- Database Management

### Example Code

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

This code example is from a school project named [Map With Hashing](https://github.com/Rhodes355/Portfolio/blob/master/MapWithHashing.java). In which I worked with a partner to implement a map class using hashing.


### Other Projects

[String Reassembly] (https://github.com/Rhodes355/Portfolio/blob/master/StringReassembly.java)

### Contact

Email: Rhodes.355@osu.edu
