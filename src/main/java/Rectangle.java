import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.*;


public class Rectangle {
    private File file;
    private Double[] coordsOfPoint = new Double[2];
    private Double[][] rectangleCoords = new Double[4][2];

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
    }

    public Rectangle() {
        readData();
        analyzePoint();


    }


    private void readData() {
        this.file = getFileName();
        this.coordsOfPoint = getCoordinates();
        this.rectangleCoords = getNumbersFromFile();
    }

    private void analyzePoint() {
        Point2D leftUp = new Point2D.Double(rectangleCoords[0][0], rectangleCoords[0][1]);
        Point2D rightUp = new Point2D.Double(rectangleCoords[1][0], rectangleCoords[1][1]);
        Point2D rightDown = new Point2D.Double(rectangleCoords[2][0], rectangleCoords[2][1]);
        Point2D leftDown = new Point2D.Double(rectangleCoords[3][0], rectangleCoords[3][1]);

        Polygon polygon = new Polygon();
        polygon.addPoint(rectangleCoords[0][0].intValue(), rectangleCoords[0][1].intValue());
        polygon.addPoint(rectangleCoords[1][0].intValue(), rectangleCoords[1][1].intValue());
        polygon.addPoint(rectangleCoords[2][0].intValue(), rectangleCoords[2][1].intValue());
        polygon.addPoint(rectangleCoords[3][0].intValue(), rectangleCoords[3][1].intValue());

        Point2D.Double userPoint = new Point2D.Double(coordsOfPoint[0], coordsOfPoint[1]);

        Line2D lineUp = new Line2D.Double(leftUp, rightUp);
        Line2D lineRight = new Line2D.Double(rightUp, rightDown);
        Line2D lineDown = new Line2D.Double(rightDown, leftDown);
        Line2D lineLeft = new Line2D.Double(leftDown, leftUp);

        if (userPoint.equals(leftUp) ||
                userPoint.equals(rightUp) ||
                userPoint.equals(rightDown) ||
                userPoint.equals(leftDown)) {
            System.out.println("The point - vertex of the rectangle");
            return;
        }

        if (lineUp.intersects(userPoint.x, userPoint.y, 1,1) ||
                lineDown.intersects(userPoint.x, userPoint.y, 1,1) ||
                lineRight.intersects(userPoint.x, userPoint.y, 1,1) ||
                lineLeft.intersects(userPoint.x, userPoint.y, 1,1)) {
            System.out.println("The point on line(edge) of rectangle");
            return;
        }

        if (polygon.contains(userPoint)) {
            System.out.println("The point inside of the rectangle");
            return;
        }
        else System.out.println("The point NOT inside of the rectangle");
    }

    private Double[][] getNumbersFromFile() {
        Double[][] rectangleCoords = new Double[4][2];
        //if file writed in OS Windows and started with BOM symbol
        Character bom = 65279;
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\IdeaProjects\\rectangle\\src\\main\\resources\\coords.txt")));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
            int count = 0;
            while (reader.ready()) {
                String[] str = reader.readLine().replace(bom, ' ').trim().split(" ");
                rectangleCoords[count][0] = Double.valueOf(str[0]);
                rectangleCoords[count][1] = Double.valueOf(str[1]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error when reading numbers from file. Maybe file have wrong format.");
            e.printStackTrace();
        }
        return rectangleCoords;

    }

    private Double[] getCoordinates() {
        Double[] coords = new Double[2];
        System.out.println("Please enter coordinates for point to analyze. Use space as separator: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] str = reader.readLine().split(" ");
            if (str.length > 2) throw new IOException("Wrong input for coordinates point");
            coords[0] = Double.valueOf(str[0]);
            coords[1] = Double.valueOf(str[1]);
        } catch (IOException e) {
            System.out.println("Error, when reading coordinates. Maybe you write something illegal in console.\n");
            e.printStackTrace();
        }
        return coords;
    }


    private File getFileName() {
        System.out.println("Please enter destination of file with rectangle coordinats: ");
        File readed = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String fileName = reader.readLine();
            readed = new File(fileName);
            if (!readed.exists() || readed.isDirectory())
                throw new IllegalArgumentException("File not exist or its directory.");

        } catch (IOException e) {
            System.out.println("Error, when reading filename. Maybe you write something illegal in console.\n" + e.toString());
        }
        return readed;
    }


}
