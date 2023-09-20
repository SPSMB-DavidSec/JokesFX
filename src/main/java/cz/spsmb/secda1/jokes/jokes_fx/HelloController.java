package cz.spsmb.secda1.jokes.jokes_fx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private ListView<String> jokesListView;

    @FXML
    private TextField searchTextFiled;

    @FXML
    private TextArea jokeText;


    JokeService jokeService;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText(jokeService.getRandomJoke().getText());
    }

    @FXML
    protected void save(ActionEvent event) throws IOException {
       String text = jokeText.getText();
        System.out.println(text);
        Joke joke = new Joke(jokeService.getNextId(), text);
        jokeService.saveJoke(joke);
        switchToAnotherView(event, "hello-view.fxml");
    }


    @FXML
    protected void switchView(ActionEvent event) throws IOException {
        switchToAnotherView(event, "create-joke-view.fxml");
    }


    @FXML
    public void onSearch(){
        System.out.println("Key pressed");
        String searchText = searchTextFiled.getText();
        List<String> filteredItems = new ArrayList<>();
        for (Joke joke: jokeService.getJokes()){
            if (joke.getText().contains(searchText)){
                filteredItems.add(joke.getText());
            }
        }
        jokesListView.setItems(FXCollections.observableList(filteredItems));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        jokeService = new JokeService();
        jokeService.loadJokesFromCSVFile();

        if (jokesListView != null) {
            jokesListView.setItems(FXCollections.observableList(jokeService.getJokes().stream().map(Joke::getText)
                    .collect(Collectors.toList())));
        }
    }

    private void switchToAnotherView(ActionEvent event, String s) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(s));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}