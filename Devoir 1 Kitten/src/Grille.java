import java.lang.Math;
import java.util.Scanner;

public class Grille {
	//Nombre de lignes dans la grille
	public int rows; 
	
	//Nombre de colonnes dans la grille
	public int cols; 
	
	private Case[][] grille;
	
	// Demande  à l’utilisateur d’entrer son mouvement
	public String prompt;
	
	public Case[][] getGrille() {
		return grille;
	}
	
    
    // Cette fonction prend 2 entiers en parametre et trouve des cases vides 
    // comprises entre ces parametres et retourne leurs coordonnees 
    public Point randomEmptyCell(int rows, int cols) {
    	
		int randomI = (int) (Math.random() * rows);
		int randomJ = (int) (Math.random() * cols);
		
		while (!(this.grille[randomI][randomJ] instanceof CaseVide)) {
			randomI = (int) (Math.random() * rows);
			randomJ = (int) (Math.random() * cols);
		}
		Point p = new Point(randomI, randomJ);
		return p ;	
    }
    
    
    // Générer des coordonnées aléatoires dans les intervalles [x1,x2] et [y1,y2]
    public Point randomCellInRange(int x1, int x2, int y1, int y2) {
        
        int randomI = (int) (Math.random() * (y2 - y1 + 1)) + y1;
        int randomJ = (int) (Math.random() * (x2 - x1 + 1)) + x1;
        
        Point p = new Point(randomI, randomJ);
        return p;
    }  
    
    // Construire la grille
	public Grille(int nbrPiecesX, int nbrPiecesY, int largeurPiece,
			int hauteurPiece, int nbrNonKitten) {
		
		rows = nbrPiecesY*(hauteurPiece+1)+ 1;     
		cols = nbrPiecesX*(largeurPiece+1)+ 1;
		grille = new Case[rows][cols];
		prompt = "R.O.B. [" + String.valueOf(0) + "]>";
		
		// Initialisation d'une grille vide
		for (int i = 0; i < rows; i++) {
			for (int j= 0;j < cols; j++) {
				
				if ((( j%(largeurPiece+1) )== 0) || ( i%(hauteurPiece+1)== 0)) {
					grille[i][j]= new Mur();
				}
				else grille[i][j]= new CaseVide();
				
				if (grille[i][j] instanceof Mur) {
					if (j%(largeurPiece+1) == Math.ceil(largeurPiece/2)+1) {
						if (i!=0 && i!= rows-1){
							grille[i][j]= new Porte();
						}
					}
					if (i%(hauteurPiece+1) == Math.ceil(hauteurPiece/2)+1) {
						if (j!=0 && j!= cols-1){
							grille[i][j]= new Porte();
						}
					}
				}
			}
		}
	
		// Placer le chaton dans une case aleatoire
		Point kitten = randomEmptyCell(rows, cols);
		grille[kitten.getX()][kitten.getY()] = new Kitten();
		
		// Placer le teleporteur dans une case aleatoire
		Point teleporteur = randomEmptyCell(rows, cols);
		grille[teleporteur.getX()][teleporteur.getY()] = new Teleporteur();
		
		// Placer une clé par piece en prenant des intervalles [x1,x2] et [y1,y2] 
		int x1 = 1;
		int x2 = largeurPiece;
		int y1 = 1;
		int y2 = hauteurPiece;
		
		for (int i = 0; i < nbrPiecesY ; i++) {
			for (int j= 0;j < nbrPiecesX ; j++) {
				
				Point keyPoint = randomCellInRange(x1, x2, y1, y2);
				grille[keyPoint.getX()][keyPoint.getY()] = new Cle();
				x1 += largeurPiece + 1;
				x2 += largeurPiece + 1;
			}
			
			y1 += hauteurPiece + 1;
	        y2 += hauteurPiece + 1;
			x1 = 1;
	        x2 = largeurPiece;
		}
		
		// Placer les NonKittenItems dans des cases vides aleatoires
		Point nonKittenItem = randomEmptyCell(rows, cols);
		
		while (nbrNonKitten > 0) {
			NonKitten kitty = new NonKitten();
			
			grille[nonKittenItem.getX()][nonKittenItem.getY()] = kitty ;
			nbrNonKitten-= 1;
			nonKittenItem = randomEmptyCell(rows, cols);
		}
 }
	
	
	
