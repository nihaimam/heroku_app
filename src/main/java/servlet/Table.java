import java.util.ArrayList;

public class Table {

	ArrayList<Equation> variables = new ArrayList<>();
	ArrayList<Object> equation = new ArrayList<>();
	ArrayList<Object> temp;
	ArrayList<Object> retlist = new ArrayList<>();
	String operator;

	public Table(ArrayList<Equation> var, ArrayList<Object> eq, String s) {
		variables = var;
		equation = eq;
		operator = s;
	}

	public ArrayList<Object> constructTable() {
		int size = variables.size();
		for (int i = 1; i < Math.pow(2, size) + 1; i++) {
			for (int j = size - 1; j >= 0; j--) {
				if (i % variables.get(j).getWeight() == 1) { variables.get(j).setState(); }
				if (variables.get(j).getWeight() == 1) { variables.get(j).setState(); }
				retlist.add((variables.get(j).getState() ? 1 : 0));
			}
			parse();
			//if (!parse()) { return new ArrayList<>(); }				
		}
		return retlist;
	}

	public void invert(int pos){
		if ((Integer) temp.get(pos) == 0) { temp.set(pos, 1); }
		else { temp.set(pos, 0); }
	}

	public void or(int left, int right){
		if ((Integer) temp.get(left) == 1 || (Integer) temp.get(right) == 1) { temp.set(right, 1); }
		else { temp.set(right, 0); }
	}
	
	public void and(int left, int right){
		if ((Integer) temp.get(left) == 1 && (Integer) temp.get(right) == 1) { temp.set(right, 1); }
		else { temp.set(right, 0); }
	}
	
	public void xor(int left, int right){
		if ((Integer) temp.get(left) == 1 && (Integer) temp.get(right) == 0) { temp.set(right, 1); }
		else if ((Integer) temp.get(left) == 0 && (Integer) temp.get(right) == 1) { temp.set(right, 1); }
		else { temp.set(right, 0); }
	}

	public boolean isInt(int pos){
		return (temp.get(pos) instanceof Integer);
	}


	public boolean parse() {
		temp = new ArrayList<>(equation);
		for (int k = 0; k < temp.size(); k++) {
			if (temp.get(k) instanceof Equation) {
				temp.set(k, ((Equation)temp.get(k)).getState() ? 1 : 0);
			}
		}
		for (int idx = 0; idx < temp.size(); idx++) {
			if (temp.get(idx) instanceof String){
				if (temp.get(idx).equals('!') && isInt(idx + 1)) { invert(idx + 1); }
				else if ((!(idx == temp.size() - 1)) && isInt(idx - 1)) {
					if (isInt(idx + 1)){
						if (operator.equals("or")) { or(idx - 1, idx + 1); }
						else if (operator.equals("and")) { and(idx - 1, idx + 1); }
						else if (operator.equals("xor")) { xor(idx - 1, idx + 1); }
					}
					else if (temp.get(idx + 1).equals('!') && isInt(idx + 2)) {
						invert(idx + 2);
						if (operator.equals("or")) { or(idx - 1, idx + 1); }
						else if (operator.equals("and")) { and(idx - 1, idx + 1); }
						else if (operator.equals("xor")) { xor(idx - 1, idx + 1); }
					}
				}
				else { return false; }
			}
			else if (isInt(idx) && idx < temp.size() - 1 && isInt(idx + 1)){
				return false;
			}
		}
		retlist.add(temp.get(temp.size() - 1));
		return true;
	}
}
