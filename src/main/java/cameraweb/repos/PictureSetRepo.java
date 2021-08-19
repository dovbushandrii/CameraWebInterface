package cameraweb.repos;

import cameraweb.pictureset.PictureSetForDB;
import org.springframework.data.repository.CrudRepository;

public interface PictureSetRepo extends CrudRepository<PictureSetForDB, Long> {
}
