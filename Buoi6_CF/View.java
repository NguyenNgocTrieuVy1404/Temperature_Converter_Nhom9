package Buoi6_CF;

import java.util.Observable;
import java.util.Observer;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class View extends JFrame implements Observer{
    //private Control controlRemote = new Control();
    private Model modelRemote;
    private JPanel jPanelRemote;
    private JLabel jLabelRemoteInput1, jLabelRemoteInput2;
    private JTextField jTextFieldInput1Remote, jTextFieldInput2Remote;
    private JMenuBar menuBarRemote = null;
    private MenuController menuCotrollerRemote;
    private CommandProcessor commandProcessorRemote;
    private EnterController enterControllerRemote;

    public void setModelRemote(Model modelRemote) {
        this.modelRemote = modelRemote;
        this.modelRemote.addObserver(this);
    }
    
    public void setCommandProcessorRemote(CommandProcessor commandProcessorRemote) {
        this.commandProcessorRemote = commandProcessorRemote;
    }

    View()
    {
        menuCotrollerRemote = new MenuController();
        enterControllerRemote = new EnterController();
        //commandProcessorRemote = CommandProcessor.makeCommandProcessor();
        buildMenu();
        buildPanel();
        add(jPanelRemote);
        setTitle("Temperature Converter");
        setSize(400,400);
        setVisible(true);
        setJMenuBar(menuBarRemote);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    
    public void buildPanel(){
        jPanelRemote = new JPanel();
        jLabelRemoteInput1 = new JLabel("Celsius: ");
        jPanelRemote.add(jLabelRemoteInput1);
        jTextFieldInput1Remote = new JTextField(10);
        jPanelRemote.add(jTextFieldInput1Remote);
        jTextFieldInput1Remote.addKeyListener(enterControllerRemote);
        jLabelRemoteInput2 = new JLabel("Fahrenheit: ");
        jPanelRemote.add(jLabelRemoteInput2);
        jTextFieldInput2Remote = new JTextField(10);
        jPanelRemote.add(jTextFieldInput2Remote);
        jTextFieldInput2Remote.addKeyListener(enterControllerRemote);

    }

    public void buildMenu(){
        menuBarRemote = new JMenuBar();
        JMenu calJMenuRemote = new JMenu("Commands");
        JMenuItem f2CRemote = new JMenuItem("F2C");
        f2CRemote.addActionListener(menuCotrollerRemote);
        JMenuItem c2FRemote = new JMenuItem("C2F");
        c2FRemote.addActionListener(menuCotrollerRemote);
        JMenuItem exitRemote = new JMenuItem("Exit");
        exitRemote.addActionListener(menuCotrollerRemote);
        calJMenuRemote.add(f2CRemote);
        calJMenuRemote.add(c2FRemote);
        calJMenuRemote.add(exitRemote);

        menuBarRemote.add(calJMenuRemote);
    }
    class MenuController implements ActionListener{
        
        @Override
        public void actionPerformed(ActionEvent e) {
            
            Command commandRemote = null;
            
            String command = e.getActionCommand();        
            if (command.equals("C2F")) {
                double C = Double.parseDouble(jTextFieldInput1Remote.getText());
                commandRemote = new C2F(modelRemote, 0, C);
                commandProcessorRemote.execute(commandRemote);
            }
            else if (command.equals("F2C")) {
                double F = Double.parseDouble(jTextFieldInput2Remote.getText());
                commandRemote = new F2C(modelRemote, F, 0);
                commandProcessorRemote.execute(commandRemote); 
            } else{
                System.exit(ABORT);
            }           
        }
    }  
    class EnterController implements KeyListener {
        
        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }
        
        public void keyPressed(KeyEvent e) {
            int command = e.getExtendedKeyCode();
            if (command == KeyEvent.VK_ENTER) {
                Command commandRemote = null;
                if (e.getSource() == jTextFieldInput1Remote) {
                    double C = Double.parseDouble(jTextFieldInput1Remote.getText());
                    commandRemote = new C2F(modelRemote, 0, C);
                    commandProcessorRemote.execute(commandRemote);
                } else if (e.getSource() == jTextFieldInput2Remote) {
                    double F = Double.parseDouble(jTextFieldInput2Remote.getText());
                    commandRemote = new F2C(modelRemote, F, 0);
                    commandProcessorRemote.execute(commandRemote);
                }
            }
        }
    }
    @Override
    public void update(Observable o, Object arg) {
        
            double result = modelRemote.getResult();
            if (jTextFieldInput1Remote.getText().isEmpty()) { 
                jTextFieldInput1Remote.setText("" + result);
            } else if (jTextFieldInput2Remote.getText().isEmpty()){
                jTextFieldInput2Remote.setText("" + result);
            }
            if (jTextFieldInput1Remote.hasFocus()) {
                jTextFieldInput2Remote.setText("" + result);
            } else if (jTextFieldInput2Remote.hasFocus()) {
                jTextFieldInput1Remote.setText("" + result);
            }
    }
}