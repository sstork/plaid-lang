/**
 * Copyright (c) 2010 The Plaid Group (see AUTHORS file)
 * 
 * This file is part of Plaid Programming Language.
 *
 * Plaid Programming Language is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 *  Plaid Programming Language is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Plaid Programming Language.  If not, see <http://www.gnu.org/licenses/>.
 */
 
package plaid.parser.ast;

import java.util.List;

import plaid.parser.Token;

public abstract class Decl extends ASTNode implements DeclOrStateOp{
	
	protected final Identifier name;
	protected final List<Modifier> modifiers;
	
	public Decl(Token t, List<Modifier> modifiers, Identifier name) {
		super(t);
		this.modifiers = modifiers;
		this.name = name;
	}
	
	public Identifier getName() {
		return this.name;
	}
	
	public boolean equivalent(ASTNode other) {
		if(other instanceof Decl) {
			Decl otherDecl = (Decl)other;
			for(int i=0; i<modifiers.size();i++) {
				if(!modifiers.get(i).equivalent(otherDecl.modifiers.get(i))) {
					return false;
				}
			}
			return name.equivalent(otherDecl.name);
		} else {
			return false;
		}
	}
}
