package cameraweb.model;


public class PictureSet {
    private int count;
    private CamSettings exposure;
    private double exposureTime;
    private CamSettings iso;
    private CamSettings aperture;
    private String pictureName;
    private double pause;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public CamSettings getExposure() {
        return exposure;
    }

    public void setExposure(CamSettings exposure) {
        this.exposure = exposure;
    }

    public double getExposureTime() {
        return exposureTime;
    }

    public void setExposureTime(double exposureTime) {
        this.exposureTime = exposureTime;
    }

    public CamSettings getIso() {
        return iso;
    }

    public void setIso(CamSettings iso) {
        this.iso = iso;
    }

    public CamSettings getAperture() {
        return aperture;
    }

    public void setAperture(CamSettings aperture) {
        this.aperture = aperture;
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
