import java.util.ArrayList;

/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {

            boolean successful = selectedLot.bidFor(new Bid(bidder,value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.

                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * imprime por pantalla los objetos subastados y muestra si tiene pujas o no, la cantidad y la persona 
     */
    public void close()
    {
        int cont = 0;//
        while(cont < lots.size())
        {
            if (lots.get(cont).getHighestBid() !=null)
            {
                System.out.println("Nombre del pujador " +
                    lots.get(cont).getHighestBid().getBidder().getName()+
                    " cantidad = " + lots.get(cont).getHighestBid().getValue()+
                    "detalles = " + lots.get(cont).getDescription());
            }
            else
            {
                System.out.println("Este objeto ("+lots.get(cont).getDescription()+")"+
                    "no tiene niguna puja");
            }
            cont = cont +1;
        }

    }

    /**
     * Partiendo del código terminado de la actividad 0060, 
     * añade un método denominado getUnsold a la clase Auction que devuelva una colección
     * de todos los items por los que no habido ninguna puja en este momento; 
     * este método no debe imprimir nada por pantalla.
     * Testea los cambios y haz un co
     */
    public ArrayList getUnsold()
    {
        ArrayList<Lot> unsold = new ArrayList<Lot>();
        int indice = 0;
        while (indice< lots.size())
        {
            if (lots.get(indice).getHighestBid() == null)
            {
                unsold.add(lots.get(indice));

            }
            indice = indice + 1 ;

        }
        return unsold;
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                    selectedLot.getNumber() +
                    " was returned instead of " +
                    lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }
}
