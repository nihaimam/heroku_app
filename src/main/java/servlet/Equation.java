public class Equation {
	
	private boolean state;
	private String name;
	private int weight;
	
	public Equation (String s, boolean b, int i) {
		name = s;
		state = b;
		weight = i;
	}
	
	public String getName() { return name; }
	public boolean getState() { return state; }
	public void setState() { state = !state; }
	public int getWeight() { return weight; }
	
	@Override
	public String toString() { return name; }

}
