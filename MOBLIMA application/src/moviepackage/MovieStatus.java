package moviepackage;

public enum MovieStatus {
    PREVIEW("PREVIEW"),
    NOW_SHOWING("NOW SHOWING"),
    COMING_SOON("COMING SOON"),
    END_OF_SHOWING("END OF SHOWING");
    private final String textRepresenatation;
    private MovieStatus(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}
