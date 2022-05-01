(ns runner
  (:require [clojure.test.junit :as junit]
            [cognitect.test-runner :as cognitect]
            [clojure.java.io :as io]
            [clojure.test :as test])
  (:import (org.apache.commons.io.output TeeOutputStream)))

(def cognitect-test' cognitect/test)

(defn cognitect-test-junit-output
  [& args]
  (binding [test/*test-out* (io/writer (TeeOutputStream. System/out (io/output-stream "junit.xml")))]
    (junit/with-junit-output
      (apply cognitect-test'
             args))))

(defn -main
  "Entry point for the test runner.
   Takes the same options as cognitect test runner plus the following flags:
     - junit -> Output results in JUnit XML format to standard out and ./junit.xml"
  [& args]
  (let [cognitect-args (filter #(not= "--junit" %) args)
        junit?
        (not= (count args) (count cognitect-args))]
    (with-redefs [cognitect/test (if junit? cognitect-test-junit-output cognitect-test')]
      (apply cognitect/-main cognitect-args))))