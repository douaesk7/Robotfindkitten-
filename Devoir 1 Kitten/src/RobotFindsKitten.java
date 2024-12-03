/** Zouhair El Yani 20238383
 *  Douae Sakkat 20253363
 *  Ayman Kaissi 20253368
 */

public class RobotFindsKitten {
		public static void main( String[] args ) {
			  
			Grille array = new Grille(5,2,11,5,48) ;
			Case[][] grille = array.getGrille();
			Point p = array.randomEmptyCell(6, 6);
			Robot robot = new Robot(p);
			robot.setKeys(0);
			
			System.out.println( "       Bienvenue dans RobotFindsKitten" + "\n" +
			"Super Dungeon Master 3000 Ultra Turbo Edition !");
			
			array.afficher(robot);
			
			while (!( grille[robot.getPoint().getX()][robot.getPoint().getY()] instanceof Kitten )) {
				array.deplacement(robot);
				array.afficher(robot);	
			}
		}  
 }

  
