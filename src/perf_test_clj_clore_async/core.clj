(ns perf-test-clj-clore-async.core
  (:require [perf-test-clj-clore-async.mocker :as mk])
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "info:\n\n"
           "this program is a basic perf tool for clojure core.async go schedule performance \n\n"
           "you can setting how much go-thread your msg will pass as simple increment to a long value as msg:eg 100,each msg will inc 100 times through 100 go-thread \n\n"
           "and you can setting how much msg you wish to calc in the queue process:eg 10000 \n\n"
           "the third params is for you to setting each channel buffer size:eg 10/100 \n\n"
           "the forth one is core.async pool-size :eg: 8\n\n"
           "the last one is setting for weather you will to mock heavy work in each xform function: eg: true\n")
  (println "Result: latency of your first msg processed,latency of your last msg processed, the total time consumerd to process all your msg")
  (println "eg: lein run 100 10000 10 8 true\n")
  (println "lein run {cnt-go-thread} {cnt-msg} {buffer-size} {pool-size} {true/false as is add heavy task per each go calc function\n")
  (try
    (let [go-cnt      (Integer/parseInt (nth args 0))
          msg-cnt     (Integer/parseInt (nth args 1))
          buffer-size (Integer/parseInt (nth args 2))
          pool-size   (str (nth args 3))
          is-heavy    (Boolean/parseBoolean (last args))]
      (System/setProperty "clojure.core.async.pool-size" pool-size)
      (mk/test-channel go-cnt msg-cnt buffer-size is-heavy))
    (catch Exception e
      (println (.getMessage e))
      (println "please input correct args: \n"
               "eg: lein run 100 10000 10 8 true"))))
