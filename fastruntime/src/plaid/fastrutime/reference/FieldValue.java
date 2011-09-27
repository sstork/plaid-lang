package plaid.fastrutime.reference;

public final class FieldValue extends MemberValue {

	public FieldValue(String name) {
		super(name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FieldValue) {
			return this.getName().equals(((FieldValue)obj).getName());
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "val " + getName();
	}
}