package authenticationsystem;

public class PassRemover {
    
    private String subString1;
    private String subString2;
    private String fixedLine;
    private int  firstQuote;
    private int  secondQuote;
     
    
    // constructor method
    public PassRemover () {
        subString1 = "";
        subString2 = "";
        fixedLine = "";
        firstQuote = 0;
        secondQuote = 0;
                
    }
    
    /* this method removes the plaintext password from each line read from the
   credential file so that the a password cannot be entered as a valid
   username */
    
    public String getNewScanLine (String fixedLine) {
        firstQuote = fixedLine.indexOf('"');
        secondQuote = fixedLine.lastIndexOf('"') + 1;
        subString1 = fixedLine.substring(0, firstQuote);
        subString2 = fixedLine.substring(secondQuote, fixedLine.length());
        fixedLine = subString1 + subString2;
        return fixedLine;
    }
    
}
