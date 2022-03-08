package Modele;
public class Complexe {

	/**
	 * Cette classe definit un objet Complexe et les operations qu'on peut effectuer sur les nombres complexes
	 */
	private double reel;
	private double image;

	public Complexe(double r, double i){
		this.reel = r;
		this.image = i;
	}


	public double getReel() {
		return reel;
	}


	public double getImage() {
		return image;
	}


	public Complexe somme(Complexe a){
		return new Complexe(reel+a.reel,image+a.image);
	}


	public  Complexe multiplication(Complexe b) {

		double r = reel*b.reel-image*b.image;
		double i = reel*b.image+image*b.reel;
		return new Complexe(r,i);

	}


	public double module(){
		return Math.sqrt(reel*reel+image*image);
	}

	public String toString(){
		if(image>0)
			return reel+"+i "+image;
		if(image<0)
			return reel+"-i "+(-image);
		return reel+"";
	}


}
