
public class Porte extends Case {
	
    public Porte() {
    	super.representation = (char) 33;
    }
	
	@Override
	public boolean interactionPossible(Robot robot) {
    	return false;
	}
	
	@Override 
	public void interagir(Robot robot) {
		
	}

}
