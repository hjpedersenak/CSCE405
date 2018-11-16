/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 /*
 * This is the application of the rules generated from the ID3. I changed some of the syntax from the rules because
 * java doesn't accept the code generated from ID3. However, the rules should work the same nonetheless.
 *  This is the original rules
if( Physician-fee-freeze == "n") { 
  Party = "democrat"; 
} else if( Physician-fee-freeze == "y") { 
 if( Adoption-of-the-budget-resolution == "y") { 
  if( Anti-satellite-test-ban == "n") { 
    Party = "democrat"; 
  } else   if( Anti-satellite-test-ban == "y") { 
    Party = "republican"; 
  } 
 } else  if( Adoption-of-the-budget-resolution == "n") { 
   Party = "republican"; 
 } 
} 
 * The one I changed is in function "processData"
 * 
 *
 *
 * Most of the code in here is adopted from ID3. 
 */
import java.util.*;
import java.io.*;

public class RulesFromID3 {

    private int PhysicianFeeFreezeIndex, AdoptionOfTheBudgetResolutionIndex, AntiSatelliteTestBanIndex, PartyIndex;
    private int numAttributes;

    private String[] attributeNames;
    private int correctGuessTotal = 0, incorrectGuessTotal = 0;
    private int totalSample = 0;

    /**
     * Function to read the data file. The first line of the data file should
     * contain the names of all attributes. The number of attributes is inferred
     * from the number of words in this line. The last word is taken as the name
     * of the output attribute. Each subsequent line contains the values of
     * attributes for a data point. If any line starts with // it is taken as a
     * comment and ignored. Blank lines are also ignored.
     */
    public int readData(String filename) /* throws Exception */ {

        FileInputStream in = null;

        try {
            File inputFile = new File(filename);
            in = new FileInputStream(inputFile);
        } catch (Exception e) {
            System.err.println("Unable to open data file: " + filename + "\n" + e);
            return 0;
        }

        BufferedReader bin = new BufferedReader(new InputStreamReader(in));

        String input = null;
        while (true) { // Read attributes
            try {
                input = bin.readLine();
            } catch (Exception e2) {
                System.err.println("Error reading data file.");
                System.exit(0);
            }

            if (input == null) {
                System.err.println("No data found in the data file: " + filename + "\n");
                return 0;
            }
            if (input.startsWith("//")) {
                continue;
            }
            if (input.equals("")) {
                continue;
            }
            break;
        }

        StringTokenizer tokenizer = new StringTokenizer(input);
        numAttributes = tokenizer.countTokens();
        if (numAttributes <= 1) {
            System.err.println("Read line: " + input);
            System.err.println("Could not obtain the names of attributes in the line");
            System.err.println("Expecting at least one input attribute and one output attribute");
            return 0;
        }

        attributeNames = new String[numAttributes];
        getAttributesIndexes(tokenizer);

        while (true) { // read value of each attribute on each row
            try {
                input = bin.readLine();
            } catch (Exception e3) {
                System.err.println("Error reading data file.");
                System.exit(0);
            }

            if (input == null) {
                break;
            }
            if (input.startsWith("//")) {
                continue;
            }
            if (input.equals("")) {
                continue;
            }

            tokenizer = new StringTokenizer(input);
            int numtokens = tokenizer.countTokens();
            if (numtokens != numAttributes) {
                System.err.println("Last line read: " + input);
                System.err.println("Expecting " + numAttributes + " attributes");
                return 0;
            }

            String[] rowValue = new String[numAttributes];
            for (int i = 0; i < numAttributes; i++) {
                rowValue[i] = tokenizer.nextToken();
            }
            processData(rowValue);
        }

        try {
            bin.close();
        } catch (Exception e4) {
            System.out.println("Error closing file.");
            System.exit(0);
        }

        return 1;

    }

    public void report() {
        System.out.println("Rules ran through " + totalSample + " sample data.");
        double percentage = incorrectGuessTotal * 100 / totalSample;
        System.out.println("Percentage of incorrect guess: " + percentage + "%");
    }

    public void processData(String[] rowValue) {
        String PhysicianFeeFreeze = rowValue[PhysicianFeeFreezeIndex],
                AdoptionOfTheBudgetResolution = rowValue[AdoptionOfTheBudgetResolutionIndex],
                AntiSatelliteTestBan = rowValue[AntiSatelliteTestBanIndex];

        String Party = "";
        String PartyFromTestFiles = rowValue[PartyIndex];

        if (PhysicianFeeFreeze.equals("n")) {
            Party = "democrat";
        } else if (PhysicianFeeFreeze.equals("y")) {
            if (AdoptionOfTheBudgetResolution.equals("y")) {
                if (AntiSatelliteTestBan.equals("n")) {
                    Party = "democrat";
                } else if (AntiSatelliteTestBan.equals("y")) {
                    Party = "republican";
                }
            } else if (AdoptionOfTheBudgetResolution.equals("n")) {
                Party = "republican";
            }
        }

        totalSample++;

        Boolean correctGuess = PartyFromTestFiles.equalsIgnoreCase(Party);

        if (correctGuess) {
            correctGuessTotal++;
        } else {
            incorrectGuessTotal++;
            System.out.println("Incorrect Classification! Predicted: " + Party
                    + " Data: " + PartyFromTestFiles);
        }

    }

    private void getAttributesIndexes(StringTokenizer tokenizer) {
        for (int i = 0; i < numAttributes; i++) {
            attributeNames[i] = tokenizer.nextToken();

            if (attributeNames[i].equals("Physician-fee-freeze")) {
                PhysicianFeeFreezeIndex = i;
            } else if (attributeNames[i].equals("Adoption-of-the-budget-resolution")) {
                AdoptionOfTheBudgetResolutionIndex = i;
            } else if (attributeNames[i].equals("Anti-satellite-test-ban")) {
                AntiSatelliteTestBanIndex = i;
            } else if (attributeNames[i].equals("Party")) {
                PartyIndex = i;
            }
        }
    }

    public static void main(String[] args) {
        RulesFromID3 result = new RulesFromID3();
        result.readData("test-house-votes-1984.txt");
        result.report();

    }
}
