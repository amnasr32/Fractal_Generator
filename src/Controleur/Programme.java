package Controleur;
import Modele.*;
import java.util.Scanner;


public class Programme {
	
	
	/*
	 *  Variables pour ecrire en couleur dans le terminal
	 */
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_MAGENTA = "\u001B[35m";
	public static final String ANSI_RESET = "\u001B[0m";
	
	
	/** ************************************************************************************************************
	 *                                   Affichage et lancement de l'application								   *
	 ***************************************************************************************************************/
	public Programme() {
		
		  System.out.println("");
		  System.out.println("");
		  System.out.println("");
		  System.out.println("");
		  System.out.println("						Bienvenue dans Fractal Generator				");
		  System.out.println("");
		  System.out.println("");
		  System.out.println("");
		  System.out.println("");
		  lancement();
		  System.out.println("");
		  System.out.println("");
		  System.out.println("");
		  System.out.println(""); 
	}
	
	
	
	private void lancement(){
        System.out.println("	Comment voulez vous utiliser le Programme ?");
        System.out.println("");
		System.out.println(""); 
        
        afficheDebut();
        System.out.println("");
		System.out.println("");
		System.out.println("");
		System.out.println(""); 
        String reponse = demandeReponse();
        while(! reponseValide(reponse)){
            System.out.println(ANSI_RED+"Merci de rentrer une reponse correcte parmis les choix suivants :"+ANSI_RESET);
            afficheDebut();
            reponse = demandeReponse();
        }
        lanceDebut(reponse);
    }
	

 /***************************************************************************************************************************
  * 						Fonction affichant les modes possibles du programme (console ou graphique)					    *
  ***************************************************************************************************************************/
	
    private void afficheDebut(){
        System.out.println("\t1 : En Mode Graphique");
        System.out.println("\t2 : En Mode Console");
        System.out.println("\tQ : Quitter le programme");
    }
    
     
    /**********************************************************************
     * 	Fonction qui demande une reponse au utilisateur et la retourne	  *
     * @return la reponse de l'utilisateur								  *
     **********************************************************************/
	private String demandeReponse(){
        System.out.print("\nEntrez votre choix :  ");
        Scanner sc = new Scanner(System.in);
        String rep =  sc.nextLine();
        System.out.println();
        return rep;
    }

    
	/*************************************************************
	 * 	Teste si l'utilisateur a donne une reponse valide		 *
	 * @param choix, la reponse entre par l'utilisateur			 *
	 * @param debut												 *
	 * @return true si la reponse est correcte, false sinon		 *
	 *************************************************************/
	private boolean reponseValide(String choix ){
         if (choix.equals("Q")){
        	System.out.println(ANSI_MAGENTA+"Au revoir"+ANSI_RESET);
            System.exit(0);
           
        }
        return choix.equals("1") || choix.equals("2");
    }
	

     /********************************************************************************
      * Lance le mode console/graphique en fonction du choix de l'utilisateur		 *
      * @param reponse le choix de l'utilisateur									 *
      ********************************************************************************/
    private void lanceDebut(String reponse){
        if (reponse.equals("2")){
            lancementConsole();
        }else {
            lancementGraphique();
        }
    }
    

    /*********************************************************************
     * 	Lance le mode graphique											 *
     *********************************************************************/
   private void lancementGraphique(){ 
       Controleur controleur = new Controleur();
       controleur.lancement();
   }


    /***********************************************************************
     *  Lance le mode console											   *
     ***********************************************************************/
   private void lancementConsole(){
       System.out.println("\tQue voulez-vous faire ?");
       afficheChoix();
       String reponse = demandeReponse();
       while(! reponseValide(reponse)){
           System.out.println(ANSI_RED+"\tMerci de rentrer une reponse correcte parmis les choix suivants :"+ANSI_RESET);
           afficheChoix();
           reponse = demandeReponse();
       }
       lancementChoix(reponse);
   }

   /************************************************************************
    * 	Affiche les  choix possibles des fractales						   * 
    ************************************************************************/
   private void afficheChoix(){
	   System.out.println("");
	   System.out.println("");
       System.out.println("\t1 : Ensembles de Julia");
       System.out.println("\t2 : Ensembles Mandelbrot");
       System.out.println("\tQ : Quitter le programme");
       System.out.println("");
	   System.out.println("");
   }
   
   
  
