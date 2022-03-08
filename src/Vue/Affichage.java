package Vue;
import Controleur.Controleur;

import Modele.Fractales;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;


public class Affichage  extends JFrame  implements  ActionListener {

	private static final long serialVersionUID = 1L;
	private Controleur controleur;
	private final JPanel accueil;
	private JPanel selectionFractales;
	private JPanel makeFractale;
	private JPanel afficheFractale;
	private Fractales fractales;
	private JButton fractale;
	private JTextField complexeA, complexeB, nbIter, Xmin, Xmax, Ymin, Ymax;


	/***
	 * Constructeur 
	 * @param control
	 */
	public Affichage(Controleur control) {
		controleur = control;

		this.setResizable(false);
		setTitle("Projet CPOO");
		getContentPane().setBackground(Color.WHITE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 700);
		setLocationRelativeTo(null); 

		//Configuration de l'accueil
		accueil= new JPanelWithBackground("images/Fractales.png");
		accueil.setBackground(Color.BLUE);;
		getContentPane().add(accueil);
		accueil.setLayout(null);

		JButton commencer = new JButton("Start");
		commencer.setBackground(new Color(100, 100, 100));
		commencer.setBounds(250, 500, 300, 61);
		accueil.add(commencer);

		JLabel lblNewLabel = new JLabel("Fractal Generator");
		lblNewLabel.setFont(new Font(Font.SERIF, Font.BOLD, 50));
		lblNewLabel.setForeground(new Color(0, 0,0));
		lblNewLabel.setBounds(190, 12, 500, 145);
		accueil.add(lblNewLabel);

		commencer.addActionListener (( click ) -> { controleur.choixFractales(); });


	}

	/* *************************************************************************************
	 * 	Getters et setters pour les attributs d'une factale 							   *
	 *																					   *
	 ***************************************************************************************/
	public Fractales getFractales() {
		return fractales;
	}


	public void setFractales(Fractales frac) {
		this.fractales = frac;
	}


	public double getComplexeA() {
		return Double.parseDouble(complexeA.getText());
	}


	public double getComplexeB() {
		return Double.parseDouble(complexeB.getText());
	}


	public int getNbIter() {
		return Integer.parseInt(nbIter.getText());
	}


	public double getXmin() {
		return Double.parseDouble(Xmin.getText());
	}


	public double getXmax() {
		return Double.parseDouble(Xmax.getText());
	}


	public double getYmin() {
		return Double.parseDouble(Ymin.getText());
	}


	public double getYmax() {
		return Double.parseDouble(Ymax.getText());
	}


	
	
	
	/**************************************************************
	 * Permet la mise a jour de la fenetre avec un nouveau panel  *
	 * @param p le panel a afficher								  *
	 **************************************************************/
	public void miseAjour(JPanel p) {
		getContentPane().removeAll();
		getContentPane().setLayout(null);
		setContentPane(p);
		revalidate();
		repaint();

	}
	
	/******************************************************************************************
	 *  Cree le panel pour la selection de l'ensemble de fractales							  *
	 * @return le panel selectionFractales													  *
	 ******************************************************************************************/
	public JPanel selectionFractales() {

		selectionFractales = new JPanel();
		selectionFractales.setBackground(new Color(100,0,77));
		selectionFractales.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.gridy = 0;
		c.gridx = 1;
		c.insets = new Insets(0, 0, 100, 0);

		JLabel intro = new JLabel("Choose your Fractal");
		intro.setForeground(Color.LIGHT_GRAY);
		intro.setFont(new Font(Font.SERIF, Font.BOLD, 40));
		selectionFractales.add(intro,c);

		c.insets = new Insets(0, 0, 50, 350);
		c.gridy = 2;
		ImageIcon juliaIcone = new ImageIcon(Affichage.class.getResource("/images/MyJuliaChoix.png"));
		JLabel juliaImg = new JLabel(juliaIcone);
		selectionFractales.add(juliaImg, c);

		c.insets = new Insets(0, 350, 50, 0);
		ImageIcon mandelIcone = new ImageIcon(Affichage.class.getResource("/images/MyMandelChoix.png"));
		JLabel mandelImg = new JLabel(mandelIcone);
		selectionFractales.add(mandelImg, c);


		c.insets = new Insets(0, 0, 50, 350);
		c.gridy = 3;
		c.gridx = 1;
		JButton julia = new JButton("Julia");
		julia.setPreferredSize(new Dimension(250,80));
		julia.setForeground(new Color(100,0,77));
		julia.setBackground(Color.LIGHT_GRAY);
		selectionFractales.add(julia, c);

		julia.addActionListener(event -> { 
			controleur.getMakeJuliaFirst();
			complexeA.setVisible(true);
			complexeB.setVisible(true);
		});

		c.insets = new Insets(0, 350, 50, 0);
		c.gridy = 3;
		c.gridx = 1;
		JButton mandel = new JButton("Mandelbrot");
		mandel.setBackground(Color.LIGHT_GRAY);
		mandel.setForeground(new Color(100,0,77));
		mandel.setPreferredSize(new Dimension(250,80));
		selectionFractales.add(mandel, c);

		mandel.addActionListener(event -> {
			controleur.getMakeMandelbrotFirst();
			//on  cache les textFields qui permettent la selection d'un nombre complexe
			complexeA.setVisible(false);
			complexeB.setVisible(false);
		});	


		return selectionFractales;
	}

