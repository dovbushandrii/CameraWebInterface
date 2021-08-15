package cameraweb.model;

import camera_api.interfaces.Camera;
import camera_api.interfaces.CameraProp;

public class CamSettings {
    private CameraProp iso;
    private CameraProp[] isos;

    private CameraProp exposure;
    private CameraProp[] exposures;

    private CameraProp exposureComp;
    private CameraProp[] exposureComps;

    private CameraProp aperture;
    private CameraProp[] apertures;

    private CameraProp afSetting;
    private CameraProp[] afSettings;

    private CameraProp wbSetting;
    private CameraProp[] wbSettings;

    private CameraProp driveMode;
    private CameraProp[] driveModes;

    private CameraProp colorSpace;
    private CameraProp[] colorSpaces;

    public CamSettings(Camera cam){
        this.iso           = cam.getISO();
        this.exposure      = cam.getExposure();
        this.exposureComp  = cam.getExposureComp();
        this.aperture      = cam.getAperture();
        this.afSetting     = cam.getFocusSettings();
        this.wbSetting     = cam.getWhiteBalance();
        this.driveMode     = cam.getDriveMode();
        this.colorSpace    = cam.getColorSpace();

        this.isos          = cam.getPossibleISO();
        this.exposures     = cam.getPossibleExposure();
        this.exposureComps = cam.getPossibleExposureComp();
        this.apertures     = cam.getPossibleAperture();
        this.afSettings    = cam.getPossibleFocusSettings();
        this.wbSettings    = cam.getPossibleWhiteBalance();
        this.driveModes    = cam.getPossibleDriveMode();
        this.colorSpaces   = cam.getPossibleColorSpace();
    }

    public CameraProp getISO(){
        return this.iso;
    }

    public CameraProp getExposure(){
        return this.exposure;
    }

    public CameraProp getExposureComp(){
        return this.exposureComp;
    }

    public CameraProp getAperture(){
        return this.aperture;
    }

    public CameraProp getAFSetting(){
        return this.afSetting;
    }

    public CameraProp getWBSetting(){
        return this.wbSetting;
    }

    public CameraProp getDriveMode(){
        return this.driveMode;
    }

    public CameraProp getColorSpace(){
        return this.colorSpace;
    }
}
