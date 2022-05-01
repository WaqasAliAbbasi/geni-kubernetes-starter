(ns geni-kubernetes-starter.jobs.job-b.core
  (:require [zero-one.geni.core :as g]
            [geni-kubernetes-starter.spark :as spark]))

(defn start
  "Entrypoint"
  []
  (let [session (spark/spark-session)
        dataframe (g/read-parquet! session "resources/housing.parquet")]
    (-> dataframe
        (g/group-by :ocean_proximity)
        (g/agg {:count        (g/count "*")
                :mean-rooms   (g/mean :total_rooms)
                :distinct-lat (g/count-distinct (g/int :latitude))})
        (g/order-by (g/desc :count))
        g/show)))