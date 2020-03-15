(ns perf-test-clj-clore-async.mocker
  (:require [clojure.core.async :as a]
            )
  (:gen-class))

(defn heavy-task []
  (reduce str ""  (range 10000)))

(defn timestamp []
  (java.util.Date.) )

(defn append-ch [c1 buffer-size with-heavy?]
  (let [c-inc (a/chan buffer-size
                      (map (do
                             (when with-heavy?
                               (heavy-task))
                             inc)))]
    (a/pipe c1 c-inc)
    c-inc))

(defn n-channels [c-in n buffer-size with-heavy?]
  (let [c-o
        (loop [i n ci c-in]
          (let [c-out (append-ch ci buffer-size with-heavy?)]
            (if-not (> i 0)
              c-out
              (recur (dec i) c-out))))]
    c-o))

(defn test-channel [go-thread-cnt msg-cnt buffer-size with-heavy?]
  (let [c-in  (a/chan 10)
        c-out (n-channels c-in go-thread-cnt buffer-size with-heavy?)]
    (println "begin ts:          " (timestamp))
    (a/thread
      (dotimes [i msg-cnt]
        (when (= (inc i) 1)
          (println "first pkg begin-ts:" (timestamp)))
        (when (= (inc i) msg-cnt)
          (println "last pkg begin-ts: " (timestamp)))
        (a/>!! c-in i)))
    (time
     (a/<!!
      (a/thread
        (loop []
          (let [v (a/<!! c-out)]
            (if (= v (+ go-thread-cnt
                        msg-cnt))
              (do
                (println "last pkg end-ts    "(timestamp)))
              (do
                (when (= v (+ 1 go-thread-cnt))
                  (println "first pkg end-ts   " (timestamp)))
                (recur))))))))))
