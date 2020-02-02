package ee.blakcat.Services;

import java.util.Set;

public interface BaseService <ENT, ID> {
    ENT getByID (ID id);
    ENT save (ENT ent);
    Set<ENT> getAll ();
}
