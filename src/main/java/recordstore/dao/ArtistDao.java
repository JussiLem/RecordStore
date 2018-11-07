package recordstore.dao;

import recordstore.data.Artist;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public interface ArtistDao {

    /**
     * @return palauttaa kaikki oliot streaminä. Striimiä voi säätää lazy tai eagerly
     * Stream täytyy sulkea käytön jälkeen.
     * @throws SQLException jos tulee virheitä.
     */
    Stream<Artist> getAll() throws SQLException;

    /**
     * @param id yksilöllinen tunniste artistille.
     * @return an optional with artist if a artist with unique identifier <code>id</code>
     *     exists, empty optional otherwise.
     * @throws SQLException jos tulee virheitä.
     */
    Optional<Artist> getById(int id) throws SQLException;
    /**
     * @param artist artisti, joka lisätään.
     * @return tosi, jos artisti lisätty onnistuneesti, epätosi jos artisti on jo olemassa.
     * @throws SQLException jos tulee virheitä.
     */
    boolean add(Artist artist) throws SQLException;

    /**
     * @param artist artisti, joka päivitetään.
     * @return tosi, jos artisti on päivitetty onnistuneesti, epätosi muuten.
     * @throws SQLException jos tulee virheitä.
     */
    boolean update(Artist artist) throws SQLException;

    /**
     * @param artist artisti, joka poistetaan.
     * @return tosi, jos artisti on olemassa, sekä onnistuneesti poistettu, muuten epätosi.
     * @throws SQLException jos tulee virheitä.
     */
    boolean delete(Artist artist) throws SQLException;
}
