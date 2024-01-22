public class Dronetype extends template{
    //Attributes
    private int idtype;
    private String manufacturer;
    private String typeName;
    private int weight;
    private int maximumSpeed;
    private int batteryCapacity;
    private int controlRange;
    private int maximumCarriage;

     // Constructor without parameters
    public Dronetype() {
        
        this.idtype = 0;
        this.manufacturer = "";
        this.typeName = "";
        this.weight = 0;
        this.maximumSpeed = 0;
        this.batteryCapacity = 0;
        this.controlRange = 0;
        this.maximumCarriage = 0;
    }

    // Constructor
    public Dronetype(int idtype, String manufacturer, String typeName, int weight, int maximumSpeed,int batteryCapacity, int controlRange, 
                    int maximumCarriage) {
        
        this.idtype = idtype;
        this.manufacturer = manufacturer;
        this.typeName = typeName;
        this.weight = weight;
        this.maximumSpeed = maximumSpeed;
        this.batteryCapacity = batteryCapacity;
        this.controlRange = controlRange;
        this.maximumCarriage = maximumCarriage;
    }

    // Getters y setters
    public int getId() {
        return idtype;
    }

    /*public void setIdtype(int idtype) {
        this.idtype = idtype;
    }*/

    public String getManufacturer() {
        return manufacturer;
    }

    /*public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }*/

    public String getTypeName() {
        return typeName;
    }

    /*public void setTypeName(String typeName) {
        this.typeName = typeName;
    }*/

    public int getWeight() {
        return weight;
    }

    /*public void setWeight(int weight) {
        this.weight = weight;
    }*/

    public int getMaximumSpeed() {
        return maximumSpeed;
    }

    /*public void setMaximumSpeed(int maximumSpeed) {
        this.maximumSpeed = maximumSpeed;
    }*/

    public int getBatteryCapacity() {
        return batteryCapacity;
    }

    /*public void setBatteryCapacity(int batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }*/

    public int getControlRange() {
        return controlRange;
    }

    /*public void setControlRange(int controlRange) {
        this.controlRange = controlRange;
    }*/

    public int getMaximumCarriage() {
        return maximumCarriage;
    }

    /*public void setMaximumCarriage(int maximumCarriage) {
        this.maximumCarriage = maximumCarriage;
    }*/

    //Methods for obtaining all the information about a type of drone
    @Override
    public String toString() {
    return "ID: " + getId() + 
            "\nManufacturer: " + getManufacturer() + 
            "\nType Name: " + getTypeName() + 
            "\nWeight: " + getWeight() + " kg" +
            "\nMaximum Speed: " + getMaximumSpeed() + " m/s" +
            "\nBattery Capacity: " + getBatteryCapacity() + " mAh" + 
            "\nControl Range: " + getControlRange() + " meters" +
            "\nMaximum Carriage: " + getMaximumCarriage() + " kg";
}

}
