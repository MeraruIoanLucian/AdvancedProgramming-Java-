package org.example;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        ImageRepo repo = new ImageRepo();

        String sampleFilePath = "/Users/ionutmeraru/Documents/Web Technologies/Lab3/images/Monke.jpg";

        ImageItem sampleImage = new ImageItem(
                "Monke",
                LocalDate.now(),
                Arrays.asList("demo", "sample"),
                sampleFilePath
        );
        repo.addImage(sampleImage);

        System.out.println("Images in repository:");
        repo.getImages().forEach(System.out::println);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name of the image to display:");
        String name = scanner.nextLine();

        for (ImageItem image : repo.getImages()) {
            if (image.name().equalsIgnoreCase(name)) {
                repo.displayImage(image);
                break;
            }
        }
        scanner.close();
    }
}