package cz.spsmb.secda1.jokes.jokes_fx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class JokeService {

    private List<Joke> jokes = new LinkedList<>();

    public Joke getRandomJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.size());
        return jokes.get(index);
    }

    public List<Joke> getJokes() {
        return jokes;
    }

    public int getNextId(){
        return jokes.size()+1;
    }

    public void loadJokesFromCSVFile() {
        Scanner sc = new Scanner(this.getClass().getResourceAsStream("/shortjokes.csv"));
        sc.useDelimiter("\n");
        while (sc.hasNext()) {
            String line = sc.next();
            int delimiter = line.indexOf(',');
            Long id = Long.parseLong(line.substring(0, delimiter));
            String text = line.substring(delimiter+2, line.length()-2);
            jokes.add(new Joke(id, text));
        }
        sc.close();
    }

    public void saveData() throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("R:\\verejne_vsichni\\PRO\\jokes_FX\\src\\main\\resources\\shortjokes.csv"));
        for (Joke joke: jokes) {
            bufferedWriter.write(joke.getId() + "," + '"' + joke.getText() + '"' + "\n");
        }
        bufferedWriter.close();
    }

    public void saveJoke(Joke joke) throws IOException {
        jokes.add(joke);
        saveData();

    }
}