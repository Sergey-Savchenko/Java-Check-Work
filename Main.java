import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

public class Main {
    public static Toy toy;
    public static void main(String[] args) {

        List<Toy> toysList = new ArrayList<>();
        toysList.add(new Toy(1, "Robocop", 5));
        toysList.add(new Toy(2, "Candyland", 3));
        toysList.add(new Toy(3, "Deathstar", 1));

        int ids[] = new int[toysList.size()];
        ids = fillIds(toysList);

        String names[] = new String[toysList.size()];
        names = fillNames(toysList);

        int frequencys[] = new int[toysList.size()];
        frequencys = fillFrequencies(toysList);
        
        Queue<Toy> toyQueue = new PriorityQueue<>();
        toyQueue = makeQueue(toysList);

        // Queue<Toy> commonQueue = new LinkedList<>();

        // while (!toyQueue.isEmpty()) {
        //     commonQueue.offer(toyQueue.poll());
        // }

        saveIntoFile(toyQueue);

    }

    public static int[] fillIds(List<Toy> toysList) {
        int[] ids = new int[toysList.size()];
        for (int i = 0; i < toysList.size(); i++) {
            Toy toy = toysList.get(i);
            ids[i] = toy.getId();
        }
        return ids;
    }

    public static String[] fillNames(List<Toy> toysList) {
        String[] ids = new String[toysList.size()];
        for (int i = 0; i < toysList.size(); i++) {
            Toy toy = toysList.get(i);
            ids[i] = toy.getName();
        }
        return ids;
    }

    public static int[] fillFrequencies(List<Toy> toysList) {
        int[] ids = new int[toysList.size()];
        for (int i = 0; i < toysList.size(); i++) {
            Toy toy = toysList.get(i);
            ids[i] = toy.getFrequency();
        }
        return ids;
    }

    public static Queue<Toy> makeQueue(List<Toy> toysList) {
        Queue<Toy> queue = new PriorityQueue<>(10, Comparator.comparingInt(Toy::getFrequency));
        int j = 0;
        for (int i = 0; i < 10; i++) {
            if (j == toysList.size()) {
                j = j - 3;
            }
            Toy toy = toysList.get(j);
            queue.offer(toysList.get(j));
            System.out.println(toy.getId());
            j++;
        }
        return queue;
    }

    public static void saveIntoFile(Queue<Toy> toyQueue) {
        try (PrintWriter writer = new PrintWriter(new File("result.txt"))) {
            for (int i = 0; i < 10; i++) {
                Toy toy = toyQueue.peek();
                if (toy != null) {
                    writer.println(toyQueue.poll().getId());
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}