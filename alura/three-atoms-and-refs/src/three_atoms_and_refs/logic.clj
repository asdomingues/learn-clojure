(ns three-atoms-and-refs.logic
  (:use [clojure pprint]))

(defn arrival
  [stage id]
  (if (seq id)
    (recur (conj stage (first id)) (rest id))
    stage))

(defn arrival-by-location
  [location stage id]
  (update location stage conj id))

(def vaccine-queue (arrival
                    clojure.lang.PersistentQueue/EMPTY
                    (reverse (range 25 30))))

(pprint vaccine-queue)
(println (peek vaccine-queue))
