package pl.edu.pwr.Objects;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;
@State(Scope.Thread)
    public class Film {
        public int id;
        public String filmTitle;
        public Optional<Integer> rating;

    public Film() {
    }

    public static final Comparator<Film> BY_RATING = Comparator.comparing(
            film -> film.rating.orElse(null),
            Comparator.nullsLast(Comparator.naturalOrder())
    );

    public static final Comparator<Film> BY_TITLE = Comparator.comparing(film -> film.filmTitle);


    public Film(int id, String filmTitle, Optional<Integer> rating) {
        this.id = id;
        this.filmTitle = filmTitle;
        this.rating = rating;
    }


    public int getId() {
        return id;
    }

    public String getFilmTitle() {
        return filmTitle;
    }

    public Optional<Integer> getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", filmTitle='" + filmTitle + '\'' +
                ", rating=" + rating +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return id == film.id && Objects.equals(filmTitle, film.filmTitle) && Objects.equals(rating, film.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, filmTitle, rating);
    }
}
