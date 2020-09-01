public class BingoGame {
    int player;//�v���C���[�l��
    String startMsg = "�r���S�Q�[�����J�n���܂�";
    String reachMsg = "���[�`�ł��I�I�I";
    String bingoMsg = "�a�h�m�f�n�ł��I�I�I";
    String endMsg = "�S�����̃J�[�h��\�����܂�";


    BingoGame(){
        this(1);
    }
    BingoGame(int player){
        this.player=player;
    }

    public void gameStart(){
        System.out.println("�r���S�J�[�h��z��܂��B");
        // �Q���l�����̃J�[�h�𐶐�����
        BingoCard[] bc=new BingoCard[this.player];
        for(int i=0;i<this.player;i++){
            bc[i]=new BingoCard("player"+(i+1));
        }
        printAllCard(bc);

    //�r���S�}�V�[���𐶐�����
        BingoMachine bm=new BingoMachine();
        System.out.println(startMsg);
    //�r���S���ł�܂Ŕԍ��𔭕\����B
        UNTILBINGO:for(int i=0;i<75;i++){
            // if(i%10==0){
            //     System.out.println("���Ԕ��\�ł�");
            //     printAllCard(bc);
            // }

            int num=bm.callBingoNumber();    // �r���S�}�V�[�����玟�̔ԍ����擾����
            announce(bm.round,num);
    // �v���C���[�l�����̃r���S�J�[�h�Ɍ���������
            for(int j=0;j<player;j++){
                bc[j].checkCard(num);
                bc[j].checkBingo();
            }
    //���[�`�̐l��������A�S�����̃r���S�J�[�h��\������
            for(int k=0;k<player;k++){
                if(bc[k].bingo>=1){
                    System.out.println("�a�h�m�f�n!!!�a�h�m�f�n���ł܂���!!!");
                    break UNTILBINGO;
                }else if(bc[k].reach>=1){
                    System.out.println("���[�`�̐l�����܂��I�����A�N����Ƀr���S����ł��傤���I");
                    break;
                }        
            }

        }
    //�S�����̌��ʂ�\������

        System.out.println(endMsg);
        printAllCard(bc);
    }



    //�������牺��private���\�b�h

    /**
     * �S�����̃J�[�h��\������
     * @param roundNow
     * @param number
     */
    void printAllCard(BingoCard[] bc){
        for(int i=0;i<player;i++){
            bc[i].printCard();
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
