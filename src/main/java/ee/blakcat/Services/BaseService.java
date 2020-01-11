package ee.blakcat.Services;

import java.util.Set;

public interface BaseService <T, ID> {
    T getByID (ID id);
    T save (T ent);
    Set<T> getAll ();
}
