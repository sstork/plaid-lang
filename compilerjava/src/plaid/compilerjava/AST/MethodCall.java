package plaid.compilerjava.AST;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import plaid.compilerjava.coreparser.Token;
import plaid.compilerjava.tools.ASTVisitor;
import plaid.compilerjava.util.CodeGen;
import plaid.compilerjava.util.IDList;
import plaid.compilerjava.util.IdGen;

public class MethodCall implements Expression {

	private final Token token;
	private final List<Expression> arguments = new ArrayList<Expression>();
	private final Expression receiver;
	private final ID method;
	
	public MethodCall(Token token, Expression receiver, ID method, Expression argument) {
		this.token = token;
		this.arguments.add(argument);
		this.receiver = receiver;
		this.method = method;
	}
	
	public MethodCall(Token token, Expression receiver, ID method, List<Expression> arguments) {
		this.token = token;
		this.arguments.addAll(arguments);
		this.receiver = receiver;
		this.method = method;
	}

	public List<Expression> getArguments() {
		return Collections.unmodifiableList(arguments);
	}

	public Expression getReceiver() {
		return receiver;
	}
	
	public ID getMethod() {
		return method;
	}

	@Override
	public void codegenExpr(CodeGen out, ID y, IDList localVars, Set<ID> stateVars) {
		out.setLocation(token);
		
		// Evaluate the receiver.
		ID o = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, o.getName());
		receiver.codegenExpr(out, o, localVars, stateVars);
		
		// Lookup the method.
		ID m = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, m.getName());
		out.assignToLookup(m.getName(), CodeGen.convertOpNames(method.getName()), o.getName());
		
		// Evaluate the argument.
		ID a = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, a.getName());
		Expression argument = plaid.compilerjava.coreparser.PlaidCoreParser.foldToPairs(arguments);
		argument.codegenExpr(out, a, localVars, stateVars);
		
		// Call the method.
		ID p = IdGen.getId();
		out.declareFinalVar(CodeGen.plaidObjectType, p.getName());
		out.assignToCall(y.getName(), m.getName(), a.getName());
	}

	@Override
	public Token getToken() {
		return token;
	}

	@Override
	public <T> T accept(ASTVisitor<T> visitor) {
		return visitor.visitNode(this);
	}

	@Override
	public boolean hasToken() {
		return token != null;
	}

	@Override
	public <T> void visitChildren(ASTVisitor<T> visitor) {
		receiver.accept(visitor);
		method.accept(visitor);
		for (Expression argument : arguments)
			argument.accept(visitor);
	}
}
