(ns logic-prog.core
  (:require [clojure.test :refer (deftest are run-tests)]
            [clojure.core.logic :refer (run == conde)]
            [clojure.core.logic.fd :as fd])
  (:refer-clojure :exclude [==]))

(defn play1 [res vs]
  (if-let [solution
           (first
            (run 1 [op x y]
                 (fd/in x (apply fd/domain vs))
                 (fd/in y (apply fd/domain vs))
                 (fd/distinct [x y])
                 (conde
                  [(fd/+ x y res) (== op '+)]
                  [(fd/- x y res) (== op '-)]
                  [(fd/* x y res) (== op '*)])))]
    (apply list solution)))

(play1 1 [1 2])
