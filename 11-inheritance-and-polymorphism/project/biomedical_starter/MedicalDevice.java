// Module 11 Project, Track C: Medical Device Fleet
// Author: YOUR NAME

import java.time.LocalDate;

/**
 * A device on a hospital ward. Every device has a serial number, a ward,
 * and the date it was last calibrated. Each KIND of device records its own
 * readings, decides for itself when to alarm, and has its own calibration interval.
 */
public abstract class MedicalDevice {
    private final String serial;
    private final String ward;
    private LocalDate lastCalibrated;

    protected MedicalDevice(String serial, String ward, LocalDate lastCalibrated) {
        // TODO: reject an empty serial or ward, and a calibration date in the future
        this.serial = serial;
        this.ward = ward;
        this.lastCalibrated = lastCalibrated;
    }

    public String getSerial() { return serial; }
    public String getWard() { return ward; }
    public LocalDate getLastCalibrated() { return lastCalibrated; }

    /** e.g. "Pulse oximeter". */
    public abstract String deviceType();

    /** How many days the device may go between calibrations. */
    public abstract int calibrationIntervalDays();

    /** The latest reading as text, or "no readings" if there isn't one yet. */
    public abstract String latestReading();

    /** True if the latest reading is outside safe limits. */
    public abstract boolean isAlarming();

    public boolean isCalibrationDue(LocalDate today) {
        // TODO: due if today is on or after lastCalibrated + interval
        //       (LocalDate has plusDays(n) and isBefore(otherDate))
        return false;
    }

    public void calibrate(LocalDate today) {
        lastCalibrated = today;
    }

    @Override
    public String toString() {
        // TODO: e.g.  "OX-104 Pulse oximeter (Ward 3B): SpO2 91%, pulse 118  ** ALARM **"
        return serial;
    }
}
