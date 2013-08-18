(ns eratosthenes.core)

(defn prime
  [primes seed]
  (if-let [prim (first seed)]
    (recur 
      (conj primes prim) 
      (remove #(zero? (mod % prim)) seed)) 
    primes))

(defn lazy-sieve [s]
  (cons (first s)
        (lazy-seq (lazy-sieve (remove #(zero? (mod % (first s))) (rest s))))))

(defn primes []
  (lazy-sieve (iterate inc 2)))

(defn prog-filter [f]
  (let [flt (atom [])]
    (fn [f1]
      (fn [result input]
        (if (not-any? #(f input %) @flt)
          (do
            (swap! flt conj input)
            (f1 result input))
          result)))))
