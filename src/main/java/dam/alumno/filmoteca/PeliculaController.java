package dam.alumno.filmoteca;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import javax.swing.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ResourceBundle;

public class PeliculaController implements Initializable {
    public ImageView imgView;
    public TextField fieldTitle;
    public TextField fieldYear;
    public TextArea fieldDescription;
    public Slider fieldRating;
    public TextField fieldURL;
    private DatosFilmoteca datosFilmoteca = DatosFilmoteca.getInstancia();
    private ObservableList<Pelicula> listaPeliculas= datosFilmoteca.getListaPeliculas();
    private Pelicula newPelicula= new Pelicula();
    public boolean okClick=false;

    @Override
    //En el initialize es donde se carga nada más empezar el programa los campos del textfield
    public void initialize(URL location, ResourceBundle resources) {
        fieldTitle.textProperty().bindBidirectional(newPelicula.titleProperty());
        fieldYear.textProperty().bindBidirectional(newPelicula.yearProperty());
        fieldYear.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    fieldYear.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        fieldDescription.textProperty().bindBidirectional(newPelicula.descriptionProperty());
        fieldRating.valueProperty().bindBidirectional(newPelicula.ratingProperty());
        fieldURL.textProperty().bindBidirectional(newPelicula.posterProperty());
        fieldURL.textProperty().addListener((observable, oldValue, newValue) -> {
            imgView.setImage(new Image(fieldURL.getText()));
        });
    }

    public boolean isOkClicked() {
        return okClick;
    }

    public Pelicula getPelicula() {
        return newPelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        newPelicula.setId(pelicula.getId());
        newPelicula.setTitle(pelicula.getTitle());
        newPelicula.setYear(pelicula.getYear());
        newPelicula.setDescription(pelicula.getDescription());
        newPelicula.setRating(pelicula.getRating());
        newPelicula.setPoster(pelicula.getPoster());
    }

    public void handlerAceptar(ActionEvent actionEvent) {
        okClick=true;
        Stage s = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        s.close();
    }
    public void handlerCancelar(ActionEvent actionEvent) {
        okClick=false;
        Stage s = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        s.close();
    }
}
