/*
 * @author man0fbass
 * 
 * The best way to get in touch with me is over Twitter. http://twitter.com/wizard0fbass
 * #SlothPower y'allz
 */

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JSplitPane;
import javax.swing.JLayeredPane;
import javax.swing.JCheckBox;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textLowest;
	private JTextField textHighest;
	private JTextField textBaseDamage;
	private JTextField textPierce;
	private JTextField txtTargetHealth;
	private final JTable tableBlades;
	private final JTable tableTraps;
	private JTextField txtTargetResist;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setTitle("Wizard101 Damage Calculator - by man0fbass");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 502);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLowest = new JLabel("Lowest Possible Damage:");
		lblLowest.setBounds(10, 8, 220, 14);
		contentPane.add(lblLowest);
		
		textLowest = new JTextField();
		textLowest.setText("65");
		lblLowest.setLabelFor(textLowest);
		textLowest.setToolTipText("This is the lowest possible damage your spell can do. (For example, if you were casting Dark Sprite, you would enter \"65\".)");
		textLowest.setBounds(262, 8, 41, 20);
		contentPane.add(textLowest);
		textLowest.setColumns(10);
		
		JLabel lblHighest = new JLabel("Highest Possible Damage:");
		lblHighest.setBounds(10, 33, 220, 14);
		contentPane.add(lblHighest);
		
		textHighest = new JTextField();
		textHighest.setText("105");
		lblHighest.setLabelFor(textHighest);
		textHighest.setToolTipText("This is the maximum BASE damage your spell can do. (ex: For Dark Sprite, you would enter \"105\".)\r\n\r\nYou can leave this empty if there is only one number. (ex: wand hits)");
		textHighest.setBounds(262, 31, 41, 20);
		contentPane.add(textHighest);
		textHighest.setColumns(10);
		
		JLabel lblBaseDamage = new JLabel("Base Damage Boost:");
		lblBaseDamage.setBounds(10, 58, 194, 14);
		contentPane.add(lblBaseDamage);
		
		textBaseDamage = new JTextField();
		textBaseDamage.setText("0");
		lblBaseDamage.setLabelFor(textBaseDamage);
		textBaseDamage.setToolTipText("This is the total damage boost from your gear.");
		textBaseDamage.setBounds(262, 56, 41, 20);
		contentPane.add(textBaseDamage);
		textBaseDamage.setColumns(10);
		
		JLabel lblPierceLabel = new JLabel("Total Pierce:");
		lblPierceLabel.setBounds(10, 84, 242, 14);
		contentPane.add(lblPierceLabel);
		
		textPierce = new JTextField();
		textPierce.setText("0");
		lblPierceLabel.setLabelFor(textPierce);
		textPierce.setToolTipText("Add your base pierce to any modifiers you may have. This includes spears, critical bubbles, Infallible, pierce enchantments, Shadow Shrike, and anything else that gives pierce.");
		textPierce.setBounds(262, 81, 41, 20);
		contentPane.add(textPierce);
		textPierce.setColumns(10);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 167, 293, 2);
		contentPane.add(separator_1);
		
		JLabel lblAddBlade = new JLabel("Blades");
		lblAddBlade.setBounds(137, 180, 73, 14);
		contentPane.add(lblAddBlade);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(10, 316, 291, 2);
		contentPane.add(separator_3);
		
		JLabel lblTraps = new JLabel("Traps and Shields");
		lblTraps.setBounds(111, 329, 129, 14);
		contentPane.add(lblTraps);
		
		JLabel lblAuras = new JLabel("Auras:");
		lblAuras.setBounds(328, 11, 41, 14);
		contentPane.add(lblAuras);
		
		final JComboBox comboAuras = new JComboBox();
		comboAuras.setModel(new DefaultComboBoxModel(new String[] {"None", "Amplify (+15%)", "Amplify TC (+20%)", "Berserk (+30%)", "Berserk TC (+35%)", "School Auras (+25%)"}));
		comboAuras.setEditable(true);
		comboAuras.setBounds(407, 8, 242, 20);
		
		contentPane.add(comboAuras);
		
		JLabel lblBubbles = new JLabel("Bubbles:");
		lblBubbles.setBounds(328, 36, 69, 14);
		contentPane.add(lblBubbles);
		
		final JComboBox comboBubbles = new JComboBox();
		comboBubbles.setModel(new DefaultComboBoxModel(new String[] {"None", "Balefrost (+35%)", "Balefrost TC (+40%)", "Brown Spider (+30%)", "Darkwind (+25%)", "Darkwind TC (+35%)", "Giant Spider (+25%)", "Ice Helephant (+35%)", "Ignite (+25%)", "Red Ghost (+25%)", "Storman (+25%)", "Time of Legend (+25%)", "Time of Legend TC (+35%)", "White Rat Magician (+25%)", "Wyldfire (+25%)", "Wyldfire TC (+35%)"}));
		comboBubbles.setEditable(true);
		comboBubbles.setBounds(407, 33, 242, 20);
		
		contentPane.add(comboBubbles);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(313, 11, 1, 442);
		contentPane.add(separator_2);
		
		JLabel lblShadow = new JLabel("Shadow:");
		lblShadow.setBounds(328, 59, 56, 14);
		contentPane.add(lblShadow);
		
		final JCheckBox chckbxShrike = new JCheckBox("Shrike");
		chckbxShrike.setBounds(338, 80, 73, 23);
		contentPane.add(chckbxShrike);
		
		final JCheckBox chckbxSentinel = new JCheckBox("Sentinel");
		chckbxSentinel.setBounds(413, 80, 97, 23);
		contentPane.add(chckbxSentinel);
		
		final JCheckBox chckbxSeraph = new JCheckBox("Seraph");
		chckbxSeraph.setBounds(508, 80, 97, 23);
		contentPane.add(chckbxSeraph);
		
		JLabel lblOther = new JLabel("Other:");
		lblOther.setBounds(328, 114, 46, 14);
		contentPane.add(lblOther);
		
		final JCheckBox chckbxFortify = new JCheckBox("Fortify");
		chckbxFortify.setBounds(338, 135, 73, 23);
		contentPane.add(chckbxFortify);
		
		final JCheckBox chckbxFortifyTc = new JCheckBox("Fortify TC");
		chckbxFortifyTc.setBounds(407, 135, 97, 23);
		contentPane.add(chckbxFortifyTc);
		
		final JCheckBox chckbxSchoolAuraDamage = new JCheckBox("School Aura");
		chckbxSchoolAuraDamage.setToolTipText("Check this if the target has a school aura up that gives you an extra 5% damage boost. (Ex: Using Ninja Pigs against a target with Galvanic Field)");
		chckbxSchoolAuraDamage.setBounds(508, 135, 141, 20);
		contentPane.add(chckbxSchoolAuraDamage);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textLowest, textHighest, textBaseDamage, textPierce, comboAuras, comboBubbles, chckbxShrike, chckbxSentinel, chckbxSeraph, chckbxFortify, chckbxFortifyTc, txtTargetHealth}));
		
		/*
		 * Only one aura can be up at a time.
		 */
		chckbxFortify.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxFortify.isSelected()) {
					chckbxFortifyTc.setSelected(false);
					chckbxSchoolAuraDamage.setSelected(false);
				}
			}
			
		});
		chckbxFortifyTc.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxFortifyTc.isSelected()) {
					chckbxFortify.setSelected(false);
					chckbxSchoolAuraDamage.setSelected(false);
				}
			}
			
		});
		chckbxSchoolAuraDamage.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(chckbxSchoolAuraDamage.isSelected()) {
					chckbxFortifyTc.setSelected(false);
					chckbxFortify.setSelected(false);
				}
			}
			
		});
		
		JLabel lblHealthOfTarget = new JLabel("Health of Target:");
		lblHealthOfTarget.setBounds(328, 202, 97, 14);
		contentPane.add(lblHealthOfTarget);
		
		txtTargetHealth = new JTextField();
		txtTargetHealth.setBounds(424, 199, 86, 20);
		contentPane.add(txtTargetHealth);
		txtTargetHealth.setColumns(10);
		
		JButton btnCalculate = new JButton("Calculate");
		btnCalculate.setFont(new Font("Tahoma", Font.PLAIN, 30));
		btnCalculate.setBounds(389, 230, 200, 50);
		contentPane.add(btnCalculate);
		
		tableBlades = new JTable();
		tableBlades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableBlades.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(50), null, null, null},
				{new Integer(-25), null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"", "", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableBlades.getColumnModel().getColumn(0).setResizable(false);
		tableBlades.setCellSelectionEnabled(true);
		tableBlades.setBounds(10, 205, 293, 97);
		contentPane.add(tableBlades);
		
		tableTraps = new JTable();
		tableTraps.setModel(new DefaultTableModel(
			new Object[][] {
				{new Integer(70), null, null, null},
				{new Integer(-50), null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"", "", "", ""
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableTraps.getColumnModel().getColumn(0).setResizable(false);
		tableTraps.getColumnModel().getColumn(1).setResizable(false);
		tableTraps.getColumnModel().getColumn(2).setResizable(false);
		tableTraps.getColumnModel().getColumn(3).setResizable(false);
		tableTraps.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableTraps.setCellSelectionEnabled(true);
		tableTraps.setBounds(10, 356, 293, 97);
		contentPane.add(tableTraps);
		
		final JCheckBox chckbxProtectedBySentinel = new JCheckBox("Protected by Sentinel");
		chckbxProtectedBySentinel.setBounds(338, 161, 172, 23);
		contentPane.add(chckbxProtectedBySentinel);
		
		JLabel lblTargetResist = new JLabel("Target Resist:");
		lblTargetResist.setBounds(10, 109, 129, 14);
		contentPane.add(lblTargetResist);
		
		txtTargetResist = new JTextField();
		txtTargetResist.setText("0");
		txtTargetResist.setBounds(262, 109, 41, 20);
		contentPane.add(txtTargetResist);
		txtTargetResist.setColumns(10);
		
		final JLabel lblOutput = new JLabel("Your output will appear here!");
		lblOutput.setVerticalAlignment(SwingConstants.TOP);
		lblOutput.setBounds(328, 329, 321, 14);
		contentPane.add(lblOutput);
		
		final JLabel lblOutputCrit = new JLabel("And here!");
		lblOutputCrit.setVerticalAlignment(SwingConstants.TOP);
		lblOutputCrit.setBounds(328, 354, 321, 14);
		contentPane.add(lblOutputCrit);
		
		final JLabel lblHealthOutput = new JLabel("And here too!");
		lblHealthOutput.setBounds(328, 379, 321, 14);
		contentPane.add(lblHealthOutput);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(328, 316, 321, 2);
		contentPane.add(separator);
		

		
		btnCalculate.addActionListener(new ActionListener() {
			
			/*
            	This is where the magic happens! :D
                It's super disorganized right now, I plan to change this eventually and make it more Java-ish with classes.
                
                @params
					lowest			the lowest possible damage value
					highest			the highest possible damage value
					pierce			the total pierce involved in the attack
					baseDamage		the attacker's damage modifier
					targetResist	the target's resist modifier
					blades			an ArrayList of all the blade modifiers
					traps			an ArrayList of all the trap/shield modifiers
					aura			the modifier of the attacker's aura, if any
					bubble			the modifier of the bubble in play, if any
					shadow
						- 0: Shadow Shrike modifier (attacker)
						- 1: Shadow Sentinel/Seraph modifier (target)
					other
						- 0: Target's current aura
						- 1: target is protected by sentinel
            */
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int lowest=0;
				int highest=0;
				double pierce = 0;
				double baseDamage=0;
				double targetResist=0;
				double targetHealth=-1;
				ArrayList<Integer> blades=new ArrayList<Integer>();
				ArrayList<Integer> traps=new ArrayList<Integer>();
				double aura;
				double bubble;
				ArrayList<Double> shadow=new ArrayList<Double>();
				ArrayList<Double> other=new ArrayList<Double>();
				
				/*
				 * Gets all the data into variable form
				 */
				
                if(!textLowest.getText().equals("")) lowest=Integer.parseInt(textLowest.getText());
				if(!textHighest.getText().equals("")) highest=Integer.parseInt(textHighest.getText());
				else highest=lowest; //single damage attack
				if(!textPierce.getText().equals("")) pierce=Double.parseDouble(textPierce.getText())/100.0;
				if(!textBaseDamage.getText().equals("")) baseDamage=Double.parseDouble(textBaseDamage.getText())/100.0;
				if(!txtTargetResist.getText().equals("")) targetResist=Double.parseDouble(txtTargetResist.getText())/100;
				if(!txtTargetHealth.getText().equals("")) targetHealth=Double.parseDouble(txtTargetHealth.getText());
				
				blades=getTableData(tableBlades);
			    traps=getTableData(tableTraps);
				
			    aura=getAuraPercent((String) comboAuras.getSelectedItem());
			    bubble=getBubblePercent((String) comboBubbles.getSelectedItem());
			    
			    targetResist*=-1;		//makes it so the resist value works in the pierce algorithm
			    
			    //shadow damage mods
			    if(chckbxShrike.isSelected()) shadow.add(0.1);
			    else shadow.add(0.0);
			    
			    //target shadow form
			    if(chckbxSentinel.isSelected()) shadow.add(-0.1);
			    else if(chckbxSeraph.isSelected()) shadow.add(0.25);
			    else shadow.add(0.0);
			    
			    //other damage mods
			    
			    //target's aura
			    if(chckbxFortify.isSelected()) other.add(-0.15);
			    else if(chckbxFortifyTc.isSelected()) other.add(-0.2);
			    else if(chckbxSchoolAuraDamage.isSelected()) other.add(0.05);
			    else other.add(0.0);
			    
			    //target guarded by sentinel
			    if(chckbxProtectedBySentinel.isSelected()) other.add(-0.25);
			    else other.add(0.0);
			    
			    
			    Collections.reverse(traps);		//traps are reversed in terms of damage
                
                //get traps and blades values in percent form
                ArrayList<Double> bladesD=new ArrayList<Double>();
                ArrayList<Double> trapsD=new ArrayList<Double>();

                for(int i=0; i<blades.size(); i++)
                	bladesD.add(blades.get(i) / 100.0);
                    
			    for(int i=0; i<traps.size(); i++)
                	trapsD.add(traps.get(i) / 100.0);
			    
			    
			    
                /*
                 * Damage calculations
                 */
                
			    final double[] unroundedDamage=new double[2];
			    unroundedDamage[0]=lowest/1.0;
			    unroundedDamage[1]=highest/1.0;
                
                /*
                
                TODO:
                	Research:
                    - Outgoing damage order: gear, blades, aura, shadow
                    - Incoming damage order: traps/shields, aura, shadow, gear, bubble
                    - shadow sentinel intercept cannot be pierced, happens after all other mods have resolved
                    - pierce order: shields as applied, then fortify if applied, then gear resist
                 */
                
			    //OUTGOING DAMAGE
			    
                //gear boost
			    unroundedDamage[0]=factorDamage(unroundedDamage[0], baseDamage);
            	unroundedDamage[1]=factorDamage(unroundedDamage[1], baseDamage);
			    
                //apply blades
			    for(int i=0; i<bladesD.size(); i++) {
			    	unroundedDamage[0]=factorDamage(unroundedDamage[0], bladesD.get(i));
			    	unroundedDamage[1]=factorDamage(unroundedDamage[1], bladesD.get(i));
                }
			    //apply damage aura
			    unroundedDamage[0]=factorDamage(unroundedDamage[0], aura);
			    unroundedDamage[1]=factorDamage(unroundedDamage[1], aura);
            	
			    //shrike buff
			    unroundedDamage[0]=factorDamage(unroundedDamage[0], shadow.get(0));
			    unroundedDamage[1]=factorDamage(unroundedDamage[1], shadow.get(0));
                
                
            	
            	//INCOMING DAMAGE
            	
			    //apply traps, pierce shields where applicable
                for(int i=0; i<trapsD.size(); i++) {
                	if(pierce>0.0 && trapsD.get(i)<0) {		//if there's pierce left and the ward is a shield
                		double tempShield=trapsD.get(i);
                        if(pierce>=trapsD.get(i) * -1) {				//if the remaining pierce is greater than/equal to the current shield
                			trapsD.set(i, 0.0);						//nullify the shield
                			pierce+=tempShield;						//remove the pierce used
                		}
                        else {									//if pierce < shield
                        	trapsD.set(i, tempShield+pierce);		//use rest of pierce on the shield
                            pierce=0.0;								//remove all pierce
                        }
                	}
                	
                	unroundedDamage[0]=factorDamage(unroundedDamage[0], trapsD.get(i));
                	unroundedDamage[1]=factorDamage(unroundedDamage[1], trapsD.get(i));
               	}
                
                //target's aura
                if(other.get(0)<0 && pierce>0) {	//if the aura is Foritfy and there's still pierce after shields
                	double temp=other.get(0);
                	if(pierce>=temp * -1) {
                		other.set(0, 0.0);
                		pierce+=temp;
                	}
                	else {
                		other.set(0, temp+pierce);
                		pierce=0.0;
                	}
                }
                
                unroundedDamage[0]=factorDamage(unroundedDamage[0], other.get(0));
                unroundedDamage[1]=factorDamage(unroundedDamage[1], other.get(0));
                
                //target's shadow form
                //Sentinel CAN be pierced!
                if(shadow.get(1)<0 && pierce>0) {	//if the shadow form is Sentinel and there's still pierce after shields & aura
                	double temp=other.get(0);
                	if(pierce>=temp * -1) {
                		other.set(0, 0.0);
                		pierce+=temp;
                	}
                	else {
                		other.set(0, temp+pierce);
                		pierce=0.0;
                	}
                }
                unroundedDamage[0]=factorDamage(unroundedDamage[0], shadow.get(1));
                unroundedDamage[1]=factorDamage(unroundedDamage[1], shadow.get(1));
                
                //gear resist
                if(targetResist>0 && pierce>0) {	//if the target has resist and there's still pierce after shields, aura, and shadow
                	double temp=other.get(0);
                	if(pierce>=temp * -1) {
                		targetResist=0.0;
                		pierce+=temp;
                	}
                	else {
                		targetResist+=pierce;
                		pierce=0.0;
                	}
                }
                unroundedDamage[0]=factorDamage(unroundedDamage[0], targetResist);
                unroundedDamage[1]=factorDamage(unroundedDamage[1], targetResist);
                
                //damage bubble
                unroundedDamage[0]=factorDamage(unroundedDamage[0], bubble);
                unroundedDamage[1]=factorDamage(unroundedDamage[1], bubble);
                
                //protected by sentinel
                //To my knowledge, intercept cannot be pierced.
                unroundedDamage[0]=factorDamage(unroundedDamage[0], other.get(1));
                unroundedDamage[1]=factorDamage(unroundedDamage[1], other.get(1));
                
                double lowCrit=unroundedDamage[0] * 2;
                double highCrit=unroundedDamage[1] * 2;
                
                String output = unroundedDamage[0] + " - " + unroundedDamage[1];
                String critOutput = "(Crit: " + lowCrit + " - " + highCrit + ")";
                String healthOutput = "";
                
                if(!(targetHealth==-1)) {
	                if(unroundedDamage[0]>=targetHealth) healthOutput = "The target will die without a crit.";		//if lowest possible damage will reduce health to 0 or less
	                else if(targetHealth>unroundedDamage[0] && targetHealth<=unroundedDamage[1]) healthOutput="The target might die without a crit.";
	                else if(targetHealth>unroundedDamage[1] && targetHealth<=lowCrit) healthOutput="The target will die if you crit.";
	                else if(targetHealth>lowCrit && targetHealth<=highCrit) healthOutput="The target might die if you crit.";
	                else healthOutput="The target won't die, even if you crit.";
                }
                
                lblOutput.setText(output);
                lblOutputCrit.setText(critOutput);
                lblHealthOutput.setText(healthOutput);
			}
			public double factorDamage(double damage, double mod) {
                return damage+(damage*mod);
			}
		});
	}
	
	//utility methods
	
	/*
	 * Returns an ArrayList of the table contents
	 */
	public ArrayList<Integer> getTableData (JTable table) {
	    DefaultTableModel dtm = (DefaultTableModel) table.getModel();
	    ArrayList<Integer> list=new ArrayList<Integer>();
	    int nRow = dtm.getRowCount(), nCol = dtm.getColumnCount();
	    for (int i = 0 ; i < nRow ; i++) {
	        for (int j = 0 ; j < nCol ; j++) {
	        	Integer cellValue=(Integer) dtm.getValueAt(i, j);
	        	if(cellValue!=null) list.add(cellValue);
	        	else list.add(0);
	        }
	    }
	    return list;
	}
	
	/*
	 * Returns the aura modifier
	 */
	public double getAuraPercent(String boxText) {
		if(boxText.equals("Amplify (+15%)")) return 0.15;
		if(boxText.equals("Amplify TC (+20%)")) return 0.2;
		if(boxText.equals("Berserk (+30%)")) return 0.3;
		if(boxText.equals("Berserk TC (+35%)")) return 0.35;
		if(boxText.equals("School Auras (+25%)")) return 0.25;
		return 0;
	}
	
	/*
	 * Returns the bubble modifier
	 */
	public double getBubblePercent(String str) {
		if(str.equals("Balefrost (+35%)")) return 0.35;
		if(str.equals("Balefrost TC (+40%)")) return 0.4;
		if(str.equals("Brown Spider (+30%)")) return 0.3;
		if(str.equals("Darkwind (+25%)")) return 0.25;
		if(str.equals("Darkwind TC (+30%)")) return 0.3;
		if(str.equals("Giant Spider (+25%)")) return 0.25;
		if(str.equals("Ice Helephant (+35%)")) return 0.35;
		if(str.equals("Ignite (+25%)")) return 0.25;
		if(str.equals("Red Ghost (+25%)")) return 0.25;
		if(str.equals("Storman (+25%)")) return 0.25;
		if(str.equals("Time of Legend (+25%)")) return 0.25;
		if(str.equals("Time of Legend TC (+35%)")) return 0.35;
		if(str.equals("White Rat Magician (+25%)")) return 0.25;
		if(str.equals("Wyldfire (+25%)")) return 0.25;
		if(str.equals("Wyldfire TC (+35%)")) return 0.35;
		return 0;
	}
}
