package comp1721.cwk2;

// Implement PokerHand class here


import java.util.*;


public class PokerHand extends CardCollection{
    // creates a hand.
    List<Card> hand = cards;
    //constant variable for the
    public static int FULL_SIZE = 5;

    public PokerHand(){

    }


    public PokerHand(String Buffer){


        //removes all spaces from string
        Buffer = Buffer.replaceAll("\\s+","");

        //assigns a random card at the beginning. just to make the variable.
        Card.Rank r2 = Card.Rank.FOUR;
        Card.Suit s2 = Card.Suit.CLUBS;

        //loops through each character
        for(int i =0; i<Buffer.length(); i++){
            // if character is a rank store in r
            if(i%2 ==0){
                //reads the rank char.
                char rank_name = Buffer.charAt(i);

                // assigns the rank.
                for(Card.Rank r: Card.Rank.values()){
                    if(rank_name == r.getSymbol()){
                        r2 = r;
                    }
                }


            }
            //if character is a suit store in s
            else{
                //assigns the suit
                char suit_name = Buffer.charAt(i);

                //finds the suit and assigns it.
                for(Card.Suit s: Card.Suit.values()){
                    if(suit_name == s.getSymbol()){
                        s2 = s;
                    }
                //adding the card


            }
                //adds the card
                hand.add(new Card(r2, s2));

        }

    }

        //throws exception if there are more than 5 cards in the hand.
        if(hand.size() > FULL_SIZE){
            throw new CardException("More than 5 cards in the hand.");
        }

    }

    public int count_check(){
        int count = 0;

        for(Card c: hand){
            for(Card c2: hand){
                if(!c.equals(c2)){
                    if (c.getRank() == c2.getRank()) {
                        //increment in the check if rank is same.
                        count += 1;
                    }
                }
            }
        }

        return count;
    }


    //function to convert hand to a string.
    @Override
    public String toString(){
        StringBuilder hand_String = new StringBuilder();

        //counter to count the index.
        int counter = 0;

        for(Card c: hand){
            counter += 1;
            hand_String.append(c.toString());
            //add space for every card.
            if(counter != hand.size()){
                hand_String.append(" ");
            }

        }
        //convert hand to  a string and return it.

        return String.valueOf(hand_String);
    }


    //function to determine if the hand is a straight.
    public boolean isStraight(){
        //the is not a  straight at the beginning.
        boolean isStraight = false;

        int count = 1;

        //create a list to add the ranks to determine if it is a straight.
        List<Integer> myList = new ArrayList<>();

        //if hand size is less than 5 then hand is not a straight.
        if(hand.size()<5){
            return false;
        }
        //determine if the hand is a straight.
        else{
            int index_count = 1;
            //adding the card values to the list.
            for(Card c: hand){
                //first assigning values for face cards.
                if(c.toString().contains("J")){
                    myList.add(11);
                }
                else if (c.toString().contains("Q")){
                    myList.add(12);
                }
                else if (c.toString().contains("K")){
                    myList.add(13);
                }
                else if (c.toString().contains("A")){
                    //since Ace changes values, adds value depending
                    // on the position.
                    if(index_count == 5){
                        myList.add(14);
                    }
                    else{
                        myList.add(1);
                    }

                }
                //if not face card add value as normal
                else{
                    myList.add(c.value());
                }

                //increment the index.
                index_count += 1;
            }

            //sort the rank order into low to high order.
            Collections.sort(myList);

            //check if the hand is sequential.
            for (int i = 0; i <5-1; i++) {
                if ((myList.get(i) + 1) ==  (myList.get(i + 1))){
                    count += 1;
                }
            }

            //if hand is sequential the
            if(count == 5){
                isStraight = true;
            }

        }








        //return if the card is straight or not.
        return isStraight;
    }

    //function to determine if the hand is a flush.
    public boolean isFlush(){
        //the is not a  flush at the beginning.
        boolean isFlush = false;

        //to count the checks.
        int count = 0;
        //if hand size is less than 5 then hand is not a flush.
        if(hand.size()<5){
            return false;
        }
        // determine if a card is a flush.
        else{
            //creates a random card, to check all the suits.
            Card card1 = hand.get(0);
            //checks if all cards have the same suit. adds one if they do.
            for(Card c: hand){
                if(card1.getSuit() == c.getSuit()){
                    count +=1;

                }
            }

            //if it is a flush then, make it true.
            if(count == 5){
                isFlush = true;
            }
        }

        //return true or false statement depending on if it is  true.
        return isFlush;
    }

