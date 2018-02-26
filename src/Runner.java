public class Runner {

    public static void main(String[] args){
        Deck d = new Deck(true);
        d.shuffleDeck();
        d.dealHand(7);
        d.listHand();
        //d.bubbleSortHand();
        //d.selectionSortHand();
        d.mergeSortHand();
        d.listHand();
        d.binarySearch(7);
    }

}
