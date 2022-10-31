package moviepackage;

public enum ShowStatus {
    NOW_SHOWING("NOW SHOWING"),
    COMING_SOON("COMING SOON"),
    PREVIEW("PREVIEW");
    private final String textRepresenatation;
    private ShowStatus(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}