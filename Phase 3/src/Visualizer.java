import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class Visualizer extends Application {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    private Box truck;
    private PerspectiveCamera camera;

    private double lastX;
    private double lastY;

    @Override
    public void start(Stage stage) throws Exception {
        // Drawing a box to represent the Truck
        truck = new Box();

        // Setting the properties of the Truck
        double scale = 70.0;
        truck.setWidth(16.5 * scale);
        truck.setHeight(4.0 * scale);
        truck.setDepth(2.5 * scale);

        // Creating a Group object
        Group group = new Group();
        group.getChildren().add(truck);

        // Creating a scene object colored in black
        camera = new PerspectiveCamera();
        Scene scene = new Scene(group, WIDTH, HEIGHT);
        scene.setFill(Color.BLACK);
        scene.setCamera(camera);

        // Set truck's position to the center of the GUI
        group.translateXProperty().set(WIDTH / 2);
        group.translateYProperty().set(HEIGHT / 2);

        // Setting title to the Stage
        stage.setTitle("Truck Visualizer");

        // Adding scene to the stage
        stage.setScene(scene);

        // Displaying the contents of the stage
        stage.show();

        // Enable mouse interaction for rotating the box
        enableMouseInteraction(scene);
    }

    private void enableMouseInteraction(Scene scene) {
        scene.setOnMousePressed(event -> {
            lastX = event.getSceneX();
            lastY = event.getSceneY();
        });

        scene.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - lastX;
            double deltaY = event.getSceneY() - lastY;

            double deltaXAngle = deltaX / scene.getWidth() * 360;
            double deltaYAngle = deltaY / scene.getHeight() * 360;

            Rotate rotateX = new Rotate(deltaYAngle, Rotate.X_AXIS);
            Rotate rotateY = new Rotate(deltaXAngle, Rotate.Y_AXIS);

            truck.getTransforms().addAll(rotateX, rotateY);

            lastX = event.getSceneX();
            lastY = event.getSceneY();
        });
    }

    public static void main(String args[]) {
        launch(args);
    }
}
