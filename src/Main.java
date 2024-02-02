import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;

        do {
            // Show all Dronetype IDs and allow the user to select one.
            API_dronetypes apiDronetypes = new API_dronetypes();
            List<Dronetype> dronetypeList = apiDronetypes.dronetype();
            String[] dronetypeIds = dronetypeList.stream().map(dronetype -> "ID: " + dronetype.getId()).toArray(String[]::new);
            int dronetypeChoice = showOptionDialog("Choose Dronetype", "Select the Dronetype ID:", dronetypeIds);

           // Get the Dronetype ID selected by the user
            int selectedDronetypeId = dronetypeList.get(dronetypeChoice).getId();

           // Ask the user whether to display Dronetype data or select a Drone
            String[] dronetypeOption = {"Show Dronetype Data", "Select Drone ID"};
            int dronetypeDecision = showOptionDialog("Dronetype Option", "Choose an option:", dronetypeOption);

            if (dronetypeDecision == 0) {
                // Display the data of the selected Dronetype
                showDronetypeData(dronetypeList.get(dronetypeChoice));
            } else if (dronetypeDecision == 1) {
                // Load all the drones corresponding to the selected Dronetype
                API_drones apiDrones = new API_drones();
                List<Drone> filteredDroneList = apiDrones.drone().stream().filter(drone -> drone.getDronetype().getId() == selectedDronetypeId).toList();

               // Check if Drones exist for the selected Dronetype
                if (filteredDroneList.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No exist Drones for the selected Dronetype.", "No Drone", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Check if Drones exist for the selected Dronetype
                    String[] droneIds = filteredDroneList.stream().map(drone -> "ID: " + drone.getId()).toArray(String[]::new);
                    int droneChoice = showOptionDialog("Choose Drone", "Select the Drone ID:", droneIds);

                    // Get the ID of the Drone selected by the user.
                    int selectedDroneId = filteredDroneList.get(droneChoice).getId();

                    // Ask the user whether to display Dronetype data or select a Drone
                    String[] droneOption = {"Show Drone Data", "Show Dronedynamics Data"};
                    int droneDecision = showOptionDialog("Drone Option", "Choose an option:", droneOption);

                    if (droneDecision == 0) {
                    // Display the data of the selected Dronetype
                    showDroneData(filteredDroneList.get(droneChoice));
                    } else if (droneDecision == 1) {

                    // Get the ID of the Drone selected by the user.
                    API_dronedynamics apiDronedynamics = new API_dronedynamics();
                    List<Dronedynamics> dronedynamicsList = apiDronedynamics.dronedyna(selectedDroneId);

                    // Check if Dronedynamics exists for the selected Drone
                    if (dronedynamicsList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No Dronedynamics exist for the selected Drone.", "No Dronedynamics", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                       // Show the Dronedynamics associated with the selected Drone using the table
                        showDronedynamicsData(dronedynamicsList);
                    }
                    }

                    exit = true;
                }
            }
        } while (!exit);
    }

    private static int showOptionDialog(String title, String message, Object[] options) {
        return JOptionPane.showOptionDialog(
                null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
    }

    private static void showDronetypeData(Dronetype dronetype) {
        showMessageDialog("Dronetype Information", dronetype.toString());
    }

    private static void showDroneData(Drone drone) {
        showMessageDialog("Drone Information", drone.toString());
    }

    private static void showDronedynamicsData(List<Dronedynamics> dronedynamicsList) {
       // Create a Dronedynamics table panel and add it to a dialogue
        JFrame frame = new JFrame("Dronedynamics Information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        DroneTablePanel tablePanel = new DroneTablePanel();
        tablePanel.updateMainTable(dronedynamicsList);

        frame.add(tablePanel);
        frame.setVisible(true);
    }

    private static void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
