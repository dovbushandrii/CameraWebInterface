package cameraweb.repos;

import cameraweb.model.pictureset.dtos.PictureSetDTO;
import org.springframework.data.repository.CrudRepository;

public interface PictureSetRepo extends CrudRepository<PictureSetDTO, Long> {
}
