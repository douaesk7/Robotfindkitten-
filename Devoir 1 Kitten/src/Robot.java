
public class Robot {
	private String robotName;
	private Point p;
	private int keys; 
	private boolean teleporteur;
	
	public Robot(Point p) {
		this.p= p; 
	}
	
	public Point moveUp() {
		Point point = new Point(this.p.getX()-1, this.p.getY()); 
		this.p = point;
		return p;
	}
	
	public Point moveDown() {
		Point point = new Point(this.p.getX()+1, this.p.getY()); 
		this.p = point;
		return p;
	}
	
	public Point moveRight() {
		Point point = new Point(this.p.getX(), this.p.getY()+1); 
		this.p = point;
		return p;
	}
	
	public Point moveLeft() {
		Point point = new Point(this.p.getX(), this.p.getY()-1); 
		this.p = point;
		return p;
	}
	
	public String getRobotName() {
		return robotName;
	}

	public int getKeys() {
		return keys;
	}

	public void setKeys(int keys) {
		this.keys = keys;
	}

	public boolean getTeleporteur() {
		return teleporteur;
	}

	public void setTeleporteur(boolean teleporteur) {
		this.teleporteur = teleporteur;
	}
	
	public Point getPoint() {
		return p;
	}
	
	public void setPoint(Point p) {
		this.p = p;
	}
}



