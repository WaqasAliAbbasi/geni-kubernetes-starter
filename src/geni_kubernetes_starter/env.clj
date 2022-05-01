(ns ^{:author "George Narroway"} geni-kubernetes-starter.env
  "Environment utilities"
  (:require [clojure.string :as str]))

(defn- keywordize
  [s]
  (-> (str/lower-case s)
      (str/replace "_" "-")
      (str/replace "." "-")
      (keyword)))


(defn- read-env
  "Creates an environment map of environment variables overlaid with JVM properties.
   Returns a map of keyword keys to string values"
  []
  (merge (->> (System/getenv)
              (map (fn [[k v]] [(keywordize k) v]))
              (into {}))
         (->> (System/getProperties)
              (map (fn [[k v]] [(keywordize k) v]))
              (into {}))))

(defonce env (read-env))