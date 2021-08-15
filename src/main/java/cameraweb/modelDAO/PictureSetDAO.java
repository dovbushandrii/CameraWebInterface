package cameraweb.modelDAO;

import cameraweb.model.PictureSet;
import org.springframework.stereotype.Component;

@Component
public class PictureSetDAO {

    public PictureSet read(){
        return new PictureSet();
    }
}
