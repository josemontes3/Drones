import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DroneTablePanel extends JPanel {
    private JTable mainTable;
    private DefaultTableModel mainTableModel;

    public DroneTablePanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 10, 10, 10));

        // Main Table
        String[] mainTableColumns = { "Timestamp", "URL", "Speed", "Alignment Roll", "Control Range", "Alignment Yaw", "Longitude", "Latitude",
                "Battery Status", "Last Seen", "Status"};
        mainTableModel = new DefaultTableModel(mainTableColumns, 0);
        mainTable = new JTable(mainTableModel);

        JScrollPane mainScrollPane = new JScrollPane(mainTable);
        mainScrollPane.setBorder(BorderFactory.createTitledBorder("Main Table"));
        add(mainScrollPane, BorderLayout.CENTER);
    }

    public void updateMainTable(List<Dronedynamics> dronedynamicsList) {
        // Clear the table before adding new data
        mainTableModel.setRowCount(0);

        // Add data to the table
        for (Dronedynamics dronedynamics : dronedynamicsList) {
            Object[] row = {
                    dronedynamics.getTimestamp(),
                    dronedynamics.getUrl(),
                    dronedynamics.getSpeed(),
                    dronedynamics.getAlignmentRoll(),
                    dronedynamics.getControlRange(),
                    dronedynamics.getAlignmentYaw(),
                    dronedynamics.getLongitude(),
                    dronedynamics.getLatitude(),
                    dronedynamics.getBatteryStatus(),
                    dronedynamics.getLastSeen(),
                    dronedynamics.getStatus()
            };
            mainTableModel.addRow(row);
        }
    }
}