    public void add(Card buffer){
        //if card is already in there then throw an exception.
       if(hand.contains(buffer)){
           throw new CardException("Card Already exists in hand");

       }
       //if hand is full throw an exception.
       else if(hand.size() == 5){
           throw new CardException("hand is full");
       }
       //if none of those errors, then add the card.
       else{
           hand.add(buffer);
       }
    }

    public boolean isPair(){
        //the is not a  pair at the beginning.
        boolean isPair = false;

        //count for checks.
        int count;

        //if hand size is less than 5 then hand is not a pair.
        if(hand.size()<5){
            return false;
        }
        //else determine if it is  a pair.
        else{
            //check if the cards rank are similar if they are not the same card.
            count = count_check();

            //if there exists a pair, make true
            if(count == 2){
                isPair = true;
            }
        }

        //return if hand is pair or not.
        return isPair;
    }
    public boolean isTwoPairs(){
        //the is not a  pair at the beginning.
        boolean isTwoPair = false;

        //count for checks.
        int count;

        //if hand size is less than 5 then hand is not a two-pair.
        if(hand.size()<5){
            return false;
        }
        //check if it is  a pair.
        else{
            //check if the cards rank are similar if they are not the same card.
            count = count_check();

            //and if hand is two pair make true.
            if(count == 4){
                isTwoPair = true;
            }
        }


        //return if hand isTwoPair or not.
        return isTwoPair;
    }

    public boolean isThreeOfAKind(){
        //the is not a  ThreeOfAKind at the beginning.
        boolean isThreeOfAKind = false;

        //count for checks.
        int count;

        //if hand size is less than 5 then hand is not a ThreeOfAKind.
        if(hand.size()<5){
            return false;
        }
        //check if it is  a ThreeOfAKind.
        else{
            //check if the cards rank are similar if they are not the same card.
            count = count_check();

            //if hand is ThreeOfAKind make true.
            if(count % 3== 0 && count % 4 != 0){
                isThreeOfAKind = true;
            }
        }

        //return if its three of a kind or not.
        return isThreeOfAKind;
    }

    public boolean isFourOfAKind(){

        //the is not a  isFourOfAKind at the beginning.
        boolean isFourOfAKind = false;

        //count for checks.
        int count;

        //if hand size is less than 5 then hand is not a isFourOfAKind.
        if(hand.size()<5){
            return false;
        }
        else{
            //check if the cards rank are similar if they are not the same card.
            count = count_check();

            //if hand is ThreeOfAKind make true.
            if(count  == 12){
                isFourOfAKind = true;
            }
        }
        //return if its Four Of A Kind or not.
        return isFourOfAKind;
    }

    public boolean isFullHouse(){
        //the is not a  isFullHouse at the beginning.
        boolean isFullHouse = false;

        //count for checks.
        int count;

        //if hand size is less than 5 then hand is not a isFullHouse.
        if(hand.size()<5){
            return false;
        }
        else{
            //check if the cards rank are similar if they are not the same card.
            count = count_check();

            //if full house then make true.
            if(count == 8){
                isFullHouse = true;
            }



        }
        //return if its full house or not.
        return isFullHouse;
    }

    //returns hand size.
    public int size(){
        return hand.size();
    }

    public void discard(){
        //if hand is empty it cannot discard.
        if(hand.size() == 0){
            throw new CardException("hand is empty");
        }
        else{
            // else clear hand.
            hand.clear();
        }
    }

    // empties hand and returns them to deck.
    public void discardTo(Deck deck){
        //if hand is empty then cannot clear.
        if(hand.size() == 0){
            throw new CardException("hand is empty");
        }
        else{
            //adds all the cards to the deck.
            for(Card c: hand){
                deck.add(c);
            }

            //clear the hand.
            hand.clear();
        }

    }


    }







