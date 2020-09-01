import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class BingoCard {

    static int cardRow=5;//カードのヨコ数
    static int cardCol=5;//カードのタテ数
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


    int[] bingoCard = new int[cardCol*cardRow];
    int reach;//リーチの数
    int bingo;//ビンゴの数
    String name;//プレイヤー名

    BingoCard(){
        this("player");
    }

    BingoCard(String name){
        reach=0;
        bingo=0;
        this.name=name;
        int colNum=bingoMaxNum/cardCol;//１列にいくつ数字が入るか
        for(int i=0;i<cardCol;i++){
            ArrayList<Integer> tempCol= makeTmpArray((i*colNum)+1,(i+1)*colNum);
            for( int j =0;j<cardRow;j++){
                bingoCard[j+cardRow*(i)]=tempCol.get(j);
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
     * @param bingoCard
     */
    public void printCard() {

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
    // System.out.printf("%2d",bingoCard[j+i*5]);
    System.out.print(" | ");
    }
    System.out.println();
    }

    System.out.println(" -------------------------");
    System.out.printf( "player:%10s\n",name);
    System.out.printf(" リーチ：%d ビンゴ：%d\n",this.reach,this.bingo);
    System.out.println(" -------------------------");

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
     */
    void checkBingo(){
        reach=0;
        bingo=0;
        holeCount(slantP1);
        holeCount(slantP2);
        holeCount(rowP1);
        holeCount(rowP2);
        holeCount(rowP3);
        holeCount(rowP4);
        holeCount(rowP5);
        holeCount(colP1);
        holeCount(colP2);
        holeCount(colP3);
        holeCount(colP4);
        holeCount(colP5);
        
    }

    /**
     * checkBingoのサブルーチン
     * 与えられたパターンの穴を数えて、リーチとビンゴをカウントする
     * @param intArray
     */
    private void holeCount(int[] intArray){
        int count=0;//空いた穴の数
        for(int value:intArray){
            if(bingoCard[value]==OUTNUM){
                count++;
                }
        }
        if(count==4){
            this.reach++;
        }else if(count==5){
            this.bingo++;
        }
    }



}