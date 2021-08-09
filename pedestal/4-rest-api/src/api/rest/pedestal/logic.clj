(ns api.rest.pedestal.logic)

(defn make-list [nm]
  {:name  nm
   :items {}})

(defn make-list-item [nm]
  {:name  nm
   :done? false})
