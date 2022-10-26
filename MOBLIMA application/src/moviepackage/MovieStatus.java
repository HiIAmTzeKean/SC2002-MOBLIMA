package moviepackage;

public enum MovieStatus {
    NOW_SHOWING("NOW SHOWING"),
    COMING_SOON("COMING SOON");
    private final String textRepresenatation;
    private MovieStatus(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}
