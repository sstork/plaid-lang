package plaid.fastruntime.reference;

import plaid.fastruntime.MethodInfo;
import plaid.fastruntime.PlaidStorage;
import fj.Ord;
import fj.data.List;
import fj.data.Set;

/**
 * Parent and innerValue can both be null.
 * @author jssunshi
 *
 */
public final class DimensionValue extends SingleValue {
	private final String tag;
	private final AbstractObjectValue innerValue;
	private final DimensionValue parent;
	
	public DimensionValue(String tag, AbstractObjectValue innerValue,
			DimensionValue parent) {
		super();
		this.tag = tag;
		this.parent = parent;
		this.innerValue = innerValue;
	}

	public String getTag() {
		return tag;
	}

	public DimensionValue getParent() {
		return parent;
	}

	public AbstractObjectValue getInnerValue() {
		return innerValue;
	}
	
	@Override
	public Set<String> getTags() {
		Set<String> currentTags = Set.single(Ord.stringOrd, tag);
		if(innerValue != null) {
			currentTags = currentTags.union(innerValue.getTags());
		}
		if(parent == null) {
			return currentTags;
		} else {
			return currentTags.union(parent.getTags());
		}
	}
	
	public DimensionValue withoutParent() {
		return new DimensionValue(this.tag, this.innerValue, null);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof DimensionValue) {
			DimensionValue dv = (DimensionValue)obj;
			return (tag.equals(dv.tag) && 
					((innerValue == null && dv.innerValue == null) || innerValue.equals(dv.innerValue)) &&
					((parent == null && dv.parent == null) || parent.equals(dv.parent)));
		} else {
			return false;
		}
	}

	@Override
	public Set<String> getOuterTags() {
		Set<String> tagSet = Set.single(Ord.stringOrd, tag);
		if(parent != null) {
			return parent.getOuterTags().union(tagSet);
		} else {
			return tagSet;
		}
	}
	
	@Override
	public Set<String> getInnerTags() {
		Set<String> tagSet = Set.empty(Ord.stringOrd);
		if(parent != null) {
			tagSet = parent.getInnerTags().union(tagSet);
		} 
		if (innerValue != null) {
			tagSet = innerValue.getTags();
		}
		return tagSet;
	}
	
	@Override
	public boolean uniqueTags() {
		//TODO: Fix this, this is wrong!
		return true;
	}
	
	@Override
	public String toString() {
		String toReturn = tag;
		if(innerValue != null) {
			toReturn = toReturn + "{" + innerValue.toString() + "}";
		}
		if (parent != null) {
			toReturn = toReturn + "<:" + parent.toString();
		}
		return toReturn;
	}

	@Override
	public List<MethodInfo> getMethods() {
		List<MethodInfo> mi = List.nil();
		if(innerValue!=null) {
			mi.append(innerValue.getMethods());
		}
		if(parent!=null) {
			mi.append(parent.getMethods());
		}
		return mi;
	}

	@Override
	public PlaidStorage getDefaultStorage() {
		// TODO Auto-generated method stub
		return null;
	}
}
