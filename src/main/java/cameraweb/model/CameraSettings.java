package cameraweb.model;

import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.encogings.CameraProp;
import lombok.Getter;

public class CameraSettings {
    @Getter
    private CameraProp iso;
    @Getter
    private CameraProp[] isos;

    @Getter
    private CameraProp exposure;
    @Getter
    private CameraProp[] exposures;

    @Getter
    private CameraProp exposureComp;
    @Getter
    private CameraProp[] exposureComps;

    @Getter
    private CameraProp aperture;
    @Getter
    private CameraProp[] apertures;

    @Getter
    private CameraProp afSetting;
    @Getter
    private CameraProp[] afSettings;

    @Getter
    private CameraProp wbSetting;
    @Getter
    private CameraProp[] wbSettings;

    @Getter
    private CameraProp driveMode;
    @Getter
    private CameraProp[] driveModes;

    @Getter
    private CameraProp colorSpace;
    @Getter
    private CameraProp[] colorSpaces;

    public CameraSettings(Camera cam) {
        this.iso = cam.getISO();
        this.exposure = cam.getExposure();
        this.exposureComp = cam.getExposureComp();
        this.aperture = cam.getAperture();
        this.afSetting = cam.getFocusSettings();
        this.wbSetting = cam.getWhiteBalance();
        this.driveMode = cam.getDriveMode();
        this.colorSpace = cam.getColorSpace();

        this.isos = cam.getPossibleISO();
        this.exposures = cam.getPossibleExposure();
        this.exposureComps = cam.getPossibleExposureComp();
        this.apertures = cam.getPossibleAperture();
        this.afSettings = cam.getPossibleFocusSettings();
        this.wbSettings = cam.getPossibleWhiteBalance();
        this.driveModes = cam.getPossibleDriveMode();
        this.colorSpaces = cam.getPossibleColorSpace();
    }
}
