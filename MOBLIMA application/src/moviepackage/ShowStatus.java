package moviepackage;

public enum ShowStatus {
    NOW_SHOWING("Now Showing"),
    COMING_SOON("Coming Soon"),
    PREVIEW("Preview");
    private final String textRepresenatation;
    private ShowStatus(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}