import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;


public class Main3 {

    // Classe Hash1: Implementação de uma tabela hash simples com função de hash polinomial
    static class Hash1 {
        private final String[] table;
        private int collisions;

        public Hash1(int size) {
            table = new String[size];
            collisions = 0;
        }

        public int getHashCode(String value) {
            int hash = 0;
            int prime = 31;
            for (int i = 0; i < value.length(); i++) {
                hash = prime * hash + value.charAt(i);
            }
            return Math.abs(hash % table.length);
        }

        public void put(String value) {
            int index = getHashCode(value);
            if (table[index] != null) {
                collisions++;
            }
            table[index] = value;
        }

        public boolean contains(String value) {
            int index = getHashCode(value);
            return table[index] != null && table[index].equals(value);
        }

        public int getColisoes() {
            return collisions;
        }

        public void printHashTableWithSize() {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    System.out.println("Índice " + i + ": 1 chave(s)");
                } else {
                    System.out.println("Índice " + i + ": Nenhuma chave");
                }
            }
        }

        public Map<Integer, Integer> getColisoesPorIndice() {
            Map<Integer, Integer> colisoesPorIndice = new HashMap<>();
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    colisoesPorIndice.put(i, colisoesPorIndice.getOrDefault(i, 0) + 1);
                }
            }
            return colisoesPorIndice;
        }
    }

    // Classe Hash2: Implementação de uma tabela hash simples com função de hash baseada na soma de caracteres
    static class Hash2 {
        private final String[] table;
        private int collisions;

        public Hash2(int size) {
            table = new String[size];
            collisions = 0;
        }

        public int getHashCode(String value) {
            int hash = 0;
            for (int i = 0; i < value.length(); i++) {
                hash += value.charAt(i);
            }
            return Math.abs(hash % table.length);
        }

        public void put(String value) {
            int index = getHashCode(value);
            if (table[index] != null) {
                collisions++;
            }
            table[index] = value;
        }

        public boolean contains(String value) {
            int index = getHashCode(value);
            return table[index] != null && table[index].equals(value);
        }

        public int getColisoes() {
            return collisions;
        }

        public void printHashTableWithSize() {
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    System.out.println("Índice " + i + ": 1 chave(s)");
                } else {
                    System.out.println("Índice " + i + ": Nenhuma chave");
                }
            }
        }

        public Map<Integer, Integer> getColisoesPorIndice() {
            Map<Integer, Integer> colisoesPorIndice = new HashMap<>();
            for (int i = 0; i < table.length; i++) {
                if (table[i] != null) {
                    colisoesPorIndice.put(i, colisoesPorIndice.getOrDefault(i, 0) + 1);
                }
            }
            return colisoesPorIndice;
        }
    }

    public static void main(String[] args) {
        int indexes = 600;
        var hash1 = new Hash1(indexes);  // Hash1 é a implementação do polinomial
        var hash2 = new Hash2(indexes);  // Hash2 é a implementação do LoseLose

        String caminhoArquivo = "src/female_names.txt"; // Caminho do arquivo de entrada

        long startTime, endTime;
        long totalInsertionTimeHash1 = 0, totalInsertionTimeHash2 = 0;
        long totalSearchTimeHash1 = 0, totalSearchTimeHash2 = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Inserção e medição de tempo para Hash1
                startTime = System.nanoTime();
                hash1.put(linha);
                endTime = System.nanoTime();
                totalInsertionTimeHash1 += (endTime - startTime);

                // Inserção e medição de tempo para Hash2
                startTime = System.nanoTime();
                hash2.put(linha);
                endTime = System.nanoTime();
                totalInsertionTimeHash2 += (endTime - startTime);

                // Busca e medição de tempo para Hash1
                startTime = System.nanoTime();
                hash1.contains(linha);
                endTime = System.nanoTime();
                totalSearchTimeHash1 += (endTime - startTime);

                // Busca e medição de tempo para Hash2
                startTime = System.nanoTime();
                hash2.contains(linha);
                endTime = System.nanoTime();
                totalSearchTimeHash2 += (endTime - startTime);

                count++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        // Calculando o tempo médio de inserção e busca
        double avgInsertionTimeHash1 = (double) totalInsertionTimeHash1 / count;
        double avgInsertionTimeHash2 = (double) totalInsertionTimeHash2 / count;
        double avgSearchTimeHash1 = (double) totalSearchTimeHash1 / count;
        double avgSearchTimeHash2 = (double) totalSearchTimeHash2 / count;

        // Exibindo resultados
        System.out.printf("Colisoes da Funcao Hash1 (Polinomial): %d%n", hash1.getColisoes());
        System.out.printf("Colisoes da Funcao Hash2 (LoseLose): %d%n", hash2.getColisoes());

        System.out.printf("Tempo total de inserção (Hash1): %.3f ms%n", totalInsertionTimeHash1 / 1_000_000.0);
        System.out.printf("Tempo total de inserção (Hash2): %.3f ms%n", totalInsertionTimeHash2 / 1_000_000.0);

        System.out.printf("Tempo total de busca (Hash1): %.3f ms%n", totalSearchTimeHash1 / 1_000_000.0);
        System.out.printf("Tempo total de busca (Hash2): %.3f ms%n", totalSearchTimeHash2 / 1_000_000.0);

        // Exibindo tabelas de colisões por índice no terminal
        System.out.println("Tabela Hash Hash1 (Número de chaves por índice):");
        hash1.printHashTableWithSize();
        System.out.println("Tabela Hash Hash2 (Número de chaves por índice):");
        hash2.printHashTableWithSize();
    }
}
