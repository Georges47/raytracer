package org.dimi;

import org.dimi.light.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static final int CANVAS_WIDTH = 900;
    public static final int CANVAS_HEIGHT = 900;

    public static final int VIEWPORT_WIDTH = 1;
    public static final int VIEWPORT_HEIGHT = 1;

    public static final Vector3 CAMERA_POSITION = Vector3.origin;
    public static final int CAMERA_TO_VIEWPORT_DISTANCE = 1;

    public static Scene scene = new Scene(
        new Sphere[]{
            new Sphere(new Vector3(0, -1, 3), 1, Color.RED),
            new Sphere(new Vector3(2, 0, 4), 1, Color.GREEN),
            new Sphere(new Vector3(-2, 0, 4), 1, Color.BLUE),
            new Sphere(new Vector3(0, -5001, 0), 5000, Color.YELLOW),
        },
        new Light[]{
            new AmbientLight(0.2f),
            new PointLight(0.6f, new Vector3(2, 1, 0)),
            new DirectionalLight(0.2f, new Vector3(1, 4, 4))
        }
    );
    public static int BACKGROUND_COLOR = Color.BLACK.getRGB();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        BufferedImage image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_ARGB);

        renderSpheres(image);

        JPanel renderPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, null);
            }
        };

        JPanel redSphereSection = new JPanel();
        JLabel redSphereLabel = new JLabel("RED    ");
        redSphereSection.add(redSphereLabel);
        JTextField redSphereXField = new JTextField(3);
        redSphereXField.setText(String.valueOf(scene.getSpheres()[0].getCenter().getX()));
        redSphereSection.add(redSphereXField);
        JTextField redSphereYField = new JTextField(3);
        redSphereYField.setText(String.valueOf(scene.getSpheres()[0].getCenter().getY()));
        redSphereSection.add(redSphereYField);
        JTextField redSphereZField = new JTextField(3);
        redSphereZField.setText(String.valueOf(scene.getSpheres()[0].getCenter().getZ()));
        redSphereSection.add(redSphereZField);

        JPanel greenSphereSection = new JPanel();
        JLabel greenSphereLabel = new JLabel("GREEN ");
        greenSphereSection.add(greenSphereLabel);
        JTextField greenSphereXField = new JTextField(3);
        greenSphereXField.setText(String.valueOf(scene.getSpheres()[1].getCenter().getX()));
        greenSphereSection.add(greenSphereXField);
        JTextField greenSphereYField = new JTextField(3);
        greenSphereYField.setText(String.valueOf(scene.getSpheres()[1].getCenter().getY()));
        greenSphereSection.add(greenSphereYField);
        JTextField greenSphereZField = new JTextField(3);
        greenSphereZField.setText(String.valueOf(scene.getSpheres()[1].getCenter().getZ()));
        greenSphereSection.add(greenSphereZField);

        JPanel blueSphereSection = new JPanel();
        JLabel blueSphereLabel = new JLabel("BLUE  ");
        blueSphereSection.add(blueSphereLabel);
        JTextField blueSphereXField = new JTextField(3);
        blueSphereXField.setText(String.valueOf(scene.getSpheres()[2].getCenter().getX()));
        blueSphereSection.add(blueSphereXField);
        JTextField blueSphereYField = new JTextField(3);
        blueSphereYField.setText(String.valueOf(scene.getSpheres()[2].getCenter().getY()));
        blueSphereSection.add(blueSphereYField);
        JTextField blueSphereZField = new JTextField(3);
        blueSphereZField.setText(String.valueOf(scene.getSpheres()[2].getCenter().getZ()));
        blueSphereSection.add(blueSphereZField);

        JPanel actionsSection = new JPanel();
        JButton quitButton = new JButton("Quit");
        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(e -> {
            String redSphereXString = redSphereXField.getText();
            String redSphereYString = redSphereYField.getText();
            String redSphereZString = redSphereZField.getText();

            if (redSphereXString.isEmpty() || redSphereYString.isEmpty() || redSphereZString.isEmpty()) return;

            double redSphereX = Float.parseFloat(redSphereXString);
            double redSphereY = Float.parseFloat(redSphereYString);
            double redSphereZ = Float.parseFloat(redSphereZString);

            scene.updateSphere(0, new Sphere(new Vector3(redSphereX, redSphereY, redSphereZ), 1, Color.RED));

            redSphereXField.setText(redSphereXString);
            redSphereYField.setText(redSphereYString);
            redSphereZField.setText(redSphereZString);

            String greenSphereXString = greenSphereXField.getText();
            String greenSphereYString = greenSphereYField.getText();
            String greenSphereZString = greenSphereZField.getText();

            if (greenSphereXString.isEmpty() || greenSphereYString.isEmpty() || greenSphereZString.isEmpty()) return;

            double greenSphereX = Float.parseFloat(greenSphereXString);
            double greenSphereY = Float.parseFloat(greenSphereYString);
            double greenSphereZ = Float.parseFloat(greenSphereZString);

            scene.updateSphere(1, new Sphere(new Vector3(greenSphereX, greenSphereY, greenSphereZ), 1, Color.GREEN));

            greenSphereXField.setText(greenSphereXString);
            greenSphereYField.setText(greenSphereYString);
            greenSphereZField.setText(greenSphereZString);


            String blueSphereXString = blueSphereXField.getText();
            String blueSphereYString = blueSphereYField.getText();
            String blueSphereZString = blueSphereZField.getText();

            if (blueSphereXString.isEmpty() || blueSphereYString.isEmpty() || blueSphereZString.isEmpty()) return;

            double blueSphereX = Float.parseFloat(blueSphereXString);
            double blueSphereY = Float.parseFloat(blueSphereYString);
            double blueSphereZ = Float.parseFloat(blueSphereZString);

            scene.updateSphere(2, new Sphere(new Vector3(blueSphereX, blueSphereY, blueSphereZ), 1, Color.BLUE));

            blueSphereXField.setText(blueSphereXString);
            blueSphereYField.setText(blueSphereYString);
            blueSphereZField.setText(blueSphereZString);

            renderSpheres(image);
            renderPanel.repaint();

        });

        quitButton.addActionListener(e -> {
            System.exit(0);
        });

        actionsSection.add(quitButton);
        actionsSection.add(updateButton);

        JPanel controlPanel = new JPanel(new GridLayout(4,1));
        controlPanel.add(redSphereSection);
        controlPanel.add(greenSphereSection);
        controlPanel.add(blueSphereSection);
        controlPanel.add(actionsSection);

        frame.add(renderPanel, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void renderSpheres(BufferedImage image) {
        for (int y = -CANVAS_HEIGHT / 2; y < CANVAS_HEIGHT / 2; y++) {
            for (int x = -CANVAS_WIDTH / 2; x < CANVAS_WIDTH / 2; x++) {
                Vector3 direction = canvasToViewport(x, y);
                int color = traceRay(CAMERA_POSITION, direction, 1, Integer.MAX_VALUE);
                image.setRGB(screenX(x), screenY(y), color);
            }
        }
    }

    //
    private static Vector3 canvasToViewport(int canvasX, int canvasY) {
        return new Vector3(viewportX(canvasX), viewportY(canvasY), CAMERA_TO_VIEWPORT_DISTANCE);
    }

    private static double viewportX(int canvasX) {
        return (double) (canvasX * VIEWPORT_WIDTH) / CANVAS_WIDTH;
    }

    private static double viewportY(int canvasY) {
        return (double) (canvasY * VIEWPORT_HEIGHT) / CANVAS_HEIGHT;
    }

    //
    private static int traceRay(Vector3 rayOrigin, Vector3 rayDirection, int tMin, int tMax) {
        double closestT = Integer.MAX_VALUE;
        Sphere closestSphere = null;
        for (Sphere sphere : scene.getSpheres()) {
            double[] ts = intersectRaySphere(rayOrigin, rayDirection, sphere);
            if (ts[0] >= tMin && ts[0] <= tMax && ts[0] < closestT) {
                closestT = ts[0];
                closestSphere = sphere;
            }
            if (ts[1] >= tMin && ts[1] <= tMax && ts[1] < closestT) {
                closestT = ts[1];
                closestSphere = sphere;
            }
        }
        if (closestSphere == null) return BACKGROUND_COLOR;
        Vector3 point = CAMERA_POSITION.add(rayDirection.scale(closestT));
        Vector3 normal = point.subtract(closestSphere.getCenter()).normalize();

        double intensity = computeLighting(point, normal);
        int red = closestSphere.getColor().getRed();
        int green = closestSphere.getColor().getGreen();
        int blue = closestSphere.getColor().getBlue();
        return new Color((float) (red * intensity/255), (float) (green * intensity/255), (float) (blue * intensity/255)).getRGB();
    }

    private static double[] intersectRaySphere(Vector3 rayOrigin, Vector3 rayDirection, Sphere sphere) {
        double r = sphere.getRadius();
        Vector3 CO = rayOrigin.subtract(sphere.getCenter());

        double a = rayDirection.dot(rayDirection);
        double b = 2 * CO.dot(rayDirection);
        double c = CO.dot(CO) - r * r;

        double discriminant = b * b - 4 * a * c;
        if (discriminant < 0) {
            return new double[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        double t1 = (-b + Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b - Math.sqrt(discriminant)) / (2 * a);

        return new double[]{t1, t2};
    }

    //
    private static int screenX(int canvasX) {
        return CANVAS_WIDTH / 2 + canvasX;
    }

    private static int screenY(int canvasY) {
        return CANVAS_HEIGHT / 2 - canvasY - 1; // TODO. Que hago con ese -1 ?
    }

    // Lighting
    private static double computeLighting(Vector3 point, Vector3 normal) {
        LightingProcessor lightingProcessor = new LightingProcessor(point, normal);
        for (Light light : scene.getLights()) {
            light.accept(lightingProcessor);
        }
        return lightingProcessor.getIntensity();
    }
}