package Modele;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;


import javax.imageio.ImageIO;

public abstract class Fractales  {
	

	
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_MAGENTA = "\u001B[35m";
	public static final String ANSI_RESET = "\u001B[0m";
		
	 
	 private  double h ;
	 private final double xmax,xmin,ymin,ymax;
	 private int[][] pixels;
	 private final double RADIUS=2.0;
	 private Complexe C;
	 private final int nbPointX;
	 private final int nbPointY;
	 private final int iteration;
     private double rel,im;
  
	/**
	 * constructeur par defaut d'une fractale Julia 
	* @param rel partie reele
	 * @param im partie imaginaire
	 * @param max_iter nombre maximum d'iteration
	 * @param hauteur hauteur de l'image generee
	 * @param largeur largeur de l'image generee
	 * @param xmin x minimum du plan complexe
	 * @param xmax x maximum du plan complexe
	 * @param ymin y minimun du plan complexe
	 * @param ymax y maximum du plan complexe
	 */
	 
	 public Fractales(double rel,double im ,int max_iter,
			 
			 int hauteur, int largeur,double xmin,double xmax,
			 
			 double ymin,double ymax){
		 
		 
    	 this.rel=rel;
    	 this.im=im;
    	 this.iteration=max_iter;
         this.xmin=xmin;
         this.xmax=xmax;
         this.ymin=ymin;
         this.ymax=ymax;
         this.nbPointX = largeur;
         this.nbPointY = hauteur;
         this.pixels = new int[nbPointX][nbPointY];
         C = new Complexe( this.rel,this.im);
                    
     }
	
	 /***
	  *  fractale pour mandelbrot qui ne demande pas de nombre complexe 
	  * @param max_iter
	  * @param hauteur
	  * @param largeur
	  * @param xmin
	  * @param xmax
	  * @param ymin
	  * @param ymax
	  */
	 public Fractales(int max_iter,
			 
			 int hauteur, int largeur,double xmin,double xmax,
			 
			 double ymin,double ymax){
		 	 
    	
    	 this.iteration=max_iter;
         this.xmin=xmin;
         this.xmax=xmax;
         this.ymin=ymin;
         this.ymax=ymax;
         this.nbPointX = largeur;
         this.nbPointY = hauteur;
         this.pixels = new int[nbPointX][nbPointY];
                            
     }
	 
	 
	 /***
	  * Fonction absraite pour le calcul de l'indice de divergence
	  *  des points complexes du plan 
	  */
	 public abstract void divergence() ;
	
	 
	 
	  /* Fonction qui calcule la distance entre deux points
	  * @param x1
	  * @param y1
	  * @param x2
	  * @param y2
	  * @return
	  */
	 public double Distance(double x1, double y1, double x2, double y2) {
	        return Math.sqrt((y2 - y1)*(y2 - y1) +(x2 - x1)*(x2 - x1)); 
	 }
	 
	 
	 /***
	  * Fonction qui calcule le pas suivant les parametres entres : largeur ou hauteur
	  * @param l
	  * @param x1
	  * @param y1
	  * @param x2
	  * @param y2
	  * @return
	  */

	 public double  genratePas(int l, double x1 , double y1, double x2, double y2) {
		 return (Distance(x1,y1,x2,y2)/l);
	 }

	 
	 /* *************************************************************
	  * Les getters pour les attributs d'une fractale				*
	  ***************************************************************/
	
	 public  int getDEF_MAX_ITER() {
		 return iteration;
	 }

	 public  double getRADIUS() {
		 return RADIUS;
	 }

	 public double getXmax() {
		 return xmax;
	 }


	 public double getXmin() {
		 return xmin;
	 }


	 public double getYmin() {
		 return ymin;
	 }


	 public double getYmax() {
		 return ymax;
	 }

	 public int[][] getPixels() {
		 return pixels;
	 }

	 public double getH() {
		 return h;
	 }


