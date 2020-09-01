public class BingoGame {
    int player;//プレイヤー人数
    String startMsg = "ビンゴゲームを開始します";
    String reachMsg = "リーチです！！！";
    String bingoMsg = "ＢＩＮＧＯです！！！";
    String endMsg = "全員分のカードを表示します";


    BingoGame(){
        this(1);
    }
    BingoGame(int player){
        this.player=player;
    }

    public void gameStart(){
        System.out.println("ビンゴカードを配ります。");
        // 参加人数分のカードを生成する
        BingoCard[] bc=new BingoCard[this.player];
        for(int i=0;i<this.player;i++){
            bc[i]=new BingoCard("player"+(i+1));
        }
        printAllCard(bc);

    //ビンゴマシーンを生成する
        BingoMachine bm=new BingoMachine();
        System.out.println(startMsg);
    //ビンゴがでるまで番号を発表する。
        UNTILBINGO:for(int i=0;i<75;i++){
            // if(i%10==0){
            //     System.out.println("中間発表です");
            //     printAllCard(bc);
            // }

            int num=bm.callBingoNumber();    // ビンゴマシーンから次の番号を取得する
            announce(bm.round,num);
    // プレイヤー人数分のビンゴカードに穴をあける
            for(int j=0;j<player;j++){
                bc[j].checkCard(num);
                bc[j].checkBingo();
            }
    //リーチの人がいたら、全員分のビンゴカードを表示する
            for(int k=0;k<player;k++){
                if(bc[k].bingo>=1){
                    System.out.println("ＢＩＮＧＯ!!!ＢＩＮＧＯがでました!!!");
                    break UNTILBINGO;
                }else if(bc[k].reach>=1){
                    System.out.println("リーチの人がいます！さあ、誰が先にビンゴするでしょうか！");
                    break;
                }        
            }

        }
    //全員分の結果を表示する

        System.out.println(endMsg);
        printAllCard(bc);
    }



    //ここから下はprivateメソッド

    /**
     * 全員分のカードを表示する
     * @param roundNow
     * @param number
     */
    void printAllCard(BingoCard[] bc){
        for(int i=0;i<player;i++){
            bc[i].printCard();
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
