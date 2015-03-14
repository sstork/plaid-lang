We now have a [Comparable&lt;T&gt;](http://code.google.com/p/plaid-lang/source/browse/stdlib/pld/plaid/lang/Comparable.plaid) trait, which defines a single abstract method, "==" to compare the receiver to an operand of type T, returning an immutable Boolean. "==" must have a defined result for all values of type T. How equality is defined is up to the type in question, with the recommendation being for types composed of immutable parts that this be structural.

We also have an [Orderable&lt;T&gt;](http://code.google.com/p/plaid-lang/source/browse/stdlib/pld/plaid/lang/Orderable.plaid) (case of Comparable`<T>`) trait, which defines an abstract method "<" and using this and "==" from Comparable can implement all the other inequality operators (so a type need only provide "==" and "<" to be Orderable, and gets "<=", ">=" and ">" for free). Types which are Orderable must have a total ordering, i.e. "<" has a defined result for all values of type T. Orderable also provides a method "order(other)" which returns an Ordering instance, which has three cases: OrdLessThan, OrdEqual, and OrdGreaterThan. These can be used for pattern matching when this is more convenient than using chained ifElse blocks to check the cases.

When a type T is not Orderable, an [OrderProvider&lt;T&gt;](http://code.google.com/p/plaid-lang/source/browse/stdlib/pld/plaid/lang/Ordering.plaid) may be used in order to impose a total ordering on that type for the purposes of storing it in a collection that requires it.

Finally, we have Hashable, with a single method hash(), that produces an Int32 in the range 0 to (2^32)-1. Hashable combined with Comparable has the additional rule (like Java) that a == b => hash(a) == hash(b).