	 public Complexe getC() {
		 return C;
	 }


	 public int getNbPointX() {
		 return nbPointX;
	 }


	 public int getNbPointY() {
		 return nbPointY;
	 }


	/***
	 * Fonction qui calcule l'indice de divergence et le renvoie 
	 * @param z
	 * @param c
	 * @return
	 */

	 protected int divergenceIndex(Complexe z, Complexe c) {
		 int ite = 0;
		 Complexe zn = z;
		 while (ite < iteration && zn.module() <= RADIUS) {
			 zn = zn.multiplication(zn).somme(c); 
			 ite++;
		 }
		 return ite;
	 }
	 
	
	/**
	 * Fonction pour generer les couleurs 
	 * @param pixel l'indice de divergence
	 * @return la couleur correspondante
	 */
	public static Color getColor (int pixel) {
   	 // generer des couleurs 
  
		 int r=0 ;int g=0;int b=0;
		 if (pixel < 5) {
			 r =199;
			 g =  0 ;
		     b =57;
		     return new Color (r,g,b);}
		 if (pixel < 10) {
			 r =255;
			 g = 87 ;
		     b =51;
		     return new Color (r,g,b);}
		 if (pixel < 15) {
			 r =255;
			 g = 195 ;
		     b =0;
		     return new Color (r,g,b);}
		  if (pixel < 20) {
			 r =225;
			 g =51;
		     b =20;
		     return new Color (r,g,b);}
		     if (pixel  < 30) {
				 r =217;
				 g =226;
	 		     b =28;
	 		     return new Color (r,g,b);
		 }  if  (pixel <40) {
		    	 r =55;
				 g =248;
	 		     b =90;
	 		   return new Color (r,g,b);
		 
		
    } else if  (pixel <100) {
	    	 r =255;
			 g =181;
		     b =51;
		     return new Color (r,g,b);
    }  else { 
		    	 r=55;
		    	 g=96;
		    	 b=248;
		    	 return new Color (r,g,b);
		     }

}
		 
	 /***
	  * Fonction qui renvoie un type de coloration  
	  * @param c determine si le pixel sera en noir et blanc ou en couleur
	  * @param color l'indice de divergence
	  * @return un type de couleur
	  */
	 public Color coloration(boolean c, int color){
		
		 if(c) {
			return getColor(color);
			 
		 }
		 return (color == 0? new Color(255,255,255):new Color (0,0,0));
	 }
	 
	 
	 
	 
	 /***
	  * Fonction qui genere l'image d'une fractale en couleur ou en noir et blanc
	  * @param color
	  * @param filename 
	  */
	 public void makeImg(boolean color ,String filename) {
	    
		 divergence();
		 BufferedImage img =new BufferedImage(nbPointX, nbPointY, BufferedImage.TYPE_INT_RGB);
		 File f = new File("pictures/My"+filename+".png");
		 if (color==true){

			 for(int i = 0; i<getNbPointX() ; i++) {
				 for( int j = 0; j<getNbPointY(); j++){
					 int col;
					 col = (getColor((getPixels())[i][j])).getRGB()    ;
					 img.setRGB(i,j,col);

				 }
			 }
		 }else {
			 for(int i = 0; i<getNbPointX() ; i++) {
				 for( int j = 0; j<getNbPointY(); j++){


					 if (getPixels()[i][j]==0) {
						 img.setRGB(i,j,(new Color (255,255,255).getRGB()));

					 }else {
						 img.setRGB(i,j,(new Color (0,0,0).getRGB()));

					 }	

				 }
			 }
		 }
		 try {
			 ImageIO.write(img,"PNG",f);
		 }catch( Exception IllegalArgumentException ) {
			 System.out.println("Error impossible de charger l'image  !");
		 }
		 System.out.println(ANSI_MAGENTA+"\tVous pouvez maintenant allez visionner votre image dans le dossier pictures"+ANSI_RESET); 

	 }
}
