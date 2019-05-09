
import java.io.*;
import java.util.*;


public class Rectangle {
    private File file;
    private int[] coordsOfPoint = new int[2];
    private int[][] rectangleCoords = new int[4][2];

    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle();
        /*System.out.println(Arrays.deepToString(rectangle.rectangleCoords));
        System.out.println(Arrays.toString(rectangle.coordsOfPoint));*/
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

        if (Arrays.equals(coordsOfPoint, rectangleCoords[0]) ||
                Arrays.equals(coordsOfPoint, rectangleCoords[1]) ||
                Arrays.equals(coordsOfPoint, rectangleCoords[2]) ||
                Arrays.equals(coordsOfPoint, rectangleCoords[3])) {
            System.out.println("The point - vertex of the rectangle");
            return;
        }

        if( ((coordsOfPoint[0] >= rectangleCoords[0][0] && coordsOfPoint[0] <= rectangleCoords[1][0] && coordsOfPoint[1] == rectangleCoords[0][1] ))) {
            System.out.println("The point on UPPER line of rectangle");
            return;
        }

        if( ((coordsOfPoint[0] >= rectangleCoords[0][0] && coordsOfPoint[0] <= rectangleCoords[1][0] && coordsOfPoint[1] == rectangleCoords[3][1] ))) {
            System.out.println("The point on DOWN line of rectangle");
            return;
        }
        if( ((coordsOfPoint[1] <= rectangleCoords[0][1] && coordsOfPoint[1] >= rectangleCoords[3][1] && coordsOfPoint[0] == rectangleCoords[0][0] ))) {
            System.out.println("The point on LEFT line of rectangle");
            return;
        }
        if( ((coordsOfPoint[1] <= rectangleCoords[1][1] && coordsOfPoint[1] >= rectangleCoords[2][1] && coordsOfPoint[0] == rectangleCoords[2][0] ))) {
            System.out.println("The point on RIGHT line of rectangle");
            return;
        }


        if( ((coordsOfPoint[0] > rectangleCoords[0][0] && coordsOfPoint[0] < rectangleCoords[1][0]))
        && (coordsOfPoint[1] < rectangleCoords[0][1] && coordsOfPoint[1] > rectangleCoords[3][1])) {
            System.out.println("The point inside of the rectangle");
            return;
        }
        else System.out.println("The point NOT inside of the rectangle");
    }

    private int[][] getNumbersFromFile() {
        int[][] rectangleCoords = new int[4][2];
        //if file writed in OS Windows and started with BOM symbol
        Character bom = 65279;
        try {
            //BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("C:\\IdeaProjects\\rectangle\\src\\main\\resources\\coords.txt")));
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(this.file)));
            int count = 0;
            while (reader.ready()) {
                String[] str = reader.readLine().replace(bom, ' ').trim().split(" ");
                rectangleCoords[count][0] = Integer.valueOf(str[0]);
                rectangleCoords[count][1] = Integer.valueOf(str[1]);
                count++;
            }
        } catch (IOException e) {
            System.out.println("Error when reading numbers from file. Maybe file have wrong format.");
            e.printStackTrace();
        }
        return rectangleCoords;

    }

    private int[] getCoordinates() {
        int[] coords = new int[2];
        System.out.println("Please enter coordinates for point to analyze. Use space as separator: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String[] str = reader.readLine().split(" ");
            if (str.length > 2) throw new IOException("Wrong input for coordinates point");
            coords[0] = Integer.parseInt(str[0]);
            coords[1] = Integer.parseInt(str[1]);
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
