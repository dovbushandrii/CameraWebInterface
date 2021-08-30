package webapp.model;

import camera_api.interfaces.camerasdk.Camera;
import camera_api.interfaces.encogings.CameraProp;
import lombok.Getter;
import lombok.Setter;

public class CameraSettings {
    @Getter
    @Setter
    private CameraProp iso;
    @Getter
    private final CameraProp[] isos;

    @Getter
    @Setter
    private CameraProp exposure;
    @Getter
    private final CameraProp[] exposures;

    @Getter
    @Setter
    private CameraProp exposureComp;
    @Getter
    private final CameraProp[] exposureComps;

    @Getter
    @Setter
    private CameraProp aperture;
    @Getter
    private final CameraProp[] apertures;

    @Getter
    @Setter
    private CameraProp afSetting;
    @Getter
    private final CameraProp[] afSettings;

    @Getter
    @Setter
    private CameraProp wbSetting;
    @Getter
    private final CameraProp[] wbSettings;

    @Getter
    @Setter
    private CameraProp driveMode;
    @Getter
    private final CameraProp[] driveModes;

    @Getter
    @Setter
    private CameraProp colorSpace;
    @Getter
    private final CameraProp[] colorSpaces;

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
