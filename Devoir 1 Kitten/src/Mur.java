
public class Mur extends Case {
	
    public Mur() {
    	super.representation = (char) 37;
    }
	
	@Override
	public boolean interactionPossible(Robot robot) {
    	return false;
	}
	
	@Override 
	public void interagir(Robot robot) {
		
	}

}
