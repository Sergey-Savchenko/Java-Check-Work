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
        System.out.println(ids);

        String names[] = new String[toysList.size()];
        names = fillNames(toysList);

        int frequencys[] = new int[toysList.size()];
        frequencys = fillFrequencies(toysList);
        
        Queue<Toy> toyQueue = new PriorityQueue<>();
        toyQueue = makeQueue(toysList);

        Queue<Toy> commonQueue = new LinkedList<>();

        while (!toyQueue.isEmpty()) {
            commonQueue.offer(toyQueue.poll());
        }

        saveIntoFile(commonQueue);

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
        Queue<Toy> queue = new PriorityQueue<>(toysList.size(), Comparator.comparingInt(Toy::getFrequency));
        for (int i = 0; i < toysList.size(); i++) {
            queue.offer(toysList.get(i));
        }
        return queue;
    }

    public static void saveIntoFile(Queue<Toy> commonqueue) {
        try (PrintWriter writer = new PrintWriter(new File("result.txt"))) {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int randomNumber = random.nextInt() * 10;
                Toy toy = commonqueue.peek();
                if (toy != null && randomNumber < toy.getFrequency()) {
                    writer.println(commonqueue.poll().getId());
                } else {
                    writer.println("1");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}