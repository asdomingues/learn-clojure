(ns fifth-class)

; hash map
(def value Object)
(def backpack {"item" value})
(println backpack)

; idiomatic key naming
(def idiomatic-backpack {:item value})
(def backpack-schema
  (assoc idiomatic-backpack
         :unit-price (class 10.0)
         :quantity (class 10N)))

; keys or values
(println "Either"
 "keys:" (keys backpack-schema)
 "or values:" (vals backpack-schema))

; shallow hash map update
(def idiomatic-sample {:unit-price 10.0
                       :quantity   10N})
(println "Update property quantity:"
 (update idiomatic-sample :quantity inc))

; MORE COMPLEX HASH MAP OPERATIONS
(def storage {:backpack idiomatic-sample})
(println "quantity in backapack in storage:" (:quantity (:backpack storage)))
(println "update deep inside:" (update-in storage [:backpack :quantity] inc))

; threading first
(println (-> storage
             :backpack
             :quantity))
