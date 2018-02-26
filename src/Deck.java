import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class Deck {

    Card[] allCards = new Card[52];
    Card[] hand = new Card[52];
    int handSize = 0;
    int searchLocation = 0;

    public Deck(boolean fullSize){
        int cardCount = 0;
        if(fullSize){
            for(int i = 1; i < 14; i++){
                for(int j = 1; j < 5; j++){
                    this.allCards[cardCount] = (new Card(j, i));
                    cardCount++;
                }
            }

        }else{
            for(int k = 1; k < 14; k++){
                this.allCards[cardCount] = (new Card(1, k));
                cardCount++;
            }
        }
    }


    public void swap(int first, int second){
        Card firstCard = this.hand[first];
        this.hand[first] = this.hand[second];
        this.hand[second] = firstCard;
    }

    public void dealHand(int numCards){
        this.handSize += numCards;
        Random randomCard = new Random();
        for(int i = 0; i < numCards; i++){
            int cardNumber = randomCard.nextInt(this.allCards.length);
            Card thisCard = this.allCards[cardNumber];
            this.hand[i] = thisCard;
        }
        listHand();
    }

    public void listHand(){
        String result = "";
        for(int i = 0; i < this.handSize; i++){
            result += "card with suit ";
            result += this.hand[i].suit;
            result += " and value ";
            result += this.hand[i].value;
            result += "  ";
        }
        System.out.println(result);
    }

    public void shuffleDeck(){
        Collections.shuffle(Arrays.asList(allCards));
    }

    //sorting algorithms
    public void bubbleSortHand(){
        for(int j = 0; j < (this.handSize - 2); j++){
            for(int i = 0; i < (this.handSize - 1); i++){
                if(this.hand[i].value > this.hand[i + 1].value){
                    swap(i, (i + 1));
                }
            }
        }
    }

    public void selectionSortHand(){
        int amountOfUnsorted = this.handSize;
        int lowestCardIndex = 0;
        for(int j = 0; j < this.handSize; j++){
            for(int i = (this.handSize - amountOfUnsorted); i < this.handSize; i++){
                if(this.hand[i].value <= this.hand[lowestCardIndex].value){
                    lowestCardIndex = i;
                }
            }
            swap((this.handSize - amountOfUnsorted), lowestCardIndex);
            amountOfUnsorted--;
            lowestCardIndex = (this.handSize - amountOfUnsorted);
        }
    }

    public void mergeSortHand(){
        Card[] handArray = new Card[this.handSize];
        for(int i = 0; i < this.handSize; i++){
            handArray[i] = this.hand[i];
        }
        mergeSort(0, this.handSize - 1, handArray);
    }

    public void mergeSort(int low, int high, Card[] handArray){
        if(low < high){
            int middle = low + (high - low) / 2;
            mergeSort(low, middle, handArray);
            mergeSort(middle + 1, high, handArray);
            merge(low, middle, high, handArray);
        }
    }

    public void merge(int low, int middle, int high, Card[] handArray){
        for(int i = low; i <= high; i++){
            handArray[i] = this.hand[i];
        }

        int i = low;
        int j = middle + 1;
        int k = low;

        while(i <= middle && j <= high){
            if(handArray[i].value <= handArray[j].value){
                this.hand[k] = handArray[i];
                i++;
            }else{
                this.hand[k] = handArray[j];
                j++;
            }
            k++;
        }

        while(i <= middle){
            this.hand[k] = handArray[i];
            k++;
            i++;
        }
    }


    public void binarySearch(int searchTerm){
        binaryDivide(0, this.handSize, searchTerm);
        System.out.println(this.searchLocation);
    }

    public void binaryDivide(int low, int high, int searchTerm){
        if(low < high){
            int middle = low + (high - low) / 2;
            if(this.hand[middle].value >= searchTerm){
                binaryDivide(low, middle, searchTerm);
            }else{
                binaryDivide(middle + 1, high, searchTerm);
            }
            System.out.println(low + " " + middle + " " + high);
        }else{
            if(this.hand[low].value == searchTerm){
                this.searchLocation = low;
            }else{
                this.searchLocation = -1;
            }
        }

    }



}
