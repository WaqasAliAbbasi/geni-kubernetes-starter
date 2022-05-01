(ns geni-kubernetes-starter.core
  (:gen-class)
  (:require [geni-kubernetes-starter.spark :as spark]
            [geni-kubernetes-starter.env :refer [env]]
            [geni-kubernetes-starter.jobs.job-a.core :as job-a]
            [geni-kubernetes-starter.jobs.job-b.core :as job-b]))

(defn -main
  [& _]
  (.stop (spark/spark-session))
  (case (env :use-case)
    "job-a" (job-a/start)
    "job-b" (job-b/start)))
