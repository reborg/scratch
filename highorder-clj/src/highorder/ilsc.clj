(ns highorder.ilsc
  (:require [clojure.core.reducers :as r])
  (:refer-clojure :exclude [map filter reduce reverse]))

(defn split [coll f]
  "Split takes a collection and splits that in the middle if the collection
  is composed by an even number of items. It then applies the supplied function f
  to the subsequences."
    (let [middle (/ (count coll) 2)]
      (f (take middle coll) (drop middle coll))))

;;(defn mapreduce [f g id coll]
;;  "The condition for the singleton list is necessary because
;;  split accepts to split a one element list into a one element list
;;  forever."
;;  (cond (empty? coll) id
;;        (= 1 (count coll)) (f (first coll))
;;        :else (split coll (fn [coll1 coll2] (g (mapreduce f g id coll1) (mapreduce f g id coll2))))))

(defn mapreduce [f g id coll]
  (cond (empty? coll) id
        (<= (count coll) 10) (clojure.core/reduce g id (r/map f coll))
        :else (split coll (fn [coll1 coll2] (g (mapreduce f g id coll1) (mapreduce f g id coll2))))))

(defn map [f coll]
  "map is defined in terms of mapreduce. The idea is to apply f
  to each element and wrap that in a list. The reduction is just
  concatting that single element into a bigger and bigger list
  until all the elements can be returne as a mapped list over f.
  Since map is based on mapreduce which is based on split, we've got the same
  opportunity of parallelism at the price of a non-optimal way of mapping."
  (mapreduce #(list (f %)) concat '() coll))

(defn reduce [g id coll]
  "Reduce over mapreduce just need to neutralize the action of the mapping
  function f. For that you can mapreducing over the identity."
  (mapreduce identity g id coll))

(defn length [coll]
  "This way of counting is typical of map reduce processes.
  Assign one to each element and count them up."
  (mapreduce (constantly 1) + 0 coll)) 

(defn filter [p coll]
  "Filter over mapreduce is going to split the collection to the bottom
  and produce list with the accepted element or an empty list which is then
  grouped back together in a single list at the end, effectively producing
  garbage lists at leaves. The general tradeoff is that whenever you need to neutralize
  the action of the reducing part (because this is a map/filter thing) then you produce
  garbage just for the reduce operation to remove the garbage."
  (mapreduce #(if (p %) (list %) '()) concat '() coll))

(defn reverse [coll]
  "Reversing is like mapping f as a list wrapping and concatenating
  in reverse. It is producing garbage lists at the bottom to concat
  scalar elements together"
  (mapreduce list #(concat %2 %1) '() coll))
