(ns three-atoms-and-refs.core
  (:use [clojure pprint])
  (:gen-class)
  (:require [three-atoms-and-refs.model :as v.model]))

(let [sao-carlos (v.model/new-vaccination-workforce)]
  (pprint sao-carlos)
  (print v.model/empty-queue))
