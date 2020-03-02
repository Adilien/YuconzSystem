package authApp;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import authApp.PD.*;

/**
This is the App Controller, essentially the main class for the Application that brings everything together.
*The frames are created from here, and the main method is here as well.
*/
public class AppController {
	private User session;
	private static LoginDisplay loginFrame;
	private static MainDisplay mainFrame;
	private static myPdWindow myPdFrame;
	private static GetDetails myDetails;
	
	/**
	* Launch the application.
	*/
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 loginFrame = new LoginDisplay();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Attempts to login and creates a Log of this
	 */
    public static void logIn(String uName, String pwd) {
    	Auth auth = new Auth();
    	if(auth.checkValidUser(uName, pwd)) {
    		try {
    			auth.logAttempt(uName, pwd, true);
    			AppController.loginFrame.remove();
				mainFrame = new MainDisplay();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else {
    		auth.logAttempt(uName, pwd, false);
    		JOptionPane.showMessageDialog(null,
    		    "Username or Password is incorrect.",
    		    "Warning",
    		    JOptionPane.WARNING_MESSAGE);
    	}
    }
    /**
     * Logs out the User and closes the System
     */
    public static void logOut() {
    	System. exit(0);
    }
    /**
     * Generates Personal Details Frame and Populates it
     * @param id
     */
    public static void generatePd(int id) {
    	
    	boolean checker;
    	
    	myDetails = new GetDetails(id);
    	if(myDetails.checkDb(id)) {
    		System.out.println("I am in Checker if statement");
    		myDetails = new GetDetails(id);	//If not here breaks everything??
    		myDetails.pushDetails(id);
    		checker = true;
    		System.out.println("I am in Checker if statement");
    	}else {
    		checker = false;
    		JOptionPane.showMessageDialog(null,
        		    "Your Personal Details have not yet been created by the HR Team",
        		    "Error",
        		    JOptionPane.ERROR_MESSAGE);
        }
    	myPdFrame = new myPdWindow(id);
    	myPdFrame.setVis(true);
    	if(checker) {
    		myPdFrame.setVis(true);
    	}

    }


}
