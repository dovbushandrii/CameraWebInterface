package webapp.repos;

import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import org.springframework.data.repository.CrudRepository;

public interface PhotoSessionParamsRepo extends CrudRepository<PhotoSessionParamsDTO, Long> {
}
