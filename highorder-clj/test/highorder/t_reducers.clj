(ns highorder.t-reducers
  (:use midje.sweet)
  (:require [highorder.reducers :as c]))

(facts "high order operations built on top of reducers instead"
       (fact "map"
             (time (c/map #(* % %) (vec (range 100000)))) => (contains '(1 4 9)))
       (fact "reduce"
             (time (c/reduce + (vec (range 100000)))) => 4999950000)
       (fact "length"
             (time (c/length (vec (range 1000000)))) => 1000000)
       (fact "filter"
             (time (c/filter odd? (vec (range 100000)))) => (contains '(1 3))))
       ;;(fact "reversing"
       ;;      (time (c/reverse (vec (range 10000)))) => '(9999 9998 9997 9996)))
