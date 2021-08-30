package cameraweb.repos;

import cameraweb.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import org.springframework.data.repository.CrudRepository;

public interface PhotoSessionParamsRepo extends CrudRepository<PhotoSessionParamsDTO, Long> {
}
