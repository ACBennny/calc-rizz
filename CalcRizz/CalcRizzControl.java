package CalcRizz;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Label;
import java.io.File;
import java.text.DecimalFormat;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*************************************************************************
 * The Controller class for the CalcRizz Calculator.
 *
 * @author (Anyanwu Bendict Chukwuemeka)
 * @version (version One[1])
 ************************************************************************/
public class CalcRizzControl
{
    // Variables
    @FXML
    private Label display_input;
    @FXML
    private Label display_output;
    @FXML
    private Label display_warn;
    @FXML
    private ToggleGroup gradeTypeRadio;
    @FXML
    private MediaPlayer introSoundPlayer;
    @FXML
    private MediaPlayer btnSoundPlayer;
    @FXML
    private MediaPlayer equalToSoundPlayer;

    /***************************************
     * Method Name: initialize
     * Purpose: Initialiases values, varaibles, files, etcetera
     ***************************************/
    @FXML
    private void initialize()
    {
        
        // Loading the sound file for button press/click
        String btnSoundFile = "CalcRizz/src/btnClickSound.mp3";
        Media btnSoundMedia = new Media(new File(btnSoundFile).toURI().toString());
        btnSoundPlayer = new MediaPlayer(btnSoundMedia);
        
        // Loading the sound file for equal to "=" button press/click
        String equalToSoundFile = "CalcRizz/src/equalToClickSound.mp3";
        Media equalToSoundMedia = new Media(new File(equalToSoundFile).toURI().toString());
        equalToSoundPlayer = new MediaPlayer(equalToSoundMedia);
        
        // Plays the sound once app runs
        String introSoundFile = "CalcRizz/src/calcRizzIntro.mp3";
        Media introSoundMedia = new Media(new File(introSoundFile).toURI().toString());
        introSoundPlayer = new MediaPlayer(introSoundMedia);
        
        introSoundPlayer.play();
    }
    
    /***************************************
     * Method Name: displayUserInput
     * Purpose: This will take the value of the button clicked
     *          and display it on the display_input Label
     ***************************************/
    @FXML
    private void displayUserInput(ActionEvent event)
    {
        // Stops sound if playing before
        btnSoundPlayer.stop();
        
        // Plays sound
        btnSoundPlayer.play();
        
        // Get the button that was clicked
        Button operandBtn = (Button) event.getSource();
        
        // Locate specific button by checking class name
        if(operandBtn.getStyleClass().contains("operand_btn"))
        {
            // Get the text of the clicked button
            String operandBtnText = operandBtn.getText();
            
            // Get the current text of the display label
            String curDisplayText = display_input.getText();
            
            // Check if the button text is a number
            if (Character.isDigit(operandBtnText.charAt(0)))
            {
                // Append text without space
                display_input.setText(curDisplayText + operandBtnText);
            } 
            else
            {
                // Append text with space
                display_input.setText(curDisplayText + " " + operandBtnText + " ");
            }
        }
    }
    
    /***************************************
     * Method Name: clearDisplay
     * Purpose: Clears the display by removing all text in 
     *          the three lines if there is any content inside
     ***************************************/
    @FXML
    private void clearDisplay() 
    {
        // Stops sound if playing before
        btnSoundPlayer.stop();
        
        // Plays sound
        btnSoundPlayer.play();
        
        // Clear text in display_input
        if(display_input.getText().length() > 0)
        {
            display_input.setText("");
        }
        
        // Clear text in display_output
        if(display_output.getText().length() > 0)
        {
            display_output.setText("");
        }
        
        // Clear text in display_warn
        if(display_warn.getText().length() > 0)
        {
            display_warn.setText("");
        }
    }
    
