
public class Kitten extends Case {
	
	public Kitten(){
        super.representation = getRandomSymbole();
    }
	
	@Override
	//The robot can interact with the kitten once he finds him
	public boolean interactionPossible(Robot robot) {
	    return true;
	}
	
	@Override
	public void interagir(Robot robot) {
		System.out.println("You found kitten! Way to go, robot.\r\n" 
			    + "Caramel <3 R.O.B.");
	}
	       
}
