public class HashSecond extends Hash {
    public HashSecond(int size) {
        super(size);
    }

    @Override
    public int hashFunction(String key) {
        int hash = 7;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 37 + key.charAt(i)) % size;
        }
        return hash;
    }
}
    