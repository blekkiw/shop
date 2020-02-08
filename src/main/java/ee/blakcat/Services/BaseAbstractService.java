package ee.blakcat.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class BaseAbstractService <
        REPOSITORY extends CrudRepository <ENT, ID>
        , ENT, ID extends Serializable>
        implements BaseService <ENT, ID> {

    @Autowired
protected REPOSITORY repository;


    @Override
    public ENT findById(ID s) {
        ENT entity = repository.findById(s).get();
        if (Objects.isNull(entity)) {
            throw new RuntimeException("Not found!");
        }
        return entity;
    }

    @Override
    public ENT save(ENT ent) {
        return repository.save(ent);
    }
    @Override
    public Set<ENT> findAll() {
        Set<ENT> all = new HashSet<>();
        repository.findAll().forEach(all::add);
        return all;
    }
}
