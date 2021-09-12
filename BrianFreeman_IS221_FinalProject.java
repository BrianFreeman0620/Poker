/*
 -------------------------------------------------------------------------------
 * Name:    BrianFreeman_IS221_CH8_PP8_12.java
 * Lang:    Java
 * Date:    2021-04-26
 * Author:  Brian Freeman
 * Purpose: This program will play a hand of poker against a computer. Boths players
            will be able to discard up to 3 cards to get a better hand. The computer
            will have a system to decide which cards to discard. 
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-04-26
 * Desc:    The card and deck classes have been created. Card can show its face
            and suit. Deck can be shuffled and dealt from. 
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-04-27
 * Desc:    The hand class has been created. Hand can draw and discard cards and
            can show a full hand of cards.
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-04-28
 * Desc:    The hand class can now check if a hand is a flush or a straight.
            Can also check if it is 1 away from either. 
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-04-29
 * Desc:    The hand class can now check all possible hands. The score can be
            found using the poker scoring guide. 
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-05-02
 * Desc:    The full house and two pair scoring has been altered to accurately
            find a winner. The computer has rules for discarding if the hand is
            a straight flush, a straight, a flush, a full house, a 4 of a kind,
            or a small flush (4 cards matching a suit)
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-05-03
 * Desc:    The computer has rules for discarding if the hand is small straight 
            (4 cards in ascending order) or 2 pairs.
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-05-04
 * Desc:    The computer has rules for discarding if the hand is a 3 of a kind,
            a pair, or high card.
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-05-06
 * Desc:    The game has been created. The user and computer can both draw 5 cards
            and discard the cards of their chosing. The winner cannot be found yet.
--------------------------------------------------------------------------------
 * ChangeLog:
 * Who:     Brian Freeman           Date:       2021-05-10
 * Desc:    The print text has been changed. The player can see both hands after
            the discarding and see who wins. Altered matching to show the cards
            in pairs and 3 of a kinds to players, along with high card.
--------------------------------------------------------------------------------
 */
