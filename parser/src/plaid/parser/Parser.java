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
 
package plaid.parser;


import java.io.InputStream;

import plaid.parser.ast.CompilationUnit;
import plaid.parser.ast.Expr;

public class Parser {
	private CompilationUnit cu;
	private ParseException error;
	
	public Parser(InputStream toParse, int tabSize) {
		try {
			PlaidCoreParser pp = new PlaidCoreParser(toParse);
			pp.setTabSize(tabSize);
			cu = pp.CompilationUnit();	
		} catch (ParseException e) {
			cu = null;
			error = e;
		}
	}

	public boolean hasCompilationUnit() {
		return !(cu == null);
	}
	
	public CompilationUnit getCompilationUnit() {
		return cu;
	}
	
	public String getParseErrorMessage() {
		if ( this.error == null ) {
			return "";
		} else {
		    return this.error.toString();	
		}
	}
	
	public Token getToken() {
		if ( error.currentToken == null ) {
			return Expr.DEFAULT_TOKEN;
		} else {
			return error.currentToken;
		}
	}
}