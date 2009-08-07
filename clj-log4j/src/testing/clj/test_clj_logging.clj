(ns testing.clj.test-clj-logging
  (:refer-clojure)
  (:import org.apache.log4j.Logger)
  (:gen-class))

(defn -main [& args]
  (let [#^Logger log (Logger/getLogger "testing.java.test_clj_logging")]
    (binding [*out* *err*]
      (println "Starting Clojure test..."))
    (let [start (long (System/currentTimeMillis))]
      (dotimes [x 10000]
        (.info log "Info.")
        (.error log "Error."))
      (let [stop (long (System/currentTimeMillis))
            runtime (- stop start)]
        (binding [*out* *err*]
          (println "Run time for" (* 10000 2) "log messages:" runtime))))))