package brianfreeman_is221_finalproject;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class BrianFreeman_IS221_FinalProject {

    public static void main(String[] args) {
        
        // Prints a welcome message
        System.out.println("Welcome to poker! In this game, you will be playing"
                + " against a computer player.\nYou and the computer will each take"
                + " turns drawing cards until both players have 5 cards in their "
                + " hand.\nThen, each player can discard up to 3 cards to try to"
                + " improve their hand.\nYou win by having a better hand than your"
                + " opponent, whether it be with a higher pair or a royal flush.\n");
        
        // Declares a scanner object
        Scanner scanMain = new Scanner(System.in);
        
        // Prompts the user to type start to play
        System.out.println("Type start to play poker.");
        String strPlay = scanMain.next();
        
        // Checks if the user wants to play poker
        if (strPlay.toLowerCase().equals("start")){
        
            // The deck is declared and shuffled
            Deck deckMain = new Deck();
            deckMain.Shuffle();

            // Declares the human and computer player's hand
            Hand handPlayer = new Hand();
            Hand handComputer = new Hand();

            // Deals out both players hands
            for (int intCardNumber = 1; intCardNumber < 6; intCardNumber++){

                // The computer draws a card from the deck and tells the user
                handComputer.Draw(deckMain.Deal());
                System.out.println("Computer drew card number " + intCardNumber);

                // The player draws a card from the deck and tells the user
                handPlayer.Draw(deckMain.Deal());
                System.out.println("You drew card number " + intCardNumber);
            }
            
            // Shows the user their hand
            System.out.println("\nYour hand is:\n");
            handPlayer.Show();
            System.out.println();
            
            // The computer discards the appropriate amount of cards and tells
            // the user
            handComputer.ComputerDiscard(deckMain);
            
            // The user is asked how many cards they want to discard
            System.out.println("\nHow many cards would you like to discard?"
                    + "\nType an integer between 0 and 3");
            int intDiscard = scanMain.nextInt();
            
            // Runs until an appropriate number of cards to discard are inputted
            while (intDiscard < 0 || intDiscard > 3){
                System.out.println("That number is not between 0 and 3. Please"
                        + " try again.");
                intDiscard = scanMain.nextInt();
            }
            
            // Initializes a boolean as false
            boolean boolDoneDiscarding = false;
            
            // Runs until the user is happy with which cards should be discarded
            while (!boolDoneDiscarding){
                
                // Checks if the user doesn't want to discard and sets the
                // boolean to true
                if (intDiscard == 0){
                    System.out.println("No cards were discarded");
                    boolDoneDiscarding = true;
                }
                else{
                    
                    // Creates an empty array of intDiscard length
                    int[] arrayDiscard = new int[intDiscard];
                    
                    // Loops for the number of cards wanted to discard
                    for (int intCardNumber = 0; intCardNumber < intDiscard; intCardNumber++){
                        
                        // Intializes boolean to false
                        boolean boolAlreadyDiscarded = false;
                        
                        // Asks the user which card should be discarded
                        System.out.println("Which card should be discarded? Refer"
                                + " to the order above and type in the card number.");
                        int intCardPosition = scanMain.nextInt();
                        
                        // Checks if the card has already been chosen to be 
                        // discarded by looping through the array and finding
                        // any matching integers
                        for (int intInArray : arrayDiscard){
                            if (intInArray == intCardPosition){
                                boolAlreadyDiscarded = true;
                            }
                        }
                        
                        // Loops until chosen card hasn't already been discarded
                        while (boolAlreadyDiscarded){
                            boolAlreadyDiscarded = false;
                            System.out.println("Card number " + intCardPosition 
                                    + " has already been discarded. Type in a "
                                    + "different integer.");
                            
                            // Replaces previous integer with new input
                            intCardPosition = scanMain.nextInt();
                            
                            // Checks if the card has already been chosen to be 
                            // discarded by looping through the array and finding
                            // any matching integers
                            for (int intInArray : arrayDiscard){
                                if (intInArray == intCardPosition){
                                    boolAlreadyDiscarded = true;
                                }
                            }
                        }
                        arrayDiscard[intCardNumber] = intCardPosition;
                    }
                
                // Checks if the user is happy with the cards chosen
                System.out.println("Are you sure you want to discard:");
                
                // Lists out the cards chosen
                for (int intInArray : arrayDiscard){
                    System.out.println(handPlayer.array5CardHand[intInArray - 1].Show());
                }
                System.out.println("?\nType yes if you are happy.");
                String strHappy = scanMain.next();
                
                // If the user is happy, discards the cards and draws new ones
                if (strHappy.toLowerCase().equals("yes")){
                    for (int intInArray : arrayDiscard){
                        handPlayer.Discard(intInArray - 1);
                        handPlayer.Draw(deckMain.Deal());
                    }
                    boolDoneDiscarding = true;
                }
                }
            }
            
            // Shows the user their new hand
            System.out.println("\nYour new hand is:\n");
            handPlayer.Show();
            System.out.println();
            
            // Tells the user what kind of hand that is
            if (handPlayer.isStraightFlush()){
                if (handPlayer.Scoring() == 80014){
                    System.out.println("Royal Flush");
                }
                else{
                    System.out.println("Straight Flush");
                }
            }
            else{
                if (handPlayer.StraightAmount().equals("Large")){
                    System.out.println("Straight");
                }
                else{
                    if (handPlayer.FlushAmount().equals("Large")){
                        System.out.println("Flush");
                    }
                    else{
                        System.out.println(handPlayer.Matching());
                    }
                }
            }
            System.out.println();
            
            // Shows the user the computer's hand
            System.out.println("The computer's hand is:\n");
            handComputer.Show();
            System.out.println();
            
            // Tells the user what kind of hand that is
            if (handComputer.isStraightFlush()){
                if (handComputer.Scoring() == 80014){
                    System.out.println("Royal Flush");
                }
                else{
                    System.out.println("Straight Flush");
                }
            }
            else{
                if (handComputer.StraightAmount().equals("Large")){
                    System.out.println("Straight");
                }
                else{
                    if (handComputer.FlushAmount().equals("Large")){
                        System.out.println("Flush");
                    }
                    else{
                        System.out.println(handComputer.Matching());
                    }
                }
            }
            System.out.println();
            
            // Checks if the user is the winner
            if (handPlayer.Scoring() > handComputer.Scoring()){
                System.out.println("You win! Congratulations!");
            }
            else{
                
                // Checks if the computer is the winner
                if (handPlayer.Scoring() < handComputer.Scoring()){
                    System.out.println("You lost. Better luck next time.");
                }
                
                // If neither player is the winner, reports a tie
                else{
                    System.out.println("It's a draw.");
                }
            }
        }
        // If the user does not want to play, prints a message
        else{
            System.out.println("Maybe you'll want to play next time. Goodbye!");
        }
    }
    
    static class Card{
        
        // Declares the string strSuit for the suit of a card and declares the int
        // intFace for the face of a card
        String strSuit;
        int intFace;
        
        public Card(String strSuitValue, int intFaceValue){
            
            // Initializes suit and face from inputted values
            strSuit = strSuitValue;
            intFace = intFaceValue;
        }
        
        public String Show(){
            
            String strFace;
            
            // Converts the value of the card's face to a string
            switch(intFace){
                case 1:
                    
                    // If the value is 1, then the string is Ace
                    strFace = "Ace";
                    break;
                case 11:
                    
                    // If the value is 11, then the string is Jack
                    strFace = "Jack";
                    break;
                case 12:
                    
                    // If the value is 12, then the string is Queen
                    strFace = "Queen";
                    break;
                case 13:
                    
                    // If the value is 13, then the string is King
                    strFace = "King";
                    break;
                default:
                    
                    // All of values are a string of the integer
                    strFace = Integer.toString(intFace);
                
            }
            // Combines the string gotten from the switch and the suit
            String strShowCard = strFace + " of " + strSuit;
            
            // Returns the face and suit
            return strShowCard;
        }
    }
    
    static class Deck{
        
        // Declares an empty array of cards with length 52
        Card[] arrayCards = new Card[52];
        
        public Deck(){
            
            // Loops 52 times to get every card Ace through King of all suits 
            // into the deck
            for(int intCardNumber = 0; intCardNumber < 52; intCardNumber++){
                
                // If the card number is less than 13, then it is a heart
                if (intCardNumber < 13){
                    
                    arrayCards[intCardNumber] = new Card("Hearts", ((intCardNumber + 1)%13 + 1));
                }
                else{
                    
                    // If the card number is less than 26, then it is a diamond
                    if (intCardNumber < 26){
                        
                        arrayCards[intCardNumber] = new Card("Diamonds", ((intCardNumber+1)%13 + 1));
                    }
                    
                    else{
                        
                        // If the card number is less than 39, then it is a club
                        if (intCardNumber < 39){
                            
                            arrayCards[intCardNumber] = new Card("Clubs", ((intCardNumber+1)%13 + 1));
                        }
                        
                        // If the card number is greater than 38, then it is a spade
                        else{
                            
                            arrayCards[intCardNumber] = new Card("Spades", ((intCardNumber+1)%13 + 1));
                        }
                    }
                }
            }
        }
        
        public Card Deal(){
            
            // Creates a second empty array of 52 cards
            Card[] arrayNewCards = new Card[52];
            
            // Saves the top card of the deck
            Card cardDealt = arrayCards[0];
            
            // Moves the top card to the bottom of the deck
            for (int intCardNumber = 0; intCardNumber < 51; intCardNumber ++){
                
                arrayNewCards[intCardNumber] = arrayCards[intCardNumber + 1];
            }
            arrayNewCards[51] = cardDealt;
            
            // Changes arrayCards to the array with the drawn card at the bottom
            arrayCards = arrayNewCards;
            
            // Returns drawn card
            return cardDealt;
        }
        
        public void Shuffle(){
            
            // Creates a second empty array of 52 cards
            Card[] arrayShuffledCards = new Card[52];
            
            // Creates an array of 52 integers of 53 because new int[52] creates
            // 52 0s, which prevents the first position from having a card
            int[] arrayUsedPositions = new int[52];
                for (int intCount = 0; intCount < 52; intCount++){
                    
                    arrayUsedPositions[intCount] = 53;
                }
            
            // Initializes the integer intPosition
            int intPosition;
            
            // Creates a new random object
            Random randPosition = new Random();
            
            // Initializes the boolean boolInArray
            boolean boolInArray;
            
            // Loops 52 times for each card in deck
            for (int intCardNumber = 0; intCardNumber < 52; intCardNumber ++){
                
                boolInArray = false;
                
                // Randomly chooses a position for the card
                intPosition = randPosition.nextInt(52);
                
                // Checks if the position already has a card
                for (int intUsedPositions : arrayUsedPositions){
                    if(intUsedPositions == intPosition){
                        boolInArray = true;
                    }
                }
                // Loops until empty position is found
                while (boolInArray){
                    
                    boolInArray = false;
                    
                    // Adds 1 to position unless already at 51, then resets counting
                    if (intPosition == 51){
                        intPosition = 0;
                    }
                    else{
                        intPosition ++;
                    }
                    
                    for (int intUsedPositions : arrayUsedPositions){
                        if(intUsedPositions == intPosition){
                            boolInArray = true;
                        }
                    }
                }
                
                // Adds card into random position, making sure the position cannot
                // be chosen again
                arrayShuffledCards[intPosition] = arrayCards[intCardNumber];
                arrayUsedPositions[intCardNumber] = intPosition;
            }
            // Changes arrayCards to shuffled deck
            arrayCards = arrayShuffledCards;
        }
    }
    static class Hand{
        
        // Initializes an empty array of 5 cards
        Card[] array5CardHand = new Card[5];
        
        // Declares an array of 5 integers of 6
        int[] arrayUsedHandPositions = {6,6,6,6,6};
        
        // Declares boolean boolFullHand and integer intCardsInHand
        boolean boolFullHand;
        int intCardsInHand;
        
        public Hand(){
            
            // Sets hand as not full and 0 cards in hand
            boolFullHand = false;
            intCardsInHand = 0;
        }
        
        public void Draw(Card cardDrawnCard){
            
            // Checks if hand is not full
            if (!boolFullHand){
                
                // If it isn't full, starts a counter to find a position in the
                // hand to place the card
                int intCounter = 0;
                for (int intUsedHandPositions : arrayUsedHandPositions){
                    if (intUsedHandPositions == 6){
                        
                        // Replaces empty card with drawn card and adds used
                        // card position to the array
                        array5CardHand[intCounter] = cardDrawnCard;
                        arrayUsedHandPositions[intCounter] = intCounter;
                        
                        // Increases the number of cards that are in hand
                        intCardsInHand ++;
                        
                        // Checks if the hand is now full and sets values accordingly
                        if (intCardsInHand == 5){
                            boolFullHand = true;
                        }
                        break;
                    }
                    
                    // If the position is full, checks the next one
                    else{
                        intCounter ++;
                    }
                }
            }
            else{
                // If the hand is full, responds that another card cannot be added
                System.out.println("Sorry, but you can only have 5 cards at once.");
            }
        }
        
        public void Discard(int intCardNumber){
            
            // Checks if the card number is in the valid range
            if (intCardNumber > -1 && intCardNumber < 5){
                
                // Declares a boolean boolCardExists as false
                boolean boolCardExists = false;
                
                // Checks if the card number has a card in the position
                for (int intUsedHandPositions : arrayUsedHandPositions){
                    
                    // If the card position is in the hand, then the card exists
                    if(intCardNumber == intUsedHandPositions){
                        boolCardExists = true;
                    }
                }
                
                // If the card exists, removes it from hand and sets 
                // appropriate values
                if (boolCardExists){
                    
                    array5CardHand[intCardNumber] = new Card("blank", 0);
                    arrayUsedHandPositions[intCardNumber] = 6;
                    boolFullHand = false;
                    intCardsInHand --;
                }
                
                else{
                    
                    // If the card doesn't exist, prints that no card was removed
                    System.out.println("There are no cards in position " + 
                            (intCardNumber + 1) + ".");
                }
            }
            
            else{
                
                // If the card is out of range, prints that the card cannot exists
                System.out.println("Only 5 cards are in a hand.");
            }
        }
        
        public void Show(){
            
            // Checks if hand is full
            if (boolFullHand){
                
                // If it is full, shows each card in hand
                for(Card cardInHand : array5CardHand){
                    System.out.println(cardInHand.Show());
                }
            }
            else{
                
                // If it is not full, displays message that hand will not be shown
                System.out.println("Sorry, but your hand isn't full.");
            }
        }
        
        public String FlushAmount(){
            
            // Sets number of each suit in hand to 0
            int intNumberHearts = 0;
            int intNumberDiamonds = 0;
            int intNumberClubs = 0;
            int intNumberSpades = 0;
            
            // Counts the number of each suit in hand
            for (Card cardInHand : array5CardHand){
                switch(cardInHand.strSuit){
                    case "Hearts":
                        intNumberHearts ++;
                        break;
                    case "Diamonds":
                        intNumberDiamonds ++;
                        break;
                    case "Clubs":
                        intNumberClubs ++;
                        break;
                    case "Spades":
                        intNumberSpades ++;
                        break;
                }
            }
            
            // Checks if the hand has a small flush of 4 matching suits
            // Only used by computer player
            if (intNumberHearts == 4||intNumberDiamonds == 4||intNumberClubs == 4
                    ||intNumberSpades == 4){
                return "Small";
            }
            else{
                
                // Checks if the hand has a large flush of 5 matching suits
                // Used for scoring against the other player
                if (intNumberHearts == 5||intNumberDiamonds == 5||intNumberClubs == 5
                    ||intNumberSpades == 5){
                    return "Large";
                }
                
                // If less than 4 matching of one suit, returns not enough for a
                // straight to be considered
                else{
                    return "None";
                }
            }
        }
        
        public String StraightAmount(){
            
            // Declares 2 empty arrays of empty integers
            // Every variable ending with 14 in StraightAmount() has Ace set to
            // value 14
            // Every variable ending with 1 in StraightAmount() has Ace set to
            // value 1
            int[] arrayAce14 = new int[5];
            int[] arrayAce1 = new int[5];
            
            // Initializes 2 dummy hands to discard cards without messing up the
            // main hand
            Hand handDummy14 = new Hand();
            Hand handDummy1 = new Hand();
            
            // Declares 2 integers of 0 to keep track on how straight a hand is
            int intNotOrder14 = 0;
            int intNotOrder1 = 0;
            
            // Fills the 2 dummy hands with the cards from the main hand
            for (int intCardNumber = 0; intCardNumber < 5; intCardNumber++){
                
                handDummy14.Draw(array5CardHand[intCardNumber]);
                handDummy1.Draw(array5CardHand[intCardNumber]);
            }
                    
            for (int intCardNumber = 0; intCardNumber < 5; intCardNumber++){
                
                // intSmallest checks the next smallest value card
                int intSmallest1 = 15;
                int intSmallest14 = 15;
                
                // intSmallestPosition sets the next smallest value card's position
                int intSmallestPosition1 = 6;
                int intSmallestPosition14 = 6;
                
                // intCounter keeps track of the position in the hand
                int intCounter = 0;
                
                // Finds the next smallest card value
                for (Card cardInHand : handDummy14.array5CardHand){
                    if (cardInHand.intFace < intSmallest14){
                        
                        // Checks if the card is an Ace and sets value to 14,
                        // which is 1 more than the King's 13
                        if (cardInHand.intFace == 1){
                            intSmallestPosition14 = intCounter;
                            intSmallest14 = 14;
                        }
                        else{
                            
                            // Checks if the card is not empty
                            if (cardInHand.intFace > 1){
                                intSmallestPosition14 = intCounter;
                                intSmallest14 = cardInHand.intFace;
                            }
                        }
                    }
                    intCounter ++;
                }
                
                // Resets the counter for Ace of value 1
                intCounter = 0;
                
                // Works similar to Ace of value 14, but doesn't change Ace's
                // value when finding smallest
                for (Card cardInHand : handDummy1.array5CardHand){
                    if (cardInHand.intFace < intSmallest1 && cardInHand.intFace > 0){
                        intSmallestPosition1 = intCounter;
                        intSmallest1 = cardInHand.intFace;
                    }
                    intCounter ++;
                }
                
                // After finding the next smallest, puts the value of the card
                // into the ordered array
                arrayAce14[intCardNumber] = intSmallest14;
                arrayAce1[intCardNumber] = intSmallest1;
                
                // Discards the card to not count it twice
                handDummy14.Discard(intSmallestPosition14);
                handDummy1.Discard(intSmallestPosition1);
            }
            
            // For every card not 1 less than the next smallest card, the value
            // of how far from a straight increaes
            for (int intCard = 0; intCard < 4; intCard++){
                if (arrayAce14[intCard] + 1 != arrayAce14[intCard + 1]){
                    intNotOrder14 ++;
                }
                if (arrayAce1[intCard] + 1 != arrayAce1[intCard + 1]){
                    intNotOrder1 ++;
                }
            }
            
            // If the hand is a straight, returns large
            // Used for comparing score of the 2 players
            if (intNotOrder14 == 0 || intNotOrder1 == 0){
                return "Large";
            }
            else{
                
                // If only 1 card needs to change to become a straight, 
                // returns small
                // Only used by computer to see if it is 1 card away
                if (intNotOrder14 == 1 || intNotOrder1 == 0){
                    return "Small";
                }
                
                // If it isn't close to a straight, returns none
                else{
                    return "None";
                }
            }
        }
        public boolean isStraightFlush(){
            
            // Checks if the hand is both a straight and a flush
            if (this.FlushAmount().equals("Large") && this.StraightAmount().equals("Large")){
                return true;
            }
            else{
                return false;
            }
        }
        
        public String Matching(){
            
            // Declares an empty array of 13 integers
            int[] arrayFaceNumber = new int[13];
            
            // Declares 2 integers for keeping track of the number of pairs,
            // 3 of a kinds, and high card
            int intPairs = 0;
            int intThree = 0;
            int intHigh = 0;
            
            // Declares an integer and initializes 4 strings to keep track of 
            // face value of the matching cards and high card
            int intPairFace1 = 0;
            String strPairFace1 = "";
            String strPairFace2 = "";
            String strThreeFace = "";
            String strHighFace = "";
            
            // Counts the number of each face value
            for (Card cardInHand : array5CardHand){
                
                // Increases the value of the face value's position
                arrayFaceNumber[cardInHand.intFace - 1]++;
            }
            
            // Goes through each card to find pairs, 3 of a kinds, and 4 of a kinds
            for (int intFaceNumber = 0; intFaceNumber < 13; intFaceNumber++){ 
                
                // Initializes a string
                String strFace;
                
                // Assigns a value to the string given the face number
                switch(intFaceNumber){
                case 0:
                    
                    // If the value is 0, then the string is Ace
                    strFace = "Ace";
                    break;
                case 10:
                    
                    // If the value is 10, then the string is Jack
                    strFace = "Jack";
                    break;
                case 11:
                    
                    // If the value is 11, then the string is Queen
                    strFace = "Queen";
                    break;
                case 12:
                    
                    // If the value is 12, then the string is King
                    strFace = "King";
                    break;
                default:
                    
                    // All of values are a string of the integer
                    strFace = Integer.toString(intFaceNumber + 1);
                    }
                
                // If the card has a pair, increases pair counter
                if (arrayFaceNumber[intFaceNumber] == 2){
                    intPairs++;
                    
                    // Checks if there is already a pair
                    if (intPairFace1 > 0){
                        strPairFace2 = strFace;
                    }
                    else{
                        intPairFace1 = 1 + intFaceNumber;
                        strPairFace1 = strFace;
                    }
                }
                else{
                    
                    // If the card has a 3 of a kind, increases 3 of a kind 
                    // counter and sets the 3 face
                    if (arrayFaceNumber[intFaceNumber] == 3){
                        intThree++;
                        strThreeFace = strFace;
                    }
                    else{
                        
                        // If the card has 4 of a kind, returns four of a kind
                        // with card value
                        if (arrayFaceNumber[intFaceNumber] == 4){
                            return "Four of a Kind (" + strFace + ")";
                        }
                        else{
                            
                            // If there is only 1 of the card in the hand, changes 
                            // high card
                            if (arrayFaceNumber[intFaceNumber] == 1){
                                if (intFaceNumber == 0 || intHigh == 13){
                                    intHigh = 13;
                                    strHighFace = "Ace";
                                }
                                else{
                                    intHigh = intFaceNumber;
                                    strHighFace = strFace;
                                }
                            }
                        }
                    }
                }
            }

            // If there are 2 pairs, returns two pairs
            if (intPairs == 2){
                return "Two Pairs (" + strPairFace1 + ", " + strPairFace2 + ")";
            }
            else{
                
                // Checks if there are any pairs
                if (intPairs == 1){
                    
                    // If there is a pair and a 3 of a kind, returns full house
                    if (intThree == 1){
                        return "Full House (3 " + strThreeFace + "s, 2 " 
                                + strPairFace1 + "s)";
                    }
                    
                    // If there is 1 pair only, returns one pair
                    else{
                        return "One Pair (" + strPairFace1 + ")";
                    }
                }
                else{
                    
                    // If there is 1 3 of a kind only, returns three of a kind
                    if (intThree == 1){
                        return "Three of a Kind (" + strThreeFace + ")";
                    }
                    
                    // If no condition was met, returns high card
                    else{
                        return "High Card (" + strHighFace + ")";
                    }
                }
            }
        }
        
        public int Scoring(){
            
            // Sets the starting score to 0
            int intScore = 0;
            
            // Checks if the hand has, in order, a straight flush, a 4 of a kind,
            // a full house, a flush, a straight, a 3 of a kind, 2 pairs, or 1 pair
            // The point values decrease with each condition not met
            // The values go by 10000s to account for high card in case both players
            // meet the same hand conditions, with full house and 2 pair needing
            // 2 high cards
            if (this.isStraightFlush()){
                intScore = 80000;
            }
            else{
                if (this.Matching().contains("Four of a Kind")){
                    intScore = 70000;
                }
                else{
                    if (this.Matching().contains("Full House")){
                        intScore = 60000;
                    }
                    else{
                        if (this.FlushAmount().equals("Large")){
                            intScore = 50000;
                        }
                        else{
                            if (this.StraightAmount().equals("Large")){
                                intScore = 40000;
                            }
                            else{
                                if (this.Matching().contains("Three of a Kind")){
                                    intScore = 30000;
                                }
                                else{
                                    if (this.Matching().contains("Two Pairs")){
                                        intScore = 20000;
                                    }
                                    else{
                                        if (this.Matching().contains("One Pair")){
                                            intScore = 10000;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            
            // Checks if the hand is a straight flush or a straight
            if (intScore == 80000 || intScore == 40000){
                
                // Declares that the hand does not have a King nor an Ace
                boolean boolHasKing = false;
                boolean boolHasAce = false;
                
                // Starts the highest card at value 0
                int intHighestCard = 0;
                
                // Finds the highest card
                for (Card cardInHand : array5CardHand){
                    
                    // Checks if the card is the new highest
                    if (cardInHand.intFace > intHighestCard){
                        intHighestCard = cardInHand.intFace;
                    }
                    
                    // Checks if the card is a King
                    if (cardInHand.intFace == 13){
                        boolHasKing = true;
                    }
                    else{
                        
                        // Checks if the card is an Ace
                        if (cardInHand.intFace == 1){
                            boolHasAce = true;
                        }
                    }
                }
                
                // If the straight flush or straight has a King and an Ace, then
                // sets the added value to 14
                if (boolHasKing && boolHasAce){
                    intScore += 14;
                }
                
                // If not, adds the highest value to the score
                else{
                    intScore += intHighestCard;
                }
            }
            else{
                
                // Check if the hand is a flush or high card
                if (intScore == 50000 || intScore == 0){
                    
                    // Starts the highest card at value 0
                    int intHighestCard = 0;
                
                    // Finds the highest card
                    for (Card cardInHand : array5CardHand){
                    
                        // Checks if there is an Ace
                        if (cardInHand.intFace == 1){
                            intHighestCard = 14;
                        }
                        
                        else{
                            
                            // Checks if the card is the new highest given it's
                            // not an Ace
                            if (cardInHand.intFace > intHighestCard){
                                intHighestCard = cardInHand.intFace;
                            }
                        }
                    }
                    
                    // Adds the highest card's value to the score
                    intScore += intHighestCard;
                }
                
                else{
                    
                    // Declares an empty array of 13 integers
                    int[] arrayFaceNumber = new int[13];
                    
                    // Declares the highest and second highest value of 
                    // importance is 0
                    int intHighestMatchingCard = 0;
                    int intSecondHighestMatchingCard = 0;
                    
                    // Sets the highest highest match as a pair
                    int intHighestMatch = 2;
                    
                    // Counts the number of each face value
                    for (Card cardInHand : array5CardHand){
                
                        // Increases the value of the face value's position
                        arrayFaceNumber[cardInHand.intFace - 1]++;
                    }
                    
                    // Finds the highest face value, in order, in a 4 of a kind,
                    // 3 of a kind, or a pair
                    for (int intFaceValue = 0; intFaceValue < 13; intFaceValue++){
                        
                        // Checks if there are equal or more matching cards than
                        // the highest current match
                        if (arrayFaceNumber[intFaceValue] >= intHighestMatch){
                            
                            if (intFaceValue == 0){
                                // Sets the new highest match and the face value
                                // and shifts the old highest to second highest
                                intHighestMatch = arrayFaceNumber[intFaceValue];
                                intSecondHighestMatchingCard = intHighestMatchingCard;
                                intHighestMatchingCard = 14;
                            }
                            else{
                                
                                // Checks if Ace is not a pair or there is a 3
                                // of a kind when the pair is an Ace
                                if (intHighestMatchingCard < 14 || arrayFaceNumber[intFaceValue] > intHighestMatch){
                                    
                                    // Sets the new highest match and the face value
                                    // and shifts the old highest to second highest
                                    intHighestMatch = arrayFaceNumber[intFaceValue];
                                    intSecondHighestMatchingCard = intHighestMatchingCard;
                                    intHighestMatchingCard = intFaceValue + 1;
                                }
                            }
                        }
                        else{
                            // If the value is a pair lower than the highest pair's
                            // value, sets the second highest match to the value
                            if (arrayFaceNumber[intFaceValue] == 2){  
                                intSecondHighestMatchingCard = intFaceValue + 1;
                            }
                        }
                    }
                    
                    // Adds the highest and second highest match in a way to
                    // allow the correct winner to be found in case both have
                    // either 2 pair or a full house
                    intScore += (intHighestMatchingCard * 100) 
                            + intSecondHighestMatchingCard;
                }
            }
        
        // Returns the calculated score value
        return intScore;
        }
        
        public void ComputerDiscard(Deck deckDrawnFrom){
            
            // Checks if the hand cannot be improved
            if(this.FlushAmount().equals("Large") || this.StraightAmount().equals("Large") 
                    || this.Matching().contains("Full House") 
                    || this.Matching().contains("Four of a Kind")){
                
                // Tells the user the computer did not discard any cards
                System.out.println("The computer discarded 0 cards.");
            }
            else{
                
                // Checks if discarding 1 card is the optimal play
                if(this.FlushAmount().equals("Small") || this.StraightAmount().equals("Small")
                        || this.Matching().contains("Two Pairs")){
                    
                    // Finds the card to discard for flush
                    if(this.FlushAmount().equals("Small")){
                        
                        // Initializes strings for 1 of the suits in the computer's 
                        // hand as none
                        String strSuit1 = "None";
                        
                        // Initalizes integer for the last card referenced of 
                        // each suit to 0
                        int intLastSuit1 = 0;
                        int intLastSuit2 = 0;
                        
                        // Counts the number of the first suit
                        int intSuit1Count = 0;
                        
                        // Checks the suit and value of each card
                        for (int intCardNumber = 0; intCardNumber < 5; intCardNumber++){
                            
                            // If this is the first card, sets the suit and value
                            // to the card
                            if (strSuit1.equals("None")){
                                strSuit1 = array5CardHand[intCardNumber].strSuit;
                                intLastSuit1 = intCardNumber;
                                intSuit1Count++;
                            }
                            else{
                                
                                // If the card matches the first suit, increases
                                // the number of suit 1
                                if (strSuit1.equals(array5CardHand[intCardNumber].strSuit)){
                                    intSuit1Count++;
                                }
                                
                                // If the card does not match the first suit,
                                // changes the last referenced suit 2 card
                                else{
                                    intLastSuit2 = intCardNumber;
                                }
                            }
                        }
                        
                        // Checks with suit has a card to discard
                        if (intSuit1Count == 1){
                            
                            // Discards the first suit's card
                            this.Discard(intLastSuit1);
                        }
                        else{
                            
                            // Discards the second suit's card
                            this.Discard(intLastSuit2);
                        }
                    }
                    else{
                        
                        // Finds the card to discard for straight
                        if (this.StraightAmount().equals("Small")){
                            
                            // Initializes the card to discard as the last 1
                            int intCardtoDiscard = 4;
                            
                            // Initializes the card's value to discard as the last 1
                            int intCardValue = array5CardHand[4].intFace;
                            
                            // Initializes an empty array of 5 integers
                            int[] arrayOrdered = new int[5];
                            
                            // Adds the cards' face value in hand into an array
                            for (int intCardOrder = 0; intCardOrder < 5; intCardOrder++){
                                arrayOrdered[intCardOrder] = array5CardHand[intCardOrder].intFace;
                            }
                            
                            // Orders the cards in ascending order
                            Arrays.sort(arrayOrdered);
                            
                            // Checks each card to see if it isn't in the straight
                            for (int intCard = 0; intCard < 4; intCard++){
                                
                                // If the card isn't in the straight, then the
                                // face value is saved
                                if (arrayOrdered[intCard] + 1 != arrayOrdered[intCard + 1]){
                                    intCardValue = arrayOrdered[intCard];
                                }
                                
                                // If the card matches the value, saves the 
                                // position, but does not discard now in case of 
                                // a pair
                                if (array5CardHand[intCard].intFace == intCardValue){
                                    intCardtoDiscard = intCard;
                                }
                            }
                            
                            this.Discard(intCardtoDiscard);
                        }
                        
                        // Finds the card to discard for 2 pair
                        else{
                            
                            // Initializes the 3 integers of the faces as 15
                            int intFace1 = 15;
                            int intFace2 = 15;
                            int intFace3 = 15;
                            
                            // Initializes the 3 integers of the face counts as 0
                            int intCount1 = 0;
                            int intCount2 = 0;
                            int intCount3 = 0;
                            
                            // Declares integer intNonPair
                            int intNonPair;
                            
                            // Finds the count of the 3 faces
                            for (Card cardInHand : array5CardHand){
                                
                                // Adds to the face 1 count is it is blank or
                                // is equal to the value
                                if (intFace1 == 15 || intFace1 == cardInHand.intFace){
                                    intFace1 = cardInHand.intFace;
                                    intCount1++;
                                }
                                else{
                                    // Adds to the face 2 count is it is blank or
                                    // is equal to the value
                                    if (intFace2 == 15 || intFace2 == cardInHand.intFace){
                                        intFace2 = cardInHand.intFace;
                                        intCount2++;
                                    }
                                    
                                    // If the card isn't either of the first 2 
                                    // faces, then adds to the face 3 count
                                    else{
                                        intFace3 = cardInHand.intFace;
                                        intCount3++;
                                    }
                                }
                            }
                            
                            // Checks if face 3 isn't in a pair
                            if (intCount1 == intCount2){
                                intNonPair = intFace3;
                            }
                            else{
                                
                                // Checks if face 2 isn't in a pair
                                if (intCount1 == intCount3){
                                    intNonPair = intFace2;
                                }
                                
                                // Sets face 1 as not being in a pair
                                else{
                                    intNonPair = intFace1;
                                }
                            }
                            
                            // Finds the card that isn't in a pair and discards it
                            for (int intCardPosition = 0; intCardPosition < 5;
                                    intCardPosition++){
                                if (array5CardHand[intCardPosition].intFace == intNonPair){
                                    this.Discard(intCardPosition);
                                }
                            }
                        }
                    }
                    
                    // The computer draws a new card
                    this.Draw(deckDrawnFrom.Deal());
                    
                    // Tells the user the computer discarded 1 card
                    System.out.println("The computer discarded 1 card.");
                }
                else{
                    
                    // Checks if discarding 2 cards is the optimal play
                    if (this.Matching().contains("Three of a Kind")){
                        
                        // Declares an integer intMatchingValue
                        int intMatchingValue;
                        
                        // Finds the value that has 3 matching cards
                        if (array5CardHand[0].intFace == array5CardHand[1].intFace){
                            intMatchingValue = array5CardHand[1].intFace;
                        }
                        else{
                            if (array5CardHand[2].intFace == array5CardHand[3].intFace){
                                intMatchingValue = array5CardHand[2].intFace;
                            }
                            else{
                                if (array5CardHand[0].intFace == array5CardHand[3].intFace){
                                    intMatchingValue = array5CardHand[3].intFace;
                                }
                                else{
                                    intMatchingValue = array5CardHand[4].intFace;
                                }
                            }
                        }
                        
                        // Finds the nonmatching cards and discards them
                        for (int intCardPosition = 0; intCardPosition < 5; intCardPosition++){
                            if (array5CardHand[intCardPosition].intFace != intMatchingValue){
                                this.Discard(intCardPosition);
                            }
                        }
                        
                        // The computer draws 2 cards
                        this.Draw(deckDrawnFrom.Deal());
                        this.Draw(deckDrawnFrom.Deal());
                        
                        // Tells the user the computer discarded 2 cards
                        System.out.println("The computer discarded 2 cards."); 
                    }
                    else{
                        
                        // Checks if the hand has a pair
                        if (this.Matching().contains("One Pair")){
                            // Initializes the matching value as 15
                            int intMatchingValue = 15;
                            
                            // Finds the matching value by looping through all 
                            // cards against each other
                            for (int intFirstCardPosition = 0; intFirstCardPosition <5;
                                    intFirstCardPosition++){
                                for (int intSecondCardPosition = 0; intSecondCardPosition <5;
                                    intSecondCardPosition++){
                                    
                                    // Checks if both cards are not in the same position
                                    if (intFirstCardPosition != intSecondCardPosition){
                                        
                                        // Checks if the cards are the pair
                                        if (array5CardHand[intFirstCardPosition].intFace 
                                                == array5CardHand[intSecondCardPosition].intFace){
                                            intMatchingValue = array5CardHand[intFirstCardPosition].intFace;
                                        }
                                    }
                                }
                            }
                            
                            // Discards any card that isn't in a pair
                            for (int intCardPosition = 0; intCardPosition <5;
                                    intCardPosition++){
                                if (array5CardHand[intCardPosition].intFace 
                                        != intMatchingValue){
                                    this.Discard(intCardPosition);
                                }
                            }
                        }
                        
                        // Only will run if the hand only has high card
                        else{
                            
                            // Finds the 3 cards to discard
                            for (int intDiscardCount = 0; intDiscardCount <3;
                                    intDiscardCount++){
                                
                                // Initializes smallest card as 15
                                int intSmallestCard = 15;
                                
                                // Initializes smallest card's position as 0
                                int intSmallestPosition = 0;
                                
                                // Finds the smallest card
                                for (int intCardPosition = 0; intCardPosition <5;
                                        intCardPosition++){
                                    
                                    // Checks if the card is smaller than the
                                    // smallest card and not an Ace or empty
                                    if (array5CardHand[intCardPosition].intFace > 1
                                            && array5CardHand[intCardPosition].intFace 
                                            < intSmallestCard){
                                        
                                        // Changes smallest to current card
                                        intSmallestCard = array5CardHand[intCardPosition].intFace;
                                        intSmallestPosition = intCardPosition;
                                    }
                                }
                                this.Discard(intSmallestPosition);
                            }
                        }
                        
                        // The computer draws 3 cards
                        this.Draw(deckDrawnFrom.Deal());
                        this.Draw(deckDrawnFrom.Deal());
                        this.Draw(deckDrawnFrom.Deal());
                        
                        // Tells the user the computer discarded 3 cards
                        System.out.println("The computer discarded 3 cards."); 
                    }
                }
            }
        }
    }
}