   /***  ****************************************************************
    *  Fonction qui cree les fractales Julia ou Mandelbrot 		        *
    * @param s la fractale choisi										*
    *   *****************************************************************/
  private void FractalBuilder (String s) {
	   
	
	  switch (s) {
	  case "Julia" : 
		   System.out.println("\nVous avez choisi l'ensemble de Julia. Choisissez vos parametres.");
           System.out.println("");
	   	   System.out.println("");
	   	   affichageChoixCouleur();
	   	   String ch =afficher_reponses_fractal();
	   	   String []  param = demandeReponseFractal("Julia");
	   	   lancementChoix_Fractal(param,ch,"Julia");
   	   
		  break;
	  case "Mandelbrot": 
		   System.out.println("\nVous avez choisi l'ensemble de Mandelbrot. Choisissez vos parametres.");
           System.out.println("");
	   	   System.out.println("");
	   	   affichageChoixCouleur();
	   	   String ch1 =afficher_reponses_fractal();
	   	   String []  param1 = demandeReponseFractal("Mandelbrot");
	   	   lancementChoix_Fractal(param1,ch1,"Mandelbrot");
  	   
		  break;
	  }
  }
   

  /**********************************************************************************
   *  Pour lancer la fractale														*
   * @param choix le choix de l'utilisateur											*
   **********************************************************************************/
   private void lancementChoix(String choix){
      
       switch (choix) {
           case "1" : 
        	   FractalBuilder("Julia");
        	
      	    break;
      	
           case "2" : 
        	   FractalBuilder("Mandelbrot");
               break;
        	  
           case "Q" : System.exit(0);

       }
   }
   
   
  
   /*******************************************************************************************
    * 	Fonction pour demander une reponse et verifier si elle est valide					  *
    * @return la reponse valide de l'utilisateur											  *
     ******************************************************************************************/
   private String afficher_reponses_fractal(){
	  
       String reponse = demandeReponse();
       while(! reponseValide(reponse)){
           System.out.println(ANSI_RED+"\nMerci de rentrer une reponse correcte parmis les choix suivants :"+ANSI_RESET);
           affichageChoixCouleur();
           reponse = demandeReponse();
          
       }
       return reponse;
       
   }
   
   /***  **************************************************************************************************
    *  Cree la fractale a partir des parametres rentres par l'utilisateur								  *
    * @param choix les parametres de la fractale														  *
    * @param ch permet de savoir si la fractale sera en couleur ou en noir et blanc						  *
    * @param name le type de la fractale																  *
    *  ***************************************************************************************************/
    private void lancementChoix_Fractal(String  []choix,String ch,String name){
    	
       if (name == "Julia") {
	          Julia test  = new Julia(Double.parseDouble(choix[0]),Double.parseDouble(choix[1]),Integer.parseInt(choix[2]),
	        		  Integer.parseInt(choix[3]),Integer.parseInt(choix[4]),Double.parseDouble(choix[5]),Double.parseDouble(choix[6]),
	        		  Double.parseDouble(choix[7]),Double.parseDouble(choix[8]));

		   switch (ch) {
			   case "1" : 
				   test.makeImg(true);
				   break;
			   case "2" :
				   test.makeImg(false) ;
				   break;
		   }
       
       } 
       
       else {
    	   Mandelbrot test  = new Mandelbrot(Integer.parseInt(choix[2]),
	        		  Integer.parseInt(choix[3]),Integer.parseInt(choix[4]),Double.parseDouble(choix[5]),Double.parseDouble(choix[6]),Double.parseDouble(choix[7]),Double.parseDouble(choix[8]));

    	   switch (ch) {
			   case "1" : 
				   test.makeImg(true);
				   break;
			   case "2" :
				   test.makeImg(false) ;
				   break;
			}
       
       }
	   
	  
   }
   
  
    /********************************************************************************************
     *  							Fonction pour afficher le choix des couleurs 				*
     *   																						*
     ********************************************************************************************/
     void affichageChoixCouleur(){
           System.out.println("\n\t1: Afficher la fractale en couleurs");
           System.out.println("\t2: Afficher la fractale en noir et blanc");
           
   }
    	   
  
  /*********************************************************************************************************************************
   * Fonctions pour demander les parametres et verifier que ce sont des int / doubles et les stocker dans un tableau de string	   *
   * @param str le type de la fractale																							   *
   * @return le tableau contenant les parametres de la fractale																	   *
   *********************************************************************************************************************************/
 
