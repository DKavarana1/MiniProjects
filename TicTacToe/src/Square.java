public class Square {
	SquareVal value;
	int position;

	public Square(SquareVal value, int position) {
		super();
		this.value = value;
		this.position = position;
	}

	public SquareVal getValue() {
		return value;
	}
	public void setValue(SquareVal value) {
		this.value = value;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		
		if(obj == null) {
			return false;
		}
		
		if (!(obj instanceof Square)) {
			return false;
		}
		
		return this.getValue() == ((Square)obj).getValue();
	}
	
	public String getStringValue() {
		if(value == SquareVal.BLANK) {
			return "BLANK";
		}else if(value == SquareVal.X) {
			return "X";
		}else {
			return "O";
		}
	}
}
