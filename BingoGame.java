import java.lang.reflect.Array;
import java.util.ArrayList;


public class BingoGame {
    private int player;//プレイヤー人数
    private String startMsg = "ビンゴゲームを開始します";
    private String endMsg = "全員分のカードを表示します";
    private ArrayList<BingoCard> bc=new ArrayList<BingoCard>();//ビンゴカード
    private ArrayList<BingoCard> lanking=new ArrayList<BingoCard>();//順位
    private BingoMachine bm;

    public int getPlayer() {
        return this.player;
    }

    BingoGame(){
        this(1);
    }
    BingoGame(int player){
        this.player=player;
        // 参加人数分のカードを生成する
        for(int i=0;i<this.player;i++){
            bc.add(new BingoCard("player"+(i+1)));
        }
            //ビンゴマシーンを生成する
            bm=new BingoMachine();
    }

    public void gameStart(){
        System.out.println("ビンゴカードを配ります。");

        printAllCard(bc,false);

        System.out.println(startMsg);
        //ビンゴがでるまで番号を発表する。
        UNTILBINGO:for(int i=0;i<75;i++){

            int num=bm.callBingoNumber();    // ビンゴマシーンから次の番号を取得する
            announce(bm.getRound(),num);
    // プレイヤー人数分のビンゴカードに穴をあける
            for(int j=0;j<bc.size();j++){
                bc.get(j).checkCard(num);
                bc.get(j).checkBingo(bm.getRound());
            }
//リーチとビンゴがあった時の処理
            for(int k=0;k<bc.size();k++){
                if(bc.get(k).bingo>=1){
                    System.out.println("ＢＩＮＧＯがでました!!!"+bc.get(k).name+"さん、ビンゴです!!!");
                    lanking.add(bc.get(k));
                    bc.remove(k);//bcからlankingに移行する
                    if(bc.size()==0){break UNTILBINGO;}
                }else if(bc.get(k).reach>=1){
                    System.out.println("リーチの人がいます！さあ、誰が先にビンゴするでしょうか！");
                    break;
                }        
            }

        }
    //全員分の結果を表示する

        System.out.println(endMsg);
        printCardByLanking(lanking,true);
    }



    /**
     * 全員分のビンゴカードを表示する（生成順）
     * @param targetList
     * @param statusPrint
     */
    void printAllCard(ArrayList<BingoCard> targetList,boolean statusPrint){
        for(int i=0;i<targetList.size();i++){
            targetList.get(i).printCard(statusPrint);
        }
    }

    /**
     * 全員分のカードを表示する（ランキング順）
     * @param targetList
     * @param statusPrint
     */
    void printCardByLanking(ArrayList<BingoCard> targetList,boolean statusPrint){
        for(int i=0;i<targetList.size();i++){
            System.out.printf("%3d位    ROUND:%2d\n",i+1,targetList.get(i).getBingoRound());
            targetList.get(i).printCard(statusPrint);
        }
    }


    /**
     * 番号を発表する
     * @param number 発表された番号
     * @param roundNow 現在のラウンド
     */
    private void announce(int roundNow,int number){
        if(number==-1){
            System.out.println("ビンゴはすべて終了しました。");
        }
        if(roundNow==0){    
            System.out.println("最初の番号は…");
        }else{
            System.out.println("次の番号は…");
        }
        System.out.println(number);
        System.out.println("です！！！");
    }   
}
