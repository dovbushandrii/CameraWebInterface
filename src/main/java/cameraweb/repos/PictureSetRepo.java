package cameraweb.repos;

import cameraweb.pictureset.dbobjects.PictureSetForDB;
import org.springframework.data.repository.CrudRepository;

public interface PictureSetRepo extends CrudRepository<PictureSetForDB, Long> {
}
