package org.dimi;

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

    public static Sphere[] SPHERES = new Sphere[]{
            new Sphere(new Vector3(0, -1, 3), 1, Color.RED.getRGB()),
            new Sphere(new Vector3(2, 0, 4), 1, Color.GREEN.getRGB()),
            new Sphere(new Vector3(-2, 0, 4), 1, Color.BLUE.getRGB()),
    };
    public static int BACKGROUND_COLOR = Color.BLACK.getRGB();

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(CANVAS_WIDTH, CANVAS_HEIGHT);

        BufferedImage image = new BufferedImage(CANVAS_WIDTH, CANVAS_HEIGHT, BufferedImage.TYPE_INT_RGB);

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
        redSphereXField.setText(String.valueOf(SPHERES[0].getCenter().getX()));
        redSphereSection.add(redSphereXField);
        JTextField redSphereYField = new JTextField(3);
        redSphereYField.setText(String.valueOf(SPHERES[0].getCenter().getY()));
        redSphereSection.add(redSphereYField);
        JTextField redSphereZField = new JTextField(3);
        redSphereZField.setText(String.valueOf(SPHERES[0].getCenter().getZ()));
        redSphereSection.add(redSphereZField);

        JPanel greenSphereSection = new JPanel();
        JLabel greenSphereLabel = new JLabel("GREEN ");
        greenSphereSection.add(greenSphereLabel);
        JTextField greenSphereXField = new JTextField(3);
        greenSphereXField.setText(String.valueOf(SPHERES[1].getCenter().getX()));
        greenSphereSection.add(greenSphereXField);
        JTextField greenSphereYField = new JTextField(3);
        greenSphereYField.setText(String.valueOf(SPHERES[1].getCenter().getY()));
        greenSphereSection.add(greenSphereYField);
        JTextField greenSphereZField = new JTextField(3);
        greenSphereZField.setText(String.valueOf(SPHERES[1].getCenter().getZ()));
        greenSphereSection.add(greenSphereZField);

        JPanel blueSphereSection = new JPanel();
        JLabel blueSphereLabel = new JLabel("BLUE  ");
        blueSphereSection.add(blueSphereLabel);
        JTextField blueSphereXField = new JTextField(3);
        blueSphereXField.setText(String.valueOf(SPHERES[2].getCenter().getX()));
        blueSphereSection.add(blueSphereXField);
        JTextField blueSphereYField = new JTextField(3);
        blueSphereYField.setText(String.valueOf(SPHERES[2].getCenter().getY()));
        blueSphereSection.add(blueSphereYField);
        JTextField blueSphereZField = new JTextField(3);
        blueSphereZField.setText(String.valueOf(SPHERES[2].getCenter().getZ()));
        blueSphereSection.add(blueSphereZField);

        JPanel actionsSection = new JPanel();
        JButton quitButton = new JButton("Quit");
        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(e -> {
            String redSphereXString = redSphereXField.getText();
            String redSphereYString = redSphereYField.getText();
            String redSphereZString = redSphereZField.getText();

            if (redSphereXString.isEmpty() || redSphereYString.isEmpty() || redSphereZString.isEmpty()) return;

            double redSphereX = Double.parseDouble(redSphereXString);
            double redSphereY = Double.parseDouble(redSphereYString);
            double redSphereZ = Double.parseDouble(redSphereZString);

            SPHERES[0] = new Sphere(new Vector3(redSphereX, redSphereY, redSphereZ), 1, Color.RED.getRGB());

            redSphereXField.setText(redSphereXString);
            redSphereYField.setText(redSphereYString);
            redSphereZField.setText(redSphereZString);

            String greenSphereXString = greenSphereXField.getText();
            String greenSphereYString = greenSphereYField.getText();
            String greenSphereZString = greenSphereZField.getText();

            if (greenSphereXString.isEmpty() || greenSphereYString.isEmpty() || greenSphereZString.isEmpty()) return;

            double greenSphereX = Double.parseDouble(greenSphereXString);
            double greenSphereY = Double.parseDouble(greenSphereYString);
            double greenSphereZ = Double.parseDouble(greenSphereZString);

            SPHERES[1] = new Sphere(new Vector3(greenSphereX, greenSphereY, greenSphereZ), 1, Color.GREEN.getRGB());

            greenSphereXField.setText(greenSphereXString);
            greenSphereYField.setText(greenSphereYString);
            greenSphereZField.setText(greenSphereZString);


            String blueSphereXString = blueSphereXField.getText();
            String blueSphereYString = blueSphereYField.getText();
            String blueSphereZString = blueSphereZField.getText();

            if (blueSphereXString.isEmpty() || blueSphereYString.isEmpty() || blueSphereZString.isEmpty()) return;

            double blueSphereX = Double.parseDouble(blueSphereXString);
            double blueSphereY = Double.parseDouble(blueSphereYString);
            double blueSphereZ = Double.parseDouble(blueSphereZString);

            SPHERES[2] = new Sphere(new Vector3(blueSphereX, blueSphereY, blueSphereZ), 1, Color.BLUE.getRGB());

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
        for (Sphere sphere : SPHERES) {
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
        return closestSphere == null ? BACKGROUND_COLOR : closestSphere.getColor();
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

        int t1 = (int) ((-b + Math.sqrt(discriminant)) / (2 * a));
        int t2 = (int) ((-b - Math.sqrt(discriminant)) / (2 * a));

        return new double[]{t1, t2};
    }

    //
    private static int screenX(int canvasX) {
        return CANVAS_WIDTH / 2 + canvasX;
    }

    private static int screenY(int canvasY) {
        return CANVAS_HEIGHT / 2 - canvasY - 1; // TODO. Que hago con ese -1 ?
    }
}