    /***************************************
     * Method Name: numberOperations
     * Purpose: Takes the user input, performs an
     *          operation, and displays the result
     ***************************************/
    @FXML
    private void numberOperations()
    {
        // Stops sound if playing before
        equalToSoundPlayer.stop();
        
        // Plays sound
        equalToSoundPlayer.play();
        
        // Get the text from the display_input Label
        String expression = display_input.getText();
        
        // Split the expression to get the operands and the operator
        String[] components = expression.split("\\s+");
        
        // Check if it is a valid expression
        // Valid expression contains an operator and two operands
        if(components.length == 3)
        {
            // Operand 1
            int operand1 = Integer.parseInt(components[0]);
            
            // Operand 2
            int operand2 = Integer.parseInt(components[2]);
            
            // Variable to hold the result of the operation
            int result = 0;
            
            // Get the radio button group for the grades (i.e. grade 4, 5 and 6)
            RadioButton pickedRadBtn = (RadioButton) gradeTypeRadio.getSelectedToggle();
            
            // Get the id of the radio buttons in the group
            String pickedRadBtnId = pickedRadBtn.getId();
            
            // Check if a radio button in the group is selected
            if(pickedRadBtn != null)
            {
                // Perform the operation based on the operator
                switch (components[1])
                {
                    // Addition Operator
                    case "+":
                        // Calculate the operation as it works for all grades
                        result = operand1 + operand2;
                        
                        // Display the result in the display_output Label
                        display_output.setText(String.valueOf(result));
                        break;
                    
                    // Multiplication Operator
                    case "×":
                        // Calculate the operation as it works for all grades
                        result = operand1 * operand2;
                        
                        // Display the result in the display_output Label
                        display_output.setText(String.valueOf(result));
                        
                        break;
                    
                    // Subtraction Operator
                    case "-":
                        // Grade 4 only works if operand is greater than/equal to operand2
                        if(pickedRadBtnId.equals("grade4Rad"))
                        {
                            // Check if operand1 is greater than/equal to than operand2
                            if(operand1 >= operand2)
                            {
                                // Calculate the operation
                                result = operand1 - operand2;
                                
                                // Display the result
                                display_output.setText(String.valueOf(result));
                            }
                            else
                            {
                                // Displays error message
                                display_warn.setText(String.valueOf("ERROR"));
                                
                                // Clear Ouput line
                                display_output.setText(String.valueOf(""));
                                
                                return;
                            }
                        }
                        // Grade 5 and 6 subtracts normally
                        else
                        {
                            result = operand1 - operand2;
                            
                            // Display the result in the display_output Label
                            display_output.setText(String.valueOf(result));
                            
                            // Clear the error message
                            display_warn.setText(String.valueOf(""));
                        }
                        
                        break;
                    
                    // Division Operator
                    case "÷":
                        // Check which grade radio button is selected
                        // For grade 4, can only work without remainder
                        if(pickedRadBtnId.equals("grade4Rad"))
                        {
                            // Check if number divides without remainder
                            if(operand1 % operand2 == 0)
                            {
                                // Calculate division
                                result = operand1 / operand2;
                                
                                // Display the result
                                display_output.setText(String.valueOf(result));
                            }
                            else
                            {
                                // Display error message
                                display_warn.setText(String.valueOf("ERROR"));
                                
                                // Remove any value in output line
                                display_output.setText(String.valueOf(""));
                                
                                return;
                            }
                        }
                        // For grade 5 use quotient and remainder
                        else if(pickedRadBtnId.equals("grade5Rad"))
                        {
                            // Calculate Quotient
                            int quotient = (int) (operand1 / operand2);
                            
                            // Calculate Remainder
                            int remainder = (int) (operand1 % operand2);
                            
                            // Store Result
                            String g5Result = quotient + " R " + remainder;
                            
                            // Display result
                            display_output.setText(String.valueOf(g5Result));
                            
                            // Remove error message
                            display_warn.setText(String.valueOf(""));
                            
                            return;
                        }
                        // For grade 6, display result to 4 decimal places
                        else
                        {                            
                            // Converting operands to double
                            double op1 = (double) operand1;
                            double op2 = (double) operand2;
                            
                            // Calculating division
                            double g6Operation = op1 / op2;
                            
                            // Rounding to 4 decimal places
                            DecimalFormat g6Cond = new DecimalFormat("0.0000");
                            String g6Conv = g6Cond.format(g6Operation);
                            
                            // Display Result
                            display_output.setText(String.valueOf(g6Conv));
                            
                            //Remove error
                            display_warn.setText(String.valueOf(""));
                        }
                        break;
                        
                    default:
                        // If none of the operators appear this error message appears
                        display_warn.setText(String.valueOf("ERROR :/"));
                        
                        // Remove any value in output line
                        display_output.setText(String.valueOf(""));
                        
                        break;
                }
            }
            // If the none of the radio buttons are picked
            // this error message appears
            else
            {
                display_warn.setText(String.valueOf("ERROR :/"));
                
                // Remove any value in output line
                display_output.setText(String.valueOf(""));
            }
        }
        // If operator is invalid this error message appears
        else
        {
            display_warn.setText(String.valueOf("ERROR :/"));
            
            // Remove any value in output line
            display_output.setText(String.valueOf(""));
        }
    }
}
