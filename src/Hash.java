public abstract class Hash {
    protected String[] table;
    protected int colisao;
    protected int size;

    public Hash(int size) {
        this.size = size;
        this.table = new String[size];
        this.colisao = 0;
    }

    public abstract int hashFunction(String key);

    public void insert(String key) {
        int hash = hashFunction(key);
        if (table[hash] != null) {
            colisao++;
        }
        table[hash] = key;
    }

    public void printReport(String functionName) {
        System.out.println("Report for " + functionName);
        System.out.println("Numero de colisao: " + colisao);
        int slotOcupado = 0;
        for (String s : table) {
            if (s != null) slotOcupado++;
        }
        System.out.println("Total (slots ocupados) " + slotOcupado);
    }
}
    