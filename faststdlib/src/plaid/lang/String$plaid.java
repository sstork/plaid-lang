package plaid.lang;

import plaid.fastruntime.ObjectValue;
import plaid.fastruntime.PlaidJavaObject;
import plaid.fastruntime.PlaidObject;
import plaid.fastruntime.errors.PlaidIllegalArgumentException;
import plaid.fastruntime.reference.AbstractPlaidState;
import plaid.fastruntime.reference.SimplePlaidJavaObject;
import plaid.generated.IendsWith$1$plaid;
import plaid.generated.Ieqeq$plaid$1$plaid;
import plaid.generated.Iplus$plaid$1$plaid;
import plaid.generated.IstartsWith$1$plaid;
import plaid.generated.Isubstring$2$plaid;
import plaid.generated.ItoLowerCase$0$plaid;

public class String$plaid extends AbstractPlaidState
						  implements Iplus$plaid$1$plaid,
						  			 Ieqeq$plaid$1$plaid,
						  			 IstartsWith$1$plaid,
						  			 IendsWith$1$plaid,
						  			 ItoLowerCase$0$plaid,
						  			 Isubstring$2$plaid
{

	public String$plaid(ObjectValue metadata) {
		super(metadata);
		// TODO Auto-generated constructor stub
	}

	public PlaidObject plus$plaid(PlaidObject receiver, PlaidObject other) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) other).getJavaObject());
			java.lang.String concat = first + second;
			return new SimplePlaidJavaObject(this, null, concat);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("string concatenation failed", e.getCause());
		}
	}

	@Override
	public PlaidObject substring(PlaidObject receiver, PlaidObject arg1,
			PlaidObject arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidObject toLowerCase(PlaidObject receiver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidObject endsWith(PlaidObject receiver, PlaidObject arg) {
		try {
			java.lang.String first = ((java.lang.String) ((PlaidJavaObject) receiver).getJavaObject());
			java.lang.String second = ((java.lang.String) ((PlaidJavaObject) arg).getJavaObject());
			boolean concat = first.endsWith(second);
			return new SimplePlaidJavaObject(this, null, concat);
			
		} catch (Exception e) {
			throw new PlaidIllegalArgumentException("string concatenation failed", e.getCause());
		}
	}

	@Override
	public PlaidObject startsWith(PlaidObject receiver, PlaidObject arg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PlaidObject eqeq$plaid(PlaidObject receiver, PlaidObject arg) {
		// TODO Auto-generated method stub
		return null;
	}

}
