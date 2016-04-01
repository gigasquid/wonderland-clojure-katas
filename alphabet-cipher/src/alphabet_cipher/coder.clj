(ns alphabet-cipher.coder
  (:require [clojure.set]))

(def alphas "abcdefghijklmnopqrstuvwxyz")

(def letters-to-numbers
  (into {} 
        (vec 
          (map vec 
               (partition 2 (interleave (map str alphas) (range 26)))))))

(defn encode [keyword message]
  (loop [kw (take (count message) (cycle keyword))
         msg (seq message)
         emsg ""]
    (if (or (empty? msg) (empty? kw))
      emsg
      (recur (rest kw)
             (rest msg)
             (str
               emsg
               (str
                 (nth
                   (take
                     26 
                     (drop-while 
                       #(not= % (first kw))
                       (cycle (seq alphas))))
                   (letters-to-numbers (str (first msg))))))))))

(defn decode [keyword message]
  (loop [kw (take (count message) (cycle keyword))
         msg (seq message)
         dmsg ""]
    (if (or (empty? msg) (empty? kw))
      dmsg
      (recur (rest kw)
             (rest msg)
             (str
               dmsg
               ((clojure.set/map-invert letters-to-numbers)
                (count
                  (take-while 
                    #(not= % (first msg))
                    (take 
                      26 
                      (drop-while 
                        #(not= % (first kw)) 
                        (cycle (seq alphas))))))))))))

(defn decipher [cipher message]
  (loop [shortestkey (decode message cipher)
         trykey (decode message cipher)]
    (if (empty? trykey)
      shortestkey
      (if (not= message (decode (apply str (drop-last trykey)) cipher))
        (recur shortestkey 
               (apply str (drop-last trykey)))
        (recur (apply str (drop-last trykey)) 
               (apply str (drop-last trykey)))))))
