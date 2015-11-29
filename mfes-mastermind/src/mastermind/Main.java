/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package mastermind;

import gameinterface.Play;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.co.csk.vdm.toolbox.VDM.CGException;

/**
 *
 * @author joaoqalves
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        int selection = -1;

        do {

            System.out.println("----- MASTERMIND -----");
            System.out.println("[1] Quick Game");
            System.out.println("[2] New Championship");
            System.out.println("[0] Exit");

            System.out.print("Selection: ");
            
            try {

                Scanner scanner = new Scanner (System.in);
                selection = scanner.nextInt();

            }
            catch(InputMismatchException e) {

                System.err.println("Please enter a valid option");
                continue;

            }



            switch(selection) {

                case 1:
                    Play.quickGame();
                    break;
                case 2:
                    try {
                        Play.championship();
                    } catch (CGException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.err.println("Please enter a valid option");

            }



        }while(selection != 0);
        
    }

}
