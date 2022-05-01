(ns geni-kubernetes-starter.spark
  (:require [zero-one.geni.spark :as gs]
            [geni-kubernetes-starter.env :refer [env]]))

(defn spark-session
  "Gets a spark session"
  []
  (let [master (env :spark-master)
        _ (println "spark-master" master)
        s (gs/create-spark-session
           (cond-> {:app-name (or (env :use-case) "gumi")
                    :log-level "INFO"
                    :configs {:spark.sql.files.ignoreCorruptFiles "true"
                              :spark.sql.sources.partitionColumnTypeInference.enabled "false"}}
                          ;Explicitly set master because geni defaults it to local[*] which does not create executors in kubernetes
             master (assoc :master master)))]
    (println "Spark session" (gs/spark-conf s))
    s))