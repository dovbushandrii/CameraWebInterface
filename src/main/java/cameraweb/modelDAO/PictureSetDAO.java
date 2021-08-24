package cameraweb.modelDAO;

import cameraweb.model.pictureset.dbobjects.PictureSetForDB;
import cameraweb.repos.PictureSetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureSetDAO {

    private final PictureSetRepo repo;
    //private final PictureSetTransformer transformer;

    @Autowired
    public PictureSetDAO(PictureSetRepo repo) {
        this.repo = repo;
        //this.transformer = transformer;
    }

    public List<PictureSetForDB> read() {
        List<PictureSetForDB> sets = new ArrayList<>();
        Iterable<PictureSetForDB> list = repo.findAll();
        for (PictureSetForDB pictureSetForDB : list) {
            sets.add(pictureSetForDB);
        }
        return sets;
    }

    public PictureSetForDB read(int id) {
        PictureSetForDB set = repo.findById((long) id).orElse(null);
        if (set != null) {
            return set;
        }
        throw new IndexOutOfBoundsException("No such picture set with id: " + id);
    }

    public void update(List<PictureSetForDB> newList) {
        this.delete();
        for (PictureSetForDB set : newList) {
            this.create(set);
        }
    }

    public void update(PictureSetForDB newSet, int id) {
        //TODO
    }

    public void create(PictureSetForDB set) {
        repo.save(set);
    }

    public void delete() {
        repo.deleteAll();
    }

    public void delete(int id) {
        repo.deleteById((long) id);
    }
}
