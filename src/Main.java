import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        boolean exit = false;

        do {
            // Crear un menú para que el usuario elija qué información mostrar
            String[] options = {"Dronedynamics", "Dronetype", "Drone"};
            int choice = showOptionDialog("Choose Information", "Select the type of information to display:", options);

            switch (choice) {
                case 0:
                    // Obtener datos de Dronedynamics desde la API
                    API_dronedynamics apiDronedynamics = new API_dronedynamics();
                    List<Dronedynamics> dronedynamicsList = apiDronedynamics.dronedyna();
                    showObjectInformation(dronedynamicsList, "Dronedynamics Information");
                    break;
                case 1:
                    // Obtener datos de Dronetype desde la API
                    API_dronetypes apiDronetypes = new API_dronetypes();
                    List<Dronetype> dronetypeList = apiDronetypes.dronetype();
                    showObjectInformation(dronetypeList, "Dronetype Information");
                    break;
                case 2:
                    // Obtener datos de Drone desde la API
                    API_drones apiDrones = new API_drones();
                    List<Drone> droneList = apiDrones.drone();
                    showObjectInformation(droneList, "Drone Information");
                    break;
                default:
                    System.out.println("Invalid choice");
            }

            // Preguntar al usuario si desea volver al menú principal
            int option = JOptionPane.showConfirmDialog(null, "Do you want to go back to the main menu?", "Go Back", JOptionPane.YES_NO_OPTION);
            exit = (option != JOptionPane.YES_OPTION);
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

    private static <T> void showObjectInformation(List<T> objectList, String title) {
        // Crear una lista de IDs de objetos para que el usuario elija cuál mostrar
        String[] objectIds = objectList.stream().map(obj -> getIdFromObject(obj)).toArray(String[]::new);

        // Permitir al usuario seleccionar qué objeto mostrar
        int objectChoice = showOptionDialog(title, "Select the object to display:", objectIds);

        // Mostrar la información completa del objeto seleccionado
        if (objectChoice >= 0 && objectChoice < objectList.size()) {
            T selectedObject = objectList.get(objectChoice);
            showMessageDialog(title, selectedObject.toString());
        } else {
            System.out.println("Invalid object choice");
        }
    }

    // Método para obtener el ID de un objeto
    private static <T> String getIdFromObject(T object) {
        // Llama a la función getId() en cada objeto para obtener su ID
        try {
            java.lang.reflect.Method method = object.getClass().getMethod("getId");
            String id = String.valueOf(method.invoke(object));
            return "ID: " + id;
        } catch (Exception e) {
            e.printStackTrace();
            return "Error getting ID";
        }
    }

    private static void showMessageDialog(String title, String message) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
