package Code.PokemonCardGame;

//Imports the ArrayList and Random classes for card use.
import java.util.ArrayList;
import java.util.Random;

/**
 * @author Mia Watts
 * This class houses methods pertaining to the actual Pokemon game with 2 players along with
 * all methods associated with deck setup, building the deck, and printing instructions.
 */
public class PokemonCardGame {

    //Declares the deck, hand, prizePile, discardPile, activePokemon, and bench hands/cards.
    //Each one is an ArrayList that can be added to eventually.
    private ArrayList <Card> deck;
    private ArrayList <Card> hand;
    private ArrayList <Card> prizePile;
    private ArrayList <Card> discardPile;
    private ArrayList <Card> activePokemon;
    private ArrayList <Card> bench;

    /**
     * This constructor initializes each of the private variables as new ArrayLists of type Card.
     */
    public PokemonCardGame() {
        deck = new ArrayList<Card>();
        hand = new ArrayList<Card>();
        bench = new ArrayList<Card>();
        discardPile = new ArrayList<Card>();
        prizePile = new ArrayList<Card>();
        activePokemon = new ArrayList<Card>();
    }

    /**
     * Returns the current deck.
     * @return ArrayList deck object.
     */
    public ArrayList<Card> getDeck(){
        return deck;
    }

    /**
     * Returns the current hand.
     * @return ArrayList hand object.
     */
    public ArrayList<Card> getHand(){
        return hand;
    }

    /**
     * Returns the player's prize pile.
     * @return ArrayList prize pile object.
     */
    public ArrayList<Card> getPrizePile() { return prizePile; }

    /**
     * Returns the player's discard pile.
     * @return ArrayList discard pile object.
     */
    public ArrayList<Card> getDiscardPile() { return discardPile; }

    /**
     * Returns the player's current bench.
     * @return ArrayList bench object.
     */
    public ArrayList<Card> getBench() { return bench; }

    /**
     * Returns the player's active Pokemon.
     * @return ArrayList active Pokemon object.
     */
    public ArrayList<Card> getActivePokemon() { return activePokemon; }

    /**
     * Builds the deck with 10 Pokemon, 30 Trainers, and 20 Energy cards.
     */
    public void buildDeck(){
        //Adds 10 Pokemon.
        for(int i = 0; i < 10; i++){
            deck.add(new Pokemon());
        }

        //Adds 30 Trainer cards.
        for(int i = 0; i < 30; i++){
            deck.add(new Trainer());
        }

        //Adds 20 Energy cards.
        for(int i = 0; i < 20; i++){
            deck.add(new Energy());
        }
    }

    /**
     * Plays the NestBall card by finding all Pokemon, putting one on the bench, and reshuffling them.
     */
    public void playNestBall() {
        //Declares and initializes a temporary deck which will be eventually filled with Pokemon.
        ArrayList<Card> tempPokemon = new ArrayList<>();

        //Collects all Pokemon from the deck and puts them in the temporary deck.
        int i = 0;
        while(i < deck.size()){
            if(deck.get(i) instanceof Pokemon){
                tempPokemon.add(deck.get(i));
                deck.remove(i);
            } else{
                i++;
            }
        }

        //Prints out the current count of Pokemon found in the deck.
        System.out.println("Pokemon found so far: " + tempPokemon);
        System.out.println("Count: " + tempPokemon.size());

        //Randomly selects a Pokemon to be placed on the bench.
        Random rng = new Random();
        int saveRandomNumber = rng.nextInt(tempPokemon.size());
        bench.add(tempPokemon.get(saveRandomNumber));
        tempPokemon.remove(saveRandomNumber);

        //Reshuffles the Pokemon back into the deck.
        for(int j = 0; j < tempPokemon.size(); i++){
            deck.add(tempPokemon.get(i));
        }
    }

    /**
     * Builds a deck and then plays a NestBall trainer card.
     */
    public void nestPlay() {
        buildDeck();
        playNestBall();
    }

