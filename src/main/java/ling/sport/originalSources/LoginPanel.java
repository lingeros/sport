package ling.sport.originalSources;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.UIManager;

public class LoginPanel {
    private Color nblue = new Color(56, 87, 118);
    private static AdminOper adminOper = new AdminOper();
    private static MainPanel mainPanel =new MainPanel();
    private JFrame loginFrame;
    private JPanel loginPane;
    private JLabel commandLaybel;
    private JLabel titleLaybel;
    private JPasswordField passwordTXTField;
    private JButton enterButton;
    private JButton closeButton;

    private static LoginPanel loginPanelInstance = null;

    private LoginPanel() {

    }

    public static LoginPanel getInstance() {
        if (loginPanelInstance == null) {
            loginPanelInstance = new LoginPanel();
        }
        return loginPanelInstance;

    }

    public void login() {
        loginPanelInstance.setUIFont();
        loginFrame = new JFrame("登陆");
        loginPane = new JPanel();
        commandLaybel = new JLabel("口令:");
        titleLaybel = new JLabel("智能体能考核接收机");
        passwordTXTField = new JPasswordField();
        enterButton = new JButton("登陆");
        closeButton = new JButton("退出");
        loginFrame.setLayout(null);
        loginPane.setLayout(null);

        loginFrame.setBounds(0, 0, 380, 180);
        loginPane.setBounds(0, 0, 380, 180);
        loginPane.setBackground(nblue);
        commandLaybel.setBounds(20, 70, 60, 20);
        titleLaybel.setBounds(80, 30, 200, 20);
        commandLaybel.setFont(new Font("", 1, 20));
        titleLaybel.setFont(new Font("", 1, 20));
        commandLaybel.setForeground(Color.white);
        titleLaybel.setForeground(Color.white);
        passwordTXTField.setBounds(80, 70, 200, 30);
        enterButton.setBounds(180, 118, 100, 30);
        closeButton.setBounds(320, 0, 60, 20);

        loginPane.add(enterButton);
        loginPane.add(closeButton);
        loginPane.add(commandLaybel);
        loginPane.add(titleLaybel);
        loginPane.add(passwordTXTField);
        loginFrame.add(loginPane);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);
        loginFrame.setResizable(false);
        loginFrame.setUndecorated(true);
        loginFrame.setVisible(true);
        enterButton.addActionListener(e -> {
            String s = String.valueOf(passwordTXTField.getPassword());
            SurperAdminOper surperAdminOper = SurperAdminOper.getInstance();

            if (surperAdminOper.select(s) || adminOper.select(s)) {
                mainPanel.mainpane();
                loginFrame.dispose();
                loginFrame = null;

            } else {
                mainPanel.RemindPgSelect("       口令错误");
            }

        });
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginFrame.dispose();
                loginFrame = null;
                System.exit(0);
            }
        });

    }

    private void setUIFont() {
        Font f = new Font("微软雅黑", Font.PLAIN, 11);
        String[] names = {"Label", "CheckBox", "PopupMenu", "MenuItem", "CheckBoxMenuItem", "JRadioButtonMenuItem",
                "ComboBox", "Button", "Tree", "ScrollPane", "TabbedPane", "EditorPane", "TitledBorder", "Menu",
                "TextArea", "OptionPane", "MenuBar", "ToolBar", "ToggleButton", "ToolTip", "ProgressBar", "TableHeader",
                "Panel", "List", "ColorChooser", "PasswordField", "TextField", "Table", "Label", "Viewport",
                "RadioButtonMenuItem", "RadioButton", "DesktopPane", "InternalFrame"};
        for (String item : names) {
            UIManager.put(item + ".font", f);
        }
    }


}
