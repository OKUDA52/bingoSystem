import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BingoMachine {
    private int round;// ゲームカウント
    static final int maxRound = 75;
    ArrayList<Integer> bingoMachine;// 発表する玉の配列

    public int getRound() {
        return this.round;
    }

    BingoMachine() {
        round = 0;
        bingoMachine = new ArrayList<Integer>();
        for (int i = 0; i < maxRound; i++) {
            bingoMachine.add(i + 1);
        }
        Collections.shuffle(bingoMachine);
    }

    /**
     * ビンゴマシーンを回して玉を出す
     * 
     * @return 出た玉の番号
     */
    int callBingoNumber() {
        if (round < maxRound) {
            int num = bingoMachine.get(this.round);
            this.round++;
            return num;
        } else {
            return -1;
        }
    }

}