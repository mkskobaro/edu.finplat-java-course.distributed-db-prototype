package edu.finplatjavacourse.distributeddbprototype.processing.searchservice.supportsearchservice;

import edu.finplatjavacourse.distributeddbprototype.entity.Hotel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.function.Predicate;

public class FileWorker {
    private static final String PATH_FIlE = "data/hotel-list.txt";

    private FileWorker() {
    }

    static public Hotel parseLine(String line) throws IllegalArgumentException {
        String[] parts = line.split(",");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid format of the line");
        }
        long id = Long.parseLong(parts[0].trim());
        String name = parts[1].trim();
        name=name.substring(1,name.length()-1);
        long price = Long.parseLong(parts[2].trim());
        Hotel hotel = new Hotel(id, name, price);
        return hotel;
    }

    static public String getLine(Long numberStr) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_FIlE))) {
            for (int i = 1; i < numberStr; i++) {
                if (reader.readLine() == null) {
                    throw new IllegalArgumentException("Requested line number is out of range");
                }
            }
            String line = reader.readLine();

            if (line == null) {
                throw new IllegalArgumentException("Requested line number is out of range");
            }

            return line;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Error parsing data: " + e.getMessage());
            return null;
        }
    }

    static public List<Hotel> getAllDataProcessedSubstringSearch(Predicate<Hotel> predicate) {
        List<Hotel> allHotels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_FIlE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Hotel currentHotel = FileWorker.parseLine(line);
                if (predicate.test(currentHotel))
                    allHotels.add(currentHotel);
            }
            return allHotels;
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }

    }


}