	/**************************************************************************************************************
	 * Construit le panel qui permet de voir la fractale et de choisir ses parametres. 							  *
	 * Ce panel est compose de deux sous panel, un qui affiche la fractale et un qui contient les parametres	  *
	 * @param name le nom de la fractale 																		  * 
	 * @param isColor permet de savoir la couleur de la fractale  qu'on va dessiner										  *	
	 * @return le panel makeFractale																			  *
	 **************************************************************************************************************/
	public JPanel makeFractale(String name, boolean isColor) {

		makeFractale = new JPanel();

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{600, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0};
		makeFractale.setLayout(gridBagLayout);


		afficheFractale = updateAfficheFrac(isColor) ;
		GridBagConstraints gbc_afficheFractale = new GridBagConstraints();
		gbc_afficheFractale.insets = new Insets(0, 0, 0, 0);
		gbc_afficheFractale.anchor = GridBagConstraints.WEST;
		gbc_afficheFractale.fill = GridBagConstraints.BOTH;
		gbc_afficheFractale.gridx = 0;
		gbc_afficheFractale.gridy = 0;
		makeFractale.add(afficheFractale, gbc_afficheFractale);


		JPanel parametresFractale = new JPanel();
		parametresFractale.setBackground(Color.WHITE);
		parametresFractale.setLayout(null);
		GridBagConstraints gbc_parametresFractale = new GridBagConstraints();
		gbc_parametresFractale.fill = GridBagConstraints.BOTH;
		gbc_parametresFractale.gridx = 1;
		gbc_parametresFractale.gridy = 0;
		makeFractale.add(parametresFractale,gbc_parametresFractale);


		/********************************************************
		 *Creation et personalisation des boutons et textFields *
		 ********************************************************/

		if(complexeA == null) {
			complexeA = new JTextField("Rel c", 10);
		}else {
			complexeA.setText (complexeA.getText());
		}

		complexeA.setBounds(0, 60, 85, 20);
		parametresFractale.add(complexeA);


		if(complexeB == null) {
			complexeB = new JTextField("Im c", 10);
		}else {
			complexeB.setText (complexeB.getText());
		}

		complexeB.setBounds(90, 60, 85, 20);
		parametresFractale.add(complexeB);


		JLabel msj = new JLabel("Choose your values") ;
		msj.setFont(new Font("Serif", Font.BOLD, 14));
		msj.setBounds(10, 30, 160, 20);
		parametresFractale.add(msj);


		if (Xmin ==null) {
			Xmin = new JTextField("Xmin",10);
		} else {
			Xmin.setText(Xmin.getText());
		}

		Xmin.setBounds(0, 90, 85, 20);
		parametresFractale.add(Xmin);


		if (Xmax ==null) {
			Xmax = new JTextField("Xmax",10);
		} else {
			Xmax.setText(Xmax.getText());
		}

		Xmax.setBounds(90, 90, 85, 20);
		parametresFractale.add(Xmax);


		if (Ymin ==null) {
			Ymin = new JTextField("Ymin",10);
		} else {
			Ymin.setText(Ymin.getText());
		}

		Ymin.setBounds(0, 120, 85, 20);
		parametresFractale.add(Ymin);


		if (Ymax ==null) {
			Ymax = new JTextField("Ymax",10);
		} else {
			Ymax.setText(Ymax.getText());
		}

		Ymax.setBounds(90, 120, 85, 20);
		parametresFractale.add(Ymax);


		if (nbIter ==null) {
			nbIter = new JTextField("iteration",10);
		} else {
			nbIter.setText(nbIter.getText());
		}

		nbIter.setBounds(45, 150, 85, 20);
		parametresFractale.add(nbIter);


		fractale = new JButton("New fractal");
		fractale.setBounds(0,180, 170, 23);
		fractale.setBackground(new Color(100, 100, 200));
		parametresFractale.add(fractale);

		JButton menu = new JButton("Menu");
		menu.setBounds(0, 600, 170, 23);
		menu.setBackground(new Color(200,100,100));
		parametresFractale.add(menu);

		JButton sauvegarder = new JButton("Save");
		sauvegarder.setBounds(0, 550, 170, 23);
		sauvegarder.setBackground(new Color(100, 200, 100));
		parametresFractale.add(sauvegarder);


		JButton zoomin = new JButton("Zoom in");
		zoomin.setBounds(0, 450, 170, 23);
		zoomin.setBackground(new Color(100, 100, 200));
		parametresFractale.add(zoomin);


		JButton zoomout = new JButton("Zoom out");
		zoomout.setBounds(0, 475, 170, 23);
		zoomout.setBackground(new Color(100, 100, 200));
		parametresFractale.add(zoomout);

		JButton couleur = new JButton("Color");
		couleur.setBounds(0, 250, 80, 23);
		couleur.setBackground(new Color(100, 100, 200));
		parametresFractale.add(couleur);

		JButton noirBlanc = new JButton("B & W");
		noirBlanc.setBounds(90, 250, 80, 23);
		noirBlanc.setBackground(new Color(100, 100, 200));
		parametresFractale.add(noirBlanc);

		JButton gauche = new JButton();
		gauche.setIcon(new ImageIcon(Affichage.class.getResource("/images/fleche.png")));
		gauche.setBounds(25, 350, 41, 38);
		parametresFractale.add(gauche);

		JButton droite = new JButton();
		droite.setIcon(new ImageIcon(Affichage.class.getResource("/images/flechedroite.png")));
		droite.setBounds(113, 350, 41, 38);
		parametresFractale.add(droite);

		JButton haut = new JButton();
		haut.setIcon(new ImageIcon(Affichage.class.getResource("/images/flechehaut.png")));
		haut.setBounds(68, 300, 41, 38);
		parametresFractale.add(haut);

		JButton bas = new JButton();
		bas.setIcon(new ImageIcon(Affichage.class.getResource("/images/flechebas.png")));
		bas.setBounds(68, 400, 41, 38);
		parametresFractale.add(bas);

		/****
		 *Gestion de l'interaction entre les boutons et l'interface graphique 
		 ****/

		couleur.addActionListener (( click ) -> { controleur.changerCouleur(name,true); });
		noirBlanc.addActionListener(( click ) -> { controleur.changerCouleur(name,false); });
		makeFractale.setCursor (new Cursor (Cursor.HAND_CURSOR));
		menu.addActionListener (( click ) -> { controleur.choixFractales(); });
		sauvegarder.addActionListener (( click ) -> { controleur.sauvegarderImage(name,afficheFractale);
		JOptionPane.showMessageDialog(this,"Votre image se trouve dans le dossier pictures");
		});

		fractale.addActionListener((click) -> {

			// verification du bon type + que tous les champs sont remplis sinon un message d'erreur apparait
			if (name=="Julia") {
				if( !(DoubleValide(complexeA.getText())) ||  !(DoubleValide(complexeB.getText())) || 	
						!(IntegerValide(nbIter.getText())) || !(DoubleValide(Xmin.getText())) || 			
						!(DoubleValide(Xmax.getText())) || !(DoubleValide(Ymax.getText()))||
						!(DoubleValide(Ymin.getText()) )) {

					JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs avec le bon type\nLes types acceptes : \n\t Entier ou Double");
				}else { 
					controleur.getMakeJulia();
				}

			}else {
				if(!(IntegerValide(nbIter.getText())) || !(DoubleValide(Xmin.getText())) || 			
						!(DoubleValide(Xmax.getText())) || !(DoubleValide(Ymax.getText()))||
						!(DoubleValide(Ymin.getText()) )) {

					JOptionPane.showMessageDialog(this,"Veuillez remplir tous les champs avec le bon type\nLes types acceptes : \n\t Entier ou Double");
				}else {
					controleur.getMakeMandelbrot();
				}
			}
		});

		droite.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor, fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER(),fractales.getXmin()-0.5,fractales.getXmax()-0.5,
						fractales.getYmin(),fractales.getYmax()); 

			} else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER(),
						fractales.getXmin()-0.5,fractales.getXmax()-0.5,fractales.getYmin(),fractales.getYmax()); 
			}
		});

		gauche.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor,fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER(),fractales.getXmin()+0.5,fractales.getXmax()+0.5,
						fractales.getYmin(),fractales.getYmax()); 
			} else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER(),
						fractales.getXmin()+0.5,fractales.getXmax()+0.5,fractales.getYmin(),fractales.getYmax()); 
			}

		});

		bas.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor,fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER(),fractales.getXmin(),fractales.getXmax(),
						fractales.getYmin()-0.5,fractales.getYmax()-0.5);

			}else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER(),
						fractales.getXmin(),fractales.getXmax(),fractales.getYmin()-0.5,fractales.getYmax()-0.5);
			}

		});

		haut.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor,fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER(),fractales.getXmin(),fractales.getXmax(),
						fractales.getYmin()+0.5,fractales.getYmax()+0.5); 

			}else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER(),
						fractales.getXmin(),fractales.getXmax(),fractales.getYmin()+0.5,fractales.getYmax()+0.5); 	
			}

		});


		zoomin.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor,fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER()+100,fractales.getXmin()+0.25,fractales.getXmax()-0.25,
						fractales.getYmin()+0.25,fractales.getYmax()-0.25); 

			}else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER()+100,
						fractales.getXmin()+0.25,fractales.getXmax()-0.25,fractales.getYmin()+0.25,fractales.getYmax()-0.25); 	
			}

		});
		zoomout.addActionListener((click) -> {
			if (name=="Julia") {
				controleur.setMakeJulia(isColor,fractales.getC().getReel(), fractales.getC().getImage(),
						fractales.getDEF_MAX_ITER(),fractales.getXmin()-0.5,fractales.getXmax()+0.5,
						fractales.getYmin()-0.5,fractales.getYmax()+0.5); 

			}else {
				controleur.setMakeMandelbrot(isColor,fractales.getDEF_MAX_ITER(),
						fractales.getXmin()-0.5,fractales.getXmax()+0.5,fractales.getYmin()-0.5,fractales.getYmax()+0.5); 	
			}

		});

		return makeFractale;
	}


	/***
	 * Permet la mise a jour du sous panel qui contient la fractale
	 * @param isColor permet de savoir la couleur de la fractale 
	 * @return le panel afficheFractale
	 */
	public JPanel updateAfficheFrac(boolean isColor) {
		if(afficheFractale != null) {
			afficheFractale.removeAll();
		}

		afficheFractale = new JPanelFractale(isColor);

		return afficheFractale;
	}


	@Override
	public void actionPerformed(ActionEvent e) {}


	/***************************************************************************************************************************
	 * 																														   *					  *
	 *													CLASSES INTERNES													   * 								  *
	 ***************************************************************************************************************************/

	@SuppressWarnings("serial")
	private  class JPanelWithBackground extends JPanel  {

		private BufferedImage backgroundImage;
		ImageIcon imic;


		public JPanelWithBackground(String fileName)  {

			imic = new ImageIcon(fileName);
			backgroundImage= new BufferedImage(imic.getIconWidth(),imic.getIconHeight(),BufferedImage.TYPE_INT_ARGB);
			Graphics2D gg = backgroundImage.createGraphics();
			gg.drawImage(imic.getImage(),0,0,imic.getImageObserver());


		}

		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0 , 0 , this.getWidth() , this.getHeight(), this);
		}
	}



	@SuppressWarnings("serial")
	private  class JPanelFractale extends JPanel {
		boolean isColored;
		public JPanelFractale(boolean b)  {isColored = b; }

		private void  drawFractale(Graphics g) {
			BufferedImage img =new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
			int col;

			for(int i = 0; i < fractales.getPixels().length ; i++) {
				for ( int j = 0; j< fractales.getPixels()[i].length; j++) {
					col = fractales.coloration(isColored,fractales.getPixels()[i][j]).getRGB();
					img.setRGB(i,j,col);
				}
			}
			g.drawImage(img, 0, 0, null);
		}

		@Override
		public void paintComponent(Graphics gg) {
			super.paintComponent(gg);
			Graphics2D g = (Graphics2D) gg;
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			drawFractale(g);
		}

	}


	/******************************************************************************************
	 *    Fonctions auxiliaires qui  verifient qu'on rentre bien des entiers et des doubles	  *
	 * @param choix																			  *
	 * @return																				  *
	 ******************************************************************************************/
	public boolean IntegerValide(String choix) {
		return choix.matches("-?\\d+") ;

	}

	public boolean DoubleValide(String choix) {
		try {
			Double.parseDouble(choix);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
