 package view;

import java.awt.*;
import java.io.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class Map extends AbstractDAO {
/*
 * <H1> The map class</H1> 
 * We will implement the IMAP interface in order to use the methods in other classes.
 * 
 */
	
	/*
	 * 
Instantiates a matrix so that we can manage our map.
	 */
	private String map2d[][] = new String[21][40];
	private String map1d[] = new String[40];
	private Image sand, wall, empty, rock, diamond, monster, redDiamond, redDiamondT;
	private Image perso_face, perso_back, perso_left, perso_right, blood;
	private Image coal_ore, iron_ore, diamond_ore, gold_ore, emerald_ore, redstone_ore, lapis_ore, diamondT, heart, heartB, heartL, nyancat;
	/*
	 * 
The different sprites according to the displacements.
	 */
	private Menu menu;
	private String skin, mapString = "";
	private int level;
	
	/*
	 * 
This attribute allows us to store the result of our stored procedure.
	 */
    private static String sqlshowMaps   = "{call showMaps(?)}";
	
	
	public Map(){
		
		/*
		 * 
Instantiating the menu.
		 */
		try{
			menu = new Menu();
			/*
			 * 
			We recover the skin and level.
			 */
			skin = menu.getSkin();
			setLevel(menu.getLevel());
		} catch (Exception e) {
			System.out.println(skin + getLevel());
		}
		
		try {
			
			/*
			 * 
One recovers the path, transforms it into an image and makes it available to the other classes.
This is done in all other methods
			 */
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\wall.png";
			ImageIcon img = new ImageIcon(path);
			wall = img.getImage();
		} catch (Exception e) {
			System.out.println("load1 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\empty.png";
			ImageIcon img = new ImageIcon(path);
			empty = img.getImage();
		} catch (Exception e) {
			System.out.println("load2 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\rock.png";
			ImageIcon img = new ImageIcon(path);
			rock = img.getImage();
		} catch (Exception e) {
			System.out.println("load3 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\diamond.png";
			ImageIcon img = new ImageIcon(path);
			diamond = img.getImage();
		} catch (Exception e) {
			System.out.println("load4 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\sand.png";
			ImageIcon img = new ImageIcon(path);
			sand = img.getImage();
		} catch (Exception e) {
			System.out.println("load5 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\monster.png";
			ImageIcon img = new ImageIcon(path);
			monster = img.getImage();
		} catch (Exception e) {
			System.out.println("load6 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_face.png";
			ImageIcon img = new ImageIcon(path);
			perso_face = img.getImage();
		} catch (Exception e) {
			System.out.println("load7 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_back.png";
			ImageIcon img = new ImageIcon(path);
			perso_back = img.getImage();
		} catch (Exception e) {
			System.out.println("load8 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_left.png";
			ImageIcon img = new ImageIcon(path);
			perso_left = img.getImage();
		} catch (Exception e) {
			System.out.println("load9 fail");
		}
		try {
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\perso_right.png";
			ImageIcon img = new ImageIcon(path);
			perso_right = img.getImage();
		} catch (Exception e) {
			System.out.println("load10 fail");
		}
			String basePath = new File("").getAbsolutePath();
			String path = basePath + "\\..\\view\\" + skin + "\\coal_ore.png";
			ImageIcon img = new ImageIcon(path);
			coal_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\emerald_ore.png";
			img = new ImageIcon(path);
			emerald_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\iron_ore.png";
			img = new ImageIcon(path);
			iron_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\diamond_ore.png";
			img = new ImageIcon(path);
			diamond_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\gold_ore.png";
			img = new ImageIcon(path);
			gold_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\lapis_ore.png";
			img = new ImageIcon(path);
			lapis_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\redstone_ore.png";
			img = new ImageIcon(path);
			redstone_ore = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\diamond_trans.png";
			img = new ImageIcon(path);
			diamondT = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\skinClassic\\heart.png";
			img = new ImageIcon(path);
			heart = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\skinClassic\\heart_break.png";
			img = new ImageIcon(path);
			heartB = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\skinClassic\\heart_lost.png";
			img = new ImageIcon(path);
			heartL = img.getImage();

			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\reddiamond.png";
			img = new ImageIcon(path);
			redDiamond = img.getImage();

			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\" + skin + "\\reddiamond_trans.png";
			img = new ImageIcon(path);
			redDiamondT = img.getImage();

			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\img\\blood.png";
			img = new ImageIcon(path);
			blood = img.getImage();
			
			basePath = new File("").getAbsolutePath();
			path = basePath + "\\..\\view\\img\\nyancat_trans.png";
			img = new ImageIcon(path);
			nyancat = img.getImage();
		
		
			try {
				mapString = openFile(getLevel());
			} catch (SQLException e1) {
				System.out.println("getMapById fail");
			}
			try{
				readFile();
				completeMap();
			} catch (Exception e) {
				System.out.println("read/complete fail");
			}
	}

	

	public Image getWall() {
		return wall;
	}
	public Image getEmpty() {
		return empty;
	}
	public Image getRock() {
		return rock;
	}
	public Image getDiamond() {
		return diamond;
	}
	public Image getSand() {
		return sand;
	}
	public Image getMonster() {
		return monster;
	}
	public Image getRedDiamond() {
		return redDiamond;
	}
	
	
	public Image getCoal_ore() {
		return coal_ore;
	}
	public Image getIron_ore() {
		return iron_ore;
	}
	public Image getGold_ore() {
		return gold_ore;
	}
	public Image getDiamond_ore() {
		return diamond_ore;
	}
	public Image getLapis_ore() {
		return lapis_ore;
	}
	public Image getEmerald_ore() {
		return emerald_ore;
	}
	
	/*
	 * @return image
	The getter returns us the image of the right perso
	 */
	public Image getRedstone_ore() {
		return redstone_ore;
	}
	
	/*
	 * @return image
	The getter returns us the image of the diamondT
	 */
	public Image getDiamondT() {
		return diamondT;
	}
	/*
	 * @return image
	The getter returns us the image of the red DiamondT
	 */
	public Image getRedDiamondT() {
		return redDiamondT;
		/*
		 * @return image
		The getter returns us the image of Heart
		 */
	}
	public Image getHeart() {
		return heart;
	}
	/*
	 * @return image
	The getter returns us the image of the HeartB
	 */
	public Image getHeartB() {
		return heartB;
	}
	/*
	 * @return image
	The getter returns us the image of the  HeartL
	 */
	public Image getHeartL() {
		return heartL;
	}
	/*
	 * @return image
	The getter returns us the image of Nyancat
	 */
	public Image getNyancat() {
		return nyancat;
	}
	
	/*
	 * @return image
	The getter returns us the image of the face perso
	 */
	
	public Image getPerso_face() {
		return perso_face;
	}
	/*
	 * @return image
	The getter returns us the image of the back perso
	 */
	public Image getPerso_back() {
		return perso_back;
	}
	/*
	 * @return image
	The getter returns us the image of the left perso
	 */
	public Image getPerso_left() {
		return perso_left;
	}
	/*
	 * @return image
	The getter returns us the image of the right perso
	 */
	public Image getPerso_right() {
		return perso_right;
	}
	/*
	 * @return blood
	 * 
The getter returns us the image of the blood
	 */
	public Image getBlood() {
		return blood;
	}
	
	/*
	 * @param x, y 
	 * the x
	 * the y 
	 * @return sprite. 
	 */
	public synchronized String getMap(int x, int y) {
		String sprite = map2d[y][x];
		return sprite;
	}
	/*
	 * @param x, y , sprite
	 * 	the x is the absciss
	 * 	the y is the ordered 
	 * 	The sprite is the corresponding image
	 */
	public void setMap(int x, int y, String sprite) {
		map2d[y][x] = sprite;
	}
	
	public static String openFile(final int id) throws SQLException {
		
        final CallableStatement callStatement = prepareCall(sqlshowMaps);
		
        callStatement.setInt(1, id);
		
        String mapString = "";
        if (callStatement.execute()) {
            final ResultSet result = callStatement.getResultSet();
            
            if (result.first()) {
                mapString = result.getString("map");
            }
            result.close();
        }
        return mapString;
    }
	/*
	 * 
We fill in our simple table by taking the result of the query.
	 */
	public void readFile() {
		for(int y = 0; y<21; y++) {
			map1d[y] = mapString.substring(y*40, (y+1)*40);
		}
	}
	/*
	 * 
It goes through the simple table to fill a matrix so that its use is simpler.
	 */
	
	private void completeMap() {
		for(int x = 0; x < 40; x++) {
			for(int y = 0; y<21; y++) {
				map2d[y][x] = map1d[y].substring(x, x+1);
			}
		}
	}


	/*
	 * 
This is a level getter.
	 */
	public int getLevel() {
		return level;
	}
/*
 * @param level 
 * 	the level
 * 
We initialize our level with the level that is going to be in parameter		
 */
	private void setLevel(int level) {
		this.level = level;
	}
}