   private String [] demandeReponseFractal(String str ){
	   String [] rep = new String [9];
	   Scanner sc = new Scanner(System.in);
	   switch (str) {
	   case "Julia": 
		   System.out.println("\n\tLa partie reelle du nombre complexe C :  ");
	       rep[0] =  sc.nextLine();
	       while(DoubleValide(rep[0])==false) {
	    	   System.out.println("");
	 		   System.out.println("");
	    	   System.out.println(ANSI_RED+"\n\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
	    	   System.out.println("");
	 		   System.out.println("");
	    	   System.out.println("\n\tLa partie reelle du nombre complexe C :  ");
	           rep[0] =  sc.nextLine();
	       }
	       
	       
	       System.out.println("\n\tLa partie imaginaire du nombre complexe C :  ");
	       rep[1] =  sc.nextLine();
	       while(DoubleValide(rep[1])==false) {
	    	   System.out.println("");
	 		   System.out.println("");
	 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
	    	   System.out.println("");
	 		   System.out.println("");
	    	   System.out.println("\n\tLa partie imaginaire du nombre complexe C :  ");
	           rep[1] =  sc.nextLine();
	       }
	       
		   break;
		   
	   case "Mandelbrot":
		   rep[0]="";
		   rep[1]="";
		   break; 
	   }
   
        
      
       System.out.println("\n\tL'indice du nombre d'iteration maximal :  ");
       rep[2] =  sc.nextLine();
       while(IntegerValide(rep[2])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tL'indice du nombre d'iteration maximal :  ");
           rep[2] =  sc.nextLine();
       }
       
       
       System.out.println("\n\tLa largeur de la  fractale :  ");
       rep[3] =  sc.nextLine();
       while(IntegerValide(rep[3])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa longueur de la fractale :  ");
           rep[3] =  sc.nextLine();
       }
       System.out.println("\n\tLa longueur de la fractale :  ");
       rep[4] =  sc.nextLine();
       while(IntegerValide(rep[4])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa longueur de la fractale :  ");
           rep[4] =  sc.nextLine();
       }
       
       
       
       System.out.println("\n\tLa valeur de Xmin :  ");
       rep[5]=  sc.nextLine();
       while(DoubleValide(rep[5])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		   System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa valeur de Xmin :  ");
           rep[5]=  sc.nextLine();
       }
       
       
       
       System.out.println("\n\tLa valeur de Xmax :  ");
       rep[6]=  sc.nextLine();
       while(DoubleValide(rep[6])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa valeur de Xmax :  ");
           rep[6]=  sc.nextLine();
       }
       
       
       System.out.println("\n\tLa valeur de Ymin :  ");
       rep[7]=  sc.nextLine();
       while(DoubleValide(rep[7])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa valeur de Ymin :  ");
           rep[7]=  sc.nextLine();
       }
      
       
       System.out.println("\n\tLa valeur de Ymax :  ");
       rep[8]=  sc.nextLine();
       while(DoubleValide(rep[8])==false) {
    	   System.out.println("");
 		   System.out.println("");
 		  System.out.println(ANSI_RED+"\tCe parametre n'est pas valide, veuillez reessayer"+ANSI_RESET);
    	   System.out.println("");
 		   System.out.println("");
    	   System.out.println("\n\tLa valeur de Ymax :  ");
           rep[8]=  sc.nextLine();
       }
       System.out.println();
           
       
       return rep;

   }

   /**************************************************************************************************
    * 	Verifie qu'on choisit bien un entier														 *
    * @param le choix de l'utilisateur																 *
    * @return true si le choix correspond a un entier, false sinon									 *
    **************************************************************************************************/
   private boolean IntegerValide(String choix) {
	   return choix.matches("-?\\d+") ; 
	   
   }
   
   /**************************************************************************************************
    * 	Verifie qu'on choisit bien un double														 *
    * @param le choix de l'utilisateur																 *
    * @return true si le choix correspond a un double, false sinon									 *
    *************************************************************************************************/
   private boolean DoubleValide(String choix) {
	   try {
           Double.parseDouble(choix);
           return true;
       }  catch (NumberFormatException e) {
           return false;
       }
   }
	
}
