# highorder

Highorder is just the exercise of building higher abstraction list operations on top of car, cdr and cons which in Clojure is first, rest and conj/cons respectively. I then used other primitives like item, list, split and conc to implement the same. The latter set of primitives results in higher memory storage requirements but allows effective parallelism. The list of list operations built here is:

* Map
* Reduce
* Mapreduce
* Filter
* Reverse
* Lenght

## Reference

I experimented with this while watching [Foldr/Foldl considered slightly harmful](vimeo.com/6624203) talk by Guy Steele.
