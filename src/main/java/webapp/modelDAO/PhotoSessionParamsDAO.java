package webapp.modelDAO;

import camera_api.CompanyLoader;
import webapp.model.photosessionparams.dtos.PhotoSessionParamsDTO;
import webapp.repos.PhotoSessionParamsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PhotoSessionParamsDAO {

    private final PhotoSessionParamsRepo repo;
    private final CompanyLoader companyLoader;

    @Autowired
    public PhotoSessionParamsDAO(PhotoSessionParamsRepo repo, CompanyLoader companyLoader) {
        this.repo = repo;
        this.companyLoader = companyLoader;
    }

    public List<PhotoSessionParamsDTO> read() {
        List<PhotoSessionParamsDTO> params = new ArrayList<>();
        Iterable<PhotoSessionParamsDTO> list = repo.findAll();
        String photoSessionParamsClassName = companyLoader.getCompany()
                .getPhotoSessionParamsClassName();

        for (PhotoSessionParamsDTO photoSessionParamsDTO : list) {
            if (photoSessionParamsDTO.getEncodingsType().equals(photoSessionParamsClassName)) {
                params.add(photoSessionParamsDTO);
            }
        }
        return params;
    }

    public PhotoSessionParamsDTO read(int id) {
        return repo.findById((long) id)
                .orElseThrow(() -> new IndexOutOfBoundsException(
                        "No such photo session parameters with id: " + id
                ));
    }

    public void update(List<PhotoSessionParamsDTO> newList) {
        delete();
        newList.forEach(this::create);
    }

    public void update(PhotoSessionParamsDTO newParams) {
        this.delete(newParams.getId());
        this.create(newParams);
    }

    public void create(PhotoSessionParamsDTO params) {
        repo.save(params);
    }

    public void delete() {
        repo.deleteAll();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
