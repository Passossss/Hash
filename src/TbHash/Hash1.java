package TbHash;

public class Hash1 extends TabelaHash {

    public Hash1(int length) {
        super(length);
    }

    @Override
    public int getHashCode(String value) {
        var hash = 0;
        for (int i = 0; i < value.length(); i++) {
            hash += value.charAt(i);
        }
        return hash;
    }
}
