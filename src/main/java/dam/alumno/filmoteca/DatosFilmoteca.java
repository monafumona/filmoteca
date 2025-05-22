package dam.alumno.filmoteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatosFilmoteca {

    private static DatosFilmoteca instancia = null;
    private final ObservableList<Pelicula> listaPeliculas = FXCollections.observableArrayList();

    private static void DatosFilmoteca () {
    }

    public static DatosFilmoteca  getInstancia() {
        if (instancia == null) {
            instancia = new DatosFilmoteca();
        }
        return instancia;
    }

    public ObservableList<Pelicula> getListaPeliculas() {
        return listaPeliculas;
    }


}
