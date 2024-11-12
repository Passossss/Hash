import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public abstract class TabelaHash {
    private final LinkedList<String>[] arr;
    private Integer colisoes = 0;

    public TabelaHash(int length) {
        this.arr = new LinkedList[length];
    }

    public boolean put(String value) {
        if (value != null) {
            var hash = this.getHashCode(value);
            var position = hash % this.getLength();

            if (this.arr[position] == null) {
                this.arr[position] = new LinkedList<>();
            } else {
                this.colisoes += 1;
            }

            this.arr[position].add(value);
            return true;
        }
        return false;
    }

    public abstract int getHashCode(String value);

    public int getColisoes() {
        return this.colisoes;
    }

    public boolean contains(String value) {
        int hash = this.getHashCode(value);
        int position = hash % this.getLength();

        if (this.arr[position] != null) {
            return this.arr[position].contains(value);
        }
        return false;
    }

    public void printHashTableWithSize() {
        for (int i = 0; i < this.getLength(); i++) {
            if (this.arr[i] != null) {
                System.out.printf("Índice %d: %d chave(s)%n", i, this.arr[i].size());
            } else {
                System.out.printf("Índice %d: Nenhuma chave%n", i);
            }
        }
    }

    private int getLength() {
        return this.arr.length;
    }

    public String getHashTableAsString() {
        return Arrays.toString(this.arr);
    }

    public Map<Integer, Integer> getColisoesPorIndice() {
        Map<Integer, Integer> colisaoPorIndice = new HashMap<>();
        for (int i = 0; i < this.arr.length; i++) {
            if (this.arr[i] != null) {
                colisaoPorIndice.put(i, this.arr[i].size() - 1);
            }
        }
        return colisaoPorIndice;
    }


}
