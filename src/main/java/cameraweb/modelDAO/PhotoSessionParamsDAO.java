package cameraweb.modelDAO;

import camera_api.ProxyCompany;
import cameraweb.model.pictureset.dtos.PictureSetDTO;
import cameraweb.repos.PictureSetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PictureSetDAO {

    private final PictureSetRepo repo;
    private final ProxyCompany company;

    @Autowired
    public PictureSetDAO(PictureSetRepo repo, ProxyCompany company) {
        this.repo = repo;
        this.company = company;
    }

    public List<PictureSetDTO> read() {
        List<PictureSetDTO> sets = new ArrayList<>();
        Iterable<PictureSetDTO> list = repo.findAll();
        String pictureSetClassName = company.getCompany()
                .getPhotoSessionParamsClassName();

        for (PictureSetDTO pictureSetDTO : list) {
            if(pictureSetDTO.getEncodingsType().equals(pictureSetClassName)) {
                sets.add(pictureSetDTO);
            }
        }
        return sets;
    }

    public PictureSetDTO read(int id) {
        Optional<PictureSetDTO> set = repo.findById((long) id);
        if (set.isPresent()) {
            return set.get();
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
