(ns testing.clj.test-clj-logging
  (:refer-clojure)
  (:use clojure.contrib.logging)
  (:gen-class))

(defn -main [& args]
  (binding [*out* *err*]
    (println "Starting Clojure test..."))
  
  ;; Forcibly engage agent logging.
  (swap! *allow-direct-logging* (constantly false))
  
  (let [start (long (System/currentTimeMillis))]
    (dotimes [x 10000]
      (log :info "Info.")
      (log :error "Error."))
    
    ;; For agent logging, wait for all log messages to be processed.
    (when (not *allow-direct-logging*)
      (await *logging-agent*))
    
    (let [stop (long (System/currentTimeMillis))
          runtime (- stop start)]
      (binding [*out* *err*]
        (println "Run time for" (* 10000 2) "log messages:" runtime))))
  
  ;; Shutdown cleanly.
  (when (not *allow-direct-logging*)
    (shutdown-agents)))
