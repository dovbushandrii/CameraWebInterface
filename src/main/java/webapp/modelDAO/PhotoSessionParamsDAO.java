package webapp.modelDAO;

import camera_api.CompanyLoader;
import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import webapp.repos.PhotoSessionParamsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PhotoSessionParamsDAO {

    private final PhotoSessionParamsRepo repo;
    private final CompanyLoader company;

    @Autowired
    public PhotoSessionParamsDAO(PhotoSessionParamsRepo repo, CompanyLoader company) {
        this.repo = repo;
        this.company = company;
    }

    public List<PhotoSessionParamsDTO> read() {
        List<PhotoSessionParamsDTO> params = new ArrayList<>();
        Iterable<PhotoSessionParamsDTO> list = repo.findAll();
        String photoSessionParamsClassName = company.getCompany()
                .getPhotoSessionParamsClassName();

        for (PhotoSessionParamsDTO photoSessionParamsDTO : list) {
            if (photoSessionParamsDTO.getEncodingsType().equals(photoSessionParamsClassName)) {
                params.add(photoSessionParamsDTO);
            }
        }
        return params;
    }

    public PhotoSessionParamsDTO read(int id) {
        Optional<PhotoSessionParamsDTO> params = repo.findById((long) id);
        if (params.isPresent()) {
            return params.get();
        }
        throw new IndexOutOfBoundsException("No such photo session parameters with id: " + id);
    }

    public void update(List<PhotoSessionParamsDTO> newList) {
        this.delete();
        for (PhotoSessionParamsDTO params : newList) {
            this.create(params);
        }
    }

    public void update(PhotoSessionParamsDTO newParams) {
        //TODO
    }

    public void create(PhotoSessionParamsDTO params) {
        repo.save(params);
    }

    public void delete() {
        repo.deleteAll();
    }

    public void delete(int id) {
        repo.deleteById((long) id);
    }
}
