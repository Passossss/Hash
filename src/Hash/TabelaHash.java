package Hash;

public class TabelaHash {
    private int[] tabela;

    public TabelaHash() {
        tabela = new int[10];
    }

    private int hashFunc(int chave) {
        return chave % 10;
    }

    public void inserir(int chave) {
        int posicao = hashFunc(chave);
        tabela[posicao] = chave;
    }

        public boolean buscar(int chave) {
        int posicao = hashFunc(chave);
        return tabela[posicao] == chave;
    }

        public void remover(int chave) {
        int posicao = hashFunc(chave);
        if (tabela[posicao] == chave) {
            tabela[posicao] = 0;
        }
    }

        public void imprimir() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.println("Posição " + i + ": " + tabela[i]);
        }
    }

    public static void main(String[] args) {
        TabelaHash tabela = new TabelaHash();

        int[] valores = {12, 25, 37, 41, 53, 64, 78, 89};

                for (int valor : valores) {
            tabela.inserir(valor);
        }


        System.out.println("Tabela após inserções:");
        tabela.imprimir();

        System.out.println("\nBuscando os valores:");
        for (int valor : valores) {
            System.out.println("Buscar " + valor + ": " + tabela.buscar(valor));
        }

        tabela.remover(25);
        tabela.remover(89);

        System.out.println("\nTabela após remoções de 25 e 89:");
        tabela.imprimir();
    }
}

