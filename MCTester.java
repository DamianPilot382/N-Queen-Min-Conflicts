public class MCTester{
    public static void main(String[] args){
        //MinConflict searcher = new MinConflict(new Board(new int[]{5, 3, 0, 1, 4, 6, 2, 7}), 1);
        int iter = 1;
        double total = 0;
        int count = 0;

        while(iter-- > 0){
            MinConflict searcher = new MinConflict(100, 10000);
            searcher.search();
            if(searcher.board.getAttackingValue() == 0)
                count++;
            total++;
            //System.out.println(searcher.board);
        }

        System.out.println(count/total*100);
        //MinConflict searcher = new MinConflict(16, 100000);

        //System.out.println(searcher.board);
        //System.out.println(searcher);

        //System.out.println(searcher.search());
    }
}