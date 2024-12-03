
public class Cle extends Case {
	// les cles sont representees par une apostrophe
	public Cle() {
		super.representation = (char) 39;
	}
	
	@Override
	public boolean interactionPossible(Robot robot) {
		return true;
	}
	
	@Override
	public void interagir(Robot robot) {
	}
}
