(ns highorder.t-reducers
  (:use midje.sweet)
  (:require [highorder.reducers :as c]))

(facts "high order operations built with reducers instead"
       (fact "map"
             (c/map #(* % %) (range 1000)) => (contains '(1 4 9)))
       (fact "reduce"
             (c/reduce + (range 10000)) => 49995000)
       (fact "length"
             (c/length (range 100000)) => 100000)
       (fact "filter"
             (c/filter odd? (range 1000)) => (contains '(1 3)))
       (fact "reversing"
             (take 4 (c/reverse (range 1000))) => '(999 998 997 996)))
