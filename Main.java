public class Main {
    public static void main(String[] args) {
//Input how many player as args[0]
try{
        BingoGame bg=new BingoGame(Integer.parseInt(args[0]));
        bg.gameStart();
    }catch(NumberFormatException e){
System.out.println("ƒvƒŒƒCƒ„[”‚ğ“ü—Í‚µ‚Ä‚­‚¾‚³‚¢");
}
 
        
    }

}

