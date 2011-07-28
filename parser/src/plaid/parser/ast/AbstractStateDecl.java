package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public final class AbstractStateDecl extends StateDecl {

	public AbstractStateDecl(Token t, List<Modifier> modifiers,
			Identifier name, List<MetaArgSpec> metaArgsSpec,
			QualifiedIdentifier caseOf, List<Expression> metaCaseOfArgs) {
		super(t, modifiers, name, metaArgsSpec, caseOf, metaCaseOfArgs);
	}

}