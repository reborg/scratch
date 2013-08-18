(ns highorder.ccc
  (:refer-clojure :exclude [map filter reduce]))

(defn map [f coll]
  (if (empty? coll)
    coll
    (cons (f (first coll)) (map f (rest coll)))))

(defn reduce [g id coll]
  "Note that here you can decide if the accumulator is going to be the first
  arg for g or the second."
  (if (empty? coll)
    id
    (g  (reduce g id (rest coll)) (first coll))))

(defn mapreduce [f g id coll]
  "Mapreduce is a reduce over coll with g with an additional
  mapping on the single element with f"
  (if (empty? coll)
    id
    (g (f (first coll)) (mapreduce f g id (rest coll)))))

(defn length [coll]
  (mapreduce (constantly 1) + 0 coll)) 

(defn filter-1 [p coll]
  (cond (empty? coll) coll
        (p (first coll)) (cons (first coll) (filter-1 p (rest coll)))
        :else (filter-1 p (rest coll))))

(defn filter-2 [p coll]
  (apply concat (map #(if (p %) (list %) nil) coll)))

(defn filter-3 [p coll]
  "The order with which mapreduce is applying the reducing function is crucial
  here, resulting in an inverted result list."
  (mapreduce #(if (p %) (list %) nil) concat '() coll))
