package cameraweb.modelDAO;

import cameraweb.model.pictureset.dtos.PictureSetDTO;
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

    public List<PictureSetDTO> read() {
        List<PictureSetDTO> sets = new ArrayList<>();
        Iterable<PictureSetDTO> list = repo.findAll();
        for (PictureSetDTO pictureSetDTO : list) {
            sets.add(pictureSetDTO);
        }
        return sets;
    }

    public PictureSetDTO read(int id) {
        PictureSetDTO set = repo.findById((long) id).orElse(null);
        if (set != null) {
            return set;
        }
        throw new IndexOutOfBoundsException("No such picture set with id: " + id);
    }

    public void update(List<PictureSetDTO> newList) {
        this.delete();
        for (PictureSetDTO set : newList) {
            this.create(set);
        }
    }

    public void update(PictureSetDTO newSet) {
        //TODO
    }

    public void create(PictureSetDTO set) {
        repo.save(set);
    }

    public void delete() {
        repo.deleteAll();
    }

    public void delete(int id) {
        repo.deleteById((long) id);
    }
}
