(ns sisth-class)

; MORE COMPLEX HASH MAP OPERATIONS
(def storage {:1 {:name "backpack", :weight 8}
              :2 {:name "wallet", :weight 0.1}})

; destructuring
(defn package-weight [[_ properties]] (:weight properties))
(println "normal: "(reduce + 0 (map package-weight storage)))

(defn total-weight
  [storages]
  (->> storages
       (map package-weight)
       (reduce + 0)))
(println "thread last:" (total-weight storage))

(defn lightweight?
  [properties]
  (<= (:weight properties) 1))

(println "Lightweight storages:" (->> storage
                                      vals
                                      (filter lightweight?)
                                      (map :name)))

