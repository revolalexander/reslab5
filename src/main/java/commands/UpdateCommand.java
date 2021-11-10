package commands;

import DOM.Ticket;
import utils.CollectionManager;
import utils.Console;
import utils.Logger;
import utils.TicketAsker;

/**
 * Updates the element by its key
 * **/
public class UpdateCommand extends AbstractCommand{
    private CollectionManager collectionManager;
    private TicketAsker ticketAsker;
    private Console console;
    public UpdateCommand(Console console, CollectionManager collectionManager, TicketAsker ticketAsker){
        super("update","updates the element with specified id");
        this.collectionManager = collectionManager;
        this.ticketAsker = ticketAsker;
        this.console = console;
    }

    /**
     * @see Executable
     * @param id that will be used to perform the update
     * **/
    @Override
    public boolean execute(String id){
        try{
            if(id == null || id.isEmpty()) {
                Logger.error("id is empty or null");
                return false;
            }
            Ticket ticket = collectionManager.read(Integer.parseInt(id));
            if(ticket == null) {
                console.println("No ticket with such id exists");
            }
            if(ticketAsker.ask("Do you want to change the name?")) ticket.setName(ticketAsker.askName());
            if(ticketAsker.ask("Do you want to change the coordinates?")){
                if(ticketAsker.ask("Do you want to change the x coordinate?")) ticket.getCoordinates().setX(ticketAsker.askX());
                if(ticketAsker.ask("Do you want to change the y coordinate?")) ticket.getCoordinates().setY(ticketAsker.askY());
            }
            if(ticketAsker.ask("Do you want to change the price?")) ticket.setPrice(ticketAsker.askPrice());
            if(ticketAsker.ask("Do you want to change the comment?")) ticket.setComment(ticketAsker.askComment());
            ticket.setRefundable(ticketAsker.ask("Is it refundable?"));
            if(ticketAsker.ask("Do you want to change the ticket type?")) ticket.setTicketType(ticketAsker.askTicketType());
            if(ticketAsker.ask("Do you want to change the venue?")){
                if(ticketAsker.ask("Do you want to change the name of the venue?")) ticket.getVenue().setName(ticketAsker.askName());
                if(ticketAsker.ask("Do you want to change the capacity of the venue?")) ticket.getVenue().setCapacity(ticketAsker.askCapacity());
                if(ticketAsker.ask("Do you want to change the type of the venue?")) ticket.getVenue().setType(ticketAsker.askVenueType());
                if(ticketAsker.ask("Do you want to change the address?")) {
                    if(ticketAsker.ask("Do you want to change the street of the address?")) ticket.getVenue().getAddress().setStreet(ticketAsker.askStreet());
                    if(ticketAsker.ask("Do you want to change the street of the zip code?")) ticket.getVenue().getAddress().setZipCode(ticketAsker.askZipCode());
                    if(ticketAsker.ask("Do you want to change the street of the location?")){
                        if(ticketAsker.ask("Do you want to change x coordinate?")) ticket.getVenue().getAddress().getTown().setX((int)ticketAsker.askX());
                        if(ticketAsker.ask("Do you want to change y coordinate?")) ticket.getVenue().getAddress().getTown().setY(ticketAsker.askLocY());
                        if(ticketAsker.ask("Do you want to change z coordinate?")) ticket.getVenue().getAddress().getTown().setZ(ticketAsker.askZ());
                    }
                }
            }
            return true;
        } catch (NumberFormatException e){
            System.out.println("Key must be a number");
            return false;
        }
    }
}