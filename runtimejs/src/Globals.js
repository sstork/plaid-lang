//True and False states
var plaidNewState_True = new Object();
var plaidNewState_False = new Object();
function returnTrue() {return true;}
function returnFalse() {return false;}
plaidNewState_True.instantiate = returnTrue;
plaidNewState_False.instantiate = returnFalse;

//Array state
var plaidNewState_Array = new Object();
function returnArray() {return [];}
plaidNewState_Array.instantiate = returnArray;
//Anything declared in Plaid as an Array should be able to use all the methods available to JavaScript arrays
Array.prototype.get = function(i){
	return this[i];
}

//if function
var if$plaid = function(bool) {
	if (bool===true) {
		var plaidNewVar4 = function(func) {
			var plaidNewVar5 = func();
			return plaidNewVar5;
		};
		var returnVal=plaidNewVar4;
	}
	else if (bool===false) {
		var plaidNewVar6 = function(func) {
			return undefined;
		};
		var returnVal=plaidNewVar6;
	}
	return returnVal;
};

//ifElse function
var ifElse = function(bool) {
	if (bool===true) {
		var plaidNewVar8 = function(trueClause) {
			var plaidNewVar9 = function(falseClause) {
				var plaidNewVar10 = trueClause();
				return plaidNewVar10;
			};
			return plaidNewVar9;
		};
		var returnVal = plaidNewVar8;
	}
	else if (bool===false) {
		var plaidNewVar11 = function(trueClause) {
			var plaidNewVar12 = function(falseClause) {
				var plaidNewVar13 = falseClause();
				return plaidNewVar13;
			};
			return plaidNewVar12;
		};
		var returnVal = plaidNewVar11;
	}
	return returnVal;
};

