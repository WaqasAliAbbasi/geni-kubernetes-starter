(ns geni-kubernetes-starter.jobs.job-a.core
  (:require [zero-one.geni.core :as g]
            [geni-kubernetes-starter.spark :as spark]))

(defn start
  "Entrypoint"
  []
  (let [session (spark/spark-session)
        dataframe (g/read-parquet! session "resources/housing.parquet")]
    (-> dataframe
        (g/order-by (g/desc :total_bedrooms))
        g/show)))