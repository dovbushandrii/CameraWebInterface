package cameraweb.modelDAO;

import cameraweb.pictureset.PictureSetForDB;
import cameraweb.repos.PictureSetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class PictureSetDAO {
    @Autowired
    private PictureSetRepo repo;

    public List<PictureSetForDB> read(){
        List<PictureSetForDB> sets = new ArrayList<>();
        Iterable<PictureSetForDB> iters = repo.findAll();
        Iterator<PictureSetForDB> iter = iters.iterator();
        while(iter.hasNext()){
            sets.add(iter.next());
        }
        return sets;
    }

    public PictureSetForDB read(int id) {
        PictureSetForDB set = repo.findById(Long.valueOf(id)).orElse(null);
        if(set != null){
            return set;
        }
        throw new IndexOutOfBoundsException("No such picture set with id: " + String.valueOf(id));
    }

    public void update(List<PictureSetForDB> newList){
        this.delete();
        for(PictureSetForDB set: newList){
            this.create(set);
        }
    }

    public void update(PictureSetForDB newSet, int id){
        //TODO
    }

    public void create(PictureSetForDB set){
        repo.save(set);
    }

    public void delete(){
        repo.deleteAll();
    }

    public void delete(int id){
        repo.deleteById(Long.valueOf(id));
    }
}
