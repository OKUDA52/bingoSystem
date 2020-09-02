import java.lang.reflect.Array;
import java.util.ArrayList;


public class BingoGame {
    private int player;//�v���C���[�l��
    private String startMsg = "�r���S�Q�[�����J�n���܂�";
    private String endMsg = "�S�����̃J�[�h��\�����܂�";
    private ArrayList<BingoCard> bc=new ArrayList<BingoCard>();//�r���S�J�[�h
    private ArrayList<BingoCard> lanking=new ArrayList<BingoCard>();//����
    private BingoMachine bm;

    public int getPlayer() {
        return this.player;
    }

    BingoGame(){
        this(1);
    }
    BingoGame(int player){
        this.player=player;
        // �Q���l�����̃J�[�h�𐶐�����
        for(int i=0;i<this.player;i++){
            bc.add(new BingoCard("player"+(i+1)));
        }
            //�r���S�}�V�[���𐶐�����
            bm=new BingoMachine();
    }

    public void gameStart(){
        System.out.println("�r���S�J�[�h��z��܂��B");

        printAllCard(bc,false);

        System.out.println(startMsg);
        //�r���S���ł�܂Ŕԍ��𔭕\����B
        UNTILBINGO:for(int i=0;i<75;i++){

            int num=bm.callBingoNumber();    // �r���S�}�V�[�����玟�̔ԍ����擾����
            announce(bm.getRound(),num);
    // �v���C���[�l�����̃r���S�J�[�h�Ɍ���������
            for(int j=0;j<bc.size();j++){
                bc.get(j).checkCard(num);
                bc.get(j).checkBingo(bm.getRound());
            }
//���[�`�ƃr���S�����������̏���
            for(int k=0;k<bc.size();k++){
                if(bc.get(k).bingo>=1){
                    System.out.println("�a�h�m�f�n���ł܂���!!!"+bc.get(k).name+"����A�r���S�ł�!!!");
                    lanking.add(bc.get(k));
                    bc.remove(k);//bc����lanking�Ɉڍs����
                    if(bc.size()==0){break UNTILBINGO;}
                }else if(bc.get(k).reach>=1){
                    System.out.println("���[�`�̐l�����܂��I�����A�N����Ƀr���S����ł��傤���I");
                    break;
                }        
            }

        }
    //�S�����̌��ʂ�\������

        System.out.println(endMsg);
        printCardByLanking(lanking,true);
    }



    /**
     * �S�����̃r���S�J�[�h��\������i�������j
     * @param targetList
     * @param statusPrint
     */
    void printAllCard(ArrayList<BingoCard> targetList,boolean statusPrint){
        for(int i=0;i<targetList.size();i++){
            targetList.get(i).printCard(statusPrint);
        }
    }

    /**
     * �S�����̃J�[�h��\������i�����L���O���j
     * @param targetList
     * @param statusPrint
     */
    void printCardByLanking(ArrayList<BingoCard> targetList,boolean statusPrint){
        for(int i=0;i<targetList.size();i++){
            System.out.printf("%3d��    ROUND:%2d\n",i+1,targetList.get(i).getBingoRound());
            targetList.get(i).printCard(statusPrint);
        }
    }


    /**
     * �ԍ��𔭕\����
     * @param number ���\���ꂽ�ԍ�
     * @param roundNow ���݂̃��E���h
     */
    private void announce(int roundNow,int number){
        if(number==-1){
            System.out.println("�r���S�͂��ׂďI�����܂����B");
        }
        if(roundNow==0){    
            System.out.println("�ŏ��̔ԍ��́c");
        }else{
            System.out.println("���̔ԍ��́c");
        }
        System.out.println(number);
        System.out.println("�ł��I�I�I");
    }   
}
