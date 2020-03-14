import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class MinConflict {

    public Board board;
    private HashMap<Integer, HashSet<Integer>> conflictMap;
    private HashSet<Integer> conflicts;
    private int maxSteps;
    private final int n;
    

    public MinConflict(int n, int maxSteps){
        this.n = n;

        this.board = new Board(n);
        this.conflictMap = new HashMap<>(n, 1);
        this.maxSteps = maxSteps;
        this.conflicts = new HashSet<>(n);

        this.fillConflictsMap();
        this.checkAllConflicts();
    }

    private void fillConflictsMap(){
        for(int i = 0; i < n; i++)
            conflictMap.put(i, new HashSet<Integer>(n-1));
    }

    private void checkAllConflicts(){
        for(int i = 0; i < n; i++){
            for(int j = i + 1; j < n; j++){
                int a = board.getData()[i];
                int b = board.getData()[j];
                if(a == b || (j - i) == Math.abs(b - a)){
                    conflictMap.get(i).add(j);
                    conflictMap.get(j).add(i);
                    conflicts.add(i);
                    conflicts.add(j);
                }
            }
        }
    }

    public Board search(){
        Board current = this.board;
        int steps = maxSteps;

        while(--steps > 0){
            if(current.getAttackingValue() == 0)
                return current;
            minimizeConflict();
        }
        return this.board;
    }

    private void minimizeConflict(){
        int randCol = this.getRandomConflict();
        HashSet<Integer> best = null;
        int bestRow = -1;
        HashSet<Integer> next = null;

        for(int i = 0; i < n; i++){
            next = this.checkConflict(randCol, i);
            if(best == null || best.size() > next.size()){
                best = next;
                bestRow = i;
            }

        }

        this.updateConflicts(randCol, next);
        this.board.getData()[randCol] = bestRow;

    }

    private int getRandomConflict(){
        return (int)(Math.random() * n);
    }

    private HashSet<Integer> checkConflict(int index, int newValue){

        HashSet<Integer> temp = new HashSet<>(n-1);

        for(int i = 0; i < n; i++){
            int j = board.getData()[i];

            if(newValue == j || Math.abs(i - index) == Math.abs(j - newValue))
                temp.add(i);

        }

        return temp;
    }

    private void updateConflicts(int index, HashSet<Integer> conf){

        Iterator<Integer> iter = conflictMap.get(index).iterator();

        while(iter.hasNext()){
            int next = iter.next();
            if(next != index)
            conflictMap.get(next).remove(index);
        }

        iter = conf.iterator();

        while(iter.hasNext()){
            int next = iter.next();
            conflictMap.get(next).add(index);
        }

        conflictMap.put(index, conf);
    }

    @Override
    public String toString(){
        
        StringBuilder builder = new StringBuilder();

        Iterator iter = conflictMap.keySet().iterator();

        while(iter.hasNext()){
            Integer index = (Integer) iter.next();

            builder.append(index + ": ");

            HashSet<Integer> next = conflictMap.get(index);

            for(int i : next){
                builder.append(i+" ");
            }

            builder.append("\n");
        }

        return builder.toString();
    }

}