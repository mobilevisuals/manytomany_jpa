import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


/**
 *
 * @author eyvind
 */
public class ManyToManyTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("testPersistenceUnit");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction=em.getTransaction();
        transaction.begin();

        Book book1 = new Book();
        book1.setTitle("Tapeter och solsken");
        book1.setPages(678);
        Book book2 = new Book();
        book2.setTitle("Java och annat");
        book2.setPages(478);

        Book book3 = new Book();
        book3.setTitle("Rum som är fulla");
        book3.setPages(678);

        Author author = new Author();
        author.setCountry("Sverige");
        author.setName("Janne Andersson");

        Author author2 = new Author();
        author2.setCountry("Sverige");
        author2.setName("Gunnar Nilsson");

        //Jannes böcker
        List<Book> books = new ArrayList();
        books.add(book1);
        books.add(book2);

        //Gunnars böcker
        List<Book> books2 = new ArrayList();
        books2.add(book1);
        books2.add(book3);

        author.setBooks(books);
        author2.setBooks(books2);
        //författarna till tapeter, som har skrivits av 2
        List<Author> authors = new ArrayList();
        authors.add(author);
        authors.add(author2);
        //författaren till java, som bara har skrivits av en
        List<Author> authors2 = new ArrayList();
        authors2.add(author);
        //författaren till rum, som bara har skrivits av en
        List<Author> authors3 = new ArrayList();
        authors3.add(author2);
        book1.setAuthors(authors);
        book1.setAuthors(authors);
        book2.setAuthors(authors2);
        book3.setAuthors(authors3);

        try {
            //räcker med att spara ägande
            em.persist(author);
            em.persist(author2);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        } finally {
            em.close();
            emf.close();
        }

    }

}