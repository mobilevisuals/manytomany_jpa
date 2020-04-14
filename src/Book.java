import java.io.Serializable;
import java.util.List;
import static javax.persistence.CascadeType.PERSIST;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author eyvind
 */
@Entity
public class Book implements Serializable {
    //tabellen som äger
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int pages;
    private String title;
    //Cascade är för att det som Book refererar till också kan sparas med persist
    //alltså det räcker med att vi anropar persist på Book och vi behöver inte anropa persist på
    //alla Authors som den innehåller
    @ManyToMany(cascade=PERSIST)
    //JoinTable krävs här, som kopplar samman kolumnerna för primärnycklarna
    //ska vara på ägande klassen
    @JoinTable(name = "author_booksmap",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
            //inverse: tabellen på andra sidan
    )
    private List<Author> authors;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }


}