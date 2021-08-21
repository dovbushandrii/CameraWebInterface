package cameraweb.pictureset.dbobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PictureSetForDB {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Canon or Nikon or Sony
    private String encodingsType;

    private int count;
    private int exposureCode;
    private double exposureTime;
    private int isoCode;
    private int apertureCode;
    private String pictureName;
    private double pause;

    public long getId(){
        return this.id;
    }

    public String getEncodingsType() {
        return encodingsType;
    }

    public void setEncodingsType(String encodingsType) {
        this.encodingsType = encodingsType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getExposureCode() {
        return exposureCode;
    }

    public void setExposureCode(int exposureCode) {
        this.exposureCode = exposureCode;
    }

    public double getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(double exposureTime) {
        this.exposureTime = exposureTime;
    }

    public int getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(int isoCode) {
        this.isoCode = isoCode;
    }

    public int getApertureCode() {
        return apertureCode;
    }

    public void setApertureCode(int apertureCode) {
        this.apertureCode = apertureCode;
    }

    public String getPictureName() {
        return pictureName;
    }

    public void setPictureName(String pictureName) {
        this.pictureName = pictureName;
    }

    public double getPause() {
        return pause;
    }

    public void setPause(double pause) {
        this.pause = pause;
    }
}
