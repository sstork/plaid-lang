package plaid.fastruntime.reference;

import plaid.fastruntime.FieldInfo;
import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.ObjectValue;
import fj.data.List;

public final class FieldValue extends MemberValue implements FieldInfo {

	private final boolean settable;
	private final String canonicalRep;
	
	public FieldValue(boolean settable, String name, String classInternalName) {
		super(name, classInternalName);
		this.settable = settable;
		canonicalRep = this.constructCanonicalRep();
	}
	
	@Override
	public String toString() {
		return "val " + getName();
	}

	@Override
	public List<MethodInfo> getMethods() {
		return List.nil();
	}

	@Override
	public List<FieldInfo> getFields() {
		return List.single((FieldInfo) this);
	}

	@Override
	public int compareTo(FieldInfo o) {
		return  this.getName().compareTo(o.getName());
	}

	public boolean isSettable() {
		return settable;
	}
	
	@Override
	public ObjectValue rename(String currentName, String newName) {
		if(this.getName().equals(currentName)) {
			return new FieldValue(this.settable, newName, this.getStaticClassInternalName());
		} else {
			return this;
		}
	}

	@Override
	protected String constructCanonicalRep() {
		String settableString = settable ? "t" : "f";
		String result =  "field:" + settableString + this.getName()+ this.getStaticClassInternalName();
		return result.intern();
	}
	
	@Override
	public String getCanonicalRep() {
		return canonicalRep;
	}
}
