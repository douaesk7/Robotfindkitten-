public class Teleporteur extends Case {
	
    public Teleporteur() {
    	super.representation = getRandomSymbole();
    }
	
	@Override
	public boolean interactionPossible(Robot robot) {
    	return true;
	}
	
	@Override 
	public void interagir(Robot robot) {
		robot.setTeleporteur(true);
	}

}

