package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class AbstractMethodDecl extends MethodDecl {

	public AbstractMethodDecl(Token t, List<Modifier> modifiers, Type type,
			Identifier name, List<MetaArgSpec> metaArgsSpec,
			List<Arg> arguments, List<Arg> env) {
		super(t, modifiers, type, name, metaArgsSpec, arguments, env);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("method ");
		if ( getType() != Type.EMPTY ) sb.append(getType().toString()+ " ");
		sb.append(getName());
		if ( getMetaArgsSpec().size() > 0) sb.append(metaArgSpecsToString(getMetaArgsSpec()));
		sb.append("(");
		if ( getArguments().size() > 0 ) argsToString(getArguments());
		sb.append(")");
		sb.append(";");
		return sb.toString();
	}
}