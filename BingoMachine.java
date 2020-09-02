import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class BingoMachine {
    private int round;// �Q�[���J�E���g
    static final int maxRound = 75;
    ArrayList<Integer> bingoMachine;// ���\����ʂ̔z��

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
     * �r���S�}�V�[�����񂵂ċʂ��o��
     * 
     * @return �o���ʂ̔ԍ�
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