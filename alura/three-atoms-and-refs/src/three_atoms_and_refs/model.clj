(ns three-atoms-and-refs.model)

(def empty-queue clojure.lang.PersistentQueue/EMPTY)

(defn new-vaccination-workforce []
  {:documentation empty-queue
   :triage        empty-queue
   :vaccination   empty-queue
   :exit          empty-queue})
