package moviepackage;
public enum MovieType {
	_2D("2D"),
	_3D("3D"),
	BLOCKBUSTER("BLOCKBUSTER");
	private final String textRepresenatation;
    private MovieType(String textRepresentation){
        this.textRepresenatation = textRepresentation;
    }
    @Override
    public String toString(){
        return textRepresenatation;
    }
}