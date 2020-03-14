public class MCTester{
    public static void main(String[] args){
        MinConflict searcher = new MinConflict(8, 1000);

        System.out.println(searcher.board);

        System.out.println(searcher);
        //System.out.println(searcher.search());
    }
}