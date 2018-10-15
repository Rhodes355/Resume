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

This code example is from a school project named [MapWithHashing](https://github.com/Rhodes355/Portfolio/blob/master/MapWithHashing.java). In which I worked with a partner to implement a map class using hashing.


### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/Rhodes355/Portfolio/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
