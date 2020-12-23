import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.*;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class CM_frame extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private static final int FRAME_WIDTH = 1500;
	private static final int FRAME_HEIGHT = 1080;
	
	public String var_produs;		//the name of the selected product
	public String var_first_lot;	//the name of the FIRST lot where the selected product has been found
	public String var_second_lot;	//the name of the SECOND lot where the selected product has been found
	public int r1;					//the row from "Contracte" sheet where the selected product and the FIRST lot has been found
	public int r2;					//the row from "Contracte" sheet where the selected product and the SECOND lot has been found
	public int r3;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Avans tipar" has been found
	public int r4;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Avans tipar" has been found
	public int r5;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Storno avans tipar" has been found
	public int r6;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Storno avans tipar" has been found
	public int r7;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Tipar" has been found (first occurrence)
	public int r8;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Tipar" has been found (second occurrence)
	public int r9;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Tipar" has been found (first occurrence)
	public int r10;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Tipar" has been found (second occurrence)
	public int r11;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Avans CD" has been found
	public int r12;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Avans CD" has been found
	public int r13;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Storno avans CD" has been found
	public int r14;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Storno avans CD" has been found
	public int r15;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "CD" has been found
	public int r16;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "CD" has been found
	public int r17;					//the row from "Facturi" sheet where the selected product, the FIRST lot and the type "Logistica" has been found
	public int r18;					//the row from "Facturi" sheet where the selected product, the SECOND lot and the type "Logistica" has been found

	public XSSFWorkbook myWorkBook;
	
	DecimalFormat dc_tiraj = new DecimalFormat("###,###");
	DecimalFormat dc_pret_ex = new DecimalFormat("0.0000");
	DecimalFormat dc_pret_total = new DecimalFormat("###,###.00");
	
	
	public CM_frame() throws InvalidFormatException {
		setTitle("Contract Management by Products");
		createComponents();			
		setSize(FRAME_WIDTH, FRAME_HEIGHT);	
	}
	public void createComponents() throws InvalidFormatException {

		//Construct the panel for labels and data
				
				JPanel panelFrame = new JPanel();
				panelFrame.setLayout(new FlowLayout(FlowLayout.LEADING));
				panelFrame.setBounds(0, 0, 1200, 1000);
				panelFrame.setBackground(Color.WHITE);
				panelFrame.setLayout(null);
				
				JLabel l1 = new JLabel("");														//See the results of changing ComboBox value
				l1.setPreferredSize(new Dimension(750, 50));
				Dimension size1 = l1.getPreferredSize();
				l1.setBounds(375, 45, size1.width, size1.height);
				l1.setHorizontalAlignment(SwingConstants.CENTER);
				l1.setFont(new Font("Verdana", Font.BOLD, 22));
				l1.setOpaque(true);
				l1.setBackground(new Color(224, 224, 224));
				l1.setForeground(Color.RED);
				
				JLabel l2 = new JLabel("");														//The first LOT NUMBER for the chosen product
				l2.setPreferredSize(new Dimension(100, 50));
				Dimension size2 = l2.getPreferredSize();
				l2.setBounds(20, 95, size2.width, size2.height);
				l2.setHorizontalAlignment(SwingConstants.CENTER);
				l2.setFont(new Font("Verdana", Font.BOLD, 20));
				l2.setOpaque(true);
				l2.setBackground(new Color(224, 224, 224));
				l2.setForeground(Color.RED);		

				JLabel l3 = new JLabel("");														//The second LOT NUMBER for the chosen product
				l3.setBounds(20, 570, size2.width, size2.height);
				l3.setHorizontalAlignment(SwingConstants.CENTER);
				l3.setFont(new Font("Verdana", Font.BOLD, 20));
				l3.setOpaque(true);
				l3.setBackground(new Color(224, 224, 224));
				l3.setForeground(Color.RED);

		//Construct labels for row titles (l4-l15 for the first lot)
				
				Font rowTitleFont = new Font("Verdana", Font.BOLD, 14);
				Dimension rowTitleDimension = new Dimension(420,28);
				
				JLabel l4 = new JLabel ("Tiraj TOTAL");											//"Tiraj TOTAL" for the first lot
				l4.setPreferredSize(rowTitleDimension);
				l4.setBounds(20, 190, rowTitleDimension.width, rowTitleDimension.height);
				l4.setHorizontalAlignment(SwingConstants.LEFT);
				l4.setFont(rowTitleFont);
				l4.setForeground(Color.BLACK);		
				
				JLabel l5 = new JLabel ("Tiraj CNEE");											//"Tiraj CNEE" for the first lot
				l5.setPreferredSize(rowTitleDimension);
				l5.setBounds(20, 215, rowTitleDimension.width, rowTitleDimension.height);
				l5.setHorizontalAlignment(SwingConstants.LEFT);
				l5.setFont(rowTitleFont);
				l5.setForeground(Color.BLACK);

				JLabel l6 = new JLabel("Tiraj Piata libera");									//"Tiraj Piata libera" for the first lot
				l6.setPreferredSize(rowTitleDimension);
				l6.setBounds(20, 240, rowTitleDimension.width, rowTitleDimension.height);
				l6.setHorizontalAlignment(SwingConstants.LEFT);
				l6.setFont(rowTitleFont);
				l6.setForeground(Color.BLACK);
				
				JLabel l7 = new JLabel("Pret TIPAR (eur/ex + TVA)");							//"Pret TIPAR (eur/ex + TVA)" for the first lot
				l7.setPreferredSize(rowTitleDimension);
				l7.setBounds(20, 265, rowTitleDimension.width, rowTitleDimension.height);
				l7.setHorizontalAlignment(SwingConstants.LEFT);
				l7.setFont(rowTitleFont);
				l7.setForeground(Color.BLACK);		
				
				JLabel l8 = new JLabel("Pret TIPAR TOTAL (eur + TVA)");							//"Pret TIPAR TOTAL (eur + TVA)" for the first lot
				l8.setPreferredSize(rowTitleDimension);
				l8.setBounds(20, 290, rowTitleDimension.width, rowTitleDimension.height);
				l8.setHorizontalAlignment(SwingConstants.LEFT);
				l8.setFont(rowTitleFont);
				l8.setForeground(Color.BLACK);		

				JLabel l9 = new JLabel("Pret CD (eur/ex + TVA)");								//"Pret CD (eur/ex + TVA)" for the first lot
				l9.setPreferredSize(rowTitleDimension);
				l9.setBounds(20, 315, rowTitleDimension.width, rowTitleDimension.height);
				l9.setHorizontalAlignment(SwingConstants.LEFT);
				l9.setFont(rowTitleFont);
				l9.setForeground(Color.BLACK);		
				
				JLabel l10 = new JLabel("Pret CD TOTAL (eur + TVA)");							//"Pret CD TOTAL (eur + TVA)" for the first lot
				l10.setPreferredSize(rowTitleDimension);
				l10.setBounds(20, 340, rowTitleDimension.width, rowTitleDimension.height);
				l10.setHorizontalAlignment(SwingConstants.LEFT);
				l10.setFont(rowTitleFont);
				l10.setForeground(Color.BLACK);		
				
				JLabel l11 = new JLabel("Pret TOTAL (manual + CD)(eur + TVA)");					//"Pret TOTAL (manual + CD)(eur + TVA)" for the first lot
				l11.setPreferredSize(rowTitleDimension);
				l11.setBounds(20, 365, rowTitleDimension.width, rowTitleDimension.height);
				l11.setHorizontalAlignment(SwingConstants.LEFT);
				l11.setFont(rowTitleFont);
				l11.setForeground(Color.BLACK);		
				
				JLabel l12 = new JLabel("Pret logistica (eur/ex + TVA)");						//"Pret logistica (eur/ex + TVA)" for the first lot
				l12.setPreferredSize(rowTitleDimension);
				l12.setBounds(20, 390, rowTitleDimension.width, rowTitleDimension.height);
				l12.setHorizontalAlignment(SwingConstants.LEFT);
				l12.setFont(rowTitleFont);
				l12.setForeground(Color.BLACK);		
				
				JLabel l13 = new JLabel("Pret logistica TOTAL (eur + TVA)");					//"Pret logistica TOTAL (eur + TVA)" for the first lot
				l13.setPreferredSize(rowTitleDimension);
				l13.setBounds(20, 415, rowTitleDimension.width, rowTitleDimension.height);
				l13.setHorizontalAlignment(SwingConstants.LEFT);
				l13.setFont(rowTitleFont);
				l13.setForeground(Color.BLACK);		
				
				JLabel l14 = new JLabel("Pret TOTAL (manual + CD + logistica)(eur/ex + TVA)");	//"Pret TOTAL (manual + CD + logistica)(eur/ex + TVA)" for the first lot
				l14.setPreferredSize(rowTitleDimension);
				l14.setBounds(20, 440, rowTitleDimension.width, rowTitleDimension.height);
				l14.setHorizontalAlignment(SwingConstants.LEFT);
				l14.setFont(rowTitleFont);
				l14.setForeground(Color.BLACK);				

				JLabel l15 = new JLabel("Pret TOTAL (manual + CD + logistica)(eur + TVA)");		//"Pret TOTAL (manual + CD + logistica)(eur + TVA)" for the first lot
				l15.setPreferredSize(rowTitleDimension);
				l15.setBounds(20, 465, rowTitleDimension.width, rowTitleDimension.height);
				l15.setHorizontalAlignment(SwingConstants.LEFT);
				l15.setFont(rowTitleFont);
				l15.setForeground(Color.BLACK);		
				
		//Construct a horizontal line to separate the data between first and second lot
						
				JLabel line = new JLabel("");
				line.setPreferredSize(new Dimension(1440, 6));
				Dimension line_size = line.getPreferredSize();
				line.setBounds(20, 550, line_size.width, line_size.height);
				line.setOpaque(true);
				line.setBackground(Color.LIGHT_GRAY);				
				
		//Construct labels for row titles (l16-l27 for the second lot)
				
				JLabel l16 = new JLabel("Tiraj TOTAL");											//"Tiraj TOTAL" for the second lot
				l16.setPreferredSize(rowTitleDimension);
				l16.setBounds(20, 650, rowTitleDimension.width, rowTitleDimension.height);
				l16.setHorizontalAlignment(SwingConstants.LEFT);
				l16.setFont(rowTitleFont);
				l16.setForeground(Color.BLACK);		
				
				JLabel l17 = new JLabel("Tiraj CNEE");											//"Tiraj CNEE" for the second lot
				l17.setPreferredSize(rowTitleDimension);
				l17.setBounds(20, 675, rowTitleDimension.width, rowTitleDimension.height);
				l17.setHorizontalAlignment(SwingConstants.LEFT);
				l17.setFont(rowTitleFont);
				l17.setForeground(Color.BLACK);
						
				JLabel l18 = new JLabel("Tiraj Piata libera");									//"Tiraj Piata libera" for the second lot
				l18.setPreferredSize(rowTitleDimension);
				l18.setBounds(20, 700, rowTitleDimension.width, rowTitleDimension.height);
				l18.setHorizontalAlignment(SwingConstants.LEFT);
				l18.setFont(rowTitleFont);
				l18.setForeground(Color.BLACK);
						
				JLabel l19 = new JLabel("Pret TIPAR (eur/ex + TVA)");							//"Pret TIPAR (eur/ex + TVA)" for the second lot
				l19.setPreferredSize(rowTitleDimension);
				l19.setBounds(20, 725, rowTitleDimension.width, rowTitleDimension.height);
				l19.setHorizontalAlignment(SwingConstants.LEFT);
				l19.setFont(rowTitleFont);
				l19.setForeground(Color.BLACK);		
						
				JLabel l20 = new JLabel("Pret TIPAR TOTAL (eur + TVA)");						//"Pret TIPAR TOTAL (eur + TVA)" for the second lot
				l20.setPreferredSize(rowTitleDimension);
				l20.setBounds(20, 750, rowTitleDimension.width, rowTitleDimension.height);
				l20.setHorizontalAlignment(SwingConstants.LEFT);
				l20.setFont(rowTitleFont);
				l20.setForeground(Color.BLACK);		

				JLabel l21 = new JLabel("Pret CD (eur/ex + TVA)");								//"Pret CD (eur/ex + TVA)" for the second lot
				l21.setPreferredSize(rowTitleDimension);
				l21.setBounds(20, 775, rowTitleDimension.width, rowTitleDimension.height);
				l21.setHorizontalAlignment(SwingConstants.LEFT);
				l21.setFont(rowTitleFont);
				l21.setForeground(Color.BLACK);		
						
				JLabel l22 = new JLabel("Pret CD TOTAL (eur + TVA)");							//"Pret CD TOTAL (eur + TVA)" for the second lot
				l22.setPreferredSize(rowTitleDimension);
				l22.setBounds(20, 800, rowTitleDimension.width, rowTitleDimension.height);
				l22.setHorizontalAlignment(SwingConstants.LEFT);
				l22.setFont(rowTitleFont);
				l22.setForeground(Color.BLACK);		
						
				JLabel l23 = new JLabel("Pret TOTAL (manual + CD)(eur + TVA)");					//"Pret TOTAL (manual + CD)(eur + TVA)" for the second lot
				l23.setPreferredSize(rowTitleDimension);
				l23.setBounds(20, 825, rowTitleDimension.width, rowTitleDimension.height);
				l23.setHorizontalAlignment(SwingConstants.LEFT);
				l23.setFont(rowTitleFont);
				l23.setForeground(Color.BLACK);		
						
				JLabel l24 = new JLabel("Pret logistica (eur/ex + TVA)");						//"Pret logistica (eur/ex + TVA)" for the second lot
				l24.setPreferredSize(rowTitleDimension);
				l24.setBounds(20, 850, rowTitleDimension.width, rowTitleDimension.height);
				l24.setFont(rowTitleFont);
				l24.setForeground(Color.BLACK);		
						
				JLabel l25 = new JLabel("Pret logistica TOTAL (eur + TVA)");					//"Pret logistica TOTAL (eur + TVA)" for the second lot
				l25.setPreferredSize(rowTitleDimension);
				l25.setBounds(20, 875, rowTitleDimension.width, rowTitleDimension.height);
				l25.setHorizontalAlignment(SwingConstants.LEFT);
				l25.setFont(rowTitleFont);
				l25.setForeground(Color.BLACK);		
						
				JLabel l26 = new JLabel("Pret TOTAL (manual + CD + logistica)(eur/ex + TVA)");	//"Pret TOTAL (manual + CD + logistica)(eur/ex + TVA)" for the second lot
				l26.setPreferredSize(rowTitleDimension);
				l26.setBounds(20, 900, rowTitleDimension.width, rowTitleDimension.height);
				l26.setHorizontalAlignment(SwingConstants.LEFT);
				l26.setFont(rowTitleFont);
				l26.setForeground(Color.BLACK);				
						
				JLabel l27 = new JLabel("Pret TOTAL (manual + CD + logistica)(eur + TVA)");		//"Pret TOTAL (manual + CD + logistica)(eur + TVA)" for the second lot
				l27.setPreferredSize(rowTitleDimension);
				l27.setBounds(20, 925, rowTitleDimension.width, rowTitleDimension.height);
				l27.setHorizontalAlignment(SwingConstants.LEFT);
				l27.setFont(rowTitleFont);
				l27.setForeground(Color.BLACK);		
		
		//Construct labels for columns titles

				Font colTitleFont = new Font("Verdana", Font.BOLD, 14);
				Dimension colTitleDimension = new Dimension(100,35);
				
				JLabel l28 = new JLabel("Contract");											//"Contract"
				l28.setPreferredSize(colTitleDimension);
				l28.setBounds(470, 95, colTitleDimension.width, colTitleDimension.height);
				l28.setHorizontalAlignment(SwingConstants.CENTER);
				l28.setVerticalAlignment(SwingConstants.CENTER);
				l28.setFont(colTitleFont);
				l28.setForeground(Color.BLACK);

		//Construct labels for values in "Contract" column (l29-l42 for the first lot)

				Font fieldFont = new Font("Verdana", Font.PLAIN, 14);
				Dimension fieldDimension = new Dimension(100, 28);

				
				JLabel l29 = new JLabel();														//"Contract number" for the first lot
				l29.setPreferredSize(fieldDimension);
				l29.setBounds(470, 130, fieldDimension.width, fieldDimension.height);
				l29.setHorizontalAlignment(SwingConstants.CENTER);
				l29.setFont(fieldFont);
				l29.setForeground(Color.BLACK);
				
				JLabel l30 = new JLabel();														//"Contract date" for the first lot
				l30.setPreferredSize(fieldDimension);
				l30.setBounds(470, 150, fieldDimension.width, fieldDimension.height);
				l30.setHorizontalAlignment(SwingConstants.CENTER);
				l30.setFont(fieldFont);
				l30.setForeground(Color.BLACK);
				
				JLabel l31 = new JLabel();														//"Tiraj total in Contract" for the first lot
				l31.setPreferredSize(fieldDimension);
				l31.setBounds(470, 190, fieldDimension.width, fieldDimension.height);
				l31.setHorizontalAlignment(SwingConstants.RIGHT);
				l31.setFont(fieldFont);
				l31.setForeground(Color.BLACK);
				
				JLabel l32 = new JLabel();														//"Tiraj CNEE in Contract" for the first lot
				l32.setPreferredSize(fieldDimension);
				l32.setBounds(470, 215, fieldDimension.width, fieldDimension.height);
				l32.setHorizontalAlignment(SwingConstants.RIGHT);
				l32.setFont(fieldFont);
				l32.setForeground(Color.BLACK);

				JLabel l33 = new JLabel();														//"Tiraj Piata Libera in Contract" for the first lot
				l33.setPreferredSize(fieldDimension);
				l33.setBounds(470, 240, fieldDimension.width, fieldDimension.height);
				l33.setHorizontalAlignment(SwingConstants.RIGHT);
				l33.setFont(fieldFont);
				l33.setForeground(Color.BLACK);

				JLabel l34 = new JLabel();														//"Pret tipo (eur/ex + TVA) in Contract" for the first lot
				l34.setPreferredSize(fieldDimension);
				l34.setBounds(470, 265, fieldDimension.width, fieldDimension.height);
				l34.setHorizontalAlignment(SwingConstants.RIGHT);
				l34.setFont(fieldFont);
				l34.setForeground(Color.BLACK);

				JLabel l35 = new JLabel();														//"Pret tipo total (eur + TVA) in Contract" for the first lot
				l35.setPreferredSize(fieldDimension);
				l35.setBounds(470, 290, fieldDimension.width, fieldDimension.height);
				l35.setHorizontalAlignment(SwingConstants.RIGHT);
				l35.setFont(fieldFont);
				l35.setForeground(Color.BLACK);
				
				JLabel l36 = new JLabel();														//"Pret CD (eur/ex + TVA) in Contract" for the first lot
				l36.setPreferredSize(fieldDimension);
				l36.setBounds(470, 315, fieldDimension.width, fieldDimension.height);
				l36.setHorizontalAlignment(SwingConstants.RIGHT);
				l36.setFont(fieldFont);
				l36.setForeground(Color.BLACK);
				
				JLabel l37 = new JLabel();														//"Pret CD total (eur + TVA) in Contract" for the first lot
				l37.setPreferredSize(fieldDimension);
				l37.setBounds(470, 340, fieldDimension.width, fieldDimension.height);
				l37.setHorizontalAlignment(SwingConstants.RIGHT);
				l37.setFont(fieldFont);
				l37.setForeground(Color.BLACK);
				
				JLabel l38 = new JLabel();														//"Pret total (manual + CD)(eur/ex + TVA) in Contract" for the first lot
				l38.setPreferredSize(fieldDimension);
				l38.setBounds(470, 365, fieldDimension.width, fieldDimension.height);
				l38.setHorizontalAlignment(SwingConstants.RIGHT);
				l38.setFont(fieldFont);
				l38.setForeground(Color.BLACK);
				
				JLabel l39 = new JLabel();														//"Pret logistica (eur/ex + TVA) in Contract" for the first lot
				l39.setPreferredSize(fieldDimension);
				l39.setBounds(470, 390, fieldDimension.width, fieldDimension.height);
				l39.setHorizontalAlignment(SwingConstants.RIGHT);
				l39.setFont(fieldFont);
				l39.setForeground(Color.BLACK);
				
				JLabel l40 = new JLabel();														//"Pret logistica total (eur + TVA) in Contract" for the first lot
				l40.setPreferredSize(fieldDimension);
				l40.setBounds(470, 415, fieldDimension.width, fieldDimension.height);
				l40.setHorizontalAlignment(SwingConstants.RIGHT);
				l40.setFont(fieldFont);
				l40.setForeground(Color.BLACK);
				
				JLabel l41 = new JLabel();														//"Pret total (manual + Cd + logistica)(eur/ex + TVA) in Contract" for the first lot
				l41.setPreferredSize(fieldDimension);
				l41.setBounds(470, 440, fieldDimension.width, fieldDimension.height);
				l41.setHorizontalAlignment(SwingConstants.RIGHT);
				l41.setFont(fieldFont);
				l41.setForeground(Color.BLACK);
				
				JLabel l42 = new JLabel();														//"Pret total (manual + CD + logistica)(eur + TVA) in Contract" for the first lot
				l42.setPreferredSize(fieldDimension);
				l42.setBounds(470, 465, fieldDimension.width, fieldDimension.height);
				l42.setHorizontalAlignment(SwingConstants.RIGHT);
				l42.setFont(fieldFont);
				l42.setForeground(Color.BLACK);

		//Construct label for value "Contract" (column title)

				JLabel l43 = new JLabel("Contract");											//"Contract"
				l43.setPreferredSize(colTitleDimension);
				l43.setBounds(470, 560, colTitleDimension.width, colTitleDimension.height);
				l43.setHorizontalAlignment(SwingConstants.CENTER);
				l43.setVerticalAlignment(SwingConstants.CENTER);
				l43.setFont(colTitleFont);
				l43.setForeground(Color.BLACK);

		//Construct labels for values in "Contract" column (l44-l57 for the second lot)
				
				JLabel l44 = new JLabel();														//"Contract number" for the second lot
				l44.setPreferredSize(fieldDimension);
				l44.setBounds(470, 595, fieldDimension.width, fieldDimension.height);
				l44.setHorizontalAlignment(SwingConstants.CENTER);
				l44.setFont(fieldFont);
				l44.setForeground(Color.BLACK);
				
				JLabel l45 = new JLabel();														//"Contract date" for the second lot
				l45.setPreferredSize(fieldDimension);
				l45.setBounds(470, 615, fieldDimension.width, fieldDimension.height);
				l45.setHorizontalAlignment(SwingConstants.CENTER);
				l45.setFont(fieldFont);
				l45.setForeground(Color.BLACK);
				
				JLabel l46 = new JLabel();														//"Tiraj total in Contract" for the second lot
				l46.setPreferredSize(fieldDimension);
				l46.setBounds(470, 650, fieldDimension.width, fieldDimension.height);
				l46.setHorizontalAlignment(SwingConstants.RIGHT);
				l46.setFont(fieldFont);
				l46.setForeground(Color.BLACK);
				
				JLabel l47 = new JLabel();														//"Tiraj CNEE in Contract" for the second lot
				l47.setPreferredSize(fieldDimension);
				l47.setBounds(470, 675, fieldDimension.width, fieldDimension.height);
				l47.setHorizontalAlignment(SwingConstants.RIGHT);
				l47.setFont(fieldFont);
				l47.setForeground(Color.BLACK);
				
				JLabel l48 = new JLabel();														//"Tiraj Piata Libera in Contract" for the second lot
				l48.setPreferredSize(fieldDimension);
				l48.setBounds(470, 700, fieldDimension.width, fieldDimension.height);
				l48.setHorizontalAlignment(SwingConstants.RIGHT);
				l48.setFont(fieldFont);
				l48.setForeground(Color.BLACK);
				
				JLabel l49 = new JLabel();														//"Pret tipo (eur/ex + TVA) in Contract" for the second lot
				l49.setPreferredSize(fieldDimension);
				l49.setBounds(470, 725, fieldDimension.width, fieldDimension.height);
				l49.setHorizontalAlignment(SwingConstants.RIGHT);
				l49.setFont(fieldFont);
				l49.setForeground(Color.BLACK);
				
				JLabel l50 = new JLabel();														//"Pret tipo total (eur + TVA) in Contract" for the second lot
				l50.setPreferredSize(fieldDimension);
				l50.setBounds(470, 750, fieldDimension.width, fieldDimension.height);
				l50.setHorizontalAlignment(SwingConstants.RIGHT);
				l50.setFont(fieldFont);
				l50.setForeground(Color.BLACK);
				
				JLabel l51 = new JLabel();														//"Pret CD (eur/ex + TVA) in Contract" for the second lot
				l51.setPreferredSize(fieldDimension);
				l51.setBounds(470, 775, fieldDimension.width, fieldDimension.height);
				l51.setHorizontalAlignment(SwingConstants.RIGHT);
				l51.setFont(fieldFont);
				l51.setForeground(Color.BLACK);
				
				JLabel l52 = new JLabel();														//"Pret CD total (eur + TVA) in Contract" for the second lot
				l52.setPreferredSize(fieldDimension);
				l52.setBounds(470, 800, fieldDimension.width, fieldDimension.height);
				l52.setHorizontalAlignment(SwingConstants.RIGHT);
				l52.setFont(fieldFont);
				l52.setForeground(Color.BLACK);
				
				JLabel l53 = new JLabel();														//"Pret total (manual + CD) (eur/ex + TVA) in Contract" for the second lot
				l53.setPreferredSize(fieldDimension);
				l53.setBounds(470, 825, fieldDimension.width, fieldDimension.height);
				l53.setHorizontalAlignment(SwingConstants.RIGHT);
				l53.setFont(fieldFont);
				l53.setForeground(Color.BLACK);
				
				JLabel l54 = new JLabel();														//"Pret logistica (eur/ex + TVA) in Contract" for the second lot
				l54.setPreferredSize(fieldDimension);
				l54.setBounds(470, 850, fieldDimension.width, fieldDimension.height);
				l54.setHorizontalAlignment(SwingConstants.RIGHT);
				l54.setFont(fieldFont);
				l54.setForeground(Color.BLACK);
				
				JLabel l55 = new JLabel();														//"Pret logistica total (eur + TVA) in Contract" for the second lot
				l55.setPreferredSize(fieldDimension);
				l55.setBounds(470, 875, fieldDimension.width, fieldDimension.height);
				l55.setHorizontalAlignment(SwingConstants.RIGHT);
				l55.setFont(fieldFont);
				l55.setForeground(Color.BLACK);
				
				JLabel l56 = new JLabel();														//"Pret total (manual + CD + logistica) (eur/ex + TVA) in Contract" for the second lot
				l56.setPreferredSize(fieldDimension);
				l56.setBounds(470, 900, fieldDimension.width, fieldDimension.height);
				l56.setHorizontalAlignment(SwingConstants.RIGHT);
				l56.setFont(fieldFont);
				l56.setForeground(Color.BLACK);
				
				JLabel l57 = new JLabel();														//"Pret total (manual + CD + logistica) (eur + TVA) in Contract" for the second lot
				l57.setPreferredSize(fieldDimension);
				l57.setBounds(470, 925, fieldDimension.width, fieldDimension.height);
				l57.setHorizontalAlignment(SwingConstants.RIGHT);
				l57.setFont(fieldFont);
				l57.setForeground(Color.BLACK);

		//Construct label "Factura avans tipar" column title				
								
				JLabel l58 = new JLabel("<html><div style='text-align: center;'>Factura avans tipar</div></html>");		//"Factura avans tipar" for the first lot
				l58.setPreferredSize(colTitleDimension);
				l58.setBounds(580, 95, colTitleDimension.width, colTitleDimension.height);
				l58.setHorizontalAlignment(SwingConstants.CENTER);
				l58.setVerticalAlignment(SwingConstants.CENTER);
				l58.setFont(colTitleFont);
				l58.setForeground(Color.BLACK);

		//Construct labels for values in "Factura avans tipar" column (l59-l61 for the first lot)				
				
				JLabel l59 = new JLabel();															//"Factura avans tipar" number for the first lot
				l59.setPreferredSize(fieldDimension);
				l59.setBounds(580, 130, fieldDimension.width, fieldDimension.height);
				l59.setHorizontalAlignment(SwingConstants.CENTER);
				l59.setFont(fieldFont);
				l59.setForeground(Color.BLACK);
				
				JLabel l60 = new JLabel();															//"Factura avans tipar" date for the first lot
				l60.setPreferredSize(fieldDimension);
				l60.setBounds(580, 150, fieldDimension.width, fieldDimension.height);
				l60.setHorizontalAlignment(SwingConstants.CENTER);
				l60.setFont(fieldFont);
				l60.setForeground(Color.BLACK);
				
				JLabel l61 = new JLabel();															//"Factura avans tipar" amount for the first lot
				l61.setPreferredSize(fieldDimension);
				l61.setBounds(580, 290, fieldDimension.width, fieldDimension.height);
				l61.setHorizontalAlignment(SwingConstants.RIGHT);
				l61.setFont(fieldFont);
				l61.setForeground(Color.BLACK);
				
		//Construct label "Factura avans tipar" column title				
				
				JLabel l62 = new JLabel("<html><div style='text-align: center;'>Factura avans tipar</div></html>");		//"Factura avans tipar" for the second lot
				l62.setPreferredSize(colTitleDimension);
				l62.setBounds(580, 560, colTitleDimension.width, colTitleDimension.height);
				l62.setHorizontalAlignment(SwingConstants.CENTER);
				l62.setFont(colTitleFont);
				l62.setForeground(Color.BLACK);

		//Construct labels for values in "Factura avans tipar" column (l63-l65 for the second lot)		
				
				JLabel l63 = new JLabel();															//"Factura avans tipar" number for the second lot
				l63.setPreferredSize(fieldDimension);
				l63.setBounds(580, 595, fieldDimension.width, fieldDimension.height);
				l63.setHorizontalAlignment(SwingConstants.CENTER);
				l63.setFont(fieldFont);
				l63.setForeground(Color.BLACK);
				
				JLabel l64 = new JLabel();															//"Factura avans tipar" date for the second lot
				l64.setPreferredSize(fieldDimension);
				l64.setBounds(580, 615, fieldDimension.width, fieldDimension.height);
				l64.setHorizontalAlignment(SwingConstants.CENTER);
				l64.setFont(fieldFont);
				l64.setForeground(Color.BLACK);
				
				JLabel l65 = new JLabel();															//"Factura avans tipar" amount for the second lot
				l65.setPreferredSize(fieldDimension);
				l65.setBounds(580, 750, fieldDimension.width, fieldDimension.height);
				l65.setHorizontalAlignment(SwingConstants.RIGHT);
				l65.setFont(fieldFont);
				l65.setForeground(Color.BLACK);
				
		//Construct label "Storno avans tipar" column title
				
				JLabel l66 = new JLabel("<html><div style='text-align: center;'>Storno avans tipar</div></html>");		//"Storno avans tipar" for the first lot
				l66.setPreferredSize(colTitleDimension);
				l66.setBounds(690, 95, colTitleDimension.width, colTitleDimension.height);
				l66.setHorizontalAlignment(SwingConstants.CENTER);
				l66.setFont(colTitleFont);
				l66.setForeground(Color.BLACK);

		//Construct labels for values in "Storno avans tipar" column (l67-l69 for the first lot)			
				
				JLabel l67 = new JLabel();															//"Storno avans tipar" number for the first lot
				l67.setPreferredSize(fieldDimension);
				l67.setBounds(690, 130, fieldDimension.width, fieldDimension.height);
				l67.setHorizontalAlignment(SwingConstants.CENTER);
				l67.setFont(fieldFont);
				l67.setForeground(Color.BLACK);

				JLabel l68 = new JLabel();															//"Storno avans tipar" date for the first lot
				l68.setBounds(690, 150, fieldDimension.width, fieldDimension.height);
				l68.setHorizontalAlignment(SwingConstants.CENTER);
				l68.setFont(fieldFont);
				l68.setForeground(Color.BLACK);
				
				JLabel l69 = new JLabel();															//"Storno avans tipar" amount for the first lot
				l69.setBounds(690, 290, fieldDimension.width, fieldDimension.height);
				l69.setHorizontalAlignment(SwingConstants.CENTER);
				l69.setFont(fieldFont);
				l69.setForeground(Color.BLACK);
				
				JLabel AvansTiparOK_firstLot = new JLabel();										//"AvansTiparOK_firstLot"
				AvansTiparOK_firstLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeAvansTiparOK_firstLot = AvansTiparOK_firstLot.getPreferredSize();
				AvansTiparOK_firstLot.setBounds(580, 485, sizeAvansTiparOK_firstLot.width, sizeAvansTiparOK_firstLot.height);
				AvansTiparOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				AvansTiparOK_firstLot.setOpaque(true);
				AvansTiparOK_firstLot.setBackground(new Color(224, 224, 224));
				AvansTiparOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));

				
		//Construct label "Storno avans tipar" column title
				
				JLabel l70 = new JLabel("<html><div style='text-align: center;'>Storno avans tipar</div></html>");		//"Storno avans tipar" for the second lot
				l70.setPreferredSize(colTitleDimension);
				l70.setBounds(690, 560, colTitleDimension.width, colTitleDimension.height);
				l70.setHorizontalAlignment(SwingConstants.CENTER);
				l70.setFont(colTitleFont);
				l70.setForeground(Color.BLACK);

		//Construct labels for values in "Storno avans tipar" column (l71-l73 for the second lot)						
				
				JLabel l71 = new JLabel();															//"Storno avans tipar" number for the second lot
				l71.setPreferredSize(fieldDimension);
				l71.setBounds(690, 595, fieldDimension.width, fieldDimension.height);
				l71.setHorizontalAlignment(SwingConstants.CENTER);
				l71.setFont(fieldFont);
				l71.setForeground(Color.BLACK);
				
				JLabel l72 = new JLabel();															//"Storno avans tipar" date for the second lot
				l72.setPreferredSize(fieldDimension);
				l72.setBounds(690, 615, fieldDimension.width, fieldDimension.height);
				l72.setHorizontalAlignment(SwingConstants.CENTER);
				l72.setFont(fieldFont);
				l72.setForeground(Color.BLACK);
				
				JLabel l73 = new JLabel();															//"Storno avans tipar" amount for the second lot
				l73.setPreferredSize(fieldDimension);
				l73.setBounds(690, 750, fieldDimension.width, fieldDimension.height);
				l73.setHorizontalAlignment(SwingConstants.CENTER);
				l73.setFont(fieldFont);
				l73.setForeground(Color.BLACK);
				
				JLabel AvansTiparOK_secondLot = new JLabel();										//"AvansTiparOK_secondLot"
				AvansTiparOK_secondLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeAvansTiparOK_secondLot = AvansTiparOK_secondLot.getPreferredSize();
				AvansTiparOK_secondLot.setBounds(580, 945, sizeAvansTiparOK_secondLot.width, sizeAvansTiparOK_secondLot.height);
				AvansTiparOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				AvansTiparOK_secondLot.setOpaque(true);
				AvansTiparOK_secondLot.setBackground(new Color(224, 224, 224));
				AvansTiparOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));
				AvansTiparOK_secondLot.setForeground(Color.BLACK);
				
		//Construct label "Factura tipar 1" column title		
				
				JLabel l74 = new JLabel("<html><div style='text-align: center;'>Factura tipar 1</div></html>");		//"Factura tipar 1" for the first lot
				l74.setPreferredSize(colTitleDimension);
				l74.setBounds(800, 95, colTitleDimension.width, colTitleDimension.height);
				l74.setHorizontalAlignment(SwingConstants.CENTER);
				l74.setFont(colTitleFont);
				l74.setForeground(Color.BLACK);
				
		//Construct labels for values in "Factura tipar 1" column (l74-l78 for the first lot)				

				JLabel l75 = new JLabel();															//"Factura tipar 1" number for the first lot
				l75.setPreferredSize(fieldDimension);
				l75.setBounds(800, 130, fieldDimension.width, fieldDimension.height);
				l75.setHorizontalAlignment(SwingConstants.CENTER);
				l75.setFont(fieldFont);
				l75.setForeground(Color.BLACK);

				JLabel l76 = new JLabel();															//"Factura tipar 1" date for the first lot
				l76.setPreferredSize(fieldDimension);
				l76.setBounds(800, 150, fieldDimension.width, fieldDimension.height);
				l76.setHorizontalAlignment(SwingConstants.CENTER);
				l76.setFont(fieldFont);
				l76.setForeground(Color.BLACK);
				
				JLabel l77 = new JLabel();															//"Factura tipar 1" edition quantity for the first lot
				l77.setPreferredSize(fieldDimension);
				l77.setBounds(800, 190, fieldDimension.width, fieldDimension.height);
				l77.setHorizontalAlignment(SwingConstants.CENTER);
				l77.setFont(fieldFont);
				l77.setForeground(Color.BLACK);				
				
				JLabel l78 = new JLabel();															//"Factura tipar 1" amount for the first lot
				l78.setPreferredSize(fieldDimension);
				l78.setBounds(800, 290, fieldDimension.width, fieldDimension.height);
				l78.setHorizontalAlignment(SwingConstants.CENTER);
				l78.setFont(fieldFont);
				l78.setForeground(Color.BLACK);				

		//Construct label "Factura tipar 1" column title
				
				JLabel l79 = new JLabel("<html><div style='text-align: center;'>Factura tipar 1</div></html>");		//"Factura tipar 1" for the second lot
				l79.setPreferredSize(colTitleDimension);
				l79.setBounds(800, 560, colTitleDimension.width, colTitleDimension.height);
				l79.setHorizontalAlignment(SwingConstants.CENTER);
				l79.setFont(colTitleFont);
				l79.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura tipar 1" column (l80-l83 for the second lot)				
				
				JLabel l80 = new JLabel();															//"Factura tipar 1" number for the second lot
				l80.setPreferredSize(fieldDimension);
				l80.setBounds(800, 595, fieldDimension.width, fieldDimension.height);
				l80.setHorizontalAlignment(SwingConstants.CENTER);
				l80.setFont(fieldFont);
				l80.setForeground(Color.BLACK);

				JLabel l81 = new JLabel();															//"Factura tipar 1" date for the second lot
				l81.setPreferredSize(fieldDimension);
				l81.setBounds(800, 615, fieldDimension.width, fieldDimension.height);
				l81.setHorizontalAlignment(SwingConstants.CENTER);
				l81.setFont(fieldFont);
				l81.setForeground(Color.BLACK);
				
				JLabel l82 = new JLabel();															//"Factura tipar 1" edition quantity for the second lot
				l82.setPreferredSize(fieldDimension);
				l82.setBounds(800, 650, fieldDimension.width, fieldDimension.height);
				l82.setHorizontalAlignment(SwingConstants.CENTER);
				l82.setFont(fieldFont);
				l82.setForeground(Color.BLACK);				
				
				JLabel l83 = new JLabel();															//"Factura tipar 1" amount for the second lot
				l83.setPreferredSize(fieldDimension);
				l83.setBounds(800, 750, fieldDimension.width, fieldDimension.height);
				l83.setHorizontalAlignment(SwingConstants.CENTER);
				l83.setFont(fieldFont);
				l83.setForeground(Color.BLACK);				
				
		//Construct labels "Factura tipar 2" column title
				
				JLabel l84 = new JLabel("<html><div style='text-align: center;'>Factura tipar 2</div></html>");		//"Factura tipar 2" for the first lot
				l84.setPreferredSize(colTitleDimension);
				l84.setBounds(910, 95, colTitleDimension.width, colTitleDimension.height);
				l84.setHorizontalAlignment(SwingConstants.CENTER);
				l84.setFont(colTitleFont);
				l84.setForeground(Color.BLACK);
				
		//Construct labels for values in "Factura tipar 2" column (l85-l88 for the first lot)				
				
				JLabel l85 = new JLabel();															//"Factura tipar 2" number for the first lot
				l85.setPreferredSize(fieldDimension);
				l85.setBounds(910, 130, fieldDimension.width, fieldDimension.height);
				l85.setHorizontalAlignment(SwingConstants.CENTER);
				l85.setFont(fieldFont);
				l85.setForeground(Color.BLACK);

				JLabel l86 = new JLabel();															//"Factura tipar 2" date for the first lot
				l86.setPreferredSize(fieldDimension);
				l86.setBounds(910, 150, fieldDimension.width, fieldDimension.height);
				l86.setHorizontalAlignment(SwingConstants.CENTER);
				l86.setFont(fieldFont);
				l86.setForeground(Color.BLACK);
				
				JLabel l87 = new JLabel();															//"Factura tipar 2" edition quantity for the first lot
				l87.setPreferredSize(fieldDimension);
				l87.setBounds(910, 190, fieldDimension.width, fieldDimension.height);
				l87.setHorizontalAlignment(SwingConstants.CENTER);
				l87.setFont(fieldFont);
				l87.setForeground(Color.BLACK);	
				
				JLabel l88 = new JLabel();															//"Factura tipar 2" amount for the first lot
				l88.setPreferredSize(fieldDimension);
				l88.setBounds(910, 290, fieldDimension.width, fieldDimension.height);
				l88.setHorizontalAlignment(SwingConstants.CENTER);
				l88.setFont(fieldFont);
				l88.setForeground(Color.BLACK);	
				
				JLabel TirajOK_firstLot = new JLabel();												//"TirajOK_firstLot"
				TirajOK_firstLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeTirajOK_firstLot = TirajOK_firstLot.getPreferredSize();
				TirajOK_firstLot.setBounds(800, 485, sizeTirajOK_firstLot.width, sizeTirajOK_firstLot.height);
				TirajOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				TirajOK_firstLot.setOpaque(true);
				TirajOK_firstLot.setBackground(new Color(224, 224, 224));
				TirajOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));
				
				JLabel FacturaTiparOK_firstLot = new JLabel();										//"FacturaTiparOK_firstLot"
				FacturaTiparOK_firstLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeFacturaTiparOK_firstLot = FacturaTiparOK_firstLot.getPreferredSize();
				FacturaTiparOK_firstLot.setBounds(800, 515, sizeFacturaTiparOK_firstLot.width, sizeFacturaTiparOK_firstLot.height);
				FacturaTiparOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaTiparOK_firstLot.setOpaque(true);
				FacturaTiparOK_firstLot.setBackground(new Color(224, 224, 224));
				FacturaTiparOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));
				
		//Construct label "Factura tipar 2" column title
				
				JLabel l89 = new JLabel("<html><div style='text-align: center;'>Factura tipar 2</div></html>");		//"Factura tipar 2" for the second lot
				l89.setPreferredSize(colTitleDimension);
				l89.setBounds(910, 560, colTitleDimension.width, colTitleDimension.height);
				l89.setHorizontalAlignment(SwingConstants.CENTER);
				l89.setFont(colTitleFont);
				l89.setForeground(Color.BLACK);

		//Construct labels for values in "Factura tipar 2" column (l90-l93 for the second lot)						
				
				JLabel l90 = new JLabel();															//"Factura tipar 2" number for the second lot
				l90.setPreferredSize(fieldDimension);
				l90.setBounds(910, 595, fieldDimension.width, fieldDimension.height);
				l90.setHorizontalAlignment(SwingConstants.CENTER);
				l90.setFont(fieldFont);
				l90.setForeground(Color.BLACK);

				JLabel l91 = new JLabel();															//"Factura tipar 2" date for the second lot
				l91.setPreferredSize(fieldDimension);
				l91.setBounds(910, 615, fieldDimension.width, fieldDimension.height);
				l91.setHorizontalAlignment(SwingConstants.CENTER);
				l91.setFont(fieldFont);
				l91.setForeground(Color.BLACK);
				
				JLabel l92 = new JLabel();															//"Factura tipar 2" edition quantity for the second lot
				l92.setPreferredSize(fieldDimension);
				l92.setBounds(910, 650, fieldDimension.width, fieldDimension.height);
				l92.setHorizontalAlignment(SwingConstants.CENTER);
				l92.setFont(fieldFont);
				l92.setForeground(Color.BLACK);	
				
				JLabel l93 = new JLabel();															//"Factura tipar 2" amount for the second lot
				l93.setPreferredSize(fieldDimension);
				l93.setBounds(910, 750, fieldDimension.width, fieldDimension.height);
				l93.setHorizontalAlignment(SwingConstants.CENTER);
				l93.setFont(fieldFont);
				l93.setForeground(Color.BLACK);	
				
				JLabel TirajOK_secondLot = new JLabel();											//"TirajOK_firstLot"
				TirajOK_secondLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeTirajOK_secondLot = TirajOK_secondLot.getPreferredSize();
				TirajOK_secondLot.setBounds(800, 945, sizeTirajOK_secondLot.width, sizeTirajOK_secondLot.height);
				TirajOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				TirajOK_secondLot.setOpaque(true);
				TirajOK_secondLot.setBackground(new Color(224, 224, 224));
				TirajOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));
				
				JLabel FacturaTiparOK_secondLot = new JLabel();										//"FacturaTiparOK_firstLot"
				FacturaTiparOK_secondLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeFacturaTiparOK_secondLot = FacturaTiparOK_secondLot.getPreferredSize();
				FacturaTiparOK_secondLot.setBounds(800, 975, sizeFacturaTiparOK_secondLot.width, sizeFacturaTiparOK_firstLot.height);
				FacturaTiparOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaTiparOK_secondLot.setOpaque(true);
				FacturaTiparOK_secondLot.setBackground(new Color(224, 224, 224));
				FacturaTiparOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));

		//Construct label "Factura avans CD" column title
				
				JLabel l94 = new JLabel("<html><div style='text-align: center;'>Factura avans CD</div></html>");		//"Factura avans CD" for the first lot
				l94.setPreferredSize(colTitleDimension);
				l94.setBounds(1020, 95, colTitleDimension.width, colTitleDimension.height);
				l94.setHorizontalAlignment(SwingConstants.CENTER);
				l94.setVerticalAlignment(SwingConstants.CENTER);
				l94.setFont(colTitleFont);
				l94.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura avans CD" column (l95-l97 for the first lot)				
				
				JLabel l95 = new JLabel();															//"Factura avans CD" number for the first lot
				l95.setPreferredSize(fieldDimension);
				l95.setBounds(1020, 130, fieldDimension.width, fieldDimension.height);
				l95.setHorizontalAlignment(SwingConstants.CENTER);
				l95.setFont(fieldFont);
				l95.setForeground(Color.BLACK);

				JLabel l96 = new JLabel();															//"Factura avans CD" date for the first lot
				l96.setPreferredSize(fieldDimension);
				l96.setBounds(1020, 150, fieldDimension.width, fieldDimension.height);
				l96.setHorizontalAlignment(SwingConstants.CENTER);
				l96.setFont(fieldFont);
				l96.setForeground(Color.BLACK);

				JLabel l97 = new JLabel();															//"Factura avans CD" amount for the second lot
				l97.setPreferredSize(fieldDimension);
				l97.setBounds(1020, 340, fieldDimension.width, fieldDimension.height);
				l97.setHorizontalAlignment(SwingConstants.CENTER);
				l97.setFont(fieldFont);
				l97.setForeground(Color.BLACK);	

		//Construct label "Factura avans CD" column title
				
				JLabel l98 = new JLabel("<html><div style='text-align: center;'>Factura avans CD</div></html>");		//"Factura avans CD" for the second lot
				l98.setPreferredSize(colTitleDimension);
				l98.setBounds(1020, 560, colTitleDimension.width, colTitleDimension.height);
				l98.setHorizontalAlignment(SwingConstants.CENTER);
				l98.setVerticalAlignment(SwingConstants.CENTER);
				l98.setFont(colTitleFont);
				l98.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura avans CD" column (l99-l101 for the second lot)				
				
				JLabel l99 = new JLabel();															//"Factura avans CD" number for the second lot
				l99.setPreferredSize(fieldDimension);
				l99.setBounds(1020, 595, fieldDimension.width, fieldDimension.height);
				l99.setHorizontalAlignment(SwingConstants.CENTER);
				l99.setFont(fieldFont);
				l99.setForeground(Color.BLACK);

				JLabel l100 = new JLabel();															//"Factura avans CD" date for the second lot
				l100.setPreferredSize(fieldDimension);
				l100.setBounds(1020, 615, fieldDimension.width, fieldDimension.height);
				l100.setHorizontalAlignment(SwingConstants.CENTER);
				l100.setFont(fieldFont);
				l100.setForeground(Color.BLACK);

				JLabel l101 = new JLabel();															//"Factura avans CD" amount for the second lot
				l101.setPreferredSize(fieldDimension);
				l101.setBounds(1020, 800, fieldDimension.width, fieldDimension.height);
				l101.setHorizontalAlignment(SwingConstants.CENTER);
				l101.setFont(fieldFont);
				l101.setForeground(Color.BLACK);	

		//Construct label "Factura storno avans CD" column title
				
				JLabel l102 = new JLabel("<html><div style='text-align: center;'>Storno avans CD</div></html>");		//"Factura storno avans CD" for the first lot
				l102.setPreferredSize(colTitleDimension);
				l102.setBounds(1130, 95, colTitleDimension.width, colTitleDimension.height);
				l102.setHorizontalAlignment(SwingConstants.CENTER);
				l102.setVerticalAlignment(SwingConstants.CENTER);
				l102.setFont(colTitleFont);
				l102.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura storno avans CD" column (l103-l105 for the first lot)				
				
				JLabel l103 = new JLabel();															//"Factura storno avans CD" number for the first lot
				l103.setPreferredSize(fieldDimension);
				l103.setBounds(1130, 130, fieldDimension.width, fieldDimension.height);
				l103.setHorizontalAlignment(SwingConstants.CENTER);
				l103.setFont(fieldFont);
				l103.setForeground(Color.BLACK);

				JLabel l104 = new JLabel();															//"Factura storno avans CD" date for the first lot
				l104.setPreferredSize(fieldDimension);
				l104.setBounds(1130, 150, fieldDimension.width, fieldDimension.height);
				l104.setHorizontalAlignment(SwingConstants.CENTER);
				l104.setFont(fieldFont);
				l104.setForeground(Color.BLACK);

				JLabel l105 = new JLabel();															//"Factura storno avans CD" amount for the first lot
				l105.setPreferredSize(fieldDimension);
				l105.setBounds(1130, 340, fieldDimension.width, fieldDimension.height);
				l105.setHorizontalAlignment(SwingConstants.CENTER);
				l105.setFont(fieldFont);
				l105.setForeground(Color.BLACK);
				
				JLabel AvansCDOK_firstLot = new JLabel();											//"AvansCDOK_firstLot"
				AvansCDOK_firstLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeAvansCDOK_firstLot = AvansCDOK_firstLot.getPreferredSize();
				AvansCDOK_firstLot.setBounds(1020, 485, sizeAvansCDOK_firstLot.width, sizeAvansCDOK_firstLot.height);
				AvansCDOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				AvansCDOK_firstLot.setOpaque(true);
				AvansCDOK_firstLot.setBackground(new Color(224, 224, 224));
				AvansCDOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));				

		//Construct label "Factura storno avans CD" column title
				
				JLabel l106 = new JLabel("<html><div style='text-align: center;'>Storno avans CD</div></html>");		//"Factura storno avans CD" for the second lot
				l106.setPreferredSize(colTitleDimension);
				l106.setBounds(1130, 560, colTitleDimension.width, colTitleDimension.height);
				l106.setHorizontalAlignment(SwingConstants.CENTER);
				l106.setVerticalAlignment(SwingConstants.CENTER);
				l106.setFont(colTitleFont);
				l106.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura storno avans CD" column (l107-l109 for the second lot)				
				
				JLabel l107 = new JLabel();															//"Factura storno avans CD" number for the second lot
				l107.setPreferredSize(fieldDimension);
				l107.setBounds(1130, 595, fieldDimension.width, fieldDimension.height);
				l107.setHorizontalAlignment(SwingConstants.CENTER);
				l107.setFont(fieldFont);
				l107.setForeground(Color.BLACK);

				JLabel l108 = new JLabel();															//"Factura storno avans CD" date for the second lot
				l108.setPreferredSize(fieldDimension);
				l108.setBounds(1130, 615, fieldDimension.width, fieldDimension.height);
				l108.setHorizontalAlignment(SwingConstants.CENTER);
				l108.setFont(fieldFont);
				l108.setForeground(Color.BLACK);

				JLabel l109 = new JLabel();															//"Factura storno avans CD" amount for the second lot
				l109.setPreferredSize(fieldDimension);
				l109.setBounds(1130, 800, fieldDimension.width, fieldDimension.height);
				l109.setHorizontalAlignment(SwingConstants.CENTER);
				l109.setFont(fieldFont);
				l109.setForeground(Color.BLACK);	

				JLabel AvansCDOK_secondLot = new JLabel();											//"AvansCDOK_secondLot"
				AvansCDOK_secondLot.setPreferredSize(new Dimension(210, 30));
				Dimension sizeAvansCDOK_secondLot = AvansCDOK_secondLot.getPreferredSize();
				AvansCDOK_secondLot.setBounds(1020, 945, sizeAvansCDOK_secondLot.width, sizeAvansCDOK_secondLot.height);
				AvansCDOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				AvansCDOK_secondLot.setOpaque(true);
				AvansCDOK_secondLot.setBackground(new Color(224, 224, 224));
				AvansCDOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));
				AvansCDOK_secondLot.setForeground(Color.BLACK);				

		//Construct label "Factura CD" column title
				
				JLabel l110 = new JLabel("<html><div style='text-align: center;'>Factura CD</div></html>");		//"Factura CD" for the first lot
				l110.setPreferredSize(colTitleDimension);
				l110.setBounds(1240, 95, colTitleDimension.width, colTitleDimension.height);
				l110.setHorizontalAlignment(SwingConstants.CENTER);
				l110.setVerticalAlignment(SwingConstants.CENTER);
				l110.setFont(colTitleFont);
				l110.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura CD" column (l111-l113 for the first lot)				
				
				JLabel l111 = new JLabel();															//"Factura CD" number for the first lot
				l11.setPreferredSize(fieldDimension);
				l111.setBounds(1240, 130, fieldDimension.width, fieldDimension.height);
				l111.setHorizontalAlignment(SwingConstants.CENTER);
				l111.setFont(fieldFont);
				l111.setForeground(Color.BLACK);

				JLabel l112 = new JLabel();															//"Factura CD" date for the first lot
				l112.setPreferredSize(fieldDimension);
				l112.setBounds(1240, 150, fieldDimension.width, fieldDimension.height);
				l112.setHorizontalAlignment(SwingConstants.CENTER);
				l112.setFont(fieldFont);
				l112.setForeground(Color.BLACK);

				JLabel l113 = new JLabel();															//"Factura CD" amount for the first lot
				l113.setPreferredSize(fieldDimension);
				l113.setBounds(1240, 340, fieldDimension.width, fieldDimension.height);
				l113.setHorizontalAlignment(SwingConstants.CENTER);
				l113.setFont(fieldFont);
				l113.setForeground(Color.BLACK);
				
				JLabel FacturaCDOK_firstLot = new JLabel();											//"FacturaCDOK_firstLot"
				FacturaCDOK_firstLot.setPreferredSize(new Dimension(100, 60));
				Dimension sizeFacturaCDOK_firstLot = FacturaCDOK_firstLot.getPreferredSize();
				FacturaCDOK_firstLot.setBounds(1240, 485, sizeFacturaCDOK_firstLot.width, sizeFacturaCDOK_firstLot.height);
				FacturaCDOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaCDOK_firstLot.setOpaque(true);
				FacturaCDOK_firstLot.setBackground(new Color(224, 224, 224));
				FacturaCDOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));				

		//Construct label "Factura CD" column title
				
				JLabel l114 = new JLabel("<html><div style='text-align: center;'>Factura CD</div></html>");		//"Factura CD" for the second lot
				l114.setPreferredSize(colTitleDimension);
				l114.setBounds(1240, 560, colTitleDimension.width, colTitleDimension.height);
				l114.setHorizontalAlignment(SwingConstants.CENTER);
				l114.setVerticalAlignment(SwingConstants.CENTER);
				l114.setFont(colTitleFont);
				l114.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura CD" column (l115-l117 for the second lot)				
				
				JLabel l115 = new JLabel();															//"Factura CD" number for the second lot
				l115.setPreferredSize(fieldDimension);
				l115.setBounds(1240, 595, fieldDimension.width, fieldDimension.height);
				l115.setHorizontalAlignment(SwingConstants.CENTER);
				l115.setFont(fieldFont);
				l115.setForeground(Color.BLACK);

				JLabel l116 = new JLabel();															//"Factura CD" date for the second lot
				l116.setPreferredSize(fieldDimension);
				l116.setBounds(1240, 615, fieldDimension.width, fieldDimension.height);
				l116.setHorizontalAlignment(SwingConstants.CENTER);
				l116.setFont(fieldFont);
				l116.setForeground(Color.BLACK);

				JLabel l117 = new JLabel();															//"Factura CD" amount for the second lot
				l117.setPreferredSize(fieldDimension);
				l117.setBounds(1240, 800, fieldDimension.width, fieldDimension.height);
				l117.setHorizontalAlignment(SwingConstants.CENTER);
				l117.setFont(fieldFont);
				l117.setForeground(Color.BLACK);	

				JLabel FacturaCDOK_secondLot = new JLabel();										//"FacturaCDOK_secondLot"
				FacturaCDOK_secondLot.setPreferredSize(new Dimension(100, 60));
				Dimension sizeFacturaCDOK_secondLot = FacturaCDOK_secondLot.getPreferredSize();
				FacturaCDOK_secondLot.setBounds(1240, 945, sizeFacturaCDOK_secondLot.width, sizeFacturaCDOK_secondLot.height);
				FacturaCDOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaCDOK_secondLot.setOpaque(true);
				FacturaCDOK_secondLot.setBackground(new Color(224, 224, 224));
				FacturaCDOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));
				FacturaCDOK_secondLot.setForeground(Color.BLACK);	

		//Construct label "Factura logistica" column title
				
				JLabel l118 = new JLabel("<html><div style='text-align: center;'>Factura logistica</div></html>");		//"Factura logistica" for the first lot
				l118.setPreferredSize(colTitleDimension);
				l118.setBounds(1350, 95, colTitleDimension.width, colTitleDimension.height);
				l118.setHorizontalAlignment(SwingConstants.CENTER);
				l118.setVerticalAlignment(SwingConstants.CENTER);
				l118.setFont(colTitleFont);
				l118.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura logistica" column (l119-l121 for the first lot)				
				
				JLabel l119 = new JLabel();															//"Factura logistica" number for the first lot
				l119.setPreferredSize(fieldDimension);
				l119.setBounds(1350, 130, fieldDimension.width, fieldDimension.height);
				l119.setHorizontalAlignment(SwingConstants.CENTER);
				l119.setFont(fieldFont);
				l119.setForeground(Color.BLACK);

				JLabel l120 = new JLabel();															//"Factura logistica" date for the first lot
				l120.setPreferredSize(fieldDimension);
				l120.setBounds(1350, 150, fieldDimension.width, fieldDimension.height);
				l120.setHorizontalAlignment(SwingConstants.CENTER);
				l120.setFont(fieldFont);
				l120.setForeground(Color.BLACK);

				JLabel l121 = new JLabel();															//"Factura logistica" amount for the first lot
				l121.setPreferredSize(fieldDimension);
				l121.setBounds(1350, 340, fieldDimension.width, fieldDimension.height);
				l121.setHorizontalAlignment(SwingConstants.CENTER);
				l121.setFont(fieldFont);
				l121.setForeground(Color.BLACK);
				
				JLabel FacturaLogisticaOK_firstLot = new JLabel();									//"FacturaLogisticaOK_firstLot"
				FacturaLogisticaOK_firstLot.setPreferredSize(new Dimension(100, 60));
				Dimension sizeFacturaLogisticaOK_firstLot = FacturaLogisticaOK_firstLot.getPreferredSize();
				FacturaLogisticaOK_firstLot.setBounds(1350, 485, sizeFacturaLogisticaOK_firstLot.width, sizeFacturaLogisticaOK_firstLot.height);
				FacturaLogisticaOK_firstLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaLogisticaOK_firstLot.setOpaque(true);
				FacturaLogisticaOK_firstLot.setBackground(new Color(224, 224, 224));
				FacturaLogisticaOK_firstLot.setFont(new Font("Verdana", Font.BOLD, 16));				

		//Construct label "Factura logistica" column title				
				
				JLabel l122 = new JLabel("<html><div style='text-align: center;'>Factura logistica</div></html>");		//"Factura logistica" for the second lot
				l122.setPreferredSize(colTitleDimension);
				l122.setBounds(1350, 560, colTitleDimension.width, colTitleDimension.height);
				l122.setHorizontalAlignment(SwingConstants.CENTER);
				l122.setVerticalAlignment(SwingConstants.CENTER);
				l122.setFont(colTitleFont);
				l122.setForeground(Color.BLACK);				

		//Construct labels for values in "Factura logistica" column (l123-l125 for the second lot)				
				
				JLabel l123 = new JLabel();															//"Factura logistica" number for the second lot
				l123.setPreferredSize(fieldDimension);
				l123.setBounds(1350, 595, fieldDimension.width, fieldDimension.height);
				l123.setHorizontalAlignment(SwingConstants.CENTER);
				l123.setFont(fieldFont);
				l123.setForeground(Color.BLACK);

				JLabel l124 = new JLabel();															//"Factura logistica" date for the second lot
				l124.setPreferredSize(fieldDimension);
				l124.setBounds(1350, 615, fieldDimension.width, fieldDimension.height);
				l124.setHorizontalAlignment(SwingConstants.CENTER);
				l124.setFont(fieldFont);
				l124.setForeground(Color.BLACK);

				JLabel l125 = new JLabel();															//"Factura logistica" amount for the second lot
				l125.setPreferredSize(fieldDimension);
				l125.setBounds(1350, 800, fieldDimension.width, fieldDimension.height);
				l125.setHorizontalAlignment(SwingConstants.CENTER);
				l125.setFont(fieldFont);
				l125.setForeground(Color.BLACK);	

				JLabel FacturaLogisticaOK_secondLot = new JLabel();									//"FacturaLogisticaOK_secondLot"
				FacturaLogisticaOK_secondLot.setPreferredSize(new Dimension(100, 60));
				Dimension sizeFacturaLogisticaOK_secondLot = FacturaLogisticaOK_secondLot.getPreferredSize();
				FacturaLogisticaOK_secondLot.setBounds(1350, 945, sizeFacturaLogisticaOK_secondLot.width, sizeFacturaLogisticaOK_secondLot.height);
				FacturaLogisticaOK_secondLot.setHorizontalAlignment(SwingConstants.CENTER);
				FacturaLogisticaOK_secondLot.setOpaque(true);
				FacturaLogisticaOK_secondLot.setBackground(new Color(224, 224, 224));
				FacturaLogisticaOK_secondLot.setFont(new Font("Verdana", Font.BOLD, 16));
				FacturaLogisticaOK_secondLot.setForeground(Color.BLACK);	
				
		//Construct an array list of string containing products

				List<String> products;
				Boolean debug = false;
				ArrayList<XSSFSheet> mySheet = new ArrayList<XSSFSheet>(SelectSheets.selectSheets());
				XSSFSheet sheet_ListaProduse = mySheet.get(2);
				products = ArrayFromExcelToFeedCombo.GetExcelTableIntoArrayListString(sheet_ListaProduse, debug);

		//Convert the array list "products" into an array, to construct JComboBox based on this array		

				String[] products_array = products.toArray(new String[products.size()]);

		//Construct a JComboBox named "selectProduct", to select an item and view information about this item
				
				JComboBox<String> selectProduct = new JComboBox<>(products_array);
				selectProduct.setBounds(20,10,400,25);
				selectProduct.setPreferredSize(new Dimension(400, 40));

		//Add a Listener for the JComboBox "SelectProduct"

				XSSFSheet sheet_contracte = mySheet.get(0);
				XSSFSheet sheet_facturi = mySheet.get(1);
				
				selectProduct.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						l1.setText((String) e.getItem());
						var_produs = (String) selectProduct.getSelectedItem();
						var_first_lot = l2.getText();
						var_second_lot = l3.getText();
					if (FindTheRow.findRows(sheet_contracte, var_produs).size() == 1) {
						r1 = FindTheRow.findRows(sheet_contracte, var_produs).get(0);
						r2 = 0;
					}
					else if (FindTheRow.findRows(sheet_contracte, var_produs).size() == 2) {
						r1 = FindTheRow.findRows(sheet_contracte, var_produs).get(0);
						r2 = FindTheRow.findRows(sheet_contracte, var_produs).get(1);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans tipar").size() == 0) {
						r3 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans tipar").size() == 1) {
						r3 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans tipar").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans tipar").size() == 0) {
						r4 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans tipar").size() == 1) {
						r4 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans tipar").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans tipar").size() == 0) {
						r5 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans tipar").size() == 1) {
						r5 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans tipar").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans tipar").size() == 0) {
						r6 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans tipar").size() == 1) {
						r6 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans tipar").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").size() == 0) {
						r7 = 0;
						r8 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").size() == 1) {
						r7 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").get(0);
						r8 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").size() == 2) {
						r7 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").get(0);
						r8 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Tipar").get(1);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").size() == 0) {
						r9 = 0;
						r10 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").size() == 1) {
						r9 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").get(0);
						r10 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").size() == 2) {
						r9 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").get(0);
						r10 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Tipar").get(1);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans CD").size() == 0) {
						r11 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans CD").size() == 1) {
						r11 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Avans CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans CD").size() == 0) {
						r12 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans CD").size() == 1) {
						r12 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Avans CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans CD").size() == 0) {
						r13 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans CD").size() == 1) {
						r13 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Storno avans CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans CD").size() == 0) {
						r14 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans CD").size() == 1) {
						r14 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Storno avans CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "CD").size() == 0) {
						r15 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "CD").size() == 1) {
						r15 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "CD").size() == 0) {
						r16 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "CD").size() == 1) {
						r16 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "CD").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Logistica").size() == 0) {
						r17 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Logistica").size() == 1) {
						r17 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_first_lot, "Logistica").get(0);
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Logistica").size() == 0) {
						r18 = 0;
					}
					if (FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Logistica").size() == 1) {
						r18 = FindTheRow_3conditions.findRows_3conditions(sheet_facturi, var_produs, var_second_lot, "Logistica").get(0);
					}
		
		//Define the row in the sheet, corresponding to the integer number r1 and r2 (first and second occurrence of the product in sheet)
						
						Row row1 = sheet_contracte.getRow(r1);	//the row in "Contracte" sheet where the contract for FIRST lot has to be found
						Row row2 = sheet_contracte.getRow(r2);	//the row in "Contracte" sheet where the contract for SECOND lot has to be found
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column B ("Lot" column)
						
						Cell cell2 = row1.getCell(1);	//the cell from row row1 and column B where the name of FIRST lot has been found
						Cell cell3 = row2.getCell(1);	//the cell from row row2 and column B where the name of SECOND lot has been found
						
		//Assign the value of cell2 to the label l2 and cell3 to the label l3
						
						l2.setText(cell2.getStringCellValue());
						if(r2 == 0) {
							l3.setText("");
						}
						else {
							l3.setText(cell3.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r1 and r2 and column C ("Contract" column)
						
						Cell cell29 = row1.getCell(2);	//the cell from row row1 and column C where the number of contract corresponding to the FIRST lot has been found
						Cell cell44 = row2.getCell(2);	//the cell from row row2 and column C where the number of contract corresponding to the SECOND lot has been found
						
		//Assign the value of cell29 to the label l29 and cell44 to the label l44
						
						l29.setText(cell29.getStringCellValue());
						if(r2 == 0) {
							l44.setText("");
						}
						else {
							l44.setText(cell44.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column D ("Contract date" column)				
						
						Cell cell30 = row1.getCell(3);	//the cell from row row1 and column D where the date of contract corresponding to the FIRST lot has been found
						Cell cell45 = row2.getCell(3);	//the cell from row row2 and column D where the date of contract corresponding to the SECOND lot has been found
						
		//Assign the value of cell30 to the label l30 and cell45 to the label l45
						
						l30.setText(cell30.getStringCellValue());
						if(r2 == 0) {
							l45.setText("");
						}
						else {
							l45.setText(cell45.getStringCellValue());
						}
		
		//Define the cells in the sheet, corresponding to r1 and r2 and column no. E ("Tiraj" column)					
						
						Cell cell31 = row1.getCell(4);	//the cell from row row1 and column E where the edition quantity of contract corresponding to the FIRST lot has been found
						Cell cell46 = row2.getCell(4);	//the cell from row row2 and column E where the edition quantity of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell31 to the label l31 and cell46 to the label l46
						
						l31.setText(dc_tiraj.format((int)cell31.getNumericCellValue()));
						if(r2 == 0) {
							l46.setText("");
						}
						else {
							l46.setText(dc_tiraj.format((int)cell46.getNumericCellValue()));
						}						

		//Define the cells in the sheet, corresponding to r1 and r2 and column no. F ("Tiraj CNEE" column)					
						
						Cell cell32 = row1.getCell(5);	//the cell from row row1 and column F where the edition quantity for CNEE of contract corresponding to the FIRST lot has been found
						Cell cell47 = row2.getCell(5);	//the cell from row row2 and column F where the edition quantity for CNEE of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell32 to the label l32 and cell47 to the label l47
						
						l32.setText(dc_tiraj.format((int)cell32.getNumericCellValue()));
						if(r2 == 0) {
							l47.setText("");
						}
						else {
							l47.setText(dc_tiraj.format((int)cell47.getNumericCellValue()));
						}						

		//Define the cells in the sheet, corresponding to r1 and r2 and column no. G ("Tiraj Piata Libera" column)					
						
						Cell cell33 = row1.getCell(6);	//the cell from row row1 and column G where the edition quantity for free market of contract corresponding to the FIRST lot has been found
						Cell cell48 = row2.getCell(6);	//the cell from row row2 and column G where the edition quantity for free market of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell33 to the label l33 and cell48 to the label l48
						
						l33.setText(dc_tiraj.format((int)cell33.getNumericCellValue()));
						if(r2 == 0) {
							l48.setText("");
						}
						else {
							l48.setText(dc_tiraj.format((int)cell48.getNumericCellValue()));
						}									

		//Define the cells in the sheet, corresponding to r1 and r2 and column no. H ("Pret tipo (euro/ex + TVA)" column)					
						
						Cell cell34 = row1.getCell(7);	//the cell from row row1 and column H where the unit price for printing of contract corresponding to the FIRST lot has been found
						Cell cell49 = row2.getCell(7);	//the cell from row row2 and column H where the unit price for printing of contract corresponding to the SECOND lot has been found								

		//Assign the value of cell34 to the label l34 and cell49 to the label l49
						
						l34.setText(dc_pret_ex.format((Double)cell34.getNumericCellValue()));
						if(r2 == 0) {
							l49.setText("");
						}
						else {
							l49.setText(dc_pret_ex.format((Double)cell49.getNumericCellValue()));
						}									

		//Define the cells in the sheet, corresponding to r1 and r2 and column no. I ("Pret tipo TOTAL (euro + TVA)" column)
						
						Cell cell35 = row1.getCell(8);	//the cell from row row1 and column I where the total price for printing of contract corresponding to the FIRST lot has been found
						Cell cell50 = row2.getCell(8);	//the cell from row row2 and column I where the total price for printing of contract corresponding to the SECOND lot has been found								

		//Assign the value of cell35 to the label l35 and cell50 to the label l50
						
						l35.setText(dc_pret_total.format((Double)cell35.getNumericCellValue()));
						if(r2 == 0) {
							l50.setText("");
						}
						else {
							l50.setText(dc_pret_total.format((Double)cell50.getNumericCellValue()));
						}				
		//Define the cells in the sheet, corresponding to r1 and r2 and column J ("Pret CD (euro/ex + TVA)" column)					
						
						Cell cell36 = row1.getCell(9);	//the cell from row row1 and column J where the unit price for CD of contract corresponding to the FIRST lot has been found
						Cell cell51 = row2.getCell(9);	//the cell from row row2 and column J where the unit price for CD of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell36 to the label l36 and cell51 to the label l51
						
						l36.setText(dc_pret_ex.format((Double)cell36.getNumericCellValue()));
						if(r2 == 0) {
							l51.setText("");
						}
						else {
							l51.setText(dc_pret_ex.format((Double)cell51.getNumericCellValue()));
						}		
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column K ("Pret CD TOTAL (euro + TVA)" column)					
						
						Cell cell37 = row1.getCell(10);	//the cell from row row1 and column K where the total price for CD of contract corresponding to the FIRST lot has been found
						Cell cell52 = row2.getCell(10);	//the cell from row row2 and column K where the total price for CD of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell37 to the label l37 and cell52 to the label l52
						
						l37.setText(dc_pret_total.format((Double)cell37.getNumericCellValue()));
						if(r2 == 0) {
							l52.setText("");
						}
						else {
							l52.setText(dc_pret_total.format((Double)cell52.getNumericCellValue()));
						}		
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column L ("Pret total (manual + CD) (euro + TVA)" column)					
						
						Cell cell38 = row1.getCell(11);	//the cell from row row1 and column L where the total price for manual + CD of contract corresponding to the FIRST lot has been found
						Cell cell53 = row2.getCell(11);	//the cell from row row2 and column L where the total price for manual + CD of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell38 to the label l38 and cell53 to the label l53
						
						l38.setText(dc_pret_total.format((Double)cell38.getNumericCellValue()));
						if(r2 == 0) {
							l53.setText("");
						}
						else {
							l53.setText(dc_pret_total.format((Double)cell53.getNumericCellValue()));
						}		
		//Define the cells in the sheet, corresponding to r1 and r2 and column M ("Pret logistica (euro/ex + TVA)" column)					
						
						Cell cell39 = row1.getCell(12);	//the cell from row row1 and column M where the unit price for logistics of contract corresponding to the FIRST lot has been found
						Cell cell54 = row2.getCell(12);	//the cell from row row2 and column M where the unit price for logistics of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell39 to the label l39 and cell54 to the label l54
						
						l39.setText(dc_pret_ex.format((Double)cell39.getNumericCellValue()));
						if(r2 == 0) {
							l54.setText("");
						}
						else {
							l54.setText(dc_pret_ex.format((Double)cell54.getNumericCellValue()));
						}		
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column N ("Pret logistica TOTAL (euro + TVA)" column)					
						
						Cell cell40 = row1.getCell(13);	//the cell from row row1 and column N where the total price for logistics of contract corresponding to the FIRST lot has been found
						Cell cell55 = row2.getCell(13);	//the cell from row row2 and column N where the total price for logistics of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell40 to the label l40 and cell55 to the label l55
						
						l40.setText(dc_pret_total.format((Double)cell40.getNumericCellValue()));
						if(r2 == 0) {
							l55.setText("");
						}
						else {
							l55.setText(dc_pret_total.format((Double)cell55.getNumericCellValue()));
						}		
		//Define the cells in the sheet, corresponding to r1 and r2 and column O ("Pret TOTAL (manual + CD + logistica) (euro/ex + TVA)" column)					
						
						Cell cell41 = row1.getCell(14);	//the cell from row row1 and column O where the total unit price (manual + CD + logistics) of contract corresponding to the FIRST lot has been found
						Cell cell56 = row2.getCell(14);	//the cell from row row2 and column O where the total unit price (manual + CD + logistics) of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell41 to the label l41 and cell56 to the label l56
						
						l41.setText(dc_pret_ex.format((Double)cell41.getNumericCellValue()));
						if(r2 == 0) {
							l56.setText("");
						}
						else {
							l56.setText(dc_pret_ex.format((Double)cell56.getNumericCellValue()));
						}		
						
		//Define the cells in the sheet, corresponding to r1 and r2 and column P ("Pret TOTAL (manual + CD + logistica) (euro + TVA)" column)					
						
						Cell cell42 = row1.getCell(15);	//the cell from row row1 and column P where the total price (manual + CD + logistics) of contract corresponding to the FIRST lot has been found
						Cell cell57 = row2.getCell(15);	//the cell from row row2 and column P where the total price (manual + CD + logistics) of contract corresponding to the SECOND lot has been found												

		//Assign the value of cell42 to the label l42 and cell57 to the label l57
						
						l42.setText(dc_pret_total.format((Double)cell42.getNumericCellValue()));
						if(r2 == 0) {
							l57.setText("");
						}
						else {
							l57.setText(dc_pret_total.format((Double)cell57.getNumericCellValue()));
						}
						
		//Define the rows in the sheet, corresponding to the integer number r3 and r4 (rows from "Facturi" sheet where the selected product, for type "Avans tipar" for FIRST lot (respectively for SECOND lot) has been found
						
						Row row3 = sheet_facturi.getRow(r3);	//the row in "Facturi" sheet where the type is "Avans tipar" for FIRST lot
						Row row4 = sheet_facturi.getRow(r4);	//the row in "Facturi" sheet where the type is "Avans tipar" for SECOND lot
		
		//Define the cells in the sheet, corresponding to r3 and r4 and column B ("Factura" column), type "Avans tipar" for the FIRST lot (respectively the SECOND lot)				
						
						Cell cell59 = row3.getCell(1);	//the cell from the row row3 and column B, where the number of invoice "Avans tipar" from FIRST lot has been found
						Cell cell63 = row4.getCell(1);	//the cell from the row row4 and column B, where the number of invoice "Avans tipar" from SECOND lot has been found

		//Assign the value of cell59 to the label l59 and the value of cell63 to the label l63
						
						if(r3 == 0) {
							l59.setText("");
						}
						else {
							l59.setText(cell59.getStringCellValue());
						}
						
						if(r4 == 0) {
							l63.setText("");
						}
						else {
							l63.setText(cell63.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r3 and r4 and column C ("Data factura" column), type "Avans tipar" for the FIRST lot (respectively the SECOND lot)				
						
						Cell cell60 = row3.getCell(2);	//the cell from the row row3 and column C, where the date of invoice "Avans tipar" from FIRST lot has been found
						Cell cell64 = row4.getCell(2);	//the cell from the row row4 and column C, where the date of invoice "Avans tipar" from SECOND lot has been found

		//Assign the value of cell60 to the label l60 and the value of cell64 to the label l64
						
						if(r3 == 0) {
							l60.setText("");
						}
						else {
							l60.setText(cell60.getStringCellValue());
						}
						
						if(r4 == 0) {
							l64.setText("");
						}
						else {
							l64.setText(cell64.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r3 and r4 and column J ("Valoare EUR" column), type "Avans tipar" for the FIRST lot, (respectively the SECOND lot)				
						
						Cell cell61 = row3.getCell(9);	//the cell from the row row3 and column J, where the amount of invoice "Avans tipar" from FIRST lot has been found
						Cell cell65 = row4.getCell(9);	//the cell from the row row4 and column J, where the amount of invoice "Avans tipar" from SECOND lot has been found

		//Assign the value of cell61 to the label l61 and the value of cell65 to the label l65
						
						if(r3 == 0) {
							l61.setText("");
						}
						else {
							l61.setText(dc_pret_total.format((Double)cell61.getNumericCellValue()));
						}
						
						if(r4 == 0) {
							l65.setText("");
						}
						else {
							l65.setText(dc_pret_total.format((Double)cell65.getNumericCellValue()));
						}

		//Define the rows in the sheet, corresponding to the integer number r5 and r6 (rows from "Facturi" sheet where the selected product, for type "Storno avans tipar" for FIRST lot (respectively for SECOND lot) has been found
						
						Row row5 = sheet_facturi.getRow(r5);	//the row in "Facturi" sheet where the type is "Storno avans tipar" for FIRST lot
						Row row6 = sheet_facturi.getRow(r6);	//the row in "Facturi" sheet where the type is "Storno avans tipar" for SECOND lot				

		//Define the cells in the sheet, corresponding to r5 and r6 and column B ("Factura" column), type "Storno avans tipar" for the FIRST lot (respectively the SECOND lot)				
						
						Cell cell67 = row5.getCell(1);	//the cell from the row row5 and column B, where the number of invoice "Storno avans tipar" from FIRST lot has been found
						Cell cell71 = row6.getCell(1);	//the cell from the row row6 and column B, where the number of invoice "Storno avans tipar" from SECOND lot has been found

		//Assign the value of cell67 to the label l67 and the value of cell71 to the label l71
						
						if(r5 == 0) {
							l67.setText("");
						}
						else {
							l67.setText(cell67.getStringCellValue());
						}
						
						if(r4 == 0) {
							l71.setText("");
						}
						else {
							l71.setText(cell71.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r5 and r6 and column C ("Data Factura" column), type "Storno avans tipar" for the FIRST lot (respectively the SECOND lot)				
						
						Cell cell68 = row5.getCell(2);	//the cell from the row row5 and column C, where the date of invoice "Storno avans tipar" from FIRST lot has been found
						Cell cell72 = row6.getCell(2);	//the cell from the row row6 and column C, where the date of invoice "Storno avans tipar" from SECOND lot has been found

		//Assign the value of cell68 to the label l68 and the value of cell72 to the label l72
						
						if(r5 == 0) {
							l68.setText("");
						}
						else {
							l68.setText(cell68.getStringCellValue());
						}
						
						if(r4 == 0) {
							l72.setText("");
						}
						else {
							l72.setText(cell72.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r5 and r6 and column J ("Valoare EUR"), type "Storno avans tipar" for the FIRST lot (respectively the SECOND lot)				
						
						Cell cell69 = row5.getCell(9);	//the cell from the row row5 and column J, where the amount of invoice "Storno avans tipar" from FIRST lot has been found
						Cell cell73 = row6.getCell(9);	//the cell from the row row6 and column J, where the amount of invoice "Storno avans tipar" from SECOND lot has been found

		//Assign the value of cell69 to the label l69 and the value of cell73 to the label l73
						
						if(r5 == 0) {
							l69.setText("");
						}
						else {
							l69.setText(dc_pret_total.format((Double)cell69.getNumericCellValue()));
						}
						
						if(r4 == 0) {
							l73.setText("");
						}
						else {
							l73.setText(dc_pret_total.format((Double)cell73.getNumericCellValue()));
						}

		//Assign text to label "AvansTiparOK_firstLot" and "AvansTiparOK_secondLot" depending on the values of "Avans" and "Storno avans"
						
						if(r3 != 0) {
							if(Math.round(cell61.getNumericCellValue()*100)/100 == -Math.round(cell69.getNumericCellValue()*100)/100) {
									AvansTiparOK_firstLot.setText("Avans tipar inchis");
									AvansTiparOK_firstLot.setForeground(new Color(0, 153, 0));
							}
							else {
									AvansTiparOK_firstLot.setText("Avans neinchis: " + Math.round(cell61.getNumericCellValue()+cell69.getNumericCellValue()*100)/100);
									AvansTiparOK_firstLot.setForeground(Color.RED);
							}
						}
						else if (r3 == 0) {
									AvansTiparOK_firstLot.setText("Fara avans tipar");
									AvansTiparOK_firstLot.setForeground(new Color(0, 153, 0));
						}
						if(r2 != 0) {
							if(r4 != 0) {
								if(Math.round(cell65.getNumericCellValue()*100)/100 == -Math.round(cell73.getNumericCellValue()*100)/100) {
									AvansTiparOK_secondLot.setText("Avans tipar inchis");
									AvansTiparOK_secondLot.setForeground(new Color(0, 153, 0));
								}
								else {
									AvansTiparOK_secondLot.setText("Avans neinchis: " + Math.round(cell65.getNumericCellValue()+cell73.getNumericCellValue()*100)/100);
									AvansTiparOK_secondLot.setForeground(Color.RED);
								}
							}
							else if (r4 == 0) {
									AvansTiparOK_secondLot.setText("Fara avans tipar");
									AvansTiparOK_secondLot.setForeground(new Color(0, 153, 0));
							}
						}
						else if (r2 == 0) {
							AvansTiparOK_secondLot.setText("");
						}
						
		//Define the rows in the sheet, corresponding to the integer number r7 and r8 (rows from "Facturi" sheet where the selected product, for type "Tipar", for FIRST lot, for first invoice (respectively for second invoice) has been found
						
						Row row7 = sheet_facturi.getRow(r7);	//the row in "Facturi" sheet where the type is "Tipar" for FIRST lot (first occurrence)
						Row row8 = sheet_facturi.getRow(r8);	//the row in "Facturi" sheet where the type is "Tipar" for FIRST lot (second occurrence)

		//Define the cells in the sheet, corresponding to r7 and r8 and column B ("Factura" column), type is "Tipar" for the first invoice (respectively second invoice) from FIRST lot				
						
						Cell cell75 = row7.getCell(1);	//the cell from the row row7 and column B, where the number of first invoice "Tipar" from FIRST lot has been found
						Cell cell85 = row8.getCell(1);	//the cell from the row row8 and column B, where the number of second invoice "Tipar" from FIRST lot has been found

		//Assign the value of cell75 to the label l75 and the value of cell85 to the label l85
						
						if(r7 == 0) {
							l75.setText("");
						}
						else {
							l75.setText(cell75.getStringCellValue());
						}
						
						if(r8 == 0) {
							l85.setText("");
						}
						else {
							l85.setText(cell85.getStringCellValue());
						}
	
		//Define the cells in the sheet, corresponding to r7 and r8 and column C ("Data Factura" column), type is "Tipar" for the first invoice (respectively second invoice) from FIRST lot				
						
						Cell cell76 = row7.getCell(2);	//the cell from the row row7 and column C, where the date of first invoice "Tipar" from FIRST lot has been found
						Cell cell86 = row8.getCell(2);	//the cell from the row row8 and column C, where the date of second invoice "Tipar" from FIRST lot has been found

		//Assign the value of cell76 to the label l76 and the value of cell86 to the label l86
						
						if(r7 == 0) {
							l76.setText("");
						}
						else {
							l76.setText(cell76.getStringCellValue());
						}
						
						if(r8 == 0) {
							l86.setText("");
						}
						else {
							l86.setText(cell86.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r7 and r8 and column G ("Tiraj" column), type is "Tipar" for the first invoice (respectively second invoice) from FIRST lot				
						
						Cell cell77 = row7.getCell(6);	//the cell from the row row7 and column G, where the edition quantity of first invoice "Tipar" from FIRST lot has been found
						Cell cell87 = row8.getCell(6);	//the cell from the row row8 and column G, where the edition quantity of second invoice "Tipar" from FIRST lot has been found

		//Assign the value of cell77 to the label l77 and the value of cell87 to the label l87
						
						if(r7 == 0) {
							l77.setText("");
						}
						else {
							l77.setText(dc_tiraj.format((int)cell77.getNumericCellValue()));
						}
						
						if(r8 == 0) {
							l87.setText("");
						}
						else {
							l87.setText(dc_tiraj.format((int)cell87.getNumericCellValue()));
						}

		//Define the cell in the sheet, corresponding to r7 and r8 and column J ("Valoare EUR" column), type is "Tipar" for the first invoice (respectively second invoice) from FIRST lot				
						
						Cell cell78 = row7.getCell(9);	//the cell from the row row7 and column J, where the amount of first invoice "Tipar" from FIRST lot has been found
						Cell cell88 = row8.getCell(9);	//the cell from the row row8 and column J, where the amount of second invoice "Tipar" from FIRST lot has been found

		//Assign the value of cell78 to the label l78 and the value of cell88 to the label l88
						
						if(r7 == 0) {
							l78.setText("");
						}
						else {
							l78.setText(dc_pret_total.format((Double)cell78.getNumericCellValue()));
						}
						
						if(r8 == 0) {
							l88.setText("");
						}
						else {
							l88.setText(dc_pret_total.format((Double)cell88.getNumericCellValue()));
						}

		//Assign text to label "TirajOK_firstLot" depending on the values of "Contract", "Factura tipar 1" and "Factura tipar 2"
						
						if(r7 != 0) {
							if(r8 == 0) {
								if(cell31.getNumericCellValue() == cell77.getNumericCellValue()) {
									TirajOK_firstLot.setText("Tiraj tiparit integral");
									TirajOK_firstLot.setForeground(new Color(0, 153, 0));
								}
								else {
									TirajOK_firstLot.setText("Tiraj ramas:" + dc_tiraj.format((cell31.getNumericCellValue() - cell77.getNumericCellValue())));
									TirajOK_firstLot.setForeground(Color.RED);
								}
							}
							else if (r8 != 0){
								if(cell31.getNumericCellValue() == cell77.getNumericCellValue() + cell87.getNumericCellValue()) {
									TirajOK_firstLot.setText("Tiraj tiparit integral");
									TirajOK_firstLot.setForeground(new Color(0, 153, 0));
								}
								else {
									TirajOK_firstLot.setText("Tiraj ramas:" + dc_tiraj.format((cell31.getNumericCellValue() - cell77.getNumericCellValue() - cell87.getNumericCellValue())));
									TirajOK_firstLot.setForeground(Color.RED);
								}
							}
						}
						else if (r7 == 0) {
									TirajOK_firstLot.setText("");
						}

		//Assign text to label "FacturaTiparOK_firstLot" depending on the values of "Contract", "Factura tipar 1" and "Factura tipar 2"
						
						if(r7 != 0) {
							if(r8 == 0) {
								if(((Double)cell35.getNumericCellValue() - (Double)cell78.getNumericCellValue()) < 2 || ((Double)cell35.getNumericCellValue() - (Double)cell78.getNumericCellValue()) > -2) {
									FacturaTiparOK_firstLot.setText("Factura tipar inchisa");
									FacturaTiparOK_firstLot.setForeground(new Color(0, 153, 0));
								}
								else {
									FacturaTiparOK_firstLot.setText("Rest factura tipo:" + Math.round((cell35.getNumericCellValue() - cell78.getNumericCellValue())*100)/100);
									FacturaTiparOK_firstLot.setForeground(Color.RED);
								}
							}
							else if (r8 != 0){
								if(((Double)cell35.getNumericCellValue() - (Double)cell78.getNumericCellValue() - (Double)cell88.getNumericCellValue()) < 2 || ((Double)cell35.getNumericCellValue() - (Double)cell78.getNumericCellValue() - (Double)cell88.getNumericCellValue()) > -2) {
									FacturaTiparOK_firstLot.setText("Factura tipar inchisa");
									FacturaTiparOK_firstLot.setForeground(new Color(0, 153, 0));
								}
								else {
									FacturaTiparOK_firstLot.setText("Rest factura tipo:" + Math.round((cell35.getNumericCellValue() - cell78.getNumericCellValue() - cell88.getNumericCellValue())*100)/100);
									FacturaTiparOK_firstLot.setForeground(Color.RED);
								}
							}
						}
						else if (r7 == 0) {
									FacturaTiparOK_firstLot.setText("");
						}

		//Define the rows in the sheet, corresponding to the integer number r9 and r10 (rows from "Facturi" sheet where the selected product, for type "Tipar", for SECOND lot, for first invoice (respectively for second invoice) has been found
						
						Row row9 = sheet_facturi.getRow(r9);	//the row in "Facturi" sheet where the type is "Tipar" for SECOND lot (first occurrence)
						Row row10 = sheet_facturi.getRow(r10);	//the row in "Facturi" sheet where the type is "Tipar" for SECOND lot (second occurrence)				

		//Define the cells in the sheet, corresponding to r9 and r10 and column B ("Factura" column), type is "Tipar" for the first invoice (respectively second invoice) from SECOND lot				
						
						Cell cell80 = row9.getCell(1);	//the cell from the row row9 and column B, where the number of first invoice "Tipar" from SECOND lot has been found
						Cell cell90 = row10.getCell(1);	//the cell from the row row10 and column B, where the number of second invoice "Tipar" from SECOND lot has been found

		//Assign the value of cell80 to the label l80 and the value of cell90 to the label l90
						
						if(r9 == 0) {
							l80.setText("");
						}
						else {
							l80.setText(cell80.getStringCellValue());
						}
						
						if(r10 == 0) {
							l90.setText("");
						}
						else {
							l90.setText(cell90.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r9 and r10 and column C ("Data Factura" column), type is "Tipar" for the first invoice (respectively second invoice) from SECOND lot				
						
						Cell cell81 = row9.getCell(2);	//the cell from the row row9 and column C, where the date of first invoice "Tipar" from SECOND lot has been found
						Cell cell91 = row10.getCell(2);	//the cell from the row row10 and column C, where the date of second invoice "Tipar" from SECOND lot has been found

		//Assign the value of cell81 to the label l81 and the value of cell91 to the label l91
						
						if(r9 == 0) {
							l81.setText("");
						}
						else {
							l81.setText(cell81.getStringCellValue());
						}
						
						if(r10 == 0) {
							l91.setText("");
						}
						else {
							l91.setText(cell91.getStringCellValue());
						}
							
		//Define the cells in the sheet, corresponding to r9 and r10 and column G ("Tiraj" column), type is "Tipar" for the first invoice (respectively second invoice) from SECOND lot				
						
						Cell cell82 = row9.getCell(6);	//the cell from the row row9 and column G, where the edition quantity of first invoice "Tipar" from SECOND lot has been found
						Cell cell92 = row10.getCell(6);	//the cell from the row row10 and column G, where the edition quantity of second invoice "Tipar" from SECOND lot has been found

		//Assign the value of cell82 to the label l82 and the value of cell92 to the label l92
						
						if(r9 == 0) {
							l82.setText("");
						}
						else {
							l82.setText(dc_tiraj.format((int)cell82.getNumericCellValue()));
						}
						
						if(r10 == 0) {
							l92.setText("");
						}
						else {
							l92.setText(dc_tiraj.format((int)cell92.getNumericCellValue()));
						}

		//Define the cells in the sheet, corresponding to r9 and r10 and column J ("Valoare EUR" column), type is "Tipar" for the first invoice (respectively second invoice) from SECOND lot				
						
						Cell cell83 = row9.getCell(9);	//the cell from the row row9 and column J, where the amount of first invoice "Tipar" from SECOND lot has been found
						Cell cell93 = row10.getCell(9);	//the cell from the row row10 and column J, where the amount of second invoice "Tipar" from SECOND lot has been found

		//Assign the value of cell83 to the label l83 and the value of cell93 to the label l93
						
						if(r9 == 0) {
							l83.setText("");
						}
						else {
							l83.setText(dc_pret_total.format((Double)cell83.getNumericCellValue()));
						}
						
						if(r10 == 0) {
							l93.setText("");
						}
						else {
							l93.setText(dc_pret_total.format((Double)cell93.getNumericCellValue()));
						}

		//Assign text to label "TirajOK_secondLot" depending on the values of "Contract", "Factura tipar 1" and "Factura tipar 2"
						
						if(r2 != 0) {
							if(r9 != 0) {
								if(r10 == 0) {
									if(cell46.getNumericCellValue() == cell82.getNumericCellValue()) {
										TirajOK_secondLot.setText("Tiraj tiparit integral");
										TirajOK_secondLot.setForeground(new Color(0, 153, 0));
									}
									else {
										TirajOK_secondLot.setText("Tiraj ramas:" + dc_tiraj.format((cell46.getNumericCellValue() - cell82.getNumericCellValue())));
										TirajOK_secondLot.setForeground(Color.RED);
									}
								}
								else if (r10 != 0){
									if(cell46.getNumericCellValue() == cell82.getNumericCellValue() + cell92.getNumericCellValue()) {
										TirajOK_secondLot.setText("Tiraj tiparit integral");
										TirajOK_secondLot.setForeground(new Color(0, 153, 0));
									}
									else {
										TirajOK_secondLot.setText("Tiraj ramas:" + dc_tiraj.format((cell46.getNumericCellValue() - cell82.getNumericCellValue() - cell92.getNumericCellValue())));
										TirajOK_secondLot.setForeground(Color.RED);
									}
								}
							}
							else if (r7 == 0) {
										TirajOK_secondLot.setText("");
							}
						}
						else if (r2 == 0) {
									TirajOK_secondLot.setText("");
						}

		//Assign text to label "FacturaTiparOK_secondLot" depending on the values of "Contract", "Factura tipar 1" and "Factura tipar 2"
						
						if(r9 != 0) {
							if(r10 == 0) {
								if(((Double)cell50.getNumericCellValue() - (Double)cell83.getNumericCellValue()) < 2 || ((Double)cell50.getNumericCellValue() - (Double)cell83.getNumericCellValue()) > -2) {								
									FacturaTiparOK_secondLot.setText("Factura tipar inchisa");
									FacturaTiparOK_secondLot.setForeground(new Color(0, 153, 0));
								}
								else {
									FacturaTiparOK_secondLot.setText("Rest factura tipo:" + Math.round((cell50.getNumericCellValue() - cell83.getNumericCellValue())*100)/100);
									FacturaTiparOK_secondLot.setForeground(Color.RED);
								}
							}
							else if (r10 != 0){
								if(((Double)cell50.getNumericCellValue() - (Double)cell83.getNumericCellValue() - (Double)cell93.getNumericCellValue()) < 2 || ((Double)cell50.getNumericCellValue() - (Double)cell83.getNumericCellValue() - (Double)cell93.getNumericCellValue()) > -2) {								
									FacturaTiparOK_secondLot.setText("Factura tipar inchisa");
									FacturaTiparOK_secondLot.setForeground(new Color(0, 153, 0));
								}
								else {
									FacturaTiparOK_secondLot.setText("Rest factura tipo:" + Math.round((cell50.getNumericCellValue() - cell83.getNumericCellValue() - cell93.getNumericCellValue())*100)/100);
									FacturaTiparOK_secondLot.setForeground(Color.RED);
								}
							}
						}
						else if (r9 == 0) {
									FacturaTiparOK_secondLot.setText("");
						}

		//Define the rows in the sheet, corresponding to the integer number r11 and r12 (rows from "Facturi" sheet where the selected product, for type "Avans CD", for the FIRST lot (respectively for SECOND lot) has been found
						
						Row row11 = sheet_facturi.getRow(r11);	//the row in "Facturi" sheet where the type is "Avans CD" for FIRST lot
						Row row12 = sheet_facturi.getRow(r12);	//the row in "Facturi" sheet where the type is "Avans CD" for SECOND lot
		
		//Define the cells in the sheet, corresponding to r11 and r12 and column B ("Factura" column), type is "Avans CD" for the FIRST lot (respectively SECOND lot)
						
						Cell cell95 = row11.getCell(1);	//the cell from the row row11 and column B, where the number of invoice "Avans CD" from FIRST lot has been found
						Cell cell99 = row12.getCell(1);	//the cell from the row row12 and column B, where the number of invoice "Avans CD" from SECOND lot has been found

		//Assign the value of cell95 to the label l95 and the value of cell99 to the label l99
						
						if(r11 == 0) {
							l95.setText("");
						}
						else {
							l95.setText(cell95.getStringCellValue());
						}
						
						if(r12 == 0) {
							l99.setText("");
						}
						else {
							l99.setText(cell99.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r11 and r12 and column C ("Data factura" column), type is "Avans CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell96 = row11.getCell(2);		//the cell from the row row11 and column C, where the date of invoice "Avans CD" from FIRST lot has been found
						Cell cell100 = row12.getCell(2);	//the cell from the row row12 and column C, where the date of invoice "Avans CD" from SECOND lot has been found

		//Assign the value of cell96 to the label l96 and the value of cell100 to the label l100
						
						if(r11 == 0) {
							l96.setText("");
						}
						else {
							l96.setText(cell96.getStringCellValue());
						}
						
						if(r12 == 0) {
							l100.setText("");
						}
						else {
							l100.setText(cell100.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r11 and r12 and column J ("Valoare EUR" column), type is "Avans CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell97 = row11.getCell(9);		//the cell from the row row11 and column J, where the amount of invoice "Avans CD" from FIRST lot has been found
						Cell cell101 = row12.getCell(9);	//the cell from the row row12 and column J, where the amount of invoice "Avans CD" from SECOND lot has been found

		//Assign the value of cell97 to the label l97 and the value of cell101 to the label l101
						
						if(r11 == 0) {
							l97.setText("");
						}
						else {
							l97.setText(dc_pret_total.format((Double)cell97.getNumericCellValue()));
						}
						
						if(r12 == 0) {
							l101.setText("");
						}
						else {
							l101.setText(dc_pret_total.format((Double)cell101.getNumericCellValue()));
						}

		//Define the rows in the sheet, corresponding to the integer number r13 and r14 (rows from "Facturi" sheet where the selected product, for type "Storno Avans CD", for the FIRST lot (respectively for SECOND lot) has been found
						
						Row row13 = sheet_facturi.getRow(r13);	//the row in "Facturi" sheet where the type is "Storno Avans CD" for FIRST lot
						Row row14 = sheet_facturi.getRow(r14);	//the row in "Facturi" sheet where the type is "Storno Avans CD" for SECOND lot
		
		//Define the cells in the sheet, corresponding to r13 and r14 and column B ("Factura" column), type is "Storno Avans CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell103 = row13.getCell(1);	//the cell from the row row13 and column B, where the number of invoice "Storno Avans CD" from FIRST lot has been found
						Cell cell107 = row14.getCell(1);	//the cell from the row row14 and column B, where the number of invoice "Storno Avans CD" from SECOND lot has been found

		//Assign the value of cell103 to the label l103 and the value of cell107 to the label l107
						
						if(r13 == 0) {
							l103.setText("");
						}
						else {
							l103.setText(cell103.getStringCellValue());
						}
						
						if(r14 == 0) {
							l107.setText("");
						}
						else {
							l107.setText(cell107.getStringCellValue());
						}

		//Define the cells in the sheet, corresponding to r13 and r14 and column C ("Data Factura" column), type is "Storno Avans CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell104 = row13.getCell(2);	//the cell from the row row13 and column C, where the date of invoice "Storno Avans CD" from FIRST lot has been found
						Cell cell108 = row14.getCell(2);	//the cell from the row row14 and column C, where the date of invoice "Storno Avans CD" from SECOND lot has been found

		//Assign the value of cell104 to the label l104 and the value of cell108 to the label l108
						
						if(r13 == 0) {
							l104.setText("");
						}
						else {
							l104.setText(cell104.getStringCellValue());
						}
						
						if(r14 == 0) {
							l108.setText("");
						}
						else {
							l108.setText(cell108.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r13 and r14 and column J ("Valoare EUR" column), type is "Storno Avans CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell105 = row13.getCell(9);	//the cell from the row row13 and column J, where the amount of invoice "Storno Avans CD" from FIRST lot has been found
						Cell cell109 = row14.getCell(9);	//the cell from the row row14 and column J, where the amount of invoice "Storno Avans CD" from SECOND lot has been found

		//Assign the value of cell105 to the label l105 and the value of cell109 to the label l109
						
						if(r13 == 0) {
							l105.setText("");
						}
						else {
							l105.setText(dc_pret_total.format((Double)cell105.getNumericCellValue()));
						}
						
						if(r14 == 0) {
							l109.setText("");
						}
						else {
							l109.setText(dc_pret_total.format((Double)cell109.getNumericCellValue()));
						}

		//Assign text to label "AvansCDOK_firstLot" and "AvansCDOK_secondLot" depending on the values of "Avans CD" and "Storno avans CD"
						
						if(r11 != 0) {
							if(Math.round(cell97.getNumericCellValue()*100)/100 == -Math.round(cell105.getNumericCellValue()*100)/100) {
									AvansCDOK_firstLot.setText("Avans CD inchis");
									AvansCDOK_firstLot.setForeground(new Color(0, 153, 0));
							}
							else {
									AvansCDOK_firstLot.setText("Avans CD neinchis: " + Math.round(cell97.getNumericCellValue()+cell105.getNumericCellValue()*100)/100);
									AvansCDOK_firstLot.setForeground(Color.RED);
							}
						}
						else if (r11 == 0) {
									AvansCDOK_firstLot.setText("Fara avans CD");
									AvansCDOK_firstLot.setForeground(new Color(0, 153, 0));
						}
						if(r2 != 0) {
							if(r12 != 0) {
								if(Math.round(cell101.getNumericCellValue()*100)/100 == -Math.round(cell109.getNumericCellValue()*100)/100) {
									AvansCDOK_secondLot.setText("Avans CD inchis");
									AvansCDOK_secondLot.setForeground(new Color(0, 153, 0));
								}
								else {
									AvansCDOK_secondLot.setText("Avans CD neinchis: " + Math.round(cell101.getNumericCellValue()+cell109.getNumericCellValue()*100)/100);
									AvansCDOK_secondLot.setForeground(Color.RED);
								}
							}
							else if (r12 == 0) {
									AvansCDOK_secondLot.setText("Fara avans CD");
									AvansCDOK_secondLot.setForeground(new Color(0, 153, 0));
							}
						}
						else if (r2 == 0) {
							AvansCDOK_secondLot.setText("");
						}			

		//Define the rows in the sheet, corresponding to the integer number r15 and r16 (rows from "Facturi" sheet where the selected product, for type "CD", for the FIRST lot (respectively for SECOND lot) has been found
						
						Row row15 = sheet_facturi.getRow(r15);	//the row in "Facturi" sheet where the type is "CD" for FIRST lot
						Row row16 = sheet_facturi.getRow(r16);	//the row in "Facturi" sheet where the type is "CD" for SECOND lot				

		//Define the cells in the sheet, corresponding to r15 and r16 and column B ("Factura" column), type is "CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell111 = row15.getCell(1);	//the cell from the row row15 and column B, where the number of invoice "CD" from FIRST lot has been found
						Cell cell115 = row16.getCell(1);	//the cell from the row row16 and column B, where the number of invoice "CD" from SECOND lot has been found

		//Assign the value of cell111 to the label l111 and the value of cell115 to the label l115
						
						if(r15 == 0) {
							l111.setText("");
						}
						else {
							l111.setText(cell111.getStringCellValue());
						}
						
						if(r16 == 0) {
							l115.setText("");
						}
						else {
							l115.setText(cell115.getStringCellValue());
						}
	
		//Define the cells in the sheet, corresponding to r15 and r16 and column C ("Data Factura" column), type is "CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell112 = row15.getCell(2);	//the cell from the row row15 and column C, where the date of invoice "CD" from FIRST lot has been found
						Cell cell116 = row16.getCell(2);	//the cell from the row row16 and column C, where the date of invoice "CD" from SECOND lot has been found

		//Assign the value of cell112 to the label l112 and the value of cell116 to the label l116
						
						if(r15 == 0) {
							l112.setText("");
						}
						else {
							l112.setText(cell112.getStringCellValue());
						}
						
						if(r16 == 0) {
							l116.setText("");
						}
						else {
							l116.setText(cell116.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r15 and r16 and column J ("Valoare EUR" column), type is "CD" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell113 = row15.getCell(9);	//the cell from the row row15 and column J, where the amount of invoice "CD" from FIRST lot has been found
						Cell cell117 = row16.getCell(9);	//the cell from the row row16 and column J, where the amount of invoice "CD" from SECOND lot has been found

		//Assign the value of cell113 to the label l113 and the value of cell117 to the label l117
						
						if(r15 == 0) {
							l113.setText("");
						}
						else {
							l113.setText(dc_pret_total.format((Double)cell113.getNumericCellValue()));
						}
						
						if(r16 == 0) {
							l117.setText("");
						}
						else {
							l117.setText(dc_pret_total.format((Double)cell117.getNumericCellValue()));
						}

		//Assign text to label "FacturaCDOK_firstLot" depending on the values of "Contract", and "Factura CD"
					
						if(r15 != 0) {
							if(((Double)cell37.getNumericCellValue() - (Double)cell113.getNumericCellValue()) < 2 || ((Double)cell37.getNumericCellValue() - (Double)cell113.getNumericCellValue()) > -2) {
									FacturaCDOK_firstLot.setText("<html><div style='text-align: center;'>Factura CD inchisa</div></html>");
									FacturaCDOK_firstLot.setForeground(new Color(0, 153, 0));
								}
							else {
									FacturaCDOK_firstLot.setText("<html><div style='text-align: center;'>Rest factura CD: </div></html>" + Math.round((cell37.getNumericCellValue() - cell113.getNumericCellValue())*100)/100);
									FacturaCDOK_firstLot.setForeground(Color.RED);
							}
						}
						else if (r15 == 0) {
									FacturaCDOK_firstLot.setText("<html><div style='text-align: center;'>Fara factura CD</div></html");
									FacturaCDOK_firstLot.setForeground(new Color(0, 153, 0));
						}

		//Assign text to label "FacturaCDOK_secondLot" depending on the values of "Contract", and "Factura CD"
						
						if(r2 != 0) {
							if(r16 != 0) {
								if(((Double)cell52.getNumericCellValue() - (Double)cell117.getNumericCellValue()) < 2 || ((Double)cell52.getNumericCellValue() - (Double)cell117.getNumericCellValue()) > -2) {
									FacturaCDOK_secondLot.setText("<html><div style='text-align: center;'>Factura CD inchisa</div></html>");
									FacturaCDOK_secondLot.setForeground(new Color(0, 153, 0));
								}
								else {
									FacturaCDOK_secondLot.setText("<html><div style='text-align: center;'>Rest factura CD:</div></html>" + Math.round((cell52.getNumericCellValue() - cell117.getNumericCellValue())*100)/100);
									FacturaCDOK_secondLot.setForeground(Color.RED);
								}
							}
							else if (r16 == 0) {
									FacturaCDOK_secondLot.setText("<html><div style='text-align: center;'>Fara factura CD</div></html");
									FacturaCDOK_secondLot.setForeground(new Color(0, 153, 0));
							}
						}
						else if (r2 == 0) {
								FacturaCDOK_secondLot.setText("");
						}

		//Define the rows in the sheet, corresponding to the integer number r17 and r18 (rows from "Facturi" sheet where the selected product, for type "Logistica", for the FIRST lot (respectively for SECOND lot) has been found
						
						Row row17 = sheet_facturi.getRow(r17);	//the row in "Facturi" sheet where the type is "Logistica" for FIRST lot
						Row row18 = sheet_facturi.getRow(r18);	//the row in "Facturi" sheet where the type is "Logistica" for SECOND lot

		//Define the cells in the sheet, corresponding to r17 and r18 and column B ("Factura" column), type is "Logistica" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell119 = row17.getCell(1);	//the cell from the row row17 and column B, where the number of invoice "Logistica" from FIRST lot has been found
						Cell cell123 = row18.getCell(1);	//the cell from the row row18 and column B, where the number of invoice "Logistica" from SECOND lot has been found

		//Assign the value of cell119 to the label l119 and the value of cell123 to the label l123
						
						if(r17 == 0) {
							l119.setText("");
						}
						else {
							l119.setText(cell119.getStringCellValue());
						}
						
						if(r18 == 0) {
							l123.setText("");
						}
						else {
							l123.setText(cell123.getStringCellValue());
						}
	
		//Define the cells in the sheet, corresponding to r17 and r18 and column C ("Data Factura" column), type is "Logistica" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell120 = row17.getCell(2);	//the cell from the row row17 and column C, where the date of invoice "Logistica" from FIRST lot has been found
						Cell cell124 = row18.getCell(2);	//the cell from the row row18 and column C, where the date of invoice "Logistica" from SECOND lot has been found

		//Assign the value of cell120 to the label l120 and the value of cell124 to the label l124
						
						if(r17 == 0) {
							l120.setText("");
						}
						else {
							l120.setText(cell120.getStringCellValue());
						}
						
						if(r18 == 0) {
							l124.setText("");
						}
						else {
							l124.setText(cell124.getStringCellValue());
						}
						
		//Define the cells in the sheet, corresponding to r17 and r18 and column J ("Valoare EUR" column), type is "Logistica" for the FIRST lot (respectively SECOND lot)				
						
						Cell cell121 = row17.getCell(9);	//the cell from the row row17 and column J, where the amount of invoice "Logistica" from FIRST lot has been found
						Cell cell125 = row18.getCell(9);	//the cell from the row row18 and column J, where the amount of invoice "Logistica" from SECOND lot has been found

		//Assign the value of cell121 to the label l121 and the value of cell125 to the label l125
						
						if(r17 == 0) {
							l121.setText("");
						}
						else {
							l121.setText(dc_pret_total.format((Double)cell121.getNumericCellValue()));
						}
						
						if(r18 == 0) {
							l125.setText("");
						}
						else {
							l125.setText(dc_pret_total.format((Double)cell125.getNumericCellValue()));
						}

		//Assign text to label "FacturaLogisticaOK_firstLot" depending on the values of "Contract", and "Factura Logistica"
					
						if(r17 != 0) {
							if(((Double)cell40.getNumericCellValue() - (Double)cell121.getNumericCellValue()) < 2 || ((Double)cell40.getNumericCellValue() - (Double)cell121.getNumericCellValue()) > -2) {
									FacturaLogisticaOK_firstLot.setText("<html><div style='text-align: center;'>Factura logistica inchisa</div></html>");
									FacturaLogisticaOK_firstLot.setForeground(new Color(0, 153, 0));
								}
							else {
									FacturaLogisticaOK_firstLot.setText("<html><div style='text-align: center;'>Rest factura logistica: </div></html>" + Math.round((cell40.getNumericCellValue() - cell121.getNumericCellValue())*100)/100);
									FacturaCDOK_firstLot.setForeground(Color.RED);
							}
						}
						else if (r17 == 0) {
									FacturaLogisticaOK_firstLot.setText("<html><div style='text-align: center;'>Fara factura logistica</div></html>");
									FacturaLogisticaOK_firstLot.setForeground(new Color(0, 153, 0));
						}

		//Assign text to label "FacturaLogisticaOK_secondLot" depending on the values of "Contract", and "Factura Logistica"
						
						if (r2 != 0) {
							if (r18 != 0) {
								if (((Double)cell55.getNumericCellValue() - (Double)cell125.getNumericCellValue()) < 2 || ((Double)cell55.getNumericCellValue() - (Double)cell125.getNumericCellValue()) > -2) {
									FacturaLogisticaOK_secondLot.setText("<html><div style='text-align: center;'>Factura logistica inchisa</div></html>");
									FacturaLogisticaOK_secondLot.setForeground(new Color(0, 153, 0));
								}
							
								else {
									FacturaLogisticaOK_secondLot.setText("<html><div style='text-align: center;'>Rest factura logistica:</div></html>" + Math.round((cell52.getNumericCellValue() - cell117.getNumericCellValue())*100)/100);
									FacturaLogisticaOK_secondLot.setForeground(Color.RED);
								}
							}
							else if (r18 == 0) {
									FacturaLogisticaOK_secondLot.setText("<html><div style='text-align: center;'>Fara factura logistica</div></html>");
									FacturaLogisticaOK_secondLot.setForeground(new Color(0, 153, 0));
							}
						}
						else if (r2 == 0) {
									FacturaLogisticaOK_secondLot.setText("");
						}
					}
				});
				
		//Add components to the panel
		
				panelFrame.add(selectProduct);
				panelFrame.add(l1);
				panelFrame.add(l2);
				panelFrame.add(l3);
				panelFrame.add(l4);
				panelFrame.add(l5);
				panelFrame.add(l6);
				panelFrame.add(l7);
				panelFrame.add(l8);
				panelFrame.add(l9);
				panelFrame.add(l10);
				panelFrame.add(l11);
				panelFrame.add(l12);
				panelFrame.add(l13);
				panelFrame.add(l14);
				panelFrame.add(l15);
				panelFrame.add(l16);
				panelFrame.add(line);
				panelFrame.add(l17);
				panelFrame.add(l18);
				panelFrame.add(l19);
				panelFrame.add(l20);
				panelFrame.add(l21);
				panelFrame.add(l22);
				panelFrame.add(l23);
				panelFrame.add(l24);
				panelFrame.add(l25);
				panelFrame.add(l26);
				panelFrame.add(l27);
				panelFrame.add(l28);
				panelFrame.add(l29);
				panelFrame.add(l30);
				panelFrame.add(l31);
				panelFrame.add(l32);
				panelFrame.add(l33);
				panelFrame.add(l34);
				panelFrame.add(l35);
				panelFrame.add(l36);
				panelFrame.add(l37);
				panelFrame.add(l38);
				panelFrame.add(l39);
				panelFrame.add(l40);
				panelFrame.add(l41);
				panelFrame.add(l42);
				panelFrame.add(l43);
				panelFrame.add(l44);
				panelFrame.add(l45);
				panelFrame.add(l46);
				panelFrame.add(l47);
				panelFrame.add(l48);
				panelFrame.add(l49);
				panelFrame.add(l50);
				panelFrame.add(l51);
				panelFrame.add(l52);
				panelFrame.add(l53);
				panelFrame.add(l54);
				panelFrame.add(l55);
				panelFrame.add(l56);
				panelFrame.add(l57);
				panelFrame.add(l58);
				panelFrame.add(l59);
				panelFrame.add(l60);
				panelFrame.add(l61);
				panelFrame.add(l62);
				panelFrame.add(l63);
				panelFrame.add(l64);
				panelFrame.add(l65);
				panelFrame.add(l66);
				panelFrame.add(l67);
				panelFrame.add(l68);
				panelFrame.add(l69);
				panelFrame.add(AvansTiparOK_firstLot);
				panelFrame.add(l70);
				panelFrame.add(l71);
				panelFrame.add(l72);
				panelFrame.add(l73);
				panelFrame.add(AvansTiparOK_secondLot);
				panelFrame.add(l74);
				panelFrame.add(l75);
				panelFrame.add(l76);
				panelFrame.add(l77);
				panelFrame.add(l78);
				panelFrame.add(l79);
				panelFrame.add(l80);
				panelFrame.add(l81);
				panelFrame.add(l82);
				panelFrame.add(l83);
				panelFrame.add(l84);
				panelFrame.add(l85);
				panelFrame.add(l86);
				panelFrame.add(l87);
				panelFrame.add(l88);
				panelFrame.add(TirajOK_firstLot);
				panelFrame.add(FacturaTiparOK_firstLot);
				panelFrame.add(l89);
				panelFrame.add(l90);
				panelFrame.add(l91);
				panelFrame.add(l92);
				panelFrame.add(l93);
				panelFrame.add(TirajOK_secondLot);
				panelFrame.add(FacturaTiparOK_secondLot);
				panelFrame.add(l94);
				panelFrame.add(l95);
				panelFrame.add(l96);
				panelFrame.add(l97);
				panelFrame.add(l98);
				panelFrame.add(l99);
				panelFrame.add(l100);
				panelFrame.add(l101);
				panelFrame.add(l102);
				panelFrame.add(l103);
				panelFrame.add(l104);
				panelFrame.add(l105);
				panelFrame.add(AvansCDOK_firstLot);
				panelFrame.add(l106);
				panelFrame.add(l107);
				panelFrame.add(l108);
				panelFrame.add(l109);
				panelFrame.add(AvansCDOK_secondLot);
				panelFrame.add(l110);
				panelFrame.add(l111);
				panelFrame.add(l112);
				panelFrame.add(l113);
				panelFrame.add(FacturaCDOK_firstLot);
				panelFrame.add(l114);
				panelFrame.add(l115);
				panelFrame.add(l116);
				panelFrame.add(l117);
				panelFrame.add(FacturaCDOK_secondLot);
				panelFrame.add(l118);
				panelFrame.add(l119);
				panelFrame.add(l120);
				panelFrame.add(l121);
				panelFrame.add(FacturaLogisticaOK_firstLot);
				panelFrame.add(l122);
				panelFrame.add(l123);
				panelFrame.add(l124);
				panelFrame.add(l125);
				panelFrame.add(FacturaLogisticaOK_secondLot);
				
		//Add panel to the frame
				
				add(panelFrame);
				
	}
}
