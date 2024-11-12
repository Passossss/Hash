public class HashFirst extends Hash {
    public HashFirst(int size) {
        super(size);
    }

    @Override
    public int hashFunction(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (31 * hash + key.charAt(i)) % size;
        }
        return hash;
    }
}
    