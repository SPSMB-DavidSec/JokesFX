package cz.spsmb.secda1.jokes.jokes_fx;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> jokesListView;

    JokeService jokeService;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(jokeService.getRandomJoke().getText());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jokeService = new JokeService();
        jokeService.loadJokesFromCSVFile();

        jokesListView.setItems(FXCollections.observableList(jokeService.getJokes().stream().map(Joke::getText)
                .collect(Collectors.toList())));
    }
}