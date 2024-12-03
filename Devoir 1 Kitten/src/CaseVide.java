
public class CaseVide extends Case {
	public CaseVide() {
    	super.representation = (char) 32;
    }
	
	@Override
	public boolean interactionPossible(Robot robot) {
    	return true;
	}
	 
	@Override 
	public void interagir(Robot robot) {
	}

}


