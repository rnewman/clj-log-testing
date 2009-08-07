(ns testing.clj.test-clj-logging
  (:refer-clojure)
  (:use clojure.contrib.logging)
  (:gen-class))

(defn -main [& args]
  (binding [*out* *err*]
    (println "Starting Clojure test..."))
  (let [start (long (System/currentTimeMillis))]
    (dotimes [x 10000]
      (log :info "Info.")
      (log :error "Error."))
    (await *log-system-agent*)
    (let [stop (long (System/currentTimeMillis))
          runtime (- stop start)]
      (binding [*out* *err*]
        (println "Run time for" (* 10000 2) "log messages:" runtime))))
  (shutdown-agents))
