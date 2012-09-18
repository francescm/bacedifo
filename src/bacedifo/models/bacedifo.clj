(ns bacedifo.models.bacedifo
  (:require [clojure.set])
  )

(def letters (set (map char (range 97 123))))
(def vowels #{\a \e \i \o \u})
(def not-vowels (clojure.set/difference letters vowels))

(defn bacedifo-values[]
  (let [
        start-vowel (int (rand (count vowels)))
        start-not-vowel (int (rand (count not-vowels)))
        vowels-seq (drop start-vowel (cycle vowels))
        not-vowels-seq (drop start-not-vowel (cycle not-vowels))
        vowel-first? (even? (int (rand 2)))
        ]
    (if vowel-first?
      (interleave vowels-seq not-vowels-seq)
      (interleave not-vowels-seq vowels-seq)
      )
    )
  )

(def bacedifo (atom (bacedifo-values)))

(defn init-bacedifo[]
  (reset! bacedifo (bacedifo-values))
  nil
  )

(defn bacedify-char [index char]
  (if (re-matches #"[a-zA-Z]" (str char))
    (nth @bacedifo index)
    char
    )
  )

(defn bacedify-string [text]
  (map-indexed #(bacedify-char %1 %2) text)
  )

(defn bacedify-str [text]
  (loop [t text
         index 0
         res ""
         ]
    (let [char (first t)]
      (if t
        (if (re-matches #"[a-zA-Z]" (str char))
          (recur (next t) (inc index) (str res (nth @bacedifo index)))
          (recur (next t) index (str res char))
          )
        res
        )
      )
    )
  )