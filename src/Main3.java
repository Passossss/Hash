import TbHash.Hash2;
import TbHash.Hash1;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Main3 {
    public static void main(String[] args) {
        int indexes = 600;
        var Hash1 = new Hash1(indexes);
        var Hash2 = new Hash2(indexes);

        String caminhoArquivo = "C:/Users/Gustavo Passos/Desktop/Hash/src/female_names.txt";

        long startTime, endTime;
        long totalInsertionTimeHash1 = 0, totalInsertionTimeHash2 = 0;
        long totalSearchTimeHash1 = 0, totalSearchTimeHash2 = 0;
        int count = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                startTime = System.nanoTime();
                Hash1.put(linha);
                endTime = System.nanoTime();
                totalInsertionTimeHash1 += (endTime - startTime);

                startTime = System.nanoTime();
                Hash2.put(linha);
                endTime = System.nanoTime();
                totalInsertionTimeHash2 += (endTime - startTime);

                startTime = System.nanoTime();
                Hash1.contains(linha);
                endTime = System.nanoTime();
                totalSearchTimeHash1 += (endTime - startTime);

                startTime = System.nanoTime();
                Hash2.contains(linha);
                endTime = System.nanoTime();
                totalSearchTimeHash2 += (endTime - startTime);

                count++;
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }

        double avgInsertionTimeHash1 = (double) totalInsertionTimeHash1 / count;
        double avgInsertionTimeHash2 = (double) totalInsertionTimeHash2 / count;
        double avgSearchTimeHash1 = (double) totalSearchTimeHash1 / count;
        double avgSearchTimeHash2 = (double) totalSearchTimeHash2 / count;

        System.out.printf("Colisoes da Funcao Hash Hash1: %d%n", Hash1.getColisoes());
        System.out.printf("Colisoes da Funcao Hash Hash2: %d%n", Hash2.getColisoes());

        System.out.printf("Tempo total de inserção (Hash1): %.3f ms%n", totalInsertionTimeHash1 / 1_000_000.0);
        System.out.printf("Tempo total de inserção (Hash2): %.3f ms%n", totalInsertionTimeHash2 / 1_000_000.0);

        System.out.printf("Tempo total de busca (Hash1): %.3f ms%n", totalSearchTimeHash1 / 1_000_000.0);
        System.out.printf("Tempo total de busca (Hash2): %.3f ms%n", totalSearchTimeHash2 / 1_000_000.0);

        Map<Integer, Integer> colisaoHash1 = Hash1.getColisoesPorIndice();
        JFrame frameHash1 = new JFrame("Gráfico de Colisões Hash1");
        frameHash1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHash1.setSize(800, 600);
        frameHash1.add(new GraficoColisoesPanel(colisaoHash1, "Colisões por Índice - Hash1"));
        frameHash1.setVisible(true);

        Map<Integer, Integer> colisaoHash2 = Hash2.getColisoesPorIndice();
        JFrame frameHash2 = new JFrame("Gráfico de Colisões Hash2");
        frameHash2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameHash2.setSize(800, 600);
        frameHash2.add(new GraficoColisoesPanel(colisaoHash2, "Colisões por Índice - Hash2"));
        frameHash2.setVisible(true);

        System.out.println("Tabela Hash Hash1 (Número de chaves por índice):");
        Hash1.printHashTableWithSize();
        System.out.println("Tabela Hash Hash2 (Número de chaves por índice):");
        Hash2.printHashTableWithSize();
    }
}

class GraficoColisoesPanel extends JPanel {
    private final Map<Integer, Integer> colisoes;
    private final String titulo;

    public GraficoColisoesPanel(Map<Integer, Integer> colisoes, String titulo) {
        this.colisoes = colisoes;
        this.titulo = titulo;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int barWidth = (width - 2 * padding) / colisoes.size();

        g2d.drawString(titulo, width / 2 - 50, padding / 2);

        int maxColisoes = colisoes.values().stream().mapToInt(Integer::intValue).max().orElse(1);


        g2d.setColor(Color.BLACK);
        for (int i = 0; i <= 10; i++) { 
            int y = height - padding - (i * (height - 2 * padding) / 10);
            int colisoesValor = (int) ((double) maxColisoes * i / 10);
            g2d.drawString(String.valueOf(colisoesValor), padding - 30, y);
            g2d.drawLine(padding - 5, y, padding, y); 
        }

        int x = padding;
        for (Map.Entry<Integer, Integer> entry : colisoes.entrySet()) {
            int numColisoes = entry.getValue();

            int barHeight = (int) ((double) numColisoes / maxColisoes * (height - 2 * padding));

            g2d.setColor(Color.BLUE);
            g2d.fillRect(x, height - padding - barHeight, barWidth, barHeight);

            x += barWidth + 5;
        }
    }
}
