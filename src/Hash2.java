public class Hash2 extends TabelaHash {

    public Hash2(int length) {
        super(length);
    }

    @Override
    public int getHashCode(String value) {
        int hash = 0;
        int prime = 31;

        for (int i = 0; i < value.length(); i++) {
            hash = prime * hash + value.charAt(i);
        }

        return Math.abs(hash);
    }
}
