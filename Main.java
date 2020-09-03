public class Main {
    public static void main(String[] args) {
//Input how many player as args[0]
        int player=1;
        if(args.length!=0){
            try{
                player=Integer.parseInt(args[0]);
            }catch(NumberFormatException e){
                System.out.println("ƒvƒŒƒCƒ„[”‚ğ®”‚Å“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
            }
        }

        BingoGame bg=new BingoGame(player);
        bg.gameStart();
        
        
    }

}

