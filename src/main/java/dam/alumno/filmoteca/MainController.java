package dam.alumno.filmoteca;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;

public class MainController {

    @FXML
    public Button btnAna;
    public Button btnBor;
    public Button btnMod;
    public Button btnSal;
    public TableView<Pelicula> tableView;
    public TableColumn<Pelicula, Integer> idCol;
    public TableColumn<Pelicula, String> titCol;
    public TableColumn<Pelicula, Integer> anoCol;
    public TableColumn<Pelicula, String> desCol;
    public TableColumn<Pelicula, Float> ratCol;
    public TableColumn<Pelicula, String> posCol;
    private ObservableList<Pelicula> listaPeliculas;
    private DatosFilmoteca datosFilmoteca = DatosFilmoteca.getInstancia();

    @FXML
    void anaPelicula(ActionEvent event) {
        Scene escena = null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("secondview.fxml"));
        try {
            escena = new Scene(fxmlLoader.load(),600,400);
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ventana de nueva persona");
            e.printStackTrace();
        }
        PeliculaController controlador = fxmlLoader.getController();
        Stage st = new Stage();
        st.setResizable(false);
        st.setScene(escena);
        st.setTitle("Crear Nueva Película");
        st.showAndWait();

        if (controlador.isOkClicked()) {
            Pelicula newPersona = controlador.getPelicula();
            Pelicula p = Collections.max(listaPeliculas, Comparator.comparingInt(Pelicula::getId));

            newPersona.setId(p.getId()+1);
            tableView.getItems().add(newPersona);
        }
    }

    @FXML
    void borrarPelicula(ActionEvent event) {
        int indice = tableView.getSelectionModel().getSelectedIndex();

        if (indice >= 0) {
            tableView.getItems().remove(indice);
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ADVERTENCIA");
            alerta.setHeaderText("No se ha seleccionado ninguna persona");
            alerta.setContentText("Por favor seleccione una persona de la tabla para eliminar");
            alerta.showAndWait();
        }
    }

    @FXML
    void modPelicula(ActionEvent event) {
        int indice = tableView.getSelectionModel().getSelectedIndex();

        if (indice < 0) {
            Alert alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("ATENCIÓN");
            alerta.setHeaderText(" No se ha seleccionado ninguna persona");
            alerta.setContentText(" Por favor seleccione un apersona de la tabla para poder editarla");
            alerta.showAndWait();
            return;
        }

        Scene escena = null;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("secondview.fxml"));
        try {
            escena = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("ERROR al cargar la ventana de nueva persona");
            e.printStackTrace();
        }
        PeliculaController controlador = fxmlLoader.getController();
        controlador.setPelicula(tableView.getSelectionModel().getSelectedItem());

        Stage st = new Stage();
        st.setScene(escena);
        st.getIcons().add(new Image("https://cdn-icons-png.flaticon.com/512/2459/2459778.png"));
        st.setResizable(false);
        st.setTitle("EDITAR PELÍCULA");
        st.showAndWait();

        if (controlador.isOkClicked()) {
            Pelicula peliculaEditada = controlador.getPelicula();
            Pelicula peliculaActual = tableView.getSelectionModel().getSelectedItem();
            peliculaActual.setTitle(peliculaEditada.getTitle());
            peliculaActual.setYear(peliculaEditada.getYear());
            peliculaActual.setDescription(peliculaEditada.getDescription());
            peliculaActual.setRating(peliculaEditada.getRating());
            peliculaActual.setPoster(peliculaEditada.getPoster());
        }
    }


    public void salirAplicacion(ActionEvent event) {
        Stage s = (Stage)((Node)event.getSource()).getScene().getWindow();
        s.close();
    }

    public void initialize() {
        listaPeliculas = datosFilmoteca.getListaPeliculas();
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        titCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        anoCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        desCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        ratCol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        posCol.setCellValueFactory(new PropertyValueFactory<>("poster"));
        tableView.setItems(listaPeliculas);
    }

    public void guardarApp(ActionEvent event) {
        ObservableList<Pelicula> listaPeliculas = DatosFilmoteca.getInstancia().getListaPeliculas();
        System.out.println(listaPeliculas);
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("datos/peliculas.json"),listaPeliculas);
        }catch (IOException e) {
            System.out.println("ERROR no se ha podido guardar los datos de la aplicación");
            e.printStackTrace();
        }
    }
}
