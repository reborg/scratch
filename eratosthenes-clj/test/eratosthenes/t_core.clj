(ns eratosthenes.t-core
  (:use midje.sweet)
  (:use [eratosthenes.core]))

(facts "filtering all the primes up to a certain number"
       (fact "all primes up to 121"
             (last (prime [] (range 2 10))) => 7))

(facts "prime number generator model"
       (fact "gimme the first 10"
             (last (take 10 (primes))) => 29))

(facts "progressive filters"
       (fact "find out how to invoke the thing"
             (((prog-filter mod) cons) 2 (range 2 10)) => ""))


;; the problem is removing numbers that are multiple of something different than 1 and themselves
;; it is a filter operation on the initial set of number
;; it is not reduce because removing the next number in the list does not depend on the rest of results of the previous iteration
;; so using reducers in this case is essentially wrong, no "reduce" is logical in this case
;; the article is not showing any reduce example at the end, because there isn't really an operation for that
