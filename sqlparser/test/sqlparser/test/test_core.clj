(ns sqlparser.test.test-core
  (:use sqlparser.core
        clojure.test
        midje.sweet))

(unfinished parse )

(fact "it generates the java parser given a grammar"
         (cleanup-generated)
         (generate-parser "Expr.g")
        (contains? generated-dir
