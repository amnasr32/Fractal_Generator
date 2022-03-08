package Controleur;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Modele.Julia;
import Modele.Mandelbrot;
import Vue.Affichage;
public class Controleur {

	private  Affichage affichage;

	public Controleur(){}

	/***
	 * Affichage de la fenetre graphique
	 */
	public void lancement (){
		Controleur controleur = this;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					affichage = new Affichage(controleur);
					affichage.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/***
	 * Affiche le panel pour le choix de la fractale
	 */
	public void choixFractales(){

		affichage.miseAjour(affichage.selectionFractales());
	}

	/***
	 * Affiche une fractale de Julia par defaut au lancement 
	 */
	public void getMakeJuliaFirst() {
		affichage.setFractales(new Julia(0.3,0.5,500,661,600,-1,1,-1,1));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Julia",true));
	}

	/***
	 * Mise a jour de la fractale de Julia en fonction des nouveaux parametres
	 */
	public void getMakeJulia() {
		affichage.setFractales(new Julia(affichage.getComplexeA(),affichage.getComplexeB(),affichage.getNbIter()
				,661,600,affichage.getXmin(),affichage.getXmax(),affichage.getYmin(),affichage.getYmax()));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Julia",true));

	}

	/***
	 * Mise a jour de la fractale de Julia apres utilisation des boutons (Zoom in/out, fleches )
	 * @param isColor
	 * @param reel
	 * @param img
	 * @param ite
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 */
	public void setMakeJulia(boolean isColor,double reel, double img,int ite, double xmin, double xmax, double ymin, double ymax) {
		affichage.setFractales(new Julia(reel,img,ite,661,600,xmin,xmax,ymin,ymax));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Julia",isColor));
	}

	/***
	 * Affiche une fractale de Mandelbrot par defaut au lancement 
	 */
	public void getMakeMandelbrotFirst() {
		affichage.setFractales(new Mandelbrot(200,661,600,-2.1,1,-2,2));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Mandelbrot",true));

	}

	/***
	 * Mise a jour de la fractale de Mandelbrot fonction des nouveaux parametres
	 */
	public void getMakeMandelbrot() {
		affichage.setFractales(new Mandelbrot(affichage.getNbIter()
				,661,600,affichage.getXmin(),affichage.getXmax(),affichage.getYmin(),affichage.getYmax()));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Mandelbrot",true));

	}

	/***
	 * Mise a jour de la fractale de Mandelbrot apres utilisation des boutons (Zoom in/out, fleches )
	 * @param isColor
	 * @param ite
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 */
	public void setMakeMandelbrot(boolean isColor,int ite ,double xmin, double xmax, double ymin, double ymax) {
		affichage.setFractales(new Mandelbrot(ite,661,600,xmin,xmax,ymin,ymax));
		affichage.getFractales().divergence();
		affichage.miseAjour(affichage.makeFractale("Mandelbrot",isColor));
	}


	/***
	 * Permet de sauvegarder la courante sous forme d'image png
	 * @param name le nom de la fractale courante
	 * @param pan panel contenant le dessin de la fractale
	 */
	public void sauvegarderImage(String name,JPanel pan) {

		BufferedImage image = new BufferedImage(pan.getWidth(), pan.getHeight(), BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		pan.paint(g);


		try {
			ImageIO.write(image, "png", new File("pictures/my"+name+".png"));
		} catch (IOException ex) {
			System.out.println("error!");
		}

	}

	/**
	 * Permet de changer la couleur de la fractale courante
	 * @param name le nom de la fractale courante
	 * @param b indique de quelle couleur il faut  dessiner la fractale
	 */
	public void changerCouleur( String name,boolean b) {

		affichage.miseAjour(affichage.makeFractale(name,b));

	}
}
