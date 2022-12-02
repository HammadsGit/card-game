package comp1721.cwk2;

import java.util.Collections;
import java.util.List;

// Implement Deck class here
public class Deck extends CardCollection{


    //create a deck.
     List<Card> deck = cards;


    //adds the cards to the deck.
    public Deck(){
        for(Card.Suit s: Card.Suit.values()){
            for(Card.Rank r: Card.Rank.values()){
                deck.add(new Card(r, s));
            }
        }
    }

    //shuffles the deck.
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //deals from the deck.
    public Card deal(){
        //if deck is empty it cannot deal.
        if(deck.size() == 0){
            throw new CardException("Dealing from empty deck,");
        }
        else{
            //deal the card
            Card cards = deck.get(0);
            deck.remove(0);
            return cards;
        }
    }

    //return size of deck.
    public int size(){
        return deck.size();
    }

    //clear the deck.
    public void discard(){
        deck.clear();
    }

    //check if deck is empty.
    public boolean isEmpty(){
        return size() == 0;
    }



    //checks if deck contains a card.
    public boolean contains(Card buffer){
        boolean valid= false;
        for(Card c: deck){
            if (c.equals(buffer)) {
                valid = true;
                break;
            }
        }

        return valid;
    }

    //adds a card.
    public void add(Card buffer){
        //throw error if card already exists.
        if(deck.contains(buffer)){
            throw new CardException("Card already exists");
        }
        //add card.
        else{
            deck.add(buffer);
        }
    }
}