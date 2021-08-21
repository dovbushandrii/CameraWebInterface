package cameraweb.modelDAO;

import camera_api.canon.encodings.cameraprops.EdsAperture;
import camera_api.canon.encodings.cameraprops.EdsExposure;
import camera_api.canon.encodings.cameraprops.EdsISO;
import cameraweb.pictureset.canonEncodings.CanonPictureSet;
import cameraweb.pictureset.dbobjects.PictureSetForDB;
import cameraweb.pictureset.inter.PictureSet;
import cameraweb.pictureset.inter.PictureSetTransformer;
import cameraweb.repos.PictureSetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PictureSetDAO {

    private final PictureSetRepo repo;
    private final PictureSetTransformer transformer;

    @Autowired
    public PictureSetDAO(PictureSetRepo repo,
                         PictureSetTransformer transformer){
        this.repo = repo;
        this.transformer = transformer;
    }

    public List<PictureSet> read(){
        List<PictureSet> sets = new ArrayList<>();
        Iterable<PictureSetForDB> list = repo.findAll();
        for (PictureSetForDB pictureSetForDB : list) {
            sets.add(transformer.transformFromDBO(pictureSetForDB));
        }
        return sets;
    }

    public PictureSet read(int id) {
        PictureSetForDB set = repo.findById((long) id).orElse(null);
        if(set != null){
            return transformer.transformFromDBO(set);
        }
        throw new IndexOutOfBoundsException("No such picture set with id: " + id);
    }

    public void update(List<PictureSet> newList){
        this.delete();
        for(PictureSet set: newList){
            this.create(set);
        }
    }

    public void update(PictureSet newSet, int id){
        //TODO
    }

    public void create(PictureSet set){
        repo.save(transformer.transformToDBO(set));
    }

    public void delete(){
        repo.deleteAll();
    }

    public void delete(int id){
        repo.deleteById((long) id);
    }
}
