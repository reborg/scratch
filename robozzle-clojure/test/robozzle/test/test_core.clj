(ns robozzle.test.test-core
  (:use robozzle.core
        clojure.test
        midje.sweet))

(unfinished route)

(fact "it always win if there are no stars"
      (robozzle "^" route) => truthy)
