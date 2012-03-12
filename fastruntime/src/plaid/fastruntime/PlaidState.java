package plaid.fastruntime;

/**
 * A PlaidState is functional. All methods return new objects.  
 */
public interface PlaidState {
	
	public abstract PlaidState with (PlaidState other);
	
	public abstract PlaidObject instantiate();
	
	public abstract PlaidObject[] getStorage();
	
	public abstract PlaidState remove(String member);
	
	public abstract PlaidState rename(String from, String to);
	
	public abstract PlaidState specialize(PlaidState other);

	public abstract PlaidState change(PlaidState s);
	
	public abstract String getTopTag();
	
	public abstract boolean matches(String tag);
	
	public abstract ObjectValue getObjectValue();
}
