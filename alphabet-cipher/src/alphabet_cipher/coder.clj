(ns alphabet-cipher.coder)

(defn repeat-keyword [keyword message]
  (let [keyword-count (count keyword)
        message-count (count message)]
    (if (>= keyword-count message-count)
      (subs keyword 0 message-count)
      (recur (str keyword keyword) message))))

(defn encode [keyword message]
  "encodeme")

(defn decode [keyword message]
  "decodeme")

(defn decipher [cipher message]
  "decypherme")
