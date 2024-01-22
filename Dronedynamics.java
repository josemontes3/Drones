
public class Dronedynamics extends template{
    // Attributesextends
    private int ID;
    private String timestamp;
    private String drone;
    private double speed;
    private String alignmentRoll;
    private String controlRange;
    private String alignmentYaw;
    private String longitude;
    private String latitude;
    private int batteryStatus;
    private String lastSeen;
    private String status;

    // Constructor without parameters
    public Dronedynamics() {
        this.ID = 0;
        this.timestamp = "";
        this.drone = "";
        this.speed = 0;
        this.alignmentRoll = "";
        this.controlRange = "";
        this.alignmentYaw = "";
        this.longitude = "";
        this.latitude = "";
        this.batteryStatus = 0;
        this.lastSeen = "";
        this.status = "";
    }


    // Constructor
    public Dronedynamics(int ID, String timestamp, String drone, double speed, String alignmentRoll,
                         String controlRange, String alignmentYaw, String longitude, String latitude,
                         int batteryStatus, String lastSeen, String status) {
        this.ID = ID;
        this.timestamp = timestamp;
        this.drone = drone;
        this.speed = speed;
        this.alignmentRoll = alignmentRoll;
        this.controlRange = controlRange;
        this.alignmentYaw = alignmentYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.lastSeen = lastSeen;
        this.status = status;
    }

    // Getters y setters

    public int getId() {
        return ID;
    }

    /*public void setID(int ID) {
        this.ID = ID;
    }*/

    public String getTimestamp() {
        return timestamp;
    }

    /*public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }*/

    public String getDrone() {
        return drone;
    }

    /*public void setDrone(String drone) {
        this.drone = drone;
    }*/

    public double getSpeed() {
        return speed;
    }

    /*public void setSpeed(double speed) {
        this.speed = speed;
    }*/

    public String getAlignmentRoll() {
        return alignmentRoll;
    }

    /*public void setAlignmentRoll(String alignmentRoll) {
        this.alignmentRoll = alignmentRoll;
    }*/

    public String getControlRange() {
        return controlRange;
    }

    /*public void setControlRange(String controlRange) {
        this.controlRange = controlRange;
    }*/

    public String getAlignmentYaw() {
        return alignmentYaw;
    }

    /*public void setAlignmentYaw(String alignmentYaw) {
        this.alignmentYaw = alignmentYaw;
    }*/

    public String getLongitude() {
        return longitude;
    }

    /*public void setLongitude(String longitude) {
        this.longitude = longitude;
    }*/

    public String getLatitude() {
        return latitude;
    }

    /*public void setLatitude(String latitude) {
        this.latitude = latitude;
    }*/

    public int getBatteryStatus() {
        return batteryStatus;
    }

    /*public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }*/

    public String getLastSeen() {
        return lastSeen;
    }

    /*public void setLastSeen(String lastSeen) {
        this.lastSeen = lastSeen;
    }*/

    public String getStatus() {
        return status;
    }

    /*public void setStatus(String status) {
        this.status = status;
    }*/

    @Override
    public String toString() {
        return  "ID = " + getId() +
                "\n timestamp = " + getTimestamp() +
                "\n drone = " + getDrone() +
                "\n speed = " + getSpeed() +
                "\n Alignment Roll = " + getAlignmentRoll() +
                "\n Control Range = " + getControlRange() +
                "\n Alignment Yaw =" + getAlignmentYaw() +
                "\n longitude = " + getLongitude() +
                "\n latitude = " + getLatitude() +
                "\n batteryStatus = " + getBatteryStatus() +
                "\n lastSeen = " + getLastSeen() +
                "\n status = " + getStatus();
    }
}
