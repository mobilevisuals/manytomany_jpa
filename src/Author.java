import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author eyvind
 */
@Entity

public class Author implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String country;
    //mappedBy pekar på den som äger, alltså books-listan. Book är då den ägande klassen.
    @ManyToMany(cascade=PERSIST,mappedBy="authors")
    //authors: List för tabellen på andra sidan många-till-många förhållandet
    private List<Book> books;

    public String getName() {
        return name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }



}