	// On indique si c’est possible pour le robot de marcher sur
	// la cellule de coordonnée (x, y)
	
	public boolean deplacementPossible(Robot robot, int x, int y) {
		
		// le prompt est different selon si l'utilisateur possede un teleporteur 
		// il est ainsi mis a jour avant l'affichage 
		
		if (grille[x][y] instanceof Mur) { 
			return false;
		}
		
		else if ((grille[x][y] instanceof Porte)) {
			if ( robot.getKeys() > 0) {
				// Deplacement possible si le robot possede au moins une cle
				robot.setKeys(robot.getKeys()-1);
				
				if (robot.getTeleporteur()) {
					prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]T>";
					}
				else prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]>";
				return true;
			}
			else {
				return false;  
			}
		}
		
		else if ((grille[x][y] instanceof Cle)) {
			robot.setKeys(robot.getKeys()+1);
			
			if (robot.getTeleporteur()) {
			prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]T>";
			}
			else prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]>";
			return true;
		}
		
		else if ((grille[x][y] instanceof Teleporteur)) {
			robot.setTeleporteur(true);
			prompt= "R.O.B. [" + String.valueOf(robot.getKeys()) + "]T>";
			grille[x][y] = new CaseVide();
			return true;
		}
		else {
			return true;
		}
	}	
	
	
	public void deplacement(Robot robot) {
		Point p = robot.getPoint();
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		
		// verifier si l'utilisateur peut se teleporter
		if (robot.getTeleporteur()){ 
    		if (input.equals("t")){
    			robot.setPoint(randomEmptyCell(rows, cols));
    			robot.setTeleporteur(false);
    			prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]>";
    		}
    	}
		
    	else prompt = "R.O.B. [" + String.valueOf(robot.getKeys()) + "]>";
			
		// Deplacement du robot selon l'entrée de l'utilisateur
		
		if (input.equals("w")) {
			if (deplacementPossible( robot, p.getX()-1, p.getY())) {
				robot.moveUp();
			}
		}
		else if (input.equals("a")) {
			if (deplacementPossible( robot, p.getX(), p.getY()-1)) {
				robot.moveLeft();
			}
		}
		else if (input.equals("s")) {
			if (deplacementPossible( robot, p.getX()+1, p.getY())) {
				robot.moveDown();
			}
		}
		else if (input.equals("d")) {
			if (deplacementPossible( robot, p.getX(), p.getY()+1)) {
				robot.moveRight();
			}
		}
	}
	
	
	// Lance l’interaction entre le robot et la case sur laquelle il se trouve
	public void interagir(Robot robot) {
		Point p = robot.getPoint();
		int i= p.getX();
		int j= p.getY();
		
		if (grille[i][j] instanceof Kitten) {
			grille[i][j].interagir(robot);
			// Terminer le jeu en verifiant la condition de la boucle while
			Point kitty = new Point(i,j);
			robot.setPoint(kitty);
		}
		
		else if (grille[i][j] instanceof NonKitten) {
			grille[i][j].interagir(robot);
		}
		
		else if (grille[i][j] instanceof Cle) {
			grille[i][j] = new CaseVide();
			grille[i][j].interagir(robot);
		}
		
		if (grille[i][j] instanceof Porte) {
			grille[i][j] = new CaseVide();
			grille[i][j].interagir(robot);
		}
		
		else if (grille[i][j] instanceof Teleporteur) {
			grille[i][j] = new CaseVide();
			grille[i][j].interagir(robot);
		}
	}
	
	
	// Afficher la grille dans la console 
	public void afficher(Robot robot) {
  
    	Point p = robot.getPoint();
    	interagir(robot);
    	
		for (int i = 0; i < rows; i++) {
			for (int j= 0;j < cols; j++) {
				if (i== p.getX() && j== p.getY()) {
					// afficher le robot #
					System.out.print((char) 35);}
				else { // afficher la grille
				System.out.print(grille[i][j].getRepresentation());
				}
			} 
			System.out.print("\n");
		}
		System.out.print( prompt );
    }
}
	





	
