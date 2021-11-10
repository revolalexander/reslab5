package utils;

import DOM.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Gets all information about ticket or its components from user
 * **/
public class TicketAsker {
    private Scanner scanner;
    private Console console;
    private final double MIN_PRICE = 0d;
    private final Long MAX_Y = 3L;
    private final Long MIN_CAPACITY = 0L;
    private final int MAX_STREET_LENGTH = 56;
    private final int MIN_ZIP_CODE_LENGTH = 9;
    private final String YES = "y";
    private final String NO = "n";
    public TicketAsker(Scanner scanner, Console console){
        this.scanner = scanner;
        this.console = console;
    }
    /**
     * Sets new console
     * **/
    public void setScanner(Scanner scanner){
        this.scanner = scanner;
    }

    public void error(String fatal, String nonFatal){
        if (!console.isInteractive()){
            Logger.error(fatal);
            System.exit(0);
        } else {
            console.println(nonFatal);
        }
    }
    /**
     * Asks the user specified question
     * @return true if user's answer is yes, else false;
     * @param question
     * **/
    public boolean ask(String question){
        String resQuestion = question + " (y/n): ";
        String answer;
        while(console.isInteractive() || scanner.hasNextLine()){
            console.println(resQuestion);
            answer = scanner.nextLine().trim();
            if (!answer.equals(YES) && !answer.equals(NO)) {
                continue;
            }
            return answer.equals(YES);
        }
        return false;
    }

    /**
     * Asks all information about ticket
     * @return Ticket
     * **/
    public Ticket askTicket(){
        return new Ticket(
                askName(),
                askCoordinates(),
                askPrice(),
                askComment(),
                ask("Is it refundable?"),
                askTicketType(),
                askVenue()
        );
    }

    public Venue askVenue(){
        console.println("Enter the venue: ");
        Venue venue;
        String name = askName();
        Long capacity = askCapacity();
        VenueType venueType = askVenueType();
        Address address = askAddress();
        return new Venue(name,capacity,venueType,address);
    }

    public Address askAddress(){
        console.println("Enter the address: ");
        String street = askStreet();
        String zipCode = askZipCode();
        Location town = askLocation();
        return new Address(street,zipCode,town);
    }

    public Location askLocation(){
        console.println("Enter the location: ");
        int x = Double.valueOf(askX()).intValue();
        Float y = askLocY();
        Double z = askZ();
        return new Location(x,y,z);
    }

    public String askZipCode(){
        console.println("Enter zip code: ");
        String zipCode = "";
        while(console.isInteractive() || scanner.hasNextLine()){
            zipCode = scanner.nextLine().trim();
            if (zipCode != null && zipCode.length() < MIN_ZIP_CODE_LENGTH) {
                console.println("Zip code length must be more than " + MIN_ZIP_CODE_LENGTH);
                continue;
            };
            if (zipCode.isEmpty()){
                continue;
            }
            break;
        }
        return zipCode;
    }

    public String askStreet(){
        console.println("Enter the street: ");
        String street = "";
        while(console.isInteractive() || scanner.hasNextLine()){
            street = scanner.nextLine().trim();
            if (street != null && street.length() > MAX_STREET_LENGTH) {
                console.println("Street length must be less than " + MAX_STREET_LENGTH);
                continue;
            };
            if (street.isEmpty()){
                continue;
            }
            break;
        }
        return street;
    }

    public VenueType askVenueType(){
        console.println("Enter the venue type (LOFT, OPEN_AREA, THEATER) : ");
        String venueType;
        VenueType result = VenueType.LOFT;
        while(console.isInteractive() || scanner.hasNextLine()){
            venueType = scanner.nextLine().trim();
            try{
                result = VenueType.valueOf(venueType);
                break;
            } catch (IllegalArgumentException e){
                error("Error parsing venue type in TicketAsker askVenueType()", "Incorrect venue type");
                continue;
            }
        }
        return result;
    }

    public Long askCapacity(){
        console.println("Enter capacity: ");
        Long capacity = 0L;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                capacity = Long.parseLong(scanner.nextLine().trim());
                if (capacity != null && capacity <= MIN_CAPACITY){
                    console.println("Capacity must be more than 0");
                    continue;
                }
                break;
            } catch (NumberFormatException e){
                error("Error parsing capacity in TicketAsker askCapacity()", "Incorrect capacity");
                continue;
            }
        }
        return capacity;
    }

    /**
     * Asks the name
     * @return name
     * **/
    public String askName(){
        console.println("Enter the name: ");
        String name = "";
        while(console.isInteractive() || scanner.hasNextLine()){
            name = scanner.nextLine().trim();
            if (name == null || name.isEmpty()) {
                continue;
            };
            break;
        }
        return name;
    }

    public String askComment(){
        console.println("Enter the comment: ");
        String comment = "";
        while(console.isInteractive() || scanner.hasNextLine()){
            comment = scanner.nextLine().trim();
            if (comment != null && comment.isEmpty()) {
                continue;
            };
            break;
        }
        return comment;
    }

    public TicketType askTicketType(){
        console.println("Enter the ticket type (VIP, BUDGETARY, CHEAP): ");
        String ticketType;
        TicketType result = TicketType.CHEAP;
        while(console.isInteractive() || scanner.hasNextLine()){
            ticketType = scanner.nextLine().trim();
            try{
                result = TicketType.valueOf(ticketType);
                break;
            } catch (IllegalArgumentException e){
                error("Error parsing ticket type in TicketAsker askTicketType()", "Incorrect ticket type");
                continue;
            }
        }
        return result;
    }
    /**
     * Asks the X coordinate
     * @return X coordinate
     * **/
    public double askX(){
        console.println("Enter x: ");
        double x = 0;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                x = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                error("Error parsing x in TicketAsker askX()", "Incorrect x");
                continue;
            }
            break;
        }
        return x;
    }
    public Double askZ(){
        console.println("Enter z: ");
        Double z = 0D;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                z = Double.parseDouble(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                error("Error parsing z in TicketAsker askZ()", "Incorrect z");
                continue;
            }
            break;
        }
        return z;
    }

    public float askLocY(){
        console.println("Enter y: ");
        float y = 0;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                y = Float.parseFloat(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                error("Error parsing y in TicketAsker askLocY()", "Incorrect y");
                continue;
            }
            break;
        }
        return y;
    }
    /**
     * Asks the Y coordinate
     * @return Y coordinate
     * **/
    public Long askY(){
        console.println("Enter y: ");
        Long y = 0L;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                y = Long.parseLong(scanner.nextLine().trim());
                if (y > MAX_Y){
                    console.println("y cannot be more than " + MAX_Y);
                    continue;
                }
            } catch (NumberFormatException e){
                error("Error parsing y in TicketAsker askY()", "Incorrect y");
                continue;
            }
            break;
        }
        return y;
    }

    public Coordinates askCoordinates(){
        console.println("Enter the coordinates: ");
        Double x = askX();
        Long y = askY();
        return new Coordinates(x,y);
    }
    /**
     * Asks the price
     * @return price
     * **/
    public double askPrice(){
        console.println("Enter the price: ");
        double price = 0;
        while(console.isInteractive() || scanner.hasNextLine()){
            try{
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price <= MIN_PRICE){
                    console.println("Price must be more than " + MIN_PRICE);
                    continue;
                }
            } catch (NumberFormatException e){
                error("Error parsing price in TicketAsker askPrice()", "Incorrect price");
                continue;
            }
            break;
        }
        return price;
    }
}