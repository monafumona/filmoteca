package dam.alumno.filmoteca;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.beans.property.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pelicula {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty title = new SimpleStringProperty();
    private final StringProperty year = new SimpleStringProperty();
    private final StringProperty description = new SimpleStringProperty();
    private final FloatProperty rating = new SimpleFloatProperty();
    private final StringProperty poster = new SimpleStringProperty();


    public Pelicula() {

    }

    public Pelicula(int id, String title, String year, String description, float rating, String poster) {
        this.id.set(id);
        this.title.set(title);
        this.year.set(year);
        this.description.set(description);
        this.rating.set(rating);
        this.poster.set(poster);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getTitle() {
        return title.get();
    }

    public StringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getYear() {
        return year.get();
    }

    public StringProperty yearProperty() {
        return year;
    }

    public void setYear(String year) {
        this.year.set(year);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public float getRating() {
        return rating.get();
    }

    public Property<Number> ratingProperty() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating.set(rating);
    }

    public String getPoster() {
        return poster.get();
    }

    public Property<String> posterProperty() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster.set(poster);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
                "id=" + id +
                ", title=" + title +
                ", year=" + year +
                ", description=" + description +
                ", rating=" + rating +
                ", poster=" + poster +
                '}';
    }
}
