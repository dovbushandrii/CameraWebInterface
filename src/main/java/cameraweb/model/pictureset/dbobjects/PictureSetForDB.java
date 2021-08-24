package cameraweb.model.pictureset.dbobjects;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PictureSetForDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Getter
    @Setter
    private String encodingsType;

    @Getter
    @Setter
    private int count;

    @Getter
    @Setter
    private int exposureCode;

    @Getter
    @Setter
    private double exposureTime;

    @Getter
    @Setter
    private int isoCode;

    @Getter
    @Setter
    private int apertureCode;

    @Getter
    @Setter
    private String pictureName;

    @Getter
    @Setter
    private double pause;

}
