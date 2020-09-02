import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class BingoCard {

    // static int cardRow=5;//カードのヨコ数
    // static int cardCol=5;//カードのタテ数
    static int bingoMaxNum=75;//カードの最大値
    static String outMark="●";
    static final int OUTNUM=0;//穴が空いているしるしの数

    //当たり配列番号パターン
    static int[] slantP1={0,6,12,18,24};
    static int[] slantP2={4,8,12,16,20};
    static int[] rowP1={0,5,10,15,20};
    static int[] rowP2={1,6,11,16,21};
    static int[] rowP3={2,7,12,17,22};
    static int[] rowP4={3,8,13,18,23};
    static int[] rowP5={4,9,14,19,24};
    static int[] colP1={0,1,2,3,4};
    static int[] colP2={5,6,7,8,9};
    static int[] colP3={10,11,12,13,14};
    static int[] colP4={15,16,17,18,19};
    static int[] colP5={20,21,22,23,24};


    int[] bingoCard = new int[25];
    int reach;//リーチの数
    int bingo;//ビンゴの数
    String name;//プレイヤー名
    private int bingoRound;//Bingoしたラウンド

    public int getBingoRound() {
        return this.bingoRound;
    }

    BingoCard(){
        this("player");
    }

    BingoCard(String name){
        reach=0;
        bingo=0;
        this.name=name;
        for(int i=0;i<5;i++){
            ArrayList<Integer> tempCol= makeTmpArray((i*15)+1,(i+1)*15);
            for( int j =0;j<5;j++){
                bingoCard[j+5*(i)]=tempCol.get(j);
            }
        }
    //12番目だけ穴をあける
        bingoCard[12]=OUTNUM;
    }

    /**
     * ビンゴカードの列を生成する
     * @param min 格納される数値の最小値
     * @param max 格納される数値の最小値
     * @return シャッフル使いたいからArrayList<Integer>
     */
    public ArrayList<Integer> makeTmpArray(int min,int max){
        ArrayList<Integer> tmpList=new ArrayList<Integer>();
        int count=max-min;
        
        for(int i=0;i<count;i++){
            tmpList.add(min+i);
        }
        Collections.shuffle(tmpList);
        
        return tmpList;
    }

    /**
     * ビンゴカードを表示する
     * @param statusPrint trueだったら、リーチとビンゴ数を表示する
     */
    public void printCard(boolean statusPrint) {
    // System.out.println(" -------------------------");    
    System.out.printf( "  player名:%10s\n",name);

    System.out.println(" -------------------------");
    System.out.println(" |    B   I   N   G   O   |");
    System.out.println(" -------------------------");

    for(int j=0;j<5;j++){
        System.out.print(" | ");
        for(int i =0;i<5;i++){
    if(bingoCard[j+i*5]==0){
        System.out.printf("%2s",outMark);
    }else{
    System.out.printf("%2d",bingoCard[j+i*5]);
    }
    System.out.print(" | ");
    }
    System.out.println();
    }

    System.out.println(" -------------------------");
    if(statusPrint){System.out.printf(" リーチ：%d ビンゴ：%d\n\n",this.reach,this.bingo);}


    }


    /**
     * 番号をチェックして、ビンゴカードに穴をあける
     * @param num 今回の番号
     */
    void checkCard(int num){
        for(int i=0;i<25;i++){
            if(bingoCard[i]==num){
                bingoCard[i]=OUTNUM;
            }
        }
    }


    /**
     * ビンゴカードをチェックする
     * @param round ビンゴのラウンド
     */
    void checkBingo( int round){
        reach=0;
        bingo=0;
        holeCount(round,slantP1);
        holeCount(round,slantP2);
        holeCount(round,rowP1);
        holeCount(round,rowP2);
        holeCount(round,rowP3);
        holeCount(round,rowP4);
        holeCount(round,rowP5);
        holeCount(round,colP1);
        holeCount(round,colP2);
        holeCount(round,colP3);
        holeCount(round,colP4);
        holeCount(round,colP5);
        
    }

    /**
     * checkBingoのサブルーチン
     * 与えられたパターンの穴を数えて、リーチとビンゴをカウントする
     * @param intArray
     */
    private void holeCount(int round,int[] intArray){
        int count=0;//空いた穴の数
        for(int value:intArray){
            if(bingoCard[value]==OUTNUM){
                count++;
                }
        }
        if(count==4){
            this.reach++;
        }else if(count==5){
            this.bingoRound=round;
            this.bingo++;
        }
    }



}