    /**
     * Takes a deck ArrayList parameter and converts instances of different Pokemon, Trainers,
     * or Energies into readable String language.
     * @param deck - ArrayList deck object that is used to convert the objects into
     *             String values so the items in the deck can be understood.
     * @return The result array which contains the String values of the deck.
     */
    public ArrayList<String> printDeck(ArrayList<Card> deck) {
        ArrayList<String> resultArray = new ArrayList<>();
        //Iterates through the deck and checks which instance it belongs to, then adds
        //a String identifier to the result array.
        for (Card currentCard : deck) {
            if (currentCard instanceof Pokemon) {
                resultArray.add("Pokemon");
            } else if (currentCard instanceof Trainer) {
                resultArray.add("Trainer");
            } else if (currentCard instanceof RareCandy) {
                resultArray.add("Rare Candy");
            } else {
                resultArray.add("Energy");
            }
        }
        return resultArray;
    }

    /**
     * Takes a hand ArrayList parameter and converts instances of Pokemon, Trainers, or Energies
     * into readable String language.
     * @param hand - ArrayList hand object that is used to convert the objects into
     *             String values so the items in the hand can be understood.
     * @return The result array which contains the String values of the deck.
     */
    public ArrayList<String> printHand(ArrayList<Card> hand) {
        ArrayList<String> resultArray = new ArrayList<>();
        //Iterates through the hand to determine what types of cards are there.
        for (Card currentCard : hand) {
            if (currentCard instanceof Pokemon) {
                resultArray.add("Pokemon");
            } else if (currentCard instanceof Trainer) {
                resultArray.add("Trainer");
            } else if (currentCard instanceof RareCandy) {
                resultArray.add("Rare Candy");
            } else {
                resultArray.add("Energy");
            }
        }
        return resultArray;
    }

    /**
     * String method called printInstructions that prints the instructions for the game.
     * @return A String that contains the instructions for the game.
     */
    public String printInstructions() {
        return "Pick a Pokemon to attack with! If " +
                "the other player's prize pile runs out before yours, you win!";
    }

    /**
     * Originally used for the Monte Carlo simulation. The method clears the deck and hand, then
     * adds one Pokemon to the deck, leaving the rest of the space for Energy cards. (59 Energy,
     * 1 Pokemon).
     */
    public void newDeckOnePokemon() {
        deck.clear();
        hand.clear();
        int newDeckSize = 60;

        //Adds 1 Pokemon.
        deck.add(new Pokemon());

        //Adds 59 Energy cards.
        for(int i = 1; i <= newDeckSize; i++) {
            deck.add(new Energy());
        }
    }

    /**
     * Takes a parameter and adds that many Pokemon to the deck based on whatever the parameter number is.
     * @param pokemonCount - Integer value that represents how many Pokemon should be added
     *                     to the deck.
     */
    public void newDeckMultiplePokemon(int pokemonCount) {
        deck.clear();
        hand.clear();
        int newDeckSize = 60;

        //Adds as many Pokemon as is specified in the parameter pokemonCount.
        for(int i = 1; i <= pokemonCount; i++) {
            deck.add(new Pokemon());
        }

        //Adds Energies into the remaining deck space.
        for(int i = pokemonCount; i <= newDeckSize; i++) {
            deck.add(new Energy());
        }
    }

    /**
     * Creates the game deck for the player game.
     */
    public void gameDeck() {

    }

    /**
     * This method uses the Random class to randomly draw and select a card from the deck,
     * returning the chosen card.
     * @return The card that was randomly drawn from the deck.
     */
    public Card drawCard() {
        Random rng = new Random();
        int cardIndex = rng.nextInt(deck.size()); //Find random card.
        Card drawnCard = deck.get(cardIndex);
        deck.remove(cardIndex);
        return drawnCard;
    }

    /**
     * Draws a hand of 7 cards for a player.
     */
    public void drawHand() {
        for(int i = 0; i < 7; i++) {
            hand.add(drawCard());
        }
    }

    /**
     * Method that checks whether a current card is of type Pokemon.
     * @return true if the current card in the deck is of type Pokemon and false otherwise.
     */
    public boolean evaluateOpeningHand() {
        for (Card currentCard : hand) {
            if (currentCard instanceof Pokemon) {
                return true;
            }
        }
        return false;
    }
}