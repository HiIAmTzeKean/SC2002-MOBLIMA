package moviepackage;

public enum AgeRestriction {
	GENERAL("General"),
	PG("PG"),
	PG13("PG13"),
	M18("M18"),
	R21("R21");
	private final String textRepresenatation;
    private AgeRestriction(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}