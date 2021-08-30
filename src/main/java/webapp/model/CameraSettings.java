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

    public CameraSettings(Camera camera) {
        this.iso = camera.getISO();
        this.exposure = camera.getExposure();
        this.exposureComp = camera.getExposureComp();
        this.aperture = camera.getAperture();
        this.afSetting = camera.getFocusSettings();
        this.wbSetting = camera.getWhiteBalance();
        this.driveMode = camera.getDriveMode();
        this.colorSpace = camera.getColorSpace();

        this.isos = camera.getPossibleISO();
        this.exposures = camera.getPossibleExposure();
        this.exposureComps = camera.getPossibleExposureComp();
        this.apertures = camera.getPossibleAperture();
        this.afSettings = camera.getPossibleFocusSettings();
        this.wbSettings = camera.getPossibleWhiteBalance();
        this.driveModes = camera.getPossibleDriveMode();
        this.colorSpaces = camera.getPossibleColorSpace();
    }

    public void applyCameraSettings(Camera camera) {
        camera.setExposure(exposure);
        camera.setISO(iso);
        camera.setAperture(aperture);
        camera.setExposureComp(exposureComp);
        camera.setFocusSettings(afSetting);
        camera.setWhiteBalance(wbSetting);
        camera.setDriveMode(driveMode);
        camera.setColorSpace(colorSpace);
    }
}
