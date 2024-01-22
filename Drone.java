public class Drone extends template{
    // Drone class specific attributes
    private int id;
    private String dronetype;
    private String serialnumber;
    private int carriageweight;
    private String carriagetype;
    private String created;

    // Constructor without parameters
    public Drone() {
        this.id = 0;
        this.dronetype = "";
        this.created = "";
        this.serialnumber = "";
        this.carriageweight = 0;
        this.carriagetype = "";
    }

    // Constructor
    public Drone(int id, String dronetype, String serialnumber, String created, int carriageweight, String carriagetype) {
        // Initialises the attributes specific to the Drone class
        this.id = id;
        this.dronetype = dronetype;
        this.created = created;
        this.serialnumber = serialnumber;
        this.carriageweight = carriageweight;
        this.carriagetype = carriagetype;
    }

    // Getters y setters 

    public int getId() {
        return id;
    }

    /*public void setId(int id){
        this.id = id;
    }*/

    /*public void setDronetype(String dronetype) {
        this.dronetype = dronetype;
    }*/

    public String getDronetype() {
        return dronetype;
    }

    public String getCreated() {
        return created;
    }

    /*public void setCreated(String created){
        this.created = created;
    }*/

    public String getSerialNumber() {
        return serialnumber;
    }

    /*public void setSerialNumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }*/

    public int getCarriageWeight() {
        return carriageweight;
    }

    /*public void setCarriageWeight(int carriageweight) {
        this.carriageweight = carriageweight;
    }*/

    public String getCarriageType() {
        return carriagetype;
    }

    /*public void setCarriageType(String carriagetype) {
        this.carriagetype = carriagetype;
    }*/
 
    @Override
    public String toString() {
        return  "ID: " + getId() + 
                "\n DroneType: " + getDronetype() + 
                "\n Created: " + getCreated() +
                "\n SerialNumber: " + getSerialNumber() + 
                "\n CarriageWeight: " + getCarriageWeight() +
                "\n CarriageType:" + getCarriageType();
    }
}
