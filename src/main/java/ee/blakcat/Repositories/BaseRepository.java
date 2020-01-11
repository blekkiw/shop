package ee.blakcat.Repositories;

import java.util.Set;

public interface BaseRepository <T, ID> {
    T getByID (ID id);
    T save (T ent);
    Set<T> getAll ();
}
