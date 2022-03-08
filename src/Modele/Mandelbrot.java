package Modele;

import java.util.stream.IntStream;

public class Mandelbrot  extends Fractales{


	/***
	 * Constructeur de la fractale de mandelbrot
	 * 
	 * @param max_iter
	 * @param hauteur
	 * @param largeur
	 * @param xmin
	 * @param xmax
	 * @param ymin
	 * @param ymax
	 */
	
	public Mandelbrot (int max_iter,int hauteur,
			int largeur,double xmin,double xmax,double ymin,double ymax){
		
   	super( max_iter,hauteur, largeur, xmin, xmax,ymin, ymax);
                  
    }

	

	/***
     * Fonction qui calcule l'indice de divergence pour chaque nombre complexe du plan definit
     *  et qui les stockes dans un tableau 
     */
	@Override
	public void divergence() {
		 double largeurH =super.genratePas(super.getNbPointX(), super.getXmin(), 0, super.getXmax(), 0);
		 double longeurH =super.genratePas(super.getNbPointY(), 0, super.getYmin(), 0, super.getYmax());; 
   
		 IntStream.range(0, super.getNbPointX()).parallel().forEach(i -> {
    		 IntStream.range(0, super.getNbPointY()).parallel().forEach(j -> {
    		
    			 Complexe z = new Complexe(0,0);
    			 Complexe c = new Complexe(super.getXmin()+(i*largeurH),super.getYmin()+(j*longeurH));
    			 int ind = super.divergenceIndex(z,c);
    			 
    			 
    			 if(ind<super.getDEF_MAX_ITER()) {
                     (super.getPixels())[i][j]=ind;
    			 } else {
                     (super.getPixels())[i][j]=0;    
    			 }
    			
    			 
    		 });
    	 });
     }
	
	
	/***
	 * fonction qui cree l'image de la fractale 
	 * @param color
	 */
     public void makeImg(boolean color ) {
    	
    	super.makeImg(color,"Mandelbrot");
     
     }

	
	
}
