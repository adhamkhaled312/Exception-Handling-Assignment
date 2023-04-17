public class Containers implements Comparable<Containers>{
    private String ID;
    private String longName;
    private String shortName;

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getID() {
        return ID;
    }

    public String getLongName() {
        return longName;
    }

    public String getShortName() {
        return shortName;
    }
    //We need to override compareTo method so we can order the containers array
    @Override
    public int compareTo(Containers s){
        if(this.getShortName().charAt(9)>s.getShortName().charAt(9)){
            return 1;
        }
        if(this.getShortName().equals(s.getShortName())){
            return 0;
        }
        else{
            return -1;
        }
    }

    @Override
    public String toString() {
        return "<CONTAINER "+ ID + ">\n" +
                "   <SHORT-NAME>"+shortName+"</SHORT-NAME>\n"+
                "    <LONG-NAME>"+longName+"</LONG-NAME>\n"
                +"</CONTAINER>\n";
    }
}